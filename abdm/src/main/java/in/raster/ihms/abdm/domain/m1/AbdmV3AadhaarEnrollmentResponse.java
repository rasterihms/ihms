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

import in.raster.ihms.abdm.domain.AbdmV3AadharEnrolmentProfile;
import in.raster.ihms.abdm.domain.AbdmV3Token;

/**
 * Custom object for ABDM aadhaar response
 */
public class AbdmV3AadhaarEnrollmentResponse {

    private String message;
    private String txnId;
    private AbdmV3Token tokens;
    private AbdmV3AadharEnrolmentProfile ABHAProfile;
    private boolean isNew;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public AbdmV3Token getTokens() {
        return tokens;
    }

    public void setTokens(AbdmV3Token tokens) {
        this.tokens = tokens;
    }

    public AbdmV3AadharEnrolmentProfile getABHAProfile() {
        return ABHAProfile;
    }

    public void setABHAProfile(AbdmV3AadharEnrolmentProfile ABHAProfile) {
        this.ABHAProfile = ABHAProfile;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    @Override
    public String toString() {
        return "AbdmV3AadhaarResponse{" +
            "message='" + message + '\'' +
            ", txnId='" + txnId + '\'' +
            ", tokens=" + tokens +
            ", ABHAProfile=" + ABHAProfile +
            ", isNew=" + isNew +
            '}';
    }
}
