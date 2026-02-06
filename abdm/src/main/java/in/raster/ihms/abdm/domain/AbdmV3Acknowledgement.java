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
 * Custom object for ABDM V3 acknowledgement
 */
public class AbdmV3Acknowledgement {

    private AbdmAcknowledgementStatus status;
    private String abhaAddress;
    private AbdmV3AcknowledgementProfile profile;

    public AbdmAcknowledgementStatus getStatus() {
        return status;
    }

    public void setStatus(AbdmAcknowledgementStatus status) {
        this.status = status;
    }

    public String getAbhaAddress() {
        return abhaAddress;
    }

    public void setAbhaAddress(String abhaAddress) {
        this.abhaAddress = abhaAddress;
    }

    public AbdmV3AcknowledgementProfile getProfile() {
        return profile;
    }

    public void setProfile(AbdmV3AcknowledgementProfile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "AbdmV3Acknowledgement{" +
            "status=" + status +
            ", abhaAddress='" + abhaAddress + '\'' +
            ", profile=" + profile +
            '}';
    }
}
