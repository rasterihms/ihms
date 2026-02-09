/*******************************************************************************
 *  CONFIDENTIAL AND PROPRIETARY
 *
 *  The source code and other information contained herein is the confidential and the exclusive property of
 *  Raster Images Pvt. Ltd. and is subject to the terms and conditions in your end user license agreement.
 *  This source code, and any other information contained herein, shall not be copied, reproduced, published,
 *  displayed or distributed, in whole or in part, in any medium, by any means, for any purpose except as
 *  expressly permitted under such license agreement.
 *
 *  Copyright Raster Images Pvt. Ltd.
 *
 *  ALL RIGHTS RESERVED
 *******************************************************************************/
package in.raster.ihms.authserver.ldap.service.impl;

import in.raster.ihms.authserver.domain.custom.*;
import in.raster.ihms.authserver.ldap.domain.*;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.authserver.ldap.repository.LdapUserRepository;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.ldap.util.ApacheDSLdapDirectories;
import in.raster.ihms.authserver.ldap.util.LdapUtil;
import in.raster.ihms.authserver.mapper.LdapContextMapper;
import in.raster.ihms.authserver.util.DateUtil;
import in.raster.ihms.authserver.util.ExceptionConstants;
import in.raster.ihms.authserver.util.ExceptionUtil;
import in.raster.ihms.commons.security.JwtTokenUtil;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.exceptions.custom.InvalidCredentialsException;
import in.raster.ihms.exceptions.custom.ObjectAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.ldap.NameAlreadyBoundException;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.*;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.naming.Name;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Ldap custom service implementation for interfacing with ldap server.
 */
@Service
public class LdapCustomServiceImpl implements LdapCustomService {

    private static final Logger logger = LoggerFactory.getLogger(LdapCustomServiceImpl.class);
    private static final String USER_UNIT = "ldap-defaults.user-unit";
    private static final String BRANCH_UNIT = "ldap-defaults.organizational-units.branches";
    private static final String APPLICATION_UNIT = "ldap-defaults.application-unit";
    private static final String DEPARTMENT_UNIT = "ldap-defaults.organizational-units.departments";
    private static final String LDAP_PARTITION_SUFFIX = "ldap.partitionSuffix";

    @Value("${ldap-defaults.login.username}")
    private String adminUserName;

    @Value("${ldap-defaults.login.role}")
    private String adminUserRole;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private LdapUtil ldapUtil;

    @Autowired
    private Environment env;

    @Autowired
    private ContextSource contextSource;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LdapUserRepository ldapUserRepository;

    /**
     * Authenticate user credentials.
     *
     * @param username - username
     * @param password - password
     * @return - boolean value indicates valid user or not
     * @throws - InvalidCredentialsException
     */
    @Override
    public boolean authenticate(final String username, final String password) throws InvalidCredentialsException {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.UID.getAttribute(), username));
        final LdapUser ldapUser = ldapUserRepository.findByUsername(username);
        final Name dn = LdapNameBuilder.newInstance(env.getRequiredProperty(USER_UNIT))
            .add(ApacheDSLdapDirectories.UID.getAttribute(), username).build();
        if (!ObjectUtils.isEmpty(ldapUser.getPwdAccountLockedTime())) {
            ModificationItem[] modificationItems = new ModificationItem[1];
            modificationItems[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute(ApacheDSLdapDirectories.PWD_ACCOUNT_LOCKED_TIME.getAttribute()));
            ldapTemplate.modifyAttributes(dn, modificationItems);
        }
        final boolean isValidUser = ldapTemplate.authenticate(CommonConstants.EMPTY_STRING, filter.encode(), password);
        if (!isValidUser) {
            throw new InvalidCredentialsException(ExceptionConstants.INCORRECT_USER_NAME_PASSWORD);
        }
        return isValidUser;
    }

    /**
     * Query method to find unique groups in LDAP.
     *
     * @param member - member dn
     * @return list of ldap group
     */
    @Override
    public List<LdapGroup> getLdapGroupsByMemberDn(final String member) {
        final String memberWithBaseDn = ldapUtil.appendBaseDnWithGivenDn(member);
        return ldapTemplate.search(
            query().where(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute())
                .is(ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute())
                .and(ApacheDSLdapDirectories.MEMBER.getAttribute()).is(memberWithBaseDn),
            LdapContextMapper.LDAP_GROUP_CONTEXT_MAPPER);
    }

    /**
     * Query method to find unique groups(Branches) in LDAP.
     *
     * @param userDn         - user distinguished name
     * @param organizationDn - organization distinguished name
     * @return list of ldap group
     */
    @Override
    public List<LdapGroup> getBranchesByUserDn(final String userDn, final String organizationDn) {
        final String memberWithBaseDn = ldapUtil.appendBaseDnWithGivenDn(userDn);
        return ldapTemplate.search(
            query().base(env.getRequiredProperty(BRANCH_UNIT) + CommonConstants.COMMA_SEPARATOR + organizationDn)
                .where(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute())
                .is(ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute())
                .and(ApacheDSLdapDirectories.MEMBER.getAttribute()).is(memberWithBaseDn)
                .and(ApacheDSLdapDirectories.ACTIVE.getAttribute()).is(String.valueOf(CommonConstants.TRUE)),
            LdapContextMapper.LDAP_GROUP_CONTEXT_MAPPER);
    }

    /**
     * Get applications by user dn and branch dn.
     *
     * @param userDn     - user distinguished name
     * @param branchName - branch name
     * @return list of ldap application
     */
    @Override
    public List<LdapApplication> getApplicationsByUserAndBranch(final String userDn, final String branchName,
                                                                final String organizationName) {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_APPLICATION.getAttribute()));
        if (!ObjectUtils.isEmpty(userDn)) {
            final String userDnWithBaseDn = ldapUtil.appendBaseDnWithGivenDn(userDn);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), userDnWithBaseDn));
        }
        if (!ObjectUtils.isEmpty(branchName) && !ObjectUtils.isEmpty(organizationName)) {
            final String branchDn = ldapUtil.buildBranchDn(branchName, organizationName);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), branchDn));
        }
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(CommonConstants.TRUE)));
        return ldapTemplate.search(env.getRequiredProperty(APPLICATION_UNIT), filter.encode(),
            LdapContextMapper.LDAP_APPLICATION_CONTEXT_MAPPER);
    }

    /**
     * Get roles by user.
     *
     * @param userDn          - user distinguished name
     * @param branchName      - branch name
     * @param applicationName - base dn to be searched
     * @return list of ldap roles
     */
    @Override
    public List<LdapRole> getRolesByUserBranchAndApplication(final String userDn, final String branchName,
                                                             final String organizationName,
                                                             final String applicationName) {
        final String applicationDn = ldapUtil.buildApplicationDn(applicationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_ROLE.getAttribute()));
        if (!ObjectUtils.isEmpty(userDn)) {
            final String userDnWithBaseDn = ldapUtil.appendBaseDnWithGivenDn(userDn);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), userDnWithBaseDn));
        }
        if (!ObjectUtils.isEmpty(branchName)) {
            final String branchDn = ldapUtil.buildBranchDn(branchName, organizationName);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), branchDn));
        }
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(CommonConstants.TRUE)));
        filter.and(new NotFilter(new EqualsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), adminUserRole)));
        return ldapTemplate.search(applicationDn, filter.encode(), LdapContextMapper.LDAP_ROLE_CONTEXT_MAPPER);
    }

    /**
     * Get all roles by user.
     *
     * @param userDn          - user distinguished name
     * @param branchName      - branch name
     * @param applicationName - base dn to be searched
     * @return list of ldap roles
     */
    @Override
    public List<LdapRole> getAllRolesByUserBranchAndApplication(final String userDn, final String branchName,
                                                                final String organizationName,
                                                                final String applicationName) {
        final String applicationDn = ldapUtil.buildApplicationDn(applicationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_ROLE.getAttribute()));
        if (!ObjectUtils.isEmpty(userDn)) {
            final String userDnWithBaseDn = ldapUtil.appendBaseDnWithGivenDn(userDn);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), userDnWithBaseDn));
        }
        if (!ObjectUtils.isEmpty(branchName)) {
            final String branchDn = ldapUtil.buildBranchDn(branchName, organizationName);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), branchDn));
        }
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(CommonConstants.TRUE)));
        return ldapTemplate.search(applicationDn, filter.encode(), LdapContextMapper.LDAP_ROLE_CONTEXT_MAPPER);
    }

    /**
     * Get menus by user role.
     *
     * @param roleDn          - role distinguished name
     * @param applicationName - base dn to be searched
     * @return list of ldap menus
     */
    @Override
    public List<LdapMenu> getMenusByUserRole(final String roleDn,
                                             final String applicationName) {
        final String roleDnWithBaseDn = ldapUtil.appendBaseDnWithGivenDn(roleDn);
        final String applicationDn = ldapUtil.buildApplicationDn(applicationName);
        return ldapTemplate.search(
            query().base(applicationDn).where(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute())
                .is(ApacheDSLdapDirectories.IHMS_MENU.getAttribute())
                .and(ApacheDSLdapDirectories.MEMBER.getAttribute()).is(roleDnWithBaseDn)
                .and(ApacheDSLdapDirectories.ACTIVE.getAttribute()).is(String.valueOf(CommonConstants.TRUE)),
            LdapContextMapper.LDAP_MENU_CONTEXT_MAPPER);
    }

    /**
     * Get resources by user role.
     *
     * @param roleDn          - role distinguished name
     * @param applicationName - application name
     * @return list of resource
     */
    @Override
    public List<LdapResource> getResourcesByUserRole(final String roleDn,
                                                     final String applicationName) {
        final String roleDnWithBaseDn = ldapUtil.appendBaseDnWithGivenDn(roleDn);
        final String applicationDn = ldapUtil.buildApplicationDn(applicationName);
        return ldapTemplate.search(
            query().base(applicationDn).where(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute())
                .is(ApacheDSLdapDirectories.IHMS_RESOURCE.getAttribute())
                .and(ApacheDSLdapDirectories.MEMBER.getAttribute()).is(roleDnWithBaseDn)
                .and(ApacheDSLdapDirectories.ACTIVE.getAttribute()).is(String.valueOf(CommonConstants.TRUE)),
            LdapContextMapper.LDAP_RESOURCE_CONTEXT_MAPPER);
    }

    /**
     * Get active resources by application.
     *
     * @param applicationName - application name
     * @return list of resource
     */
    @Override
    public List<LdapResource> getActiveResourcesByApplication(final String applicationName) {
        final String applicationDn = ldapUtil.buildApplicationDn(applicationName);
        return ldapTemplate.search(
            query().base(applicationDn).where(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute())
                .is(ApacheDSLdapDirectories.IHMS_RESOURCE.getAttribute())
                .and(ApacheDSLdapDirectories.ACTIVE.getAttribute()).is(String.valueOf(CommonConstants.TRUE)),
            LdapContextMapper.LDAP_RESOURCE_CONTEXT_MAPPER);
    }

    /**
     * Create ldap user.
     *
     * @param user - user object
     * @return distinguished name created in ldap
     */
    @Override
    public String createOrganizationUser(final User user)
        throws ObjectAlreadyExistsException {
        final Name dn = LdapNameBuilder.newInstance(env.getRequiredProperty(USER_UNIT))
            .add(ApacheDSLdapDirectories.UID.getAttribute(), user.getUserName()).build();
        final DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            new String[]{ApacheDSLdapDirectories.TOP.getAttribute(),
                ApacheDSLdapDirectories.PERSON.getAttribute(),
                ApacheDSLdapDirectories.ORGANIZATIONAL_PERSON.getAttribute(),
                ApacheDSLdapDirectories.INET_ORG_PERSON.getAttribute(),
                ApacheDSLdapDirectories.IHMS_USER.getAttribute(),
                ApacheDSLdapDirectories.BASE_ENTITY.getAttribute()});
        context.setAttributeValue(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), user.getUserName());
        context.setAttributeValue(ApacheDSLdapDirectories.SUR_NAME.getAttribute(), user.getSurName());
        context.setAttributeValue(ApacheDSLdapDirectories.UID.getAttribute(), user.getUserName());
        context.setAttributeValue(ApacheDSLdapDirectories.USER_PASSWORD.getAttribute(),
            ldapUtil.digestSHA(user.getPassword()));
        context.setAttributeValue(ApacheDSLdapDirectories.MAIL.getAttribute(), user.getMail());
        context.setAttributeValue(ApacheDSLdapDirectories.MOBILE.getAttribute(), user.getMobileNumber());
        context.setAttributeValue(ApacheDSLdapDirectories.EMPLOYEE_NUMBER.getAttribute(), user.getEmployeeNumber());
        context.setAttributeValue(ApacheDSLdapDirectories.IS_CREDIT_AUTHORIZED_USER.getAttribute(),
            String.valueOf(user.isCreditAuthorizedUser()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREDIT_AMOUNT_LIMIT.getAttribute(), String.valueOf(user.getCreditAmountLimit()));
        if (!ObjectUtils.isEmpty(user.getUserType())) {
            context.setAttributeValue(ApacheDSLdapDirectories.USER_TYPE.getAttribute(), String.valueOf(user.getUserType()));
        }
        if (!ObjectUtils.isEmpty(user.getDoctorId())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DOCTOR_ID.getAttribute(), String.valueOf(user.getDoctorId()));
        }
        if (!ObjectUtils.isEmpty(user.getSignFilePath())) {
            context.setAttributeValue(ApacheDSLdapDirectories.SIGN_FILE_PATH.getAttribute(), String.valueOf(user.getSignFilePath()));
        }
        if (!ObjectUtils.isEmpty(user.getPhotoFilePath())) {
            context.setAttributeValue(ApacheDSLdapDirectories.PHOTO_FILE_PATH.getAttribute(), String.valueOf(user.getPhotoFilePath()));
        }
        if (!ObjectUtils.isEmpty(user.getDepartmentIds())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DEPARTMENT_NUMBER.getAttribute(), String.valueOf(user.getDepartmentIds()));
        }
        if (!ObjectUtils.isEmpty(user.getDefaultLocationId())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DEFAULT_LOCATION_ID.getAttribute(), String.valueOf(user.getDefaultLocationId()));
        }
        if (!ObjectUtils.isEmpty(user.getLocationIds())) {
            context.setAttributeValue(ApacheDSLdapDirectories.LOCATION_IDS.getAttribute(), String.valueOf(user.getLocationIds()));
        }
        if (!ObjectUtils.isEmpty(user.getExternalUserId())) {
            context.setAttributeValue(ApacheDSLdapDirectories.EXTERNAL_USER_ID.getAttribute(), String.valueOf(user.getExternalUserId()));
        }
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(user.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(user.getCreatedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(user.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(user.getCreatedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(user.getModifiedBy()));
        logger.info("----> " + dn);
        try {
            ldapTemplate.bind(context);
        } catch (NameAlreadyBoundException nameAlreadyBoundException) {
            ExceptionUtil.throwObjectAlreadyExistsException(ExceptionConstants.USER_ALREADY_EXISTS);
        } catch (NamingException e) {
            logger.error("Naming Exception " + e);
        }
        return dn.toString();
    }

    /**
     * Modify ldap user attributes.
     *
     * @param user - user object
     */
    @Override
    public void modifyUserAttributes(final User user) {
        final DirContextOperations context = ldapTemplate.lookupContext(user.getId());
        context.setAttributeValue(ApacheDSLdapDirectories.SUR_NAME.getAttribute(), user.getSurName());
        if (!ObjectUtils.isEmpty(user.getPassword())) {
            context.setAttributeValue(ApacheDSLdapDirectories.USER_PASSWORD.getAttribute(), user.getPassword());
        }
        context.setAttributeValue(ApacheDSLdapDirectories.IS_CREDIT_AUTHORIZED_USER.getAttribute(),
            String.valueOf(user.isCreditAuthorizedUser()));
        if (user.isCreditAuthorizedUser()) {
            context.setAttributeValue(ApacheDSLdapDirectories.CREDIT_AMOUNT_LIMIT.getAttribute(),
                String.valueOf(user.getCreditAmountLimit()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.CREDIT_AMOUNT_LIMIT.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getUserType())) {
            context.setAttributeValue(ApacheDSLdapDirectories.USER_TYPE.getAttribute(), String.valueOf(user.getUserType()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.USER_TYPE.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getDoctorId())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DOCTOR_ID.getAttribute(), String.valueOf(user.getDoctorId()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.DOCTOR_ID.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getMail())) {
            context.setAttributeValue(ApacheDSLdapDirectories.MAIL.getAttribute(), String.valueOf(user.getMail()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.MAIL.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getMobileNumber())) {
            context.setAttributeValue(ApacheDSLdapDirectories.MOBILE.getAttribute(), String.valueOf(user.getMobileNumber()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.MOBILE.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getEmployeeNumber())) {
            context.setAttributeValue(ApacheDSLdapDirectories.EMPLOYEE_NUMBER.getAttribute(), String.valueOf(user.getEmployeeNumber()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.EMPLOYEE_NUMBER.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getDepartmentIds())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DEPARTMENT_NUMBER.getAttribute(), String.valueOf(user.getDepartmentIds()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.DEPARTMENT_NUMBER.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getSignFilePath())) {
            context.setAttributeValue(ApacheDSLdapDirectories.SIGN_FILE_PATH.getAttribute(), String.valueOf(user.getSignFilePath()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.SIGN_FILE_PATH.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getPhotoFilePath())) {
            context.setAttributeValue(ApacheDSLdapDirectories.PHOTO_FILE_PATH.getAttribute(), String.valueOf(user.getPhotoFilePath()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.PHOTO_FILE_PATH.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getDefaultLocationId())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DEFAULT_LOCATION_ID.getAttribute(), String.valueOf(user.getDefaultLocationId()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.DEFAULT_LOCATION_ID.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getLocationIds())) {
            context.setAttributeValue(ApacheDSLdapDirectories.LOCATION_IDS.getAttribute(), String.valueOf(user.getLocationIds()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.LOCATION_IDS.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getExternalUserId())) {
            context.setAttributeValue(ApacheDSLdapDirectories.EXTERNAL_USER_ID.getAttribute(), String.valueOf(user.getExternalUserId()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.EXTERNAL_USER_ID.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(user.getThemeColor())) {
            context.setAttributeValue(ApacheDSLdapDirectories.THEME_COLOR.getAttribute(), String.valueOf(user.getThemeColor()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.THEME_COLOR.getAttribute(), null);
        }
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(user.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(user.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(user.getModifiedBy()));
        ldapTemplate.modifyAttributes(context);
    }

    /**
     * Modify ldap user attributes.
     *
     * @param user - user object
     */
    @Override
    public void updateUserPassword(final User user) {
        final Name dn = LdapNameBuilder.newInstance(env.getRequiredProperty(USER_UNIT))
            .add(ApacheDSLdapDirectories.UID.getAttribute(), user.getUserName()).build();
        final DirContextOperations context = ldapTemplate.lookupContext(user.getId());
        if (!ObjectUtils.isEmpty(user.getPassword())) {
            context.setAttributeValue(ApacheDSLdapDirectories.USER_PASSWORD.getAttribute(), user.getPassword());
            ldapTemplate.modifyAttributes(context);
        }
        final LdapUser ldapUser = ldapUserRepository.findByUsername(user.getUserName());
        if (!ObjectUtils.isEmpty(ldapUser.getPwdAccountLockedTime())) {
            ModificationItem[] modificationItems = new ModificationItem[1];
            modificationItems[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute(ApacheDSLdapDirectories.PWD_ACCOUNT_LOCKED_TIME.getAttribute()));
            ldapTemplate.modifyAttributes(dn, modificationItems);
        }
    }

    /**
     * Create ldap branch.
     *
     * @param branch           - branch object
     * @param organizationName - organization name
     * @return distinguished name created in ldap
     */
    @Override
    public String createOrganizationBranch(final Branch branch, final String organizationName)
        throws ObjectAlreadyExistsException {
        final Name dn = LdapNameBuilder.newInstance(env.getRequiredProperty(BRANCH_UNIT) +
            CommonConstants.COMMA_SEPARATOR + ldapUtil.buildOrganizationDn(organizationName))
            .add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), branch.getName()).build();
        final DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            new String[]{ApacheDSLdapDirectories.TOP.getAttribute(),
                ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute(), ApacheDSLdapDirectories.BASE_ENTITY.getAttribute()});
        context.setAttributeValue(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), branch.getName());
        context.setAttributeValue(ApacheDSLdapDirectories.CODE.getAttribute(), branch.getCode());
        context.setAttributeValue(ApacheDSLdapDirectories.TIME_ZONE.getAttribute(), branch.getTimeZone());
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(branch.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(branch.getCreatedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(branch.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(branch.getCreatedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(branch.getModifiedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MEMBER.getAttribute(), ldapUtil.getAdminUserDn());
        try {
            ldapTemplate.bind(context);
        } catch (NameAlreadyBoundException nameAlreadyBoundException) {
            ExceptionUtil.throwObjectAlreadyExistsException(ExceptionConstants.BRANCH_ALREADY_EXISTS);
        } catch (NamingException e) {
            logger.info("Naming Exception " + e);
        }
        return dn.toString();
    }

    /**
     * Modify ldap branch attributes.
     *
     * @param branch - branch object
     */
    @Override
    public void modifyBranchAttributes(final Branch branch) {
        final DirContextOperations context = ldapTemplate.lookupContext(branch.getId());
        if (!ObjectUtils.isEmpty(branch.getCode())) {
            context.setAttributeValue(ApacheDSLdapDirectories.CODE.getAttribute(), branch.getCode());
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.CODE.getAttribute(), null);
        }
        context.setAttributeValue(ApacheDSLdapDirectories.TIME_ZONE.getAttribute(), branch.getTimeZone());
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(branch.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(branch.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(branch.getModifiedBy()));
        ldapTemplate.modifyAttributes(context);
    }

    /**
     * Get all menus by application id.
     *
     * @param applicationName - application name
     * @param menuName        - menu name
     * @param parentMenuName  - parent menu name
     * @param active          - active flag
     * @return list of ldap menus
     */
    @Override
    public List<LdapMenu> getApplicationMenus(final String applicationName, final String menuName,
                                              final String parentMenuName, final Boolean active) {
        final String applicationDn = ldapUtil.buildApplicationDn(applicationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_MENU.getAttribute()));
        filter.and(new WhitespaceWildcardsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), menuName));
        if (!ObjectUtils.isEmpty(parentMenuName))
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.PARENT_MENU.getAttribute(),
                ldapUtil.buildParentMenuDn(parentMenuName, applicationDn)));
        if (!ObjectUtils.isEmpty(active))
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(active)));
        return ldapTemplate.search(applicationDn, filter.encode(), LdapContextMapper.LDAP_MENU_CONTEXT_MAPPER);
    }

    /**
     * Get all resources by application id.
     *
     * @param applicationName - application name
     * @param resourceName    - resource name
     * @return list of ldap resources
     */
    @Override
    public List<LdapResource> getApplicationResources(final String applicationName, final String resourceName) {
        final String applicationDn = ldapUtil.buildApplicationDn(applicationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_RESOURCE.getAttribute()));
        filter.and(new WhitespaceWildcardsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), resourceName));
        return ldapTemplate.search(applicationDn, filter.encode(), LdapContextMapper.LDAP_RESOURCE_CONTEXT_MAPPER);
    }

    /**
     * Get roles by application.
     *
     * @param applicationName  - application name
     * @param roleName         - role name
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of ldap roles
     */
    @Override
    public List<LdapRole> getApplicationRoles(final String applicationName, final String roleName,
                                              final String branchName, final String organizationName) {
        final String applicationDn = ldapUtil.buildApplicationDn(applicationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_ROLE.getAttribute()));
        if (!ObjectUtils.isEmpty(branchName) && !ObjectUtils.isEmpty(organizationName)) {
            final String branchDn = ldapUtil.buildBranchDn(branchName, organizationName);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), branchDn));
        }
        filter.and(new WhitespaceWildcardsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), roleName));
        filter.and(new NotFilter(new EqualsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), adminUserRole)));
        return ldapTemplate.search(applicationDn, filter.encode(), LdapContextMapper.LDAP_ROLE_CONTEXT_MAPPER);
    }

    /**
     * Get role by name.
     *
     * @param applicationName  - application name
     * @param roleName         - role name
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of ldap roles
     */
    @Override
    public List<LdapRole> getLdapRoleByName(final String applicationName, final String roleName,
                                            final String branchName, final String organizationName) {
        final String applicationDn = ldapUtil.buildApplicationDn(applicationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_ROLE.getAttribute()));
        if (!ObjectUtils.isEmpty(branchName) && !ObjectUtils.isEmpty(organizationName)) {
            final String branchDn = ldapUtil.buildBranchDn(branchName, organizationName);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), branchDn));
        }
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), roleName));
        return ldapTemplate.search(applicationDn, filter.encode(), LdapContextMapper.LDAP_ROLE_CONTEXT_MAPPER);
    }

    /**
     * Create ldap menu.
     *
     * @param menu - menu object
     * @return distinguished name created in ldap
     */
    @Override
    public String createApplicationMenu(final Menu menu)
        throws ObjectAlreadyExistsException {
        final String applicationDn = ldapUtil.buildApplicationDn(menu.getApplicationName());
        final String defaultRoleDn = ldapUtil.buildDefaultRoleDn(applicationDn);
        final Name dn = LdapNameBuilder.newInstance(applicationDn)
            .add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), ApacheDSLdapDirectories.MENU_ROOT.getAttribute())
            .add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), menu.getName()).build();
        final DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            new String[]{ApacheDSLdapDirectories.TOP.getAttribute(),
                ApacheDSLdapDirectories.IHMS_MENU.getAttribute(), ApacheDSLdapDirectories.BASE_ENTITY.getAttribute()});
        context.setAttributeValue(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), menu.getName());
        if (!ObjectUtils.isEmpty(menu.getDisplayName())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DISPLAY_NAME.getAttribute(), menu.getDisplayName());
        }
        context.setAttributeValue(ApacheDSLdapDirectories.MENU_ORDER.getAttribute(), String.valueOf(menu.getOrder()));
        context.setAttributeValue(ApacheDSLdapDirectories.MENU_ICON.getAttribute(), menu.getIcon());
        context.setAttributeValue(ApacheDSLdapDirectories.URL.getAttribute(), menu.getUrl());
        if (!ObjectUtils.isEmpty(menu.getParentMenu())) {
            context.setAttributeValue(ApacheDSLdapDirectories.PARENT_MENU.getAttribute(),
                ldapUtil.buildParentMenuDn(menu.getParentMenu(), applicationDn));
        }
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(menu.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(menu.getCreatedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(menu.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(menu.getCreatedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(menu.getModifiedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MEMBER.getAttribute(), defaultRoleDn);
        try {
            ldapTemplate.bind(context);
        } catch (NameAlreadyBoundException nameAlreadyBoundException) {
            ExceptionUtil.throwObjectAlreadyExistsException(ExceptionConstants.MENU_ALREADY_EXISTS);
        } catch (NamingException e) {
            logger.info("Naming Exception " + e);
        }
        return dn.toString();
    }

    /**
     * Modify ldap menu attributes.
     *
     * @param menu - menu object
     */
    @Override
    public void modifyMenuAttributes(final Menu menu) {
        final String applicationDn = ldapUtil.buildApplicationDn(menu.getApplicationName());
        final DirContextOperations context = ldapTemplate.lookupContext(menu.getId());
        if (!ObjectUtils.isEmpty(menu.getDisplayName())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DISPLAY_NAME.getAttribute(), menu.getDisplayName());
        }
        context.setAttributeValue(ApacheDSLdapDirectories.MENU_ORDER.getAttribute(), String.valueOf(menu.getOrder()));
        if (!ObjectUtils.isEmpty(menu.getIcon())) {
            context.setAttributeValue(ApacheDSLdapDirectories.MENU_ICON.getAttribute(), menu.getIcon());
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.MENU_ICON.getAttribute(), null);
        }
        context.setAttributeValue(ApacheDSLdapDirectories.URL.getAttribute(), menu.getUrl());
        if (!ObjectUtils.isEmpty(menu.getParentMenu())) {
            context.setAttributeValue(ApacheDSLdapDirectories.PARENT_MENU.getAttribute(),
                ldapUtil.buildParentMenuDn(menu.getParentMenu(), applicationDn));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.PARENT_MENU.getAttribute(), null);
        }
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(menu.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(menu.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(menu.getModifiedBy()));
        ldapTemplate.modifyAttributes(context);
    }

    /**
     * Create ldap role.
     *
     * @param role             - role object
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return distinguished name created in ldap
     */
    @Override
    public String createApplicationRole(final Role role, final String branchName, final String organizationName)
        throws ObjectAlreadyExistsException {
        final String applicationDn = ldapUtil.buildApplicationDn(role.getApplicationName());
        final String branchDn = ldapUtil.buildBranchDn(branchName, organizationName);
        final String[] roleMembers = {ldapUtil.getAdminUserDn(), branchDn};
        final Name dn = LdapNameBuilder.newInstance(applicationDn)
            .add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), ApacheDSLdapDirectories.ROLE_ROOT.getAttribute())
            .add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), role.getName()).build();
        final DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            new String[]{ApacheDSLdapDirectories.TOP.getAttribute(),
                ApacheDSLdapDirectories.IHMS_ROLE.getAttribute(), ApacheDSLdapDirectories.BASE_ENTITY.getAttribute()});
        context.setAttributeValue(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), role.getName());
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(role.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(role.getCreatedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(role.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(role.getCreatedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(role.getModifiedBy()));
        context.setAttributeValues(ApacheDSLdapDirectories.MEMBER.getAttribute(), roleMembers);
        try {
            ldapTemplate.bind(context);
        } catch (NameAlreadyBoundException nameAlreadyBoundException) {
            ExceptionUtil.throwObjectAlreadyExistsException(ExceptionConstants.ROLE_ALREADY_EXISTS);
        } catch (NamingException e) {
            logger.info("Naming Exception " + e);
        }
        return dn.toString();
    }

    /**
     * Add given member id to the group.
     *
     * @param memberDn - member distinguished name
     * @param groupDn  - can be branch, application, menu or resource id
     */
    @Override
    public void addMemberToGroup(final String memberDn, final String groupDn) {
        final DirContextOperations context = ldapTemplate.lookupContext(groupDn);
        context.addAttributeValue(ApacheDSLdapDirectories.MEMBER.getAttribute(), ldapUtil.appendBaseDnWithGivenDn(memberDn));
        ldapTemplate.modifyAttributes(context);
    }

    /**
     * Remove given member from the group.
     *
     * @param memberDn - member distinguished name
     * @param groupDn  - can be branch, application, menu or resource id
     */
    @Override
    public void removeMemberFromGroup(final String memberDn, final String groupDn) {
        final DirContextOperations context = ldapTemplate.lookupContext(groupDn);
        context.removeAttributeValue(ApacheDSLdapDirectories.MEMBER.getAttribute(), ldapUtil.appendBaseDnWithGivenDn(memberDn));
        ldapTemplate.modifyAttributes(context);
    }

    /**
     * Create ldap resource.
     *
     * @param resource - resource object
     * @return distinguished name created in ldap
     */
    @Override
    public String createApplicationResource(final Resource resource)
        throws ObjectAlreadyExistsException {
        final String applicationDn = ldapUtil.buildApplicationDn(resource.getApplicationName());
        final String defaultRoleDn = ldapUtil.buildDefaultRoleDn(applicationDn);
        final Name dn = LdapNameBuilder.newInstance(applicationDn)
            .add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), ApacheDSLdapDirectories.MENU_ROOT.getAttribute())
            .add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), resource.getMenuId())
            .add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), resource.getName()).build();
        final DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            new String[]{ApacheDSLdapDirectories.TOP.getAttribute(),
                ApacheDSLdapDirectories.IHMS_RESOURCE.getAttribute(), ApacheDSLdapDirectories.BASE_ENTITY.getAttribute()});
        context.setAttributeValue(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), resource.getName());
        context.setAttributeValue(ApacheDSLdapDirectories.PERMISSION_TYPE.getAttribute(), resource.getPermissionType());
        context.setAttributeValue(ApacheDSLdapDirectories.URL.getAttribute(), resource.getUrl());
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(resource.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(resource.getCreatedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(resource.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(resource.getCreatedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(resource.getModifiedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MEMBER.getAttribute(), defaultRoleDn);
        try {
            ldapTemplate.bind(context);
        } catch (NameAlreadyBoundException nameAlreadyBoundException) {
            ExceptionUtil.throwObjectAlreadyExistsException(ExceptionConstants.RESOURCE_ALREADY_EXISTS);
        } catch (NamingException e) {
            logger.info("Naming Exception " + e);
        }
        return dn.toString();
    }

    /**
     * Modify ldap resource attributes.
     *
     * @param resource - resource object
     */
    @Override
    public void modifyResourceAttributes(final Resource resource) {
        final DirContextOperations context = ldapTemplate.lookupContext(resource.getId());
        context.setAttributeValue(ApacheDSLdapDirectories.PERMISSION_TYPE.getAttribute(), resource.getPermissionType());
        context.setAttributeValue(ApacheDSLdapDirectories.URL.getAttribute(), resource.getUrl());
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(resource.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(resource.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(resource.getModifiedBy()));
        ldapTemplate.modifyAttributes(context);
    }

    /**
     * Delete ldap resource by given dn.
     *
     * @param resourceName    - resource name
     * @param applicationName - application name
     * @param menuName        - menu name
     */
    @Override
    public void deleteApplicationResource(final String resourceName,
                                          final String applicationName,
                                          final String menuName) {
        final String resourceDn = ldapUtil.buildResourceDn(resourceName, applicationName, menuName);
        ldapTemplate.unbind(resourceDn);
    }

    /**
     * Query method to find all branches in LDAP.
     *
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of ldap group
     */
    @Override
    public List<LdapGroup> getAllBranches(final String branchName, final String organizationName) {
        return ldapTemplate.search(
            query().base(env.getRequiredProperty(BRANCH_UNIT) +
                CommonConstants.COMMA_SEPARATOR + ldapUtil.buildOrganizationDn(organizationName))
                .where(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute())
                .is(ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute())
                .and(ApacheDSLdapDirectories.COMMON_NAME.getAttribute()).whitespaceWildcardsLike(branchName),
            LdapContextMapper.LDAP_GROUP_CONTEXT_MAPPER);
    }

    /**
     * Get all users.
     *
     * @param userName       - user name
     * @param departmentName - department name
     * @param userType       - user type
     * @param active         - active
     * @return user list
     */
    @Override
    public List<LdapUser> getUsers(final String userName, final String departmentName,
                                   final UserType userType, final String active) {
        final AndFilter andFilter = new AndFilter();
        final OrFilter orFilter = new OrFilter();
        andFilter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.INET_ORG_PERSON.getAttribute()));
        orFilter.or(new WhitespaceWildcardsFilter(ApacheDSLdapDirectories.UID.getAttribute(), userName));
        orFilter.or(new WhitespaceWildcardsFilter(ApacheDSLdapDirectories.SUR_NAME.getAttribute(), userName));
        if (!ObjectUtils.isEmpty(departmentName)) {
            andFilter.and(new WhitespaceWildcardsFilter(ApacheDSLdapDirectories.DEPARTMENT_NUMBER.getAttribute(), departmentName));
        }
        if (!ObjectUtils.isEmpty(userType)) {
            andFilter.and(new EqualsFilter(ApacheDSLdapDirectories.USER_TYPE.getAttribute(), userType.toString()));
        }
        if (!ObjectUtils.isEmpty(active)) {
            andFilter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), active.toString()));
        }
        andFilter.and(new NotFilter(new EqualsFilter(ApacheDSLdapDirectories.UID.getAttribute(), adminUserName)));
        andFilter.and(orFilter);
        return ldapTemplate.search("", andFilter.encode(), LdapContextMapper.LDAP_USER_CONTEXT_MAPPER);
    }

    /**
     * Get active users.
     *
     * @return user list
     */
    @Override
    public List<LdapUser> getActiveUsers() {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.INET_ORG_PERSON.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(CommonConstants.TRUE)));
        return ldapTemplate.search("", filter.encode(), LdapContextMapper.LDAP_USER_CONTEXT_MAPPER);
    }

    /**
     * Get credit authorized users.
     *
     * @return user list
     */
    @Override
    public List<LdapUser> getCreditAuthorizedUsers() {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.INET_ORG_PERSON.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.IS_CREDIT_AUTHORIZED_USER.getAttribute(),
            String.valueOf(CommonConstants.TRUE)));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(CommonConstants.TRUE)));
        return ldapTemplate.search("", filter.encode(), LdapContextMapper.LDAP_USER_CONTEXT_MAPPER);
    }

    /**
     * Get users by type.
     *
     * @return user list
     */
    @Override
    public List<LdapUser> getUsersByType(final UserType userType) {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.INET_ORG_PERSON.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.USER_TYPE.getAttribute(), String.valueOf(userType)));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(CommonConstants.TRUE)));
        return ldapTemplate.search("", filter.encode(), LdapContextMapper.LDAP_USER_CONTEXT_MAPPER);
    }

    /**
     * Get list of active organizations by user.
     *
     * @param userId - user id
     * @return list of organization
     */
    @Override
    public List<LdapOrganization> getOrganizationsByUser(final String userId) {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.ORGANIZATION.getAttribute()));
        if (!ObjectUtils.isEmpty(userId)) {
            final String userDn = ldapUtil.appendBaseDnWithGivenDn(userId);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), userDn));
        }
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(CommonConstants.TRUE)));
        return ldapTemplate.search("", filter.encode(), LdapContextMapper.LDAP_ORGANIZATION_CONTEXT_MAPPER);
    }

    /**
     * Get list of branches by user and organization.
     *
     * @param userId           - user id
     * @param organizationName - organization name
     * @return list of branches
     */
    @Override
    public List<LdapGroup> getBranchesByUserAndOrganization(final String userId, final String organizationName) {
        final String organizationDn = ldapUtil.buildOrganizationDn(organizationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute()));
        if (!ObjectUtils.isEmpty(userId)) {
            final String userDn = ldapUtil.appendBaseDnWithGivenDn(userId);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), userDn));
        }
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(CommonConstants.TRUE)));
        return ldapTemplate.search(organizationDn, filter.encode(), LdapContextMapper.LDAP_GROUP_CONTEXT_MAPPER);
    }

    /**
     * Create ldap department.
     *
     * @param department       - department object
     * @param organizationName - organization name
     * @return distinguished name created in ldap
     */
    @Override
    public String createOrganizationDepartment(final Department department, final String organizationName)
        throws ObjectAlreadyExistsException {
        final String departmentDn = env.getRequiredProperty(DEPARTMENT_UNIT) +
            CommonConstants.COMMA_SEPARATOR + ldapUtil.buildOrganizationDn(organizationName);
        final Name dn = LdapNameBuilder.newInstance(departmentDn)
            .add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), department.getName()).build();
        final DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            new String[]{ApacheDSLdapDirectories.TOP.getAttribute(),
                ApacheDSLdapDirectories.IHMS_DEPARTMENT.getAttribute(), ApacheDSLdapDirectories.BASE_ENTITY.getAttribute()});
        context.setAttributeValue(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), department.getName());
        if (!ObjectUtils.isEmpty(department.getCode())) {
            context.setAttributeValue(ApacheDSLdapDirectories.CODE.getAttribute(), department.getCode());
        }
        context.setAttributeValue(ApacheDSLdapDirectories.DESCRIPTION.getAttribute(), department.getDescription());
        if (!ObjectUtils.isEmpty(department.getParentDepartment())) {
            context.setAttributeValue(ApacheDSLdapDirectories.PARENT_DEPARTMENT.getAttribute(),
                ldapUtil.buildParentDepartmentDn(department.getParentDepartment(), organizationName));
        }
        context.setAttributeValue(ApacheDSLdapDirectories.IS_INVESTIGATION_DEPARTMENT.getAttribute(),
            String.valueOf(department.isInvestigationDepartment()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREDIT_LIMIT_ENABLED.getAttribute(), String.valueOf(department.isCreditLimitEnabled()));
        context.setAttributeValue(ApacheDSLdapDirectories.BARCODE_IDENTIFIER.getAttribute(), department.getBarcodeIdentifier());
        if (!ObjectUtils.isEmpty(department.getDisplayOrder())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DISPLAY_ORDER.getAttribute(), String.valueOf(department.getDisplayOrder()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.DISPLAY_ORDER.getAttribute(), null);
        }
        context.setAttributeValue(ApacheDSLdapDirectories.EXTERNAL_DEPARTMENT_ID.getAttribute(),
            String.valueOf(department.getExternalDepartmentId()));
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(department.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(department.getCreatedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(department.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREATED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(department.getCreatedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(department.getModifiedBy()));
        context.setAttributeValue(ApacheDSLdapDirectories.MEMBER.getAttribute(), ldapUtil.getAdminUserDn());
        try {
            ldapTemplate.bind(context);
        } catch (NameAlreadyBoundException nameAlreadyBoundException) {
            ExceptionUtil.throwObjectAlreadyExistsException(ExceptionConstants.DEPARTMENT_ALREADY_EXISTS);
        } catch (NamingException e) {
            logger.info("Naming Exception " + e);
        }
        return dn.toString();
    }

    /**
     * Modify ldap department attributes.
     *
     * @param department       - department object
     * @param organizationName - organization name
     */
    @Override
    public void modifyDepartmentAttributes(final Department department, final String organizationName) {
        final DirContextOperations context = ldapTemplate.lookupContext(department.getId());
        context.setAttributeValue(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(department.isActive()));
        context.setAttributeValue(ApacheDSLdapDirectories.DESCRIPTION.getAttribute(), department.getDescription());
        if (!ObjectUtils.isEmpty(department.getCode())) {
            context.setAttributeValue(ApacheDSLdapDirectories.CODE.getAttribute(), department.getCode());
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.CODE.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(department.getParentDepartment())) {
            context.setAttributeValue(ApacheDSLdapDirectories.PARENT_DEPARTMENT.getAttribute(),
                ldapUtil.buildParentDepartmentDn(department.getParentDepartment(), organizationName));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.PARENT_DEPARTMENT.getAttribute(), null);
        }
        context.setAttributeValue(ApacheDSLdapDirectories.IS_INVESTIGATION_DEPARTMENT.getAttribute(),
            String.valueOf(department.isInvestigationDepartment()));
        context.setAttributeValue(ApacheDSLdapDirectories.CREDIT_LIMIT_ENABLED.getAttribute(),
            String.valueOf(department.isCreditLimitEnabled()));
        if (!ObjectUtils.isEmpty(department.getBarcodeIdentifier())) {
            context.setAttributeValue(ApacheDSLdapDirectories.BARCODE_IDENTIFIER.getAttribute(), department.getBarcodeIdentifier());
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.BARCODE_IDENTIFIER.getAttribute(), null);
        }
        if (!ObjectUtils.isEmpty(department.getDisplayOrder())) {
            context.setAttributeValue(ApacheDSLdapDirectories.DISPLAY_ORDER.getAttribute(), String.valueOf(department.getDisplayOrder()));
        } else {
            context.setAttributeValue(ApacheDSLdapDirectories.DISPLAY_ORDER.getAttribute(), null);
        }
        context.setAttributeValue(ApacheDSLdapDirectories.EXTERNAL_DEPARTMENT_ID.getAttribute(),
            String.valueOf(department.getExternalDepartmentId()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute(),
            DateUtil.convertInstantToLdapTimeStamp(department.getModifiedDate()));
        context.setAttributeValue(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute(),
            ldapUtil.appendBaseDnWithGivenDn(department.getModifiedBy()));
        ldapTemplate.modifyAttributes(context);
    }

    /**
     * Query method to find all department in LDAP.
     *
     * @param departmentName   - department name
     * @param organizationName - organization name
     * @param isActive         - active flag
     * @return list of ldap department
     */
    @Override
    public List<LdapDepartment> getDepartments(final String departmentName,
                                               final String organizationName,
                                               final Boolean isActive, final Boolean isCreditLimitEnabled) {
        final String organizationDn = ldapUtil.buildOrganizationDn(organizationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_DEPARTMENT.getAttribute()));
        if (!ObjectUtils.isEmpty(departmentName)) {
            filter.and(new WhitespaceWildcardsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), departmentName));
        }
        filter.and(new NotPresentFilter(ApacheDSLdapDirectories.PARENT_DEPARTMENT.getAttribute()));
        if (!ObjectUtils.isEmpty(isActive)) {
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(isActive)));
        }
        if (!ObjectUtils.isEmpty(isCreditLimitEnabled)) {
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.CREDIT_LIMIT_ENABLED.getAttribute(), String.valueOf(isCreditLimitEnabled)));
        }
        return ldapTemplate.search(organizationDn, filter.encode(), LdapContextMapper.LDAP_DEPARTMENT_CONTEXT_MAPPER);
    }

    /**
     * Get Departments By Description
     *
     * @param departmentDescription - department description
     * @param organizationName      - organization name
     * @param isActive              -is active
     * @param isCreditLimitEnabled  - isCreditLimitEnabled
     * @return list of ldap department
     */
    @Override
    public List<LdapDepartment> getDepartmentsByDescription(final String departmentDescription, final String organizationName, final Boolean isActive, final Boolean isCreditLimitEnabled) {
        final String organizationDn = ldapUtil.buildOrganizationDn(organizationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_DEPARTMENT.getAttribute()));
        if (!ObjectUtils.isEmpty(departmentDescription)) {
            filter.and(new WhitespaceWildcardsFilter(ApacheDSLdapDirectories.DESCRIPTION.getAttribute(), departmentDescription));
        }
        filter.and(new NotPresentFilter(ApacheDSLdapDirectories.PARENT_DEPARTMENT.getAttribute()));
        if (!ObjectUtils.isEmpty(isActive)) {
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(isActive)));
        }
        if (!ObjectUtils.isEmpty(isCreditLimitEnabled)) {
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.CREDIT_LIMIT_ENABLED.getAttribute(), String.valueOf(isCreditLimitEnabled)));
        }
        return ldapTemplate.search(organizationDn, filter.encode(), LdapContextMapper.LDAP_DEPARTMENT_CONTEXT_MAPPER);
    }

    /**
     * Query method to find all sub department in LDAP.
     *
     * @param subDepartmentName - sub department name
     * @param parentDepartment  - parent department name
     * @param organizationName  - organization name
     * @param isActive          - active flag
     * @return list of ldap department
     */
    @Override
    public List<LdapDepartment> getSubDepartments(final String subDepartmentName, final String parentDepartment,
                                                  final String organizationName, final Boolean isActive) {
        final String organizationDn = ldapUtil.buildOrganizationDn(organizationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_DEPARTMENT.getAttribute()));
        if (!ObjectUtils.isEmpty(subDepartmentName)) {
            filter.and(new WhitespaceWildcardsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), subDepartmentName));
        }
        filter.and(new PresentFilter(ApacheDSLdapDirectories.PARENT_DEPARTMENT.getAttribute()));
        if (!ObjectUtils.isEmpty(parentDepartment)) {
            final String parentDepartmentDn = ldapUtil.buildParentDepartmentDn(parentDepartment, organizationName);
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.PARENT_DEPARTMENT.getAttribute(), parentDepartmentDn));
        }
        if (!ObjectUtils.isEmpty(isActive)) {
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(isActive)));
        }
        return ldapTemplate.search(organizationDn, filter.encode(), LdapContextMapper.LDAP_DEPARTMENT_CONTEXT_MAPPER);
    }

    /**
     * Query method to find department by name.
     *
     * @param departmentName   - department name
     * @param organizationName - organization name
     * @return list of ldap department
     */
    @Override
    public List<LdapDepartment> getDepartmentByName(final String departmentName,
                                                    final String organizationName) {
        final String organizationDn = ldapUtil.buildOrganizationDn(organizationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_DEPARTMENT.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), departmentName));
        return ldapTemplate.search(organizationDn, filter.encode(), LdapContextMapper.LDAP_DEPARTMENT_CONTEXT_MAPPER);
    }

    /**
     * Query method to find all investigational departments in LDAP.
     *
     * @param organizationName - organization name
     * @param isActive         - active flag
     * @return list of ldap department
     */
    @Override
    public List<LdapDepartment> getInvestigationalDepartments(final String organizationName,
                                                              final Boolean isActive) {
        final String organizationDn = ldapUtil.buildOrganizationDn(organizationName);
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.IHMS_DEPARTMENT.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.IS_INVESTIGATION_DEPARTMENT.getAttribute(),
            String.valueOf(CommonConstants.TRUE)));
        if (!ObjectUtils.isEmpty(isActive)) {
            filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(isActive)));
        }
        return ldapTemplate.search(organizationDn, filter.encode(), LdapContextMapper.LDAP_DEPARTMENT_CONTEXT_MAPPER);
    }


    /**
     * Todo : This url is used in pharmacy, do not delete
     * To retrieve active list of users by name or employeeNumber
     *
     * @param searchText : Name or EmployeeNumber
     * @return List of users
     */
    @Override
    public List<LdapUser> getActiveUsersByNameOrEmployeeNumber(final String searchText) {
        final AndFilter filter = new AndFilter();
        final OrFilter orFilter = new OrFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.INET_ORG_PERSON.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ACTIVE.getAttribute(), String.valueOf(CommonConstants.TRUE)));
        if (!ObjectUtils.isEmpty(searchText)) {
            orFilter.or(new LikeFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), searchText + "*"))
                .or(new LikeFilter(ApacheDSLdapDirectories.DISPLAY_NAME.getAttribute(), searchText + "*"))
                .or(new LikeFilter(ApacheDSLdapDirectories.EMPLOYEE_NUMBER.getAttribute(), searchText + "*"));
            filter.and(orFilter);
        }
        return ldapTemplate.search("", filter.encode(), LdapContextMapper.LDAP_USER_CONTEXT_MAPPER);
    }
}
