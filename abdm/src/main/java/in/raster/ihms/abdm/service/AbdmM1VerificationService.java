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
import in.raster.ihms.abdm.domain.AbdmV3Profile;
import in.raster.ihms.abdm.domain.enumeration.AbdmOtpType;
import in.raster.ihms.abdm.domain.m1.AbdmV3OtpConfirmResponse;
import in.raster.ihms.abdm.domain.AbdmV3OtpResponse;
import in.raster.ihms.abdm.domain.AbdmV3PhrProfile;

/**
 * Custom service to verify abdm
 */
public interface AbdmM1VerificationService {

    /**
     * Request aadhaar verification otp.
     *
     * @param aadhaarNo - aadhaar number
     * @return AbdmV3OtpResponse
     */
    AbdmV3OtpResponse requestAadhaarVerificationOtp(final String aadhaarNo);

    /**
     * Verify aadhaar verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3Profile
     */
    AbdmV3Profile confirmAadhaarVerificationOtp(final String otp, final String txnId);

    /**
     * Request abha number verification otp.
     *
     * @param abhaNumber - abha number
     * @return AbdmV3OtpResponse
     */
    AbdmV3OtpResponse requestAbhaNumberVerificationOtp(final String abhaNumber, final AbdmOtpType otpType);

    /**
     * Confirm abha number verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3Profile
     */
    AbdmV3Profile confirmAbhaNumberVerificationOtp(final String otp, final String txnId, final AbdmOtpType otpType);

    /**
     * Request abha address verification otp.
     *
     * @param abhaAddress - abha address
     * @return AbdmV3OtpResponse
     */
    AbdmV3OtpResponse requestAbhaAddressVerificationOtp(final String abhaAddress, final AbdmOtpType otpType);

    /**
     * Confirm abha address verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3PhrProfile
     */
    AbdmV3PhrProfile confirmAbhaAddressVerificationOtp(final String otp, final String txnId, final AbdmOtpType otpType);

    /**
     * Request mobile verification otp.
     *
     * @param mobileNumber - mobile number
     * @return AbdmV3OtpResponse
     */
    AbdmV3OtpResponse requestMobileVerificationOtp(final String mobileNumber);

    /**
     * Confirm mobile verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3OtpConfirmResponse
     */
    AbdmV3OtpConfirmResponse confirmMobileVerificationOtp(final String otp, final String txnId);

    /**
     * Confirm patient on mobile verification
     *
     * @param abhaNumber - abha number
     * @param txnId      - txnId
     * @param tToken     - tToken
     * @return abdmV3Profile
     */
    AbdmV3Profile confirmPatientOnMobileVerification(final String abhaNumber, final String txnId, final String tToken);
}
