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

import in.raster.ihms.abdm.domain.enumeration.AbdmAcknowledgementStatus;

/**
 * Custom object for ABDM acknowledgement
 */
public class AbdmAcknowledgement {

    private AbdmAcknowledgementStatus status;
    private String healthId;
    private String tokenNumber;

    public AbdmAcknowledgementStatus getStatus() {
        return status;
    }

    public void setStatus(AbdmAcknowledgementStatus status) {
        this.status = status;
    }

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    @Override
    public String toString() {
        return "AbdmAcknowledgement{" +
            "status=" + status +
            ", healthId='" + healthId + '\'' +
            ", tokenNumber='" + tokenNumber + '\'' +
            '}';
    }
}
