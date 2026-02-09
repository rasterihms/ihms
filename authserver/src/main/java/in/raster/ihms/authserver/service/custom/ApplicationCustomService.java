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

import in.raster.ihms.authserver.domain.custom.Application;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Custom service to manage application related functions.
 */
public interface ApplicationCustomService {

    /**
     * Get applications by user and branch.
     *
     * @param request - http servlet request
     * @return - list of applications
     */
    List<Application> getApplicationsByUserAndBranch(final HttpServletRequest request);

    /**
     * Get list of applications by branch and organization.
     *
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of application
     */
    List<Application> getApplicationsByBranchAndOrganization(final String branchName, final String organizationName);
}
