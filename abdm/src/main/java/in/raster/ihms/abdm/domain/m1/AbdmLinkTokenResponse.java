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

import in.raster.ihms.abdm.domain.AbdmResponse;

/**
 * Custom object for link token response
 */
public class AbdmLinkTokenResponse {

    private String abhaAddress;
    private String linkToken;
    private AbdmResponse response;

    public String getAbhaAddress() {
        return abhaAddress;
    }

    public void setAbhaAddress(String abhaAddress) {
        this.abhaAddress = abhaAddress;
    }

    public String getLinkToken() {
        return linkToken;
    }

    public void setLinkToken(String linkToken) {
        this.linkToken = linkToken;
    }

    public AbdmResponse getResponse() {
        return response;
    }

    public void setResponse(AbdmResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "AbdmLinkTokenResponse{" +
            "abhaAddress='" + abhaAddress + '\'' +
            ", linkToken='" + linkToken + '\'' +
            ", response=" + response +
            '}';
    }
}
