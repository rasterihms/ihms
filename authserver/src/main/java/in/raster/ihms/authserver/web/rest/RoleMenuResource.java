package in.raster.ihms.authserver.web.rest;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.domain.RoleMenu;
import in.raster.ihms.authserver.service.RoleMenuService;
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
 * REST controller for managing RoleMenu.
 */
@RestController
@RequestMapping("/api")
public class RoleMenuResource {

    private final Logger log = LoggerFactory.getLogger(RoleMenuResource.class);

    private static final String ENTITY_NAME = "roleMenu";

    private final RoleMenuService roleMenuService;

    public RoleMenuResource(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    /**
     * POST  /role-menus : Create a new roleMenu.
     *
     * @param roleMenu the roleMenu to create
     * @return the ResponseEntity with status 201 (Created) and with body the new roleMenu, or with status 400 (Bad Request) if the roleMenu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/role-menus")
    @Timed
    public ResponseEntity<RoleMenu> createRoleMenu(@Valid @RequestBody RoleMenu roleMenu) throws URISyntaxException {
        log.debug("REST request to save RoleMenu : {}", roleMenu);
        if (roleMenu.getId() != null) {
            throw new BadRequestAlertException("A new roleMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleMenu result = roleMenuService.save(roleMenu);
        return ResponseEntity.created(new URI("/api/role-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /role-menus : Updates an existing roleMenu.
     *
     * @param roleMenu the roleMenu to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated roleMenu,
     * or with status 400 (Bad Request) if the roleMenu is not valid,
     * or with status 500 (Internal Server Error) if the roleMenu couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/role-menus")
    @Timed
    public ResponseEntity<RoleMenu> updateRoleMenu(@Valid @RequestBody RoleMenu roleMenu) throws URISyntaxException {
        log.debug("REST request to update RoleMenu : {}", roleMenu);
        if (roleMenu.getId() == null) {
            return createRoleMenu(roleMenu);
        }
        RoleMenu result = roleMenuService.save(roleMenu);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, roleMenu.getId().toString()))
            .body(result);
    }

    /**
     * GET  /role-menus : get all the roleMenus.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of roleMenus in body
     */
    @GetMapping("/role-menus")
    @Timed
    public List<RoleMenu> getAllRoleMenus() {
        log.debug("REST request to get all RoleMenus");
        return roleMenuService.findAll();
        }

    /**
     * GET  /role-menus/:id : get the "id" roleMenu.
     *
     * @param id the id of the roleMenu to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the roleMenu, or with status 404 (Not Found)
     */
    @GetMapping("/role-menus/{id}")
    @Timed
    public ResponseEntity<RoleMenu> getRoleMenu(@PathVariable Long id) {
        log.debug("REST request to get RoleMenu : {}", id);
        RoleMenu roleMenu = roleMenuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(roleMenu));
    }

    /**
     * DELETE  /role-menus/:id : delete the "id" roleMenu.
     *
     * @param id the id of the roleMenu to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/role-menus/{id}")
    @Timed
    public ResponseEntity<Void> deleteRoleMenu(@PathVariable Long id) {
        log.debug("REST request to delete RoleMenu : {}", id);
        roleMenuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
