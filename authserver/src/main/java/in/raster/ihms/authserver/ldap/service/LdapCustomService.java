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
package in.raster.ihms.authserver.ldap.service;

import in.raster.ihms.authserver.domain.custom.*;
import in.raster.ihms.authserver.ldap.domain.*;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.exceptions.custom.InvalidCredentialsException;

import java.util.List;

/**
 * Ldap custom service.
 */
public interface LdapCustomService {

    /**
     * Authenticate user credentials.
     *
     * @param username - username
     * @param password - password
     * @return - boolean value indicates valid user or not
     */
    boolean authenticate(final String username, final String password) throws InvalidCredentialsException;

    /**
     * Query method to find unique groups in LDAP.
     *
     * @param member - member dn
     * @return list of ldap group
     */
    List<LdapGroup> getLdapGroupsByMemberDn(final String member);

    /**
     * Query method to find unique groups(Branches) in LDAP.
     *
     * @param userDn         - user distinguished name
     * @param organizationDn - organization distinguished name
     * @return list of ldap group
     */
    List<LdapGroup> getBranchesByUserDn(final String userDn, final String organizationDn);

    /**
     * Get applications by user dn and branch name.
     *
     * @param userDn           - user distinguished name
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of ldap application
     */
    List<LdapApplication> getApplicationsByUserAndBranch(final String userDn, final String branchName,
                                                         final String organizationName);

    /**
     * Get roles by user.
     *
     * @param userDn           - user distinguished name
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @param applicationName  - base dn to be searched
     * @return list of ldap roles
     */
    List<LdapRole> getRolesByUserBranchAndApplication(final String userDn, final String branchName,
                                                      final String organizationName,
                                                      final String applicationName);

    /**
     * Get roles by user.
     *
     * @param userDn           - user distinguished name
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @param applicationName  - base dn to be searched
     * @return list of ldap roles
     */
    List<LdapRole> getAllRolesByUserBranchAndApplication(final String userDn, final String branchName,
                                                         final String organizationName,
                                                         final String applicationName);

    /**
     * Get menus by user role.
     *
     * @param roleDn          - role distinguished name
     * @param applicationName - base dn to be searched
     * @return list of ldap menus
     */
    List<LdapMenu> getMenusByUserRole(final String roleDn,
                                      final String applicationName);

    /**
     * Get resources by user role.
     *
     * @param roleDn          - role distinguished name
     * @param applicationName - base dn to be searched
     * @return list of resource
     */
    List<LdapResource> getResourcesByUserRole(final String roleDn,
                                              final String applicationName);

    /**
     * Create ldap user.
     *
     * @param user - user object
     * @return distinguished name created in ldap
     */
    String createOrganizationUser(final User user);

    /**
     * Modify ldap user attributes.
     *
     * @param user - user object
     */
    void modifyUserAttributes(final User user);

    /**
     * Modify ldap user attributes.
     *
     * @param user - user object
     */
    void updateUserPassword(final User user);

    /**
     * Get all users.
     *
     * @param userName       - user name
     * @param departmentName - department name
     * @param userType       - user type
     * @param active         - active
     * @return user list
     */
    List<LdapUser> getUsers(final String userName, final String departmentName,
                            final UserType userType, final String active);

    /**
     * Get credit authorized users.
     *
     * @return user list
     */
    List<LdapUser> getCreditAuthorizedUsers();

    /**
     * Get users by type.
     *
     * @return user list
     */
    List<LdapUser> getUsersByType(final UserType userType);

    /**
     * Create ldap branch.
     *
     * @param branch           - branch object
     * @param organizationName - organization name
     * @return distinguished name created in ldap
     */
    String createOrganizationBranch(final Branch branch, final String organizationName);

    /**
     * Modify ldap branch attributes.
     *
     * @param branch - branch object
     */
    void modifyBranchAttributes(final Branch branch);

    /**
     * Get all menus by application id.
     *
     * @param applicationName - application name
     * @param menuName        - menu name
     * @param parentMenuName  - parent menu name
     * @param active          - active flag
     * @return list of ldap menus
     */
    List<LdapMenu> getApplicationMenus(final String applicationName, final String menuName,
                                       final String parentMenuName, final Boolean active);

    /**
     * Create ldap menu.
     *
     * @param menu - menu object
     * @return distinguished name created in ldap
     */
    String createApplicationMenu(final Menu menu);

    /**
     * Modify ldap menu attributes.
     *
     * @param menu - menu object
     */
    void modifyMenuAttributes(final Menu menu);

    /**
     * Get active resources by application.
     *
     * @param applicationName - application name
     * @return list of resource
     */
    List<LdapResource> getActiveResourcesByApplication(final String applicationName);

    /**
     * Create ldap role.
     *
     * @param role             - role object
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return distinguished name created in ldap
     */
    String createApplicationRole(final Role role, final String branchName, final String organizationName);

    /**
     * Add given member id to the group.
     *
     * @param memberDn - member distinguished name
     * @param groupDn  - can be branch, application, menu or resource id
     */
    void addMemberToGroup(final String memberDn, final String groupDn);

    /**
     * Remove given member from the group.
     *
     * @param memberDn - member distinguished name
     * @param groupDn  - can be branch, application, menu or resource id
     */
    void removeMemberFromGroup(final String memberDn, final String groupDn);

    /**
     * Get all resources by application.
     *
     * @param applicationName - application name
     * @param resourceName    - resource name
     * @return list of ldap resources
     */
    List<LdapResource> getApplicationResources(final String applicationName,
                                               final String resourceName);

    /**
     * Create ldap resource.
     *
     * @param resource - resource object
     * @return distinguished name created in ldap
     */
    String createApplicationResource(final Resource resource);

    /**
     * Modify ldap resource attributes.
     *
     * @param resource - resource object
     */
    void modifyResourceAttributes(final Resource resource);

    /**
     * Delete ldap resource by given dn.
     *
     * @param resourceName    - resource name
     * @param applicationName - application name
     * @param menuName        - menu name
     */
    void deleteApplicationResource(final String resourceName, final String applicationName, final String menuName);

    /**
     * Get roles by application name.
     *
     * @param applicationName  - application name
     * @param roleName         - role name
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of ldap roles
     */
    List<LdapRole> getApplicationRoles(final String applicationName, final String roleName,
                                       final String branchName, final String organizationName);

    /**
     * Get role by name.
     *
     * @param applicationName  - application name
     * @param roleName         - role name
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of ldap roles
     */
    List<LdapRole> getLdapRoleByName(final String applicationName, final String roleName,
                                     final String branchName, final String organizationName);

    /**
     * Query method to find all branches in LDAP.
     *
     * @param branchName       - branch name
     * @param organizationName - organization name
     * @return list of ldap group
     */
    List<LdapGroup> getAllBranches(final String branchName, final String organizationName);

    /**
     * Get list of active organizations by user.
     *
     * @param userName - user name
     * @return list of organization
     */
    List<LdapOrganization> getOrganizationsByUser(final String userName);

    /**
     * Get list of branches by user and organization.
     *
     * @param userId           - user id
     * @param organizationName - organization name
     * @return list of branches
     */
    List<LdapGroup> getBranchesByUserAndOrganization(final String userId,
                                                     final String organizationName);

    /**
     * Create ldap department.
     *
     * @param department       - department object
     * @param organizationName - organization name
     * @return distinguished name created in ldap
     */
    String createOrganizationDepartment(final Department department, final String organizationName);

    /**
     * Modify ldap department attributes.
     *
     * @param department       - department object
     * @param organizationName - organization name
     */
    void modifyDepartmentAttributes(final Department department, final String organizationName);

    /**
     * Query method to find all department in LDAP.
     *
     * @param departmentName   - department name
     * @param organizationName - organization name
     * @param isActive         - active flag
     * @return list of ldap department
     */
    List<LdapDepartment> getDepartments(final String departmentName, final String organizationName,
                                        final Boolean isActive, final Boolean isCreditLimitEnabled);

    /**
     * Get Departments By Description
     *
     * @param departmentDescription - departmentDescription
     * @param organizationName      - organizationName
     * @param isActive              - isActive
     * @param isCreditLimitEnabled  - isCreditLimitEnabled
     * @return list of ldap department
     */
    List<LdapDepartment> getDepartmentsByDescription(final String departmentDescription, final String organizationName,
                                                     final Boolean isActive, final Boolean isCreditLimitEnabled);

    /**
     * Query method to find all sub department in LDAP.
     *
     * @param subDepartmentName - sub department name
     * @param parentDepartment  - parent department name
     * @param organizationName  - organization name
     * @param isActive          - active flag
     * @return list of ldap department
     */
    List<LdapDepartment> getSubDepartments(final String subDepartmentName, final String parentDepartment,
                                           final String organizationName, final Boolean isActive);

    /**
     * Query method to find department by name.
     *
     * @param departmentName   - department name
     * @param organizationName - organization name
     * @return ldap department
     */
    List<LdapDepartment> getDepartmentByName(final String departmentName,
                                             final String organizationName);

    /**
     * Get active users.
     *
     * @return user list
     */
    List<LdapUser> getActiveUsers();

    /**
     * Query method to find all investigational departments in LDAP.
     *
     * @param organizationName - organization name
     * @param isActive         - active flag
     * @return list of ldap department
     */
    List<LdapDepartment> getInvestigationalDepartments(final String organizationName,
                                                       final Boolean isActive);

    /**
     * Todo : This url is used in pharmacy, do not delete
     * To retrieve active list of users by name or employeeNumber
     *
     * @param searchText : Name or EmployeeNumber
     * @return List of users
     */
    List<LdapUser> getActiveUsersByNameOrEmployeeNumber(final String searchText);
}
