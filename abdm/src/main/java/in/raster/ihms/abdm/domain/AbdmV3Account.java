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
 * Custom object for ABDM account
 */
public class AbdmV3Account {

    private String ABHANumber;
    private String name;
    private String preferredAbhaAddress;
    private String profilePhoto;
    private String status;
    private String dob;
    private String gender;
    private boolean kycVerified;
    private String verificationType;
    private String verifiedStatus;

    public String getABHANumber() {
        return ABHANumber;
    }

    public void setABHANumber(String ABHANumber) {
        this.ABHANumber = ABHANumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreferredAbhaAddress() {
        return preferredAbhaAddress;
    }

    public void setPreferredAbhaAddress(String preferredAbhaAddress) {
        this.preferredAbhaAddress = preferredAbhaAddress;
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

    public boolean isKycVerified() {
        return kycVerified;
    }

    public void setKycVerified(boolean kycVerified) {
        this.kycVerified = kycVerified;
    }

    public String getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(String verificationType) {
        this.verificationType = verificationType;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    @Override
    public String toString() {
        return "AbdmV3Account{" +
            "ABHANumber='" + ABHANumber + '\'' +
            ", name='" + name + '\'' +
            ", preferredAbhaAddress='" + preferredAbhaAddress + '\'' +
            ", profilePhoto='" + profilePhoto + '\'' +
            ", status='" + status + '\'' +
            ", dob='" + dob + '\'' +
            ", gender='" + gender + '\'' +
            ", kycVerified=" + kycVerified +
            ", verificationType='" + verificationType + '\'' +
            ", verifiedStatus='" + verifiedStatus + '\'' +
            '}';
    }
}
