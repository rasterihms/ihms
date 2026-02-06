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

import java.util.List;

/**
 * Custom object for abha profile
 */
public class AbdmV3AadharEnrolmentProfile {

    private String firstName;
    private String middleName;
    private String lastName;
    private String dob;
    private String gender;
    private String photo;
    private String mobile;
    private List<String> phrAddress;
    private String address;
    private String districtCode;
    private String stateCode;
    private String pinCode;
    private String stateName;
    private String districtName;
    private String ABHANumber;
    private String abhaStatus;

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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<String> getPhrAddress() {
        return phrAddress;
    }

    public void setPhrAddress(List<String> phrAddress) {
        this.phrAddress = phrAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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

    public String getABHANumber() {
        return ABHANumber;
    }

    public void setABHANumber(String ABHANumber) {
        this.ABHANumber = ABHANumber;
    }

    public String getAbhaStatus() {
        return abhaStatus;
    }

    public void setAbhaStatus(String abhaStatus) {
        this.abhaStatus = abhaStatus;
    }

    @Override
    public String toString() {
        return "AbdmV3Profile{" +
            "firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", dob='" + dob + '\'' +
            ", gender='" + gender + '\'' +
            ", photo='" + photo + '\'' +
            ", mobile='" + mobile + '\'' +
            ", phrAddress=" + phrAddress +
            ", address='" + address + '\'' +
            ", districtCode='" + districtCode + '\'' +
            ", stateCode='" + stateCode + '\'' +
            ", pinCode='" + pinCode + '\'' +
            ", stateName='" + stateName + '\'' +
            ", districtName='" + districtName + '\'' +
            ", ABHANumber='" + ABHANumber + '\'' +
            ", abhaStatus='" + abhaStatus + '\'' +
            '}';
    }
}


