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
package in.raster.ihms.authserver.util;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import in.raster.ihms.authserver.cloud.StorageService;
import in.raster.ihms.exceptions.custom.ServiceUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StorageUtil {

    private static final String STORAGE_SERVICE_DOWN = "Storage service is down";

    private final StorageService storageService;
    private final Logger log = LoggerFactory.getLogger(StorageUtil.class);

    public StorageUtil(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Upload file to storage service.
     *
     * @param file        - upload file
     * @param bucketName  - bucket name
     * @param fileName    - file name
     * @param contentType - file content type
     * @param token       - token
     * @return uploaded file path
     */
    public String uploadFile(final MultipartFile file, final String bucketName, final String fileName,
                             final String contentType, final String token) {
        String filePath = null;
        try {
            filePath = storageService.upload(file, bucketName, fileName, contentType, token);
        } catch (HystrixRuntimeException exception) {
            throw new ServiceUnavailableException(STORAGE_SERVICE_DOWN);
        }
        return filePath;
    }
}
