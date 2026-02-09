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
package in.raster.ihms.authserver.ldap.util;

/**
 * Defines the constants to be used for ldap operations.
 */
public enum ApacheDSLdapDirectories {

    COUNTRY_NAME("c"),
    DOMAIN_COMPONENT("dc"),
    GIVEN_NAME("gn"),
    ORGANIZATION("organization"),
    ORGANIZATION_NAME("o"),
    MAIL("mail"),
    MOBILE("mobile"),
    MEMBER("member"),
    PERSON("person"),
    COMMON_NAME("cn"),
    DISPLAY_NAME("displayName"),
    SUR_NAME("sn"),
    ORGANISATIONAL_UNIT("ou"),
    ORGANISATIONAL_UNIT_CLASS("organizationalUnit"),
    UID("uid"),
    USER_PASSWORD("userPassword"),
    TOP("top"),
    ORGANIZATIONAL_PERSON("organizationalPerson"),
    INET_ORG_PERSON("inetOrgPerson"),
    APPLICATION_ENTITY("applicationEntity"),
    GROUP_OF_NAMES("groupOfNames"),
    BASE_ENTITY("baseEntity"),
    IHMS_APPLICATION("ihmsApplication"),
    IHMS_ROLE("ihmsRole"),
    IHMS_MENU("ihmsMenu"),
    IHMS_RESOURCE("ihmsResource"),
    IHMS_DEPARTMENT("ihmsDepartment"),
    IHMS_USER("ihmsUser"),
    CODE("code"),
    DESCRIPTION("description"),
    OBJECTCLASS("objectclass"),
    URL("url"),
    MENU_ORDER("menuOrder"),
    MENU_ICON("menuIcon"),
    PARENT_MENU("parentMenu"),
    PARENT_DEPARTMENT("parentDepartment"),
    MENU_ROOT("Menus"),
    ROLE_ROOT("Roles"),
    PERMISSION_TYPE("permissionType"),
    TIME_ZONE("timeZone"),
    BILLING_LIMIT("billingLimit"),
    IS_CREDIT_AUTHORIZED_USER("isCreditAuthorizedUser"),
    CREDIT_AMOUNT_LIMIT("creditAmountLimit"),
    IS_DOCTOR("isDoctor"),
    DOCTOR_ID("doctorId"),
    OPENING_TIME("openingTime"),
    CLOSING_TIME("closingTime"),
    ROHINI_ID("rohiniId"),
    HOSPITAL_NT_CODE("ntCode"),
    TELEPHONE_NUMBER("telephoneNumber"),
    FAX_NUMBER("facsimileTelephoneNumber"),
    LANDMARK("landmark"),
    CITY("city"),
    STATE("st"),
    PINCODE("postalCode"),
    REGISTERED_ADDRESS("registeredAddress"),
    SEAL_FILE_PATH("sealFilePath"),
    LOGO_FILE_PATH("logoFilePath"),
    DEPARTMENT_NUMBER("departmentNumber"),
    SIGN_FILE_PATH("signFilePath"),
    PHOTO_FILE_PATH("photoFilePath"),
    EXTERNAL_USER_ID("externalUserId"),
    IS_INVESTIGATION_DEPARTMENT("isInvestigationDepartment"),
    EXTERNAL_DEPARTMENT_ID("externalDepartmentId"),
    BARCODE_IDENTIFIER("barcodeIdentifier"),
    DISPLAY_ORDER("displayOrder"),
    CREDIT_LIMIT_ENABLED("creditLimitEnabled"),
    DEFAULT_LOCATION_ID("defaultLocationId"),
    LOCATION_IDS("locationIds"),
    THEME_COLOR("themeColor"),
    EMPLOYEE_NUMBER("employeeNumber"),
    USER_TYPE("userType"),
    PWD_ACCOUNT_LOCKED_TIME("pwdAccountLockedTime"),
    PREFERRED_LANGUAGE("preferredLanguage"),
    CURRENCY("currency"),
    ACTIVE("active"),
    CREATED_BY("createdBy"),
    MODIFIED_BY("modifiedBy"),
    CREATED_DATE("createdDate"),
    MODIFIED_DATE("modifiedDate");

    private String attributeValue;

    ApacheDSLdapDirectories(String ldabAttributeValue) {
        this.attributeValue = ldabAttributeValue;
    }

    public String getAttribute() {
        return attributeValue;
    }
}
