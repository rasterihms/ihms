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
package in.raster.ihms.authserver.service.impl.custom;

import in.raster.ihms.authserver.domain.custom.Application;
import in.raster.ihms.authserver.ldap.domain.LdapApplication;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.ApplicationCustomService;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.TokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Custom service implementation to manage applications.
 */
@Service
@Transactional
public class ApplicationCustomServiceImpl implements ApplicationCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;
    private final TokenUtil tokenUtil;

    public ApplicationCustomServiceImpl(LdapCustomService ldapCustomService, TokenUtil tokenUtil) {
        this.ldapCustomService = ldapCustomService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * Get applications by user and branch.
     *
     * @param request - http servlet request
     * @return - list of applications
     */
    @Override
    public List<Application> getApplicationsByUserAndBranch(final HttpServletRequest request) {
        final List<LdapApplication> ldapApplications = ldapCustomService
            .getApplicationsByUserAndBranch(AuthServerUtil.getUser().getId(), tokenUtil.getBranchIdFromToken(request),
                tokenUtil.getOrganizationIdFromToken(request));
        return ldapMapper.constructApplicationListFromLdapApplications(ldapApplications);
    }

    /**
     * Get list of applications by branch and organization.
     *
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of application
     */
    @Override
    public List<Application> getApplicationsByBranchAndOrganization(final String branchName,
                                                                    final String organizationName) {
        final List<LdapApplication> ldapApplicationList = ldapCustomService.
            getApplicationsByUserAndBranch(null, branchName, organizationName);
        return ldapMapper.constructApplicationListFromLdapApplications(ldapApplicationList);
    }
}
