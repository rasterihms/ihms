package in.raster.ihms.authserver.service.impl;

import in.raster.ihms.authserver.service.MenuResourceService;
import in.raster.ihms.authserver.domain.MenuResource;
import in.raster.ihms.authserver.repository.MenuResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing MenuResource.
 */
@Service
@Transactional
public class MenuResourceServiceImpl implements MenuResourceService {

    private final Logger log = LoggerFactory.getLogger(MenuResourceServiceImpl.class);

    private final MenuResourceRepository menuResourceRepository;

    public MenuResourceServiceImpl(MenuResourceRepository menuResourceRepository) {
        this.menuResourceRepository = menuResourceRepository;
    }

    /**
     * Save a menuResource.
     *
     * @param menuResource the entity to save
     * @return the persisted entity
     */
    @Override
    public MenuResource save(MenuResource menuResource) {
        log.debug("Request to save MenuResource : {}", menuResource);
        return menuResourceRepository.save(menuResource);
    }

    /**
     * Get all the menuResources.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuResource> findAll() {
        log.debug("Request to get all MenuResources");
        return menuResourceRepository.findAll();
    }

    /**
     * Get one menuResource by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MenuResource findOne(Long id) {
        log.debug("Request to get MenuResource : {}", id);
        return menuResourceRepository.findOne(id);
    }

    /**
     * Delete the menuResource by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MenuResource : {}", id);
        menuResourceRepository.delete(id);
    }
}
