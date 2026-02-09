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
import in.raster.ihms.authserver.ldap.domain.LdapRole;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.RoleCustomService;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.TokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;

/**
 * Custom service implementation to manipulate roles.
 */
@Service
@Transactional
public class RoleCustomServiceImpl implements RoleCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;
    private final TokenUtil tokenUtil;

    public RoleCustomServiceImpl(LdapCustomService ldapCustomService, TokenUtil tokenUtil) {
        this.ldapCustomService = ldapCustomService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * Create role.
     *
     * @param role    - role
     * @param request - http servlet request
     * @return Role
     */
    @Override
    public Role createRole(final Role role, final HttpServletRequest request) {
        role.setCreatedBy(AuthServerUtil.getUser().getId());
        role.setModifiedBy(AuthServerUtil.getUser().getId());
        role.setCreatedDate(Instant.now());
        role.setModifiedDate(Instant.now());
        final String roleId = ldapCustomService.createApplicationRole(role, tokenUtil.getBranchIdFromToken(request),
            tokenUtil.getOrganizationIdFromToken(request));
        role.setId(roleId);
        return role;
    }

    /**
     * Get list of roles by branch, organization and application.
     *
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @param applicationName  - application name
     * @return list of role
     */
    @Override
    public List<Role> getRolesByBranchAndApplication(final String branchName, final String organizationName,
                                                     final String applicationName) {
        final List<LdapRole> ldapRoleList = ldapCustomService.getRolesByUserBranchAndApplication(null, branchName,
            organizationName, applicationName);
        final List<Role> roleList = ldapMapper.constructRoleListFromLdapRoles(ldapRoleList);
        roleList.sort(Comparator.comparing(Role::getName));
        return roleList;
    }

    /**
     * Get roles by user branch and application.
     *
     * @param branchName      - branch name
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return - list of roles
     */
    @Override
    public List<Role> getRolesByUserBranchAndApplication(final String branchName,
                                                         final String applicationName,
                                                         final HttpServletRequest request) {
        final List<LdapRole> ldapRoles = ldapCustomService
            .getRolesByUserBranchAndApplication(AuthServerUtil.getUser().getId(), branchName,
                tokenUtil.getOrganizationIdFromToken(request), applicationName);
        return ldapMapper.constructRoleListFromLdapRoles(ldapRoles);
    }

    /**
     * Get roles by user branch and application.
     *
     * @param branchName      - branch name
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return - list of roles
     */
    @Override
    public List<Role> getAllRolesByUserBranchAndApplication(final String branchName,
                                                            final String applicationName,
                                                            final HttpServletRequest request) {
        final List<LdapRole> ldapRoles = ldapCustomService
            .getAllRolesByUserBranchAndApplication(AuthServerUtil.getUser().getId(), branchName,
                tokenUtil.getOrganizationIdFromToken(request), applicationName);
        return ldapMapper.constructRoleListFromLdapRoles(ldapRoles);
    }
}
