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

import in.raster.ihms.authserver.domain.custom.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Custom service to manage organization related functions.
 */
public interface OrganizationCustomService {

    /**
     * Save organization.
     *
     * @param organization - organization object
     * @param hospitalSealFile - seal file
     * @return organization object
     */
    Organization saveOrganization(final Organization organization, final MultipartFile hospitalSealFile);

    /**
     * Update organization.
     *
     * @param organization     - organization object
     * @param hospitalLogoFile - logo file
     * @return organization object
     */
    Organization updateOrganization(final Organization organization, final MultipartFile hospitalLogoFile);

    /**
     * Get active organizations.
     *
     * @return list of active organizations
     */
    List<Organization> getActiveOrganizations();

    /**
     * Get list of organizations by user name.
     *
     * @param userName - user name
     * @return list of organization
     */
    List<Organization> getOrganizationsByUserName(final String userName);

    /**
     * Get organization by name.
     *
     * @param organizationName - organization name
     * @return Organization
     */
    Organization getOrganizationByName(final String organizationName);

    /**
     * Get all organizations.
     *
     * @param pageable - pageable object
     * @return organization list
     */
    Page<Organization> getAllOrganizations(final Pageable pageable);
}
