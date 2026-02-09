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
package in.raster.ihms.authserver.util;

import in.raster.ihms.commons.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Utility class to get branch and organization from token.
 */
@Component
public class TokenUtil {

    @Value("${jwt.header}")
    private String tokenHeader;

    public final JwtTokenUtil jwtTokenUtil;

    public TokenUtil(final JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Get branch id from token
     * @param request - httpServletRequest
     *
     * @return branchId
     * */
    public String getBranchIdFromToken(final HttpServletRequest request) {
        return jwtTokenUtil.getBranchIdFromToken(request.getHeader(tokenHeader));
    }

    /**
     * Get organizationId id from token
     * @param request - httpServletRequest
     *
     * @return organizationId
     * */
    public String getOrganizationIdFromToken(final HttpServletRequest request) {
        return jwtTokenUtil.getOrganizationIdFromToken(request.getHeader(tokenHeader));
    }
}
