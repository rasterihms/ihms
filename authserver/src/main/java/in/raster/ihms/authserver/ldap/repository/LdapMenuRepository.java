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
package in.raster.ihms.authserver.ldap.repository;

import in.raster.ihms.authserver.ldap.domain.LdapMenu;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import javax.naming.Name;

/**
 * Ldap data repository to interact with ldap menu.
 */
@Repository
public interface LdapMenuRepository  extends LdapRepository<LdapMenu> {

    /**
     * Find ldap menu by menu id.
     *
     * @param menuDn - menu id
     * @return Ldap menu object
     */
    LdapMenu findByDn(final Name menuDn);

    /**
     * Find ldap menu by menu name.
     *
     * @param menuName - menu name
     * @return Ldap menu object
     */
    LdapMenu findByName(final String menuName);
}
