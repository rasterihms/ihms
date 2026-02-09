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

import in.raster.ihms.authserver.domain.custom.Role;

import java.util.List;
import java.util.Objects;

/**
 * Custom dto to map role and menus.
 */
public class RoleMenuDto {

    private Role role;
    private List<MenuDto> menuDtoList;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<MenuDto> getMenuDtoList() {
        return menuDtoList;
    }

    public void setMenuDtoList(List<MenuDto> menuDtoList) {
        this.menuDtoList = menuDtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleMenuDto)) return false;
        RoleMenuDto that = (RoleMenuDto) o;
        return Objects.equals(getRole(), that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole());
    }

    @Override
    public String toString() {
        return "RoleMenuDto{" +
            "role=" + role +
            ", menuDtoList=" + menuDtoList +
            '}';
    }
}
