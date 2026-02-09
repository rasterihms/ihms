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
package in.raster.ihms.authserver.web.rest.custom;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.service.custom.MenuDtoCustomService;
import in.raster.ihms.authserver.service.dto.MenuDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Custom resource to manipulate menu dtos.
 */
@RestController
@RequestMapping("/api")
public class MenuDtoCustomResource {

    private final MenuDtoCustomService menuDtoCustomService;

    public MenuDtoCustomResource(MenuDtoCustomService menuDtoCustomService) {
        this.menuDtoCustomService = menuDtoCustomService;
    }

    /**
     * Load all menu's for the given application.
     *
     * @param applicationName - application name
     * @return RoleDto
     */
    @GetMapping("/menu-dtos")
    @Timed
    public List<MenuDto> loadMenusAndResourceByApplication(
        @RequestParam(value = "application") final String applicationName) {
        return menuDtoCustomService.loadMenusAndResourceByApplication(applicationName);
    }
}
