package in.raster.ihms.authserver.service.impl;

import in.raster.ihms.authserver.service.RoleResourceService;
import in.raster.ihms.authserver.domain.RoleResource;
import in.raster.ihms.authserver.repository.RoleResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing RoleResource.
 */
@Service
@Transactional
public class RoleResourceServiceImpl implements RoleResourceService {

    private final Logger log = LoggerFactory.getLogger(RoleResourceServiceImpl.class);

    private final RoleResourceRepository roleResourceRepository;

    public RoleResourceServiceImpl(RoleResourceRepository roleResourceRepository) {
        this.roleResourceRepository = roleResourceRepository;
    }

    /**
     * Save a roleResource.
     *
     * @param roleResource the entity to save
     * @return the persisted entity
     */
    @Override
    public RoleResource save(RoleResource roleResource) {
        log.debug("Request to save RoleResource : {}", roleResource);
        return roleResourceRepository.save(roleResource);
    }

    /**
     * Get all the roleResources.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoleResource> findAll() {
        log.debug("Request to get all RoleResources");
        return roleResourceRepository.findAll();
    }

    /**
     * Get one roleResource by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RoleResource findOne(Long id) {
        log.debug("Request to get RoleResource : {}", id);
        return roleResourceRepository.findOne(id);
    }

    /**
     * Delete the roleResource by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RoleResource : {}", id);
        roleResourceRepository.delete(id);
    }
}
