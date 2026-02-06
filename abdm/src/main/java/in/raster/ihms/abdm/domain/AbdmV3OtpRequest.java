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
 * Custom object for ABDM generate otp request.
 */
public class AbdmV3OtpRequest {

    private String txnId;
    private List<String> scope;
    private String loginHint;
    private String loginId;
    private String otpSystem;

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public String getLoginHint() {
        return loginHint;
    }

    public void setLoginHint(String loginHint) {
        this.loginHint = loginHint;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getOtpSystem() {
        return otpSystem;
    }

    public void setOtpSystem(String otpSystem) {
        this.otpSystem = otpSystem;
    }

    @Override
    public String toString() {
        return "AbdmV3OtpRequest{" +
            "txnId='" + txnId + '\'' +
            ", scope=" + scope +
            ", loginHint='" + loginHint + '\'' +
            ", loginId='" + loginId + '\'' +
            ", otpSystem='" + otpSystem + '\'' +
            '}';
    }
}
