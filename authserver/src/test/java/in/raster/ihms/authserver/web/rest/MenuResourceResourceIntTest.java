package in.raster.ihms.authserver.web.rest;

import in.raster.ihms.authserver.AuthserverApp;

import in.raster.ihms.authserver.domain.MenuResource;
import in.raster.ihms.authserver.repository.MenuResourceRepository;
import in.raster.ihms.authserver.service.MenuResourceService;
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

/**
 * Test class for the MenuResourceResource REST controller.
 *
 * @see MenuResourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthserverApp.class)
public class MenuResourceResourceIntTest {

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
    private MenuResourceRepository menuResourceRepository;

    @Autowired
    private MenuResourceService menuResourceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMenuResourceMockMvc;

    private MenuResource menuResource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MenuResourceResource menuResourceResource = new MenuResourceResource(menuResourceService);
        this.restMenuResourceMockMvc = MockMvcBuilders.standaloneSetup(menuResourceResource)
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
    public static MenuResource createEntity(EntityManager em) {
        MenuResource menuResource = new MenuResource()
            .active(DEFAULT_ACTIVE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return menuResource;
    }

    @Before
    public void initTest() {
        menuResource = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenuResource() throws Exception {
        int databaseSizeBeforeCreate = menuResourceRepository.findAll().size();

        // Create the MenuResource
        restMenuResourceMockMvc.perform(post("/api/menu-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuResource)))
            .andExpect(status().isCreated());

        // Validate the MenuResource in the database
        List<MenuResource> menuResourceList = menuResourceRepository.findAll();
        assertThat(menuResourceList).hasSize(databaseSizeBeforeCreate + 1);
        MenuResource testMenuResource = menuResourceList.get(menuResourceList.size() - 1);
        assertThat(testMenuResource.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testMenuResource.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMenuResource.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMenuResource.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testMenuResource.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createMenuResourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuResourceRepository.findAll().size();

        // Create the MenuResource with an existing ID
        menuResource.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuResourceMockMvc.perform(post("/api/menu-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuResource)))
            .andExpect(status().isBadRequest());

        // Validate the MenuResource in the database
        List<MenuResource> menuResourceList = menuResourceRepository.findAll();
        assertThat(menuResourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = menuResourceRepository.findAll().size();
        // set the field null
        menuResource.setActive(null);

        // Create the MenuResource, which fails.

        restMenuResourceMockMvc.perform(post("/api/menu-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuResource)))
            .andExpect(status().isBadRequest());

        List<MenuResource> menuResourceList = menuResourceRepository.findAll();
        assertThat(menuResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMenuResources() throws Exception {
        // Initialize the database
        menuResourceRepository.saveAndFlush(menuResource);

        // Get all the menuResourceList
        restMenuResourceMockMvc.perform(get("/api/menu-resources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuResource.getId().intValue())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())));
    }

    @Test
    @Transactional
    public void getMenuResource() throws Exception {
        // Initialize the database
        menuResourceRepository.saveAndFlush(menuResource);

        // Get the menuResource
        restMenuResourceMockMvc.perform(get("/api/menu-resources/{id}", menuResource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(menuResource.getId().intValue()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMenuResource() throws Exception {
        // Get the menuResource
        restMenuResourceMockMvc.perform(get("/api/menu-resources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuResource() throws Exception {
        // Initialize the database
        menuResourceService.save(menuResource);

        int databaseSizeBeforeUpdate = menuResourceRepository.findAll().size();

        // Update the menuResource
        MenuResource updatedMenuResource = menuResourceRepository.findOne(menuResource.getId());
        // Disconnect from session so that the updates on updatedMenuResource are not directly saved in db
        em.detach(updatedMenuResource);
        updatedMenuResource
            .active(UPDATED_ACTIVE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModified(UPDATED_LAST_MODIFIED)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restMenuResourceMockMvc.perform(put("/api/menu-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMenuResource)))
            .andExpect(status().isOk());

        // Validate the MenuResource in the database
        List<MenuResource> menuResourceList = menuResourceRepository.findAll();
        assertThat(menuResourceList).hasSize(databaseSizeBeforeUpdate);
        MenuResource testMenuResource = menuResourceList.get(menuResourceList.size() - 1);
        assertThat(testMenuResource.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testMenuResource.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMenuResource.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMenuResource.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testMenuResource.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingMenuResource() throws Exception {
        int databaseSizeBeforeUpdate = menuResourceRepository.findAll().size();

        // Create the MenuResource

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMenuResourceMockMvc.perform(put("/api/menu-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuResource)))
            .andExpect(status().isCreated());

        // Validate the MenuResource in the database
        List<MenuResource> menuResourceList = menuResourceRepository.findAll();
        assertThat(menuResourceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMenuResource() throws Exception {
        // Initialize the database
        menuResourceService.save(menuResource);

        int databaseSizeBeforeDelete = menuResourceRepository.findAll().size();

        // Get the menuResource
        restMenuResourceMockMvc.perform(delete("/api/menu-resources/{id}", menuResource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MenuResource> menuResourceList = menuResourceRepository.findAll();
        assertThat(menuResourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuResource.class);
        MenuResource menuResource1 = new MenuResource();
        menuResource1.setId(1L);
        MenuResource menuResource2 = new MenuResource();
        menuResource2.setId(menuResource1.getId());
        assertThat(menuResource1).isEqualTo(menuResource2);
        menuResource2.setId(2L);
        assertThat(menuResource1).isNotEqualTo(menuResource2);
        menuResource1.setId(null);
        assertThat(menuResource1).isNotEqualTo(menuResource2);
    }
}
