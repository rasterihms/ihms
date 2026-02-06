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
import in.raster.ihms.abdm.domain.m1.AbdmV3OtpConfirmRequest;
import in.raster.ihms.abdm.domain.m1.AbdmV3OtpConfirmResponse;
import in.raster.ihms.abdm.domain.m1.AbdmV3PhrOtpConfirmResponse;
import in.raster.ihms.abdm.util.ExceptionUtil;
import in.raster.ihms.abdm.domain.*;
import in.raster.ihms.abdm.domain.enumeration.*;
import in.raster.ihms.abdm.domain.m1.*;
import in.raster.ihms.abdm.service.AbdmM1VerificationService;
import in.raster.ihms.abdm.util.AbdmConstants;
import in.raster.ihms.abdm.util.AbdmUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom service implementation to verify abdm
 */
@Service
@Transactional
public class AbdmM1VerificationServiceImpl implements AbdmM1VerificationService {

    private final Logger log = LoggerFactory.getLogger(AbdmM1VerificationServiceImpl.class);

    private final AbdmUtil abdmUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    public AbdmM1VerificationServiceImpl(AbdmUtil abdmUtil) {
        this.abdmUtil = abdmUtil;
    }

    /**
     * Request aadhaar verification otp.
     *
     * @param aadhaarNo - aadhaar number
     * @return AbdmV3OtpResponse
     */
    @Override
    public AbdmV3OtpResponse requestAadhaarVerificationOtp(final String aadhaarNo) {
        abdmUtil.validateAadhaarNumber(aadhaarNo);
        final String encryptedValue = abdmUtil.encryptValue(aadhaarNo);
        final AbdmV3OtpRequest abdmV3OtpRequest = constructOtpRequest(encryptedValue, AbdmOtpType.AADHAAR, AbdmM1VerificationType.AADHAAR);
        final String requestOtpUrl = abdmUtil.m1VerificationRequestOtpUrl;
        return abdmUtil.requestOtp(abdmV3OtpRequest, requestOtpUrl, AbdmConstants.AADHAAR, AbdmM1Process.AADHAAR_VERIFICATION);
    }

    /**
     * Verify aadhaar verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3Profile
     */
    @Override
    public AbdmV3Profile confirmAadhaarVerificationOtp(final String otp, final String txnId) {
        log.info("Method Verify OTP For Aadhaar verification Implementation Starts");
        final String encryptedOtpValue = abdmUtil.encryptValue(otp);
        final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest = constructOtpConfirmRequest(txnId, encryptedOtpValue,
            AbdmOtpType.AADHAAR, AbdmM1VerificationType.AADHAAR);
        final String confirmOtpUrl = abdmUtil.m1VerificationConfirmOtpUrl;
        final AbdmV3OtpConfirmResponse abdmV3OtpConfirmResponse = abdmUtil.confirmOtp(abdmV3OtpConfirmRequest, confirmOtpUrl);
        final AbdmV3Profile abdmV3Profile = abdmUtil.getAbhaProfile(abdmV3OtpConfirmResponse.getToken());
        abdmV3Profile.setxToken(abdmV3OtpConfirmResponse.getToken());
        return abdmV3Profile;
    }

    /**
     * Request abha number verification otp.
     *
     * @param abhaNumber - abha number
     * @return AbdmV3OtpResponse
     */
    @Override
    public AbdmV3OtpResponse requestAbhaNumberVerificationOtp(final String abhaNumber, final AbdmOtpType otpType) {
        final String encryptedValue = abdmUtil.encryptValue(abhaNumber);
        final String otpSystem = abdmUtil.getOtpSystemFromOtpType(otpType);
        final AbdmV3OtpRequest abdmV3OtpRequest = constructOtpRequest(encryptedValue, otpType, AbdmM1VerificationType.ABHA_NUMBER);
        final String requestOtpUrl = abdmUtil.m1VerificationRequestOtpUrl;
        return abdmUtil.requestOtp(abdmV3OtpRequest, requestOtpUrl, otpSystem, AbdmM1Process.ABHA_NUMBER_VERIFICATION);
    }

    /**
     * Confirm abha number verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3Profile
     */
    @Override
    public AbdmV3Profile confirmAbhaNumberVerificationOtp(final String otp, final String txnId,
                                                          final AbdmOtpType otpType) {
        final String encryptedOtpValue = abdmUtil.encryptValue(otp);
        final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest = constructOtpConfirmRequest(txnId, encryptedOtpValue, otpType, AbdmM1VerificationType.ABHA_NUMBER);
        final String confirmOtpUrl = abdmUtil.m1VerificationConfirmOtpUrl;
        final AbdmV3OtpConfirmResponse abdmV3OtpConfirmResponse = abdmUtil.confirmOtp(abdmV3OtpConfirmRequest, confirmOtpUrl);
        final AbdmV3Profile abdmV3Profile = abdmUtil.getAbhaProfile(abdmV3OtpConfirmResponse.getToken());
        abdmV3Profile.setxToken(abdmV3OtpConfirmResponse.getToken());
        return abdmV3Profile;
    }

    /**
     * Request abha address verification otp.
     *
     * @param abhaAddress - abha address
     * @return AbdmV3OtpResponse
     */
    @Override
    public AbdmV3OtpResponse requestAbhaAddressVerificationOtp(final String abhaAddress, final AbdmOtpType otpType) {
        final AbdmV3AbhaAddressSearchResponse abdmV3AbhaAddressSearchResponse = abdmUtil.searchAbhaAddress(abhaAddress);
        final String otpSystem = abdmUtil.getOtpSystemFromOtpType(otpType);
        AbdmV3OtpResponse abdmV3OtpResponse = null;
        if (!ObjectUtils.isEmpty(abdmV3AbhaAddressSearchResponse)) {
            final String encryptedValue = abdmUtil.encryptValue(abhaAddress);
            final AbdmV3OtpRequest abdmV3OtpRequest = constructOtpRequest(encryptedValue, otpType, AbdmM1VerificationType.ABHA_ADDRESS);
            final String requestOtpUrl = abdmUtil.m1VerificationPhrRequestOtpUrl;
            abdmV3OtpResponse = abdmUtil.requestOtp(abdmV3OtpRequest, requestOtpUrl, otpSystem, AbdmM1Process.ABHA_ADDRESS_VERIFICATION);
        } else {
            ExceptionUtil.throwCustomParameterizedException(AbdmConstants.ABHA_ADDRESS_SEARCH_RESPONSE_EMPTY);
        }
        return abdmV3OtpResponse;
    }

    /**
     * Confirm abha address verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3Profile
     */
    @Override
    public AbdmV3PhrProfile confirmAbhaAddressVerificationOtp(final String otp, final String txnId, final AbdmOtpType otpType) {
        final String encryptedOtpValue = abdmUtil.encryptValue(otp);
        final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest = constructOtpConfirmRequest(txnId, encryptedOtpValue, otpType,
            AbdmM1VerificationType.ABHA_ADDRESS);
        final String confirmOtpUrl = abdmUtil.m1VerificationPhrConfirmOtpUrl;
        final AbdmV3PhrOtpConfirmResponse abdmV3PhrOtpConfirmResponse = abdmUtil.confirmPhrOtp(abdmV3OtpConfirmRequest, confirmOtpUrl);
        final AbdmV3PhrProfile abdmV3PhrProfile = abdmUtil.getAbhaPhrProfile(abdmV3PhrOtpConfirmResponse.getTokens().getToken());
        abdmV3PhrProfile.setxToken(abdmV3PhrOtpConfirmResponse.getTokens().getToken());
        return abdmV3PhrProfile;
    }

    /**
     * Request mobile verification otp.
     *
     * @param mobileNumber - mobile number
     * @return AbdmV3OtpResponse
     */
    @Override
    public AbdmV3OtpResponse requestMobileVerificationOtp(final String mobileNumber) {
        final String encryptedValue = abdmUtil.encryptValue(mobileNumber);
        final AbdmV3OtpRequest abdmV3OtpRequest = constructOtpRequest(encryptedValue, AbdmOtpType.COMMUNICATION_MOBILE,
            AbdmM1VerificationType.MOBILE);
        final String requestOtpUrl = abdmUtil.m1VerificationRequestOtpUrl;
        return abdmUtil.requestOtp(abdmV3OtpRequest, requestOtpUrl, AbdmConstants.MOBILE, AbdmM1Process.MOBILE_VERIFICATION);
    }

    /**
     * Confirm mobile verification otp.
     *
     * @param otp   - otp
     * @param txnId - transaction id
     * @return AbdmV3OtpConfirmResponse
     */
    @Override
    public AbdmV3OtpConfirmResponse confirmMobileVerificationOtp(final String otp, final String txnId) {
        final String encryptedOtpValue = abdmUtil.encryptValue(otp);
        final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest = constructOtpConfirmRequest(txnId, encryptedOtpValue,
            AbdmOtpType.COMMUNICATION_MOBILE, AbdmM1VerificationType.MOBILE);
        final String confirmOtpUrl = abdmUtil.m1VerificationConfirmOtpUrl;
        return abdmUtil.confirmOtp(abdmV3OtpConfirmRequest, confirmOtpUrl);
    }

    /**
     * Confirm patient on mobile verification
     *
     * @param abhaNumber - abha number
     * @param txnId      - txn id
     * @param tToken     - ttoken
     * @return abdmV3Profile
     */
    @Override
    public AbdmV3Profile confirmPatientOnMobileVerification(final String abhaNumber, final String txnId,
                                                            final String tToken) {
        final AbdmV3User abdmV3User = new AbdmV3User();
        abdmV3User.setTxnId(txnId);
        abdmV3User.setABHANumber(abhaNumber);
        AbdmV3OtpConfirmResponse abdmV3OtpConfirmResponse = null;
        final String accessToken = abdmUtil.getAccessToken();
        try {
            final HttpHeaders headers = new HttpHeaders();
            headers.set(AbdmConstants.AUTHORIZATION, AbdmConstants.BEARER + accessToken);
            headers.set(AbdmConstants.REQUEST_ID, abdmUtil.generateRandomUuid());
            headers.set(AbdmConstants.TIMESTAMP, Instant.now().toString());
            headers.set(AbdmConstants.T_TOKEN, AbdmConstants.BEARER + tToken);
            headers.setContentType(MediaType.APPLICATION_JSON);
            final RestTemplate restTemplate = abdmUtil.constructHttpsRestTemplate();
            abdmUtil.setJsonContentTypeInRestTemplate(restTemplate);
            abdmUtil.setMediaType(restTemplate);
            final Gson gson = new Gson();
            String jsonString = gson.toJson(abdmV3User);
            final HttpEntity<?> httpEntity = new HttpEntity<>(jsonString, headers);
            final HttpEntity<String> responseEntity = restTemplate.exchange(abdmUtil.m1VerificationConfirmPatientByMobileUrl,
                HttpMethod.POST, httpEntity, String.class);
            final String responseJsonString = responseEntity.getBody();
            abdmV3OtpConfirmResponse = gson.fromJson(responseJsonString, AbdmV3OtpConfirmResponse.class);
            if(!ObjectUtils.isEmpty(abdmV3OtpConfirmResponse.getAuthResult())
                && abdmV3OtpConfirmResponse.getAuthResult().equalsIgnoreCase(AbdmConstants.FAILED)) {
                ExceptionUtil.throwCustomParameterizedException(abdmV3OtpConfirmResponse.getMessage());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            if (e.getMessage().contains("1202") || e.getMessage().contains("403") ||
                e.getMessage().contains("422") || e.getMessage().contains("500")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
            } else if (e.getMessage().contains("400")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.INVALID_OTP_VALUE);
            } else if (e.getMessage().contains("401")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.OTP_EXPIRED);
            } else {
                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
            }
        }
        final AbdmV3Profile abdmV3Profile = abdmUtil.getAbhaProfile(abdmV3OtpConfirmResponse.getToken());
        abdmV3Profile.setxToken(abdmV3OtpConfirmResponse.getToken());
        return abdmV3Profile;
    }

    /**
     * Construct otp request.
     *
     * @param encryptedValue - encrypted Value
     * @return AbdmV3OtpRequest
     */
    private AbdmV3OtpRequest constructOtpRequest(final String encryptedValue,
                                                 final AbdmOtpType otpType,
                                                 final AbdmM1VerificationType verificationType) {
        final AbdmV3OtpRequest abdmV3OtpRequest = new AbdmV3OtpRequest();
        abdmV3OtpRequest.setLoginId(encryptedValue);
        final List<String> scope = new ArrayList<>();
        if (verificationType.equals(AbdmM1VerificationType.AADHAAR)) {
            abdmV3OtpRequest.setLoginHint(AbdmConstants.AADHAAR);
            scope.add(AbdmConstants.ABHA_LOGIN);
        } else if (verificationType.equals(AbdmM1VerificationType.ABHA_NUMBER)) {
            abdmV3OtpRequest.setLoginHint(AbdmConstants.ABHA_NUMBER);
            scope.add(AbdmConstants.ABHA_LOGIN);
        } else if (verificationType.equals(AbdmM1VerificationType.ABHA_ADDRESS)) {
            abdmV3OtpRequest.setLoginHint(AbdmConstants.ABHA_ADDRESS);
            scope.add(AbdmConstants.ABHA_ADDRESS_LOGIN);
        } else {
            abdmV3OtpRequest.setLoginHint(AbdmConstants.MOBILE);
            scope.add(AbdmConstants.ABHA_LOGIN);
        }
        if (otpType.equals(AbdmOtpType.AADHAAR)) {
            abdmV3OtpRequest.setOtpSystem(AbdmConstants.AADHAAR);
            scope.add(AbdmConstants.AADHAAR_VERIFY);
        } else {
            abdmV3OtpRequest.setOtpSystem(AbdmConstants.ABDM);
            scope.add(AbdmConstants.MOBILE_VERIFY);
        }
        abdmV3OtpRequest.setScope(scope);
        return abdmV3OtpRequest;
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
//     * Method to generate link token.
//     *
//     * @param patient - patient
//     */
//    @Override
//    public void generateLinkToken(final Patient patient) {
//        final String accessToken = abdmUtil.getAccessToken();
//        log.info("Method ABDM Generate Link Token Starts");
//        try {
//            final AbdmLinkTokenRequest abdmLinkTokenRequest = abdmUtil.constructAbdmLinkTokenRequest(patient);
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + accessToken);
//            headers.set("REQUEST-ID", abdmUtil.generateRandomUuid());
//            headers.set("X-HIP-ID", "IN3310001996");
//            headers.set("X-CM-ID", "sbx");
//            headers.set("TIMESTAMP", Instant.now().toString());
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            final RestTemplate restTemplate = hisUtil.constructRestTemplate();
//            hisUtil.setMediaType(restTemplate);
//            HttpEntity<?> httpEntity = new HttpEntity<Object>(abdmLinkTokenRequest, headers);
//            restTemplate.exchange(abdmUtil.m1CardScanGenerateTokenUrl, HttpMethod.POST, httpEntity, Void.class);
//            log.info("Method ABDM Generate Link Token Ends");
//            log.info("-------------------------------------------------");
//            log.info("abdm link token request" + abdmLinkTokenRequest);
//            log.info("-------------------------------------------------");
//        } catch (Exception e) {
//            if (e.getMessage().contains("1202") || e.getMessage().contains("403") ||
//                e.getMessage().contains("422") || e.getMessage().contains("500")) {
//                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
//            } else {
//                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
//            }
//            log.info(e.getMessage());
//        }
//    }
//
//    /**
//     * Get link token response
//     *
//     * @param abdmLinkTokenResponse - abdmLinkTokenResponse
//     * @param request-              http servlet request
//     * @return abdmLinkTokenResponse
//     */
//    @Override
//    public AbdmLinkTokenResponse getLinkTokenResponse(final AbdmLinkTokenResponse abdmLinkTokenResponse,
//                                                      final HttpServletRequest request) {
//        log.info("Method Get Link Token Implementation Starts");
//        log.info("--------------------------------------------");
//        log.info("abdm link token response" + abdmLinkTokenResponse);
//        log.info("--------------------------------------------");
//        if (!ObjectUtils.isEmpty(abdmLinkTokenResponse) && !(ObjectUtils.isEmpty(abdmLinkTokenResponse.getAbhaAddress()))
//            && !(ObjectUtils.isEmpty(abdmLinkTokenResponse.getLinkToken()))) {
//            final Patient patient = abdmUtil.getPatientByAbhaAddress(abdmLinkTokenResponse.getAbhaAddress());
//            patient.setAbhaLinkToken(abdmLinkTokenResponse.getLinkToken());
//            patientRepository.save(patient);
//            log.info("--------------------------------------------");
//            log.info(abdmLinkTokenResponse.getLinkToken());
//            log.info("--------------------------------------------");
//        }
//        log.info("Method Get Link Token Implementation Ends");
//        return abdmLinkTokenResponse;
//    }
}
