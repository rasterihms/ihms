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

import in.raster.ihms.abdm.domain.AbdmV1Intent;
import in.raster.ihms.abdm.domain.AbdmV1Location;

/**
 * Custom object for ABDM v1 profile share response.
 */
public class AbdmV1FacilityQRResponse {

    private String requestId;
    private String timestamp;
    private AbdmV1Intent intent;
    private AbdmV1Location location;
    private AbdmV1Profile profile;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public AbdmV1Intent getIntent() {
        return intent;
    }

    public void setIntent(AbdmV1Intent intent) {
        this.intent = intent;
    }

    public AbdmV1Location getLocation() {
        return location;
    }

    public void setLocation(AbdmV1Location location) {
        this.location = location;
    }

    public AbdmV1Profile getProfile() {
        return profile;
    }

    public void setProfile(AbdmV1Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "AbdmV1ProfileShareResponse{" +
            "requestId='" + requestId + '\'' +
            ", timestamp=" + timestamp +
            ", intent=" + intent +
            ", location=" + location +
            ", profile=" + profile +
            '}';
    }
}
