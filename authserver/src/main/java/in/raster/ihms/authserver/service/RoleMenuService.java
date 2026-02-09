package in.raster.ihms.authserver.service;

import in.raster.ihms.authserver.domain.RoleMenu;
import java.util.List;

/**
 * Service Interface for managing RoleMenu.
 */
public interface RoleMenuService {

    /**
     * Save a roleMenu.
     *
     * @param roleMenu the entity to save
     * @return the persisted entity
     */
    RoleMenu save(RoleMenu roleMenu);

    /**
     * Get all the roleMenus.
     *
     * @return the list of entities
     */
    List<RoleMenu> findAll();

    /**
     * Get the "id" roleMenu.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RoleMenu findOne(Long id);

    /**
     * Delete the "id" roleMenu.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
