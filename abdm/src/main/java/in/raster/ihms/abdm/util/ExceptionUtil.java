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
package in.raster.ihms.abdm.util;

import in.raster.ihms.exceptions.custom.*;
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
     * To throw object has reference exception.
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwObjectHasReferenceException(final String exceptionMessage) {
        throw new ObjectHasReferenceException(exceptionMessage);
    }

    /**
     * To throw object already exist exception.
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwObjectAlreadyExistsException(final String exceptionMessage) {
        throw new ObjectAlreadyExistsException(exceptionMessage);
    }

    /**
     * To throw exception with custom business message.
     *
     * @param exceptionMessage message of exception
     */
    public static void throwCustomParameterizedException(final String exceptionMessage) {
        throw new CustomParameterizedException(exceptionMessage);
    }

    /**
     * To throw exception about parsing file.
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwIOException(final String exceptionMessage) {
        throw new IOException(exceptionMessage);
    }

    /**
     * To throw exception about issue while printing
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwPrintException(final String exceptionMessage) {
        throw new PrintException(exceptionMessage);
    }

    /**
     * To throw exception about issue in html parsing template.
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwTemplateException(final String exceptionMessage) {
        throw new TemplateException(exceptionMessage);
    }

    /**
     * To throw exception about file you are trying to get is not found
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwFileNotFoundException(final String exceptionMessage) {
        throw new FileNotFoundException(exceptionMessage);
    }

    /**
     * To throw exception about document/pdf related issues
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwDocumentException(final String exceptionMessage) {
        throw new DocumentException(exceptionMessage);
    }

    /**
     * To throw exception about bad credential related issues
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwBadCredentialsException(final String exceptionMessage) {
        throw new BadCredentialsException(exceptionMessage);
    }

    /**
     * To throw exception about mail authetication related issues
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwMailAuthenticationException(final String exceptionMessage) {
        throw new MailAuthenticationException(exceptionMessage);
    }

    /**
     * To throw insufficient funds exception
     *
     * @param exceptionMessage exceptionMessage
     */
    public static void throwInsufficientFundsException(final String exceptionMessage) {
        throw new InsufficientFundsException(exceptionMessage);
    }
}

