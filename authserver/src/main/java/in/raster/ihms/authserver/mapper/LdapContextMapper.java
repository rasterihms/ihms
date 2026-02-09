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
package in.raster.ihms.authserver.mapper;

import in.raster.ihms.authserver.ldap.domain.*;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.authserver.ldap.util.ApacheDSLdapDirectories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.util.ObjectUtils;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Ldap context mapper.
 */
public class LdapContextMapper {

    private static final Logger logger = LoggerFactory.getLogger(LdapContextMapper.class);

    /**
     * Context mapper for ldap group.
     */
    public final static ContextMapper<LdapGroup> LDAP_GROUP_CONTEXT_MAPPER = new AbstractContextMapper<LdapGroup>() {
        @Override
        public LdapGroup doMapFromContext(final DirContextOperations context) {
            final LdapGroup group = new LdapGroup();
            final Set<Name> members = new HashSet<>();
            final LdapName dn = LdapUtils.newLdapName(context.getDn());
            final Attributes attributes = context.getAttributes();
            group.setDn(dn);
            try {
                group.setName(String.valueOf(attributes.get(ApacheDSLdapDirectories.COMMON_NAME.getAttribute()).get()));
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.CODE.getAttribute()))) {
                    group.setCode(String.valueOf(attributes.get(ApacheDSLdapDirectories.CODE.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.TIME_ZONE.getAttribute()))) {
                    group.setTimeZone(String.valueOf(attributes.get(ApacheDSLdapDirectories.TIME_ZONE.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.BILLING_LIMIT.getAttribute()))) {
                    group.setBillingLimit(String.valueOf(attributes.get(ApacheDSLdapDirectories.BILLING_LIMIT.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.OPENING_TIME.getAttribute()))) {
                    group.setOpeningTime(String.valueOf(attributes.get(ApacheDSLdapDirectories.OPENING_TIME.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.CLOSING_TIME.getAttribute()))) {
                    group.setClosingTime(String.valueOf(attributes.get(ApacheDSLdapDirectories.CLOSING_TIME.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.PREFERRED_LANGUAGE.getAttribute()))) {
                    group.setPreferredLanguage(String.valueOf(attributes.get(ApacheDSLdapDirectories.PREFERRED_LANGUAGE.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.CURRENCY.getAttribute()))) {
                    group.setCurrency(String.valueOf(attributes.get(ApacheDSLdapDirectories.CURRENCY.getAttribute()).get()));
                }
                group.setActive(String.valueOf(attributes.get(ApacheDSLdapDirectories.ACTIVE.getAttribute()).get()));
                group.setCreatedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_BY.getAttribute()).get()));
                group.setModifiedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute()).get()));
                group.setCreatedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_DATE.getAttribute()).get()));
                group.setModifiedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute()).get()));
                final Attribute attribute = attributes.get(ApacheDSLdapDirectories.MEMBER.getAttribute());
                if (!ObjectUtils.isEmpty(attribute)) {
                    for (Enumeration vals = attribute.getAll(); vals.hasMoreElements(); ) {
                        members.add(LdapNameBuilder.newInstance(String.valueOf(vals.nextElement())).build());
                    }
                }
                group.setMembers(members);
            } catch (NamingException e) {
                logger.info("Naming Exception " + e);
            }
            logger.info(group.toString());
            return group;
        }
    };

    /**
     * Context mapper for ldap application.
     */
    public final static ContextMapper<LdapApplication> LDAP_APPLICATION_CONTEXT_MAPPER = new AbstractContextMapper<LdapApplication>() {
        @Override
        public LdapApplication doMapFromContext(final DirContextOperations context) {
            final LdapApplication ldapApplication = new LdapApplication();
            final Set<Name> members = new HashSet<>();
            final LdapName dn = LdapUtils.newLdapName(context.getDn());
            ldapApplication.setDn(dn);
            final Attributes attributes = context.getAttributes();
            try {
                ldapApplication.setName(String.valueOf(attributes.get(ApacheDSLdapDirectories.COMMON_NAME.getAttribute()).get()));
                ldapApplication.setUrl(String.valueOf(attributes.get(ApacheDSLdapDirectories.URL.getAttribute()).get()));
                ldapApplication.setActive(String.valueOf(attributes.get(ApacheDSLdapDirectories.ACTIVE.getAttribute()).get()));
                ldapApplication.setCreatedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_BY.getAttribute()).get()));
                ldapApplication.setModifiedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute()).get()));
                ldapApplication.setCreatedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_DATE.getAttribute()).get()));
                ldapApplication.setModifiedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute()).get()));
                final Attribute attribute = attributes.get(ApacheDSLdapDirectories.MEMBER.getAttribute());
                if (!ObjectUtils.isEmpty(attribute)) {
                    for (Enumeration vals = attribute.getAll(); vals.hasMoreElements(); ) {
                        members.add(LdapNameBuilder.newInstance(String.valueOf(vals.nextElement())).build());
                    }
                }
                ldapApplication.setMembers(members);
            } catch (NamingException e) {
                logger.info("Naming Exception " + e);
            }
            return ldapApplication;
        }
    };

    /**
     * Context mapper for ldap role.
     */
    public final static ContextMapper<LdapRole> LDAP_ROLE_CONTEXT_MAPPER = new AbstractContextMapper<LdapRole>() {
        @Override
        public LdapRole doMapFromContext(final DirContextOperations context) {
            final LdapRole ldapRole = new LdapRole();
            final Set<Name> members = new HashSet<>();
            final LdapName dn = LdapUtils.newLdapName(context.getDn());
            ldapRole.setDn(dn);
            final Attributes attributes = context.getAttributes();
            try {
                ldapRole.setName(String.valueOf(attributes.get(ApacheDSLdapDirectories.COMMON_NAME.getAttribute()).get()));
                ldapRole.setActive(String.valueOf(attributes.get(ApacheDSLdapDirectories.ACTIVE.getAttribute()).get()));
                ldapRole.setCreatedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_BY.getAttribute()).get()));
                ldapRole.setModifiedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute()).get()));
                ldapRole.setCreatedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_DATE.getAttribute()).get()));
                ldapRole.setModifiedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute()).get()));
                final Attribute attribute = attributes.get(ApacheDSLdapDirectories.MEMBER.getAttribute());
                if (!ObjectUtils.isEmpty(attribute)) {
                    for (Enumeration vals = attribute.getAll(); vals.hasMoreElements(); ) {
                        members.add(LdapNameBuilder.newInstance(String.valueOf(vals.nextElement())).build());
                    }
                }
                ldapRole.setMembers(members);
            } catch (NamingException e) {
                logger.info("Naming Exception " + e);
            }
            return ldapRole;
        }
    };

    /**
     * Context mapper for ldap menu.
     */
    public final static ContextMapper<LdapMenu> LDAP_MENU_CONTEXT_MAPPER = new AbstractContextMapper<LdapMenu>() {
        @Override
        public LdapMenu doMapFromContext(final DirContextOperations context) {
            final LdapMenu ldapMenu = new LdapMenu();
            final Set<Name> members = new HashSet<>();
            final LdapName dn = LdapUtils.newLdapName(context.getDn());
            ldapMenu.setDn(dn);
            final Attributes attributes = context.getAttributes();
            try {
                ldapMenu.setName(String.valueOf(attributes.get(ApacheDSLdapDirectories.COMMON_NAME.getAttribute()).get()));
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.DISPLAY_NAME.getAttribute()))) {
                    ldapMenu.setDisplayName(String.valueOf(attributes.get(ApacheDSLdapDirectories.DISPLAY_NAME.getAttribute()).get()));
                }
                ldapMenu.setOrder(String.valueOf(attributes.get(ApacheDSLdapDirectories.MENU_ORDER.getAttribute()).get()));
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.MENU_ICON.getAttribute()))) {
                    ldapMenu.setIcon(String.valueOf(attributes.get(ApacheDSLdapDirectories.MENU_ICON.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.URL.getAttribute()))) {
                    ldapMenu.setUrl(String.valueOf(attributes.get(ApacheDSLdapDirectories.URL.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.PARENT_MENU.getAttribute()))) {
                    ldapMenu.setParentMenuId(String.valueOf(attributes.get(ApacheDSLdapDirectories.PARENT_MENU.getAttribute()).get()));
                }
                ldapMenu.setActive(String.valueOf(attributes.get(ApacheDSLdapDirectories.ACTIVE.getAttribute()).get()));
                ldapMenu.setCreatedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_BY.getAttribute()).get()));
                ldapMenu.setModifiedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute()).get()));
                ldapMenu.setCreatedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_DATE.getAttribute()).get()));
                ldapMenu.setModifiedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute()).get()));
                final Attribute attribute = attributes.get(ApacheDSLdapDirectories.MEMBER.getAttribute());
                if (!ObjectUtils.isEmpty(attribute)) {
                    for (Enumeration vals = attribute.getAll(); vals.hasMoreElements(); ) {
                        members.add(LdapNameBuilder.newInstance(String.valueOf(vals.nextElement())).build());
                    }
                }
                ldapMenu.setMembers(members);
            } catch (NamingException e) {
                logger.info("Naming Exception " + e);
            }
            return ldapMenu;
        }
    };

    /**
     * Context mapper for ldap resource.
     */
    public final static ContextMapper<LdapResource> LDAP_RESOURCE_CONTEXT_MAPPER = new AbstractContextMapper<LdapResource>() {
        @Override
        public LdapResource doMapFromContext(final DirContextOperations context) {
            final LdapResource ldapResource = new LdapResource();
            final Set<Name> members = new HashSet<>();
            final LdapName dn = LdapUtils.newLdapName(context.getDn());
            ldapResource.setDn(dn);
            final Attributes attributes = context.getAttributes();
            try {
                ldapResource.setName(String.valueOf(attributes.get(ApacheDSLdapDirectories.COMMON_NAME.getAttribute()).get()));
                ldapResource.setUrl(String.valueOf(attributes.get(ApacheDSLdapDirectories.URL.getAttribute()).get()));
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.PERMISSION_TYPE.getAttribute()))) {
                    ldapResource.setPermissionType(String.valueOf(attributes.get(ApacheDSLdapDirectories.PERMISSION_TYPE.getAttribute()).get()));
                }
                ldapResource.setActive(String.valueOf(attributes.get(ApacheDSLdapDirectories.ACTIVE.getAttribute()).get()));
                ldapResource.setCreatedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_BY.getAttribute()).get()));
                ldapResource.setModifiedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute()).get()));
                ldapResource.setCreatedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_DATE.getAttribute()).get()));
                ldapResource.setModifiedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute()).get()));
                final Attribute attribute = attributes.get(ApacheDSLdapDirectories.MEMBER.getAttribute());
                if (!ObjectUtils.isEmpty(attribute)) {
                    for (Enumeration vals = attribute.getAll(); vals.hasMoreElements(); ) {
                        members.add(LdapNameBuilder.newInstance(String.valueOf(vals.nextElement())).build());
                    }
                }
                ldapResource.setMembers(members);
            } catch (NamingException e) {
                logger.info("Naming Exception " + e);
            }
            return ldapResource;
        }
    };

    /**
     * Context mapper for ldap user.
     */
    public final static ContextMapper<LdapUser> LDAP_USER_CONTEXT_MAPPER = new AbstractContextMapper<LdapUser>() {
        @Override
        public LdapUser doMapFromContext(final DirContextOperations context) {
            final LdapUser ldapUser = new LdapUser();
            final LdapName dn = LdapUtils.newLdapName(context.getDn());
            ldapUser.setId(dn);
            final Attributes attributes = context.getAttributes();
            try {
                ldapUser.setUsername(String.valueOf(attributes.get(ApacheDSLdapDirectories.COMMON_NAME.getAttribute()).get()));
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.SUR_NAME.getAttribute()))) {
                    ldapUser.setSurname(String.valueOf(attributes.get(ApacheDSLdapDirectories.SUR_NAME.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.MAIL.getAttribute()))) {
                    ldapUser.setMail(String.valueOf(attributes.get(ApacheDSLdapDirectories.MAIL.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.MOBILE.getAttribute()))) {
                    ldapUser.setMobileNumber(String.valueOf(attributes.get(ApacheDSLdapDirectories.MOBILE.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.EMPLOYEE_NUMBER.getAttribute()))) {
                    ldapUser.setEmployeeNumber(String.valueOf(attributes.get(ApacheDSLdapDirectories.EMPLOYEE_NUMBER.getAttribute()).get()));
                }
                ldapUser.setActive(String.valueOf(attributes.get(ApacheDSLdapDirectories.ACTIVE.getAttribute()).get()));
                ldapUser.setCreatedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_BY.getAttribute()).get()));
                ldapUser.setModifiedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute()).get()));
                ldapUser.setCreatedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_DATE.getAttribute()).get()));
                ldapUser.setModifiedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute()).get()));
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.IS_CREDIT_AUTHORIZED_USER.getAttribute()))) {
                    final String isCreditAuthorizedUser = String.valueOf(attributes.get(ApacheDSLdapDirectories.IS_CREDIT_AUTHORIZED_USER.getAttribute()).get());
                    if (Boolean.valueOf(isCreditAuthorizedUser)) {
                        ldapUser.setIsCreditAuthorizedUser(isCreditAuthorizedUser);
                        ldapUser.setCreditAmountLimit(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREDIT_AMOUNT_LIMIT.getAttribute()).get()));
                    }
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.USER_TYPE.getAttribute()))) {
                    ldapUser.setUserType(String.valueOf(attributes.get(ApacheDSLdapDirectories.USER_TYPE.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.DOCTOR_ID.getAttribute()))) {
                    ldapUser.setDoctorId(String.valueOf(attributes.get(ApacheDSLdapDirectories.DOCTOR_ID.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.DEPARTMENT_NUMBER.getAttribute()))) {
                    ldapUser.setDepartment(String.valueOf(attributes.get(ApacheDSLdapDirectories.DEPARTMENT_NUMBER.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.SIGN_FILE_PATH.getAttribute()))) {
                    ldapUser.setSignFilePath(String.valueOf(attributes.get(ApacheDSLdapDirectories.SIGN_FILE_PATH.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.PHOTO_FILE_PATH.getAttribute()))) {
                    ldapUser.setPhotoFilePath(String.valueOf(attributes.get(ApacheDSLdapDirectories.PHOTO_FILE_PATH.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.EXTERNAL_USER_ID.getAttribute()))) {
                    ldapUser.setExternalUserId(String.valueOf(attributes.get(ApacheDSLdapDirectories.EXTERNAL_USER_ID.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.DEFAULT_LOCATION_ID.getAttribute()))) {
                    ldapUser.setDefaultLocationId(String.valueOf(attributes.get(ApacheDSLdapDirectories.DEFAULT_LOCATION_ID.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.LOCATION_IDS.getAttribute()))) {
                    ldapUser.setLocationIds(String.valueOf(attributes.get(ApacheDSLdapDirectories.LOCATION_IDS.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.THEME_COLOR.getAttribute()))) {
                    ldapUser.setThemeColor(String.valueOf(attributes.get(ApacheDSLdapDirectories.THEME_COLOR.getAttribute()).get()));
                }
            } catch (NamingException e) {
                logger.info("Naming Exception " + e);
            }
            return ldapUser;
        }
    };

    /**
     * Context mapper for ldap organization.
     */
    public final static ContextMapper<LdapOrganization> LDAP_ORGANIZATION_CONTEXT_MAPPER = new AbstractContextMapper<LdapOrganization>() {
        @Override
        public LdapOrganization doMapFromContext(final DirContextOperations context) {
            final LdapOrganization ldapOrganization = new LdapOrganization();
            final Set<Name> members = new HashSet<>();
            final LdapName dn = LdapUtils.newLdapName(context.getDn());
            final Attributes attributes = context.getAttributes();
            ldapOrganization.setDn(dn);
            try {
                ldapOrganization.setName(String.valueOf(attributes.get(ApacheDSLdapDirectories.ORGANIZATION_NAME.getAttribute()).get()));
                ldapOrganization.setActive(String.valueOf(attributes.get(ApacheDSLdapDirectories.ACTIVE.getAttribute()).get()));
                ldapOrganization.setCreatedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_BY.getAttribute()).get()));
                ldapOrganization.setModifiedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute()).get()));
                ldapOrganization.setCreatedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_DATE.getAttribute()).get()));
                ldapOrganization.setModifiedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute()).get()));
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.ROHINI_ID.getAttribute()))) {
                    ldapOrganization.setRohiniId(String.valueOf(attributes.get(ApacheDSLdapDirectories.ROHINI_ID.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.DESCRIPTION.getAttribute()))) {
                    ldapOrganization.setRohiniId(String.valueOf(attributes.get(ApacheDSLdapDirectories.DESCRIPTION.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.HOSPITAL_NT_CODE.getAttribute()))) {
                    ldapOrganization.setNtCode(String.valueOf(attributes.get(ApacheDSLdapDirectories.HOSPITAL_NT_CODE.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.TELEPHONE_NUMBER.getAttribute()))) {
                    ldapOrganization.setContactNumber(String.valueOf(attributes.get(ApacheDSLdapDirectories.TELEPHONE_NUMBER.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.FAX_NUMBER.getAttribute()))) {
                    ldapOrganization.setFaxNumber(String.valueOf(attributes.get(ApacheDSLdapDirectories.FAX_NUMBER.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.MAIL.getAttribute()))) {
                    ldapOrganization.setEmailId(String.valueOf(attributes.get(ApacheDSLdapDirectories.MAIL.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.LANDMARK.getAttribute()))) {
                    ldapOrganization.setLandmark(String.valueOf(attributes.get(ApacheDSLdapDirectories.LANDMARK.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.CITY.getAttribute()))) {
                    ldapOrganization.setCity(String.valueOf(attributes.get(ApacheDSLdapDirectories.CITY.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.STATE.getAttribute()))) {
                    ldapOrganization.setState(String.valueOf(attributes.get(ApacheDSLdapDirectories.STATE.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.PINCODE.getAttribute()))) {
                    ldapOrganization.setPincode(String.valueOf(attributes.get(ApacheDSLdapDirectories.PINCODE.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.REGISTERED_ADDRESS.getAttribute()))) {
                    ldapOrganization.setAddress(String.valueOf(attributes.get(ApacheDSLdapDirectories.REGISTERED_ADDRESS.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.SEAL_FILE_PATH.getAttribute()))) {
                    ldapOrganization.setSealFilePath(String.valueOf(attributes.get(ApacheDSLdapDirectories.SEAL_FILE_PATH.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.LOGO_FILE_PATH.getAttribute()))) {
                    ldapOrganization.setLogoFilePath(String.valueOf(attributes.get(ApacheDSLdapDirectories.LOGO_FILE_PATH.getAttribute()).get()));
                }
                final Attribute attribute = attributes.get(ApacheDSLdapDirectories.MEMBER.getAttribute());
                if (!ObjectUtils.isEmpty(attribute)) {
                    for (Enumeration vals = attribute.getAll(); vals.hasMoreElements(); ) {
                        members.add(LdapNameBuilder.newInstance(String.valueOf(vals.nextElement())).build());
                    }
                }
                ldapOrganization.setMembers(members);
            } catch (NamingException e) {
                logger.info("Naming Exception " + e);
            }
            logger.info(ldapOrganization.toString());
            return ldapOrganization;
        }
    };

    /**
     * Context mapper for ldap department.
     */
    public final static ContextMapper<LdapDepartment> LDAP_DEPARTMENT_CONTEXT_MAPPER = new AbstractContextMapper<LdapDepartment>() {
        @Override
        public LdapDepartment doMapFromContext(final DirContextOperations context) {
            final LdapDepartment ldapDepartment = new LdapDepartment();
            final Set<Name> members = new HashSet<>();
            final LdapName dn = LdapUtils.newLdapName(context.getDn());
            ldapDepartment.setDn(dn);
            final Attributes attributes = context.getAttributes();
            try {
                ldapDepartment.setName(String.valueOf(attributes.get(ApacheDSLdapDirectories.COMMON_NAME.getAttribute()).get()));
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.CODE.getAttribute()))) {
                    ldapDepartment.setCode(String.valueOf(attributes.get(ApacheDSLdapDirectories.CODE.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.DESCRIPTION.getAttribute()))) {
                    ldapDepartment.setDescription(String.valueOf
                        (attributes.get(ApacheDSLdapDirectories.DESCRIPTION.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.PARENT_DEPARTMENT.getAttribute()))) {
                    ldapDepartment.setParentDepartment
                        (String.valueOf(attributes.get(ApacheDSLdapDirectories.PARENT_DEPARTMENT.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.IS_INVESTIGATION_DEPARTMENT.getAttribute()))) {
                    ldapDepartment.setInvestigationDepartment
                        (String.valueOf(attributes.get(ApacheDSLdapDirectories.IS_INVESTIGATION_DEPARTMENT.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.EXTERNAL_DEPARTMENT_ID.getAttribute()))) {
                    ldapDepartment.setExternalDepartmentId
                        (String.valueOf(attributes.get(ApacheDSLdapDirectories.EXTERNAL_DEPARTMENT_ID.getAttribute()).get()));
                }

                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.BARCODE_IDENTIFIER.getAttribute()))) {
                    ldapDepartment.setBarcodeIdentifier(String.valueOf(attributes.get(ApacheDSLdapDirectories.BARCODE_IDENTIFIER.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.DISPLAY_ORDER.getAttribute()))) {
                    ldapDepartment.setDisplayOrder(String.valueOf(attributes.get(ApacheDSLdapDirectories.DISPLAY_ORDER.getAttribute()).get()));
                }
                if (!ObjectUtils.isEmpty(attributes.get(ApacheDSLdapDirectories.CREDIT_LIMIT_ENABLED.getAttribute()))) {
                    ldapDepartment.setCreditLimitEnabled
                        (String.valueOf(attributes.get(ApacheDSLdapDirectories.CREDIT_LIMIT_ENABLED.getAttribute()).get()));
                }
                ldapDepartment.setActive(String.valueOf(attributes.get(ApacheDSLdapDirectories.ACTIVE.getAttribute()).get()));
                ldapDepartment.setCreatedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_BY.getAttribute()).get()));
                ldapDepartment.setModifiedBy(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_BY.getAttribute()).get()));
                ldapDepartment.setCreatedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.CREATED_DATE.getAttribute()).get()));
                ldapDepartment.setModifiedDate(String.valueOf(attributes.get(ApacheDSLdapDirectories.MODIFIED_DATE.getAttribute()).get()));
                final Attribute attribute = attributes.get(ApacheDSLdapDirectories.MEMBER.getAttribute());
                if (!ObjectUtils.isEmpty(attribute)) {
                    for (Enumeration values = attribute.getAll(); values.hasMoreElements(); ) {
                        members.add(LdapNameBuilder.newInstance(String.valueOf(values.nextElement())).build());
                    }
                }
                ldapDepartment.setMembers(members);
            } catch (NamingException e) {
                logger.info("Naming Exception " + e);
            }
            return ldapDepartment;
        }
    };
}
