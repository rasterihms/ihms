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

import in.raster.ihms.authserver.ldap.domain.LdapGroup;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.commons.security.JwtUser;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Jwt factory class to map user and roles to JWT user object.
 */
public class JwtUserFactory {

    private JwtUserFactory() {
    }

    /**
     * Create jwt user.
     *
     * @param ldapUser  - ldap user
     * @param userRoles - user roles
     * @return JWT user
     */
    public static JwtUser create(final LdapUser ldapUser, final List<LdapGroup> userRoles) {
        return new JwtUser(
            String.valueOf(ldapUser.getId()),
            appendUserIdWithUserName(ldapUser.getUsername(), String.valueOf(ldapUser.getId())),
            CommonConstants.EMPTY_STRING,
            CommonConstants.EMPTY_STRING,
            CommonConstants.EMPTY_STRING,
            CommonConstants.EMPTY_STRING,
            mapToGrantedAuthorities(userRoles),
            Boolean.valueOf(ldapUser.getActive()),
            null
        );
    }

    /**
     * Appends user name and id.
     *
     * @param userName - user name
     * @param userId   - user id
     * @return user name with id
     */
    private static String appendUserIdWithUserName(final String userName, final String userId) {
        return StringUtils.commaAppender(userName, userId);
    }

    /**
     * Map given ldap roles to granted authority.
     *
     * @param userRoles - ldap user roles
     * @return list of granted authority for the logged in user
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(final List<LdapGroup> userRoles) {
        return userRoles.stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
    }
}
