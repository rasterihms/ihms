package in.raster.ihms.authserver.web.rest;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.domain.RoleResource;
import in.raster.ihms.authserver.service.RoleResourceService;
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
 * REST controller for managing RoleResource.
 */
@RestController
@RequestMapping("/api")
public class RoleResourceResource {

    private final Logger log = LoggerFactory.getLogger(RoleResourceResource.class);

    private static final String ENTITY_NAME = "roleResource";

    private final RoleResourceService roleResourceService;

    public RoleResourceResource(RoleResourceService roleResourceService) {
        this.roleResourceService = roleResourceService;
    }

    /**
     * POST  /role-resources : Create a new roleResource.
     *
     * @param roleResource the roleResource to create
     * @return the ResponseEntity with status 201 (Created) and with body the new roleResource, or with status 400 (Bad Request) if the roleResource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/role-resources")
    @Timed
    public ResponseEntity<RoleResource> createRoleResource(@Valid @RequestBody RoleResource roleResource) throws URISyntaxException {
        log.debug("REST request to save RoleResource : {}", roleResource);
        if (roleResource.getId() != null) {
            throw new BadRequestAlertException("A new roleResource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleResource result = roleResourceService.save(roleResource);
        return ResponseEntity.created(new URI("/api/role-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /role-resources : Updates an existing roleResource.
     *
     * @param roleResource the roleResource to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated roleResource,
     * or with status 400 (Bad Request) if the roleResource is not valid,
     * or with status 500 (Internal Server Error) if the roleResource couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/role-resources")
    @Timed
    public ResponseEntity<RoleResource> updateRoleResource(@Valid @RequestBody RoleResource roleResource) throws URISyntaxException {
        log.debug("REST request to update RoleResource : {}", roleResource);
        if (roleResource.getId() == null) {
            return createRoleResource(roleResource);
        }
        RoleResource result = roleResourceService.save(roleResource);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, roleResource.getId().toString()))
            .body(result);
    }

    /**
     * GET  /role-resources : get all the roleResources.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of roleResources in body
     */
    @GetMapping("/role-resources")
    @Timed
    public List<RoleResource> getAllRoleResources() {
        log.debug("REST request to get all RoleResources");
        return roleResourceService.findAll();
        }

    /**
     * GET  /role-resources/:id : get the "id" roleResource.
     *
     * @param id the id of the roleResource to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the roleResource, or with status 404 (Not Found)
     */
    @GetMapping("/role-resources/{id}")
    @Timed
    public ResponseEntity<RoleResource> getRoleResource(@PathVariable Long id) {
        log.debug("REST request to get RoleResource : {}", id);
        RoleResource roleResource = roleResourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(roleResource));
    }

    /**
     * DELETE  /role-resources/:id : delete the "id" roleResource.
     *
     * @param id the id of the roleResource to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/role-resources/{id}")
    @Timed
    public ResponseEntity<Void> deleteRoleResource(@PathVariable Long id) {
        log.debug("REST request to delete RoleResource : {}", id);
        roleResourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
