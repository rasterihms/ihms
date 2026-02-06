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
 * Custom object for ABDM v1 profile
 */
public class AbdmV1Profile {

    private String hipCode;
    private AbdmV1ProfileSharePatient patient;

    public String getHipCode() {
        return hipCode;
    }

    public void setHipCode(String hipCode) {
        this.hipCode = hipCode;
    }

    public AbdmV1ProfileSharePatient getPatient() {
        return patient;
    }

    public void setPatient(AbdmV1ProfileSharePatient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "AbdmV1Profile{" +
            "hipCode='" + hipCode + '\'' +
            ", patient=" + patient +
            '}';
    }
}
