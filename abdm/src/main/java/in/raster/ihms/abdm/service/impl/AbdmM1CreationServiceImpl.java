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
package in.raster.ihms.abdm.service.impl;

import com.google.gson.Gson;
import in.raster.ihms.abdm.domain.*;
import in.raster.ihms.abdm.domain.enumeration.AbdmM1Process;
import in.raster.ihms.abdm.domain.enumeration.AbdmM1VerificationType;
import in.raster.ihms.abdm.domain.enumeration.AbdmOtpType;
import in.raster.ihms.abdm.domain.m1.*;
import in.raster.ihms.abdm.service.AbdmM1CreationService;
import in.raster.ihms.abdm.util.AbdmConstants;
import in.raster.ihms.abdm.util.AbdmUtil;
import in.raster.ihms.abdm.util.ExceptionUtil;
import in.raster.ihms.abdm.domain.*;
import in.raster.ihms.abdm.domain.m1.*;
import in.raster.ihms.commons.util.CommonConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom service implementation to verify abdm.
 */
@Service
@Transactional
public class AbdmM1CreationServiceImpl implements AbdmM1CreationService {

    private final Logger log = LoggerFactory.getLogger(AbdmM1CreationServiceImpl.class);

    private final AbdmUtil abdmUtil;

    public AbdmM1CreationServiceImpl(final AbdmUtil abdmUtil) {
        this.abdmUtil = abdmUtil;
    }

    /**
     * Request aadhaar enrollment otp.
     *
     * @param aadhaarNo - Aadhaar number.
     * @return abdmGenerateOtpResponse
     */
    @Override
    public AbdmV3OtpResponse requestAadhaarEnrollmentOtp(final String aadhaarNo) {
        abdmUtil.validateAadhaarNumber(aadhaarNo);
        final String encryptedValue = abdmUtil.encryptValue(aadhaarNo);
        final AbdmV3OtpRequest abdmV3OtpRequest = constructAadhaarEnrollmentOtpRequest(encryptedValue);
        final String generateOtpUrl = abdmUtil.m1RequestAadhaarEnrollmentOtpUrl;
        return abdmUtil.requestOtp(abdmV3OtpRequest, generateOtpUrl, AbdmConstants.AADHAAR, AbdmM1Process.AADHAAR_ENROLLMENT);
    }

    /**
     * Enrol by aadhaar.
     *
     * @param otp-     - otp
     * @param mobileNo - mobile no
     * @param txnId    - transaction id
     * @return abdmV3VerifyByAadhaarResponse
     */
    @Override
    public AbdmV3AadhaarEnrollmentResponse enrolByAadhaar(final String otp, final String mobileNo, final String txnId) {
        log.info("Method Verify OTP By Aadhaar Implementation Starts");
        AbdmV3AadhaarEnrollmentResponse abdmV3AadhaarEnrollmentResponse = null;
        final String accessToken = abdmUtil.getAccessToken();
        final String encryptedOtpValue = abdmUtil.encryptValue(otp);
        final AbdmV3AadhaarEnrollmentRequest abdmV3AadhaarEnrollmentRequest = constructAbdmV3AadhaarEnrollmentRequest(txnId,
                mobileNo, encryptedOtpValue);
        try {
            final RestTemplate restTemplate =
                    abdmUtil.constructHttpsRestTemplateForAbdm(accessToken,
                            abdmUtil.generateRandomUuid(), null, null, true);
            abdmUtil.setMediaType(restTemplate);
            String jsonString = restTemplate.postForObject(abdmUtil.m1AadhaarEnrollmentUrl,
                    abdmV3AadhaarEnrollmentRequest, String.class);
            final Gson gson = new Gson();
            try {
                final JSONObject jsonObject = new JSONObject(jsonString);
                abdmV3AadhaarEnrollmentResponse = gson.fromJson(jsonObject.toString(), AbdmV3AadhaarEnrollmentResponse.class);
            } catch (JSONException e) {
                log.info(e.getMessage());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            if (e.getMessage().contains("1202") || e.getMessage().contains("403")
                    || e.getMessage().contains("500")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
            } else if (e.getMessage().contains("400") || e.getMessage().contains("422")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.INVALID_OTP_VALUE);
            } else if (e.getMessage().contains("401")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.OTP_EXPIRED);
            } else {
                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
            }
        }
        log.info("Method Verify OTP By Aadhaar Implementation Ends");
        return abdmV3AadhaarEnrollmentResponse;
    }

    /**
     * Get abha address suggestions
     *
     * @param txnId - transaction id
     * @return abdmV3AbhaSuggestions
     */
    @Override
    public AbdmV3AddressSuggestion getAbhaAddressSuggestions(final String txnId) {
        log.info("Method Get ABHA Address Suggestions Implementation Starts");
        AbdmV3AddressSuggestion abdmV3AbhaSuggestions = null;
        final String accessToken = abdmUtil.getAccessToken();
        try {
            final RestTemplate restTemplate =
                    abdmUtil.constructHttpsRestTemplateForAbdm(accessToken,
                            abdmUtil.generateRandomUuid(), txnId, null, true);
            abdmUtil.setMediaType(restTemplate);
            abdmV3AbhaSuggestions = restTemplate.getForObject(abdmUtil.m1AddressSuggessionUrl, AbdmV3AddressSuggestion.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            if (e.getMessage().contains("1202") || e.getMessage().contains("403") ||
                    e.getMessage().contains("422") || e.getMessage().contains("500")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
            } else {
                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
            }
        }
        log.info("Method Get ABHA Address Suggestions Implementation Ends");
        return abdmV3AbhaSuggestions;
    }

    /**
     * Create abha address
     *
     * @param txnId       - transaction id
     * @param abhaAddress - abha address
     * @return abdmV3CreateAbhaAddressResponse
     */
    @Override
    public AbdmV3AddressResponse createAbhaAddress(final String txnId, final String abhaAddress) {
        log.info("Method Create ABHA Address Implementation Starts");
        AbdmV3AddressResponse abdmV3GenerateOtpResponse = null;
        final String accessToken = abdmUtil.getAccessToken();
        final AbdmV3AddressRequest abdmV3AddressRequest =
                constructAbdmV3AbhaAddressRequest(txnId, abhaAddress);
        try {
            final RestTemplate restTemplate =
                    abdmUtil.constructHttpsRestTemplateForAbdm(accessToken,
                            abdmUtil.generateRandomUuid(), null, null, true);
            abdmV3GenerateOtpResponse = restTemplate.postForObject(abdmUtil.m1CreateAbhaAddressUrl, abdmV3AddressRequest,
                    AbdmV3AddressResponse.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            if (e.getMessage().contains("1202") || e.getMessage().contains("403") ||
                    e.getMessage().contains("422") || e.getMessage().contains("500")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
            } else if (e.getMessage().contains("400")) {
                ExceptionUtil.throwCustomParameterizedException(
                        AbdmConstants.INVALID_ABHA_ADDRESS_ENTER_A_VALID_ABHA_ADDRESS);
            } else if (e.getMessage().contains("409")) {
                ExceptionUtil.throwCustomParameterizedException(
                        AbdmConstants.ABHA_ADDRESS_ALREADY_EXISTS);
            } else if (e.getMessage().contains("401")) {
                ExceptionUtil.throwCustomParameterizedException(
                        AbdmConstants.TRANSACTION_TIMEOUT);
            } else {
                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
            }
        }
        log.info("Method Create ABHA Address Implementation Ends");
        return abdmV3GenerateOtpResponse;
    }

    /**
     * Request otp for update mobile number.
     *
     * @param txnId    - transaction id
     * @param mobileNo - mobile no
     * @return AbdmV3OtpResponse
     */
    @Override
    public AbdmV3OtpResponse requestMobileNumberUpdateOtp(final String txnId, final String mobileNo) {
        final String encryptedValue = abdmUtil.encryptValue(mobileNo);
        final AbdmV3OtpRequest abdmV3OtpRequest = constructMobileUpdateOtpRequest(encryptedValue, txnId);
        final String requestOtpUrl = abdmUtil.m1RequestAadhaarEnrollmentOtpUrl;
        return abdmUtil.requestOtp(abdmV3OtpRequest, requestOtpUrl, AbdmConstants.MOBILE, AbdmM1Process.MOBILE_NUMBER_UPDATE);
    }

    /**
     * Confirm otp for mobile update.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return abdmVerifyOtpMobileUpdateResponse
     */
    @Override
    public AbdmV3OtpConfirmResponse confirmMobileNumberUpdateOtp(final String otp, final String txnId) {
        log.info("Method Verify OTP For Mobile Number Update Implementation Starts");
        final String encryptedOtpValue = abdmUtil.encryptValue(otp);
        final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest = constructMobileUpdateOtpConfirmRequest(txnId, encryptedOtpValue);
        final String mobileNumberUpdateUrl = abdmUtil.m1UpdateMobileNumberUrl;
        return abdmUtil.confirmOtp(abdmV3OtpConfirmRequest, mobileNumberUpdateUrl);
    }

    /**
     * Request OTP for abha card download.
     *
     * @param authMethod  - auth methods
     * @param abhaAddress - abha address
     * @return xbdmGenerateOtpResponse
     */
//    @Override
//    public AbdmOtpResponse requestAbhaCardDownloadOtp(final String authMethod, final String abhaAddress) {
//        log.info("Method ABHA Verify By OTP Implementation Starts");
//        AbdmOtpResponse abdmOtpResponse = null;
//        final String accessToken = abdmUtil.getAccessToken();
//        final AbdmAuthInitRequest abdmAuthInitRequest = constructAbdmAuthInitRequest(authMethod, abhaAddress);
//        try {
//            final RestTemplate restTemplate =
//                abdmUtil.constructHttpsRestTemplateForAbdm(accessToken,
//                    null, null, null, false);
//            abdmOtpResponse = restTemplate.postForObject(abdmUtil.m1RequestAbhaCardDownloadOtpUrl, abdmAuthInitRequest,
//                AbdmOtpResponse.class);
//        } catch (Exception e) {
//            log.info(e.getMessage());
//            if (e.getMessage().contains("1202") || e.getMessage().contains("403") ||
//                e.getMessage().contains("422") || e.getMessage().contains("500")) {
//                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
//            } else {
//                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
//            }
//        }
//        log.info("Method ABHA Verify By OTP Implementation Ends");
//        return abdmOtpResponse;
//    }

    /**
     * Confirm and download abha card
     *
     * @param otp     - otp
     * @param txnId   - txnId
     * @param otpType - otpType
     * @return byte array
     */
    @Override
    public byte[] confirmAndDownloadAbhaCard(String otp, String txnId, AbdmOtpType otpType) {
        final String encryptedOtpValue = abdmUtil.encryptValue(otp);
        final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest = constructOtpConfirmRequest(txnId, encryptedOtpValue, otpType, AbdmM1VerificationType.ABHA_NUMBER);
        final String confirmOtpUrl = abdmUtil.m1VerificationConfirmOtpUrl;
        final AbdmV3OtpConfirmResponse abdmV3OtpConfirmResponse = abdmUtil.confirmOtp(abdmV3OtpConfirmRequest, confirmOtpUrl);
        byte[] bytes = null;
        if (!ObjectUtils.isEmpty(abdmV3OtpConfirmResponse.getToken())) {
            bytes = downloadAbhaCard(abdmV3OtpConfirmResponse.getToken());
        }
        return bytes;
    }

    /**
     * Construct otp confirm request.
     *
     * @param txnId        - transaction id
     * @param encryptedOtp - encrypted otp value
     * @return abdmVerifyOtpMobileUpdateRequest
     */
    private AbdmV3OtpConfirmRequest constructOtpConfirmRequest(final String txnId,
                                                               final String encryptedOtp,
                                                               final AbdmOtpType otpType,
                                                               final AbdmM1VerificationType verificationType) {
        final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest = new AbdmV3OtpConfirmRequest();
        final List<String> scope = new ArrayList<>();
        if (verificationType.equals(AbdmM1VerificationType.ABHA_ADDRESS)) {
            scope.add(AbdmConstants.ABHA_ADDRESS_LOGIN);
        } else {
            scope.add(AbdmConstants.ABHA_LOGIN);
        }
        if (otpType.equals(AbdmOtpType.AADHAAR)) {
            scope.add(AbdmConstants.AADHAAR_VERIFY);
        } else {
            scope.add(AbdmConstants.MOBILE_VERIFY);
        }
        abdmV3OtpConfirmRequest.setScope(scope);
        final AbdmV3AuthData abdmV3AuthData = constructAuthData(txnId, encryptedOtp);
        abdmV3OtpConfirmRequest.setAuthData(abdmV3AuthData);
        return abdmV3OtpConfirmRequest;
    }

    /**
     * Construct AbdmV3AuthData for aadhar verification.
     *
     * @param transactionId     - transactionId
     * @param encryptedOtpValue - otpValue
     * @return AbdmV3AuthData
     */
    private AbdmV3AuthData constructAuthData(final String transactionId,
                                             final String encryptedOtpValue) {
        final AbdmV3AuthData abdmV3AuthData = new AbdmV3AuthData();
        abdmV3AuthData.setAuthMethods(abdmUtil.constructAuthMethod());
        final AbdmV3Otp abdmV3Otp = new AbdmV3Otp();
        abdmV3Otp.setTxnId(transactionId);
        abdmV3Otp.setOtpValue(encryptedOtpValue);
        abdmV3AuthData.setOtp(abdmV3Otp);
        return abdmV3AuthData;
    }

//    /**
//     * Confirm otp and download abha card.
//     *
//     * @param otpConfirmRequest - otpConfirmRequest
//     * @param request           - http servlet request
//     * @return byte array
//     */
//    @Override
//    public byte[] confirmAndDownloadAbhaCard(final AbdmOtpConfirmRequest otpConfirmRequest,
//                                             final HttpServletRequest request) {
//        log.info("Method ABHA Verify OTP For Card Download Implementation Starts");
//        byte[] bytes = null;
//        String url = null;
//        final String transactionId = otpConfirmRequest.getTxnId();
//        final String authMethod = otpConfirmRequest.getAuthMethod();
//        if (authMethod.equals(AbdmConstants.MOBILE_OTP)) {
//            url = abdmUtil.m1ConfirmMobileOtpUrl;
//        } else if (authMethod.equals(AbdmConstants.AADHAAR_OTP)) {
//            url = abdmUtil.m1ConfirmAadhaarOtpUrl;
//        }
//        final String publicKey = abdmUtil.getAuthPublicKey();
//        final String encryptedOtpValue = RsaEncryptionUtil.encrypt(otpConfirmRequest.getOtp(), publicKey,
//            AbdmConstants.RSA_ECB_PKCS1Padding);
//        AbdmV3Token abdmV3Token = null;
//        if (!ObjectUtils.isEmpty(url)) {
//            final String accessToken = abdmUtil.getAccessToken();
//            final AbdmOtpConfirmRequest abdmOtpConfirmRequest =
//                constructAbdmOtpRequest(encryptedOtpValue, transactionId);
//            try {
//                final RestTemplate restTemplate =
//                    abdmUtil.constructHttpsRestTemplateForAbdm(accessToken,
//                        null, null, null, false, null, null);
//                abdmV3Token = restTemplate.postForObject(url, abdmOtpConfirmRequest,
//                    AbdmV3Token.class);
//                if (!ObjectUtils.isEmpty(abdmV3Token.getToken())) {
//                    bytes = downloadAbhaCard(accessToken, abdmV3Token.getToken());
//                }
//            } catch (Exception e) {
//                log.info(e.getMessage());
//                if (e.getMessage().contains("1202") || e.getMessage().contains("403") ||
//                    e.getMessage().contains("422") || e.getMessage().contains("500")) {
//                    ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
//                } else {
//                    ExceptionUtil.throwCustomParameterizedException(e.getMessage());
//                }
//            }
//        }
//        log.info("Method ABHA Verify OTP For Card Download Implementation Ends");
//        return bytes;
//    }

    /**
     * Abha card download
     *
     * @param xToken - x-token
     * @return byte array
     */
    @Override
    public byte[] downloadAbhaCard(final String xToken) {
        final String accessToken = abdmUtil.getAccessToken();
        byte[] bytes = null;
        if (!ObjectUtils.isEmpty(xToken)) {
            log.info("Method ABHA Card Download Implementation Starts");
            try {
                final RestTemplate restTemplate =
                        abdmUtil.constructHttpsRestTemplateForAbdm(accessToken, abdmUtil.generateRandomUuid(), null, xToken, CommonConstants.TRUE);
                bytes = restTemplate.getForObject(abdmUtil.m1DownloadAbhaCardUrl, byte[].class);
                log.info("Method ABHA Card Download Implementation Ends");
            } catch (Exception e) {
                log.info(e.getMessage());
                if (e.getMessage().contains("1202") || e.getMessage().contains("403") ||
                        e.getMessage().contains("422") || e.getMessage().contains("500")) {
                    ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
                } else {
                    ExceptionUtil.throwCustomParameterizedException(e.getMessage());
                }
            }
        }
        return bytes;
    }

    /**
     * Construct aadhaar enrollment otp request.
     *
     * @param encryptedValue - encrypted Value
     * @return AbdmV3OtpRequest
     */
    private AbdmV3OtpRequest constructAadhaarEnrollmentOtpRequest(final String encryptedValue) {
        final AbdmV3OtpRequest abdmV3OtpRequest = new AbdmV3OtpRequest();
        abdmV3OtpRequest.setLoginHint(AbdmConstants.AADHAAR);
        abdmV3OtpRequest.setLoginId(encryptedValue);
        abdmV3OtpRequest.setOtpSystem(AbdmConstants.AADHAAR);
        final List<String> scope = new ArrayList<>();
        scope.add(AbdmConstants.ABHA_ENROL);
        abdmV3OtpRequest.setScope(scope);
        return abdmV3OtpRequest;
    }


    /**
     * Construct mobile update otp request.
     *
     * @param encryptedValue - encrypted Value
     * @return AbdmV3OtpRequest
     */
    private AbdmV3OtpRequest constructMobileUpdateOtpRequest(final String encryptedValue, final String txnId) {
        final AbdmV3OtpRequest abdmV3OtpRequest = new AbdmV3OtpRequest();
        abdmV3OtpRequest.setLoginHint(AbdmConstants.MOBILE);
        abdmV3OtpRequest.setLoginId(encryptedValue);
        abdmV3OtpRequest.setOtpSystem(AbdmConstants.ABDM);
        abdmV3OtpRequest.setTxnId(txnId);
        final List<String> scope = new ArrayList<>();
        scope.add(AbdmConstants.ABHA_ENROL);
        scope.add(AbdmConstants.MOBILE_VERIFY);
        abdmV3OtpRequest.setScope(scope);
        return abdmV3OtpRequest;
    }

    /**
     * Construct Abdm version3 verify by aadhaar request
     *
     * @param transactionId     - transaction id
     * @param mobileNo          - mobile no
     * @param encryptedOtpValue - otp value
     * @return AbdmV3AadhaarRequest
     */
    private AbdmV3AadhaarEnrollmentRequest constructAbdmV3AadhaarEnrollmentRequest(final String transactionId,
                                                                                   final String mobileNo,
                                                                                   final String encryptedOtpValue) {
        final AbdmV3AadhaarEnrollmentRequest abdmV3AadhaarEnrollmentRequest = new AbdmV3AadhaarEnrollmentRequest();
        final AbdmV3AuthData abdmV3AuthData = constructAuthDataForAadhaarEnrollment(transactionId, mobileNo, encryptedOtpValue);
        abdmV3AadhaarEnrollmentRequest.setAuthData(abdmV3AuthData);
        abdmV3AadhaarEnrollmentRequest.setConsent(abdmUtil.constructAbdmV3Consent());
        return abdmV3AadhaarEnrollmentRequest;
    }

    /**
     * Construct abdm v3 create abha address request
     *
     * @param txnId       - transaction id
     * @param abhaAddress - abha address
     * @return abdmV3CreateAbhaAddressRequest
     */
    private AbdmV3AddressRequest constructAbdmV3AbhaAddressRequest(final String txnId,
                                                                   final String abhaAddress) {
        final AbdmV3AddressRequest abdmV3AddressRequest = new AbdmV3AddressRequest();
        abdmV3AddressRequest.setAbhaAddress(abhaAddress);
        abdmV3AddressRequest.setPreferred(AbdmConstants.PREFERRED);
        abdmV3AddressRequest.setTxnId(txnId);
        return abdmV3AddressRequest;
    }

    /**
     * Construct abdm auth init response
     *
     * @param authMethod  - auth methods
     * @param abhaAddress - abha address
     * @return abdmAuthInitRequest
     */
    private AbdmAuthInitRequest constructAbdmAuthInitRequest(final String authMethod, final String abhaAddress) {
        final AbdmAuthInitRequest abdmAuthInitRequest = new AbdmAuthInitRequest();
        abdmAuthInitRequest.setAuthMethod(authMethod);
        abdmAuthInitRequest.setHealthid(abhaAddress);
        return abdmAuthInitRequest;
    }

    /**
     * Construct Abdm Verification With Otp Request
     *
     * @param encyptedOtpValue - encrypted otp value
     * @param txnId            - transaction id
     * @return abdmVerificationWithOtpRequest
     */
    private AbdmOtpConfirmRequest constructAbdmOtpRequest(final String encyptedOtpValue,
                                                          final String txnId) {
        final AbdmOtpConfirmRequest abdmOtpConfirmRequest = new AbdmOtpConfirmRequest();
        abdmOtpConfirmRequest.setOtp(encyptedOtpValue);
        abdmOtpConfirmRequest.setTxnId(txnId);
        return abdmOtpConfirmRequest;
    }

    /**
     * Construct abdm mobile update otp verification request.
     *
     * @param txnId        - transaction id
     * @param encryptedOtp - encrypted otp value
     * @return abdmVerifyOtpMobileUpdateRequest
     */
    private AbdmV3OtpConfirmRequest constructMobileUpdateOtpConfirmRequest(final String txnId,
                                                                           final String encryptedOtp) {
        final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest = new AbdmV3OtpConfirmRequest();
        final List<String> scope = new ArrayList<>();
        scope.add(AbdmConstants.ABHA_ENROL);
        scope.add(AbdmConstants.MOBILE_VERIFY);
        abdmV3OtpConfirmRequest.setScope(scope);
        final AbdmV3AuthData abdmV3AuthData = constructAuthDataForMobileUpdate(txnId, encryptedOtp);
        abdmV3OtpConfirmRequest.setAuthData(abdmV3AuthData);
        return abdmV3OtpConfirmRequest;
    }

    /**
     * Construct AbdmV3AuthData for aadhaar enrollment.
     *
     * @param transactionId     - transactionId
     * @param mobileNo          - mobileNo
     * @param encryptedOtpValue - otpValue
     * @return AbdmV3AuthData
     */
    private AbdmV3AuthData constructAuthDataForAadhaarEnrollment(final String transactionId,
                                                                 final String mobileNo, final String encryptedOtpValue) {
        final AbdmV3AuthData abdmV3AuthData = new AbdmV3AuthData();
        abdmV3AuthData.setAuthMethods(abdmUtil.constructAuthMethod());
        final AbdmV3Otp abdmV3Otp = new AbdmV3Otp();
        abdmV3Otp.setTimeStamp(Instant.now().toString());
        abdmV3Otp.setTxnId(transactionId);
        abdmV3Otp.setMobile(mobileNo);
        abdmV3Otp.setOtpValue(encryptedOtpValue);
        abdmV3AuthData.setOtp(abdmV3Otp);
        return abdmV3AuthData;
    }

    /**
     * Construct AbdmV3AuthData for mobile number update.
     *
     * @param transactionId     - transactionId
     * @param encryptedOtpValue - otpValue
     * @return AbdmV3AuthData
     */
    private AbdmV3AuthData constructAuthDataForMobileUpdate(final String transactionId,
                                                            final String encryptedOtpValue) {
        final AbdmV3AuthData abdmV3AuthData = new AbdmV3AuthData();
        abdmV3AuthData.setAuthMethods(abdmUtil.constructAuthMethod());
        final AbdmV3Otp abdmV3Otp = new AbdmV3Otp();
        abdmV3Otp.setTimeStamp(Instant.now().toString());
        abdmV3Otp.setTxnId(transactionId);
        abdmV3Otp.setOtpValue(encryptedOtpValue);
        abdmV3AuthData.setOtp(abdmV3Otp);
        return abdmV3AuthData;
    }
}
