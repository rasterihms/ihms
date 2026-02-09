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

import in.raster.ihms.authserver.domain.custom.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Custom service to manage departments.
 */
public interface DepartmentCustomService {

    /**
     * Create department.
     *
     * @param department - department object
     * @param request    - http servlet request
     * @return distinguished name created in ldap
     */
    Department createDepartment(final Department department, final HttpServletRequest request);

    /**
     * Modify department attributes.
     *
     * @param department - department object
     * @param request    - http servlet request
     */
    void modifyDepartmentAttributes(final Department department, final HttpServletRequest request);

    /**
     * Get all departments.
     *
     * @param departmentName - department name to search
     * @param pageable       - pageable object
     * @param request        - http servlet request
     * @return list of departments
     */
    Page<Department> loadDepartments(final String departmentName, final Pageable pageable, final HttpServletRequest request);

    /**
     * Get active departments.
     *
     * @param request - http servlet request
     * @return list of departments
     */
    List<Department> getActiveDepartments(final HttpServletRequest request);

    /**
     * Get credit limit enabled departments.
     *
     * @param request - http servlet request
     * @return list of departments
     */
    List<Department> getCreditLimitEnabledDepartments(final HttpServletRequest request);

    /**
     * Get all sub departments.
     *
     * @param subDepartmentName - sub department name to search
     * @param pageable          - pageable object
     * @param request           - http servlet request
     * @return list of departments
     */
    Page<Department> loadSubDepartments(final String subDepartmentName, final Pageable pageable, final HttpServletRequest request);

    /**
     * Get active sub departments by parent department.
     *
     * @param parentDepartment - parent department name
     * @param request          - http servlet request
     * @return list of departments
     */
    List<Department> getActiveSubDepartmentsByDepartment(final String parentDepartment, final HttpServletRequest request);

    /**
     * Get sub departments by parent department.
     *
     * @param subDepartment    - sub department name
     * @param parentDepartment - parent department name
     * @param request          - http servlet request
     * @return list of departments
     */
    List<Department> getSubDepartmentsByDepartment(final String subDepartment, final String parentDepartment, final Boolean isActive, final HttpServletRequest request);

    /**
     * Method to find department by name.
     *
     * @param departmentName   - department name
     * @param organizationName - organization name
     * @return ldap department
     */
    Department getDepartmentByName(final String departmentName, final String organizationName);

    /**
     * Get investigational departments.
     *
     * @param organizationName - organization name
     * @return list of departments
     */
    List<Department> getInvestigationalDepartments(final String organizationName);

    /**
     * Get Max Display Order
     *
     * @return - display order
     */
    Integer getMaxDisplayOrder(final HttpServletRequest request);

    /**
     * Load all departments.
     *
     * @param name    - name
     * @param request - http request
     * @return department list
     */
    List<Department> loadDepartments(final String name, final Boolean isActive, final HttpServletRequest request);
}
