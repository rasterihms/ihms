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
package in.raster.ihms.abdm.domain;

import java.util.Arrays;

/**
 * Custom object for abha v3 profile.
 */
public class AbdmV3Profile {

    private String ABHANumber;
    private String preferredAbhaAddress;
    private String mobile;
    private String firstName;
    private String middleName;
    private String lastName;
    private String name;
    private int yearOfBirth;
    private int dayOfBirth;
    private int monthOfBirth;
    private String gender;
    private String profilePhoto;
    private String status;
    private String stateCode;
    private String districtCode;
    private String pincode;
    private String address;
    private String kycPhoto;
    private String stateName;
    private String districtName;
    private String subdistrictName;
    private String townName;
    private String[] authMethods;
    private Object tags;
    private boolean kycVerified;
    private String verificationStatus;
    private String verificationType;
    private String createdDate;
    private String xToken;

    public String getABHANumber() {
        return ABHANumber;
    }

    public void setABHANumber(String ABHANumber) {
        this.ABHANumber = ABHANumber;
    }

    public String getPreferredAbhaAddress() {
        return preferredAbhaAddress;
    }

    public void setPreferredAbhaAddress(String preferredAbhaAddress) {
        this.preferredAbhaAddress = preferredAbhaAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSubdistrictName() {
        return subdistrictName;
    }

    public void setSubdistrictName(String subdistrictName) {
        this.subdistrictName = subdistrictName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String[] getAuthMethods() {
        return authMethods;
    }

    public void setAuthMethods(String[] authMethods) {
        this.authMethods = authMethods;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public boolean isKycVerified() {
        return kycVerified;
    }

    public void setKycVerified(boolean kycVerified) {
        this.kycVerified = kycVerified;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(String verificationType) {
        this.verificationType = verificationType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getKycPhoto() {
        return kycPhoto;
    }

    public void setKycPhoto(String kycPhoto) {
        this.kycPhoto = kycPhoto;
    }

    public String getxToken() {
        return xToken;
    }

    public void setxToken(String xToken) {
        this.xToken = xToken;
    }

    @Override
    public String toString() {
        return "AbdmV3Profile{" +
            "ABHANumber='" + ABHANumber + '\'' +
            ", preferredAbhaAddress='" + preferredAbhaAddress + '\'' +
            ", mobile='" + mobile + '\'' +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", name='" + name + '\'' +
            ", yearOfBirth=" + yearOfBirth +
            ", dayOfBirth=" + dayOfBirth +
            ", monthOfBirth=" + monthOfBirth +
            ", gender='" + gender + '\'' +
            ", profilePhoto='" + profilePhoto + '\'' +
            ", status='" + status + '\'' +
            ", stateCode='" + stateCode + '\'' +
            ", districtCode='" + districtCode + '\'' +
            ", pincode='" + pincode + '\'' +
            ", address='" + address + '\'' +
            ", kycPhoto='" + kycPhoto + '\'' +
            ", stateName='" + stateName + '\'' +
            ", districtName='" + districtName + '\'' +
            ", subdistrictName='" + subdistrictName + '\'' +
            ", townName='" + townName + '\'' +
            ", authMethods=" + Arrays.toString(authMethods) +
            ", tags=" + tags +
            ", kycVerified=" + kycVerified +
            ", verificationStatus='" + verificationStatus + '\'' +
            ", verificationType='" + verificationType + '\'' +
            ", createdDate='" + createdDate + '\'' +
            ", xToken='" + xToken + '\'' +
            '}';
    }
}
