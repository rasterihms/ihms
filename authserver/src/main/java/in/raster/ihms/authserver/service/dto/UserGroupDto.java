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
import in.raster.ihms.authserver.domain.custom.Organization;
import in.raster.ihms.authserver.domain.custom.Role;

import java.util.Objects;

/**
 * Custom dto to save user group details.
 */
public class UserGroupDto {

    private Organization organization;
    private Branch branch;
    private Application application;
    private Role role;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroupDto)) return false;
        UserGroupDto that = (UserGroupDto) o;
        return Objects.equals(getOrganization(), that.getOrganization()) &&
            Objects.equals(getBranch(), that.getBranch()) &&
            Objects.equals(getApplication(), that.getApplication()) &&
            Objects.equals(getRole(), that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrganization(), getBranch(), getApplication(), getRole());
    }

    @Override
    public String toString() {
        return "UserGroupDto{" +
            "organization=" + organization +
            ", branch=" + branch +
            ", application=" + application +
            ", role=" + role +
            '}';
    }
}
