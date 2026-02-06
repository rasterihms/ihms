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
 * Custom object for ABDM auth data
 */
public class AbdmV3AuthData {

    private List<String> authMethods;
    private AbdmV3Otp otp;

    public List<String> getAuthMethods() {
        return authMethods;
    }

    public void setAuthMethods(List<String> authMethods) {
        this.authMethods = authMethods;
    }

    public AbdmV3Otp getOtp() {
        return otp;
    }

    public void setOtp(AbdmV3Otp otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "AbdmV3AuthData{" +
            "authMethods=" + authMethods +
            ", otp=" + otp +
            '}';
    }
}
