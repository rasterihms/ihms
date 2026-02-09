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
package in.raster.ihms.authserver.util;

/**
 * Constants file for authserver.
 */
public final class ExceptionConstants {
    private static final String EXP_NOT_FOUND = " not found";
    private static final String ALREADY_EXISTS = " already exists";
    private static final String EXP_ROLE = "Role";
    private static final String EXP_USER = "User";
    private static final String EXP_BRANCH = "Branch";
    private static final String EXP_DEPARTMENT = "Department";
    private static final String EXP_MENU = "Menu";
    private static final String EXP_RESOURCE = "Resource";
    public static final String ROLE_NOT_FOUND = EXP_ROLE + EXP_NOT_FOUND;
    public static final String USER_NOT_FOUND = EXP_USER + EXP_NOT_FOUND;
    public static final String USER_IS_NOT_ACTIVE = "User is not active";
    public static final String INCORRECT_USER_NAME_PASSWORD = "Incorrect username or password";
    public static final String USER_ALREADY_EXISTS = EXP_USER + ALREADY_EXISTS;
    public static final String BRANCH_ALREADY_EXISTS = EXP_BRANCH + ALREADY_EXISTS;
    public static final String DEPARTMENT_ALREADY_EXISTS = EXP_DEPARTMENT + ALREADY_EXISTS;
    public static final String ROLE_ALREADY_EXISTS = EXP_ROLE + ALREADY_EXISTS;
    public static final String MENU_ALREADY_EXISTS = EXP_MENU + ALREADY_EXISTS;
    public static final String RESOURCE_ALREADY_EXISTS = EXP_RESOURCE + ALREADY_EXISTS;
    public static final String NO_USER_FOUND_WITH_GIVEN_NAME = "No user found with username '%s'.";
    public static final String DEPARTMENT_CODE_ALREADY_EXISTS = "Department code already exist.";
    public static final String DEPARTMENT_DISPLAY_ORDER_ALREADY_EXISTS = "Department display order already exist.";
    public static final String DEFAULT_LOCATION_NOT_FOUND = "Default location" + EXP_NOT_FOUND;
    public static final String DEFAULT_LOCATION_EXISTS_IN_ADDITIONAL_LOCATION = "Default location exists in additional location, Please remove";
}
