package in.raster.ihms.authserver.web.rest;

import in.raster.ihms.authserver.AuthserverApp;

import in.raster.ihms.authserver.domain.UsersRole;
import in.raster.ihms.authserver.repository.UsersRoleRepository;
import in.raster.ihms.authserver.service.UsersRoleService;
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

import in.raster.ihms.authserver.domain.enumeration.LinkTo;
/**
 * Test class for the UsersRoleResource REST controller.
 *
 * @see UsersRoleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthserverApp.class)
public class UsersRoleResourceIntTest {

    private static final String DEFAULT_LDAP_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_LDAP_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LDAP_ROLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_LDAP_ROLE_ID = "BBBBBBBBBB";

    private static final LinkTo DEFAULT_LINK_TO = LinkTo.DEPARTMENT;
    private static final LinkTo UPDATED_LINK_TO = LinkTo.SPECIALITY;

    private static final Long DEFAULT_LINK_TO_ID = 1L;
    private static final Long UPDATED_LINK_TO_ID = 2L;

    private static final Instant DEFAULT_EFFECTIVE_FROM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_FROM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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
    private UsersRoleRepository usersRoleRepository;

    @Autowired
    private UsersRoleService usersRoleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUsersRoleMockMvc;

    private UsersRole usersRole;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UsersRoleResource usersRoleResource = new UsersRoleResource(usersRoleService);
        this.restUsersRoleMockMvc = MockMvcBuilders.standaloneSetup(usersRoleResource)
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
    public static UsersRole createEntity(EntityManager em) {
        UsersRole usersRole = new UsersRole()
            .ldapUserId(DEFAULT_LDAP_USER_ID)
            .ldapRoleId(DEFAULT_LDAP_ROLE_ID)
            .linkTo(DEFAULT_LINK_TO)
            .linkToId(DEFAULT_LINK_TO_ID)
            .effectiveFrom(DEFAULT_EFFECTIVE_FROM)
            .active(DEFAULT_ACTIVE)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return usersRole;
    }

    @Before
    public void initTest() {
        usersRole = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsersRole() throws Exception {
        int databaseSizeBeforeCreate = usersRoleRepository.findAll().size();

        // Create the UsersRole
        restUsersRoleMockMvc.perform(post("/api/users-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usersRole)))
            .andExpect(status().isCreated());

        // Validate the UsersRole in the database
        List<UsersRole> usersRoleList = usersRoleRepository.findAll();
        assertThat(usersRoleList).hasSize(databaseSizeBeforeCreate + 1);
        UsersRole testUsersRole = usersRoleList.get(usersRoleList.size() - 1);
        assertThat(testUsersRole.getLdapUserId()).isEqualTo(DEFAULT_LDAP_USER_ID);
        assertThat(testUsersRole.getLdapRoleId()).isEqualTo(DEFAULT_LDAP_ROLE_ID);
        assertThat(testUsersRole.getLinkTo()).isEqualTo(DEFAULT_LINK_TO);
        assertThat(testUsersRole.getLinkToId()).isEqualTo(DEFAULT_LINK_TO_ID);
        assertThat(testUsersRole.getEffectiveFrom()).isEqualTo(DEFAULT_EFFECTIVE_FROM);
        assertThat(testUsersRole.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testUsersRole.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUsersRole.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUsersRole.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testUsersRole.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createUsersRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usersRoleRepository.findAll().size();

        // Create the UsersRole with an existing ID
        usersRole.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsersRoleMockMvc.perform(post("/api/users-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usersRole)))
            .andExpect(status().isBadRequest());

        // Validate the UsersRole in the database
        List<UsersRole> usersRoleList = usersRoleRepository.findAll();
        assertThat(usersRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = usersRoleRepository.findAll().size();
        // set the field null
        usersRole.setActive(null);

        // Create the UsersRole, which fails.

        restUsersRoleMockMvc.perform(post("/api/users-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usersRole)))
            .andExpect(status().isBadRequest());

        List<UsersRole> usersRoleList = usersRoleRepository.findAll();
        assertThat(usersRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsersRoles() throws Exception {
        // Initialize the database
        usersRoleRepository.saveAndFlush(usersRole);

        // Get all the usersRoleList
        restUsersRoleMockMvc.perform(get("/api/users-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usersRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].ldapUserId").value(hasItem(DEFAULT_LDAP_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].ldapRoleId").value(hasItem(DEFAULT_LDAP_ROLE_ID.toString())))
            .andExpect(jsonPath("$.[*].linkTo").value(hasItem(DEFAULT_LINK_TO.toString())))
            .andExpect(jsonPath("$.[*].linkToId").value(hasItem(DEFAULT_LINK_TO_ID.intValue())))
            .andExpect(jsonPath("$.[*].effectiveFrom").value(hasItem(DEFAULT_EFFECTIVE_FROM.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())));
    }

    @Test
    @Transactional
    public void getUsersRole() throws Exception {
        // Initialize the database
        usersRoleRepository.saveAndFlush(usersRole);

        // Get the usersRole
        restUsersRoleMockMvc.perform(get("/api/users-roles/{id}", usersRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(usersRole.getId().intValue()))
            .andExpect(jsonPath("$.ldapUserId").value(DEFAULT_LDAP_USER_ID.toString()))
            .andExpect(jsonPath("$.ldapRoleId").value(DEFAULT_LDAP_ROLE_ID.toString()))
            .andExpect(jsonPath("$.linkTo").value(DEFAULT_LINK_TO.toString()))
            .andExpect(jsonPath("$.linkToId").value(DEFAULT_LINK_TO_ID.intValue()))
            .andExpect(jsonPath("$.effectiveFrom").value(DEFAULT_EFFECTIVE_FROM.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUsersRole() throws Exception {
        // Get the usersRole
        restUsersRoleMockMvc.perform(get("/api/users-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsersRole() throws Exception {
        // Initialize the database
        usersRoleService.save(usersRole);

        int databaseSizeBeforeUpdate = usersRoleRepository.findAll().size();

        // Update the usersRole
        UsersRole updatedUsersRole = usersRoleRepository.findOne(usersRole.getId());
        // Disconnect from session so that the updates on updatedUsersRole are not directly saved in db
        em.detach(updatedUsersRole);
        updatedUsersRole
            .ldapUserId(UPDATED_LDAP_USER_ID)
            .ldapRoleId(UPDATED_LDAP_ROLE_ID)
            .linkTo(UPDATED_LINK_TO)
            .linkToId(UPDATED_LINK_TO_ID)
            .effectiveFrom(UPDATED_EFFECTIVE_FROM)
            .active(UPDATED_ACTIVE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModified(UPDATED_LAST_MODIFIED)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restUsersRoleMockMvc.perform(put("/api/users-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsersRole)))
            .andExpect(status().isOk());

        // Validate the UsersRole in the database
        List<UsersRole> usersRoleList = usersRoleRepository.findAll();
        assertThat(usersRoleList).hasSize(databaseSizeBeforeUpdate);
        UsersRole testUsersRole = usersRoleList.get(usersRoleList.size() - 1);
        assertThat(testUsersRole.getLdapUserId()).isEqualTo(UPDATED_LDAP_USER_ID);
        assertThat(testUsersRole.getLdapRoleId()).isEqualTo(UPDATED_LDAP_ROLE_ID);
        assertThat(testUsersRole.getLinkTo()).isEqualTo(UPDATED_LINK_TO);
        assertThat(testUsersRole.getLinkToId()).isEqualTo(UPDATED_LINK_TO_ID);
        assertThat(testUsersRole.getEffectiveFrom()).isEqualTo(UPDATED_EFFECTIVE_FROM);
        assertThat(testUsersRole.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testUsersRole.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUsersRole.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUsersRole.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testUsersRole.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingUsersRole() throws Exception {
        int databaseSizeBeforeUpdate = usersRoleRepository.findAll().size();

        // Create the UsersRole

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUsersRoleMockMvc.perform(put("/api/users-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usersRole)))
            .andExpect(status().isCreated());

        // Validate the UsersRole in the database
        List<UsersRole> usersRoleList = usersRoleRepository.findAll();
        assertThat(usersRoleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUsersRole() throws Exception {
        // Initialize the database
        usersRoleService.save(usersRole);

        int databaseSizeBeforeDelete = usersRoleRepository.findAll().size();

        // Get the usersRole
        restUsersRoleMockMvc.perform(delete("/api/users-roles/{id}", usersRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UsersRole> usersRoleList = usersRoleRepository.findAll();
        assertThat(usersRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsersRole.class);
        UsersRole usersRole1 = new UsersRole();
        usersRole1.setId(1L);
        UsersRole usersRole2 = new UsersRole();
        usersRole2.setId(usersRole1.getId());
        assertThat(usersRole1).isEqualTo(usersRole2);
        usersRole2.setId(2L);
        assertThat(usersRole1).isNotEqualTo(usersRole2);
        usersRole1.setId(null);
        assertThat(usersRole1).isNotEqualTo(usersRole2);
    }
}
