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
package in.raster.ihms.authserver.service.dto;

import in.raster.ihms.authserver.domain.Role;
import in.raster.ihms.authserver.domain.Users;
import in.raster.ihms.authserver.domain.UsersRole;

import javax.management.relation.RoleList;
import java.util.List;
import java.util.Objects;

/**
 * Custom dto object to handle user creation.
 */
public class UserRoleDto {
    private String userName;
    private String password;
    private Users user;
    private List<Role> roleList;
    private List<UsersRole> userRoleList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<UsersRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UsersRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleDto)) return false;
        UserRoleDto that = (UserRoleDto) o;
        return Objects.equals(getUserName(), that.getUserName()) &&
            Objects.equals(getPassword(), that.getPassword()) &&
            Objects.equals(getUser(), that.getUser()) &&
            Objects.equals(getRoleList(), that.getRoleList()) &&
            Objects.equals(getUserRoleList(), that.getUserRoleList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getPassword(), getUser(), getRoleList(), getUserRoleList());
    }

    @Override
    public String toString() {
        return "UserRoleDto{" +
            "userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", user=" + user +
            ", roleList=" + roleList +
            ", userRoleList=" + userRoleList +
            '}';
    }
}
