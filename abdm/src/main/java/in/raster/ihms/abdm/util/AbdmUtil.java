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

import com.google.gson.Gson;
import in.raster.ihms.abdm.domain.*;
import in.raster.ihms.abdm.domain.enumeration.AbdmM1Process;
import in.raster.ihms.abdm.domain.enumeration.AbdmOtpType;
import in.raster.ihms.abdm.domain.m1.AbdmV3OtpConfirmRequest;
import in.raster.ihms.abdm.domain.m1.AbdmV3OtpConfirmResponse;
import in.raster.ihms.abdm.domain.m1.AbdmV3PhrOtpConfirmResponse;
import in.raster.ihms.abdm.domain.*;
import in.raster.ihms.commons.util.CommonConstants;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Util class for ABDM.
 */
@Component
public class AbdmUtil {

    private final Logger log = LoggerFactory.getLogger(AbdmUtil.class);

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${abdm.x-cm-id}")
    public String xCmId;

    @Value("${abdm.user-id}")
    public String abdmUserId;

    @Value("${abdm.client-id}")
    private String abdmClientId;

    @Value("${abdm.client-secret}")
    private String abdmClientSecret;

    @Value("${abdm.grant-type}")
    private String abdmGrantType;

    @Value("${abdm.access-token-url}")
    private String abdmAccessTokenUrl;

    @Value("${abdm.public-key-url}")
    private String abdmPublicKeyUrl;

    @Value("${abdm.get-profile-url}")
    public String abdmGetProfileUrl;

    @Value("${abdm.search-abha-address-url}")
    public String abdmSearchAbhaAddressUrl;

    @Value("${abdm.get-phr-profile-url}")
    public String abdmGetPhrProfileUrl;

    @Value("${abdm.milestone1.creation.request-aadhaar-enrollment-otp-url}")
    public String m1RequestAadhaarEnrollmentOtpUrl;

    @Value("${abdm.milestone1.creation.aadhaar-enrollment-url}")
    public String m1AadhaarEnrollmentUrl;

    @Value("${abdm.milestone1.creation.abha-address-suggesstion-url}")
    public String m1AddressSuggessionUrl;

    @Value("${abdm.milestone1.creation.create-abha-address-url}")
    public String m1CreateAbhaAddressUrl;

    @Value("${abdm.milestone1.creation.update-communication-mobile-url}")
    public String m1UpdateMobileNumberUrl;

//    @Value("${abdm.milestone1.creation.request-abha-card-download-otp-url}")
//    public String m1RequestAbhaCardDownloadOtpUrl;

    @Value("${abdm.milestone1.creation.download-abha-card-url}")
    public String m1DownloadAbhaCardUrl;

//    @Value("${abdm.milestone1.creation.confirm-mobile-otp-url}")
//    public String m1ConfirmMobileOtpUrl;
//
//    @Value("${abdm.milestone1.creation.confirm-aadhaar-otp-url}")
//    public String m1ConfirmAadhaarOtpUrl;

    @Value("${abdm.milestone1.verfication.request-otp-url}")
    public String m1VerificationRequestOtpUrl;

    @Value("${abdm.milestone1.verfication.confirm-otp-url}")
    public String m1VerificationConfirmOtpUrl;

    @Value("${abdm.milestone1.verfication.phr-request-otp-url}")
    public String m1VerificationPhrRequestOtpUrl;

    @Value("${abdm.milestone1.verfication.phr-confirm-otp-url}")
    public String m1VerificationPhrConfirmOtpUrl;

    @Value("${abdm.milestone1.verfication.confirm-patient-by-mobile-url}")
    public String m1VerificationConfirmPatientByMobileUrl;

    @Value("${abdm.milestone1.verfication.card-scan-generate-token-url}")
    public String m1CardScanGenerateTokenUrl;


    /**
     * To get access token.
     *
     * @return abdmTokenResponse
     */
    public String getAccessToken() {
        String accessToken = null;
        try {
            final RestTemplate restTemplate = constructRestTemplateForAbdmToken();
            final AbdmTokenRequest abdmTokenRequest = new AbdmTokenRequest();
            abdmTokenRequest.setClientId(abdmClientId);
            abdmTokenRequest.setClientSecret(abdmClientSecret);
            abdmTokenRequest.setGrantType(abdmGrantType);
            log.info("Method Get Access Token Starts");
            final AbdmTokenResponse abdmTokenResponse = restTemplate.postForObject(abdmAccessTokenUrl, abdmTokenRequest,
                    AbdmTokenResponse.class);
            accessToken = abdmTokenResponse.getAccessToken();
            log.info("Method Get Access Token Ends");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return accessToken;
    }

    /**
     * To get authentication certificate public key
     *
     * @return publickey
     */
    public String getAuthPublicKey() {
        String publickey = null;
        try {
            final String accessToken = getAccessToken();
            final RestTemplate restTemplate = constructRestTemplateForAbdmPublicKey(accessToken);
            log.info("Method Get Public Key Starts");
            final AbdmV3PublicKey abdmV3PublicKey = restTemplate.getForObject(abdmPublicKeyUrl, AbdmV3PublicKey.class);
            publickey = abdmV3PublicKey.getPublicKey();
            publickey = publickey.replaceAll("^.*\n|\n-+END PUBLIC KEY-+$", "").replace("\n", "");
            log.info("Method Get Public Key Ends");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return publickey;
    }

    /**
     * Validate aadhaar number with verhoeff algorithm.
     *
     * @param aadhaarNumber - aadhaar number
     */
    public void validateAadhaarNumber(final String aadhaarNumber) {
        final Pattern aadharPattern = Pattern.compile("\\d{12}");
        boolean isValidAadhar = aadharPattern.matcher(aadhaarNumber).matches();
        if (isValidAadhar) {
            isValidAadhar = VerhoeffAlgorithm.validateVerhoeff(aadhaarNumber);
        }
        if (!isValidAadhar) {
            ExceptionUtil.throwCustomParameterizedException(AbdmConstants.INVALID_AADHAAR_NUMBER);
        }
    }

    /**
     * Encrypt given input.
     *
     * @param input - input
     * @return encrypted value
     */
    public String encryptValue(final String input) {
        final String publicKey = getAuthPublicKey();
        return RsaEncryptionUtil.encrypt(input,
                publicKey, AbdmConstants.RSA_ECB_OAEPWithSHA_1AndMGF1Padding);
    }

    /**
     * Request Otp for aadhaar or mobile number
     *
     * @param otpSystem - otp system
     * @return abdmV3GenerateOtpResponse
     */
    public AbdmV3OtpResponse requestOtp(final AbdmV3OtpRequest abdmV3OtpRequest, final String requestOtpUrl,
                                        final String otpSystem, final AbdmM1Process abdmM1Process) {
        log.info("Method Request OTP Implementation Starts");
        AbdmV3OtpResponse abdmV3OtpResponse = null;
        final String accessToken = getAccessToken();
        try {
            final RestTemplate restTemplate = constructHttpsRestTemplateForAbdm(accessToken,
                    generateRandomUuid(), null, null, true);
            abdmV3OtpResponse = restTemplate.postForObject(requestOtpUrl, abdmV3OtpRequest,
                    AbdmV3OtpResponse.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            if (e.getMessage().contains("1202") || e.getMessage().contains("500") || e.getMessage().contains("403")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
            } else if (e.getMessage().contains("404") || e.getMessage().contains("422")) {
                if (abdmM1Process.equals(AbdmM1Process.AADHAAR_ENROLLMENT)) {
                    ExceptionUtil.throwCustomParameterizedException(AbdmConstants.MOBILE_NUMBER_NOT_LINKED_WITH_AADHAAR);
                } else if (abdmM1Process.equals(AbdmM1Process.AADHAAR_VERIFICATION)) {
                    ExceptionUtil.throwCustomParameterizedException(AbdmConstants.NO_ABHA_USER_REGISTERED + " with given Aadhaar number");
                } else if (abdmM1Process.equals(AbdmM1Process.ABHA_NUMBER_VERIFICATION)) {
                    ExceptionUtil.throwCustomParameterizedException(AbdmConstants.NO_ABHA_USER_REGISTERED + " with given Abha number");
                } else if (abdmM1Process.equals(AbdmM1Process.ABHA_ADDRESS_VERIFICATION)) {
                    ExceptionUtil.throwCustomParameterizedException(AbdmConstants.NO_ABHA_USER_REGISTERED + " with given Abha address");
                } else if (abdmM1Process.equals(AbdmM1Process.MOBILE_VERIFICATION)) {
                    ExceptionUtil.throwCustomParameterizedException(AbdmConstants.NO_ABHA_USER_REGISTERED + " with given Mobile number");
                }
            } else if (e.getMessage().contains("400")) {
                if (otpSystem.equals(AbdmConstants.AADHAAR)) {
                    ExceptionUtil.throwCustomParameterizedException(
                            AbdmConstants.INVALID_AADHAAR_NUMBER_ENTERED_PLEASE_ENTER_A_VALID_AADHAAR_NUMBER);
                } else if (otpSystem.equals(AbdmConstants.MOBILE)) {
                    ExceptionUtil.throwCustomParameterizedException(
                            AbdmConstants.INVALID_MOBILE_NUMBER);
                }
            } else if (e.getMessage().contains("429")) {
                if (otpSystem.equals(AbdmConstants.AADHAAR)) {
                    ExceptionUtil.throwCustomParameterizedException(
                            AbdmConstants.TOO_MANY_OTP_REQUEST + "for this aadhaar number, Please try again after sometime");
                } else if (otpSystem.equals(AbdmConstants.MOBILE)) {
                    ExceptionUtil.throwCustomParameterizedException(
                            AbdmConstants.TOO_MANY_OTP_REQUEST + "for this mobile number, Please try again after sometime");
                }
            } else {
                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
            }
        }
        log.info("Method Request OTP Implementation Ends");
        return abdmV3OtpResponse;
    }

    /**
     * Confirm otp.
     *
     * @param abdmV3OtpConfirmRequest - abdmV3OtpConfirmRequest
     * @param url                     - url
     * @return AbdmV3OtpVerificationResponse
     */
    public AbdmV3OtpConfirmResponse confirmOtp(final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest, final String url) {
        AbdmV3OtpConfirmResponse abdmV3OtpConfirmResponse = null;
        final String accessToken = getAccessToken();
        try {
            final RestTemplate restTemplate = constructHttpsRestTemplateForAbdm(accessToken,
                    generateRandomUuid(), null, null, true);
            String jsonString = restTemplate.postForObject(url, abdmV3OtpConfirmRequest,
                    String.class);
            final Gson gson = new Gson();
            abdmV3OtpConfirmResponse = gson.fromJson(jsonString, AbdmV3OtpConfirmResponse.class);
            if (!ObjectUtils.isEmpty(abdmV3OtpConfirmResponse) && !ObjectUtils.isEmpty(abdmV3OtpConfirmResponse.getAuthResult())
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
            } else if (e.getMessage().contains("404")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.NO_ABHA_USER_REGISTERED);
            } else if (e.getMessage().contains("401")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.OTP_EXPIRED);
            } else {
                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
            }
        }
        log.info("Method Verify OTP For Mobile Number Update Implementation Ends");
        return abdmV3OtpConfirmResponse;
    }

    /**
     * Confirm phr otp.
     *
     * @param abdmV3OtpConfirmRequest - abdmV3OtpConfirmRequest
     * @param url                     - url
     * @return AbdmV3PhrOtpConfirmResponse
     */
    public AbdmV3PhrOtpConfirmResponse confirmPhrOtp(final AbdmV3OtpConfirmRequest abdmV3OtpConfirmRequest, final String url) {
        AbdmV3PhrOtpConfirmResponse abdmV3PhrOtpConfirmResponse = null;
        final String accessToken = getAccessToken();
        try {
            final RestTemplate restTemplate = constructHttpsRestTemplateForAbdm(accessToken,
                    generateRandomUuid(), null, null, true);
            abdmV3PhrOtpConfirmResponse = restTemplate.postForObject(url, abdmV3OtpConfirmRequest,
                    AbdmV3PhrOtpConfirmResponse.class);
            if (!ObjectUtils.isEmpty(abdmV3PhrOtpConfirmResponse) && !ObjectUtils.isEmpty(abdmV3PhrOtpConfirmResponse.getAuthResult())
                    && abdmV3PhrOtpConfirmResponse.getAuthResult().equalsIgnoreCase(AbdmConstants.FAILED)) {
                ExceptionUtil.throwCustomParameterizedException(abdmV3PhrOtpConfirmResponse.getMessage());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            if (e.getMessage().contains("1202") || e.getMessage().contains("403") ||
                    e.getMessage().contains("422") || e.getMessage().contains("500")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
            } else if (e.getMessage().contains("400")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.INVALID_OTP_VALUE);
            } else if (e.getMessage().contains("404")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.NO_ABHA_USER_REGISTERED);
            } else if (e.getMessage().contains("401")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.OTP_EXPIRED);
            } else {
                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
            }
        }
        return abdmV3PhrOtpConfirmResponse;
    }

    /**
     * Get Abha V3 profile.
     *
     * @param xToken - x-token
     * @return AbdmV3Profile
     */
    public AbdmV3Profile getAbhaProfile(final String xToken) {
        AbdmV3Profile abdmV3Profile = null;
        log.info("Method to get Abha profile Starts");
        try {
            final String requestId = generateRandomUuid();
            final String accessToken = getAccessToken();
            final RestTemplate restTemplate = constructHttpsRestTemplateForAbdm(accessToken, requestId, null, xToken, CommonConstants.TRUE);
            final String jsonString = restTemplate.getForObject(abdmGetProfileUrl, String.class);
            final Gson gson = new Gson();
            final JSONObject jsonObject = new JSONObject(jsonString);
            abdmV3Profile = gson.fromJson(jsonObject.toString(), AbdmV3Profile.class);
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
        return abdmV3Profile;
    }

    /**
     * Get Abha V3 Phr profile.
     *
     * @param xToken - x-token
     * @return AbdmV3Profile
     */
    public AbdmV3PhrProfile getAbhaPhrProfile(final String xToken) {
        AbdmV3PhrProfile abdmV3PhrProfile = null;
        log.info("Method to get Abha profile Starts");
        try {
            final String requestId = generateRandomUuid();
            final String accessToken = getAccessToken();
            final RestTemplate restTemplate = constructHttpsRestTemplateForAbdm(accessToken, requestId, null, xToken, CommonConstants.TRUE);
            abdmV3PhrProfile = restTemplate.getForObject(abdmGetPhrProfileUrl, AbdmV3PhrProfile.class);
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
        return abdmV3PhrProfile;
    }

    /**
     * Search abha address.
     *
     * @param abhaAddress - abha address
     * @return AbdmV3Profile
     */
    public AbdmV3AbhaAddressSearchResponse searchAbhaAddress(final String abhaAddress) {
        AbdmV3AbhaAddressSearchResponse abdmV3AbhaAddressSearchResponse = null;
        final AbdmV3AbhaAddressSearchRequest abdmV3AbhaAddressSearchRequest = new AbdmV3AbhaAddressSearchRequest();
        abdmV3AbhaAddressSearchRequest.setAbhaAddress(abhaAddress);
        log.info("Method to get Abha profile Starts");
        try {
            final String requestId = generateRandomUuid();
            final String accessToken = getAccessToken();
            final RestTemplate restTemplate = constructHttpsRestTemplateForAbdm(accessToken,
                    requestId, null, null, CommonConstants.TRUE);
            abdmV3AbhaAddressSearchResponse = restTemplate.postForObject(abdmSearchAbhaAddressUrl,
                    abdmV3AbhaAddressSearchRequest, AbdmV3AbhaAddressSearchResponse.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            if (e.getMessage().contains("1202") || e.getMessage().contains("403") ||
                    e.getMessage().contains("422") || e.getMessage().contains("500")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.PLEASE_TRY_AGAIN_SOMETIME);
            } else if (e.getMessage().contains("400")) {
                ExceptionUtil.throwCustomParameterizedException(AbdmConstants.INVALID_ABHA_ADDRESS);
            } else {
                ExceptionUtil.throwCustomParameterizedException(e.getMessage());
            }
        }
        return abdmV3AbhaAddressSearchResponse;
    }

    /**
     * Construct auth methods.
     *
     * @return auth methods
     */
    public List<String> constructAuthMethod() {
        final List<String> authMethods = new ArrayList<>(CommonConstants.ONE);
        authMethods.add(AbdmConstants.OTP);
        return authMethods;
    }

    /**
     * construct AbdmV3Consent
     *
     * @return AbdmV3Consent
     */
    public AbdmV3Consent constructAbdmV3Consent() {
        final AbdmV3Consent abdmV3Consent = new AbdmV3Consent();
        abdmV3Consent.setCode(AbdmConstants.ABHA_ENROLLMENT);
        abdmV3Consent.setVersion(AbdmConstants.CONSENT_VERSION);
        return abdmV3Consent;
    }

    /**
     * Set json content type in rest template.
     *
     */
    public void setJsonContentTypeInRestTemplate(final RestTemplate restTemplate) {
        final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        restTemplate.setInterceptors(interceptors);
    }

    /**
     * Construct rest template object to communicate with external https apis.
     *
     * @return RestTemplate
     */
    private RestTemplate constructRestTemplateForAbdmToken() throws Exception {
        final RestTemplate restTemplate = constructHttpsRestTemplate();
        final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.REQUEST_ID, generateRandomUuid()));
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.TIMESTAMP, Instant.now().toString()));
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.X_CM_ID, xCmId));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    /**
     * Construct rest template object to communicate with external https apis.
     *
     * @return RestTemplate
     */
    private RestTemplate constructRestTemplateForAbdmPublicKey(final String accessToken) throws Exception {
        final RestTemplate restTemplate = constructHttpsRestTemplate();
        final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.AUTHORIZATION, AbdmConstants.BEARER + accessToken));
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.REQUEST_ID, generateRandomUuid()));
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.TIMESTAMP, Instant.now().toString()));
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.X_CM_ID, xCmId));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    /**
     * Construct https rest template.
     *
     * @return RestTemplate
     * @throws Exception - Exception
     */
    public RestTemplate constructHttpsRestTemplate() throws Exception {
        final CloseableHttpClient httpClient = createHttpsClient();
        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }


    /**
     * Construct rest template object to communicate with external https apis.
     *
     * @return RestTemplate
     */
    public RestTemplate constructHttpsRestTemplateForAbdm(final String accessToken, final String requestId,
                                                          final String txnId, final String xToken,
                                                          final boolean isTimeStampRequired) throws Exception {
        final RestTemplate restTemplate = constructHttpsRestTemplate();
        final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HeaderRequestInterceptor(AbdmConstants.AUTHORIZATION, AbdmConstants.BEARER + accessToken));
        if (!ObjectUtils.isEmpty(requestId)) {
            interceptors.add(new HeaderRequestInterceptor(AbdmConstants.REQUEST_ID, requestId));
        }
        if (!ObjectUtils.isEmpty(isTimeStampRequired) && isTimeStampRequired) {
            interceptors.add(new HeaderRequestInterceptor(AbdmConstants.TIMESTAMP, Instant.now().toString()));
        }
        if (!ObjectUtils.isEmpty(txnId)) {
            interceptors.add(new HeaderRequestInterceptor(AbdmConstants.TRANSACTION_ID, txnId));
        }
        if (!ObjectUtils.isEmpty(xToken)) {
            interceptors.add(new HeaderRequestInterceptor(AbdmConstants.X_TOKEN, AbdmConstants.BEARER + xToken));
        }
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    /**
     * Create http client
     *
     * @return http client
     * @throws Exception - Exception
     */
    private CloseableHttpClient createHttpsClient() throws Exception {
        final CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier()).
                        setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                            public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                                return true;
                            }
                        }).build()).build();
        return httpClient;
    }

    /**
     * Generate random UUID
     *
     * @return - random uuid
     */
    public String generateRandomUuid() {
        final UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

//    /**
//     * Generate random GUID
//     *
//     * @return - random guid
//     */
//    public String generateRandomGuid() {
//        final Guid.GUID guid = Guid.GUID.newGuid();
//        return guid.toString();
//    }


    /**
     * Construct year of birth
     *
     * @param dob - dob
     * @return year of birth
     */
    private int constructYearOfBirth(final Instant dob) {
        final LocalDateTime localDateTime = LocalDateTime.ofInstant(dob, ZoneOffset.UTC);
        return localDateTime.getYear();
    }

    /**
     * Get otp system.
     *
     * @param otpType - otp type
     * @return otp system
     */
    public String getOtpSystemFromOtpType(final AbdmOtpType otpType) {
        final String otpSystem;
        if (otpType.equals(AbdmOtpType.AADHAAR)) {
            otpSystem = AbdmConstants.AADHAAR;
        } else {
            otpSystem = AbdmConstants.MOBILE;
        }
        return otpSystem;
    }

    /**
     * Set media type
     *
     * @param restTemplate - rest template
     */
    public void setMediaType(final RestTemplate restTemplate) {
        final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.ALL));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
    }

    /**
     * To get suffix consent manager.
     *
     * @param abhaAddress - abhaAddress
     * @return abha address suffix
     */
    public String getSuffixConsentManager(final String abhaAddress) {
        String suffix = null;
        if (!ObjectUtils.isEmpty(abhaAddress) && abhaAddress.contains("@")) {
            suffix = abhaAddress.split("@")[1];
        }
        return suffix;
    }
}
