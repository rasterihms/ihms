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

package in.raster.ihms.authserver.config.custom;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * Config to encode multiform data.
 */
@Configuration
public class FormEncoderConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    /**
     * Bean to encode multipart form.
     *
     * @return Encoder
     */
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder encoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
}
