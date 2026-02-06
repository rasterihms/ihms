/*
 * ***********************************************************************************************************
 *  *  CONFIDENTIAL AND PROPRIETARY
 *  *
 *  *  The source code and other information contained herein is the confidential and the exclusive property of
 *  *  Raster Images Pvt. Ltd. and is subject to the terms and conditions in your end user license agreement.
 *  *  This source code, and any other information contained herein, shall not be copied, reproduced, published,
 *  *  displayed or distributed, in whole or in part, in any medium, by any means, for any purpose except as
 *  *  expressly permitted under such license agreement.
 *  *
 *  *  Copyright Raster Images Pvt. Ltd.
 *  *
 *  *  ALL RIGHTS RESERVED
 *  ***********************************************************************************************************
 */
package in.raster.ihms.abdm.util;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Interceptor to create http custom headers.
 */
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String headerName;
    private final String headerValue;

    public HeaderRequestInterceptor(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    /**
     * Intercept external client http requests.
     *
     * @param request   - http request
     * @param body      - request body
     * @param execution - execution
     * @return client http response
     * @throws IOException - IO exception
     */
    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
                                        final ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(headerName, headerValue);
        return execution.execute(request, body);
    }
}
