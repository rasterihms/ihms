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

import in.raster.ihms.authserver.domain.custom.User;

import java.util.List;
import java.util.Objects;

/**
 * Custom dto to save user details.
 */
public class UserDto {

    private User user;
    private List<UserGroupDto> userGroupDtoList;
    private List<String> locationNames;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserGroupDto> getUserGroupDtoList() {
        return userGroupDtoList;
    }

    public void setUserGroupDtoList(List<UserGroupDto> userGroupDtoList) {
        this.userGroupDtoList = userGroupDtoList;
    }

    public List<String> getLocationNames() {
        return locationNames;
    }

    public void setLocationNames(List<String> locationNames) {
        this.locationNames = locationNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(getUser(), userDto.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }

    @Override
    public String toString() {
        return "UserDto{" +
            "user=" + user +
            ", userGroupDtoList=" + userGroupDtoList +
            ", locationNames=" + locationNames +
            '}';
    }
}
