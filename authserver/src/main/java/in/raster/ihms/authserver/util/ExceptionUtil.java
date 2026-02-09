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

import in.raster.ihms.exceptions.custom.CustomParameterizedException;
import in.raster.ihms.exceptions.custom.InvalidCredentialsException;
import in.raster.ihms.exceptions.custom.ObjectAlreadyExistsException;
import in.raster.ihms.exceptions.custom.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * To throw exceptions
 */
public class ExceptionUtil {
    /**
     * To check Object/list of Object is empty or not
     *
     * @param object           object
     * @param exceptionMessage exceptionMessage
     */
    public static void isObjectEmpty(Object object, final String exceptionMessage) {
        if (object instanceof ArrayList) {
            if (CollectionUtils.isEmpty((List) object)) {
                throwObjectNotFoundException(exceptionMessage);
            }
        } else if (ObjectUtils.isEmpty(object)) {
            throwObjectNotFoundException(exceptionMessage);
        }

    }

    /**
     * To throw Object not found exception
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwObjectNotFoundException(final String exceptionMessage) {
        throw new ObjectNotFoundException(exceptionMessage);
    }

    /**
     * To throw user name not found exception
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwUsernameNotFoundException(final String exceptionMessage) {
        throw new UsernameNotFoundException(exceptionMessage);
    }

    /**
     * To throw object already exists exception
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwObjectAlreadyExistsException(final String exceptionMessage) {
        throw new ObjectAlreadyExistsException(exceptionMessage);
    }

    /**
     * To throw invalid credentials exception
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwInvalidCredentialsException(final String exceptionMessage) {
        throw new InvalidCredentialsException(exceptionMessage);
    }

    /**
     * To throw exception with custom business message.
     *
     * @param exceptionMessage message of exception
     */
    public static void throwCustomParameterizedException(final String exceptionMessage) {
        throw new CustomParameterizedException(exceptionMessage);
    }
}
