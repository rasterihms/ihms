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
package in.raster.ihms.authserver.service.impl.custom;

import in.raster.ihms.authserver.domain.custom.Menu;
import in.raster.ihms.authserver.domain.custom.Resource;
import in.raster.ihms.authserver.ldap.domain.LdapResource;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.MenuCustomService;
import in.raster.ihms.authserver.service.custom.MenuDtoCustomService;
import in.raster.ihms.authserver.service.dto.MenuDto;
import in.raster.ihms.authserver.service.dto.MenuResourceDto;
import in.raster.ihms.authserver.util.AuthServerUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Custom service implementation to manipulate menu dtos.
 */
@Service
@Transactional
public class MenuDtoCustomServiceImpl implements MenuDtoCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final MenuCustomService menuCustomService;
    private final LdapCustomService ldapCustomService;

    public MenuDtoCustomServiceImpl(MenuCustomService menuCustomService,
                                    LdapCustomService ldapCustomService) {
        this.menuCustomService = menuCustomService;
        this.ldapCustomService = ldapCustomService;
    }

    /**
     * Construct menu dto.
     *
     * @param menuList - menu list
     * @return list of menu dto
     */
    @Override
    public List<MenuDto> constructMenuDtoList(final List<Menu> menuList) {
        final List<MenuDto> menuDtoList = new ArrayList<>();
        menuList.forEach(menu -> {
            if (ObjectUtils.isEmpty(menu.getParentMenu())) {
                final String menuName = AuthServerUtil.getNameFromParentMenuId(menu.getId());
                final MenuDto menuDto = new MenuDto();
                final List<Menu> subMenuList = menuList.stream()
                    .filter(menuObj -> Objects.nonNull(menuObj.getParentMenu()))
                    .filter(menuObj -> menuObj.getParentMenu().equals(menuName))
                    .collect(Collectors.toList());
                menuDto.setMenu(menu);
                subMenuList.sort(Comparator.comparing(Menu::getOrder));
                menuDto.setSubMenuList(subMenuList);
                menuDtoList.add(menuDto);
            }
        });
        return menuDtoList;
    }

    /**
     * Load all menu's for the given application.
     *
     * @param applicationName - application name
     * @return RoleDto
     */
    @Override
    public List<MenuDto> loadMenusAndResourceByApplication(final String applicationName) {
        final List<Menu> menuList = menuCustomService.getMenuListByApplication(applicationName);
        final List<LdapResource> ldapResourceList = getLdapResourceList(applicationName);
        return constructMenuDtoList(menuList, ldapResourceList);
    }

    /**
     * Get ldap resource list.
     *
     * @param applicationName - application name
     * @return list of ldap resource
     */
    private List<LdapResource> getLdapResourceList(final String applicationName) {
        return ldapCustomService.getActiveResourcesByApplication(applicationName);
    }

    /**
     * Construct menu dto list.
     *
     * @param menuList - menu list
     * @return list of menu dto
     */
    @Override
    public List<MenuDto> constructMenuDtoList(final List<Menu> menuList, final List<LdapResource> ldapResourceList) {
        final List<MenuDto> menuDtoList = new ArrayList<>();
        menuList.forEach(menu -> {
            if (ObjectUtils.isEmpty(menu.getParentMenu())) {
                final List<MenuResourceDto> menuResourceDtoList = new ArrayList<>();
                final String menuName = AuthServerUtil.getNameFromParentMenuId(menu.getId());
                final MenuDto menuDto = new MenuDto();
                final List<Menu> subMenuList = menuList.stream()
                    .filter(menuObj -> Objects.nonNull(menuObj.getParentMenu()))
                    .filter(menuObj -> menuObj.getParentMenu().equals(menuName))
                    .collect(Collectors.toList());
                subMenuList.forEach(subMenu -> {
                    final MenuResourceDto menuResourceDto = new MenuResourceDto();
                    final List<Resource> resourceList = new ArrayList<>();
                    ldapResourceList.forEach(ldapResource -> {
                        if (String.valueOf(ldapResource.getDn()).contains(subMenu.getId())) {
                            final Resource resource = ldapMapper.constructResourceFromLdapResource(ldapResource);
                            resourceList.add(resource);
                        }
                    });
                    menuResourceDto.setMenu(subMenu);
                    menuResourceDto.setResourceList(resourceList);
                    menuResourceDtoList.add(menuResourceDto);
                });
                menuDto.setMenu(menu);
                menuDto.setSubMenuResourceDtoList(menuResourceDtoList);
                menuDtoList.add(menuDto);
            }
        });
        return menuDtoList;
    }
}
