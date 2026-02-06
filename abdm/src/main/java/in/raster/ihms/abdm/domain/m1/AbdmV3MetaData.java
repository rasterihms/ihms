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
 * Custom object for ABDM V3 Meta data.
 */
public class AbdmV3MetaData {
    private String hipId;
    private String context;
    private String hprId;
    private String latitude;
    private String longitude;

    public String getHipId() {
        return hipId;
    }

    public void setHipId(String hipId) {
        this.hipId = hipId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getHprId() {
        return hprId;
    }

    public void setHprId(String hprId) {
        this.hprId = hprId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "AbdmV3MetaData{" +
                "hipId='" + hipId + '\'' +
                ", context='" + context + '\'' +
                ", hprId='" + hprId + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
