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

/**
 * Custom object for ABDM V3 profile share response.
 */
public class AbdmV3FacilityQRResponse {

    private String intent;
    private AbdmV3MetaData metaData;
    private AbdmV3ScanProfile profile;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public AbdmV3MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(AbdmV3MetaData metaData) {
        this.metaData = metaData;
    }

    public AbdmV3ScanProfile getProfile() {
        return profile;
    }

    public void setProfile(AbdmV3ScanProfile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "AbdmV3FacilityQRResponse{" +
                "intent='" + intent + '\'' +
                ", metaData=" + metaData +
                ", profile=" + profile +
                '}';
    }
}
