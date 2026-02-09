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

import in.raster.ihms.authserver.domain.Role;

public class RolePermissionDto {
    private Role role;
    private int activePermissions;
    private int noOfUsers;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getActivePermissions() {
        return activePermissions;
    }

    public void setActivePermissions(int activePermissions) {
        this.activePermissions = activePermissions;
    }

    public int getNoOfUsers() {
        return noOfUsers;
    }

    public void setNoOfUsers(int noOfUsers) {
        this.noOfUsers = noOfUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RolePermissionDto)) return false;

        RolePermissionDto that = (RolePermissionDto) o;

        if (getActivePermissions() != that.getActivePermissions()) return false;
        if (getNoOfUsers() != that.getNoOfUsers()) return false;
        return getRole() != null ? getRole().equals(that.getRole()) : that.getRole() == null;
    }

    @Override
    public int hashCode() {
        int result = getRole() != null ? getRole().hashCode() : 0;
        result = 31 * result + getActivePermissions();
        result = 31 * result + getNoOfUsers();
        return result;
    }

    @Override
    public String toString() {
        return "RolePermissionDto{" +
            "role=" + role +
            ", activePermissions=" + activePermissions +
            ", noOfUsers=" + noOfUsers +
            '}';
    }
}
