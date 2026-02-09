package in.raster.ihms.authserver.service;

import in.raster.ihms.authserver.domain.MenuResource;
import java.util.List;

/**
 * Service Interface for managing MenuResource.
 */
public interface MenuResourceService {

    /**
     * Save a menuResource.
     *
     * @param menuResource the entity to save
     * @return the persisted entity
     */
    MenuResource save(MenuResource menuResource);

    /**
     * Get all the menuResources.
     *
     * @return the list of entities
     */
    List<MenuResource> findAll();

    /**
     * Get the "id" menuResource.
     *
     * @param id the id of the entity
     * @return the entity
     */
    MenuResource findOne(Long id);

    /**
     * Delete the "id" menuResource.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
