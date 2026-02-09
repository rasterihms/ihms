package in.raster.ihms.authserver.web.rest;

import in.raster.ihms.authserver.AuthserverApp;

import in.raster.ihms.authserver.domain.RoleMenu;
import in.raster.ihms.authserver.repository.RoleMenuRepository;
import in.raster.ihms.authserver.service.RoleMenuService;
import in.raster.ihms.authserver.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static in.raster.ihms.authserver.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import in.raster.ihms.authserver.domain.enumeration.PermissionType;
/**
 * Test class for the RoleMenuResource REST controller.
 *
 * @see RoleMenuResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthserverApp.class)
public class RoleMenuResourceIntTest {

    private static final PermissionType DEFAULT_PERMISSION_TYPE = PermissionType.READ_ONLY;
    private static final PermissionType UPDATED_PERMISSION_TYPE = PermissionType.ADD_EDIT;

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CREATED_BY = 1L;
    private static final Long UPDATED_CREATED_BY = 2L;

    private static final Instant DEFAULT_LAST_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_MODIFIED_BY = 1L;
    private static final Long UPDATED_MODIFIED_BY = 2L;

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRoleMenuMockMvc;

    private RoleMenu roleMenu;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RoleMenuResource roleMenuResource = new RoleMenuResource(roleMenuService);
        this.restRoleMenuMockMvc = MockMvcBuilders.standaloneSetup(roleMenuResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RoleMenu createEntity(EntityManager em) {
        RoleMenu roleMenu = new RoleMenu()
            .permissionType(DEFAULT_PERMISSION_TYPE)
            .active(DEFAULT_ACTIVE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return roleMenu;
    }

    @Before
    public void initTest() {
        roleMenu = createEntity(em);
    }

    @Test
    @Transactional
    public void createRoleMenu() throws Exception {
        int databaseSizeBeforeCreate = roleMenuRepository.findAll().size();

        // Create the RoleMenu
        restRoleMenuMockMvc.perform(post("/api/role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roleMenu)))
            .andExpect(status().isCreated());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeCreate + 1);
        RoleMenu testRoleMenu = roleMenuList.get(roleMenuList.size() - 1);
        assertThat(testRoleMenu.getPermissionType()).isEqualTo(DEFAULT_PERMISSION_TYPE);
        assertThat(testRoleMenu.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testRoleMenu.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRoleMenu.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRoleMenu.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testRoleMenu.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createRoleMenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = roleMenuRepository.findAll().size();

        // Create the RoleMenu with an existing ID
        roleMenu.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoleMenuMockMvc.perform(post("/api/role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roleMenu)))
            .andExpect(status().isBadRequest());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = roleMenuRepository.findAll().size();
        // set the field null
        roleMenu.setActive(null);

        // Create the RoleMenu, which fails.

        restRoleMenuMockMvc.perform(post("/api/role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roleMenu)))
            .andExpect(status().isBadRequest());

        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRoleMenus() throws Exception {
        // Initialize the database
        roleMenuRepository.saveAndFlush(roleMenu);

        // Get all the roleMenuList
        restRoleMenuMockMvc.perform(get("/api/role-menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roleMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].permissionType").value(hasItem(DEFAULT_PERMISSION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())));
    }

    @Test
    @Transactional
    public void getRoleMenu() throws Exception {
        // Initialize the database
        roleMenuRepository.saveAndFlush(roleMenu);

        // Get the roleMenu
        restRoleMenuMockMvc.perform(get("/api/role-menus/{id}", roleMenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(roleMenu.getId().intValue()))
            .andExpect(jsonPath("$.permissionType").value(DEFAULT_PERMISSION_TYPE.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRoleMenu() throws Exception {
        // Get the roleMenu
        restRoleMenuMockMvc.perform(get("/api/role-menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRoleMenu() throws Exception {
        // Initialize the database
        roleMenuService.save(roleMenu);

        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();

        // Update the roleMenu
        RoleMenu updatedRoleMenu = roleMenuRepository.findOne(roleMenu.getId());
        // Disconnect from session so that the updates on updatedRoleMenu are not directly saved in db
        em.detach(updatedRoleMenu);
        updatedRoleMenu
            .permissionType(UPDATED_PERMISSION_TYPE)
            .active(UPDATED_ACTIVE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModified(UPDATED_LAST_MODIFIED)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restRoleMenuMockMvc.perform(put("/api/role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRoleMenu)))
            .andExpect(status().isOk());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate);
        RoleMenu testRoleMenu = roleMenuList.get(roleMenuList.size() - 1);
        assertThat(testRoleMenu.getPermissionType()).isEqualTo(UPDATED_PERMISSION_TYPE);
        assertThat(testRoleMenu.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testRoleMenu.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRoleMenu.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRoleMenu.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testRoleMenu.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingRoleMenu() throws Exception {
        int databaseSizeBeforeUpdate = roleMenuRepository.findAll().size();

        // Create the RoleMenu

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRoleMenuMockMvc.perform(put("/api/role-menus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roleMenu)))
            .andExpect(status().isCreated());

        // Validate the RoleMenu in the database
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRoleMenu() throws Exception {
        // Initialize the database
        roleMenuService.save(roleMenu);

        int databaseSizeBeforeDelete = roleMenuRepository.findAll().size();

        // Get the roleMenu
        restRoleMenuMockMvc.perform(delete("/api/role-menus/{id}", roleMenu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RoleMenu> roleMenuList = roleMenuRepository.findAll();
        assertThat(roleMenuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleMenu.class);
        RoleMenu roleMenu1 = new RoleMenu();
        roleMenu1.setId(1L);
        RoleMenu roleMenu2 = new RoleMenu();
        roleMenu2.setId(roleMenu1.getId());
        assertThat(roleMenu1).isEqualTo(roleMenu2);
        roleMenu2.setId(2L);
        assertThat(roleMenu1).isNotEqualTo(roleMenu2);
        roleMenu1.setId(null);
        assertThat(roleMenu1).isNotEqualTo(roleMenu2);
    }
}
