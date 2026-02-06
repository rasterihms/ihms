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

import in.raster.ihms.abdm.domain.AbdmError;
import in.raster.ihms.abdm.domain.AbdmResponse;
import in.raster.ihms.abdm.domain.AbdmV3Acknowledgement;

/**
 * Custom object for ABDM shared profile V3 acknowledgement
 */
public class AbdmV3ProfileShareAcknowledgement {

    private AbdmV3Acknowledgement acknowledgement;
    private AbdmResponse response;
    private AbdmError error;

    public AbdmV3Acknowledgement getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(AbdmV3Acknowledgement acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    public AbdmResponse getResponse() {
        return response;
    }

    public void setResponse(AbdmResponse response) {
        this.response = response;
    }

    public AbdmError getError() {
        return error;
    }

    public void setError(AbdmError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "AbdmV3ProfileShareAcknowledgement{" +
            "acknowledgement=" + acknowledgement +
            ", response=" + response +
            ", error=" + error +
            '}';
    }
}
