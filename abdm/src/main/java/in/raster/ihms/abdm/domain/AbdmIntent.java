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

import java.math.BigDecimal;

/**
 * Custom object for abdm intent
 */
public class AbdmIntent {

    private BigDecimal context;
    private String linkToken;

    public BigDecimal getContext() {
        return context;
    }

    public void setContext(BigDecimal context) {
        this.context = context;
    }

    public String getLinkToken() {
        return linkToken;
    }

    public void setLinkToken(String linkToken) {
        this.linkToken = linkToken;
    }

    @Override
    public String toString() {
        return "AbdmIntent{" +
            "context='" + context + '\'' +
            ", linkToken='" + linkToken + '\'' +
            '}';
    }
}
