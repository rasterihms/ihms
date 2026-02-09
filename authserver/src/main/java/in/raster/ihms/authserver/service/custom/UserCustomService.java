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
package in.raster.ihms.authserver.service.custom;

import in.raster.ihms.authserver.domain.custom.User;
import org.springframework.web.bind.annotation.RequestBody;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Custom service to manage user related functions.
 */
public interface UserCustomService {

    /**
     * Get all users.
     *
     * @return user list
     */
    List<User> getUsers();

    /**
     * Get credit authorized users.
     *
     * @return user list
     */
    List<User> getCreditAuthorizedUsers();

    /**
     * Get users by type.
     *
     * @return user list
     */
    List<User> getUsersByType(final UserType userType);

    /**
     * Get users by types.
     *
     * @return user list
     */
    List<User> getUsersByTypes(final String userType);

    /**
     * Get users by role.
     *
     * @param applicationName - application name
     * @param roleName        - role name
     * @return list of users
     */
    List<User> getUsersByRole(final String applicationName, final String roleName);

    /**
     * To get a list of user names in an application
     *
     * @param applicationName : Name of the application
     * @param userName        : Name of the user
     * @return List of User Names
     */
    List<String> getUserNamesByApplication(final String applicationName,
                                           final String userName);

    /**
     * Get user by name.
     *
     * @param userName - user name
     * @return user object
     */
    User getUserByName(final String userName);


    /**
     * Get user display name  by user name.
     *
     * @param userName - user name
     * @return string
     */
    String getUserDisplayNameByName(final String userName);

    /**
     * Find by user name.
     *
     * @param userName - user name
     * @return User
     */
    User findUserByName(final String userName);

    /**
     * Create user.
     *
     * @param user - user
     * @return user object
     */
    User createUser(final User user);

    /**
     * Update user.
     *
     * @param user - user
     * @return user object
     */
    User updateUser(final User user);

    /**
     * Update user photo in ldap.
     *
     * @param user          - user object
     * @param userPhotoFile - user photo file
     * @return saved user
     */
    User updateUserPhoto(final User user, final MultipartFile userPhotoFile);

    /**
     * Update user password.
     *
     * @param user - user
     * @return user object
     */
    User updateUserPassword(final User user);

    /**
     * Todo : This method is used in pharmacy, do not delete
     * To get user and authorities
     *
     * @return user and authorities
     */
    Map<String, String> getJwtUserNameAndAuthorities();

    /**
     * Todo : This method is used in pharmacy, do not delete
     * Get user by employee number.
     *
     * @param employeeNumber - number of the employee
     * @return user object
     */
    User getUserByEmployeeNumber(final String employeeNumber);

    /**
     * To retrieve active list of users by name or employeeNumber
     *
     * @param searchText : Name or EmployeeNumber
     * @return List of users
     */
    List<User> getListOfUsersByNameOrEmployeeNumber(final String searchText);

    /**
     * To verify user password
     *
     * @param user : user
     * @return Boolean - correct/wrong
     */
    Boolean verifyUserPassword(@RequestBody final User user);

    /**
     * Get users by branch and user type
     *
     * @param branchName - branch name
     * @param userType   - user type
     * @return list of users
     */
    List<User> getUsersByBranchAndType(final String branchName, final UserType userType);

    /**
     * Get location mapped users.
     * @return list of user
     */
    List<User> updateLocationMappedUsers();

    /**
     * Get list of users by location.
     *
     * @param locationId - location id
     * @return list of user
     */
    List<User> getUsersByLocationId(final String locationId);

    /**
     * Update user type by role.
     *
     * @param role     - role
     * @param userType - user type
     */
    void updateUserTypeByRole(final String role, final UserType userType);
}
