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
 * Custom object for abha v3 phr profile.
 */
public class AbdmV3PhrProfile {

    private String abhaAddress;
    private String abhaNumber;
    private String address;
    private String[] authMethods;
    private String dateOfBirth;
    private int dayOfBirth;
    private String districtCode;
    private String email;
    private String emailVerified;
    private String firstName;
    private String fullName;
    private String gender;
    private String kycStatus;
    private String lastName;
    private String middleName;
    private String mobile;
    private boolean mobileVerified;
    private int monthOfBirth;
    private String pincode;
    private String profilePhoto;
    private String stateCode;
    private String stateName;
    private String status;
    private String subDistrictName;
    private String subDistrictCode;
    private int yearOfBirth;
    private String xToken;

    public String getAbhaAddress() {
        return abhaAddress;
    }

    public void setAbhaAddress(String abhaAddress) {
        this.abhaAddress = abhaAddress;
    }

    public String getAbhaNumber() {
        return abhaNumber;
    }

    public void setAbhaNumber(String abhaNumber) {
        this.abhaNumber = abhaNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getAuthMethods() {
        return authMethods;
    }

    public void setAuthMethods(String[] authMethods) {
        this.authMethods = authMethods;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubDistrictName() {
        return subDistrictName;
    }

    public void setSubDistrictName(String subDistrictName) {
        this.subDistrictName = subDistrictName;
    }

    public String getSubDistrictCode() {
        return subDistrictCode;
    }

    public void setSubDistrictCode(String subDistrictCode) {
        this.subDistrictCode = subDistrictCode;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getxToken() {
        return xToken;
    }

    public void setxToken(String xToken) {
        this.xToken = xToken;
    }

    @Override
    public String toString() {
        return "AbdmV3PhrProfile{" +
            "abhaAddress='" + abhaAddress + '\'' +
            ", abhaNumber='" + abhaNumber + '\'' +
            ", address='" + address + '\'' +
            ", authMethods=" + Arrays.toString(authMethods) +
            ", dateOfBirth='" + dateOfBirth + '\'' +
            ", dayOfBirth=" + dayOfBirth +
            ", districtCode='" + districtCode + '\'' +
            ", email='" + email + '\'' +
            ", emailVerified='" + emailVerified + '\'' +
            ", firstName='" + firstName + '\'' +
            ", fullName='" + fullName + '\'' +
            ", gender='" + gender + '\'' +
            ", kycStatus='" + kycStatus + '\'' +
            ", lastName='" + lastName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", mobile='" + mobile + '\'' +
            ", mobileVerified=" + mobileVerified +
            ", monthOfBirth=" + monthOfBirth +
            ", pincode='" + pincode + '\'' +
            ", profilePhoto='" + profilePhoto + '\'' +
            ", stateCode='" + stateCode + '\'' +
            ", stateName='" + stateName + '\'' +
            ", status='" + status + '\'' +
            ", subDistrictName='" + subDistrictName + '\'' +
            ", subDistrictCode='" + subDistrictCode + '\'' +
            ", yearOfBirth=" + yearOfBirth +
            ", xToken='" + xToken + '\'' +
            '}';
    }
}
