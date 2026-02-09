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
import in.raster.ihms.authserver.domain.custom.Branch;

import java.util.List;
import java.util.Objects;

/**
 * Custom dto to load branch details.
 */
public class BranchDto {

    private Branch branch;
    private List<Application> applicationList;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BranchDto)) return false;
        BranchDto branchDto = (BranchDto) o;
        return Objects.equals(getBranch(), branchDto.getBranch());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBranch());
    }

    @Override
    public String toString() {
        return "BranchDto{" +
            "branch=" + branch +
            ", applicationList=" + applicationList +
            '}';
    }
}
