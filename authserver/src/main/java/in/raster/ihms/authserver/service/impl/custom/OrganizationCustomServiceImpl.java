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

import in.raster.ihms.authserver.domain.custom.Organization;
import in.raster.ihms.authserver.ldap.domain.LdapOrganization;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.authserver.ldap.repository.LdapOrganizationRepository;
import in.raster.ihms.authserver.ldap.repository.LdapUserRepository;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.OrganizationCustomService;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.ExceptionConstants;
import in.raster.ihms.authserver.util.ExceptionUtil;
import in.raster.ihms.authserver.util.StorageUtil;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.commons.util.PaginationUtil;
import in.raster.ihms.exceptions.custom.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom service implementation to manage organization related functions.
 */
@Service
@Transactional
public class OrganizationCustomServiceImpl implements OrganizationCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;
    private final LdapUserRepository ldapUserRepository;
    private final LdapOrganizationRepository ldapOrganizationRepository;
    private final StorageUtil storageUtil;

    public OrganizationCustomServiceImpl(LdapCustomService ldapCustomService,
                                         LdapUserRepository ldapUserRepository,
                                         LdapOrganizationRepository ldapOrganizationRepository,
                                         StorageUtil storageUtil) {
        this.ldapCustomService = ldapCustomService;
        this.ldapUserRepository = ldapUserRepository;
        this.ldapOrganizationRepository = ldapOrganizationRepository;
        this.storageUtil = storageUtil;
    }

    /**
     * Save organization.
     *
     * @param organization     - organization object
     * @param hospitalSealFile - seal file
     * @return organization object
     */
    @Override
    public Organization saveOrganization(final Organization organization, final MultipartFile hospitalSealFile) {
        String filePath = null;
        if (!ObjectUtils.isEmpty(hospitalSealFile)) {
            filePath = uploadFile(organization, hospitalSealFile, "SEAL");
        }
        LdapOrganization ldapOrganization = ldapMapper.constructLdapOrganizationFromOrganization(organization);
        ldapOrganization.setSealFilePath(filePath);
        ldapOrganization = ldapOrganizationRepository.save(ldapOrganization);
        organization.setId(ldapOrganization.getDn().toString());
        return organization;
    }

    /**
     * Update organization.
     *
     * @param organization     - organization object
     * @param hospitalLogoFile - logo file
     * @return organization object
     */
    @Override
    public Organization updateOrganization(final Organization organization, final MultipartFile hospitalLogoFile) {
        if (!ObjectUtils.isEmpty(hospitalLogoFile)) {
            final String filePath = uploadFile(organization, hospitalLogoFile, "LOGO");
            LdapOrganization ldapOrganization = ldapMapper.constructLdapOrganizationFromOrganization(organization);
            ldapOrganization.setLogoFilePath(filePath);
            ldapOrganization = ldapOrganizationRepository.save(ldapOrganization);
            organization.setId(ldapOrganization.getDn().toString());
        }
        return organization;
    }

    /**
     * Upload user sign.
     *
     * @param organization     - organization
     * @param file             -  file
     * @return file path
     */
    private String uploadFile(final Organization organization, final MultipartFile file,
                              final String identifier) {
        final String bucketName = AuthServerUtil.HOSPITAL;
        final String fileName;
        if(!ObjectUtils.isEmpty(organization.getSealFilePath())) {
            fileName = AuthServerUtil.constructFileStoragePath(organization.getSealFilePath(),
                organization.getName() + CommonConstants.UNDERSCORE + identifier);
        } else {
            fileName = AuthServerUtil.constructFileStoragePath(organization.getLogoFilePath(),
                organization.getName() + CommonConstants.UNDERSCORE + identifier);
        }
        final String filePath = bucketName + CommonConstants.SLASH + fileName;
        storageUtil.uploadFile(file, bucketName, fileName, file.getContentType(), null);
        return filePath;
    }

    /**
     * Get active organizations.
     *
     * @return list of active organizations
     */
    public List<Organization> getActiveOrganizations() {
        final List<Organization> organizationList = new ArrayList<>();
        final List<LdapOrganization> ldapOrganizationList = ldapCustomService.getOrganizationsByUser(null);
        ldapOrganizationList.forEach(ldapOrganization -> {
            final Organization organization = ldapMapper.constructOrganizationFromLdapOrganization(ldapOrganization);
            organizationList.add(organization);
        });
        return organizationList;
    }

    /**
     * Get list of organizations by user name.
     *
     * @param userName - user name
     * @return list of organization
     */
    @Override
    public List<Organization> getOrganizationsByUserName(final String userName)
        throws UsernameNotFoundException, ObjectNotFoundException {
        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
        if (ObjectUtils.isEmpty(ldapUser)) {
            ExceptionUtil.throwObjectNotFoundException(
                String.format(ExceptionConstants.NO_USER_FOUND_WITH_GIVEN_NAME, userName));
        }
        final Boolean isUserActive = Boolean.valueOf(ldapUser.getActive());
        if (!isUserActive) {
            throw new ObjectNotFoundException(ExceptionConstants.USER_IS_NOT_ACTIVE);
        }
        final List<Organization> organizationList = new ArrayList<>();
        final List<LdapOrganization> ldapOrganizationList = ldapCustomService
            .getOrganizationsByUser(String.valueOf(ldapUser.getId()));
        ldapOrganizationList.forEach(ldapOrganization -> {
            final Organization organization = ldapMapper.constructOrganizationFromLdapOrganization(ldapOrganization);
            organizationList.add(organization);
        });
        return organizationList;
    }

    /**
     * Get organization by name.
     *
     * @param organizationName - organization name
     * @return Organization
     */
    @Override
    public Organization getOrganizationByName(final String organizationName) {
        final LdapOrganization ldapOrganization = ldapOrganizationRepository.findByName(organizationName);
        return ldapMapper.constructOrganizationFromLdapOrganization(ldapOrganization);
    }

    /**
     * Get all organizations.
     *
     * @param pageable - pageable object
     * @return organization list
     */
    @Override
    public Page<Organization> getAllOrganizations(final Pageable pageable) {
        final List<Organization> organizationList = new ArrayList<>();
        final Iterable<LdapOrganization> ldapOrganizationList = ldapOrganizationRepository.findAll();
        ldapOrganizationList.forEach(ldapOrganization -> {
            final Organization organization = ldapMapper.constructOrganizationFromLdapOrganization(ldapOrganization);
            organizationList.add(organization);
        });
        return PaginationUtil.doCustomPaging(pageable, organizationList);
    }
}
