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
import in.raster.ihms.authserver.domain.custom.Menu;
import in.raster.ihms.authserver.domain.custom.Resource;
import in.raster.ihms.authserver.domain.custom.Role;
import in.raster.ihms.authserver.ldap.domain.LdapApplication;
import in.raster.ihms.authserver.ldap.repository.LdapApplicationRepository;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.*;
import in.raster.ihms.authserver.service.dto.ApplicationDto;
import in.raster.ihms.authserver.service.dto.MenuDto;
import in.raster.ihms.authserver.util.TokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

/**
 * Custom service implementation to manage application dtos.
 */
@Service
@Transactional
public class ApplicationDtoCustomServiceImpl implements ApplicationDtoCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapApplicationRepository ldapApplicationRepository;
    private final RoleCustomService roleCustomService;
    private final MenuCustomService menuCustomService;
    private final MenuDtoCustomService menuDtoCustomService;
    private final MenuResourceCustomService menuResourceCustomService;
    private final TokenUtil tokenUtil;

    public ApplicationDtoCustomServiceImpl(LdapApplicationRepository ldapApplicationRepository,
                                           RoleCustomService roleCustomService,
                                           MenuCustomService menuCustomService,
                                           MenuDtoCustomService menuDtoCustomService,
                                           MenuResourceCustomService menuResourceCustomService,
                                           TokenUtil tokenUtil) {
        this.ldapApplicationRepository = ldapApplicationRepository;
        this.roleCustomService = roleCustomService;
        this.menuCustomService = menuCustomService;
        this.menuDtoCustomService = menuDtoCustomService;
        this.menuResourceCustomService = menuResourceCustomService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * Get application details by user, branch and application.
     *
     * @param applicationName - application name
     * @param request         - http servlet request
     * @return application dto
     */
    @Override
    public ApplicationDto getApplicationDetails(final String applicationName, final HttpServletRequest request) {
        final ApplicationDto applicationDto = new ApplicationDto();
        final LdapApplication ldapApplication = ldapApplicationRepository.findByName(applicationName);
        final Application application = ldapMapper.constructApplicationFromLdapApplication(ldapApplication);
        final List<Role> roleList = roleCustomService.getAllRolesByUserBranchAndApplication(tokenUtil.getBranchIdFromToken(request),
            applicationName, request);
        final List<Menu> menuList = menuCustomService.getMenusByUserRoles(roleList, applicationName);
        final List<MenuDto> menuDtoList = menuDtoCustomService.constructMenuDtoList(menuList);
        menuDtoList.sort(Comparator.comparing(menuDto -> menuDto.getMenu().getOrder()));
        final List<Resource> resourceList = menuResourceCustomService.getResourceByUserRoles(roleList, applicationName);
        applicationDto.setApplication(application);
        applicationDto.setRoleList(roleList);
        applicationDto.setMenuDtoList(menuDtoList);
        applicationDto.setResourceList(resourceList);
        return applicationDto;
    }
}
