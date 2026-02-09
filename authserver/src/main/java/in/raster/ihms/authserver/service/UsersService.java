package in.raster.ihms.authserver.service;

import in.raster.ihms.authserver.domain.Users;
import java.util.List;

/**
 * Service Interface for managing Users.
 */
public interface UsersService {

    /**
     * Save a users.
     *
     * @param users the entity to save
     * @return the persisted entity
     */
    Users save(Users users);

    /**
     * Get all the users.
     *
     * @return the list of entities
     */
    List<Users> findAll();

    /**
     * Get the "id" users.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Users findOne(Long id);

    /**
     * Delete the "id" users.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
