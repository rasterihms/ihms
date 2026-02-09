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

import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Custom class to map ldap organization.
 */
public class Organization extends BaseEntity{

    private String id;
    private String name;
    private String description;
    private String rohiniId;
    private String ntCode;
    private String contactNumber;
    private String faxNumber;
    private String emailId;
    private String landmark;
    private String city;
    private String state;
    private String pincode;
    private String address;
    private String sealFilePath;
    private String logoFilePath;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRohiniId() {
        return rohiniId;
    }

    public void setRohiniId(String rohiniId) {
        this.rohiniId = rohiniId;
    }

    public String getNtCode() {
        return ntCode;
    }

    public void setNtCode(String ntCode) {
        this.ntCode = ntCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSealFilePath() {
        return sealFilePath;
    }

    public void setSealFilePath(String sealFilePath) {
        this.sealFilePath = sealFilePath;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getLogoFilePath() {
        return logoFilePath;
    }

    public void setLogoFilePath(String logoFilePath) {
        this.logoFilePath = logoFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Organization{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", rohiniId='" + rohiniId + '\'' +
            ", ntCode='" + ntCode + '\'' +
            ", contactNumber='" + contactNumber + '\'' +
            ", faxNumber='" + faxNumber + '\'' +
            ", emailId='" + emailId + '\'' +
            ", landmark='" + landmark + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", pincode='" + pincode + '\'' +
            ", address='" + address + '\'' +
            ", sealFilePath='" + sealFilePath + '\'' +
            ", logoFilePath='" + logoFilePath + '\'' +
            ", members=" + members +
            ", active='" + active + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            ", createdDate=" + createdDate +
            ", modifiedDate=" + modifiedDate +
            '}';
    }
}
