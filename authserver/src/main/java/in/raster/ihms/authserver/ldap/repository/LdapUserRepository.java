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
package in.raster.ihms.authserver.ldap.repository;

import in.raster.ihms.authserver.ldap.domain.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Custom repository class to query from ldap.
 */
@Repository
public interface LdapUserRepository extends LdapRepository<LdapUser> {

    /**
     * Find ldap user by name.
     *
     * @param userName - user name
     * @return Ldap user object
     */
    LdapUser findByUsername(final String userName);

    /**
     * Find ldap user by name and password.
     *
     * @param userName - user name
     * @param password - password
     * @return Ldap user object
     */
    LdapUser findByUsernameAndPassword(final String userName, final String password);

    /**
     * Find list of ldap user by name.
     *
     * @param userName - user name
     * @return list of ldap user object
     */
    List<LdapUser> findByUsernameLikeIgnoreCase(final String userName);

    /**
     * Todo : This method is used in pharmacy, do not delete
     * Find ldap user by employeeNumber.
     *
     * @param employeeNumber - number of the employee
     * @return Ldap user object
     */
    LdapUser findByEmployeeNumber(final String employeeNumber);

    /**
     * Find list of ldap user by name/employeeNumber.
     *
     * @param userName       : username
     * @param employeeNumber : employeeNumber
     * @return list of ldap user object
     */
    List<LdapUser> findByCommonNameContainsIgnoreCaseOrEmployeeNumberContainsIgnoreCase(final String userName,
                                                                                        final String employeeNumber);

    /**
     * Find ldap user by location not null.
     *
     * @return Ldap user object
     */
    List<LdapUser> findByLocationIdsIsNotNull();

    /**
     * Find ldap user by location id.
     *
     * @param locationId - location id
     * @return list of ldap user object
     */
    List<LdapUser> findByLocationIds(final String locationId);

    /**
     * Find ldap user by location id.
     *
     * @param locationId - location id
     * @return list of ldap user object
     */
    List<LdapUser> findByDefaultLocationId(final String locationId);
}
