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
 * Custom class to map menu from ldap.
 */
@Entry(objectClasses = {"top", "ihmsMenu"})
public class LdapMenu {

    @Id
    @JsonIgnore
    private Name dn;

    @Attribute(name = "cn")
    private String name;

    @Attribute(name = "displayName")
    private String displayName;

    @Attribute(name = "menuOrder")
    private String order;

    @Attribute(name = "menuIcon")
    private String icon;

    @Attribute(name = "url")
    private String url;

    @Attribute(name = "parentMenu")
    private String parentMenuId;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
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
        if (!(o instanceof LdapMenu)) return false;
        LdapMenu ldapMenu = (LdapMenu) o;
        return Objects.equals(getDn(), ldapMenu.getDn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDn());
    }

    @Override
    public String toString() {
        return "LdapMenu{" +
            "dn=" + dn +
            ", name='" + name + '\'' +
            ", displayName='" + displayName + '\'' +
            ", order='" + order + '\'' +
            ", icon='" + icon + '\'' +
            ", url='" + url + '\'' +
            ", parentMenuId='" + parentMenuId + '\'' +
            ", members=" + members +
            ", active='" + active + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            ", createdDate='" + createdDate + '\'' +
            ", modifiedDate='" + modifiedDate + '\'' +
            '}';
    }
}
