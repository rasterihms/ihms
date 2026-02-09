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
package in.raster.ihms.authserver.web.rest.custom;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.domain.custom.Branch;
import in.raster.ihms.authserver.service.custom.BranchCustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Custom resource to manage branches.
 */
@RestController
@RequestMapping("/api")
public class BranchCustomResource {

    private final BranchCustomService branchCustomService;

    public BranchCustomResource(BranchCustomService branchCustomService) {
        this.branchCustomService = branchCustomService;
    }

    /**
     * Create branch in ldap.
     *
     * @param branch  - branch object
     * @param request - http servlet request
     * @return saved branch
     */
    @PostMapping("/branches")
    @Timed
    public ResponseEntity<Branch> createBranch(@Valid @RequestBody final Branch branch,
                                               final HttpServletRequest request) {
        final Branch savedBranch = branchCustomService.createBranch(branch, request);
        return new ResponseEntity<>(savedBranch, HttpStatus.OK);
    }

    /**
     * Modify branch attributes.
     *
     * @param branch  - branch object
     * @param request - http servlet request
     */
    @PutMapping("/branches")
    @Timed
    public void modifyBranchAttributes(@Valid @RequestBody final Branch branch, final HttpServletRequest request) {
        branchCustomService.modifyBranchAttributes(branch, request);
    }

    /**
     * Get all branches.
     *
     * @param branchName - branch name to search
     * @param pageable   - pageable object
     * @param request    - http servlet request
     * @return list of branches
     */
    @GetMapping("/branches-page")
    @Timed
    public Page<Branch> getAllBranches(@RequestParam(value = "branchName", required = false) final String branchName,
                                       final Pageable pageable,
                                       final HttpServletRequest request) {
        return branchCustomService.getAllBranches(branchName, pageable, request);
    }

    /**
     * Get list of branches by organization.
     *
     * @param organizationName - organization name
     * @return list of branch
     */
    @GetMapping("/branches-by-organization")
    @Timed
    public List<Branch> getBranchesByOrganization(@RequestParam(value = "organizationName") final String organizationName) {
        return branchCustomService.getBranchesByOrganization(organizationName);
    }

    /**
     * Get list of branches by user name and organization.
     *
     * @param userName - user name
     * @return list of branch
     */
    @GetMapping("/branches-by-user-organization")
    @Timed
    public List<Branch> getBranchesByUserAndOrganization(@RequestParam(value = "userName") final String userName,
                                                         @RequestParam(value = "organizationName") final String organizationName) {
        return branchCustomService.getBranchesByUserAndOrganization(userName, organizationName);
    }

    /**
     * Get current branch billing limit.
     *
     * @param request - http servlet request
     * @return billing limit
     */
    @GetMapping("/branches-billing-limit")
    @Timed
    public Long getCurrentBranchBillingLimit(final HttpServletRequest request) {
        return branchCustomService.getCurrentBranchBillingLimit(request);
    }

    /**
     * Get branch by name.
     *
     * @param branchName - branch name
     * @return branch object
     */
    @GetMapping("/branches-by-name")
    @Timed
    public Branch getBranchByName(@RequestParam(value = "name") final String branchName) {
        return branchCustomService.getBranchByName(branchName);
    }
}
