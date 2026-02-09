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
package in.raster.ihms.authserver.service.custom;

import in.raster.ihms.authserver.domain.custom.Menu;
import in.raster.ihms.authserver.ldap.domain.LdapResource;
import in.raster.ihms.authserver.service.dto.MenuDto;

import java.util.List;

/**
 * Custom service to manipulate menu dtos.
 */
public interface MenuDtoCustomService {

    /**
     * Construct menu dto.
     *
     * @param menuList - menu list
     * @return list of menu dto
     */
    List<MenuDto> constructMenuDtoList(final List<Menu> menuList);

    /**
     * Load all menu's for the given application.
     *
     * @param applicationName - application name
     * @return RoleDto
     */
    List<MenuDto> loadMenusAndResourceByApplication(final String applicationName);

    /**
     * Construct menu dto list by menu and resource.
     *
     * @param menuList - menu list
     * @return list of menu dto
     */
    List<MenuDto> constructMenuDtoList(final List<Menu> menuList,
                                       final List<LdapResource> ldapResourceList);
}
