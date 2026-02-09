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
package in.raster.ihms.authserver.domain.custom;

import in.raster.ihms.authserver.ldap.enumeration.UserType;

import java.util.Objects;

/**
 * Custom user class will be mapped from ldap user.
 */
public class User extends BaseEntity {

    private String id;
    private String userName;
    private String surName;
    private String password;
    private String mail;
    private String mobileNumber;
    private String employeeNumber;
    private UserType userType;
    private boolean creditAuthorizedUser;
    private Long creditAmountLimit;
    private boolean doctor;
    private Long doctorId;
    private String departmentIds;
    private String signFilePath;
    private String photoFilePath;
    private Long externalUserId;
    private Long defaultLocationId;
    private String locationIds;
    private String themeColor;
    private String pwdAccountLockedTime;
    private String departmentDisplayName;
    private boolean lightTheme;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isCreditAuthorizedUser() {
        return creditAuthorizedUser;
    }

    public void setCreditAuthorizedUser(boolean creditAuthorizedUser) {
        this.creditAuthorizedUser = creditAuthorizedUser;
    }

    public Long getCreditAmountLimit() {
        return creditAmountLimit;
    }

    public void setCreditAmountLimit(Long creditAmountLimit) {
        this.creditAmountLimit = creditAmountLimit;
    }

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getSignFilePath() {
        return signFilePath;
    }

    public void setSignFilePath(String signFilePath) {
        this.signFilePath = signFilePath;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }

    public Long getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(Long externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(String departmentIds) {
        this.departmentIds = departmentIds;
    }

    public Long getDefaultLocationId() {
        return defaultLocationId;
    }

    public void setDefaultLocationId(Long defaultLocationId) {
        this.defaultLocationId = defaultLocationId;
    }

    public String getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(String locationIds) {
        this.locationIds = locationIds;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

    public String getPwdAccountLockedTime() {
        return pwdAccountLockedTime;
    }

    public void setPwdAccountLockedTime(String pwdAccountLockedTime) {
        this.pwdAccountLockedTime = pwdAccountLockedTime;
    }

    public String getDepartmentDisplayName() {
        return departmentDisplayName;
    }

    public void setDepartmentDisplayName(String departmentDisplayName) {
        this.departmentDisplayName = departmentDisplayName;
    }

    public boolean isLightTheme() {
        return lightTheme;
    }

    public void setLightTheme(boolean lightTheme) {
        this.lightTheme = lightTheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", userName='" + userName + '\'' +
            ", surName='" + surName + '\'' +
            ", password='" + password + '\'' +
            ", mail='" + mail + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", employeeNumber='" + employeeNumber + '\'' +
            ", userType=" + userType +
            ", creditAuthorizedUser=" + creditAuthorizedUser +
            ", creditAmountLimit=" + creditAmountLimit +
            ", doctor=" + doctor +
            ", doctorId=" + doctorId +
            ", departmentIds='" + departmentIds + '\'' +
            ", signFilePath='" + signFilePath + '\'' +
            ", photoFilePath='" + photoFilePath + '\'' +
            ", externalUserId=" + externalUserId +
            ", defaultLocationId=" + defaultLocationId +
            ", locationIds='" + locationIds + '\'' +
            ", themeColor='" + themeColor + '\'' +
            ", pwdAccountLockedTime='" + pwdAccountLockedTime + '\'' +
            ", departmentDisplayName='" + departmentDisplayName + '\'' +
            ", lightTheme=" + lightTheme +
            '}';
    }
}
