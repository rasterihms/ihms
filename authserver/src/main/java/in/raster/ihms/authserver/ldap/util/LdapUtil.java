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
package in.raster.ihms.authserver.ldap.util;


import in.raster.ihms.commons.util.CommonConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Util class for ldap related operations.
 */
@Configuration
public class LdapUtil {

    @Value("${ldap.partitionSuffix}")
    private String ldapBaseDn;

    @Value("${ldap-defaults.login.username}")
    private String adminUserName;

    @Value("${ldap-defaults.organizational-units.branches}")
    private String branchUnit;

    @Value("${ldap-defaults.organizational-units.departments}")
    private String departmentUnit;

    @Value("${ldap-defaults.application-unit}")
    private String applicationUnit;

    @Value("${ldap-defaults.user-unit}")
    private String userUnit;

    @Value("${ldap-defaults.login.role}")
    private String defaultRole;

    @Bean
    public LdapUtil LdapUtil() {
        return new LdapUtil();
    }

    /**
     * Digest algorithm to encrypt password.
     *
     * @param password - user password
     * @return encrypted password
     */
    public String digestSHA(final String password) {
        final String shaAlgorithm = "SHA";
        String base64;
        try {
            MessageDigest digest = MessageDigest.getInstance(shaAlgorithm);
            digest.update(password.getBytes());
            base64 = Base64.getEncoder().encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "{" + shaAlgorithm + "}" + base64;
    }

    /**
     * Append base dn with the given dn.
     *
     * @param inputDn - input disnguished name
     * @return disnguished name
     */
    public Name appendBaseDnWithGivenDn(final Name inputDn) {
        final String inputDnValue = inputDn + CommonConstants.COMMA_SEPARATOR + ldapBaseDn;
        return LdapNameBuilder.newInstance(inputDnValue).build();
    }

    /**
     * Append base dn with the given dn.
     *
     * @param inputDn - input disnguished name
     * @return disnguished name
     */
    public String appendBaseDnWithGivenDn(final String inputDn) {
        return inputDn + CommonConstants.COMMA_SEPARATOR + ldapBaseDn;
    }

    /**
     * Create admin user dn with user name.
     *
     * @return admin user dn
     */
    public String getAdminUserDn() {
        return ApacheDSLdapDirectories.UID.getAttribute() + CommonConstants.EQUALS + adminUserName
            + CommonConstants.COMMA_SEPARATOR + ldapBaseDn;
    }

    /**
     * Build branch dn.
     *
     * @param branchName - branchName
     * @return branch dn
     */
    public String buildBranchDn(final String branchName, final String organizationName) {
        return ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + CommonConstants.EQUALS + branchName
            + CommonConstants.COMMA_SEPARATOR + branchUnit + CommonConstants.COMMA_SEPARATOR
            + ApacheDSLdapDirectories.ORGANIZATION_NAME.getAttribute() + CommonConstants.EQUALS + organizationName +
            CommonConstants.COMMA_SEPARATOR + ldapBaseDn;
    }

    /**
     * Build application dn.
     *
     * @param applicationName - application name
     * @return application dn
     */
    public String buildApplicationDn(final String applicationName) {
        return ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + CommonConstants.EQUALS + applicationName
            + CommonConstants.COMMA_SEPARATOR + applicationUnit;
    }

    /**
     * Build user dn.
     *
     * @param userName - user name
     * @return user dn
     */
    public String buildUserDn(final String userName) {
        return ApacheDSLdapDirectories.UID.getAttribute() + CommonConstants.EQUALS + userName
            + CommonConstants.COMMA_SEPARATOR + userUnit + CommonConstants.COMMA_SEPARATOR + ldapBaseDn;
    }

    /**
     * Build parent menu dn.
     *
     * @param parentMenuName - parent menu name
     * @param applicationDn  - application dn
     * @return parent menu dn
     */
    public String buildParentMenuDn(final String parentMenuName, final String applicationDn) {
        return ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + CommonConstants.EQUALS + parentMenuName
            + CommonConstants.COMMA_SEPARATOR + ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + CommonConstants.EQUALS
            + ApacheDSLdapDirectories.MENU_ROOT.getAttribute() + CommonConstants.COMMA_SEPARATOR + applicationDn
            + CommonConstants.COMMA_SEPARATOR + ldapBaseDn;
    }

    /**
     * Build resource dn.
     *
     * @param resourceName    - resource name
     * @param applicationName - application name
     * @param menuName        - menu name
     */
    public String buildResourceDn(final String resourceName, final String applicationName,
                                  final String menuName) {
        final String applicationDn = buildApplicationDn(applicationName);
        return ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + CommonConstants.EQUALS + resourceName
            + CommonConstants.COMMA_SEPARATOR + ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + CommonConstants.EQUALS
            + menuName + CommonConstants.COMMA_SEPARATOR
            + ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + CommonConstants.EQUALS
            + ApacheDSLdapDirectories.MENU_ROOT.getAttribute() + CommonConstants.COMMA_SEPARATOR + applicationDn;
    }

    /**
     * Build organization dn.
     *
     * @param organizationName - organization name
     * @return organization dn
     */
    public String buildOrganizationDn(final String organizationName) {
        return ApacheDSLdapDirectories.ORGANIZATION_NAME.getAttribute() + CommonConstants.EQUALS + organizationName;
    }

    /**
     * Build parent department dn.
     *
     * @param parentDepartmentName - parent department name
     * @param organizationDn       - organization dn
     * @return parent department dn
     */
    public String buildParentDepartmentDn(final String parentDepartmentName, final String organizationDn) {
        return ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + CommonConstants.EQUALS + parentDepartmentName
            + CommonConstants.COMMA_SEPARATOR + departmentUnit + CommonConstants.COMMA_SEPARATOR +
            ApacheDSLdapDirectories.ORGANIZATION_NAME.getAttribute() + CommonConstants.EQUALS + organizationDn
            + CommonConstants.COMMA_SEPARATOR + ldapBaseDn;
    }

    /**
     * Build default role dn.
     *
     * @param applicationDn - application dn
     * @return default role dn
     */
    public String buildDefaultRoleDn(final String applicationDn) {
        return ApacheDSLdapDirectories.COMMON_NAME.getAttribute() + CommonConstants.EQUALS + defaultRole
            + CommonConstants.COMMA_SEPARATOR + ApacheDSLdapDirectories.COMMON_NAME.getAttribute() +
            CommonConstants.EQUALS + ApacheDSLdapDirectories.ROLE_ROOT.getAttribute() + CommonConstants.COMMA_SEPARATOR +
            applicationDn + CommonConstants.COMMA_SEPARATOR + ldapBaseDn;
    }
}
