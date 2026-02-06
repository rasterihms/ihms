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
 * Custom object for create abha address response
 */
public class AbdmV3AddressResponse {

    private String txnId;
    private String healthIdNumber;
    private String preferredAbhaAddress;

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getHealthIdNumber() {
        return healthIdNumber;
    }

    public void setHealthIdNumber(String healthIdNumber) {
        this.healthIdNumber = healthIdNumber;
    }

    public String getPreferredAbhaAddress() {
        return preferredAbhaAddress;
    }

    public void setPreferredAbhaAddress(String preferredAbhaAddress) {
        this.preferredAbhaAddress = preferredAbhaAddress;
    }

    @Override
    public String toString() {
        return "AbdmV3AddressResponse{" +
            "txnId='" + txnId + '\'' +
            ", healthIdNumber='" + healthIdNumber + '\'' +
            ", preferredAbhaAddress='" + preferredAbhaAddress + '\'' +
            '}';
    }
}
