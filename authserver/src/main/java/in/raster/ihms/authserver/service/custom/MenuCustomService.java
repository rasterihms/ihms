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
import in.raster.ihms.authserver.domain.custom.Role;
import in.raster.ihms.authserver.service.dto.MenuDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Custom service to manipulate menus.
 */
public interface MenuCustomService {

    /**
     * Get all menus by application id.
     *
     * @param applicationName - application name
     * @param menuName        - menu name
     * @param parentMenuName  - parent menu name
     * @param active          - active flag
     * @param pageable        - pageable object
     * @return list of menus
     */
    Page<Menu> getPageableMenus(final String applicationName, final String menuName,
                                final String parentMenuName, final Boolean active,
                                final Pageable pageable);

    /**
     * Get menus by application id.
     *
     * @param applicationName - application name
     * @param menuName        - menu name
     * @param parentMenuName  - parent menu name
     * @param active          - active flag
     * @return list of menus
     */
    List<Menu> getMenus(final String applicationName, final String menuName,
                        final String parentMenuName, final Boolean active);

    /**
     * Get only parent menus by application.
     *
     * @param applicationName - application name
     * @return list of menus
     */
    List<Menu> getParentMenus(final String applicationName);

    /**
     * Get sub menus by application.
     *
     * @param applicationName - application name
     * @return list of menus
     */
    List<Menu> getSubMenus(final String applicationName);

    /**
     * Save menu.
     *
     * @param menu - menu object
     * @return saved menu
     */
    Menu saveMenu(final Menu menu);

    /**
     * Modify menu attributes.
     *
     * @param menu - menu object
     */
    Menu modifyMenuAttributes(final Menu menu);

    /**
     * Get menus by user roles and application.
     *
     * @param roleList        - user role list
     * @param applicationName - application name
     * @return - list of menus
     */
    List<Menu> getMenusByUserRoles(final List<Role> roleList, final String applicationName);

    /**
     * Get menu list by application.
     *
     * @param applicationName - application name
     * @return list of menu
     */
    List<Menu> getMenuListByApplication(final String applicationName);
}


