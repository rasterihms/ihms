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

import in.raster.ihms.abdm.domain.enumeration.AbdmGender;

/**
 * Custom object for ABDM V3 profile share patient.
 */
public class AbdmV3ProfileSharePatient {

    private String abhaNumber;
    private String abhaAddress;
    private String name;
    private AbdmGender gender;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private String phoneNumber;
    private AbdmV1ProfileShareAddress address;

    public String getAbhaNumber() {
        return abhaNumber;
    }

    public void setAbhaNumber(String abhaNumber) {
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

    public AbdmGender getGender() {
        return gender;
    }

    public void setGender(AbdmGender gender) {
        this.gender = gender;
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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AbdmV1ProfileShareAddress getAddress() {
        return address;
    }

    public void setAddress(AbdmV1ProfileShareAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AbdmV3ProfileSharePatient{" +
                "abhaNumber='" + abhaNumber + '\'' +
                ", abhaAddress='" + abhaAddress + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", dayOfBirth=" + dayOfBirth +
                ", monthOfBirth=" + monthOfBirth +
                ", yearOfBirth=" + yearOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
}
