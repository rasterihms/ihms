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

import in.raster.ihms.authserver.domain.custom.Role;
import in.raster.ihms.authserver.ldap.domain.LdapMenu;
import in.raster.ihms.authserver.ldap.domain.LdapRole;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.ldap.util.ApacheDSLdapDirectories;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.RoleDtoCustomService;
import in.raster.ihms.authserver.service.dto.RoleDto;
import in.raster.ihms.authserver.util.TokenUtil;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.commons.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom service implementation to manipulate role dtos.
 */
@Service
@Transactional
public class RoleDtoCustomServiceImpl implements RoleDtoCustomService {

    @Value("${ldap-defaults.login.username}")
    private String adminUserName;

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;
    private final TokenUtil tokenUtil;

    public RoleDtoCustomServiceImpl(LdapCustomService ldapCustomService, TokenUtil tokenUtil) {
        this.ldapCustomService = ldapCustomService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * Get roles by application.
     *
     * @param applicationName - application name
     * @param roleName        - role name
     * @param pageable        - pageable object
     * @param request         - http servlet request
     * @return list of menus
     */
    @Override
    public Page<RoleDto> getApplicationRoles(final String applicationName, final String roleName,
                                             final Pageable pageable, final HttpServletRequest request) {
        final List<RoleDto> roleDtoList = new ArrayList<>();
        final List<LdapRole> ldapRoleList = ldapCustomService.getApplicationRoles(applicationName, roleName,
            tokenUtil.getBranchIdFromToken(request), tokenUtil.getOrganizationIdFromToken(request));
        final Comparator<LdapRole> roleNameComparator = PaginationUtil.getComparator(LdapRole::getName, pageable);
        final Page<LdapRole> ldapRolePage = PaginationUtil.doCustomPaging(pageable, ldapRoleList, roleNameComparator);
        ldapRolePage.getContent().forEach(ldapRole -> {
            final RoleDto roleDto = new RoleDto();
            final Role role = ldapMapper.constructRoleFromLdapRole(ldapRole);
            final List<LdapMenu> ldapMenuList = ldapCustomService.getMenusByUserRole(role.getId(), applicationName);
            final List<LdapMenu> filteredList = ldapMenuList.stream().filter(ldapMenu -> ldapMenu.getParentMenuId() != null)
                .collect(Collectors.toList());
            roleDto.setRole(role);
            roleDto.setActiveMenus(filteredList.size());
            int numberOfUsers = CommonConstants.ZERO;
            if(!CollectionUtils.isEmpty(role.getMembers())) {
                final String defaultUserId = ApacheDSLdapDirectories.UID.getAttribute() + CommonConstants.EQUALS +
                    adminUserName;
                for(String member: role.getMembers()) {
                    if(member.startsWith(ApacheDSLdapDirectories.UID.getAttribute()) && !member.startsWith(defaultUserId)) {
                        numberOfUsers++;
                    }
                }
            }
            roleDto.setNumberOfUsers(numberOfUsers);
            roleDtoList.add(roleDto);
        });
        return PaginationUtil.convertListToPageImpl(roleDtoList, pageable, ldapRolePage.getTotalElements());
    }

    /**
     * Get roles by application.
     *
     * @param applicationName - application name
     * @param roleName        - role name
     * @param pageable        - pageable object
     * @param request         - http servlet request
     * @return list of menus
     */
    @Override
    public Page<RoleDto> getApplicationRolesForPharmacy(final String applicationName,
                                                        final String roleName,
                                                        final Pageable pageable,
                                                        final HttpServletRequest request) {
        final List<RoleDto> roleDtoList = new ArrayList<>();
        final List<LdapRole> ldapRoleList = ldapCustomService.getApplicationRoles(applicationName, roleName,
            tokenUtil.getBranchIdFromToken(request), tokenUtil.getOrganizationIdFromToken(request));
        if (!ObjectUtils.isEmpty(pageable.getSort())) {
            if (!ObjectUtils.isEmpty(pageable.getSort().getOrderFor("name"))) {
                Boolean isAscending = pageable.getSort().getOrderFor("name").isAscending();
                if (isAscending) {
                    ldapRoleList.sort(Comparator.comparing(LdapRole::getName, String::compareToIgnoreCase));
                } else {
                    ldapRoleList.sort(Comparator.comparing(LdapRole::getName, String::compareToIgnoreCase).reversed());
                }
            }
        } else {
            ldapRoleList.sort(Comparator.comparing(LdapRole::getName));
        }
        final Page<LdapRole> ldapRolePage = PaginationUtil.doCustomPaging(pageable, ldapRoleList);
        for (LdapRole ldapRole : ldapRoleList) {
            final RoleDto roleDto = new RoleDto();
            final Role role = ldapMapper.constructRoleFromLdapRole(ldapRole);
            final List<LdapMenu> ldapMenuList = ldapCustomService.getMenusByUserRole(role.getId(), applicationName);
            final List<LdapMenu> filteredList = ldapMenuList.stream().filter(ldapMenu -> ldapMenu.getParentMenuId() != null)
                .collect(Collectors.toList());
            roleDto.setRole(role);
            roleDto.setActiveMenus(filteredList.size());
            int numberOfUsers = CommonConstants.ZERO;
            if (!CollectionUtils.isEmpty(role.getMembers())) {
                final String defaultUserId = ApacheDSLdapDirectories.UID.getAttribute() + CommonConstants.EQUALS +
                    adminUserName;
                for (String member : role.getMembers()) {
                    if (member.startsWith(ApacheDSLdapDirectories.UID.getAttribute()) && !member.startsWith(defaultUserId)) {
                        numberOfUsers++;
                    }
                }
            }
            roleDto.setNumberOfUsers(numberOfUsers);
            roleDtoList.add(roleDto);
        }
        return PaginationUtil.convertListToPageImpl(roleDtoList, pageable, ldapRolePage.getTotalElements());
    }
}
