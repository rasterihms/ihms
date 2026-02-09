package in.raster.ihms.authserver.service.impl;

import in.raster.ihms.authserver.service.UsersRoleService;
import in.raster.ihms.authserver.domain.UsersRole;
import in.raster.ihms.authserver.repository.UsersRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing UsersRole.
 */
@Service
@Transactional
public class UsersRoleServiceImpl implements UsersRoleService {

    private final Logger log = LoggerFactory.getLogger(UsersRoleServiceImpl.class);

    private final UsersRoleRepository usersRoleRepository;

    public UsersRoleServiceImpl(UsersRoleRepository usersRoleRepository) {
        this.usersRoleRepository = usersRoleRepository;
    }

    /**
     * Save a usersRole.
     *
     * @param usersRole the entity to save
     * @return the persisted entity
     */
    @Override
    public UsersRole save(UsersRole usersRole) {
        log.debug("Request to save UsersRole : {}", usersRole);
        return usersRoleRepository.save(usersRole);
    }

    /**
     * Get all the usersRoles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UsersRole> findAll() {
        log.debug("Request to get all UsersRoles");
        return usersRoleRepository.findAll();
    }

    /**
     * Get one usersRole by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UsersRole findOne(Long id) {
        log.debug("Request to get UsersRole : {}", id);
        return usersRoleRepository.findOne(id);
    }

    /**
     * Delete the usersRole by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UsersRole : {}", id);
        usersRoleRepository.delete(id);
    }
}
