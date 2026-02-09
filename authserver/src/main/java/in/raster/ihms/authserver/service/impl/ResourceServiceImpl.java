package in.raster.ihms.authserver.service.impl;

import in.raster.ihms.authserver.service.ResourceService;
import in.raster.ihms.authserver.domain.Resource;
import in.raster.ihms.authserver.repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Resource.
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    private final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);

    private final ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    /**
     * Save a resource.
     *
     * @param resource the entity to save
     * @return the persisted entity
     */
    @Override
    public Resource save(Resource resource) {
        log.debug("Request to save Resource : {}", resource);
        return resourceRepository.save(resource);
    }

    /**
     * Get all the resources.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Resource> findAll() {
        log.debug("Request to get all Resources");
        return resourceRepository.findAll();
    }

    /**
     * Get one resource by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Resource findOne(Long id) {
        log.debug("Request to get Resource : {}", id);
        return resourceRepository.findOne(id);
    }

    /**
     * Delete the resource by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Resource : {}", id);
        resourceRepository.delete(id);
    }
}
