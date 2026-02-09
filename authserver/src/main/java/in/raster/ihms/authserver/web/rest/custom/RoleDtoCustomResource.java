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
package in.raster.ihms.authserver.web.rest.custom;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.service.custom.RoleDtoCustomService;
import in.raster.ihms.authserver.service.dto.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Custom resource implementation to manipulate role dtos.
 */
@RestController
@RequestMapping("/api")
public class RoleDtoCustomResource {

    private final RoleDtoCustomService roleDtoCustomService;

    public RoleDtoCustomResource(RoleDtoCustomService roleDtoCustomService) {
        this.roleDtoCustomService = roleDtoCustomService;
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
    @GetMapping("/role-dtos")
    @Timed
    public Page<RoleDto> getApplicationRoles(@RequestParam(value = "application") final String applicationName,
                                             @RequestParam(value = "roleName", required = false) final String roleName,
                                             final Pageable pageable, final HttpServletRequest request) {
        return roleDtoCustomService.getApplicationRoles(applicationName, roleName, pageable, request);
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
    @GetMapping("/role-dtos-pharmacy")
    @Timed
    public Page<RoleDto> getApplicationRolesForPharmacy(@RequestParam(value = "application") final String applicationName,
                                                        @RequestParam(value = "roleName", required = false) final String roleName,
                                                        final Pageable pageable,
                                                        final HttpServletRequest request) {
        return roleDtoCustomService.getApplicationRolesForPharmacy(applicationName, roleName, pageable, request);
    }
}
