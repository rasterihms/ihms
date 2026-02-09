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
 * Custom class to map role from ldap.
 */
@Entry(objectClasses = {"top", "ihmsRole"})
public class LdapRole {

    @Id
    @JsonIgnore
    private Name dn;

    @Attribute(name = "cn")
    private String name;

    @Attribute(name = "member")
    private Set<Name> members;

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

    public Set<Name> getMembers() {
        return members;
    }

    public void setMembers(Set<Name> members) {
        this.members = members;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LdapRole)) return false;
        LdapRole ldapRole = (LdapRole) o;
        return Objects.equals(getDn(), ldapRole.getDn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDn());
    }

    @Override
    public String toString() {
        return "LdapRole{" +
            "dn=" + dn +
            ", name='" + name + '\'' +
            ", members=" + members +
            ", active='" + active + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            ", createdDate='" + createdDate + '\'' +
            ", modifiedDate='" + modifiedDate + '\'' +
            '}';
    }
}
