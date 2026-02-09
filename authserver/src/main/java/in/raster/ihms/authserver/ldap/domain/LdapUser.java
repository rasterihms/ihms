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
package in.raster.ihms.authserver.ldap.domain;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.Objects;

/**
 * Custom class to map user from ldap.
 */
@Entry(objectClasses = {"person", "inetOrgPerson", "top"})  //base = "ou=users",
public class LdapUser {

    public LdapUser() {
    }

    public LdapUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    private Name id;

    @Attribute(name = "uid")
    private String username;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String surname;

    @Attribute(name = "userPassword")
    private String password;

    @Attribute(name = "mail")
    private String mail;

    @Attribute(name = "mobile")
    private String mobileNumber;

    @Attribute(name = "employeeNumber")
    private String employeeNumber;

    @Attribute(name = "userType")
    private String userType;

    @Attribute(name = "isCreditAuthorizedUser")
    private String isCreditAuthorizedUser;

    @Attribute(name = "creditAmountLimit")
    private String creditAmountLimit;

    @Attribute(name = "isDoctor")
    private String isDoctor;

    @Attribute(name = "doctorId")
    private String doctorId;

    @Attribute(name = "departmentNumber")
    private String department;

    @Attribute(name = "signFilePath")
    private String signFilePath;

    @Attribute(name = "photoFilePath")
    private String photoFilePath;

    @Attribute(name = "externalUserId")
    private String externalUserId;

    @Attribute(name = "defaultLocationId")
    private String defaultLocationId;

    @Attribute(name = "locationIds")
    private String locationIds;

    @Attribute(name = "themeColor")
    private String themeColor;

    @Attribute(name = "pwdAccountLockedTime")
    private String pwdAccountLockedTime;

    @Attribute(name = "active")
    private String active;

    @Attribute(name = "createdBy")
    private String createdBy;

    @Attribute(name = "modifiedBy")
    private String modifiedBy;

    @Attribute(name = "createdDate")
    private String createdDate;

    @Attribute(name = "modifiedDate")
    private String modifiedDate;

    @Attribute(name = "lightTheme")
    private String lightTheme;

    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsCreditAuthorizedUser() {
        return isCreditAuthorizedUser;
    }

    public void setIsCreditAuthorizedUser(String isCreditAuthorizedUser) {
        this.isCreditAuthorizedUser = isCreditAuthorizedUser;
    }

    public String getCreditAmountLimit() {
        return creditAmountLimit;
    }

    public void setCreditAmountLimit(String creditAmountLimit) {
        this.creditAmountLimit = creditAmountLimit;
    }

    public String getIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(String isDoctor) {
        this.isDoctor = isDoctor;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSignFilePath() {
        return signFilePath;
    }

    public void setSignFilePath(String signFilePath) {
        this.signFilePath = signFilePath;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getDefaultLocationId() {
        return defaultLocationId;
    }

    public void setDefaultLocationId(String defaultLocationId) {
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getLightTheme() {
        return lightTheme;
    }

    public void setLightTheme(String lightTheme) {
        this.lightTheme = lightTheme;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LdapUser)) return false;
        LdapUser ldapUser = (LdapUser) o;
        return Objects.equals(getId(), ldapUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "LdapUser{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", commonName='" + commonName + '\'' +
            ", surname='" + surname + '\'' +
            ", password='" + password + '\'' +
            ", mail='" + mail + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", employeeNumber='" + employeeNumber + '\'' +
            ", userType='" + userType + '\'' +
            ", isCreditAuthorizedUser='" + isCreditAuthorizedUser + '\'' +
            ", creditAmountLimit='" + creditAmountLimit + '\'' +
            ", isDoctor='" + isDoctor + '\'' +
            ", doctorId='" + doctorId + '\'' +
            ", department='" + department + '\'' +
            ", signFilePath='" + signFilePath + '\'' +
            ", photoFilePath='" + photoFilePath + '\'' +
            ", externalUserId='" + externalUserId + '\'' +
            ", defaultLocationId='" + defaultLocationId + '\'' +
            ", locationIds='" + locationIds + '\'' +
            ", themeColor='" + themeColor + '\'' +
            ", pwdAccountLockedTime='" + pwdAccountLockedTime + '\'' +
            ", active='" + active + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            ", createdDate='" + createdDate + '\'' +
            ", modifiedDate='" + modifiedDate + '\'' +
            ", lightTheme='" + lightTheme + '\'' +
            '}';
    }
}

