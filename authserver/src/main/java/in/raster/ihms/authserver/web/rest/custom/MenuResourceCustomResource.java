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
import in.raster.ihms.authserver.domain.custom.Menu;
import in.raster.ihms.authserver.domain.custom.Resource;
import in.raster.ihms.authserver.service.custom.MenuResourceCustomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.RegEx;
import javax.validation.Valid;
import java.util.List;

/**
 * Custom resource to manipulate menu resource.
 */
@RestController
@RequestMapping("/api")
public class MenuResourceCustomResource {

    private final MenuResourceCustomService menuResourceCustomService;

    public MenuResourceCustomResource(MenuResourceCustomService menuResourceCustomService) {
        this.menuResourceCustomService = menuResourceCustomService;
    }

    /**
     * Get all resources by application.
     *
     * @param applicationName - application name
     * @param resourceName    - resource name to search
     * @param menuName        - menu name
     * @param pageable        - pageable object
     * @return list of resources
     */
    @GetMapping("/resources-by-application")
    @Timed
    public Page<Resource> getApplicationResources(@RequestParam(value = "application") final String applicationName,
                                                  @RequestParam(value = "resourceName", required = false) final String resourceName,
                                                  @RequestParam(value = "menuName", required = false) final String menuName, final Pageable pageable) {
        return menuResourceCustomService.getApplicationResources(applicationName, resourceName, menuName, pageable);
    }

    /**
     * Save resource.
     *
     * @param resource - resource object
     * @return saved resource
     */
    @PostMapping("/resources-save")
    @Timed
    public ResponseEntity<Resource> saveResource(@Valid @RequestBody final Resource resource) {
        final Resource savedResource = menuResourceCustomService.saveResource(resource);
        return new ResponseEntity<>(savedResource, HttpStatus.OK);
    }

    /**
     * Modify resource attributes.
     *
     * @param resource - resource object
     * @return updated resource
     */
    @PutMapping("/resources-update")
    @Timed
    public ResponseEntity<Resource> modifyResourceAttributes(@Valid @RequestBody final Resource resource) {
        final Resource updatedResource = menuResourceCustomService.modifyResourceAttributes(resource);
        return new ResponseEntity<>(updatedResource, HttpStatus.OK);
    }

    /**
     * Delete resource by given dn.
     *
     * @param resourceName    - resource name
     * @param applicationName - application name
     * @param menuName        - menu name
     */
    @DeleteMapping("/resources-delete/{resourceName}")
    @Timed
    public void deleteApplicationResource(@PathVariable final String resourceName,
                                          @RequestParam(value = "application") final String applicationName,
                                          @RequestParam(value = "menuName") final String menuName) {
        menuResourceCustomService.deleteApplicationResource(resourceName, applicationName, menuName);
    }
}
