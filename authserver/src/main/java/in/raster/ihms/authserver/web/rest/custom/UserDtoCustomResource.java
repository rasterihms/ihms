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
package in.raster.ihms.authserver.web.rest.custom;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.ldap.enumeration.UserType;
import in.raster.ihms.authserver.service.custom.UserDtoCustomService;
import in.raster.ihms.authserver.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Custom resource to manage user related functions.
 */
@RestController
@RequestMapping("/api")
public class UserDtoCustomResource {

    private final UserDtoCustomService userDtoCustomService;

    public UserDtoCustomResource(UserDtoCustomService userDtoCustomService) {
        this.userDtoCustomService = userDtoCustomService;
    }

    /**
     * Create user in ldap.
     *
     * @param userDto      - user dto object
     * @param userSignFile - user sign file
     * @return saved user
     */
    @PostMapping("/user-dtos")
    @Timed
    public ResponseEntity<UserDto> createUser(@RequestPart final UserDto userDto,
                                              @RequestPart(value = "file", required = false) final MultipartFile userSignFile) {
        final UserDto savedUserDto = userDtoCustomService.createUser(userDto, userSignFile);
        return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
    }

    /**
     * Update user membership in ldap.
     *
     * @param userDto      - user dto object
     * @param userSignFile - user sign file
     * @return saved user dto
     */
    @PutMapping("/user-dtos")
    @Timed
    public ResponseEntity<UserDto> updateUser(@RequestPart final UserDto userDto,
                                              @RequestPart(value = "file", required = false) final MultipartFile userSignFile) {
        final UserDto savedUserDto = userDtoCustomService.updateUser(userDto, userSignFile);
        return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
    }

    /**
     * Update user membership in ldap.
     *
     * @param userDto      - user dto object
     * @param userSignFile - user sign file
     * @return saved user dto
     */
    @PostMapping("/user-dtos-update")
    @Timed
    public ResponseEntity<UserDto> updateUserDetail(@RequestPart final UserDto userDto,
                                                    @RequestPart(value = "file", required = false) final MultipartFile userSignFile) {
        final UserDto savedUserDto = userDtoCustomService.updateUser(userDto, userSignFile);
        return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
    }

    /**
     * Get user dto list.
     *
     * @param userName - user name
     * @param pageable - pageable object
     * @return list of user dto
     */
    @GetMapping("/user-dtos-page")
    @Timed
    public Page<UserDto> getUserDtoList(@RequestParam(value = "userName", required = false) final String userName,
                                        final Pageable pageable) {
        return userDtoCustomService.getUserDtoList(userName, pageable);
    }

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
     * @param active            - active
     * @param pageable          - pageable object
     * @return list of user dto
     */
    @GetMapping("/user-dtos-page-filter")
    @Timed
    public Page<UserDto> getUserDtosByFilter(@RequestParam(value = "userName", required = false) final String userName,
                                             @RequestParam(value = "organizationName", required = false) final String organizationName,
                                             @RequestParam(value = "branchName", required = false) final String branchName,
                                             @RequestParam(value = "applicationName", required = false) final String applicationName,
                                             @RequestParam(value = "roleName", required = false) final String roleName,
                                             @RequestParam(value = "departmentName", required = false) final String departmentName,
                                             @RequestParam(value = "locationId", required = false) final String locationId,
                                             @RequestParam(value = "userType", required = false) final UserType userType,
                                             @RequestParam(value = "active", required = false) final String active,
                                             final Pageable pageable) {
        return userDtoCustomService.getUserDtosByFilter(userName, organizationName, branchName, applicationName,
            roleName, departmentName, locationId, userType, pageable, active);
    }

    /**
     * Get user dto list.
     *
     * @param userName - user name
     * @param pageable - pageable object
     * @return list of user dto
     */
    @GetMapping("/user-dtos")
    @Timed
    public UserDto getUserDto(@RequestParam(value = "userName") final String userName,
                              final Pageable pageable) {
        return userDtoCustomService.getUserDto(userName, pageable);
    }
}
