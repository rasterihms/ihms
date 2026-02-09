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
package in.raster.ihms.authserver.service.custom;

import in.raster.ihms.authserver.service.dto.ApplicationDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Custom service to manage application dtos.
 */
public interface ApplicationDtoCustomService {

    /**
     * Get application details by user, branch and application.
     *
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return application dto
     */
    ApplicationDto getApplicationDetails(final String applicationName,
                                         final HttpServletRequest request);
}
