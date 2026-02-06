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
package in.raster.ihms.abdm.web.rest;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.abdm.domain.*;
import in.raster.ihms.abdm.domain.AbdmV3OtpResponse;
import in.raster.ihms.abdm.domain.AbdmV3Profile;
import in.raster.ihms.abdm.domain.enumeration.AbdmOtpType;
import in.raster.ihms.abdm.domain.m1.AbdmV3OtpConfirmResponse;
import in.raster.ihms.abdm.service.AbdmM1VerificationService;
import in.raster.ihms.abdm.domain.AbdmV3PhrProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Custom resource to veirfy ABDM.
 */
@RestController
@RequestMapping("/api")
public class AbdmM1VerificationResource {

    final Logger log = LoggerFactory.getLogger(AbdmM1VerificationResource.class);
    private final AbdmM1VerificationService abdmM1VerificationService;

    public AbdmM1VerificationResource(AbdmM1VerificationService abdmM1VerificationService) {
        this.abdmM1VerificationService = abdmM1VerificationService;
    }

    /**
     * Request aadhaar verification otp.
     *
     * @param aadhaarNo - aadhaar number
     * @return AbdmV3OtpResponse
     */
    @GetMapping("/request-aadhaar-verification-otp")
    @Timed
    public AbdmV3OtpResponse requestAadhaarVerificationOtp(@RequestParam(value = "aadhaarNo") final String aadhaarNo) {
        return abdmM1VerificationService.requestAadhaarVerificationOtp(aadhaarNo);
    }

    /**
     * Confirm aadhaar verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3Profile
     */
    @GetMapping("/confirm-aadhaar-verification-otp")
    @Timed
    public AbdmV3Profile confirmAadhaarVerificationOtp(@RequestParam(value = "otp") final String otp,
                                                       @RequestParam(value = "txnId") final String txnId) {
        return abdmM1VerificationService.confirmAadhaarVerificationOtp(otp, txnId);
    }

    /**
     * Request abha number verification otp.
     *
     * @param abhaNumber - abha number
     * @return AbdmV3OtpResponse
     */
    @GetMapping("/request-abha-number-verification-otp")
    @Timed
    public AbdmV3OtpResponse requestAbhaNumberVerificationOtp(@RequestParam(value = "abhaNumber") final String abhaNumber,
                                                              @RequestParam(value = "otpType") final AbdmOtpType otpType) {
        return abdmM1VerificationService.requestAbhaNumberVerificationOtp(abhaNumber, otpType);
    }

    /**
     * Confirm abha number verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3Profile
     */
    @GetMapping("/confirm-abha-number-verification-otp")
    @Timed
    public AbdmV3Profile confirmAbhaNumberVerificationOtp(@RequestParam(value = "otp") final String otp,
                                                          @RequestParam(value = "txnId") final String txnId,
                                                          @RequestParam(value = "otpType") final AbdmOtpType otpType) {
        return abdmM1VerificationService.confirmAbhaNumberVerificationOtp(otp, txnId, otpType);
    }

    /**
     * Request abha address verification otp.
     *
     * @param abhaAddress - abha address
     * @return AbdmV3OtpResponse
     */
    @GetMapping("/request-abha-address-verification-otp")
    @Timed
    public AbdmV3OtpResponse requestAbhaAddressVerificationOtp(@RequestParam(value = "abhaAddress") final String abhaAddress,
                                                               @RequestParam(value = "otpType") final AbdmOtpType otpType) {
        return abdmM1VerificationService.requestAbhaAddressVerificationOtp(abhaAddress, otpType);
    }

    /**
     * Confirm abha address verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3PhrProfile
     */
    @GetMapping("/confirm-abha-address-verification-otp")
    @Timed
    public AbdmV3PhrProfile confirmAbhaAddressVerificationOtp(@RequestParam(value = "otp") final String otp,
                                                              @RequestParam(value = "txnId") final String txnId,
                                                              @RequestParam(value = "otpType") final AbdmOtpType otpType) {
        return abdmM1VerificationService.confirmAbhaAddressVerificationOtp(otp, txnId, otpType);
    }

    /**
     * Request mobile verification otp.
     *
     * @param mobileNumber - mobile number
     * @return AbdmV3OtpResponse
     */
    @GetMapping("/request-mobile-number-verification-otp")
    @Timed
    public AbdmV3OtpResponse requestMobileVerificationOtp(@RequestParam(value = "mobileNumber") final String mobileNumber) {
        return abdmM1VerificationService.requestMobileVerificationOtp(mobileNumber);
    }

    /**
     * Confirm mobile verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3OtpConfirmResponse
     */
    @GetMapping("/confirm-mobile-number-verification-otp")
    @Timed
    public AbdmV3OtpConfirmResponse confirmMobileVerificationOtp(@RequestParam(value = "otp") final String otp,
                                                                 @RequestParam(value = "txnId") final String txnId) {
        return abdmM1VerificationService.confirmMobileVerificationOtp(otp, txnId);
    }

    /**
     * Confirm patient on mobile verification
     *
     * @param abhaNumber - abha number
     * @param txnId      - txnId
     * @param tToken     - tToken
     * @return AbdmV3Profile
     */
    @GetMapping("/confirm-patient-on-mobile-verification")
    @Timed
    public AbdmV3Profile confirmPatientOnMobileVerification(@RequestParam(value = "abhaNumber") final String abhaNumber,
                                                            @RequestParam(value = "txnId") final String txnId,
                                                            @RequestParam(value = "tToken") final String tToken) {
        return abdmM1VerificationService.confirmPatientOnMobileVerification(abhaNumber, txnId, tToken);
    }
}
