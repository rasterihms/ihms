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

import in.raster.ihms.abdm.domain.AbdmV3AuthData;
import in.raster.ihms.abdm.domain.AbdmV3Consent;

/**
 * Custom object for ABDM aadhaar request
 */
public class AbdmV3AadhaarEnrollmentRequest {

    private AbdmV3AuthData authData;
    private AbdmV3Consent consent;

    public AbdmV3AuthData getAuthData() {
        return authData;
    }

    public void setAuthData(AbdmV3AuthData authData) {
        this.authData = authData;
    }

    public AbdmV3Consent getConsent() {
        return consent;
    }

    public void setConsent(AbdmV3Consent consent) {
        this.consent = consent;
    }

    @Override
    public String toString() {
        return "AbdmV3AadhaarRequest{" +
            "authData=" + authData +
            ", consent=" + consent +
            '}';
    }
}
