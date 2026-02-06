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
import in.raster.ihms.abdm.domain.AbdmV3OtpResponse;
import in.raster.ihms.abdm.domain.enumeration.AbdmOtpType;
import in.raster.ihms.abdm.domain.m1.AbdmV3AadhaarEnrollmentResponse;
import in.raster.ihms.abdm.domain.m1.AbdmV3AddressResponse;
import in.raster.ihms.abdm.domain.m1.AbdmV3AddressSuggestion;
import in.raster.ihms.abdm.domain.m1.AbdmV3OtpConfirmResponse;
import in.raster.ihms.abdm.service.AbdmM1CreationService;
import in.raster.ihms.abdm.util.AbdmUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Custom resource to create ABDM.
 */
@RestController
@RequestMapping("/api")
public class AbdmM1CreationResource {

    final Logger log = LoggerFactory.getLogger(AbdmM1CreationResource.class);
    private final AbdmM1CreationService abdmM1CreationService;
    private final AbdmUtil abdmUtil;

    public AbdmM1CreationResource(AbdmM1CreationService abdmM1CreationService, AbdmUtil abdmUtil) {
        this.abdmM1CreationService = abdmM1CreationService;
        this.abdmUtil = abdmUtil;
    }

    /**
     * Request aadhaar enrollment otp.
     *
     * @param aadhaarNo - Aadhaar number.
     * @return transaction details
     */
    @GetMapping("/request-aadhaar-enrollment-otp")
    @Timed
    public AbdmV3OtpResponse requestAadhaarEnrollmentOtp(@RequestParam(value = "aadhaarNo") final String aadhaarNo) {
        return abdmM1CreationService.requestAadhaarEnrollmentOtp(aadhaarNo);
    }

    /**
     * Enrol by aadhaar.
     *
     * @param otp      - mobile otp
     * @param mobileNo - mobile number
     * @param txnId    - transaction id
     * @return transaction details
     */
    @GetMapping("/enrol-by-aadhaar")
    @Timed
    public AbdmV3AadhaarEnrollmentResponse enrolByAadhaar(@RequestParam(value = "otp") final String otp,
                                                          @RequestParam(value = "mobileNo") final String mobileNo,
                                                          @RequestParam(value = "txnId") final String txnId) {
        return abdmM1CreationService.enrolByAadhaar(otp, mobileNo, txnId);
    }

    /**
     * Get abha address suggestions.
     *
     * @param txnId - transaction id
     * @return abdmV3AbhaSuggestions
     */
    @GetMapping("/abha-address-suggestions")
    @Timed
    public AbdmV3AddressSuggestion getAbhaAddressSuggestions(@RequestParam(value = "txnId") final String txnId) {
        return abdmM1CreationService.getAbhaAddressSuggestions(txnId);
    }

    /**
     * Create abha address.
     *
     * @param txnId       - transaction id
     * @param abhaAddress - abha address
     * @return abdmV3CreateAbhaAddressResponse
     */
    @GetMapping("/abha-address-creation")
    @Timed
    public AbdmV3AddressResponse createAbhaAddress(@RequestParam(value = "txnId") final String txnId,
                                                   @RequestParam(value = "abhaAddress") final String abhaAddress) {
        return abdmM1CreationService.createAbhaAddress(txnId, abhaAddress);
    }

    /**
     * Request mobile number update otp.
     *
     * @param txnId    - transaction id
     * @param mobileNo - mobile no
     * @return abdmV3GenerateOtpResponse
     */
    @GetMapping("/request-mobile-number-update-otp")
    @Timed
    public AbdmV3OtpResponse requestMobileNumberUpdateOtp(@RequestParam(value = "txnId") final String txnId,
                                                          @RequestParam(value = "mobileNo") final String mobileNo) {
        return abdmM1CreationService.requestMobileNumberUpdateOtp(txnId, mobileNo);
    }

    /**
     * Confirm otp for mobile update.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return abdmVerifyOtpMobileUpdateResponse
     */
    @GetMapping("/confirm-mobile-number-update-otp")
    @Timed
    public AbdmV3OtpConfirmResponse confirmMobileNumberUpdateOtp(@RequestParam(value = "otp") final String otp,
                                                                 @RequestParam(value = "txnId") final String txnId) {
        return abdmM1CreationService.confirmMobileNumberUpdateOtp(otp, txnId);
    }

    /**
     * Todo - Need to convert to V3 API.
     * <p>
//     * Request OTP for abha card download.
//     *
//     * @param authMethod  - auth methods
//     * @param abhaAddress - abha address
//     * @return abdm otp response
//     */
//    @GetMapping("/abdm-request-abha-card-download-otp")
//    @Timed
//    public AbdmOtpResponse requestAbhaCardDownloadOtp(@RequestParam(value = "authMethod") final String authMethod,
//                                                      @RequestParam(value = "abhaAddress") final String abhaAddress) {
//        return abdmM1CreationService.requestAbhaCardDownloadOtp(authMethod, abhaAddress);
//    }

//    /**
//     * Todo - Need to convert to V3 API.
//     *
//     * Confirm otp and download abha card.
//     *
//     * @param abdmOtpConfirmRequest          - abdmOtpConfirmRequest
//     * @param request                        - http servlet request
//     * @return byte array
//     */
//    @PostMapping("/abdm-confirm-download-abha-card")
//    @Timed
//    public byte[] confirmAndDownloadAbhaCard(@Valid @RequestBody final AbdmOtpConfirmRequest abdmOtpConfirmRequest,
//                                             final HttpServletRequest request) {
//        return abdmM1CreationService.confirmAndDownloadAbhaCard(abdmOtpConfirmRequest, request);
//    }


    /**
     * Confirm and download abha card
     *
     * @param otp     - otp
     * @param txnId   - txnId
     * @param otpType - otpType
     * @return byte array
     */
    @GetMapping("/confirm-download-abha-card")
    @Timed
    public byte[] confirmAndDownloadAbhaCard(@RequestParam(value = "otp") final String otp,
                                             @RequestParam(value = "txnId") final String txnId,
                                             @RequestParam(value = "otpType") final AbdmOtpType otpType) {
        return abdmM1CreationService.confirmAndDownloadAbhaCard(otp, txnId, otpType);
    }

    /**
     * Download abha card with xtoken.
     *
     * @param xToken - x-token
     * @return byte array
     */
    @GetMapping("/download-abha-card")
    @Timed
    public byte[] downloadAbhaCard(@RequestParam(value = "xToken") final String xToken) {
        return abdmM1CreationService.downloadAbhaCard(xToken);
    }

    /**
     * Validate aadhaar number with verhoeff algorithm.
     *
     * @param aadhaarNumber - aadhaar number
     */
    @GetMapping("/validate-aadhaar")
    @Timed
    public void validateAadhaarNumber(@RequestParam(value = "aadhaarNumber") final String aadhaarNumber) {
        abdmUtil.validateAadhaarNumber(aadhaarNumber);
    }

    /**
     * Get access token
     *
     */
    @GetMapping("/access-token")
    @Timed
    public String getAccessToken() {
        return abdmUtil.getAccessToken();
    }
}
