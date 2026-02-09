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
import in.raster.ihms.authserver.domain.custom.Menu;
import in.raster.ihms.authserver.service.custom.MenuCustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Custom resource to manipulate menus.
 */
@RestController
@RequestMapping("/api")
public class MenuCustomResource {

    private final MenuCustomService menuCustomService;

    public MenuCustomResource(MenuCustomService menuCustomService) {
        this.menuCustomService = menuCustomService;
    }

    /**
     * Get all menus by application id.
     *
     * @param applicationName - application name
     * @param menuName        - menu name
     * @param parentMenuName  - parent menu name
     * @param active          - active flag
     * @param pageable        - pageable object
     * @return list of menus
     */
    @GetMapping("/menus-page")
    @Timed
    public Page<Menu> getPageableMenus(@RequestParam(value = "application") final String applicationName,
                                       @RequestParam(value = "menuName", required = false) final String menuName,
                                       @RequestParam(value = "parentMenu", required = false) final String parentMenuName,
                                       @RequestParam(value = "active", required = false) final Boolean active,
                                       final Pageable pageable) {
        return menuCustomService.getPageableMenus(applicationName, menuName, parentMenuName, active, pageable);
    }

    /**
     * Get sub menus by parent menu.
     *
     * @param applicationName - application name
     * @param parentMenuName  - parent menu name
     * @param active          - active flag
     * @return list of menus
     */
    @GetMapping("/menus-by-parent-menu")
    @Timed
    public List<Menu> getMenusByParentMenu(@RequestParam(value = "application") final String applicationName,
                                           @RequestParam(value = "parentMenu", required = false) final String parentMenuName,
                                           @RequestParam(value = "active", required = false) final Boolean active) {
        return menuCustomService.getMenus(applicationName, null, parentMenuName, active);
    }

    /**
     * Get parent menus by application.
     *
     * @param applicationName - application name
     * @return list of menus
     */
    @GetMapping("/menus-parent")
    @Timed
    public List<Menu> getParentMenus(@RequestParam(value = "application") final String applicationName) {
        return menuCustomService.getParentMenus(applicationName);
    }

    /**
     * Get sub menus by application.
     *
     * @param applicationName - application name
     * @return list of menus
     */
    @GetMapping("/menus-sub")
    @Timed
    public List<Menu> getSubMenus(@RequestParam(value = "application") final String applicationName) {
        return menuCustomService.getSubMenus(applicationName);
    }

    /**
     * Save menu.
     *
     * @param menu - menu object
     * @return saved menu
     */
    @PostMapping("/menus-save")
    @Timed
    public ResponseEntity<Menu> saveMenu(@Valid @RequestBody final Menu menu) {
        final Menu savedMenu = menuCustomService.saveMenu(menu);
        return new ResponseEntity<>(savedMenu, HttpStatus.OK);
    }

    /**
     * Modify menu attributes.
     *
     * @param menu - menu object
     */
    @PutMapping("/menus-update")
    @Timed
    public ResponseEntity<Menu> modifyMenuAttributes(@Valid @RequestBody final Menu menu) {
        final Menu updatedMenu = menuCustomService.modifyMenuAttributes(menu);
        return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
    }
}
