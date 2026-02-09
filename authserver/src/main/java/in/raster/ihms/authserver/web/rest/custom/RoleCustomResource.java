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
import in.raster.ihms.authserver.service.custom.RoleCustomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Custom resource implementation to manipulate roles.
 */
@RestController
@RequestMapping("/api")
public class RoleCustomResource {

    private final RoleCustomService roleCustomService;

    public RoleCustomResource(RoleCustomService roleCustomService) {
        this.roleCustomService = roleCustomService;
    }

    /**
     * Create role.
     *
     * @param role    - role
     * @param request - http servlet request
     * @return Role
     */
    @PostMapping("/roles-save")
    @Timed
    public ResponseEntity<Role> createRole(@Valid @RequestBody final Role role,
                                           final HttpServletRequest request) {
        final Role savedRole = roleCustomService.createRole(role, request);
        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }

    /**
     * Get list of roles by branch, organization and application.
     *
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @param applicationName  - application name
     * @return list of role
     */
    @GetMapping("/roles-by-branch-application")
    @Timed
    public List<Role> getRolesByBranchAndApplication(@RequestParam(value = "branchName") final String branchName,
                                                     @RequestParam(value = "organizationName") final String organizationName,
                                                     @RequestParam(value = "applicationName") final String applicationName) {
        return roleCustomService.getRolesByBranchAndApplication(branchName, organizationName, applicationName);
    }
}
