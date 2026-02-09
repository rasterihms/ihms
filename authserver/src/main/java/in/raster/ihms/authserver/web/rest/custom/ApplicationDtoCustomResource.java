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
package in.raster.ihms.authserver.web.rest.custom;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.service.custom.ApplicationDtoCustomService;
import in.raster.ihms.authserver.service.dto.ApplicationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Custom resource to manage application dtos.
 */
@RestController
@RequestMapping("/api")
public class ApplicationDtoCustomResource {

    private final ApplicationDtoCustomService applicationDtoCustomService;

    public ApplicationDtoCustomResource(ApplicationDtoCustomService applicationDtoCustomService) {
        this.applicationDtoCustomService = applicationDtoCustomService;
    }

    /**
     * Get application details by user, branch and application.
     *
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return application dto
     */
    @GetMapping("/application-dtos")
    @Timed
    public ApplicationDto getApplicationDetails(@RequestParam(value = "application") final String applicationName,
                                                final HttpServletRequest request) {
        return applicationDtoCustomService.getApplicationDetails(applicationName, request);
    }
}
