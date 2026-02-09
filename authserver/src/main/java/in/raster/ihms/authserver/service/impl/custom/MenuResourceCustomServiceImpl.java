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

import in.raster.ihms.authserver.domain.custom.Menu;
import in.raster.ihms.authserver.domain.custom.Resource;
import in.raster.ihms.authserver.domain.custom.Role;
import in.raster.ihms.authserver.ldap.domain.LdapMenu;
import in.raster.ihms.authserver.ldap.domain.LdapResource;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.MenuResourceCustomService;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.commons.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.*;

/**
 * Custom service implementation to manipulate menu resource.
 */
@Service
@Transactional
public class MenuResourceCustomServiceImpl implements MenuResourceCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;

    public MenuResourceCustomServiceImpl(LdapCustomService ldapCustomService) {
        this.ldapCustomService = ldapCustomService;
    }

    /**
     * Get all resources by application.
     *
     * @param applicationName - application name
     * @param resourceName    - resource name to search
     * @param menuName        - menu name
     * @param pageable        - pageable object
     * @return list of resources
     */
    @Override
    public Page<Resource> getApplicationResources(final String applicationName, final String resourceName,
                                                  final String menuName, final Pageable pageable) {
        final List<Resource> resourceList = new ArrayList<>();
        final List<LdapResource> ldapResourceList =
            ldapCustomService.getApplicationResources(applicationName, resourceName);
        ldapResourceList.forEach(ldapResource -> {
            if (!ObjectUtils.isEmpty(menuName)) {
                final String resourceMenuName = AuthServerUtil
                    .getMenuNameFromResourceId(String.valueOf(ldapResource.getDn()));
                if (resourceMenuName.equalsIgnoreCase(menuName)) {
                    final Resource resource = ldapMapper.constructResourceFromLdapResource(ldapResource);
                    resourceList.add(resource);
                }
            } else {
                final Resource resource = ldapMapper.constructResourceFromLdapResource(ldapResource);
                resourceList.add(resource);
            }
        });
        return PaginationUtil.doCustomPaging(pageable, resourceList);
    }

    /**
     * Save resource.
     *
     * @param resource - resource object
     * @return saved resource
     */
    @Override
    public Resource saveResource(final Resource resource) {
        final String userId = AuthServerUtil.getUser().getId();
        resource.setCreatedBy(userId);
        resource.setModifiedBy(userId);
        resource.setCreatedDate(Instant.now());
        resource.setModifiedDate(Instant.now());
        final String resourceId = ldapCustomService.createApplicationResource(resource);
        resource.setId(resourceId);
        return resource;
    }

    /**
     * Modify resource attributes.
     *
     * @param resource - resource object
     */
    @Override
    public Resource modifyResourceAttributes(final Resource resource) {
        resource.setModifiedBy(AuthServerUtil.getUser().getId());
        resource.setModifiedDate(Instant.now());
        ldapCustomService.modifyResourceAttributes(resource);
        return resource;
    }

    /**
     * Delete resource by given dn.
     *
     * @param resourceName    - resource name
     * @param applicationName - application name
     * @param menuName        - menu name
     */
    @Override
    public void deleteApplicationResource(final String resourceName, final String applicationName,
                                          final String menuName) {
        ldapCustomService.deleteApplicationResource(resourceName, applicationName, menuName);
    }

    /**
     * Get resources by user roles and application.
     *
     * @param roleList        - role list
     * @param applicationName - application name
     * @return list of resources
     */
    @Override
    public List<Resource> getResourceByUserRoles(final List<Role> roleList, final String applicationName) {
        final Set<Resource> resourceSet = new HashSet<>();
        roleList.forEach(role -> {
            final List<LdapResource> ldapResourceList =
                ldapCustomService.getResourcesByUserRole(role.getId(), applicationName);
            ldapResourceList.forEach(ldapResource -> {
                final Resource resource = ldapMapper.constructResourceFromLdapResource(ldapResource);
                resourceSet.add(resource);
            });
        });
        return new ArrayList<>(resourceSet);
    }
}
