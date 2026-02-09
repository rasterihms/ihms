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

import in.raster.ihms.authserver.domain.custom.Menu;

import java.util.List;
import java.util.Objects;

/**
 * Custom dto to map menu, sub menu and resource list.
 */
public class MenuDto {

    private Menu menu;
    private List<Menu> subMenuList;
    private List<MenuResourceDto> subMenuResourceDtoList;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<Menu> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public List<MenuResourceDto> getSubMenuResourceDtoList() {
        return subMenuResourceDtoList;
    }

    public void setSubMenuResourceDtoList(List<MenuResourceDto> subMenuResourceDtoList) {
        this.subMenuResourceDtoList = subMenuResourceDtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuDto)) return false;
        MenuDto menuDto = (MenuDto) o;
        return Objects.equals(getMenu(), menuDto.getMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenu());
    }

    @Override
    public String toString() {
        return "MenuDto{" +
            "menu=" + menu +
            ", subMenuList=" + subMenuList +
            ", subMenuResourceDtoList=" + subMenuResourceDtoList +
            '}';
    }
}
