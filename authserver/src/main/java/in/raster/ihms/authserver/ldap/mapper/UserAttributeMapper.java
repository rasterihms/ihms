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
package in.raster.ihms.authserver.ldap.mapper;

import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.authserver.ldap.util.ApacheDSLdapDirectories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;

import javax.naming.Name;
import javax.naming.ldap.LdapName;

/**
 * Custom class to map ldap user from context.
 */
public class UserAttributeMapper extends AbstractContextMapper<LdapUser> {

    private final Logger log = LoggerFactory.getLogger(UserAttributeMapper.class);

    @Autowired
    private Environment env;

    private static final String LDAP_PARTITION_SUFFIX = "dc=skshospital,dc=com";

    /**
     * Map ldap user attributes from context.
     *
     * @param contextOperations - context object
     * @return ldap user
     */
    @Override
    protected LdapUser doMapFromContext(final DirContextOperations contextOperations) {
        final LdapUser person = new LdapUser();
        final LdapName userName = LdapUtils.newLdapName(contextOperations.getDn());
        log.info("LDAP user name : ", userName.toString());
        final Name dn = LdapNameBuilder.newInstance().add(String.valueOf(userName), LDAP_PARTITION_SUFFIX).build();
        log.info("LDAP user dn : ", dn.toString());
        person.setId(dn);
        person.setUsername(LdapUtils.getStringValue(userName, ApacheDSLdapDirectories.UID.getAttribute()));
        person.setSurname(contextOperations.getStringAttribute(ApacheDSLdapDirectories.SUR_NAME.getAttribute()));
        person.setMail(contextOperations.getStringAttribute(ApacheDSLdapDirectories.MAIL.getAttribute()));
        return person;
    }
}

