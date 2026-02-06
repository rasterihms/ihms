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

import java.util.Arrays;

/**
 * Custom object for ABDM phr otp confirm response.
 */
public class AbdmV3PhrOtpConfirmResponse {
    private String authResult;
    private String message;
    private AbdmV3OtpConfirmResponse tokens;
    private AbdmV3PhrUser[] users;

    public String getAuthResult() {
        return authResult;
    }

    public void setAuthResult(String authResult) {
        this.authResult = authResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AbdmV3OtpConfirmResponse getTokens() {
        return tokens;
    }

    public void setTokens(AbdmV3OtpConfirmResponse tokens) {
        this.tokens = tokens;
    }

    public AbdmV3PhrUser[] getUsers() {
        return users;
    }

    public void setUsers(AbdmV3PhrUser[] users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "AbdmV3PhrOtpConfirmResponse{" +
            "authResult='" + authResult + '\'' +
            ", message='" + message + '\'' +
            ", tokens=" + tokens +
            ", users=" + Arrays.toString(users) +
            '}';
    }
}
