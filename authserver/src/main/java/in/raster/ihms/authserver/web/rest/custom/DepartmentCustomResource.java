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
import in.raster.ihms.authserver.domain.custom.Department;
import in.raster.ihms.authserver.service.custom.DepartmentCustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Custom resource to manage departments.
 */
@RestController
@RequestMapping("/api")
public class DepartmentCustomResource {

    private final DepartmentCustomService departmentCustomService;

    public DepartmentCustomResource(DepartmentCustomService departmentCustomService) {
        this.departmentCustomService = departmentCustomService;
    }

    /**
     * Create department.
     *
     * @param department - department object
     * @param request    - http servlet request
     * @return distinguished name created in ldap
     */
    @PostMapping("/departments")
    @Timed
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody final Department department,
                                                       final HttpServletRequest request) {
        final Department savedDepartment = departmentCustomService.createDepartment(department, request);
        return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
    }

    /**
     * Modify department attributes.
     *
     * @param department - department object
     * @param request    - http servlet request
     */
    @PutMapping("/departments")
    @Timed
    public void modifyDepartmentAttributes(@Valid @RequestBody final Department department,
                                           final HttpServletRequest request) {
        departmentCustomService.modifyDepartmentAttributes(department, request);
    }

    /**
     * Get all departments.
     *
     * @param departmentName - department name to search
     * @param pageable       - pageable object
     * @param request        - http servlet request
     * @return list of departments
     */
    @GetMapping("/departments-page")
    @Timed
    public Page<Department> loadDepartments(@RequestParam(value = "name", required = false) final String departmentName,
                                            final Pageable pageable, final HttpServletRequest request) {
        return departmentCustomService.loadDepartments(departmentName, pageable, request);
    }

    /**
     * Get active departments.
     *
     * @param request - http servlet request
     * @return list of departments
     */
    @GetMapping("/departments-active")
    @Timed
    public List<Department> getActiveDepartments(final HttpServletRequest request) {
        return departmentCustomService.getActiveDepartments(request);
    }


    /**
     * Get credit limit enabled departments.
     *
     * @param request - http servlet request
     * @return list of departments
     */
    @GetMapping("/departments-credit-limit")
    @Timed
    public List<Department> getCreditLimitEnabledDepartments(final HttpServletRequest request) {
        return departmentCustomService.getCreditLimitEnabledDepartments(request);
    }

    /**
     * Get all sub departments.
     *
     * @param subDepartmentName - sub department name to search
     * @param pageable          - pageable object
     * @param request           - http servlet request
     * @return list of departments
     */
    @GetMapping("/departments-sub-departments-page")
    @Timed
    public Page<Department> loadSubDepartments(@RequestParam(value = "name", required = false) final String subDepartmentName,
                                               final Pageable pageable, final HttpServletRequest request) {
        return departmentCustomService.loadSubDepartments(subDepartmentName, pageable, request);
    }

    /**
     * Get active sub departments by parent department.
     *
     * @param parentDepartment - parent department name
     * @param request          - http servlet request
     * @return list of departments
     */
    @GetMapping("/departments-sub-departments-active")
    @Timed
    public List<Department> getActiveSubDepartmentsByDepartment(
        @RequestParam(value = "parentDepartment") final String parentDepartment,
        final HttpServletRequest request) {
        return departmentCustomService.getActiveSubDepartmentsByDepartment(parentDepartment, request);
    }

    /**
     * Method to find department by name.
     *
     * @param departmentName   - department name
     * @param organizationName - organization name
     * @return ldap department
     */
    @GetMapping("/departments-by-name")
    @Timed
    public Department getDepartmentByName(@RequestParam(value = "departmentName", required = false) final String departmentName,
                                          @RequestParam(value = "organizationName", required = false) final String organizationName) {
        return departmentCustomService.getDepartmentByName(departmentName, organizationName);
    }

    /**
     * Get investigational departments.
     *
     * @param organizationName - organization name
     * @return list of departments
     */
    @GetMapping("/departments-investigation")
    @Timed
    public List<Department> getInvestigationalDepartments(@RequestParam(value = "organizationName", required = false) final String organizationName) {
        return departmentCustomService.getInvestigationalDepartments(organizationName);
    }

    /**
     * Get Max Display Number
     *
     * @return display order
     */
    @GetMapping("/departments-max-display-order")
    @Timed
    public Integer getMaxDisplayNumber(final HttpServletRequest request) {
        return departmentCustomService.getMaxDisplayOrder(request);
    }

    /**
     * Load all departments.
     *
     * @param name    - name
     * @param request - http request
     * @return department list
     */
    @GetMapping("/departments-all")
    @Timed
    public List<Department> loadDepartments(@RequestParam(value = "name", required = false) final String name,
                                            @RequestParam(value = "isActive", required = false) final Boolean isActive,
                                            final HttpServletRequest request) {
        return departmentCustomService.loadDepartments(name, isActive, request);
    }

    /**
     * Get sub departments by parent department.
     *
     * @param parentDepartment - parent department name
     * @param request          - http servlet request
     * @return list of departments
     */
    @GetMapping("/departments-sub-departments")
    @Timed
    public List<Department> getSubDepartmentsByDepartment(
        @RequestParam(value = "subDepartment", required = false) final String subDepartment,
        @RequestParam(value = "parentDepartment") final String parentDepartment,
        @RequestParam(value = "isActive", required = false) final Boolean isActive,
        final HttpServletRequest request) {
        return departmentCustomService.getSubDepartmentsByDepartment(subDepartment, parentDepartment, isActive, request);
    }
}
