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

import in.raster.ihms.abdm.domain.AbdmV3Account;

import java.util.List;

/**
 * Custom object for ABDM verify otp mobile update response
 */
public class AbdmV3OtpConfirmResponse {

    private List<AbdmV3Account> accounts;
    private String authResult;
    private Long expiresIn;
    private String message;
    private Long refreshExpiresIn;
    private String refreshToken;
    private String token;
    private String txnId;

    public List<AbdmV3Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AbdmV3Account> accounts) {
        this.accounts = accounts;
    }

    public String getAuthResult() {
        return authResult;
    }

    public void setAuthResult(String authResult) {
        this.authResult = authResult;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(Long refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    @Override
    public String toString() {
        return "AbdmV3OtpConfirmResponse{" +
            "accounts=" + accounts +
            ", authResult='" + authResult + '\'' +
            ", expiresIn=" + expiresIn +
            ", message='" + message + '\'' +
            ", refreshExpiresIn=" + refreshExpiresIn +
            ", refreshToken='" + refreshToken + '\'' +
            ", token='" + token + '\'' +
            ", txnId='" + txnId + '\'' +
            '}';
    }
}
