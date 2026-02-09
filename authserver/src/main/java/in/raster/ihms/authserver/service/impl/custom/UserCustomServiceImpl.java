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

import in.raster.ihms.authserver.domain.custom.User;
import in.raster.ihms.authserver.ldap.domain.LdapApplication;
import in.raster.ihms.authserver.ldap.domain.LdapGroup;
import in.raster.ihms.authserver.ldap.domain.LdapDepartment;
import in.raster.ihms.authserver.ldap.domain.LdapRole;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.authserver.ldap.repository.*;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.ldap.util.ApacheDSLdapDirectories;
import in.raster.ihms.authserver.ldap.util.LdapUtil;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.UserCustomService;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.ExceptionConstants;
import in.raster.ihms.authserver.util.ExceptionUtil;
import in.raster.ihms.authserver.util.StorageUtil;
import in.raster.ihms.commons.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Custom service implementation to manage user related functions.
 */
@Service
@Transactional
public class UserCustomServiceImpl implements UserCustomService {

    @Autowired
    private LdapTemplate ldapTemplate;

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;
    private final LdapUtil ldapUtil;
    private final LdapApplicationRepository ldapApplicationRepository;
    private final LdapUserRepository ldapUserRepository;
    private final LdapGroupRepository ldapGroupRepository;
    private final StorageUtil storageUtil;
    private final LdapDepartmentRepository ldapDepartmentRepository;
    private final LdapRoleRepository ldapRoleRepository;

    public UserCustomServiceImpl(LdapCustomService ldapCustomService,
                                 LdapUtil ldapUtil,
                                 LdapApplicationRepository ldapApplicationRepository,
                                 LdapUserRepository ldapUserRepository, LdapGroupRepository ldapGroupRepository,
                                 StorageUtil storageUtil, LdapDepartmentRepository ldapDepartmentRepository, LdapRoleRepository ldapRoleRepository) {
        this.ldapCustomService = ldapCustomService;
        this.ldapUtil = ldapUtil;
        this.ldapApplicationRepository = ldapApplicationRepository;
        this.ldapUserRepository = ldapUserRepository;
        this.ldapGroupRepository = ldapGroupRepository;
        this.storageUtil = storageUtil;
        this.ldapDepartmentRepository = ldapDepartmentRepository;
        this.ldapRoleRepository = ldapRoleRepository;
    }

    /**
     * Get all users.
     *
     * @return user list
     */
    @Override
    public List<User> getUsers() {
        final List<LdapUser> ldapUserList = ldapCustomService.getActiveUsers();
        return ldapMapper.constructUserListFromLdapUsers(ldapUserList);
    }

    /**
     * Get credit authorized users.
     *
     * @return user list
     */
    @Override
    public List<User> getCreditAuthorizedUsers() {
        final List<LdapUser> ldapUserList = ldapCustomService.getCreditAuthorizedUsers();
        return ldapMapper.constructUserListFromLdapUsers(ldapUserList);
    }

    /**
     * Get users by type.
     *
     * @return user list
     */
    @Override
    public List<User> getUsersByType(final UserType userType) {
        final List<LdapUser> ldapUserList = ldapCustomService.getUsersByType(userType);
        return ldapMapper.constructUserListFromLdapUsers(ldapUserList);
    }

    /**
     * Get users by types.
     *
     * @return user list
     */
    @Override
    public List<User> getUsersByTypes(final String userType) {
        final List<UserType> userTypeList = AuthServerUtil.convertUserTypeToListByComma(userType);
        final List<LdapUser> ldapUserList = new ArrayList<>();
        if (!CollectionUtils.isEmpty((userTypeList))) {
            userTypeList.forEach(userType1 -> {
               final List<LdapUser> ldapUsers = ldapCustomService.getUsersByType(userType1);
                ldapUserList.addAll(ldapUsers);
            });
        }
        return ldapMapper.constructUserListFromLdapUsers(ldapUserList);
    }


    /**
     * Get users by role.
     *
     * @param applicationName - application name
     * @param roleName        - role name
     * @return list of users
     */
    @Override
    public List<User> getUsersByRole(final String applicationName, final String roleName) {
        final List<LdapRole> ldapRoleList = ldapCustomService
            .getApplicationRoles(applicationName, roleName, null, null);
        final List<User> filteredUsers = new ArrayList<>();
        ldapRoleList.forEach(ldapRole -> {
            final Set<Name> roleMembers = ldapRole.getMembers();
            roleMembers.forEach(member -> {
                final String[] memberArray = member.toString().split(CommonConstants.COMMA_SEPARATOR);
                if (!ObjectUtils.isEmpty(memberArray)) {
                    final String userName = memberArray[CommonConstants.ZERO].substring(CommonConstants.FOUR).trim();
                    if (!ObjectUtils.isEmpty(userName)) {
                        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
                        if (!ObjectUtils.isEmpty(ldapUser)) {
                            final User user = ldapMapper.constructUserFromLdapUser(ldapUser);
                            filteredUsers.add(user);
                        }
                    }
                }
            });
        });
        return filteredUsers;
    }

    /**
     * To get a list of user names in an application(toLoweCase added to make filter case sensitive)
     *
     * @param applicationName : Name of the application
     * @param userName        : Name of the user
     * @return List of User Names
     */
    @Override
    public List<String> getUserNamesByApplication(final String applicationName,
                                                  final String userName) {
        final LdapApplication ldapApplication = ldapApplicationRepository.findByName(applicationName);
        final List<Name> membersSet = new ArrayList<>(ldapApplication.getMembers());
        final List<Name> filterMembers = membersSet.stream()
            .filter(member -> member.toString().toLowerCase().contains(ApacheDSLdapDirectories.UID.getAttribute().toLowerCase()))
            .collect(Collectors.toList());
        final List<String> nameList = new ArrayList<>(filterMembers.size());
        filterMembers.forEach(name -> nameList.add(AuthServerUtil.getUserNameFromMemberId(name)));
        if (!ObjectUtils.isEmpty(userName)) {
            return nameList.stream().filter(name -> name.toLowerCase().contains(userName.toLowerCase())).collect(Collectors.toList());
        }
        return nameList;
    }

    /**
     * Get user by name.
     *
     * @param userName - user name
     * @return user object
     */
    @Override
    public User getUserByName(final String userName) {
        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
        if (ObjectUtils.isEmpty(ldapUser)) {
            ExceptionUtil.throwObjectNotFoundException(ExceptionConstants.USER_NOT_FOUND);
        }
        return constructUser(ldapUser);
    }

    /**
     * Get user display name  by user name.
     *
     * @param userName - user name
     * @return string
     */
    @Override
    public String getUserDisplayNameByName(final String userName) {
        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
        if (ObjectUtils.isEmpty(ldapUser)) {
            ExceptionUtil.throwObjectNotFoundException(ExceptionConstants.USER_NOT_FOUND);
        }
        return ldapUser.getSurname();
    }

    /**
     * Find by user name.
     *
     * @param userName - user name
     * @return User
     */
    @Override
    public User findUserByName(final String userName) {
        final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
        User user = null;
        if (!ObjectUtils.isEmpty(ldapUser)) {
            user = constructUser(ldapUser);
        }
        return user;
    }

    /**
     * Construct user from ldap user.
     *
     * @param ldapUser - ldap user
     * @return User
     */
    private User constructUser(final LdapUser ldapUser) {
        final User user = ldapMapper.constructUserFromLdapUser(ldapUser);
        if (!ObjectUtils.isEmpty(ldapUser.getDepartment())) {
            final StringBuilder departmentNameBuilder = new StringBuilder();
            final List<String> departmentList = Arrays.asList(ldapUser.getDepartment().split(CommonConstants.COMMA_SEPARATOR));
            departmentList.forEach(department -> {
                final LdapDepartment ldapDepartment = ldapDepartmentRepository.findByName(department);
                if (!ObjectUtils.isEmpty(ldapDepartment)) {
                    departmentNameBuilder.append(ldapDepartment.getDescription()).append(CommonConstants.COMMA_SEPARATOR);
                }
            });
            if (!ObjectUtils.isEmpty(departmentNameBuilder)) {
                String departmentNames = departmentNameBuilder.toString();
                departmentNames = departmentNames.substring(CommonConstants.ZERO, departmentNames.length() - CommonConstants.ONE);
                user.setDepartmentDisplayName(departmentNames);
            }
        }
        return user;
    }

    /**
     * Create user.
     *
     * @param user - user
     * @return user object
     */
    @Override
    public User createUser(final User user) {
        final String loggedInUserId = AuthServerUtil.getUser().getId();
        user.setPassword(ldapUtil.digestSHA(user.getPassword()));
        user.setCreatedBy(loggedInUserId);
        user.setModifiedBy(loggedInUserId);
        user.setCreatedDate(Instant.now());
        user.setModifiedDate(Instant.now());
        user.setCreditAmountLimit(0l);
        user.setDepartmentIds(null);
        user.setDoctorId(null);
        user.setCreditAuthorizedUser(false);
        user.setDoctor(false);
        ldapCustomService.createOrganizationUser(user);
        return user;
    }

    /**
     * Update user.
     *
     * @param user - user
     * @return user object
     */
    @Override
    public User updateUser(final User user) {
        AuthServerUtil.validateUserLocation(user);
        user.setModifiedBy(AuthServerUtil.getUser().getId());
        user.setModifiedDate(Instant.now());
        ldapCustomService.modifyUserAttributes(user);
        return user;
    }

    /**
     * Update user photo in ldap.
     *
     * @param user          - user object
     * @param userPhotoFile - user photo file
     * @return saved user
     */
    @Override
    public User updateUserPhoto(final User user, final MultipartFile userPhotoFile) {
        user.setModifiedBy(AuthServerUtil.getUser().getId());
        String filePath = user.getPhotoFilePath();
        if (!ObjectUtils.isEmpty(userPhotoFile)) {
            filePath = constructUserPhotoFilePath(user, userPhotoFile);
        }
        user.setPhotoFilePath(filePath);
        ldapCustomService.modifyUserAttributes(user);
        return user;
    }

    /**
     * Upload user sign.
     *
     * @param user         - user
     * @param userSignFile - sign file
     * @return file path
     */
    private String constructUserPhotoFilePath(final User user, final MultipartFile userSignFile) {
        final String bucketName = AuthServerUtil.USER;
        final String fileName = AuthServerUtil.constructFileStoragePath(user.getPhotoFilePath(), user.getUserName());
        final String filePath = bucketName + CommonConstants.SLASH + fileName;
        storageUtil.uploadFile(userSignFile, bucketName, fileName, userSignFile.getContentType(), null);
        return filePath;
    }

    /**
     * Update user password.
     *
     * @param user - user
     * @return user object
     */
    @Override
    public User updateUserPassword(final User user) {
        ldapCustomService.updateUserPassword(user);
        return user;
    }

    /**
     * Todo : This method is used in pharmacy, do not delete
     * To get user and authorities
     *
     * @return user and authorities
     */
    @Override
    public Map<String, String> getJwtUserNameAndAuthorities() {
        final Map<String, String> userDetails = new HashMap<>();
        String authority = "";
        for (GrantedAuthority grantedAuthority : AuthServerUtil.getUser().getAuthorities()) {
            authority = grantedAuthority.getAuthority() + ",";
        }
        userDetails.put("authorities", authority);
        userDetails.put("userName", AuthServerUtil.getUser().getUsername());
        return userDetails;
    }

    /**
     * Todo : This method is used in pharmacy, do not delete
     * Get user by employeeNumber
     *
     * @param employeeNumber - number of the employee
     * @return user object
     */
    @Override
    public User getUserByEmployeeNumber(final String employeeNumber) {
        final LdapUser ldapUser = ldapUserRepository.findByEmployeeNumber(employeeNumber);
        ExceptionUtil.isObjectEmpty(ldapUser, ExceptionConstants.USER_NOT_FOUND);
        return ldapMapper.constructUserFromLdapUser(ldapUser);
    }

    /**
     * To retrieve active list of users by name or employeeNumber
     *
     * @param searchText : Name or EmployeeNumber
     * @return List of users
     */
    @Override
    public List<User> getListOfUsersByNameOrEmployeeNumber(final String searchText) {
//        final List<LdapUser> ldapUserList = ldapUserRepository.findByCommonNameContainsIgnoreCaseOrEmployeeNumberContainsIgnoreCase(
//            searchText, searchText);
        final List<LdapUser> ldapUserList = ldapCustomService.getActiveUsersByNameOrEmployeeNumber(searchText);
        return ldapMapper.constructUserListFromLdapUsers(ldapUserList);
    }

    /**
     * To verify user password
     *
     * @param user : user
     * @return Boolean - correct/wrong
     */
    @Override
    public Boolean verifyUserPassword(@RequestBody final User user) {
        return ldapCustomService.authenticate(user.getUserName(), user.getPassword());
    }

    /**
     * Get users by branch and user type
     *
     * @param branchName - branch name
     * @param userType   - user type
     * @return list of users
     */
    @Override
    public List<User> getUsersByBranchAndType(final String branchName, final UserType userType) {
        final LdapGroup ldapGroup = ldapGroupRepository.findByName(branchName);
        final Set<Name> members = ldapGroup.getMembers();
        final List<User> userList = new ArrayList<>();
        members.forEach(member -> {
            final String[] memberArray = member.toString().split(CommonConstants.COMMA_SEPARATOR);
            if (!ObjectUtils.isEmpty(memberArray)) {
                final String userName = memberArray[CommonConstants.ZERO].substring(CommonConstants.FOUR).trim();
                if (!ObjectUtils.isEmpty(userName)) {
                    final LdapUser ldapUser = ldapUserRepository.findByUsername(userName);
                    if (!ObjectUtils.isEmpty(ldapUser) && (!ObjectUtils.isEmpty(ldapUser.getUserType()) &&
                        ldapUser.getUserType().equals(userType.toString()))) {
                        final User user = ldapMapper.constructUserFromLdapUser(ldapUser);
                        userList.add(user);
                    }
                }
            }
        });
        return userList;
    }

    /**
     * Get location mapped users.
     *
     * @return list of user
     */
    @Override
    public List<User> updateLocationMappedUsers() {
        final Iterable<LdapUser> ldapUsersIterable = ldapUserRepository.findAll();
        final List<LdapUser> ldapUsers = new ArrayList<>();
        ldapUsersIterable.forEach(ldapUsers::add);
        ldapUsers.forEach(ldapUser -> {
            final DirContextOperations context = ldapTemplate.lookupContext(String.valueOf(ldapUser.getId()));
            String locationIds = ldapUser.getLocationIds();
            if (!ObjectUtils.isEmpty(locationIds)) {
                String locationIdArr[] = locationIds.split(",");
                context.setAttributeValue(ApacheDSLdapDirectories.DEFAULT_LOCATION_ID.getAttribute(), locationIdArr[0]);
                locationIds = locationIds.substring(locationIds.indexOf(locationIdArr[0]) + 1, locationIds.length());
                context.setAttributeValue(ApacheDSLdapDirectories.LOCATION_IDS.getAttribute(), locationIds);
            }
            context.setAttributeValue("locationId", null);
            ldapTemplate.modifyAttributes(context);
        });
        return ldapMapper.constructUserListFromLdapUsers(ldapUsers);
    }

    /**
     * Get users by location id
     *
     * @param locationId - location id
     * @return list of users
     */
    @Override
    public List<User> getUsersByLocationId(final String locationId) {
        final List<LdapUser> ldapUserList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        ldapUserList.addAll(ldapUserRepository.findByDefaultLocationId(locationId));
        ldapUserList.addAll(ldapUserRepository.findByLocationIds(locationId));
        if (!CollectionUtils.isEmpty(ldapUserList)) {
            userList = ldapMapper.constructUserListFromLdapUsers(ldapUserList);
        }
        return userList;
    }

    /**
     * Update user type by role.
     *
     * @param role     - role
     * @param userType - user type
     */
    @Override
    public void updateUserTypeByRole(final String role, final UserType userType) {
        final LdapRole ldapRole = ldapRoleRepository.findByName(role);
        final Set<Name> members = ldapRole.getMembers();
        if (!CollectionUtils.isEmpty(ldapRole.getMembers())) {
            members.forEach(member -> {
                String memberString = member.toString();
                memberString = memberString.replace("dc=raster,dc=com", "");
                memberString = memberString.substring(0, memberString.length() - 1);
                System.out.println("Before Update " + memberString);
                if(memberString.startsWith("uid") && !memberString.startsWith("uid=raster")) {
                    final DirContextOperations context = ldapTemplate.lookupContext(String.valueOf(memberString));
                    context.setAttributeValue(ApacheDSLdapDirectories.USER_TYPE.getAttribute(), userType.toString());
                    ldapTemplate.modifyAttributes(context);
                    System.out.println("After Update " + memberString);
                }
            });
        }
    }
}
