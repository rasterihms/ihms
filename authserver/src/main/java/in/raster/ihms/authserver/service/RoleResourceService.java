package in.raster.ihms.authserver.service;

import in.raster.ihms.authserver.domain.RoleResource;
import java.util.List;

/**
 * Service Interface for managing RoleResource.
 */
public interface RoleResourceService {

    /**
     * Save a roleResource.
     *
     * @param roleResource the entity to save
     * @return the persisted entity
     */
    RoleResource save(RoleResource roleResource);

    /**
     * Get all the roleResources.
     *
     * @return the list of entities
     */
    List<RoleResource> findAll();

    /**
     * Get the "id" roleResource.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RoleResource findOne(Long id);

    /**
     * Delete the "id" roleResource.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
