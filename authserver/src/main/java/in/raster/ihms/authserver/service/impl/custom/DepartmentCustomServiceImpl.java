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

import in.raster.ihms.authserver.domain.custom.Department;
import in.raster.ihms.authserver.ldap.domain.LdapDepartment;
import in.raster.ihms.authserver.ldap.repository.LdapDepartmentRepository;
import in.raster.ihms.authserver.ldap.service.LdapCustomService;
import in.raster.ihms.authserver.mapper.LdapMapper;
import in.raster.ihms.authserver.service.custom.DepartmentCustomService;
import in.raster.ihms.authserver.util.AuthServerUtil;
import in.raster.ihms.authserver.util.ExceptionConstants;
import in.raster.ihms.authserver.util.ExceptionUtil;
import in.raster.ihms.authserver.util.TokenUtil;
import in.raster.ihms.commons.util.CollectionUtil;
import in.raster.ihms.commons.util.CommonConstants;
import in.raster.ihms.commons.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom service implementation to manage departments.
 */
@Service
public class DepartmentCustomServiceImpl implements DepartmentCustomService {

    private final LdapMapper ldapMapper = LdapMapper.INSTANCE_LDAP_MAPPER;
    private final LdapCustomService ldapCustomService;
    private final TokenUtil tokenUtil;
    private final LdapDepartmentRepository ldapDepartmentRepository;

    public DepartmentCustomServiceImpl(LdapCustomService ldapCustomService, TokenUtil tokenUtil,
                                       final LdapDepartmentRepository ldapDepartmentRepository) {
        this.ldapCustomService = ldapCustomService;
        this.tokenUtil = tokenUtil;
        this.ldapDepartmentRepository = ldapDepartmentRepository;
    }

    /**
     * Create department.
     *
     * @param department - department object
     * @param request    - http servlet request
     * @return distinguished name created in ldap
     */
    @Override
    public Department createDepartment(final Department department,
                                       final HttpServletRequest request) {
        if (!ObjectUtils.isEmpty(department.getCode())) {
            validateDepartmentCode(department.getCode());
        }
        if (!ObjectUtils.isEmpty(department.getDisplayOrder())) {
            validateDepartmentDisplayOrder(department.getDisplayOrder());
        }
        department.setCreatedBy(AuthServerUtil.getUser().getId());
        department.setModifiedBy(AuthServerUtil.getUser().getId());
        final String ldapDepartmentId = ldapCustomService.createOrganizationDepartment(department,
            tokenUtil.getOrganizationIdFromToken(request));
        department.setId(ldapDepartmentId);
        return department;
    }

    /**
     * Modify department attributes.
     *
     * @param department - department object
     * @param request    - http servlet request
     */
    @Override
    public void modifyDepartmentAttributes(final Department department, final HttpServletRequest request) {
        final LdapDepartment ldapDepartment = ldapDepartmentRepository.findByName(department.getName());
        if (!ObjectUtils.isEmpty(department.getDescription())) {
            if (!ObjectUtils.isEmpty(ldapDepartment.getDescription())) {
                if (!department.getDescription().equalsIgnoreCase(ldapDepartment.getDescription())) {
                    validateDepartmentDescription(department.getDescription());
                }
            } else {
                validateDepartmentDescription(department.getDescription());
            }
        }
        if (!ObjectUtils.isEmpty(department.getCode())) {
            if (!ObjectUtils.isEmpty(ldapDepartment.getCode())) {
                if (!ldapDepartment.getCode().equalsIgnoreCase(department.getCode())) {
                    validateDepartmentCode(department.getCode());
                }
            } else {
                validateDepartmentCode(department.getCode());
            }
        }
        if (!ObjectUtils.isEmpty(department.getDisplayOrder())) {
            if (!ObjectUtils.isEmpty(ldapDepartment.getDisplayOrder())) {
                if (!ldapDepartment.getDisplayOrder().equalsIgnoreCase(department.getDisplayOrder().toString())) {
                    validateDepartmentDisplayOrder(department.getDisplayOrder());
                }
            } else {
                validateDepartmentDisplayOrder(department.getDisplayOrder());
            }
        }
        department.setModifiedBy(AuthServerUtil.getUser().getId());
        ldapCustomService.modifyDepartmentAttributes(department, tokenUtil.getOrganizationIdFromToken(request));
    }

    /**
     * Validate department code
     *
     * @param code - code
     */
    private void validateDepartmentCode(final String code) {
        final List<LdapDepartment> ldapDepartmentList = ldapDepartmentRepository.findByCode(code);
        if (!CollectionUtils.isEmpty(ldapDepartmentList)) {
            ExceptionUtil.throwObjectAlreadyExistsException(ExceptionConstants.DEPARTMENT_CODE_ALREADY_EXISTS);
        }
    }

    /**
     * Validate department description
     *
     * @param description - description
     */
    private void validateDepartmentDescription(final String description) {
        final List<LdapDepartment> ldapDepartmentList = ldapDepartmentRepository.findByDescription(description);
        if (!CollectionUtils.isEmpty(ldapDepartmentList)) {
            ExceptionUtil.throwObjectAlreadyExistsException(ExceptionConstants.DEPARTMENT_ALREADY_EXISTS);
        }
    }

    /**
     * Validate department code
     *
     * @param displayOrder - display order
     */
    private void validateDepartmentDisplayOrder(final Integer displayOrder) {
        final List<LdapDepartment> ldapDepartmentList = ldapDepartmentRepository.findByDisplayOrder(displayOrder);
        if (!CollectionUtils.isEmpty(ldapDepartmentList)) {
            ExceptionUtil.throwObjectAlreadyExistsException(ExceptionConstants.DEPARTMENT_DISPLAY_ORDER_ALREADY_EXISTS);
        }
    }


    /**
     * Get all pageable departments.
     *
     * @param departmentName - department name to search
     * @param pageable       - pageable object
     * @param request        - http servlet request
     * @return list of departments
     */
    @Override
    public Page<Department> loadDepartments(final String departmentName, final Pageable pageable,
                                            final HttpServletRequest request) {
        final List<LdapDepartment> ldapDepartmentList = ldapCustomService
            .getDepartments(departmentName, tokenUtil.getOrganizationIdFromToken(request), null, null);
        final List<Department> departmentList = ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
        final Comparator<Department> departmentNameComparator = PaginationUtil.getIgnoreCaseComparator(Department::getDescription, pageable);
        return PaginationUtil.doCustomPaging(pageable, departmentList, departmentNameComparator);
    }

    /**
     * Get active departments.
     *
     * @param request - http servlet request
     * @return list of departments
     */
    @Override
    public List<Department> getActiveDepartments(final HttpServletRequest request) {
        final List<LdapDepartment> ldapDepartmentList = ldapCustomService
            .getDepartments(null, tokenUtil.getOrganizationIdFromToken(request), CommonConstants.TRUE, null);
        return ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
    }

    /**
     * Get credit limit enabled departments.
     *
     * @param request - http servlet request
     * @return list of departments
     */
    @Override
    public List<Department> getCreditLimitEnabledDepartments(final HttpServletRequest request) {
        final List<LdapDepartment> ldapDepartmentList = ldapCustomService
            .getDepartments(null, tokenUtil.getOrganizationIdFromToken(request), CommonConstants.TRUE, CommonConstants.TRUE);
        return ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
    }

    /**
     * Get all sub departments.
     *
     * @param subDepartmentName - sub department name to search
     * @param pageable          - pageable object
     * @param request           - http servlet request
     * @return list of departments
     */
    @Override
    public Page<Department> loadSubDepartments(final String subDepartmentName, final Pageable pageable,
                                               final HttpServletRequest request) {
        final List<LdapDepartment> ldapDepartmentList = ldapCustomService.getSubDepartments(subDepartmentName,
            null, tokenUtil.getOrganizationIdFromToken(request), null);
        final List<Department> subDepartmentList = ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
        final Comparator<Department> departmentNameComparator = PaginationUtil.getComparator(Department::getName, pageable);
        return PaginationUtil.doCustomPaging(pageable, subDepartmentList, departmentNameComparator);
    }

    /**
     * Get active sub departments by parent department.
     *
     * @param parentDepartment - parent department name
     * @param request          - http servlet request
     * @return list of departments
     */
    @Override
    public List<Department> getActiveSubDepartmentsByDepartment(final String parentDepartment,
                                                                final HttpServletRequest request) {
        final List<LdapDepartment> ldapDepartmentList = ldapCustomService.getSubDepartments(null,
            parentDepartment, tokenUtil.getOrganizationIdFromToken(request), CommonConstants.TRUE);
        return ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
    }

    /**
     * Get sub departments by parent department.
     *
     * @param subDepartment    - sub department name
     * @param parentDepartment - parent department name
     * @param request          - http servlet request
     * @return list of departments
     */
    @Override
    public List<Department> getSubDepartmentsByDepartment(final String subDepartment, final String parentDepartment, final Boolean isActive,
                                                          final HttpServletRequest request) {
        final List<LdapDepartment> ldapDepartmentList = ldapCustomService.getSubDepartments(subDepartment,
            parentDepartment, tokenUtil.getOrganizationIdFromToken(request), isActive);
        final List<Department> departmentList = ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
        departmentList.sort(Comparator.comparing(Department::getDescription, String::compareToIgnoreCase));
        return departmentList;
    }

    /**
     * Method to find department by name.
     *
     * @param departmentName   - department name
     * @param organizationName - organization name
     * @return ldap department
     */
    @Override
    public Department getDepartmentByName(final String departmentName, final String organizationName) {
        final List<LdapDepartment> ldapDepartmentList = ldapCustomService.getDepartmentByName(departmentName,
            organizationName);
        final List<Department> departmentList = ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
        if (!CollectionUtils.isEmpty(departmentList)) {
            return departmentList.get(CommonConstants.ZERO);
        } else {
            return null;
        }
    }

    /**
     * Get investigational departments.
     *
     * @param organizationName - organization name
     * @return list of departments
     */
    @Override
    public List<Department> getInvestigationalDepartments(final String organizationName) {
        final List<LdapDepartment> ldapDepartmentList = ldapCustomService
            .getInvestigationalDepartments(organizationName, CommonConstants.TRUE);
        return ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
    }

    /**
     * Get Max Display Order
     *
     * @return - display order
     */
    @Override
    public Integer getMaxDisplayOrder(final HttpServletRequest request) {
        final Integer maxDisplayOrder;
        final List<LdapDepartment> ldapDepartmentList = ldapCustomService
            .getDepartments(null, tokenUtil.getOrganizationIdFromToken(request), null, null);
        final List<Department> departmentList = ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
        if (!CollectionUtils.isEmpty(ldapDepartmentList)) {
            maxDisplayOrder = departmentList.stream().max(Comparator.comparing(Department::getDisplayOrder, Comparator.nullsFirst(Comparator.naturalOrder()))).get().getDisplayOrder();
        } else {
            maxDisplayOrder = CommonConstants.ONE;
        }
        return maxDisplayOrder;
    }

    /**
     * Load all departments.
     *
     * @param name    - name
     * @param request - http request
     * @return department list
     */
    @Override
    public List<Department> loadDepartments(final String name, final Boolean isActive, final HttpServletRequest request) {
        final List<LdapDepartment> ldapDepartmentList;
        if (!ObjectUtils.isEmpty(name)) {
            ldapDepartmentList = ldapCustomService.getDepartmentsByDescription(name,
                tokenUtil.getOrganizationIdFromToken(request), isActive, null);
        } else {
            ldapDepartmentList = ldapCustomService.getDepartmentsByDescription(null,
                tokenUtil.getOrganizationIdFromToken(request), isActive, null);
        }
        final List<Department> departmentList = ldapMapper.constructDepartmentListFromLdapDepartments(ldapDepartmentList);
        departmentList.sort(Comparator.nullsLast(Comparator.nullsLast(Comparator.comparing(Department::getDisplayOrder,
            Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(Comparator.comparing(Department::getDescription)))
        ));
        return departmentList;
    }
}
