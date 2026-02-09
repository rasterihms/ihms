/*******************************************************************************
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
 *******************************************************************************/
package in.raster.ihms.authserver.web.rest.custom;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.authserver.ldap.repository.LdapUserRepository;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.security.jwt.custom.JwtAuthenticationResponse;
import in.raster.ihms.authserver.util.ExceptionConstants;
import in.raster.ihms.authserver.util.ExceptionUtil;
import in.raster.ihms.commons.security.JwtAuthenticationRequest;
import in.raster.ihms.commons.security.JwtTokenUtil;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.exceptions.custom.InvalidCredentialsException;
import in.raster.ihms.exceptions.custom.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Custom resource for ldap authentication.
 */
@RestController
@RequestMapping("/api")
public class AuthenticationCustomResource {

    @Value("${ldap.partitionSuffix}")
    private String ldapBaseDn;

    private final Logger log = LoggerFactory.getLogger(AuthenticationCustomResource.class);
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final LdapCustomService ldapCustomService;
    private final LdapUserRepository ldapUserRepository;

    public AuthenticationCustomResource(JwtTokenUtil jwtTokenUtil,
                                        UserDetailsService userDetailsService,
                                        LdapCustomService ldapCustomService,
                                        LdapUserRepository ldapUserRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.ldapCustomService = ldapCustomService;
        this.ldapUserRepository = ldapUserRepository;
    }

    /**
     * Authenticate given username, password and generates token.
     *
     * @param authenticationRequest - authentication request holds username and password
     * @return JWT token
     */
    @PostMapping("/auth")
    @Timed
    public ResponseEntity<?> createAuthenticationToken(
        @RequestBody final JwtAuthenticationRequest authenticationRequest) {
        try {
            ldapCustomService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        } catch (InvalidCredentialsException invalidCredentialsException) {
            ExceptionUtil.throwInvalidCredentialsException(ExceptionConstants.INCORRECT_USER_NAME_PASSWORD);
        }
        // Perform the security
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (!userDetails.isEnabled()) {
            throw new ObjectNotFoundException(ExceptionConstants.USER_IS_NOT_ACTIVE);
        }
        final String userName = userDetails.getUsername()
            .substring(CommonConstants.ZERO, userDetails.getUsername().indexOf(CommonConstants.COMMA_SEPARATOR));
        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
        final String displayName = !ObjectUtils.isEmpty(ldapUser.getSurname()) ? ldapUser.getSurname() : CommonConstants.EMPTY_STRING;
        final String userId = userDetails.getUsername()
            .substring(userDetails.getUsername().indexOf(CommonConstants.COMMA_SEPARATOR) + CommonConstants.ONE);
        final String token = jwtTokenUtil.generateToken(userDetails, userName,
            userId, authenticationRequest.getBranchId(), authenticationRequest.getOrganizationId(),
            authenticationRequest.getZoneName(), ldapUser.getSurname());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token, userName, displayName));
    }

    /**
     * Authenticate given username, password and generates token.
     *
     * @param authenticationRequest - authentication request holds username and password
     * @return JWT token
     */
    @PostMapping("/token")
    @Timed
    public String getToken(@RequestBody final JwtAuthenticationRequest authenticationRequest) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (!userDetails.isEnabled()) {
            throw new ObjectNotFoundException(ExceptionConstants.USER_IS_NOT_ACTIVE);
        }
        final String userName = userDetails.getUsername()
            .substring(CommonConstants.ZERO, userDetails.getUsername().indexOf(CommonConstants.COMMA_SEPARATOR));
        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
        final String userId = userDetails.getUsername()
            .substring(userDetails.getUsername().indexOf(CommonConstants.COMMA_SEPARATOR) + CommonConstants.ONE);
        return jwtTokenUtil.generateToken(userDetails, userName,
            userId, authenticationRequest.getBranchId(), authenticationRequest.getOrganizationId(),
            authenticationRequest.getZoneName(), ldapUser.getSurname());
    }

    /**
     * Check token is expired or not.
     *
     * @param token - token
     * @return true or false
     */
    @GetMapping("/check-token-expiration")
    @Timed
    public Boolean isTokenExpired(@RequestParam(value = "token") final String token) {
        return jwtTokenUtil.isTokenExpired(token);
    }
}
