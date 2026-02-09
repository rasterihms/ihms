package in.raster.ihms.authserver.service;

import in.raster.ihms.authserver.domain.UsersRole;
import java.util.List;

/**
 * Service Interface for managing UsersRole.
 */
public interface UsersRoleService {

    /**
     * Save a usersRole.
     *
     * @param usersRole the entity to save
     * @return the persisted entity
     */
    UsersRole save(UsersRole usersRole);

    /**
     * Get all the usersRoles.
     *
     * @return the list of entities
     */
    List<UsersRole> findAll();

    /**
     * Get the "id" usersRole.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UsersRole findOne(Long id);

    /**
     * Delete the "id" usersRole.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
