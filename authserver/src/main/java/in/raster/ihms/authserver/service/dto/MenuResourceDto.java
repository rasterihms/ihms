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

import in.raster.ihms.authserver.domain.custom.Menu;
import in.raster.ihms.authserver.domain.custom.Resource;

import java.util.List;
import java.util.Objects;

/**
 * Custom dto to map menu and resource list.
 */
public class MenuResourceDto {

    private Menu menu;
    private List<Resource> resourceList;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuResourceDto)) return false;
        MenuResourceDto that = (MenuResourceDto) o;
        return Objects.equals(getMenu(), that.getMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenu());
    }

    @Override
    public String toString() {
        return "MenuResourceDto{" +
            "menu=" + menu +
            ", resourceList=" + resourceList +
            '}';
    }
}
