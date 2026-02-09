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
package in.raster.ihms.authserver.ldap.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.Objects;
import java.util.Set;

/**
 * Custom class to map department object from ldap.
 */
@Entry(objectClasses = {"top", "ihmsDepartment"})
public class LdapDepartment {

    @Id
    @JsonIgnore
    private Name dn;

    @Attribute(name = "cn")
    private String name;

    @Attribute(name = "code")
    private String code;

    @Attribute(name = "description")
    private String description;

    @Attribute(name = "parentDepartment")
    private String parentDepartment;

    @Attribute(name = "member")
    private Set<Name> members;

    @Attribute(name = "url")
    private String url;

    @Attribute(name = "isInvestigationDepartment")
    private String investigationDepartment;

    @Attribute(name = "externalDepartmentId")
    private String externalDepartmentId;

    @Attribute(name = "active")
    private String active;

    @Attribute(name = "createdBy")
    private String createdBy;

    @Attribute(name = "modifiedBy")
    private String modifiedBy;

    @Attribute(name = "createdDate")
    private String createdDate;

    @Attribute(name = "modifiedDate")
    private String modifiedDate;

    @Attribute(name = "barcodeIdentifier")
    private String barcodeIdentifier;

    @Attribute(name = "creditLimitEnabled")
    private String creditLimitEnabled;

    @Attribute(name = "displayOrder")
    private String displayOrder;

    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
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

    public Set<Name> getMembers() {
        return members;
    }

    public void setMembers(Set<Name> members) {
        this.members = members;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInvestigationDepartment() {
        return investigationDepartment;
    }

    public void setInvestigationDepartment(String investigationDepartment) {
        this.investigationDepartment = investigationDepartment;
    }

    public String getExternalDepartmentId() {
        return externalDepartmentId;
    }

    public void setExternalDepartmentId(String externalDepartmentId) {
        this.externalDepartmentId = externalDepartmentId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getBarcodeIdentifier() {
        return barcodeIdentifier;
    }

    public void setBarcodeIdentifier(String barcodeIdentifier) {
        this.barcodeIdentifier = barcodeIdentifier;
    }

    public String getCreditLimitEnabled() {
        return creditLimitEnabled;
    }

    public void setCreditLimitEnabled(String creditLimitEnabled) {
        this.creditLimitEnabled = creditLimitEnabled;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LdapDepartment)) return false;
        LdapDepartment that = (LdapDepartment) o;
        return Objects.equals(getDn(), that.getDn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDn());
    }

    @Override
    public String toString() {
        return "LdapDepartment{" +
            "dn=" + dn +
            ", name='" + name + '\'' +
            ", code='" + code + '\'' +
            ", description='" + description + '\'' +
            ", parentDepartment='" + parentDepartment + '\'' +
            ", members=" + members +
            ", url='" + url + '\'' +
            ", investigationDepartment='" + investigationDepartment + '\'' +
            ", externalDepartmentId='" + externalDepartmentId + '\'' +
            ", active='" + active + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            ", createdDate='" + createdDate + '\'' +
            ", modifiedDate='" + modifiedDate + '\'' +
            ", barcodeIdentifier='" + barcodeIdentifier + '\'' +
            ", creditLimitEnabled=" + creditLimitEnabled +
            ", displayOrder=" + displayOrder +
            '}';
    }
}
