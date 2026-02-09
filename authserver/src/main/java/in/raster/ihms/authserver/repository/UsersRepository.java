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

import in.raster.ihms.authserver.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data JPA repository for the Users entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Find user by name.
     *
     * @param userName - user name
     * @return Users object
     */
    Users findByUserName(String userName);

    /**
     * Find users by name.
     *
     * @param userName - user name
     * @return list of Users object
     */
    List<Users> findByLdapIdContainsIgnoreCase(String userName);

    /**
     * Find users by name.
     *
     * @param userName - user name
     * @return list of Users object
     */
    List<Users> findByUserNameContainsIgnoreCase(final String userName);
}
