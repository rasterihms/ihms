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

import in.raster.ihms.authserver.ldap.domain.LdapGroup;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.authserver.ldap.repository.LdapUserRepository;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.ldap.service.impl.LdapClientService;
import in.raster.ihms.authserver.ldap.util.JwtUserFactory;
import in.raster.ihms.authserver.ldap.util.LdapUtil;
import in.raster.ihms.authserver.util.ExceptionConstants;
import in.raster.ihms.authserver.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * User detail service implementation to load ldap user into spring context.
 */
@Service
@Transactional
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LdapCustomService ldapCustomService;

    @Autowired
    private LdapUserRepository ldapUserRepository;

    @Autowired
    private LdapUtil ldapUtil;

    /**
     * Load user from ldap by name.
     *
     * @param userName - use rname
     * @return User Details object
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
        final List<LdapGroup> userRoles = ldapCustomService.getLdapGroupsByMemberDn(String.valueOf(ldapUser.getId()));
        if (ObjectUtils.isEmpty(ldapUser)) {
            ExceptionUtil.throwUsernameNotFoundException(String.format(ExceptionConstants.NO_USER_FOUND_WITH_GIVEN_NAME, userName));
        }
        return JwtUserFactory.create(ldapUser, userRoles);
    }
}
