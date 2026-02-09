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
import in.raster.ihms.authserver.domain.RoleMenu;
import in.raster.ihms.authserver.domain.RoleResource;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the RoleMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {

    /**
     * Get list of role menus by role.
     *
     * @param role - Role object
     * @return list of RoleMenu
     */
    List<RoleMenu> findByRole(final Role role);

    /**
     * Get sub menu list by role and parent menu id.
     *
     * @param parentMenuId - parent menu id
     * @return list of RoleMenu
     */
    List<RoleMenu> findByRoleAndMenuParentMenuId(final Role role, final Long parentMenuId);

    /**
     * Get sub menu list by role, parent menu id and permission type not null.
     *
     * @param parentMenuId - parent menu id
     * @return list of RoleMenu
     */
    List<RoleMenu> findByRoleAndMenuParentMenuIdAndPermissionTypeNotNull(final Role role, final Long parentMenuId);

    /**
     * Get sub menu list by role and PermissionType Not Null.
     *
     * @param  role - role
     * @return list of RoleMenu
     */
    List<RoleMenu> findByRoleAndPermissionTypeNotNull(final Role role);
}
