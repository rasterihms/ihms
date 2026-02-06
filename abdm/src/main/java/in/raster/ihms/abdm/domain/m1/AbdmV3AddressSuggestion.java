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

import java.util.List;

/**
 * Custom object for abha address suggestions
 */
public class AbdmV3AddressSuggestion {

    private String txnId;
    private List<String> abhaAddressList;

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public List<String> getAbhaAddressList() {
        return abhaAddressList;
    }

    public void setAbhaAddressList(List<String> abhaAddressList) {
        this.abhaAddressList = abhaAddressList;
    }

    @Override
    public String toString() {
        return "AbdmV3AddressSuggestion{" +
            "txnId='" + txnId + '\'' +
            ", abhaAddressList=" + abhaAddressList +
            '}';
    }
}
