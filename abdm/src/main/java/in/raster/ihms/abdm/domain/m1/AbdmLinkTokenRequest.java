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
package in.raster.ihms.abdm.domain.m1;

/**
 * Custom object for link token request
 */
public class AbdmLinkTokenRequest {

    private long abhaNumber;
    private String abhaAddress;
    private String name;
    private String gender;
    private int yearOfBirth;

    public long getAbhaNumber() {
        return abhaNumber;
    }

    public void setAbhaNumber(long abhaNumber) {
        this.abhaNumber = abhaNumber;
    }

    public String getAbhaAddress() {
        return abhaAddress;
    }

    public void setAbhaAddress(String abhaAddress) {
        this.abhaAddress = abhaAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "AbdmLinkTokenRequest{" +
            "abhaNumber=" + abhaNumber +
            ", abhaAddress='" + abhaAddress + '\'' +
            ", name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", yearOfBirth=" + yearOfBirth +
            '}';
    }
}
