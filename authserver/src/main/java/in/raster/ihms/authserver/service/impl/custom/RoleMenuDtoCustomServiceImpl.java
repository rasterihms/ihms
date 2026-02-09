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
import in.raster.ihms.authserver.domain.custom.Resource;
import in.raster.ihms.authserver.domain.custom.Role;
import in.raster.ihms.authserver.ldap.domain.LdapMenu;
import in.raster.ihms.authserver.ldap.domain.LdapResource;
import in.raster.ihms.authserver.ldap.domain.LdapRole;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.MenuCustomService;
import in.raster.ihms.authserver.service.custom.MenuDtoCustomService;
import in.raster.ihms.authserver.service.custom.MenuResourceCustomService;
import in.raster.ihms.authserver.service.custom.RoleMenuDtoCustomService;
import in.raster.ihms.authserver.service.dto.MenuDto;
import in.raster.ihms.authserver.service.dto.RoleMenuDto;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.TokenUtil;
import in.raster.ihms.commons.util.CommonConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom service implementation to manipulate user role.
 */
@Service
@Transactional
public class RoleMenuDtoCustomServiceImpl implements RoleMenuDtoCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;
    private final TokenUtil tokenUtil;
    private final MenuCustomService menuCustomService;
    private final MenuDtoCustomService menuDtoCustomService;
    private final MenuResourceCustomService menuResourceCustomService;

    public RoleMenuDtoCustomServiceImpl(LdapCustomService ldapCustomService, TokenUtil tokenUtil,
                                        MenuCustomService menuCustomService,
                                        MenuDtoCustomService menuDtoCustomService,
                                        MenuResourceCustomService menuResourceCustomService) {
        this.ldapCustomService = ldapCustomService;
        this.tokenUtil = tokenUtil;
        this.menuCustomService = menuCustomService;
        this.menuDtoCustomService = menuDtoCustomService;
        this.menuResourceCustomService = menuResourceCustomService;
    }

    /**
     * Create role.
     *
     * @param roleMenuDto - role menu dto
     * @param request     - http servlet request
     * @return RoleMenuDto
     */
    @Override
    public RoleMenuDto createRole(final RoleMenuDto roleMenuDto, final HttpServletRequest request) {
        final Role role = roleMenuDto.getRole();
        role.setCreatedBy(AuthServerUtil.getUser().getId());
        role.setModifiedBy(AuthServerUtil.getUser().getId());
        final String roleId = ldapCustomService.createApplicationRole(role, tokenUtil.getBranchIdFromToken(request),
            tokenUtil.getOrganizationIdFromToken(request));
        role.setId(roleId);
        addRoleToMenusAndResources(roleId, roleMenuDto.getMenuDtoList());
        roleMenuDto.setRole(role);
        return roleMenuDto;
    }

    /**
     * Add role to menus and resources.
     *
     * @param roleId      - role id
     * @param menuDtoList - menu dto list
     */
    private void addRoleToMenusAndResources(final String roleId, final List<MenuDto> menuDtoList) {
        menuDtoList.forEach(menuDto -> {
            ldapCustomService.addMemberToGroup(roleId, menuDto.getMenu().getId());
            menuDto.getSubMenuResourceDtoList().parallelStream().forEach(menuResourceDto -> {
                ldapCustomService.addMemberToGroup(roleId, menuResourceDto.getMenu().getId());
                menuResourceDto.getResourceList().forEach(resource -> {
                    ldapCustomService.addMemberToGroup(roleId, resource.getId());
                });
            });
        });
    }

    /**
     * Update role menus.
     *
     * @param roleMenuDto - role menu dto
     * @return RoleMenuDto
     */
    @Override
    public RoleMenuDto updateRoleMenus(final RoleMenuDto roleMenuDto) {
        final String roleId = roleMenuDto.getRole().getId();
        roleMenuDto.getMenuDtoList().forEach(menuDto -> {
            ldapCustomService.addMemberToGroup(roleId, menuDto.getMenu().getId());
            menuDto.getSubMenuResourceDtoList().parallelStream().forEach(menuResourceDto -> {
                ldapCustomService.addMemberToGroup(roleId, menuResourceDto.getMenu().getId());
                menuResourceDto.getResourceList().forEach(resource -> {
                    ldapCustomService.addMemberToGroup(roleId, resource.getId());
                });
            });
        });
        return roleMenuDto;
    }

    /**
     * Get role menu dto by role and application.
     *
     * @param roleName        - role name
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return role menu dto
     */
    @Override
    public RoleMenuDto getRoleMenuDto(final String roleName, final String applicationName,
                                      final HttpServletRequest request) {
        final RoleMenuDto roleMenuDto = new RoleMenuDto();
        final List<LdapRole> ldapRoleList = ldapCustomService.getLdapRoleByName(applicationName, roleName,
            tokenUtil.getBranchIdFromToken(request), tokenUtil.getOrganizationIdFromToken(request));
        if (!CollectionUtils.isEmpty(ldapRoleList)) {
            final Role role = ldapMapper.constructRoleFromLdapRole(ldapRoleList.get(CommonConstants.ZERO));
            final List<Menu> menuList = menuCustomService.getMenuListByApplication(applicationName);
            final List<LdapResource> ldapResourceList = getLdapResourceList(applicationName);
            final List<MenuDto> menuDtoList = menuDtoCustomService.constructMenuDtoList(menuList, ldapResourceList);
            roleMenuDto.setRole(role);
            roleMenuDto.setMenuDtoList(menuDtoList);
        }
        return roleMenuDto;
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
     * Todo - Commented code will be removed in future.
     * <p>
     * Update role.
     *
     * @param roleMenuDto - role menu dto
     * @return RoleMenuDto
     */
    public RoleMenuDto updateRole(final RoleMenuDto roleMenuDto) {
        final Role role = roleMenuDto.getRole();
        role.setModifiedDate(Instant.now());
        role.setModifiedBy(AuthServerUtil.getUser().getId());
        final List<LdapMenu> ldapMenuList = ldapCustomService.getMenusByUserRole(role.getId(), role.getApplicationName());
        final List<Menu> existingMenuList = ldapMapper.constructMenuListFromLdapMenus(ldapMenuList);
        final List<LdapResource> ldapResourceList = ldapCustomService
            .getResourcesByUserRole(role.getId(), role.getApplicationName());
        final List<Resource> existingResourceList = new ArrayList<>(CommonConstants.ZERO);
        ldapResourceList.forEach(ldapResource -> {
            final Resource resource = ldapMapper.constructResourceFromLdapResource(ldapResource);
            existingResourceList.add(resource);
        });
        final List<MenuDto> menuDtoList = roleMenuDto.getMenuDtoList();
        if (!CollectionUtils.isEmpty(menuDtoList)) {
            menuDtoList.forEach(menuDto -> {
                if (!existingMenuList.contains(menuDto.getMenu())) {
                    ldapCustomService.addMemberToGroup(role.getId(), menuDto.getMenu().getId());
                } else {
                    existingMenuList.remove(menuDto.getMenu());
                }
                menuDto.getSubMenuResourceDtoList().forEach(menuResourceDto -> {
                    if (!existingMenuList.contains(menuResourceDto.getMenu())) {
                        ldapCustomService.addMemberToGroup(role.getId(), menuResourceDto.getMenu().getId());
                    } else {
                        existingMenuList.remove(menuResourceDto.getMenu());
                    }
                    if (!CollectionUtils.isEmpty(menuResourceDto.getResourceList())) {
                        menuResourceDto.getResourceList().forEach(resource -> {
                            if (!existingResourceList.contains(resource)) {
                                ldapCustomService.addMemberToGroup(role.getId(), resource.getId());
                            } else {
                                existingResourceList.remove(resource);
                            }
                        });
                    }
                });
            });
            existingMenuList.forEach(menu -> {
                ldapCustomService.removeMemberFromGroup(role.getId(), menu.getId());
            });
            existingResourceList.forEach(resource -> {
                ldapCustomService.removeMemberFromGroup(role.getId(), resource.getId());
            });
        }
        return roleMenuDto;
    }
}
