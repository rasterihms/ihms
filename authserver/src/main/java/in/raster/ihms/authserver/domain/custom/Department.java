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
package in.raster.ihms.authserver.domain.custom;


import java.util.List;
import java.util.Objects;

/**
 * Custom class to map ldap department.
 */
public class Department extends BaseEntity{

    private String id;
    private String name;
    private String code;
    private String description;
    private String parentDepartment;
    private List<String> members;
    private String applicationName;
    private boolean investigationDepartment;
    private Long externalDepartmentId;
    private String barcodeIdentifier;
    private boolean creditLimitEnabled;
    private Integer displayOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(String parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public boolean isInvestigationDepartment() {
        return investigationDepartment;
    }

    public void setInvestigationDepartment(boolean investigationDepartment) {
        this.investigationDepartment = investigationDepartment;
    }

    public Long getExternalDepartmentId() {
        return externalDepartmentId;
    }

    public void setExternalDepartmentId(Long externalDepartmentId) {
        this.externalDepartmentId = externalDepartmentId;
    }

    public String getBarcodeIdentifier() {
        return barcodeIdentifier;
    }

    public void setBarcodeIdentifier(String barcodeIdentifier) {
        this.barcodeIdentifier = barcodeIdentifier;
    }

    public boolean isCreditLimitEnabled() {
        return creditLimitEnabled;
    }

    public void setCreditLimitEnabled(boolean creditLimitEnabled) {
        this.creditLimitEnabled = creditLimitEnabled;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Department{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", code='" + code + '\'' +
            ", description='" + description + '\'' +
            ", parentDepartment='" + parentDepartment + '\'' +
            ", members=" + members +
            ", applicationName='" + applicationName + '\'' +
            ", investigationDepartment=" + investigationDepartment +
            ", externalDepartmentId=" + externalDepartmentId +
            ", barcodeIdentifier='" + barcodeIdentifier + '\'' +
            ", creditLimitEnabled=" + creditLimitEnabled +
            ", displayOrder=" + displayOrder +
            '}';
    }
}
