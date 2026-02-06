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

import in.raster.ihms.abdm.domain.AbdmAcknowledgement;
import in.raster.ihms.abdm.domain.AbdmError;
import in.raster.ihms.abdm.domain.AbdmResponse;

import java.time.Instant;

/**
 * Custom object for ABDM shared profile acknowledgement
 */
public class AbdmProfileShareAcknowledgement {

    private String requestId;
    private Instant timestamp;
    private AbdmAcknowledgement acknowledgement;
    private AbdmError error;
    private AbdmResponse resp;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public AbdmAcknowledgement getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(AbdmAcknowledgement acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    public AbdmError getError() {
        return error;
    }

    public void setError(AbdmError error) {
        this.error = error;
    }

    public AbdmResponse getResp() {
        return resp;
    }

    public void setResp(AbdmResponse resp) {
        this.resp = resp;
    }

    @Override
    public String toString() {
        return "AbdmSharedProfileAcknowledgement{" +
            "requestId='" + requestId + '\'' +
            ", timestamp=" + timestamp +
            ", acknowledgement=" + acknowledgement +
            ", error=" + error +
            ", resp=" + resp +
            '}';
    }
}
