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

import in.raster.ihms.authserver.domain.custom.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Custom service to manage branches.
 */
public interface BranchCustomService {

    /**
     * Create branch in ldap.
     *
     * @param branch  - branch object
     * @param request - http servlet request
     * @return saved branch
     */
    Branch createBranch(final Branch branch, final HttpServletRequest request);

    /**
     * Modify branch attributes.
     *
     * @param branch  - branch object
     * @param request - http servlet request
     */
    void modifyBranchAttributes(final Branch branch, final HttpServletRequest request);

    /**
     * Get all branches.
     *
     * @param branchName - branch name to search
     * @param pageable   - pageable object
     * @param request    - http servlet request
     * @return list of branches
     */
    Page<Branch> getAllBranches(final String branchName, final Pageable pageable, final HttpServletRequest request);

    /**
     * Get list of branches by organization.
     *
     * @param organizationName - organization name
     * @return list of branch
     */
    List<Branch> getBranchesByOrganization(final String organizationName);

    /**
     * Get list of branches by user name and organization.
     *
     * @param userName - user name
     * @return list of branch
     */
    List<Branch> getBranchesByUserAndOrganization(final String userName, final String organizationName);

    /**
     * Get current branch billing limit.
     *
     * @param request - http servlet request
     * @return billing limit
     */
    Long getCurrentBranchBillingLimit(final HttpServletRequest request);

    /**
     * Get branch by name.
     *
     * @param branchName - branch name
     * @return branch object
     */
    Branch getBranchByName(final String branchName);
}
