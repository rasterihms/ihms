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

import in.raster.ihms.authserver.cloud.HisFeign;
import in.raster.ihms.authserver.domain.custom.*;
import in.raster.ihms.authserver.ldap.domain.*;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.authserver.ldap.repository.*;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.UserDtoCustomService;
import in.raster.ihms.authserver.service.dto.*;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.ExceptionConstants;
import in.raster.ihms.authserver.util.ExceptionUtil;
import in.raster.ihms.authserver.util.StorageUtil;
import in.raster.ihms.commons.security.JwtUser;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.commons.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Custom service implementation to manage user related functions.
 */
@Service
@Transactional
public class UserDtoCustomServiceImpl implements UserDtoCustomService {

    private static final Logger logger = LoggerFactory.getLogger(UserDtoCustomServiceImpl.class);
    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;

    private final LdapCustomService ldapCustomService;
    private final LdapApplicationRepository ldapApplicationRepository;
    private final LdapUserRepository ldapUserRepository;
    private final LdapOrganizationRepository ldapOrganizationRepository;
    private final StorageUtil storageUtil;
    private final LdapGroupRepository ldapGroupRepository;
    private final HisFeign hisFeign;
    private final LdapRoleRepository ldapRoleRepository;

    public UserDtoCustomServiceImpl(LdapCustomService ldapCustomService,
                                    LdapApplicationRepository ldapApplicationRepository,
                                    LdapUserRepository ldapUserRepository, LdapOrganizationRepository ldapOrganizationRepository,
                                    StorageUtil storageUtil, LdapGroupRepository ldapGroupRepository,
                                    HisFeign hisFeign, LdapRoleRepository ldapRoleRepository) {
        this.ldapCustomService = ldapCustomService;
        this.ldapApplicationRepository = ldapApplicationRepository;
        this.ldapUserRepository = ldapUserRepository;
        this.ldapOrganizationRepository = ldapOrganizationRepository;
        this.storageUtil = storageUtil;
        this.ldapGroupRepository = ldapGroupRepository;
        this.hisFeign = hisFeign;
        this.ldapRoleRepository = ldapRoleRepository;
    }

    /**
     * Todo - this method is only for reference, will be removed in future.
     * <p>
     * Get user details.
     *
     * @param request - http servlet request
     * @return - UserDetailDto
     */
    private UserDetailDto getUserDetails(final HttpServletRequest request) {
        final UserDetailDto userDetailDto = new UserDetailDto();
        final JwtUser jwtUser = AuthServerUtil.getUser();
        final List<BranchDto> branchDtoList = loadUserBranchesAndApplications(request);
        final List<ApplicationDto> applicationDtoList = loadApplicationDetails(branchDtoList, request);
        userDetailDto.setUser(jwtUser);
        userDetailDto.setBranchDtoList(branchDtoList);
        userDetailDto.setApplicationDtoList(applicationDtoList);
        return userDetailDto;
    }

    /**
     * Todo - this method is only for reference, will be removed in future.
     * <p>
     * Load branches and applications by user.
     *
     * @param request - http servlet request
     * @return list of branch dto
     */
    private List<BranchDto> loadUserBranchesAndApplications(final HttpServletRequest request) {
        final List<BranchDto> branchDtoList = new ArrayList<>();
//        final List<Branch> branchList = getBranchesByUser();
//        branchList.forEach(branch -> {
//            final BranchDto branchDto = new BranchDto();
//            final List<Application> applicationList = getApplicationsByUserAndBranch(request);
//            branchDto.setBranch(branch);
//            branchDto.setApplicationList(applicationList);
//            branchDtoList.add(branchDto);
//        });
        return branchDtoList;
    }

    /**
     * Todo - this method is only for reference, will be removed in future.
     * <p>
     * Load application details by user.
     *
     * @param branchDtoList - branch list
     * @param request       - http servlet request
     * @return list of application dto
     */
    private List<ApplicationDto> loadApplicationDetails(final List<BranchDto> branchDtoList,
                                                        final HttpServletRequest request) {
        final List<ApplicationDto> applicationDtoList = new ArrayList<>();
//        branchDtoList.forEach(branchDto -> {
//            branchDto.getApplicationList().forEach(application -> {
//                final ApplicationDto applicationDto = new ApplicationDto();
//                final List<Role> roleList = getRolesByUserBranchAndApplication(branchDto.getBranch().getName(),
//                    application.getName(), request);
//                final List<Menu> menuList = getMenusByUserRoles(roleList, application.getName());
//                final List<MenuDto> menuDtoList = constructMenuDtoList(menuList);
//                menuDtoList.sort(Comparator.comparing(menuDto -> menuDto.getMenu().getOrder()));
//                final List<Resource> resourceList = getResourceByUserRoles(roleList, application.getName());
//                applicationDto.setApplication(application);
//                applicationDto.setRoleList(roleList);
//                applicationDto.setMenuDtoList(menuDtoList);
//                applicationDto.setResourceList(resourceList);
//                applicationDtoList.add(applicationDto);
//            });
//        });
        return applicationDtoList;
    }

    /**
     * Create user in ldap.
     *
     * @param userDto      - user dto object
     * @param userSignFile - user sign file
     * @return saved user
     */
    @Override
    public UserDto createUser(final UserDto userDto, final MultipartFile userSignFile) {
        final User user = userDto.getUser();
        AuthServerUtil.validateUserLocation(user);
        final String loggedInUserId = AuthServerUtil.getUser().getId();
        user.setCreatedBy(loggedInUserId);
        user.setModifiedBy(loggedInUserId);
        user.setCreatedDate(Instant.now());
        user.setModifiedDate(Instant.now());
        String filePath = null;
        if (!ObjectUtils.isEmpty(userSignFile)) {
            filePath = uploadUserSign(user, userSignFile);
        }
        user.setSignFilePath(filePath);
        final String ldapUserId = ldapCustomService.createOrganizationUser(user);
        user.setId(ldapUserId);
        addUserToGroups(userDto, user);
        return userDto;
    }

    /**
     * Upload user sign.
     *
     * @param user         - user
     * @param userSignFile - sign file
     * @return file path
     */
    private String uploadUserSign(final User user, final MultipartFile userSignFile) {
        final String bucketName = AuthServerUtil.USER;
        final String fileName = AuthServerUtil.constructFileStoragePath(user.getSignFilePath(), user.getUserName());
        final String filePath = bucketName + CommonConstants.SLASH + fileName;
        storageUtil.uploadFile(userSignFile, bucketName, fileName, userSignFile.getContentType(), null);
        return filePath;
    }

    /**
     * Add user to groups.
     *
     * @param userDto - user dto
     * @param user    - user object
     */
    private void addUserToGroups(final UserDto userDto, final User user) {
        userDto.getUserGroupDtoList().forEach(userGroupDto -> {
            ldapCustomService.addMemberToGroup(user.getId(), userGroupDto.getOrganization().getId());
            ldapCustomService.addMemberToGroup(user.getId(), userGroupDto.getBranch().getId());
            ldapCustomService.addMemberToGroup(user.getId(), userGroupDto.getApplication().getId());
            ldapCustomService.addMemberToGroup(user.getId(), userGroupDto.getRole().getId());
            ldapCustomService.addMemberToGroup(userGroupDto.getBranch().getId(), userGroupDto.getRole().getId());
        });
    }

    /**
     * Update user membership in ldap.
     *
     * @param userDto      - user dto object
     * @param userSignFile - user sign file
     * @return saved user dto
     */
    @Override
    public UserDto updateUser(final UserDto userDto, final MultipartFile userSignFile) {
        final User user = userDto.getUser();
        AuthServerUtil.validateUserLocation(user);
        user.setModifiedBy(AuthServerUtil.getUser().getId());
        user.setModifiedDate(Instant.now());
        String filePath = user.getSignFilePath();
        if (!ObjectUtils.isEmpty(userSignFile)) {
            filePath = uploadUserSign(user, userSignFile);
        }
        user.setSignFilePath(filePath);
        ldapCustomService.modifyUserAttributes(user);
        final List<LdapOrganization> ldapOrganizationList = ldapCustomService.getOrganizationsByUser(user.getId());
        ldapOrganizationList.forEach(ldapOrganization -> {
            final String organizationId = String.valueOf(ldapOrganization.getDn());
            final List<LdapGroup> ldapGroups = ldapCustomService.getBranchesByUserDn(user.getId(), organizationId);
            ldapGroups.forEach(ldapGroup -> {
                final String branchId = String.valueOf(ldapGroup.getDn());
                final String organizationName = organizationId.substring(organizationId.indexOf(CommonConstants.EQUALS)
                    + CommonConstants.ONE, organizationId.length());
                final List<LdapApplication> ldapApplicationList =
                    ldapCustomService.getApplicationsByUserAndBranch(user.getId(), ldapGroup.getName(), organizationName);
                ldapApplicationList.forEach(ldapApplication -> {
                    final List<LdapRole> ldapRoleList =
                        ldapCustomService.getRolesByUserBranchAndApplication(user.getId(), ldapGroup.getName(),
                            organizationName, String.valueOf(ldapApplication.getName()));
                    ldapRoleList.forEach(ldapRole -> {
                        ldapCustomService.removeMemberFromGroup(user.getId(), String.valueOf(ldapRole.getDn()));
                    });
                    ldapCustomService.removeMemberFromGroup(user.getId(), String.valueOf(ldapApplication.getDn()));
                });
                ldapCustomService.removeMemberFromGroup(user.getId(), branchId);
            });
            ldapCustomService.removeMemberFromGroup(user.getId(), organizationId);
        });
        addUserToGroups(userDto, user);
        return userDto;
    }

    /**
     * Get user dto list.
     *
     * @param userName - user name
     * @param pageable - pageable object
     * @return list of user dto
     */
    @Override
    public Page<UserDto> getUserDtoList(final String userName, final Pageable pageable) {
        final List<UserDto> userDtoList = new ArrayList<>();
        final List<LdapUser> ldapUserList = ldapCustomService.getUsers(userName, null, null, null);
        final Comparator<LdapUser> userNameComparator = PaginationUtil.getComparator(LdapUser::getUsername, pageable);
        final Page<LdapUser> ldapUserPage = PaginationUtil.doCustomPaging(pageable, ldapUserList, userNameComparator);
        ldapUserPage.getContent().forEach(ldapUser -> {
            final UserDto userDto = new UserDto();
            final User user = ldapMapper.constructUserFromLdapUser(ldapUser);
            final List<UserGroupDto> userGroupDtoList = constructUserGroupDtoList(user);
            userDto.setUser(user);
            userDto.setUserGroupDtoList(userGroupDtoList);
            userDtoList.add(userDto);
        });
        return PaginationUtil.convertListToPageImpl(userDtoList, pageable, ldapUserPage.getTotalElements());
    }

    /**
     * Get user dto list.
     *
     * @param userName          - user name
     * @param organizationName  - organization name
     * @param branchName        - branch name
     * @param applicationName   - application name
     * @param roleName          - role name
     * @param departmentName    - department name
     * @param locationId        - location id
     * @param userType          - user type
     * @param pageable          - pageable object
     * @param active            - active
     * @return list of user dto
     */
    @Override
    public Page<UserDto> getUserDtosByFilter(final String userName, final String organizationName,
                                             final String branchName, final String applicationName,
                                             final String roleName, final String departmentName, final String locationId,
                                             final UserType userType, final Pageable pageable, final String active) {
        List<LdapUser> ldapUserList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(organizationName) && ObjectUtils.isEmpty(branchName)
            && ObjectUtils.isEmpty(applicationName) && ObjectUtils.isEmpty(roleName)) {
            final LdapOrganization ldapOrganization = ldapOrganizationRepository.findByName(organizationName);
            constructLdapUserList(ldapOrganization.getMembers(), ldapUserList);
        } else if (!ObjectUtils.isEmpty(organizationName) && !ObjectUtils.isEmpty(branchName)
            && ObjectUtils.isEmpty(applicationName) && ObjectUtils.isEmpty(roleName)) {
            final List<LdapGroup> ldapGroups = ldapCustomService.getBranchesByUserAndOrganization(null, organizationName);
            final LdapGroup ldapGroup = ldapGroups.stream()
                .filter(ldapGroup1 -> ldapGroup1.getName().equalsIgnoreCase(branchName))
                .collect(Collectors.toList()).get(CommonConstants.ZERO);
            constructLdapUserList(ldapGroup.getMembers(), ldapUserList);
        } else if (!ObjectUtils.isEmpty(organizationName) && !ObjectUtils.isEmpty(branchName)
            && !ObjectUtils.isEmpty(applicationName) && ObjectUtils.isEmpty(roleName)) {
            final List<LdapApplication> ldapApplications = ldapCustomService
                .getApplicationsByUserAndBranch(null, branchName, organizationName);
            final LdapApplication ldapApplication = ldapApplications.stream()
                .filter(ldapApplication1 -> ldapApplication1.getName().equalsIgnoreCase(applicationName))
                .collect(Collectors.toList()).get(CommonConstants.ZERO);
            constructLdapUserList(ldapApplication.getMembers(), ldapUserList);
        } else if (!ObjectUtils.isEmpty(organizationName) && !ObjectUtils.isEmpty(branchName)
            && !ObjectUtils.isEmpty(applicationName) && !ObjectUtils.isEmpty(roleName)) {
            final List<LdapRole> ldapRoles = ldapCustomService
                .getRolesByUserBranchAndApplication(null, branchName, organizationName, applicationName);
            final LdapRole ldapRole = ldapRoles.stream()
                .filter(ldapRole1 -> ldapRole1.getName().equalsIgnoreCase(roleName))
                .collect(Collectors.toList()).get(CommonConstants.ZERO);
            constructLdapUserList(ldapRole.getMembers(), ldapUserList);
        }
        if (!CollectionUtils.isEmpty(ldapUserList)) {
            if (!ObjectUtils.isEmpty(userName)) {
                ldapUserList = ldapUserList.parallelStream()
                    .filter(ldapUser -> !ObjectUtils.isEmpty(ldapUser.getUsername()) &&
                        (ldapUser.getUsername().toLowerCase().contains(userName.toLowerCase()) ||
                            ldapUser.getSurname().toLowerCase().contains(userName.toLowerCase())))
                    .collect(Collectors.toList());
            }
            if (!ObjectUtils.isEmpty(departmentName)) {
                ldapUserList = ldapUserList.parallelStream()
                    .filter(ldapUser -> !ObjectUtils.isEmpty(ldapUser.getDepartment()) &&
                        ldapUser.getDepartment().toLowerCase().contains(departmentName.toLowerCase()))
                    .collect(Collectors.toList());
            }
            if (!ObjectUtils.isEmpty(userType)) {
                ldapUserList = ldapUserList.parallelStream()
                    .filter(ldapUser -> !ObjectUtils.isEmpty(ldapUser.getUserType()) &&
                        ldapUser.getUserType().equalsIgnoreCase(userType.toString()))
                    .collect(Collectors.toList());
            }
            if (!ObjectUtils.isEmpty(active)) {
                ldapUserList = ldapUserList.parallelStream()
                    .filter(ldapUser -> !ObjectUtils.isEmpty(ldapUser.getActive()) &&
                        ldapUser.getActive().toLowerCase().equalsIgnoreCase(active.toLowerCase()))
                    .collect(Collectors.toList());
            }
            return constructUserPage(ldapUserList, pageable);
        } else {
            final List<LdapUser> ldapUserList1 = ldapCustomService.getUsers(userName, departmentName, userType, active);
            return constructUserPage(ldapUserList1, pageable);
        }
    }

    /**
     * Construct user page.
     *
     * @param ldapUserList - ldap user list
     * @param pageable     - pageable
     * @return list of user dto
     */
    private Page<UserDto> constructUserPage(final List<LdapUser> ldapUserList, final Pageable pageable) {
        final List<UserDto> userDtoList = new ArrayList<>();
        final Comparator<LdapUser> userNameComparator = PaginationUtil.getComparator(LdapUser::getUsername, pageable);
        final Page<LdapUser> ldapUserPage = PaginationUtil.doCustomPaging(pageable, ldapUserList, userNameComparator);
        ldapUserPage.getContent().forEach(ldapUser -> {
            final UserDto userDto = new UserDto();
            final User user = ldapMapper.constructUserFromLdapUser(ldapUser);
            final List<UserGroupDto> userGroupDtoList = constructUserGroupDtoList(user);
            if(!ObjectUtils.isEmpty(user.getLocationIds())) {
                final List<String> locationNames = hisFeign.getLocationsById(user.getLocationIds());
                userDto.setLocationNames(locationNames);
            }
            userDto.setUser(user);
            userDto.setUserGroupDtoList(userGroupDtoList);
            userDtoList.add(userDto);
        });
        return PaginationUtil.convertListToPageImpl(userDtoList, pageable, ldapUserPage.getTotalElements());
    }

    /**
     * Get users by role.
     *
     * @param members - members
     */
    private void constructLdapUserList(final Set<Name> members, final List<LdapUser> ldapUserList) {
        members.parallelStream().forEach(member -> {
            final String[] memberArray = member.toString().split(CommonConstants.COMMA_SEPARATOR);
            if (!ObjectUtils.isEmpty(memberArray)) {
                final String userName = memberArray[CommonConstants.ZERO].substring(CommonConstants.FOUR).trim();
                if (!ObjectUtils.isEmpty(userName)) {
                    final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
                    if (!ObjectUtils.isEmpty(ldapUser) && !ldapUserList.contains(ldapUser)) {
                        ldapUserList.add(ldapUser);
                    }
                }
            }
        });
    }

    /**
     * Get user dto list.
     *
     * @param userName - user name
     * @param pageable - pageable object
     * @return list of user dto
     */
    @Override
    public UserDto getUserDto(final String userName, final Pageable pageable) {
        final UserDto userDto = new UserDto();
        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
        final User user = ldapMapper.constructUserFromLdapUser(ldapUser);
        final List<UserGroupDto> userGroupDtoList = constructUserGroupDtoList(user);
        userDto.setUser(user);
        userDto.setUserGroupDtoList(userGroupDtoList);
        return userDto;
    }

    /**
     * Construct user group dto list.
     *
     * @param user - user object
     * @return list of user group dtos
     */
    private List<UserGroupDto> constructUserGroupDtoList(final User user) {
        final List<UserGroupDto> userGroupDtoList = new ArrayList<>();
        final List<LdapOrganization> ldapOrganizationList = ldapCustomService.getOrganizationsByUser(user.getId());
        ldapOrganizationList.forEach(ldapOrganization -> {
            final Organization organization = ldapMapper.constructOrganizationFromLdapOrganization(ldapOrganization);
            final List<LdapGroup> ldapGroups = ldapCustomService.getBranchesByUserDn(user.getId(), organization.getId());
            ldapGroups.forEach(ldapGroup -> {
                final Branch branch = ldapMapper.constructBranchFromLdapBranch(ldapGroup);
                final List<LdapApplication> ldapApplicationList = ldapCustomService
                    .getApplicationsByUserAndBranch(user.getId(), branch.getName(), organization.getName());
                ldapApplicationList.forEach(ldapApplication -> {
                    final Application application = ldapMapper.constructApplicationFromLdapApplication(ldapApplication);
                    final List<LdapRole> ldapRoleList = ldapCustomService.getRolesByUserBranchAndApplication(user.getId(),
                        branch.getName(), organization.getName(), application.getName());
                    final List<Role> roleList = ldapMapper.constructRoleListFromLdapRoles(ldapRoleList);
                    roleList.forEach(role -> {
                        final UserGroupDto userGroupDto = new UserGroupDto();
                        userGroupDto.setOrganization(organization);
                        userGroupDto.setBranch(branch);
                        userGroupDto.setApplication(application);
                        userGroupDto.setRole(role);
                        userGroupDtoList.add(userGroupDto);
                    });
                });
            });
        });
        return userGroupDtoList;
    }
}
