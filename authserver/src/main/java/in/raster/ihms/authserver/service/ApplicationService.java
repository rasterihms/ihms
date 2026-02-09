package in.raster.ihms.authserver.service;

import in.raster.ihms.authserver.domain.Application;
import java.util.List;

/**
 * Service Interface for managing Application.
 */
public interface ApplicationService {

    /**
     * Save a application.
     *
     * @param application the entity to save
     * @return the persisted entity
     */
    Application save(Application application);

    /**
     * Get all the applications.
     *
     * @return the list of entities
     */
    List<Application> findAll();

    /**
     * Get the "id" application.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Application findOne(Long id);

    /**
     * Delete the "id" application.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
