package in.raster.ihms.authserver.web.rest;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.domain.MenuResource;
import in.raster.ihms.authserver.service.MenuResourceService;
import in.raster.ihms.authserver.web.rest.errors.BadRequestAlertException;
import in.raster.ihms.authserver.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MenuResource.
 */
@RestController
@RequestMapping("/api")
public class MenuResourceResource {

    private final Logger log = LoggerFactory.getLogger(MenuResourceResource.class);

    private static final String ENTITY_NAME = "menuResource";

    private final MenuResourceService menuResourceService;

    public MenuResourceResource(MenuResourceService menuResourceService) {
        this.menuResourceService = menuResourceService;
    }

    /**
     * POST  /menu-resources : Create a new menuResource.
     *
     * @param menuResource the menuResource to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuResource, or with status 400 (Bad Request) if the menuResource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menu-resources")
    @Timed
    public ResponseEntity<MenuResource> createMenuResource(@Valid @RequestBody MenuResource menuResource) throws URISyntaxException {
        log.debug("REST request to save MenuResource : {}", menuResource);
        if (menuResource.getId() != null) {
            throw new BadRequestAlertException("A new menuResource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuResource result = menuResourceService.save(menuResource);
        return ResponseEntity.created(new URI("/api/menu-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /menu-resources : Updates an existing menuResource.
     *
     * @param menuResource the menuResource to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuResource,
     * or with status 400 (Bad Request) if the menuResource is not valid,
     * or with status 500 (Internal Server Error) if the menuResource couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menu-resources")
    @Timed
    public ResponseEntity<MenuResource> updateMenuResource(@Valid @RequestBody MenuResource menuResource) throws URISyntaxException {
        log.debug("REST request to update MenuResource : {}", menuResource);
        if (menuResource.getId() == null) {
            return createMenuResource(menuResource);
        }
        MenuResource result = menuResourceService.save(menuResource);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuResource.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menu-resources : get all the menuResources.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of menuResources in body
     */
    @GetMapping("/menu-resources")
    @Timed
    public List<MenuResource> getAllMenuResources() {
        log.debug("REST request to get all MenuResources");
        return menuResourceService.findAll();
        }

    /**
     * GET  /menu-resources/:id : get the "id" menuResource.
     *
     * @param id the id of the menuResource to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuResource, or with status 404 (Not Found)
     */
    @GetMapping("/menu-resources/{id}")
    @Timed
    public ResponseEntity<MenuResource> getMenuResource(@PathVariable Long id) {
        log.debug("REST request to get MenuResource : {}", id);
        MenuResource menuResource = menuResourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(menuResource));
    }

    /**
     * DELETE  /menu-resources/:id : delete the "id" menuResource.
     *
     * @param id the id of the menuResource to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menu-resources/{id}")
    @Timed
    public ResponseEntity<Void> deleteMenuResource(@PathVariable Long id) {
        log.debug("REST request to delete MenuResource : {}", id);
        menuResourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
