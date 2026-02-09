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
import in.raster.ihms.authserver.service.dto.RoleMenuDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Custom service to manipulate role menu dtos.
 */
public interface RoleMenuDtoCustomService {


    /**
     * Create role.
     *
     * @param roleMenuDto - role menu dto
     * @param request     - http servlet request
     * @return RoleMenuDto
     */
    RoleMenuDto createRole(final RoleMenuDto roleMenuDto, final HttpServletRequest request);

    /**
     * Update role menus.
     *
     * @param roleMenuDto - role menu dto
     * @return RoleMenuDto
     */
    RoleMenuDto updateRoleMenus(final RoleMenuDto roleMenuDto);

    /**
     * Get role menu dto by role and application.
     *
     * @param roleName        - role name
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return role menu dto
     */
    RoleMenuDto getRoleMenuDto(final String roleName, final String applicationName,
                               final HttpServletRequest request);

    /**
     * Update role.
     *
     * @param roleMenuDto - role menu dto
     * @return RoleMenuDto
     */
    RoleMenuDto updateRole(final RoleMenuDto roleMenuDto);
}
