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
package in.raster.ihms.abdm.service;

import in.raster.ihms.abdm.domain.*;
import in.raster.ihms.abdm.domain.AbdmV3OtpResponse;
import in.raster.ihms.abdm.domain.enumeration.AbdmOtpType;
import in.raster.ihms.abdm.domain.m1.AbdmV3OtpConfirmResponse;
import in.raster.ihms.abdm.domain.m1.AbdmV3AadhaarEnrollmentResponse;
import in.raster.ihms.abdm.domain.m1.AbdmV3AddressResponse;
import in.raster.ihms.abdm.domain.m1.AbdmV3AddressSuggestion;

/**
 * Custom service to create ABDM.
 */
public interface AbdmM1CreationService {

    /**
     * Request aadhaar enrollment otp.
     *
     * @param aadhaarNo - Aadhaar number.
     * @return transaction details
     */
    AbdmV3OtpResponse requestAadhaarEnrollmentOtp(final String aadhaarNo);

    /**
     * Enrol by aadhaar.
     *
     * @param otp      - mobile otp
     * @param mobileNo - mobile number
     * @param txnId    - transaction id
     * @return transaction details
     */
    AbdmV3AadhaarEnrollmentResponse enrolByAadhaar(final String otp, final String mobileNo, final String txnId);

    /**
     * Verify aadhaar enrollment otp.
     *
     * @param txnId - transaction id
     * @return abdmV3AbhaSuggestions
     */
    AbdmV3AddressSuggestion getAbhaAddressSuggestions(final String txnId);

    /**
     * Create abha address.
     *
     * @param txnId       - transaction id
     * @param abhaAddress - abha address
     * @return abdmV3CreateAbhaAddressResponse
     */
    AbdmV3AddressResponse createAbhaAddress(final String txnId, final String abhaAddress);

    /**
     * Request mobile number update otp.
     *
     * @param txnId    - transaction id
     * @param mobileNo - mobile no
     * @return abdmV3GenerateOtpResponse
     */
    AbdmV3OtpResponse requestMobileNumberUpdateOtp(final String txnId, final String mobileNo);

    /**
     * Confirm otp for mobile update.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return abdmVerifyOtpMobileUpdateResponse
     */
    AbdmV3OtpConfirmResponse confirmMobileNumberUpdateOtp(final String otp, final String txnId);

    /**
//     * Request OTP for abha card download.
//     *
//     * @param authMethod  - auth methods
//     * @param abhaAddress - abha address
//     * @return xbdmGenerateOtpResponse
//     */
//    AbdmOtpResponse requestAbhaCardDownloadOtp(final String authMethod, final String abhaAddress);

//    /**
//     * Confirm otp and download abha card.
//     *
//     * @param abdmOtpConfirmRequest          - abdmOtpConfirmRequest
//     * @param request                        - http servlet request
//     * @return byte array
//     */
//    byte[] confirmAndDownloadAbhaCard(final AbdmOtpConfirmRequest abdmOtpConfirmRequest,
//                                      final HttpServletRequest request);

    /**
     * Confirm and download abha card
     *
     * @param otp     - otp
     * @param txnId   - txnId
     * @param otpType - otpType
     * @return byte array
     */
    byte[] confirmAndDownloadAbhaCard(final String otp, final String txnId, final AbdmOtpType otpType);

    /**
     * Download abha card with xtoken.
     *
     * @param xToken - x-token
     * @return byte array
     */
    byte[] downloadAbhaCard(final String xToken);
}
