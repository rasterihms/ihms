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

import in.raster.ihms.authserver.ldap.domain.LdapDepartment;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Custom repository class to query from ldap.
 */
@Repository
public interface LdapDepartmentRepository extends LdapRepository<LdapDepartment> {

    /**
     * Find ldap department by name.
     *
     * @param name - user name
     * @return LdapDepartment object
     */
    LdapDepartment findByName(final String name);

    /**
     * Find ldap department by description.
     *
     * @param description - description
     * @return LdapDepartment object
     */
    List<LdapDepartment> findByDescription(final String description);

    /**
     * Find ldap department by code.
     *
     * @param code - code
     * @return LdapDepartment object
     */
    List<LdapDepartment> findByCode(final String code);

    /**
     * Find ldap department by display order.
     *
     * @param displayOrder - display Order
     * @return LdapDepartment object
     */
    List<LdapDepartment> findByDisplayOrder(final Integer displayOrder);
}
