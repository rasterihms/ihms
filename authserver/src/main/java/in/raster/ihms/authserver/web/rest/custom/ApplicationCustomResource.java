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
import in.raster.ihms.authserver.domain.custom.Application;
import in.raster.ihms.authserver.service.custom.ApplicationCustomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Custom resource to manage application related apis.
 */
@RestController
@RequestMapping("/api")
public class ApplicationCustomResource {

    private final ApplicationCustomService applicationCustomService;

    public ApplicationCustomResource(ApplicationCustomService applicationCustomService) {
        this.applicationCustomService = applicationCustomService;
    }

    /**
     * Get applications by user and branch.
     *
     * @param request - http servlet request
     * @return - list of applications
     */
    @GetMapping("/applications-by-user")
    @Timed
    public List<Application> getApplicationsByUserAndBranch(final HttpServletRequest request) {
        return applicationCustomService.getApplicationsByUserAndBranch(request);
    }

    /**
     * Get list of applications by branch and organization.
     *
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of application
     */
    @GetMapping("/applications-by-branch-organization")
    @Timed
    public List<Application> getApplicationsByBranchAndOrganization(@RequestParam(value = "branchName") final String branchName,
                                                                    @RequestParam(value = "organizationName") final String organizationName) {
        return applicationCustomService.getApplicationsByBranchAndOrganization(branchName, organizationName);
    }
}
