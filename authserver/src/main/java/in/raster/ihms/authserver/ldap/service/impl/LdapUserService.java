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
import in.raster.ihms.authserver.ldap.repository.LdapGroupRepository;
import in.raster.ihms.authserver.ldap.repository.LdapUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Todo - We will keep this class for reference and will be removed in future.
 */
@Service
public class LdapUserService {

    @Autowired
    private LdapUserRepository userRepository;

    @Autowired
    private LdapGroupRepository ldapGroupRepository;

    public Boolean authenticate(final String username, final String password) {
        LdapUser user = userRepository.findByUsernameAndPassword(username, password);
        LdapGroup ldapGroup = ldapGroupRepository.findByName("Salem");
        return user != null;
    }

    public List<String> search(final String username) {
        List<LdapUser> userList = userRepository.findByUsernameLikeIgnoreCase(username);
        if (userList == null) {
            return Collections.emptyList();
        }

        return userList.stream()
                .map(LdapUser::getUsername)
                .collect(Collectors.toList());
    }

    public void create(final String username, final String password, String uid, String sname) {
        LdapUser newUser = new LdapUser(username, digestSHA(password));
        newUser.setId(LdapUtils.emptyLdapName());
        newUser.setSurname(sname);
        userRepository.save(newUser);

    }

    public void modify(final String username, final String password) {
        LdapUser user = userRepository.findByUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    private String digestSHA(final String password) {
        String base64;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(password.getBytes());
            base64 = Base64.getEncoder()
                    .encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "{SHA}" + base64;
    }
}
