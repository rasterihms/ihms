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
import in.raster.ihms.authserver.domain.Users;
import in.raster.ihms.authserver.domain.UsersRole;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the UsersRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsersRoleRepository extends JpaRepository<UsersRole, Long> {

    /**
     * Find users role by user.
     *
     * @param users - user object
     * @return list of UsersRole
     */
    List<UsersRole> findByUsers(Users users);

    /**
     * Find users role by role.
     *
     * @param role - role object
     * @return list of UsersRole
     */
    List<UsersRole> findByRole(Role role);

    /**
     * Find users role by user and role.
     *
     * @param users - user object
     * @param role - role object
     * @return UsersRole object
     */
    UsersRole findByUsersAndRole(final Users users, final Role role);
}
