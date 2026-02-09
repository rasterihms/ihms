package in.raster.ihms.authserver.web.rest;

import in.raster.ihms.authserver.AuthserverApp;

import in.raster.ihms.authserver.domain.RoleResource;
import in.raster.ihms.authserver.repository.RoleResourceRepository;
import in.raster.ihms.authserver.service.RoleResourceService;
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
 * Test class for the RoleResourceResource REST controller.
 *
 * @see RoleResourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthserverApp.class)
public class RoleResourceResourceIntTest {

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
    private RoleResourceRepository roleResourceRepository;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRoleResourceMockMvc;

    private RoleResource roleResource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RoleResourceResource roleResourceResource = new RoleResourceResource(roleResourceService);
        this.restRoleResourceMockMvc = MockMvcBuilders.standaloneSetup(roleResourceResource)
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
    public static RoleResource createEntity(EntityManager em) {
        RoleResource roleResource = new RoleResource()
            .active(DEFAULT_ACTIVE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return roleResource;
    }

    @Before
    public void initTest() {
        roleResource = createEntity(em);
    }

    @Test
    @Transactional
    public void createRoleResource() throws Exception {
        int databaseSizeBeforeCreate = roleResourceRepository.findAll().size();

        // Create the RoleResource
        restRoleResourceMockMvc.perform(post("/api/role-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roleResource)))
            .andExpect(status().isCreated());

        // Validate the RoleResource in the database
        List<RoleResource> roleResourceList = roleResourceRepository.findAll();
        assertThat(roleResourceList).hasSize(databaseSizeBeforeCreate + 1);
        RoleResource testRoleResource = roleResourceList.get(roleResourceList.size() - 1);
        assertThat(testRoleResource.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testRoleResource.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRoleResource.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRoleResource.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testRoleResource.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createRoleResourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = roleResourceRepository.findAll().size();

        // Create the RoleResource with an existing ID
        roleResource.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRoleResourceMockMvc.perform(post("/api/role-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roleResource)))
            .andExpect(status().isBadRequest());

        // Validate the RoleResource in the database
        List<RoleResource> roleResourceList = roleResourceRepository.findAll();
        assertThat(roleResourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = roleResourceRepository.findAll().size();
        // set the field null
        roleResource.setActive(null);

        // Create the RoleResource, which fails.

        restRoleResourceMockMvc.perform(post("/api/role-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roleResource)))
            .andExpect(status().isBadRequest());

        List<RoleResource> roleResourceList = roleResourceRepository.findAll();
        assertThat(roleResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRoleResources() throws Exception {
        // Initialize the database
        roleResourceRepository.saveAndFlush(roleResource);

        // Get all the roleResourceList
        restRoleResourceMockMvc.perform(get("/api/role-resources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roleResource.getId().intValue())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())));
    }

    @Test
    @Transactional
    public void getRoleResource() throws Exception {
        // Initialize the database
        roleResourceRepository.saveAndFlush(roleResource);

        // Get the roleResource
        restRoleResourceMockMvc.perform(get("/api/role-resources/{id}", roleResource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(roleResource.getId().intValue()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRoleResource() throws Exception {
        // Get the roleResource
        restRoleResourceMockMvc.perform(get("/api/role-resources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRoleResource() throws Exception {
        // Initialize the database
        roleResourceService.save(roleResource);

        int databaseSizeBeforeUpdate = roleResourceRepository.findAll().size();

        // Update the roleResource
        RoleResource updatedRoleResource = roleResourceRepository.findOne(roleResource.getId());
        // Disconnect from session so that the updates on updatedRoleResource are not directly saved in db
        em.detach(updatedRoleResource);
        updatedRoleResource
            .active(UPDATED_ACTIVE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModified(UPDATED_LAST_MODIFIED)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restRoleResourceMockMvc.perform(put("/api/role-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRoleResource)))
            .andExpect(status().isOk());

        // Validate the RoleResource in the database
        List<RoleResource> roleResourceList = roleResourceRepository.findAll();
        assertThat(roleResourceList).hasSize(databaseSizeBeforeUpdate);
        RoleResource testRoleResource = roleResourceList.get(roleResourceList.size() - 1);
        assertThat(testRoleResource.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testRoleResource.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRoleResource.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRoleResource.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testRoleResource.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingRoleResource() throws Exception {
        int databaseSizeBeforeUpdate = roleResourceRepository.findAll().size();

        // Create the RoleResource

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRoleResourceMockMvc.perform(put("/api/role-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(roleResource)))
            .andExpect(status().isCreated());

        // Validate the RoleResource in the database
        List<RoleResource> roleResourceList = roleResourceRepository.findAll();
        assertThat(roleResourceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRoleResource() throws Exception {
        // Initialize the database
        roleResourceService.save(roleResource);

        int databaseSizeBeforeDelete = roleResourceRepository.findAll().size();

        // Get the roleResource
        restRoleResourceMockMvc.perform(delete("/api/role-resources/{id}", roleResource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RoleResource> roleResourceList = roleResourceRepository.findAll();
        assertThat(roleResourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoleResource.class);
        RoleResource roleResource1 = new RoleResource();
        roleResource1.setId(1L);
        RoleResource roleResource2 = new RoleResource();
        roleResource2.setId(roleResource1.getId());
        assertThat(roleResource1).isEqualTo(roleResource2);
        roleResource2.setId(2L);
        assertThat(roleResource1).isNotEqualTo(roleResource2);
        roleResource1.setId(null);
        assertThat(roleResource1).isNotEqualTo(roleResource2);
    }
}
