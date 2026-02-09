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
package in.raster.ihms.authserver.web.rest.custom;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.domain.custom.User;
import in.raster.ihms.authserver.ldap.domain.LdapUser;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.authserver.service.custom.UserCustomService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Custom resource to manage user related functions.
 */
@RestController
@RequestMapping("/api")
public class UserCustomResource {

    private final UserCustomService userCustomService;

    public UserCustomResource(UserCustomService userCustomService) {
        this.userCustomService = userCustomService;
    }

    /**
     * Get all users.
     *
     * @return user list
     */
    @GetMapping("/users-all")
    @Timed
    public List<User> getUsers() {
        return userCustomService.getUsers();
    }

    /**
     * Get credit authorized users.
     *
     * @return user list
     */
    @GetMapping("/users-credit")
    @Timed
    public List<User> getCreditAuthorizedUsers() {
        return userCustomService.getCreditAuthorizedUsers();
    }

    /**
     * Get users by type.
     *
     * @return user list
     */
    @GetMapping("/users-by-type")
    @Timed
    public List<User> getUsersByType(@RequestParam(value = "userType") final UserType userType) {
        return userCustomService.getUsersByType(userType);
    }

    /**
     * Get users by types.
     *
     * @return user list
     */
    @GetMapping("/users-by-types")
    @Timed
    public List<User> getUsersByTypes(@RequestParam(value = "userType") final String userType) {
        return userCustomService.getUsersByTypes(userType);
    }

    /**
     * Get users by role.
     *
     * @param applicationName - application name
     * @param roleName        - role name
     * @return list of users
     */
    @GetMapping("/users-by-role")
    @Timed
    public List<User> getUsersByRole(@RequestParam(value = "applicationName") final String applicationName,
                                     @RequestParam(value = "roleName") final String roleName) {
        return userCustomService.getUsersByRole(applicationName, roleName);
    }

    /**
     * To get a list of user names in an application
     *
     * @param applicationName : Name of the application
     * @param userName        : Name of the user
     * @return List of User Names
     */
    @GetMapping("/user-names-by-application")
    @Timed
    public List<String> getUsersByApplication(
        @RequestParam(value = "applicationName") final String applicationName,
        @RequestParam(value = "userName", required = false) final String userName) {
        return userCustomService.getUserNamesByApplication(applicationName, userName);
    }

    /**
     * Get user by name.
     *
     * @param userName - user name
     * @return user object
     */
    @GetMapping("/users-by-name")
    @Timed
    public User getUserByName(@RequestParam(value = "name") final String userName) {
        return userCustomService.getUserByName(userName);
    }

    /**
     * Get user display name  by user name.
     *
     * @param userName - user name
     * @return string
     */
    @GetMapping("/users-display-name")
    @Timed
    public String getUserDisplayNameByName(@RequestParam(value = "name") final String userName) {
        return userCustomService.getUserDisplayNameByName(userName);
    }

    /**
     * Find by user name.
     *
     * @param userName - user name
     * @return User
     */
    @GetMapping("/users-find-by-name")
    @Timed
    public User findUserByName(@RequestParam(value = "name") final String userName) {
        return userCustomService.findUserByName(userName);
    }

    /**
     * Update user.
     *
     * @param user - user
     * @return user object
     */
    @PostMapping("/users-create")
    @Timed
    public User createUser(@RequestBody final User user) {
        return userCustomService.createUser(user);
    }

    /**
     * Update user.
     *
     * @param user - user
     * @return user object
     */
    @PutMapping("/users-update")
    @Timed
    public User updateUser(@RequestBody final User user) {
        return userCustomService.updateUser(user);
    }

    /**
     * Update user photo.
     *
     * @param user - user
     * @return user object
     */
    @PostMapping("/users-update-photo")
    @Timed
    public User updateUserPhoto(@RequestPart final User user,
                                @RequestPart(value = "file", required = false) final MultipartFile userPhotoFile) {
        return userCustomService.updateUserPhoto(user, userPhotoFile);
    }

    /**
     * Update user password.
     *
     * @param user - user
     * @return user object
     */
    @PutMapping("/users-update-password")
    @Timed
    public User updateUserPassword(@RequestBody final User user) {
        return userCustomService.updateUserPassword(user);
    }

    /**
     * Todo : This url is used in pharmacy, do not delete
     * To get user and authorities
     *
     * @return user and authorities
     */
    @GetMapping("/users-authorities")
    @Timed
    public Map<String, String> getJwtUserNameAndAuthorities() {
        return userCustomService.getJwtUserNameAndAuthorities();
    }

    /**
     * Todo : This url is used in pharmacy, do not delete
     * Get user by employee number.
     *
     * @param employeeNumber - number of the employee
     * @return user object
     */
    @GetMapping("/users-by-employee-number")
    @Timed
    public User getUserByEmployeeNumber(@RequestParam(value = "employeeNumber") final String employeeNumber) {
        return userCustomService.getUserByEmployeeNumber(employeeNumber);
    }

    /**
     * Todo : This url is used in pharmacy, do not delete
     * To retrieve active list of users by name or employeeNumber
     *
     * @param searchText : Name or EmployeeNumber
     * @return List of users
     */
    @GetMapping("/users-by-name-or-employee-number")
    @Timed
    public List<User> getUserByNameOrEmployeeNumber(@RequestParam(value = "searchText", required = false) final String searchText) {
        return userCustomService.getListOfUsersByNameOrEmployeeNumber(searchText);
    }

    /**
     * To verify user password
     *
     * @param user : user
     * @return Boolean - correct/wrong
     */
    @PutMapping("/users-verify-password")
    @Timed
    public Boolean verifyUserPassword(@RequestBody final User user) {
        return userCustomService.verifyUserPassword(user);
    }

     /**
     * Get users by branch and user type
     *
     * @param branchName - branch name
     * @param userType   - user type
     * @return list of users
     */
    @GetMapping("/users-by-branch-type")
    @Timed
    public List<User> getUsersByBranchAndType(@RequestParam(value = "branchName") final String branchName,
                                              @RequestParam(value = "userType") final UserType userType) {
        return userCustomService.getUsersByBranchAndType(branchName, userType);
    }

    /**
     * Get location mapped users.
     * @return list of user
     */
    @GetMapping("/users-update-default-location-id")
    @Timed
    public List<User> updateLocationMappedUsers() {
        return userCustomService.updateLocationMappedUsers();
    }

    /**
     * Find by location is.
     *
     * @param locationId - location id
     * @return User
     */
    @GetMapping("/users-by-location")
    @Timed
    public List<User> getUsersByLocationId(@RequestParam(value = "locationId") final String locationId) {
        return userCustomService.getUsersByLocationId(locationId);
    }

    /**
     * Update user type by role.
     *
     * @param role     - role
     * @param userType - user type
     */
    @PutMapping("/users-update-user-type-by-role")
    @Timed
    public void updateUserTypeByRole(@RequestParam(value = "role") final String role,
                                     @RequestParam(value = "userType") final UserType userType) {
        userCustomService.updateUserTypeByRole(role, userType);
    }
}
