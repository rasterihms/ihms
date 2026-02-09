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

import in.raster.ihms.authserver.domain.custom.Application;
import in.raster.ihms.authserver.domain.custom.Resource;
import in.raster.ihms.authserver.domain.custom.Role;

import java.util.List;
import java.util.Objects;

/**
 * Custom dto to load application details.
 */
public class ApplicationDto {

    private Application application;
    private List<Role> roleList;
    private List<MenuDto> menuDtoList;
    private List<Resource> resourceList;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<MenuDto> getMenuDtoList() {
        return menuDtoList;
    }

    public void setMenuDtoList(List<MenuDto> menuDtoList) {
        this.menuDtoList = menuDtoList;
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
        if (!(o instanceof ApplicationDto)) return false;
        ApplicationDto that = (ApplicationDto) o;
        return Objects.equals(getApplication(), that.getApplication());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApplication());
    }

    @Override
    public String toString() {
        return "ApplicationDto{" +
            "application=" + application +
            ", roleList=" + roleList +
            ", menuDtoList=" + menuDtoList +
            ", resourceList=" + resourceList +
            '}';
    }
}
