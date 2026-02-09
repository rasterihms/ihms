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
package in.raster.ihms.authserver.service.custom;

import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.authserver.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Custom service to manage user dto related functions.
 */
public interface UserDtoCustomService {

    /**
     * Create user in ldap.
     *
     * @param userDto      - user dto object
     * @param userSignFile - user sign file
     * @return saved user
     */
    UserDto createUser(final UserDto userDto, final MultipartFile userSignFile);

    /**
     * Update user membership in ldap.
     *
     * @param userDto      - user dto object
     * @param userSignFile - user sign file
     * @return saved user dto
     */
    UserDto updateUser(final UserDto userDto, final MultipartFile userSignFile);

    /**
     * Get user dto list.
     *
     * @param userName - user name
     * @param pageable - pageable object
     * @return list of user dto
     */
    Page<UserDto> getUserDtoList(final String userName, final Pageable pageable);

    /**
     * Get user dto list.
     *
     * @param userName          - user name
     * @param organizationName  - organization name
     * @param branchName        - branch name
     * @param applicationName   - application name
     * @param roleName          - role name
     * @param departmentName    - department name
     * @param locationId        - location id
     * @param userType          - user type
     * @param pageable          - pageable object
     * @param active            - active
     * @return list of user dto
     */
    Page<UserDto> getUserDtosByFilter(final String userName, final String organizationName,
                                      final String branchName, final String applicationName,
                                      final String roleName, final String departmentName, final String locationId,
                                      final UserType userType, final Pageable pageable, final String active);
    /**
     * Get user dto list.
     *
     * @param userName - user name
     * @param pageable - pageable object
     * @return list of user dto
     */
    UserDto getUserDto(final String userName, final Pageable pageable);
}
