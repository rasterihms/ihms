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
package in.raster.ihms.authserver.service.dto;


import in.raster.ihms.authserver.domain.custom.Role;

import java.util.Objects;

/**
 * Custom dto to load role and permission count.
 */
public class RoleDto {

    private Role role;
    private int activeMenus;
    private int numberOfUsers;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getActiveMenus() {
        return activeMenus;
    }

    public void setActiveMenus(int activeMenus) {
        this.activeMenus = activeMenus;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDto)) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(getRole(), roleDto.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole());
    }

    @Override
    public String toString() {
        return "RoleDto{" +
            "role=" + role +
            ", activeMenus=" + activeMenus +
            ", numberOfUsers=" + numberOfUsers +
            '}';
    }
}
