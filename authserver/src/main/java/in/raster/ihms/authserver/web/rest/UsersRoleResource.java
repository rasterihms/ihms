package in.raster.ihms.authserver.web.rest;

import com.codahale.metrics.annotation.Timed;
import in.raster.ihms.authserver.domain.UsersRole;
import in.raster.ihms.authserver.service.UsersRoleService;
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
 * REST controller for managing UsersRole.
 */
@RestController
@RequestMapping("/api")
public class UsersRoleResource {

    private final Logger log = LoggerFactory.getLogger(UsersRoleResource.class);

    private static final String ENTITY_NAME = "usersRole";

    private final UsersRoleService usersRoleService;

    public UsersRoleResource(UsersRoleService usersRoleService) {
        this.usersRoleService = usersRoleService;
    }

    /**
     * POST  /users-roles : Create a new usersRole.
     *
     * @param usersRole the usersRole to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usersRole, or with status 400 (Bad Request) if the usersRole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/users-roles")
    @Timed
    public ResponseEntity<UsersRole> createUsersRole(@Valid @RequestBody UsersRole usersRole) throws URISyntaxException {
        log.debug("REST request to save UsersRole : {}", usersRole);
        if (usersRole.getId() != null) {
            throw new BadRequestAlertException("A new usersRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsersRole result = usersRoleService.save(usersRole);
        return ResponseEntity.created(new URI("/api/users-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /users-roles : Updates an existing usersRole.
     *
     * @param usersRole the usersRole to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usersRole,
     * or with status 400 (Bad Request) if the usersRole is not valid,
     * or with status 500 (Internal Server Error) if the usersRole couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/users-roles")
    @Timed
    public ResponseEntity<UsersRole> updateUsersRole(@Valid @RequestBody UsersRole usersRole) throws URISyntaxException {
        log.debug("REST request to update UsersRole : {}", usersRole);
        if (usersRole.getId() == null) {
            return createUsersRole(usersRole);
        }
        UsersRole result = usersRoleService.save(usersRole);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usersRole.getId().toString()))
            .body(result);
    }

    /**
     * GET  /users-roles : get all the usersRoles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of usersRoles in body
     */
    @GetMapping("/users-roles")
    @Timed
    public List<UsersRole> getAllUsersRoles() {
        log.debug("REST request to get all UsersRoles");
        return usersRoleService.findAll();
        }

    /**
     * GET  /users-roles/:id : get the "id" usersRole.
     *
     * @param id the id of the usersRole to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usersRole, or with status 404 (Not Found)
     */
    @GetMapping("/users-roles/{id}")
    @Timed
    public ResponseEntity<UsersRole> getUsersRole(@PathVariable Long id) {
        log.debug("REST request to get UsersRole : {}", id);
        UsersRole usersRole = usersRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(usersRole));
    }

    /**
     * DELETE  /users-roles/:id : delete the "id" usersRole.
     *
     * @param id the id of the usersRole to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/users-roles/{id}")
    @Timed
    public ResponseEntity<Void> deleteUsersRole(@PathVariable Long id) {
        log.debug("REST request to delete UsersRole : {}", id);
        usersRoleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
