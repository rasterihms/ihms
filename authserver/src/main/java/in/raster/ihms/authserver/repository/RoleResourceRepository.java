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

import in.raster.ihms.authserver.domain.Role;
import in.raster.ihms.authserver.domain.RoleResource;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the RoleResource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleResourceRepository extends JpaRepository<RoleResource, Long> {

    /**
     * Get list of role resource by role.
     *
     * @param role - Role object
     * @return list of RoleResource
     */
    List<RoleResource> findByRole(final Role role);
}
