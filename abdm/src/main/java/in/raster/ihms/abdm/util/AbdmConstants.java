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

/**
 * Custom constants.
 */
public class AbdmConstants {

    //keywords
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String MOBILE_OTP = "MOBILE_OTP";
    public static final String AADHAAR_OTP = "AADHAAR_OTP";
    public static final String AADHAAR = "aadhaar";
    public static final String MOBILE = "mobile";
    public static final String ABDM = "abdm";
    public static final String ABHA_ENROL = "abha-enrol";
    public static final String ABHA_NUMBER = "abha-number";
    public static final String ABHA_ADDRESS = "abha-address";
    public static final String MOBILE_VERIFY = "mobile-verify";
    public static final String ABHA_ENROLLMENT = "abha-enrollment";
    public static final String OTP = "otp";
    public static final String CONSENT_VERSION = "1.4";
    public static final String PREFERRED = "1";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REQUEST_ID = "REQUEST-ID";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String TRANSACTION_ID = "Transaction_id";
    public static final String X_TOKEN = "X-Token";
    public static final String T_TOKEN = "T-token";
    public static final String BEARER = "Bearer ";
    public static final String SKS_HIP_ID = "IN3310001996";
    public static final String X_CM_ID = "X-CM-ID";
    public static final String X_HIP_ID = "X-HIP-ID";
    public static final String ABHA_LOGIN = "abha-login";
    public static final String AADHAAR_VERIFY = "aadhaar-verify";
    public static final String ABHA_ADDRESS_LOGIN = "abha-address-login";
    public static final String FAILED = "failed";

    //encryption
    public static final String RSA_ECB_OAEPWithSHA_1AndMGF1Padding = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
    public static final String RSA_ECB_PKCS1Padding = "RSA/ECB/PKCS1Padding";

    //exception
    public static final String INVALID_ABHA_ADDRESS_ENTER_A_VALID_ABHA_ADDRESS = "Invalid ABHA address entered. Please enter a valid ABHA address";
    public static final String PLEASE_TRY_AGAIN_SOMETIME = "Please try again";
    public static final String INVALID_AADHAAR_NUMBER_ENTERED_PLEASE_ENTER_A_VALID_AADHAAR_NUMBER = "Invalid Aadhaar number entered. Please enter a valid Aadhaar number";
    public static final String ABHA_ADDRESS_SEARCH_RESPONSE_EMPTY = "Abha address search response is empty";
    public static final String INVALID_AADHAAR_NUMBER = "Invalid aadhaar number";
    public static final String ABHA_ADDRESS_ALREADY_EXISTS = "Abha address already exists, Please enter new address";
    public static final String MOBILE_NUMBER_NOT_LINKED_WITH_AADHAAR = "Mobile number not linked with given Aadhaar Number";
    public static final String NO_ABHA_USER_REGISTERED = "No ABHA user registered";
    public static final String ERROR_IN_GETTING_COUNTRY_DIVISION = "Exception in getting country division from master ";
    public static final String INVALID_MOBILE_NUMBER = "Invalid mobile number";
    public static final String INVALID_OTP_VALUE = "Invalid OTP value";
    public static final String OTP_EXPIRED = "OTP expired, Click on Resend OTP";
    public static final String INVALID_ABHA_ADDRESS = "Invalid abha address";
    public static final String TOO_MANY_OTP_REQUEST = "Too many otp request ";
    public static final String TRANSACTION_TIMEOUT = "Transaction timeout, Please refresh";
}
