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
import in.raster.ihms.commons.QueryDsl.ExtendedQueryDslJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data JPA repository for the Role entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, ExtendedQueryDslJpaRepository<Role> {

    /**
     * Find role by name.
     *
     * @param name - role name
     * @return Role
     */
    Role findByName(final String name);

    /**
     * Find role by name and application.
     *
     * @param name          - role name
     * @param applicationId - applicationId
     * @return Role
     */
    Role findByNameAndApplicationId(final String name, final Long applicationId);

    /**
     * To get roles by application
     *
     * @param applicationIdList applicationIdList
     * @return list of Roles
     */
    List<Role> findByApplicationIdIn(final List<Long> applicationIdList);
}
