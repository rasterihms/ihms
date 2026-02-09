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
package in.raster.ihms.authserver.repository;

import in.raster.ihms.authserver.domain.Menu;
import in.raster.ihms.authserver.domain.MenuResource;
import in.raster.ihms.authserver.domain.Resource;
import in.raster.ihms.commons.QueryDsl.ExtendedQueryDslJpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the MenuResource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuResourceRepository extends JpaRepository<MenuResource, Long>, ExtendedQueryDslJpaRepository<MenuResource> {

    /**
     * Get list of menu resource by resource.
     *
     * @param resource - resource object
     * @return list of MenuResource
     */
    List<MenuResource> findByResource(final Resource resource);

    /**
     * Get list of menu resource by menu.
     *
     * @param menu - menu object
     * @return list of MenuResource
     */
    List<MenuResource> findByMenu(final Menu menu);
}
