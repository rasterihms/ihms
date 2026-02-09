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

import in.raster.ihms.authserver.domain.custom.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Custom service to manipulate roles.
 */
public interface RoleCustomService {

    /**
     * Create role.
     *
     * @param role    - role
     * @param request - http servlet request
     * @return Role
     */
    Role createRole(final Role role, final HttpServletRequest request);

    /**
     * Get list of roles by branch, organization and application.
     *
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @param applicationName  - application name
     * @return list of role
     */
    List<Role> getRolesByBranchAndApplication(final String branchName, final String organizationName,
                                              final String applicationName);

    /**
     * Get roles by user branch and application.
     *
     * @param branchName      - branch name
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return - list of roles
     */
    List<Role> getRolesByUserBranchAndApplication(final String branchName,
                                                  final String applicationName,
                                                  final HttpServletRequest request);

    /**
     * Get roles by user branch and application.
     *
     * @param branchName      - branch name
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return - list of roles
     */
    List<Role> getAllRolesByUserBranchAndApplication(final String branchName,
                                                     final String applicationName,
                                                     final HttpServletRequest request);
}
