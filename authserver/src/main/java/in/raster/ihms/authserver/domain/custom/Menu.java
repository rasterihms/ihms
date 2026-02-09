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
 * Custom class to map ldap menu.
 */
public class Menu extends BaseEntity {

    private String id;
    private String name;
    private String displayName;
    private Integer order;
    private String icon;
    private String url;
    private String parentMenu;
    private List<String> members;
    private String applicationName;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
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

    public String getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(String parentMenuId) {
        this.parentMenu = parentMenuId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", displayName='" + displayName + '\'' +
            ", order=" + order +
            ", icon='" + icon + '\'' +
            ", url='" + url + '\'' +
            ", parentMenu='" + parentMenu + '\'' +
            ", members=" + members +
            ", applicationName='" + applicationName + '\'' +
            '}';
    }
}
