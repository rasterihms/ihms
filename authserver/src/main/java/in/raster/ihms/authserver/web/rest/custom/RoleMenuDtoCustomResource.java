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
import in.raster.ihms.authserver.domain.custom.Role;
import in.raster.ihms.authserver.service.custom.RoleMenuDtoCustomService;
import in.raster.ihms.authserver.service.dto.RoleMenuDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Custom resource to manipulate roles.
 */
@RestController
@RequestMapping("/api")
public class RoleMenuDtoCustomResource {

    private final Logger log = LoggerFactory.getLogger(RoleMenuDtoCustomResource.class);

    private final RoleMenuDtoCustomService roleMenuDtoCustomService;

    public RoleMenuDtoCustomResource(RoleMenuDtoCustomService roleMenuDtoCustomService) {
        this.roleMenuDtoCustomService = roleMenuDtoCustomService;
    }

    /**
     * Create role.
     *
     * @param roleMenuDto - role menu dto
     * @param request     - http servlet request
     * @return RoleMenuDto
     */
    @PostMapping("/role-menu-dtos")
    @Timed
    public ResponseEntity<RoleMenuDto> createRole(@Valid @RequestBody final RoleMenuDto roleMenuDto,
                                                  final HttpServletRequest request) {
        final RoleMenuDto savedRoleMenuDto = roleMenuDtoCustomService.createRole(roleMenuDto, request);
        return new ResponseEntity<>(savedRoleMenuDto, HttpStatus.OK);
    }

    /**
     * Update role menus.
     *
     * @param roleMenuDto - role menu dto
     * @return RoleMenuDto
     */
    @PostMapping("/role-menu-dtos-update-menus")
    @Timed
    public ResponseEntity<RoleMenuDto> updateRoleMenus(@Valid @RequestBody final RoleMenuDto roleMenuDto) {
        final RoleMenuDto savedRoleMenuDto = roleMenuDtoCustomService.updateRoleMenus(roleMenuDto);
        return new ResponseEntity<>(savedRoleMenuDto, HttpStatus.OK);
    }

    /**
     * Get role menu dto by role and application.
     *
     * @param roleName        - role name
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return role menu dto
     */
    @GetMapping("/role-menu-dtos")
    @Timed
    public RoleMenuDto getRoleMenuDto(@RequestParam(value = "roleName") final String roleName,
                                      @RequestParam(value = "application") final String applicationName,
                                      final HttpServletRequest request) {
        return roleMenuDtoCustomService.getRoleMenuDto(roleName, applicationName, request);
    }

    /**
     * Update role.
     *
     * @param roleMenuDto - role menu dto
     * @return RoleMenuDto
     */
    @PutMapping("/role-menu-dtos")
    @Timed
    public ResponseEntity<RoleMenuDto> updateRole(@Valid @RequestBody final RoleMenuDto roleMenuDto) {
        final RoleMenuDto updatedRoleMenuDto = roleMenuDtoCustomService.updateRole(roleMenuDto);
        return new ResponseEntity<>(updatedRoleMenuDto, HttpStatus.OK);
    }
}
