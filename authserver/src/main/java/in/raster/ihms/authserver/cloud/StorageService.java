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
package in.raster.ihms.authserver.cloud;

import in.raster.ihms.authserver.config.custom.FormEncoderConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * Feign to call storage service.
 */
@FeignClient(name = "storage-service", configuration = FormEncoderConfiguration.class)
public interface StorageService {

    /**
     * Upload file to minio.
     *
     * @param file       - multipart file
     * @param bucketName - bucket name
     * @param fileName   - file name
     * @param token      - token
     * @return file path
     */
    @GetMapping(value = "/api/upload", consumes = "multipart/form-data", produces = "application/json")
    String upload(@RequestPart(value = "file") final MultipartFile file,
                  @RequestParam(value = "bucketName") final String bucketName,
                  @RequestParam(value = "fileName") final String fileName,
                  @RequestParam(value = "contentType") final String contentType,
                  @RequestHeader("token") final String token);
}
