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

import in.raster.ihms.authserver.domain.custom.Menu;
import in.raster.ihms.authserver.domain.custom.Resource;
import in.raster.ihms.authserver.domain.custom.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Custom service to manipulate menu resource.
 */
public interface MenuResourceCustomService {

    /**
     * Get all resources by application.
     *
     * @param applicationName - application name
     * @param resourceName    - resource name to search
     * @param menuName        - menu name
     * @param pageable        - pageable object
     * @return list of resources
     */
    Page<Resource> getApplicationResources(final String applicationName, final String resourceName,
                                           final String menuName, final Pageable pageable);

    /**
     * Save resource.
     *
     * @param resource - resource object
     * @return saved resource
     */
    Resource saveResource(final Resource resource);

    /**
     * Modify resource attributes.
     *
     * @param resource - resource object
     */
    Resource modifyResourceAttributes(final Resource resource);

    /**
     * Delete resource by given dn.
     *
     * @param resourceName    - resource name
     * @param applicationName - application name
     * @param menuName        - menu name
     */
    void deleteApplicationResource(final String resourceName, final String applicationName,
                                   final String menuName);

    /**
     * Get resources by user roles and application.
     *
     * @param roleList        - role list
     * @param applicationName - application name
     * @return list of resources
     */
    List<Resource> getResourceByUserRoles(final List<Role> roleList, final String applicationName);
}
