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
package in.raster.ihms.authserver.service.impl.custom;


import in.raster.ihms.authserver.domain.custom.Menu;
import in.raster.ihms.authserver.domain.custom.Role;
import in.raster.ihms.authserver.ldap.domain.LdapMenu;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.MenuCustomService;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.commons.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.*;

/**
 * Custom service implementation to manipulate menus.
 */
@Service
@Transactional
public class MenuCustomServiceImpl implements MenuCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;

    public MenuCustomServiceImpl(LdapCustomService ldapCustomService) {
        this.ldapCustomService = ldapCustomService;
    }

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
    @Override
    public Page<Menu> getPageableMenus(final String applicationName, final String menuName,
                                       final String parentMenuName, final Boolean active,
                                       final Pageable pageable) {
        final List<Menu> menuList = getMenus(applicationName, menuName, parentMenuName, active);
        if (ObjectUtils.isEmpty(parentMenuName))
            return PaginationUtil.doCustomPaging(pageable, menuList);
        else
            return PaginationUtil.doCustomPaging(pageable, menuList, Comparator.comparing(Menu::getOrder));
    }

    /**
     * Get menus by application id.
     *
     * @param applicationName - application name
     * @param menuName        - menu name
     * @param parentMenuName  - parent menu name
     * @param active          - active flag
     * @return list of menus
     */
    @Override
    public List<Menu> getMenus(final String applicationName, final String menuName,
                               final String parentMenuName, final Boolean active) {
        final List<LdapMenu> ldapMenuList = ldapCustomService.getApplicationMenus(applicationName, menuName,
            parentMenuName, active);
        final List<Menu> menuList = ldapMapper.constructMenuListFromLdapMenus(ldapMenuList);
        menuList.sort(Comparator.comparing(Menu::getOrder));
        return menuList;
    }

    /**
     * Get only parent menus by application.
     *
     * @param applicationName - application name
     * @return list of menus
     */
    public List<Menu> getParentMenus(final String applicationName) {
        final List<Menu> menuList = new ArrayList<>();
        final List<LdapMenu> ldapMenuList = ldapCustomService.getApplicationMenus(applicationName, null,
            null, CommonConstants.TRUE);
        ldapMenuList.forEach(ldapMenu -> {
            if (ObjectUtils.isEmpty(ldapMenu.getParentMenuId())) {
                final Menu menu = ldapMapper.constructMenuFromLdapMenu(ldapMenu);
                menuList.add(menu);
            }
        });
        menuList.sort(Comparator.comparing(Menu::getOrder));
        return menuList;
    }

    /**
     * Get sub menus by application.
     *
     * @param applicationName - application name
     * @return list of menus
     */
    @Override
    public List<Menu> getSubMenus(final String applicationName) {
        final List<LdapMenu> ldapMenuList = ldapCustomService.getApplicationMenus(applicationName, null,
            null, CommonConstants.TRUE);
        final List<Menu> menuList = ldapMapper.constructMenuListFromLdapMenus(ldapMenuList);
        menuList.sort(Comparator.comparing(Menu::getOrder));
        return menuList;
    }

    /**
     * Save menu.
     *
     * @param menu - menu object
     * @return saved menu object
     */
    @Override
    public Menu saveMenu(final Menu menu) {
        final String userId = AuthServerUtil.getUser().getId();
        menu.setCreatedDate(Instant.now());
        menu.setModifiedDate(Instant.now());
        menu.setCreatedBy(userId);
        menu.setModifiedBy(userId);
        final String menuId = ldapCustomService.createApplicationMenu(menu);
        menu.setId(menuId);
        return menu;
    }

    /**
     * Modify menu attributes.
     *
     * @param menu - menu object
     */
    @Override
    public Menu modifyMenuAttributes(final Menu menu) {
        menu.setModifiedBy(AuthServerUtil.getUser().getId());
        menu.setModifiedDate(Instant.now());
        ldapCustomService.modifyMenuAttributes(menu);
        return menu;
    }

    /**
     * Get menus by user roles and application.
     *
     * @param roleList        - user role list
     * @param applicationName - application name
     * @return - list of menus
     */
    @Override
    public List<Menu> getMenusByUserRoles(final List<Role> roleList, final String applicationName) {
        final Set<Menu> menuSet = new HashSet<>();
        roleList.forEach(role -> {
            final List<LdapMenu> ldapMenuList = ldapCustomService.getMenusByUserRole(role.getId(), applicationName);
            ldapMenuList.forEach(ldapMenu -> {
                final Menu menu = ldapMapper.constructMenuFromLdapMenu(ldapMenu);
                menuSet.add(menu);
            });
        });
        return new ArrayList<>(menuSet);
    }

    /**
     * Get menu list by application.
     *
     * @param applicationName - application name
     * @return list of menu
     */
    @Override
    public List<Menu> getMenuListByApplication(final String applicationName) {
        final List<LdapMenu> ldapMenuList = ldapCustomService
            .getApplicationMenus(applicationName, null, null, CommonConstants.TRUE);
        ldapMenuList.sort(Comparator.comparing(LdapMenu::getOrder));
        return ldapMapper.constructMenuListFromLdapMenus(ldapMenuList);
    }
}
