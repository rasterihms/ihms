package in.raster.ihms.authserver.service.impl;

import in.raster.ihms.authserver.service.RoleMenuService;
import in.raster.ihms.authserver.domain.RoleMenu;
import in.raster.ihms.authserver.repository.RoleMenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing RoleMenu.
 */
@Service
@Transactional
public class RoleMenuServiceImpl implements RoleMenuService {

    private final Logger log = LoggerFactory.getLogger(RoleMenuServiceImpl.class);

    private final RoleMenuRepository roleMenuRepository;

    public RoleMenuServiceImpl(RoleMenuRepository roleMenuRepository) {
        this.roleMenuRepository = roleMenuRepository;
    }

    /**
     * Save a roleMenu.
     *
     * @param roleMenu the entity to save
     * @return the persisted entity
     */
    @Override
    public RoleMenu save(RoleMenu roleMenu) {
        log.debug("Request to save RoleMenu : {}", roleMenu);
        return roleMenuRepository.save(roleMenu);
    }

    /**
     * Get all the roleMenus.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoleMenu> findAll() {
        log.debug("Request to get all RoleMenus");
        return roleMenuRepository.findAll();
    }

    /**
     * Get one roleMenu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RoleMenu findOne(Long id) {
        log.debug("Request to get RoleMenu : {}", id);
        return roleMenuRepository.findOne(id);
    }

    /**
     * Delete the roleMenu by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RoleMenu : {}", id);
        roleMenuRepository.delete(id);
    }
}
