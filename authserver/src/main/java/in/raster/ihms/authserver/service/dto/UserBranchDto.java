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

import in.raster.ihms.authserver.domain.custom.Branch;

import java.util.List;
import java.util.Objects;

/**
 * Custom dto to save user branch details.
 */
public class UserBranchDto {

    private Branch branch;
    private List<ApplicationDto> applicationDtoList;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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
        if (!(o instanceof UserBranchDto)) return false;
        UserBranchDto that = (UserBranchDto) o;
        return Objects.equals(getBranch(), that.getBranch());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBranch());
    }

    @Override
    public String toString() {
        return "UserBranchDto{" +
            "branch=" + branch +
            ", applicationDtoList=" + applicationDtoList +
            '}';
    }
}
