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

import in.raster.ihms.authserver.service.dto.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

/**
 * Custom service to manipulate role dtos.
 */
public interface RoleDtoCustomService {

    /**
     * Get roles by application.
     *
     * @param applicationName - application name
     * @param roleName        - role name
     * @param pageable        - pageable object
     * @param request         - http servlet request
     * @return list of menus
     */
    Page<RoleDto> getApplicationRoles(final String applicationName, final String roleName,
                                      final Pageable pageable, final HttpServletRequest request);

    /**
     * Get roles by application.
     *
     * @param applicationName - application name
     * @param roleName        - role name
     * @param pageable        - pageable object
     * @param request         - http servlet request
     * @return list of menus
     */
    Page<RoleDto> getApplicationRolesForPharmacy(final String applicationName,
                                                 final String roleName,
                                                 final Pageable pageable,
                                                 final HttpServletRequest request);
}
