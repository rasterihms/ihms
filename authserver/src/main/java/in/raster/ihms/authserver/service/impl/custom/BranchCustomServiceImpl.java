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

import in.raster.ihms.authserver.domain.custom.Branch;
import in.raster.ihms.authserver.ldap.domain.LdapApplication;
import in.raster.ihms.authserver.ldap.domain.LdapGroup;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.authserver.ldap.repository.LdapGroupRepository;
import in.raster.ihms.authserver.ldap.repository.LdapUserRepository;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.BranchCustomService;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.TokenUtil;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.commons.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

/**
 * Custom service implementation to manage branches.
 */
@Service
@Transactional
public class BranchCustomServiceImpl implements BranchCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;
    private final LdapUserRepository ldapUserRepository;
    private final TokenUtil tokenUtil;
    private final LdapGroupRepository ldapGroupRepository;

    public BranchCustomServiceImpl(LdapCustomService ldapCustomService, LdapUserRepository ldapUserRepository,
                                   TokenUtil tokenUtil, LdapGroupRepository ldapGroupRepository) {
        this.ldapCustomService = ldapCustomService;
        this.ldapUserRepository = ldapUserRepository;
        this.tokenUtil = tokenUtil;
        this.ldapGroupRepository = ldapGroupRepository;
    }

    /**
     * Create branch in ldap.
     *
     * @param branch  - branch object
     * @param request - http servlet request
     * @return saved branch
     */
    @Override
    public Branch createBranch(final Branch branch, final HttpServletRequest request) {
        branch.setCreatedBy(AuthServerUtil.getUser().getId());
        branch.setModifiedBy(AuthServerUtil.getUser().getId());
        final String ldapBranchId = ldapCustomService
            .createOrganizationBranch(branch, tokenUtil.getOrganizationIdFromToken(request));
        branch.setId(ldapBranchId);
        addBranchToApplications(branch);
        return branch;
    }

    /**
     * Map branch to applications.
     *
     * @param branch - branch object
     */
    private void addBranchToApplications(final Branch branch) {
        if (!CollectionUtils.isEmpty(branch.getMembers())) {
            branch.getMembers().forEach(applicationId -> {
                ldapCustomService.addMemberToGroup(branch.getId(), applicationId);
            });
        }
    }

    /**
     * Modify branch attributes.
     *
     * @param branch  - branch object
     * @param request - http servlet request
     */
    @Override
    public void modifyBranchAttributes(final Branch branch, final HttpServletRequest request) {
        branch.setModifiedBy(AuthServerUtil.getUser().getId());
        ldapCustomService.modifyBranchAttributes(branch);
        removeBranchFromApplications(branch, request);
        addBranchToApplications(branch);
    }

    /**
     * Remove branch from applications.
     *
     * @param branch  - branch object
     * @param request - http servlet request
     */
    private void removeBranchFromApplications(final Branch branch, final HttpServletRequest request) {
        final List<LdapApplication> ldapApplicationList = ldapCustomService.getApplicationsByUserAndBranch(null,
            branch.getName(), tokenUtil.getOrganizationIdFromToken(request));
        if (!CollectionUtils.isEmpty(ldapApplicationList)) {
            ldapApplicationList.forEach(ldapApplication -> {
                ldapCustomService.removeMemberFromGroup(branch.getId(), String.valueOf(ldapApplication.getDn()));
            });
        }
    }

    /**
     * Get all branches.
     *
     * @param branchName - branch name to search
     * @param pageable   - pageable object
     * @param request    - http servlet request
     * @return list of branches
     */
    @Override
    public Page<Branch> getAllBranches(final String branchName, final Pageable pageable,
                                       final HttpServletRequest request) {
        final List<LdapGroup> ldapGroupList = ldapCustomService
            .getAllBranches(branchName, tokenUtil.getOrganizationIdFromToken(request));
        final List<Branch> branchList = ldapMapper.constructBranchListFromLdapBranches(ldapGroupList);
        final Comparator<Branch> branchComparator = PaginationUtil.getComparator(Branch::getName, pageable);
        return PaginationUtil.doCustomPaging(pageable, branchList, branchComparator);
    }

    /**
     * Get list of branches by organization.
     *
     * @param organizationName - organization name
     * @return list of branch
     */
    @Override
    public List<Branch> getBranchesByOrganization(final String organizationName) {
        final List<LdapGroup> ldapGroupList = ldapCustomService
            .getBranchesByUserAndOrganization(null, organizationName);
        return ldapMapper.constructBranchListFromLdapBranches(ldapGroupList);
    }

    /**
     * Get list of branches by user name and organization.
     *
     * @param userName - user name
     * @return list of branch
     */
    @Override
    public List<Branch> getBranchesByUserAndOrganization(final String userName, final String organizationName) {
        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
        final List<LdapGroup> ldapGroupList = ldapCustomService
            .getBranchesByUserAndOrganization(String.valueOf(ldapUser.getId()), organizationName);
        return ldapMapper.constructBranchListFromLdapBranches(ldapGroupList);
    }

    /**
     * Get current branch billing limit.
     *
     * @param request - http servlet request
     * @return billing limit
     */
    @Override
    public Long getCurrentBranchBillingLimit(final HttpServletRequest request) {
        final List<LdapGroup> ldapGroupList = ldapCustomService.getAllBranches(tokenUtil.getBranchIdFromToken(request),
            tokenUtil.getOrganizationIdFromToken(request));
        final String billingLimit = ldapGroupList.get(CommonConstants.ZERO).getBillingLimit();
        return Long.valueOf(billingLimit);
    }

    /**
     * Get branch by name.
     *
     * @param branchName - branch name
     * @return branch object
     */
    @Override
    public Branch getBranchByName(final String branchName) {
        final LdapGroup ldapGroup = ldapGroupRepository.findByName(branchName);
        return ldapMapper.constructBranchFromLdapBranch(ldapGroup);
    }
}
