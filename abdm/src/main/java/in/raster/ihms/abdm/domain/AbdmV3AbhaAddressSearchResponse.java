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
 * Custom object for abha address search response.
 */
public class AbdmV3AbhaAddressSearchResponse {
    private String abhaAddress;
    private String[] authMethods;
    private String[] blockedAuthMethods;
    private String fullName;
    private String healthIdNumber;
    private String message;
    private String mobile;
    private String status;

    public String getAbhaAddress() {
        return abhaAddress;
    }

    public void setAbhaAddress(String abhaAddress) {
        this.abhaAddress = abhaAddress;
    }

    public String[] getAuthMethods() {
        return authMethods;
    }

    public void setAuthMethods(String[] authMethods) {
        this.authMethods = authMethods;
    }

    public String[] getBlockedAuthMethods() {
        return blockedAuthMethods;
    }

    public void setBlockedAuthMethods(String[] blockedAuthMethods) {
        this.blockedAuthMethods = blockedAuthMethods;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHealthIdNumber() {
        return healthIdNumber;
    }

    public void setHealthIdNumber(String healthIdNumber) {
        this.healthIdNumber = healthIdNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AbdmV3AbhaAddressSearchResponse{" +
            "abhaAddress='" + abhaAddress + '\'' +
            ", authMethods=" + Arrays.toString(authMethods) +
            ", blockedAuthMethods=" + Arrays.toString(blockedAuthMethods) +
            ", fullName='" + fullName + '\'' +
            ", healthIdNumber='" + healthIdNumber + '\'' +
            ", message='" + message + '\'' +
            ", mobile='" + mobile + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}
