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

import in.raster.ihms.authserver.domain.Users;
import in.raster.ihms.authserver.ldap.domain.LdapGroup;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.authserver.ldap.exception.LdapCommunicationException;
import in.raster.ihms.authserver.ldap.mapper.UserAttributeMapper;
import in.raster.ihms.authserver.ldap.util.ApacheDSLdapDirectories;
import in.raster.ihms.authserver.ldap.util.LdapUtil;
import in.raster.ihms.authserver.repository.UsersRepository;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.ExceptionConstants;
import in.raster.ihms.exceptions.custom.InvalidCredentialsException;
import in.raster.ihms.exceptions.custom.ObjectAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.CommunicationException;
import org.springframework.ldap.core.*;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.util.ObjectUtils;

import javax.naming.NamingException;
import javax.naming.Name;
import javax.naming.directory.*;
import javax.naming.ldap.LdapName;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Todo - We will keep this class for reference and will be removed in future.
 *
 * Ldap client service implementation.
 */
public class LdapClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapClientService.class);
    private static final String LDAP_PARTITION_SUFFIX = "ldap.partitionSuffix";
    private static final String userDnEntry = "ldap.userDn";
    private static final String EQUALS = "=";
    private static final String COMMA = ",";
    private static final String LDAP_COMMUNICATION_EXCEPTION = "Not Connected To LDAP";

    @Autowired
    private Environment env;
    @Autowired
    private ContextSource contextSource;
    @Autowired
    private LdapTemplate ldapTemplate;
    @Autowired
    private LdapUtil ldapUtil;
    @Autowired
    private UsersRepository usersRepository;

    /**
     * Context Mapper for LDAP Roles values
     */
    private final static ContextMapper<LdapGroup> PERSON_CONTEXT_MAPPER = new AbstractContextMapper<LdapGroup>() {
        @Override
        public LdapGroup doMapFromContext(DirContextOperations context) {
            final LdapGroup group = new LdapGroup();
            final LdapName dn = LdapUtils.newLdapName(context.getDn());
            group.setDn(dn);
            group.setName(LdapUtils.getStringValue(dn, 1));
            LOGGER.info(group.toString());
            return group;
        }
    };

    /**
     * Authenticating user credentials.
     *
     * @param username ldap username
     * @param password ldap password
     * @return - boolean value
     */
    public boolean authenticate(final String username, final String password) throws InvalidCredentialsException {
        try {
            contextSource.getContext(ApacheDSLdapDirectories.UID.getAttribute() + EQUALS + username + COMMA
                + env.getRequiredProperty(LDAP_PARTITION_SUFFIX), password);
        } catch (org.springframework.ldap.NamingException namingException) {
            throw new InvalidCredentialsException(ExceptionConstants.INCORRECT_USER_NAME_PASSWORD);
        }
        return AuthServerUtil.TRUE;
    }

    /**
     * Search by username for checking existing user.
     *
     * @param username ldap Username
     * @return ldap user object
     */
    public List<LdapUser> search(final String username) {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.PERSON.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.UID.getAttribute(), username));
        return ldapTemplate.search("", filter.encode(), new UserAttributeMapper());
    }

    /**
     * Search by organizational unit name.
     *
     * @param orgUnitName organizational unit name
     * @return attribute mapper
     */
    public List searchOrgUnit(final String orgUnitName) {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute(), orgUnitName));

        AttributesMapper<String> attributesMapper = new AttributesMapper<String>() {
            @Override
            public String mapFromAttributes(Attributes attributes) throws NamingException {
                return attributes.get(ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute()).get().toString();
            }
        };
        return ldapTemplate.search("", filter.encode(), attributesMapper);

    }

    /**
     * Get all the members for the given role.
     *
     * @param roleName - role name
     * @return list of members
     */
    public List<String> getMembersNameByRole(final String roleName) {
        final List<String> memberList = new ArrayList<>();
        try {
            final List<Attribute> attributeList = getRoleMemberAttribute(roleName);
            if(attributeList.size() > 0) {
                final Attribute attribute = attributeList.get(0);
                for (Enumeration vals = attribute.getAll(); vals.hasMoreElements();) {
                    String member = String.valueOf(vals.nextElement());
                    if(!member.equalsIgnoreCase("dc=openmicroscopy,dc=org")){
                        memberList.add(member);
                    }
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return memberList;
    }

    /**
     * Get member attribute for the given role.
     *
     * @param roleName - role name
     * @return member attribute
     */
    public List<Attribute> getRoleMemberAttribute(final String roleName) throws NamingException {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), roleName));
        AttributesMapper<Attribute> attributesMapper = new AttributesMapper<Attribute>() {
            @Override
            public Attribute mapFromAttributes(Attributes attributes) throws NamingException {
                final Attribute attribute = attributes.get(ApacheDSLdapDirectories.MEMBER.getAttribute());
                return attribute;
            }
        };
        return ldapTemplate.search("", filter.encode(), attributesMapper);
    }

    /**
     * Search by role name.
     *
     * @param roleName role name to search
     * @return list of ldap group
     */
    public List<LdapGroup> searchLdapRole(final String roleName) {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), roleName));
        return ldapTemplate.search("", filter.encode(), PERSON_CONTEXT_MAPPER);
    }

    /**
     * Search by member for checking role mapping.
     *
     * @param member ldap member
     * @return list of ldap group
     */
    public List<LdapGroup> searchLdapMember(final String member) {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.MEMBER.getAttribute(), member));
        return ldapTemplate.search("", filter.encode(), PERSON_CONTEXT_MAPPER);
    }

    /**
     * Search by user for checking existing user.
     *
     * @param username ldap username
     * @return list of users
     */
    public List<Users> searchLdapUser(final String username) {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.PERSON.getAttribute()));
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.UID.getAttribute(), username));
        AttributesMapper<Users> attributesMapper = new AttributesMapper<Users>() {
            @Override
            public Users mapFromAttributes(Attributes attrs)
                    throws NamingException {
                Users userObj = new Users();
                LOGGER.info("DN =" + attrs.toString());
                userObj.setUserName(attrs.get(ApacheDSLdapDirectories.UID.getAttribute()).get().toString());
                userObj = usersRepository.findByUserName(userObj.getUserName());
                return userObj;
            }
        };
        return ldapTemplate.search("", filter.encode(), attributesMapper);
    }

    /**
     * Search all the users.
     *
     * @return list of ldap user
     */
    public List<LdapUser> searchAllPersons() {
        final AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            ApacheDSLdapDirectories.PERSON.getAttribute()));
        List lst;
        try {
            lst = ldapTemplate.search("", filter.encode(), new UserAttributeMapper());
        } catch (CommunicationException ex) {
            throw new LdapCommunicationException(LDAP_COMMUNICATION_EXCEPTION);
        }
        return lst;
    }

    /**
     * Create ldap user by username and password.
     *
     * @param username user name
     * @param password user password
     * @return distinguished name created in ldap
     */
    public String createLdapUser(final String username, final String password,
                                 final String userDnEntry) throws ObjectAlreadyExistsException {
        final Name dn;
        if(!ObjectUtils.isEmpty(userDnEntry)) {
            dn = LdapNameBuilder.newInstance().add(
                ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute(),
                env.getRequiredProperty(userDnEntry)).add(ApacheDSLdapDirectories.UID.getAttribute(), username).build();
        } else {
            dn = LdapNameBuilder.newInstance().add(ApacheDSLdapDirectories.UID.getAttribute(), username).build();
        }
        final DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
            new String[]{ApacheDSLdapDirectories.TOP.getAttribute(),
                ApacheDSLdapDirectories.PERSON.getAttribute(),
                ApacheDSLdapDirectories.ORGANIZATIONAL_PERSON.getAttribute(),
                ApacheDSLdapDirectories.INET_ORG_PERSON.getAttribute()});
        context.setAttributeValue(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), username);
        context.setAttributeValue(ApacheDSLdapDirectories.SUR_NAME.getAttribute(), username);
        context.setAttributeValue(ApacheDSLdapDirectories.UID.getAttribute(), username);
        context.setAttributeValue(ApacheDSLdapDirectories.USER_PASSWORD.getAttribute(), ldapUtil.digestSHA(password));
        LOGGER.info("----> " + dn);
        try {
            ldapTemplate.bind(context);
        } catch (org.springframework.ldap.NamingException namingException) {
            throw new ObjectAlreadyExistsException(ExceptionConstants.USER_ALREADY_EXISTS);
        }
        return dn.toString();
    }

    /**
     * Modify existing ldap user entry by user name.
     *
     * @param existingDn existing user name
     * @param userName new role name
     * @return distinguished name created in ldap
     */
    public String updateLdapUser(String existingDn,final String userName) {
        final String newDn = ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + EQUALS + userName + COMMA
            + ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute() + EQUALS + env.getRequiredProperty(userDnEntry);
        final Object roleObject= ldapTemplate.lookup(existingDn);
        if(!ObjectUtils.isEmpty(roleObject)) {
            ldapTemplate.rename(existingDn, newDn);
        }
        return newDn;
    }

    /**
     * Delete ldap user by given user name.
     *
     * @param userName user name to be deleted
     * @return distinguished name created in ldap
     */
    public void deleteLdapUser(final String userName) {
        final Name dn = LdapNameBuilder.newInstance().add(
            ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute(),
            env.getRequiredProperty(userDnEntry)).add(ApacheDSLdapDirectories.UID.getAttribute(), userName).build();
        ldapTemplate.unbind(dn);
    }

    /**
     * Create ldap group entry.
     *
     * @param organizationalUnit organizational unit name
     * @return distinguished name created in ldap
     */
    public String createLdapOrgUnit(final String organizationalUnit) {
        final Name dn = LdapNameBuilder.newInstance().add(ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute(),
            organizationalUnit).build();
        final DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
                new String[]{ApacheDSLdapDirectories.TOP.getAttribute(),
                        ApacheDSLdapDirectories.ORGANISATIONAL_UNIT_CLASS.getAttribute()
                });
        context.setAttributeValue("description", "new group");
        ldapTemplate.bind(context);
        return dn.toString();
    }

    /**
     * Create ldap role by given role name.
     *
     * @param roleName role name to be created
     * @return distinguished name created in ldap
     */
    public String createLdapRole(final String roleName) throws ObjectAlreadyExistsException {
        final Name dn = LdapNameBuilder.newInstance().add(
                ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute(),
                env.getRequiredProperty(userDnEntry)).add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), roleName).build();
        final DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
                new String[]{ApacheDSLdapDirectories.TOP.getAttribute(),
                        ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute()});
        context.setAttributeValue(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), roleName);
        context.setAttributeValue(ApacheDSLdapDirectories.MEMBER.getAttribute(), env.getRequiredProperty(LDAP_PARTITION_SUFFIX));
        try {
            ldapTemplate.bind(context);
        } catch (org.springframework.ldap.NamingException namingException) {
            throw new ObjectAlreadyExistsException(ExceptionConstants.ROLE_ALREADY_EXISTS);
        }
        return dn.toString();
    }

    /**
     * Modify existing ldap role entry by role name.
     *
     * @param existingDn existing role name
     * @param newRoleName new role name
     * @return distinguished name created in ldap
     */
    public String updateLdapRole(String existingDn,final String newRoleName) {
        final String newDn = ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + EQUALS + newRoleName + COMMA
                + ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute() + EQUALS + env.getRequiredProperty(userDnEntry);
        final Object roleObject= ldapTemplate.lookup(existingDn);
        if(!ObjectUtils.isEmpty(roleObject)) {
            ldapTemplate.rename(existingDn, newDn);
        }
        return newDn;
    }

    /**
     * Delete ldap role by given role name.
     *
     * @param roleName role name to be deleted
     * @return distinguished name created in ldap
     */
    public void deleteLdapRole(final String roleName) {
        final Name dn = LdapNameBuilder.newInstance().add(
            ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute(),
            env.getRequiredProperty(userDnEntry)).add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), roleName).build();
        ldapTemplate.unbind(dn);
    }

    /**
     * Modify user details, used to change password for existing user.
     *
     * @param username ldap username
     * @param password ldap password
     */
    public void modify(final String username, final String password) {
        final Name dn = LdapNameBuilder.newInstance()
                .add(ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute(), env.getRequiredProperty(userDnEntry))
                .add(ApacheDSLdapDirectories.UID.getAttribute(), username).build();
        final DirContextOperations context = ldapTemplate.lookupContext(dn);
        context.setAttributeValues(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute(),
                new String[]{
                        ApacheDSLdapDirectories.TOP.getAttribute(),
                        ApacheDSLdapDirectories.PERSON.getAttribute(),
                        ApacheDSLdapDirectories.ORGANIZATIONAL_PERSON.getAttribute(),
                        ApacheDSLdapDirectories.INET_ORG_PERSON.getAttribute()});
        context.setAttributeValue(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), username);
        context.setAttributeValue(ApacheDSLdapDirectories.SUR_NAME.getAttribute(), username);
        context.setAttributeValue(ApacheDSLdapDirectories.USER_PASSWORD.getAttribute(), ldapUtil.digestSHA(password));
        ldapTemplate.modifyAttributes(context);

    }

    /**
     * Modify user details, used to delete user mapping from role.
     *
     * @param member ldap member
     * @param roleName  ldap role name
     */
    public void removeMemberFromRole(final String member, final String roleName) {
        final Name dn = LdapNameBuilder.newInstance().add(ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute(),
            env.getRequiredProperty(userDnEntry)).add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), roleName).build();
        final DirContextOperations context = ldapTemplate.lookupContext(dn);
        context.addAttributeValue(ApacheDSLdapDirectories.MEMBER.getAttribute(), member);
        final Attribute attr = new BasicAttribute(ApacheDSLdapDirectories.MEMBER.getAttribute(), member);
        final ModificationItem item = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr);
        ldapTemplate.modifyAttributes(dn, new ModificationItem[]{item});
    }

    /**
     * Add user to role.
     *
     * @param member  ldap member
     * @param roleName role to be mapped
     */
    public void addUserToRole(final String member, final String roleName) {
        final Name dn = LdapNameBuilder.newInstance().add(ApacheDSLdapDirectories.ORGANISATIONAL_UNIT.getAttribute(),
            env.getRequiredProperty(userDnEntry)).add(ApacheDSLdapDirectories.COMMON_NAME.getAttribute(), roleName).build();
        final DirContextOperations context = ldapTemplate.lookupContext(dn);
        context.addAttributeValue(ApacheDSLdapDirectories.MEMBER.getAttribute(), member);
        final Attribute attr = new BasicAttribute(ApacheDSLdapDirectories.MEMBER.getAttribute(), member);
        final ModificationItem item = new ModificationItem(DirContext.ADD_ATTRIBUTE, attr); //DirContext.REPLACE_ATTRIBUTE
        ldapTemplate.modifyAttributes(dn, new ModificationItem[]{item});
    }

    /**
     * Query method to find unique groups(Roles) in LDAP.
     *
     * @param member user entry
     * @return list of ldap group
     */
    public List<LdapGroup> getLdapGroupsByMemberDn(final String member) { // UID=admin,ou=users,dc=dcm4che,dc=org
        return ldapTemplate.search(
                query().where(ApacheDSLdapDirectories.OBJECTCLASS.getAttribute())
                    .is(ApacheDSLdapDirectories.GROUP_OF_NAMES.getAttribute()).
                    and(ApacheDSLdapDirectories.MEMBER.getAttribute()).is(member), PERSON_CONTEXT_MAPPER);

    }
}
