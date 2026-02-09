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
import in.raster.ihms.authserver.domain.custom.Organization;
import in.raster.ihms.authserver.service.custom.OrganizationCustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Custom resource to manage organization related functions.
 */
@RestController
@RequestMapping("/api")
public class OrganizationCustomResource {

    private final OrganizationCustomService organizationCustomService;

    public OrganizationCustomResource(OrganizationCustomService organizationCustomService) {
        this.organizationCustomService = organizationCustomService;
    }

    /**
     * Save organization.
     *
     * @param organization     - organization object
     * @param hospitalSealFile - seal file
     * @return organization object
     */
    @PostMapping("/organizations-save")
    @Timed
    public Organization saveOrganization(@RequestBody final Organization organization,
                                         @RequestPart(value = "file", required = false) final MultipartFile hospitalSealFile) {
        return organizationCustomService.saveOrganization(organization, hospitalSealFile);
    }

    /**
     * Update organization.
     *
     * @param organization     - organization object
     * @param hospitalLogoFile - logo file
     * @return organization object
     */
    @PostMapping("/organizations-update")
    @Timed
    public Organization updateOrganization(@RequestPart final Organization organization,
                                           @RequestPart(value = "file", required = false) final MultipartFile hospitalLogoFile) {
        return organizationCustomService.updateOrganization(organization, hospitalLogoFile);
    }

    /**
     * Get active organizations.
     *
     * @return list of active organizations
     */
    @GetMapping("/organizations-active")
    @Timed
    public List<Organization> getActiveOrganizations() {
        return organizationCustomService.getActiveOrganizations();
    }

    /**
     * Get list of organizations by user name.
     *
     * @param userName - user name
     * @return list of organization
     */
    @GetMapping("/organizations-by-user")
    @Timed
    public List<Organization> getOrganizationsByUserName(@RequestParam(value = "userName") final String userName) {
        return organizationCustomService.getOrganizationsByUserName(userName);
    }

    /**
     * Get organization by name.
     *
     * @param name - organization name
     * @return Organization
     */
    @GetMapping("/organizations/{name}")
    @Timed
    public Organization getOrganizationByName(@PathVariable final String name) {
        return organizationCustomService.getOrganizationByName(name);
    }

    /**
     * Get all organizations.
     *
     * @param pageable - pageable object
     * @return organization list
     */
    @GetMapping("/organizations-page")
    @Timed
    public Page<Organization> getAllOrganizations(final Pageable pageable) {
        return organizationCustomService.getAllOrganizations(pageable);
    }
}
