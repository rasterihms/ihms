/************************************************************************************************************
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
 ************************************************************************************************************/
package in.raster.ihms.authserver.util;

import in.raster.ihms.authserver.domain.custom.User;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.authserver.ldap.util.ApacheDSLdapDirectories;
import in.raster.ihms.commons.security.JwtUser;
import in.raster.ihms.commons.util.CommonConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import javax.naming.Name;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Todo - Constants will be moved to common file.
 * <p>
 * Common class for user authentication utilities.
 */
public class AuthServerUtil {

    public static int LDAP_USER_ID_INDEX = 4;
    public static int PARENT_MENU = 0;
    public static boolean TRUE = true;
    public static String ADDRESS_RESOURCE = "Address";
    public static String COUNTRY_RESOURCE = "Country";
    public static String USER = "user";
    public static String HOSPITAL = "hospital";

    /**
     * Method to load logged in user object from spring security context.
     *
     * @return JwtUser
     */
    public static JwtUser getUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (JwtUser) authentication.getPrincipal();
    }

    /**
     * Parse parent menu id and get menu name.
     *
     * @param parentMenuId - parent menu id
     * @return menu name
     */
    public static String getNameFromParentMenuId(final String parentMenuId) {
        return parentMenuId.substring(parentMenuId.indexOf(CommonConstants.EQUALS)
            + CommonConstants.ONE, parentMenuId.indexOf(CommonConstants.COMMA_SEPARATOR));
    }

    /**
     * Parse parent department id and get department name.
     *
     * @param parentDepartment - parent department
     * @return department name
     */
    public static String getNameFromParentDepartment(final String parentDepartment) {
        return parentDepartment.substring(parentDepartment.indexOf(CommonConstants.EQUALS)
            + CommonConstants.ONE, parentDepartment.indexOf(CommonConstants.COMMA_SEPARATOR));
    }

    /**
     * Parse resource id and get menu name.
     *
     * @param resourceId - resource id
     * @return menu name
     */
    public static String getMenuNameFromResourceId(final String resourceId) {
        final String menuId = resourceId.substring(resourceId.indexOf(CommonConstants.COMMA_SEPARATOR)
            + CommonConstants.ONE);
        return menuId.substring(menuId.indexOf(CommonConstants.EQUALS)
            + CommonConstants.ONE, menuId.indexOf(CommonConstants.COMMA_SEPARATOR));
    }

    /**
     * Get organization name from branch id.
     *
     * @param branchId - branch id
     * @return organization name
     */
    public static String getOrganizationNameFromBranchId(final String branchId) {
        final String organizationId = branchId
            .substring(branchId.indexOf(ApacheDSLdapDirectories.ORGANIZATION_NAME.getAttribute()));
        return organizationId.substring(CommonConstants.ZERO, organizationId.indexOf(CommonConstants.COMMA_SEPARATOR));
    }

    /**
     * Parse ldap id and get application name.
     *
     * @param ldapId - ldap id
     * @return application name
     */
    public static String getApplicationNameFromLdapId(final String ldapId) {
        final String applicationId = ldapId.substring(ldapId.indexOf(ApacheDSLdapDirectories.MENU_ROOT.getAttribute())
            + ApacheDSLdapDirectories.MENU_ROOT.getAttribute().length() + CommonConstants.ONE);
        return applicationId.substring(applicationId.indexOf(CommonConstants.EQUALS)
            + CommonConstants.ONE, applicationId.indexOf(CommonConstants.COMMA_SEPARATOR));
    }

    /**
     * Parse role id and get application name.
     *
     * @param roleId - role id
     * @return application name
     */
    public static String getApplicationNameFromRoleId(final String roleId) {
        final String applicationId = roleId.substring(roleId.indexOf(ApacheDSLdapDirectories.ROLE_ROOT.getAttribute())
            + ApacheDSLdapDirectories.ROLE_ROOT.getAttribute().length() + CommonConstants.ONE);
        return applicationId.substring(applicationId.indexOf(CommonConstants.EQUALS)
            + CommonConstants.ONE, applicationId.indexOf(CommonConstants.COMMA_SEPARATOR));
    }

    /**
     * Parse member id and get user's name
     *
     * @param memberId : member
     * @return user name
     */
    public static String getUserNameFromMemberId(final Name memberId) {
        String userId = memberId.toString();
        userId = userId.substring(userId.indexOf(ApacheDSLdapDirectories.UID.getAttribute()) + CommonConstants.ONE,
            userId.indexOf(CommonConstants.COMMA_SEPARATOR));
        return userId.substring((userId.indexOf(CommonConstants.EQUALS) + CommonConstants.ONE), userId.length());
    }

    /**
     * Parse dn and get name.
     *
     * @param dn - distinguished name
     * @return name
     */
    public static String getNameFromDn(final String dn) {
        return dn.substring(dn.indexOf(CommonConstants.EQUALS)
            + CommonConstants.ONE, dn.indexOf(CommonConstants.COMMA_SEPARATOR));
    }

    /**
     * Construct patient file storage path.
     *
     * @param fileName           - file name
     * @param documentIdentifier - document identifier
     * @return file path
     */
    public static String constructFileStoragePath(final String fileName, final String documentIdentifier) {
        final int dotIndex = fileName.lastIndexOf(CommonConstants.DOT);
        return fileName.substring(CommonConstants.ZERO, dotIndex) + CommonConstants.UNDERSCORE + documentIdentifier
            + fileName.substring(dotIndex);
    }

    /**
     * Validate user location.
     *
     * @param user - user
     */
    public static void validateUserLocation(final User user) {
        final String additionalLocationIds = user.getLocationIds();
        if(!ObjectUtils.isEmpty(user.getDefaultLocationId()) && !ObjectUtils.isEmpty(additionalLocationIds)) {
            final String additionalLocationArr [] = additionalLocationIds.split(CommonConstants.COMMA_SEPARATOR);
            final List<String> additionalLocationList = Arrays.asList(additionalLocationArr);
            if(additionalLocationList.contains(String.valueOf(user.getDefaultLocationId()))) {
                ExceptionUtil.throwCustomParameterizedException(ExceptionConstants.DEFAULT_LOCATION_EXISTS_IN_ADDITIONAL_LOCATION);
            }
        }
    }

    /**
     * Convert the user type string to a list by comma.
     *
     * @param userType - use type string
     * @return list of company type status
     */
    public static List<UserType> convertUserTypeToListByComma(final String userType) {
        return Collections.unmodifiableList(Arrays
            .asList(userType.split(CommonConstants.COMMA_SEPARATOR)))
            .stream()
            .map(UserType::valueOf)
            .collect(Collectors.toList());
    }

}
