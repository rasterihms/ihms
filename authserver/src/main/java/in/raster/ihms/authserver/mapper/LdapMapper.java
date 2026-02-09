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
package in.raster.ihms.authserver.mapper;

import in.raster.ihms.authserver.domain.custom.*;
import in.raster.ihms.authserver.ldap.domain.*;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class to ldap objects to custom object.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LdapMapper {

    /**
     * To generate instance of LdapMapper.
     */
    LdapMapper INSTANCE_LDAP_MAPPER = Mappers.getMapper(LdapMapper.class);

    /**
     * To construct branch from ldap branch.
     *
     * @param ldapBranch - ldap branch
     * @return Branch object
     */
    default Branch constructBranchFromLdapBranch(final LdapGroup ldapBranch) {
        final Branch branch = new Branch();
        List<String> branchMembers = null;
        branch.setId(String.valueOf(ldapBranch.getDn()));
        branch.setName(ldapBranch.getName());
        branch.setCode(ldapBranch.getCode());
        branch.setTimeZone(ldapBranch.getTimeZone());
        branch.setCurrency(ldapBranch.getCurrency());
        branch.setPreferredLanguage(ldapBranch.getPreferredLanguage());
        if (!ObjectUtils.isEmpty(ldapBranch.getBillingLimit())) {
            branch.setBillingLimit(Long.valueOf(ldapBranch.getBillingLimit()));
        }
        branch.setOpeningTime(ldapBranch.getOpeningTime());
        branch.setClosingTime(ldapBranch.getClosingTime());
        branch.setActive(Boolean.valueOf(ldapBranch.getActive()));
        branch.setCreatedBy(ldapBranch.getCreatedBy());
        branch.setModifiedBy(ldapBranch.getModifiedBy());
        branch.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapBranch.getCreatedDate()));
        branch.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapBranch.getModifiedDate()));
        if (!CollectionUtils.isEmpty(ldapBranch.getMembers())) {
            branchMembers = ldapBranch.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
        }
        branch.setMembers(branchMembers);
        return branch;
    }

    /**
     * To construct branch list from ldap branches.
     *
     * @param ldapBranchList - ldap branch list
     * @return Branch object
     */
    default List<Branch> constructBranchListFromLdapBranches(final List<LdapGroup> ldapBranchList) {
        final List<Branch> branchList = new ArrayList<>();
        ldapBranchList.forEach(ldapBranch -> {
            final Branch branch = new Branch();
            List<String> branchMembers = null;
            branch.setId(String.valueOf(ldapBranch.getDn()));
            branch.setName(ldapBranch.getName());
            branch.setCode(ldapBranch.getCode());
            branch.setTimeZone(ldapBranch.getTimeZone());
            if (!ObjectUtils.isEmpty(ldapBranch.getBillingLimit())) {
                branch.setBillingLimit(Long.valueOf(ldapBranch.getBillingLimit()));
            }
            branch.setOpeningTime(ldapBranch.getOpeningTime());
            branch.setClosingTime(ldapBranch.getClosingTime());
            branch.setActive(Boolean.valueOf(ldapBranch.getActive()));
            branch.setCreatedBy(ldapBranch.getCreatedBy());
            branch.setModifiedBy(ldapBranch.getModifiedBy());
            branch.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapBranch.getCreatedDate()));
            branch.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapBranch.getModifiedDate()));
            if (!CollectionUtils.isEmpty(ldapBranch.getMembers())) {
                branchMembers = ldapBranch.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
            }
            branch.setPreferredLanguage(ldapBranch.getPreferredLanguage());
            branch.setCurrency(ldapBranch.getCurrency());
            branch.setMembers(branchMembers);
            branchList.add(branch);
        });
        return branchList;
    }

    /**
     * To construct application from ldap application.
     *
     * @param ldapApplication - ldap application
     * @return Application object
     */
    default Application constructApplicationFromLdapApplication(final LdapApplication ldapApplication) {
        final Application application = new Application();
        List<String> applicationMembers = null;
        application.setId(String.valueOf(ldapApplication.getDn()));
        application.setName(ldapApplication.getName());
        application.setUrl(ldapApplication.getUrl());
        application.setActive(Boolean.valueOf(ldapApplication.getActive()));
        application.setCreatedBy(ldapApplication.getCreatedBy());
        application.setModifiedBy(ldapApplication.getModifiedBy());
        application.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapApplication.getCreatedDate()));
        application.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapApplication.getModifiedDate()));
        if (!CollectionUtils.isEmpty(ldapApplication.getMembers())) {
            applicationMembers = ldapApplication.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
        }
        application.setMembers(applicationMembers);
        return application;
    }

    /**
     * To construct applications from ldap applications.
     *
     * @param ldapApplications - ldap application list
     * @return list application object
     */
    default List<Application> constructApplicationListFromLdapApplications(final List<LdapApplication> ldapApplications) {
        final List<Application> applicationList = new ArrayList<>();
        ldapApplications.forEach(ldapApplication -> {
            final Application application = new Application();
            List<String> applicationMembers = null;
            application.setId(String.valueOf(ldapApplication.getDn()));
            application.setName(ldapApplication.getName());
            application.setUrl(ldapApplication.getUrl());
            application.setActive(Boolean.valueOf(ldapApplication.getActive()));
            application.setCreatedBy(ldapApplication.getCreatedBy());
            application.setModifiedBy(ldapApplication.getModifiedBy());
            application.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapApplication.getCreatedDate()));
            application.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapApplication.getModifiedDate()));
            if (!CollectionUtils.isEmpty(ldapApplication.getMembers())) {
                applicationMembers = ldapApplication.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
            }
            application.setMembers(applicationMembers);
            applicationList.add(application);
        });
        return applicationList;
    }

    /**
     * To construct role from ldap role.
     *
     * @param ldapRole - ldap role
     * @return Role object
     */
    default Role constructRoleFromLdapRole(final LdapRole ldapRole) {
        final Role role = new Role();
        List<String> roleMembers = null;
        role.setId(String.valueOf(ldapRole.getDn()));
        role.setName(ldapRole.getName());
        role.setApplicationName(AuthServerUtil.getApplicationNameFromRoleId(String.valueOf(ldapRole.getDn())));
        role.setActive(Boolean.valueOf(ldapRole.getActive()));
        role.setCreatedBy(ldapRole.getCreatedBy());
        role.setModifiedBy(ldapRole.getModifiedBy());
        role.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapRole.getCreatedDate()));
        role.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapRole.getModifiedDate()));
        if (!CollectionUtils.isEmpty(ldapRole.getMembers())) {
            roleMembers = ldapRole.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
        }
        role.setMembers(roleMembers);
        return role;
    }

    /**
     * To construct roles from ldap roles.
     *
     * @param ldapRoles - ldap role list
     * @return list of role object
     */
    default List<Role> constructRoleListFromLdapRoles(final List<LdapRole> ldapRoles) {
        final List<Role> roleList = new ArrayList<>();
        ldapRoles.forEach(ldapRole -> {
            final Role role = new Role();
            List<String> roleMembers = null;
            role.setId(String.valueOf(ldapRole.getDn()));
            role.setName(ldapRole.getName());
            role.setApplicationName(AuthServerUtil.getApplicationNameFromRoleId(String.valueOf(ldapRole.getDn())));
            role.setActive(Boolean.valueOf(ldapRole.getActive()));
            role.setCreatedBy(ldapRole.getCreatedBy());
            role.setModifiedBy(ldapRole.getModifiedBy());
            role.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapRole.getCreatedDate()));
            role.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapRole.getModifiedDate()));
            if (!CollectionUtils.isEmpty(ldapRole.getMembers())) {
                roleMembers = ldapRole.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
            }
            role.setMembers(roleMembers);
            roleList.add(role);
        });
        return roleList;
    }

    /**
     * To construct menu from ldap menu.
     *
     * @param ldapMenu - ldap menu
     * @return Menu object
     */
    default Menu constructMenuFromLdapMenu(final LdapMenu ldapMenu) {
        final Menu menu = new Menu();
        List<String> menuMembers = null;
        menu.setId(String.valueOf(ldapMenu.getDn()));
        menu.setName(ldapMenu.getName());
        menu.setDisplayName(ldapMenu.getDisplayName());
        menu.setOrder(Integer.valueOf(ldapMenu.getOrder()));
        menu.setIcon(ldapMenu.getIcon());
        menu.setUrl(ldapMenu.getUrl());
        if (!ObjectUtils.isEmpty(ldapMenu.getParentMenuId())) {
            menu.setParentMenu(AuthServerUtil.getNameFromParentMenuId(ldapMenu.getParentMenuId()));
        }
        menu.setApplicationName(AuthServerUtil.getApplicationNameFromLdapId(String.valueOf(ldapMenu.getDn())));
        menu.setActive(Boolean.valueOf(ldapMenu.getActive()));
        menu.setCreatedBy(ldapMenu.getCreatedBy());
        menu.setModifiedBy(ldapMenu.getModifiedBy());
        menu.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapMenu.getCreatedDate()));
        menu.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapMenu.getModifiedDate()));
        if (!CollectionUtils.isEmpty(ldapMenu.getMembers())) {
            menuMembers = ldapMenu.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
        }
        menu.setMembers(menuMembers);
        return menu;
    }

    /**
     * To construct menus from ldap menus.
     *
     * @param ldapMenus - ldap menu list
     * @return list menu object
     */
    default List<Menu> constructMenuListFromLdapMenus(final List<LdapMenu> ldapMenus) {
        final List<Menu> menuList = new ArrayList<>();
        ldapMenus.forEach(ldapMenu -> {
            final Menu menu = new Menu();
            List<String> menuMembers = null;
            menu.setId(String.valueOf(ldapMenu.getDn()));
            menu.setName(ldapMenu.getName());
            menu.setDisplayName(ldapMenu.getDisplayName());
            menu.setOrder(Integer.valueOf(ldapMenu.getOrder()));
            menu.setIcon(ldapMenu.getIcon());
            menu.setUrl(ldapMenu.getUrl());
            if (!ObjectUtils.isEmpty(ldapMenu.getParentMenuId())) {
                menu.setParentMenu(AuthServerUtil.getNameFromParentMenuId(ldapMenu.getParentMenuId()));
            }
            menu.setApplicationName(AuthServerUtil.getApplicationNameFromLdapId(String.valueOf(ldapMenu.getDn())));
            menu.setActive(Boolean.valueOf(ldapMenu.getActive()));
            menu.setCreatedBy(ldapMenu.getCreatedBy());
            menu.setModifiedBy(ldapMenu.getModifiedBy());
            menu.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapMenu.getCreatedDate()));
            menu.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapMenu.getModifiedDate()));
            if (!CollectionUtils.isEmpty(ldapMenu.getMembers())) {
                menuMembers = ldapMenu.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
            }
            menu.setMembers(menuMembers);
            menuList.add(menu);
        });
        return menuList;
    }

    /**
     * To construct resource from ldap resource.
     *
     * @param ldapResource - ldap resource
     * @return Resource object
     */
    default Resource constructResourceFromLdapResource(final LdapResource ldapResource) {
        final Resource resource = new Resource();
        List<String> resourceMembers = null;
        resource.setId(String.valueOf(ldapResource.getDn()));
        resource.setName(ldapResource.getName());
        resource.setUrl(String.valueOf(ldapResource.getUrl()));
        resource.setPermissionType(ldapResource.getPermissionType());
        resource.setApplicationName(AuthServerUtil.getApplicationNameFromLdapId(String.valueOf(ldapResource.getDn())));
        resource.setActive(Boolean.valueOf(ldapResource.getActive()));
        resource.setCreatedBy(ldapResource.getCreatedBy());
        resource.setModifiedBy(ldapResource.getModifiedBy());
        resource.setMenuId(AuthServerUtil.getMenuNameFromResourceId(String.valueOf(ldapResource.getDn())));
        resource.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapResource.getCreatedDate()));
        resource.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapResource.getModifiedDate()));
        if (!CollectionUtils.isEmpty(ldapResource.getMembers())) {
            resourceMembers = ldapResource.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
        }
        resource.setMembers(resourceMembers);
        return resource;
    }

    /**
     * To construct user from ldap user.
     *
     * @param ldapUser - ldap user
     * @return user object
     */
    default User constructUserFromLdapUser(final LdapUser ldapUser) {
        final User user = new User();
        user.setId(String.valueOf(ldapUser.getId()));
        user.setUserName(ldapUser.getUsername());
        user.setSurName(String.valueOf(ldapUser.getSurname()));
        user.setMail(ldapUser.getMail());
        user.setMobileNumber(ldapUser.getMobileNumber());
        user.setEmployeeNumber(ldapUser.getEmployeeNumber());
        if (!ObjectUtils.isEmpty(ldapUser.getIsCreditAuthorizedUser())) {
            if (Boolean.valueOf(ldapUser.getIsCreditAuthorizedUser())) {
                user.setCreditAuthorizedUser(Boolean.valueOf(ldapUser.getIsCreditAuthorizedUser()));
                user.setCreditAmountLimit(Long.valueOf(ldapUser.getCreditAmountLimit()));
            }
        }
//        if (!ObjectUtils.isEmpty(ldapUser.getIsDoctor())) {
//            if (Boolean.valueOf(ldapUser.getIsDoctor())) {
//                user.setDoctor(Boolean.valueOf(ldapUser.getIsDoctor()));
//                user.setDoctorId(Long.valueOf(ldapUser.getDoctorId()));
//            }
//        }
        if (!ObjectUtils.isEmpty(ldapUser.getUserType()) && !ldapUser.getUserType().equals("null")) {
            user.setUserType(UserType.valueOf(ldapUser.getUserType()));
        }
        if (!ObjectUtils.isEmpty(ldapUser.getDoctorId()) && !ldapUser.getDoctorId().equals("null")) {
            user.setDoctorId(Long.valueOf(ldapUser.getDoctorId()));
        }
        user.setDepartmentIds(ldapUser.getDepartment());
        user.setSignFilePath(ldapUser.getSignFilePath());
        user.setPhotoFilePath(ldapUser.getPhotoFilePath());
        if (!ObjectUtils.isEmpty(ldapUser.getExternalUserId())) {
            user.setExternalUserId(Long.valueOf(ldapUser.getExternalUserId()));
        }
        if (!ObjectUtils.isEmpty(ldapUser.getDefaultLocationId())) {
            user.setDefaultLocationId(Long.valueOf(ldapUser.getDefaultLocationId()));
        }
        if (!ObjectUtils.isEmpty(ldapUser.getLocationIds())) {
            user.setLocationIds(ldapUser.getLocationIds());
        }
        if (!ObjectUtils.isEmpty(ldapUser.getThemeColor())) {
            user.setThemeColor(ldapUser.getThemeColor());
        }
        if (!ObjectUtils.isEmpty(ldapUser.getLightTheme())) {
            user.setLightTheme(Boolean.valueOf(ldapUser.getLightTheme()));
        }
        if (!ObjectUtils.isEmpty(ldapUser.getPwdAccountLockedTime())) {
            user.setPwdAccountLockedTime(ldapUser.getPwdAccountLockedTime());
        }
        user.setActive(Boolean.valueOf(ldapUser.getActive()));
        user.setCreatedBy(ldapUser.getCreatedBy());
        user.setModifiedBy(ldapUser.getModifiedBy());
        user.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapUser.getCreatedDate()));
        user.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapUser.getModifiedDate()));
        return user;
    }

    /**
     * To construct users from ldap users.
     *
     * @param ldapUsers - ldap user list
     * @return list user object
     */
    default List<User> constructUserListFromLdapUsers(final List<LdapUser> ldapUsers) {
        final List<User> userList = new ArrayList<>();
        ldapUsers.forEach(ldapUser -> {
            final User user = new User();
            user.setId(String.valueOf(ldapUser.getId()));
            user.setUserName(ldapUser.getUsername());
            user.setSurName(String.valueOf(ldapUser.getSurname()));
            user.setMail(ldapUser.getMail());
            user.setMobileNumber(ldapUser.getMobileNumber());
            user.setEmployeeNumber(ldapUser.getEmployeeNumber());
            if (!ObjectUtils.isEmpty(ldapUser.getIsCreditAuthorizedUser())) {
                if (Boolean.valueOf(ldapUser.getIsCreditAuthorizedUser())) {
                    user.setCreditAuthorizedUser(Boolean.valueOf(ldapUser.getIsCreditAuthorizedUser()));
                    user.setCreditAmountLimit(Long.valueOf(ldapUser.getCreditAmountLimit()));
                }
            }
//            if (!ObjectUtils.isEmpty(ldapUser.getIsDoctor())) {
//                if (Boolean.valueOf(ldapUser.getIsDoctor())) {
//                    user.setDoctor(Boolean.valueOf(ldapUser.getIsDoctor()));
//                    user.setDoctorId(Long.valueOf(ldapUser.getDoctorId()));
//                }
//            }
            if (!ObjectUtils.isEmpty(ldapUser.getUserType()) && !ldapUser.getUserType().equals("null")) {
                user.setUserType(UserType.valueOf(ldapUser.getUserType()));
            }
            if (!ObjectUtils.isEmpty(ldapUser.getDoctorId()) && !ldapUser.getDoctorId().equals("null")) {
                user.setDoctorId(Long.valueOf(ldapUser.getDoctorId()));
            }
            if (!ObjectUtils.isEmpty(ldapUser.getDefaultLocationId())) {
                user.setDefaultLocationId(Long.valueOf(ldapUser.getDefaultLocationId()));
            }
            if (!ObjectUtils.isEmpty(ldapUser.getLocationIds())) {
                user.setLocationIds(ldapUser.getLocationIds());
            }
            user.setDepartmentIds(ldapUser.getDepartment());
            user.setSignFilePath(ldapUser.getSignFilePath());
            user.setPhotoFilePath(ldapUser.getPhotoFilePath());
            if (!ObjectUtils.isEmpty(ldapUser.getExternalUserId())) {
                user.setExternalUserId(Long.valueOf(ldapUser.getExternalUserId()));
            }
            user.setActive(Boolean.valueOf(ldapUser.getActive()));
            user.setCreatedBy(ldapUser.getCreatedBy());
            user.setModifiedBy(ldapUser.getModifiedBy());
            user.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapUser.getCreatedDate()));
            user.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapUser.getModifiedDate()));
            userList.add(user);
        });
        return userList;
    }

    /**
     * To construct organization from ldap organization.
     *
     * @param ldapOrganization - ldap organization
     * @return organization object
     */
    default Organization constructOrganizationFromLdapOrganization(final LdapOrganization ldapOrganization) {
        final Organization organization = new Organization();
        List<String> branchMembers = null;
        organization.setId(String.valueOf(ldapOrganization.getDn()));
        organization.setName(ldapOrganization.getName());
        organization.setDescription(ldapOrganization.getDescription());
        organization.setRohiniId(ldapOrganization.getRohiniId());
        organization.setNtCode(ldapOrganization.getNtCode());
        organization.setContactNumber(ldapOrganization.getContactNumber());
        organization.setFaxNumber(ldapOrganization.getFaxNumber());
        organization.setEmailId(ldapOrganization.getEmailId());
        organization.setLandmark(ldapOrganization.getLandmark());
        organization.setCity(ldapOrganization.getCity());
        organization.setState(ldapOrganization.getState());
        organization.setPincode(ldapOrganization.getPincode());
        organization.setAddress(ldapOrganization.getAddress());
        organization.setSealFilePath(ldapOrganization.getSealFilePath());
        organization.setLogoFilePath(ldapOrganization.getLogoFilePath());
        organization.setActive(Boolean.valueOf(ldapOrganization.getActive()));
        organization.setCreatedBy(ldapOrganization.getCreatedBy());
        organization.setModifiedBy(ldapOrganization.getModifiedBy());
        organization.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapOrganization.getCreatedDate()));
        organization.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapOrganization.getModifiedDate()));
        if (!CollectionUtils.isEmpty(ldapOrganization.getMembers())) {
            branchMembers = ldapOrganization.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
        }
        organization.setMembers(branchMembers);
        return organization;
    }

    /**
     * To construct department list from ldap departments.
     *
     * @param ldapDepartments - ldap department list
     * @return list of department object
     */
    default List<Department> constructDepartmentListFromLdapDepartments(final List<LdapDepartment> ldapDepartments) {
        final List<Department> departmentList = new ArrayList<>();
        ldapDepartments.forEach(ldapDepartment -> {
            final Department department = new Department();
            List<String> departmentMembers = null;
            department.setId(String.valueOf(ldapDepartment.getDn()));
            department.setName(ldapDepartment.getName());
            department.setCode(ldapDepartment.getCode());
            department.setDescription(ldapDepartment.getDescription());
            if (!ObjectUtils.isEmpty(ldapDepartment.getParentDepartment())) {
                department.setParentDepartment(AuthServerUtil.getNameFromParentDepartment(ldapDepartment.getParentDepartment()));
            }
            if (!ObjectUtils.isEmpty(ldapDepartment.getInvestigationDepartment())) {
                department.setInvestigationDepartment(Boolean.valueOf(ldapDepartment.getInvestigationDepartment()));
            }
            department.setActive(Boolean.valueOf(ldapDepartment.getActive()));
            department.setCreatedBy(ldapDepartment.getCreatedBy());
            department.setModifiedBy(ldapDepartment.getModifiedBy());
            department.setCreatedDate(DateUtil.convertLdapTimeStampToInstant(ldapDepartment.getCreatedDate()));
            department.setModifiedDate(DateUtil.convertLdapTimeStampToInstant(ldapDepartment.getModifiedDate()));
            if (!CollectionUtils.isEmpty(ldapDepartment.getMembers())) {
                departmentMembers = ldapDepartment.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
            }
            if (!ObjectUtils.isEmpty(ldapDepartment.getBarcodeIdentifier())) {
                department.setBarcodeIdentifier(ldapDepartment.getBarcodeIdentifier());
            }
            if (!ObjectUtils.isEmpty(ldapDepartment.getCreditLimitEnabled())) {
                department.setCreditLimitEnabled(Boolean.valueOf(ldapDepartment.getCreditLimitEnabled()));
            }
            department.setMembers(departmentMembers);
            if (!ObjectUtils.isEmpty(ldapDepartment.getExternalDepartmentId())
                && !ldapDepartment.getExternalDepartmentId().equals("null")) {
                department.setExternalDepartmentId(Long.valueOf(ldapDepartment.getExternalDepartmentId()));
            }
            if (!ObjectUtils.isEmpty(ldapDepartment.getDisplayOrder())) {
                department.setDisplayOrder(Integer.valueOf(ldapDepartment.getDisplayOrder()));
            }
            departmentList.add(department);
        });
        return departmentList;
    }

    /**
     * Todo : Commented line will be removed in future.
     * <p>
     * To construct ldap organization from organization.
     *
     * @param organization - organization
     * @return ldap organization object
     */
    default LdapOrganization constructLdapOrganizationFromOrganization(final Organization organization) {
        final LdapOrganization ldapOrganization = new LdapOrganization();
//        List<String> branchMembers = null;
        ldapOrganization.setDn(LdapNameBuilder.newInstance(organization.getId()).build());
        ldapOrganization.setName(organization.getName());
        ldapOrganization.setDescription(organization.getDescription());
        ldapOrganization.setRohiniId(organization.getRohiniId());
        ldapOrganization.setNtCode(organization.getNtCode());
        ldapOrganization.setContactNumber(organization.getContactNumber());
        ldapOrganization.setFaxNumber(organization.getFaxNumber());
        ldapOrganization.setEmailId(organization.getEmailId());
        ldapOrganization.setLandmark(organization.getLandmark());
        ldapOrganization.setCity(organization.getCity());
        ldapOrganization.setState(organization.getState());
        ldapOrganization.setPincode(organization.getPincode());
        ldapOrganization.setAddress(organization.getAddress());
        ldapOrganization.setSealFilePath(organization.getSealFilePath());
        ldapOrganization.setLogoFilePath(organization.getLogoFilePath());
        ldapOrganization.setActive(String.valueOf(organization.isActive()));
        ldapOrganization.setCreatedBy(organization.getCreatedBy());
        ldapOrganization.setModifiedBy(organization.getModifiedBy());
        ldapOrganization.setCreatedDate(DateUtil.convertInstantToLdapTimeStamp(organization.getCreatedDate()));
        ldapOrganization.setModifiedDate(DateUtil.convertInstantToLdapTimeStamp(organization.getModifiedDate()));
//        if (!CollectionUtils.isEmpty(organization.getMembers())) {
//            branchMembers = organization.getMembers().stream().map(String::valueOf).collect(Collectors.toList());
//        }
        return ldapOrganization;
    }
}
