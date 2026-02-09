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
package in.raster.ihms.authserver.domain.custom;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Custom class to map ldap branch.
 */
public class Branch extends BaseEntity{

    private String id;
    private String name;
    private String code;
    private String timeZone;
    private Long billingLimit;
    private String openingTime;
    private String closingTime;
    private String preferredLanguage;
    private String currency;
    private List<String> members;

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

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Long getBillingLimit() {
        return billingLimit;
    }

    public void setBillingLimit(Long billingLimit) {
        this.billingLimit = billingLimit;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;
        Branch branch = (Branch) o;
        return Objects.equals(getId(), branch.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Branch{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", code='" + code + '\'' +
            ", timeZone='" + timeZone + '\'' +
            ", billingLimit=" + billingLimit +
            ", openingTime='" + openingTime + '\'' +
            ", closingTime='" + closingTime + '\'' +
            ", preferredLanguage='" + preferredLanguage + '\'' +
            ", members=" + members +
            '}';
    }
}
