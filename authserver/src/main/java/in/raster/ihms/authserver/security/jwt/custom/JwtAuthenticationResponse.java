/*******************************************************************************
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
 *******************************************************************************/
package in.raster.ihms.authserver.security.jwt.custom;

/**
 * Custom class to hold token and user id and display name for user authentication.
 */
public class JwtAuthenticationResponse {

    private static final long serialVersionUID = 1250166508152483573L;
    private final String token;
    private final String userId;
    private final String displayName;

    public JwtAuthenticationResponse(String token, String userId, String displayName) {
        this.token = token;
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getToken() {
        return this.token;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
