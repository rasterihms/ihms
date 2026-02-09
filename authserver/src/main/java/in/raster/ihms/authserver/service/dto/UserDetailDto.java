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

import in.raster.ihms.authserver.domain.Application;
import in.raster.ihms.authserver.domain.Menu;
import in.raster.ihms.authserver.domain.Resource;
import in.raster.ihms.commons.security.JwtUser;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Custom dto to load user details.
 */
public class UserDetailDto {
    private JwtUser user;
    private List<BranchDto> branchDtoList;
    private List<ApplicationDto> applicationDtoList;

    public JwtUser getUser() {
        return user;
    }

    public void setUser(JwtUser user) {
        this.user = user;
    }

    public List<BranchDto> getBranchDtoList() {
        return branchDtoList;
    }

    public void setBranchDtoList(List<BranchDto> branchDtoList) {
        this.branchDtoList = branchDtoList;
    }

    public List<ApplicationDto> getApplicationDtoList() {
        return applicationDtoList;
    }

    public void setApplicationDtoList(List<ApplicationDto> applicationDtoList) {
        this.applicationDtoList = applicationDtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailDto)) return false;
        UserDetailDto that = (UserDetailDto) o;
        return Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }

    @Override
    public String toString() {
        return "UserDetailDto{" +
            "user=" + user +
            ", branchDtoList=" + branchDtoList +
            ", applicationDtoList=" + applicationDtoList +
            '}';
    }
}
