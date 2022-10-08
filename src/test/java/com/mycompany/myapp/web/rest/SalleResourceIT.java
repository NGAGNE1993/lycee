package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Salle;
import com.mycompany.myapp.domain.enumeration.Cdi;
import com.mycompany.myapp.domain.enumeration.Gym;
import com.mycompany.myapp.domain.enumeration.Specialise;
import com.mycompany.myapp.domain.enumeration.TerrainSport;
import com.mycompany.myapp.repository.SalleRepository;
import com.mycompany.myapp.service.SalleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link SalleResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SalleResourceIT {

    private static final String DEFAULT_NOMBRE_SALLECLASSE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_SALLECLASSE = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_ATELIERS = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ATELIERS = "BBBBBBBBBB";

    private static final Specialise DEFAULT_SPECIALIASE = Specialise.OUI;
    private static final Specialise UPDATED_SPECIALIASE = Specialise.NON;

    private static final String DEFAULT_NOMBRE_SALLE_SPECIALISE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_SALLE_SPECIALISE = "BBBBBBBBBB";

    private static final String DEFAULT_JUSTIFICATION_SALLE_SPE = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFICATION_SALLE_SPE = "BBBBBBBBBB";

    private static final Cdi DEFAULT_CDI = Cdi.OUI;
    private static final Cdi UPDATED_CDI = Cdi.NON;

    private static final String DEFAULT_NOMBRE_CDI = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_CDI = "BBBBBBBBBB";

    private static final String DEFAULT_JUSTIFIACTIF_SALLE_CDI = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFIACTIF_SALLE_CDI = "BBBBBBBBBB";

    private static final Gym DEFAULT_GYM = Gym.OUI;
    private static final Gym UPDATED_GYM = Gym.NON;

    private static final TerrainSport DEFAULT_TERRAIN_SPORT = TerrainSport.OUI;
    private static final TerrainSport UPDATED_TERRAIN_SPORT = TerrainSport.NON;

    private static final String DEFAULT_AUTRE_SALLE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_SALLE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/salles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalleRepository salleRepository;

    @Mock
    private SalleRepository salleRepositoryMock;

    @Mock
    private SalleService salleServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalleMockMvc;

    private Salle salle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Salle createEntity(EntityManager em) {
        Salle salle = new Salle()
            .nombreSalleclasse(DEFAULT_NOMBRE_SALLECLASSE)
            .nombreAteliers(DEFAULT_NOMBRE_ATELIERS)
            .specialiase(DEFAULT_SPECIALIASE)
            .nombreSalleSpecialise(DEFAULT_NOMBRE_SALLE_SPECIALISE)
            .justificationSalleSpe(DEFAULT_JUSTIFICATION_SALLE_SPE)
            .cdi(DEFAULT_CDI)
            .nombreCDI(DEFAULT_NOMBRE_CDI)
            .justifiactifSalleCDI(DEFAULT_JUSTIFIACTIF_SALLE_CDI)
            .gym(DEFAULT_GYM)
            .terrainSport(DEFAULT_TERRAIN_SPORT)
            .autreSalle(DEFAULT_AUTRE_SALLE);
        return salle;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Salle createUpdatedEntity(EntityManager em) {
        Salle salle = new Salle()
            .nombreSalleclasse(UPDATED_NOMBRE_SALLECLASSE)
            .nombreAteliers(UPDATED_NOMBRE_ATELIERS)
            .specialiase(UPDATED_SPECIALIASE)
            .nombreSalleSpecialise(UPDATED_NOMBRE_SALLE_SPECIALISE)
            .justificationSalleSpe(UPDATED_JUSTIFICATION_SALLE_SPE)
            .cdi(UPDATED_CDI)
            .nombreCDI(UPDATED_NOMBRE_CDI)
            .justifiactifSalleCDI(UPDATED_JUSTIFIACTIF_SALLE_CDI)
            .gym(UPDATED_GYM)
            .terrainSport(UPDATED_TERRAIN_SPORT)
            .autreSalle(UPDATED_AUTRE_SALLE);
        return salle;
    }

    @BeforeEach
    public void initTest() {
        salle = createEntity(em);
    }

    @Test
    @Transactional
    void createSalle() throws Exception {
        int databaseSizeBeforeCreate = salleRepository.findAll().size();
        // Create the Salle
        restSalleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isCreated());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeCreate + 1);
        Salle testSalle = salleList.get(salleList.size() - 1);
        assertThat(testSalle.getNombreSalleclasse()).isEqualTo(DEFAULT_NOMBRE_SALLECLASSE);
        assertThat(testSalle.getNombreAteliers()).isEqualTo(DEFAULT_NOMBRE_ATELIERS);
        assertThat(testSalle.getSpecialiase()).isEqualTo(DEFAULT_SPECIALIASE);
        assertThat(testSalle.getNombreSalleSpecialise()).isEqualTo(DEFAULT_NOMBRE_SALLE_SPECIALISE);
        assertThat(testSalle.getJustificationSalleSpe()).isEqualTo(DEFAULT_JUSTIFICATION_SALLE_SPE);
        assertThat(testSalle.getCdi()).isEqualTo(DEFAULT_CDI);
        assertThat(testSalle.getNombreCDI()).isEqualTo(DEFAULT_NOMBRE_CDI);
        assertThat(testSalle.getJustifiactifSalleCDI()).isEqualTo(DEFAULT_JUSTIFIACTIF_SALLE_CDI);
        assertThat(testSalle.getGym()).isEqualTo(DEFAULT_GYM);
        assertThat(testSalle.getTerrainSport()).isEqualTo(DEFAULT_TERRAIN_SPORT);
        assertThat(testSalle.getAutreSalle()).isEqualTo(DEFAULT_AUTRE_SALLE);
    }

    @Test
    @Transactional
    void createSalleWithExistingId() throws Exception {
        // Create the Salle with an existing ID
        salle.setId(1L);

        int databaseSizeBeforeCreate = salleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isBadRequest());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNombreSalleclasseIsRequired() throws Exception {
        int databaseSizeBeforeTest = salleRepository.findAll().size();
        // set the field null
        salle.setNombreSalleclasse(null);

        // Create the Salle, which fails.

        restSalleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isBadRequest());

        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNombreAteliersIsRequired() throws Exception {
        int databaseSizeBeforeTest = salleRepository.findAll().size();
        // set the field null
        salle.setNombreAteliers(null);

        // Create the Salle, which fails.

        restSalleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isBadRequest());

        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNombreSalleSpecialiseIsRequired() throws Exception {
        int databaseSizeBeforeTest = salleRepository.findAll().size();
        // set the field null
        salle.setNombreSalleSpecialise(null);

        // Create the Salle, which fails.

        restSalleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isBadRequest());

        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSalles() throws Exception {
        // Initialize the database
        salleRepository.saveAndFlush(salle);

        // Get all the salleList
        restSalleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salle.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreSalleclasse").value(hasItem(DEFAULT_NOMBRE_SALLECLASSE)))
            .andExpect(jsonPath("$.[*].nombreAteliers").value(hasItem(DEFAULT_NOMBRE_ATELIERS)))
            .andExpect(jsonPath("$.[*].specialiase").value(hasItem(DEFAULT_SPECIALIASE.toString())))
            .andExpect(jsonPath("$.[*].nombreSalleSpecialise").value(hasItem(DEFAULT_NOMBRE_SALLE_SPECIALISE)))
            .andExpect(jsonPath("$.[*].justificationSalleSpe").value(hasItem(DEFAULT_JUSTIFICATION_SALLE_SPE.toString())))
            .andExpect(jsonPath("$.[*].cdi").value(hasItem(DEFAULT_CDI.toString())))
            .andExpect(jsonPath("$.[*].nombreCDI").value(hasItem(DEFAULT_NOMBRE_CDI)))
            .andExpect(jsonPath("$.[*].justifiactifSalleCDI").value(hasItem(DEFAULT_JUSTIFIACTIF_SALLE_CDI.toString())))
            .andExpect(jsonPath("$.[*].gym").value(hasItem(DEFAULT_GYM.toString())))
            .andExpect(jsonPath("$.[*].terrainSport").value(hasItem(DEFAULT_TERRAIN_SPORT.toString())))
            .andExpect(jsonPath("$.[*].autreSalle").value(hasItem(DEFAULT_AUTRE_SALLE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllSallesWithEagerRelationshipsIsEnabled() throws Exception {
        when(salleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSalleMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(salleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllSallesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(salleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSalleMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(salleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getSalle() throws Exception {
        // Initialize the database
        salleRepository.saveAndFlush(salle);

        // Get the salle
        restSalleMockMvc
            .perform(get(ENTITY_API_URL_ID, salle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salle.getId().intValue()))
            .andExpect(jsonPath("$.nombreSalleclasse").value(DEFAULT_NOMBRE_SALLECLASSE))
            .andExpect(jsonPath("$.nombreAteliers").value(DEFAULT_NOMBRE_ATELIERS))
            .andExpect(jsonPath("$.specialiase").value(DEFAULT_SPECIALIASE.toString()))
            .andExpect(jsonPath("$.nombreSalleSpecialise").value(DEFAULT_NOMBRE_SALLE_SPECIALISE))
            .andExpect(jsonPath("$.justificationSalleSpe").value(DEFAULT_JUSTIFICATION_SALLE_SPE.toString()))
            .andExpect(jsonPath("$.cdi").value(DEFAULT_CDI.toString()))
            .andExpect(jsonPath("$.nombreCDI").value(DEFAULT_NOMBRE_CDI))
            .andExpect(jsonPath("$.justifiactifSalleCDI").value(DEFAULT_JUSTIFIACTIF_SALLE_CDI.toString()))
            .andExpect(jsonPath("$.gym").value(DEFAULT_GYM.toString()))
            .andExpect(jsonPath("$.terrainSport").value(DEFAULT_TERRAIN_SPORT.toString()))
            .andExpect(jsonPath("$.autreSalle").value(DEFAULT_AUTRE_SALLE));
    }

    @Test
    @Transactional
    void getNonExistingSalle() throws Exception {
        // Get the salle
        restSalleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSalle() throws Exception {
        // Initialize the database
        salleRepository.saveAndFlush(salle);

        int databaseSizeBeforeUpdate = salleRepository.findAll().size();

        // Update the salle
        Salle updatedSalle = salleRepository.findById(salle.getId()).get();
        // Disconnect from session so that the updates on updatedSalle are not directly saved in db
        em.detach(updatedSalle);
        updatedSalle
            .nombreSalleclasse(UPDATED_NOMBRE_SALLECLASSE)
            .nombreAteliers(UPDATED_NOMBRE_ATELIERS)
            .specialiase(UPDATED_SPECIALIASE)
            .nombreSalleSpecialise(UPDATED_NOMBRE_SALLE_SPECIALISE)
            .justificationSalleSpe(UPDATED_JUSTIFICATION_SALLE_SPE)
            .cdi(UPDATED_CDI)
            .nombreCDI(UPDATED_NOMBRE_CDI)
            .justifiactifSalleCDI(UPDATED_JUSTIFIACTIF_SALLE_CDI)
            .gym(UPDATED_GYM)
            .terrainSport(UPDATED_TERRAIN_SPORT)
            .autreSalle(UPDATED_AUTRE_SALLE);

        restSalleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSalle.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSalle))
            )
            .andExpect(status().isOk());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
        Salle testSalle = salleList.get(salleList.size() - 1);
        assertThat(testSalle.getNombreSalleclasse()).isEqualTo(UPDATED_NOMBRE_SALLECLASSE);
        assertThat(testSalle.getNombreAteliers()).isEqualTo(UPDATED_NOMBRE_ATELIERS);
        assertThat(testSalle.getSpecialiase()).isEqualTo(UPDATED_SPECIALIASE);
        assertThat(testSalle.getNombreSalleSpecialise()).isEqualTo(UPDATED_NOMBRE_SALLE_SPECIALISE);
        assertThat(testSalle.getJustificationSalleSpe()).isEqualTo(UPDATED_JUSTIFICATION_SALLE_SPE);
        assertThat(testSalle.getCdi()).isEqualTo(UPDATED_CDI);
        assertThat(testSalle.getNombreCDI()).isEqualTo(UPDATED_NOMBRE_CDI);
        assertThat(testSalle.getJustifiactifSalleCDI()).isEqualTo(UPDATED_JUSTIFIACTIF_SALLE_CDI);
        assertThat(testSalle.getGym()).isEqualTo(UPDATED_GYM);
        assertThat(testSalle.getTerrainSport()).isEqualTo(UPDATED_TERRAIN_SPORT);
        assertThat(testSalle.getAutreSalle()).isEqualTo(UPDATED_AUTRE_SALLE);
    }

    @Test
    @Transactional
    void putNonExistingSalle() throws Exception {
        int databaseSizeBeforeUpdate = salleRepository.findAll().size();
        salle.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salle.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalle() throws Exception {
        int databaseSizeBeforeUpdate = salleRepository.findAll().size();
        salle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalle() throws Exception {
        int databaseSizeBeforeUpdate = salleRepository.findAll().size();
        salle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalleWithPatch() throws Exception {
        // Initialize the database
        salleRepository.saveAndFlush(salle);

        int databaseSizeBeforeUpdate = salleRepository.findAll().size();

        // Update the salle using partial update
        Salle partialUpdatedSalle = new Salle();
        partialUpdatedSalle.setId(salle.getId());

        partialUpdatedSalle
            .nombreSalleSpecialise(UPDATED_NOMBRE_SALLE_SPECIALISE)
            .cdi(UPDATED_CDI)
            .nombreCDI(UPDATED_NOMBRE_CDI)
            .justifiactifSalleCDI(UPDATED_JUSTIFIACTIF_SALLE_CDI)
            .terrainSport(UPDATED_TERRAIN_SPORT);

        restSalleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalle))
            )
            .andExpect(status().isOk());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
        Salle testSalle = salleList.get(salleList.size() - 1);
        assertThat(testSalle.getNombreSalleclasse()).isEqualTo(DEFAULT_NOMBRE_SALLECLASSE);
        assertThat(testSalle.getNombreAteliers()).isEqualTo(DEFAULT_NOMBRE_ATELIERS);
        assertThat(testSalle.getSpecialiase()).isEqualTo(DEFAULT_SPECIALIASE);
        assertThat(testSalle.getNombreSalleSpecialise()).isEqualTo(UPDATED_NOMBRE_SALLE_SPECIALISE);
        assertThat(testSalle.getJustificationSalleSpe()).isEqualTo(DEFAULT_JUSTIFICATION_SALLE_SPE);
        assertThat(testSalle.getCdi()).isEqualTo(UPDATED_CDI);
        assertThat(testSalle.getNombreCDI()).isEqualTo(UPDATED_NOMBRE_CDI);
        assertThat(testSalle.getJustifiactifSalleCDI()).isEqualTo(UPDATED_JUSTIFIACTIF_SALLE_CDI);
        assertThat(testSalle.getGym()).isEqualTo(DEFAULT_GYM);
        assertThat(testSalle.getTerrainSport()).isEqualTo(UPDATED_TERRAIN_SPORT);
        assertThat(testSalle.getAutreSalle()).isEqualTo(DEFAULT_AUTRE_SALLE);
    }

    @Test
    @Transactional
    void fullUpdateSalleWithPatch() throws Exception {
        // Initialize the database
        salleRepository.saveAndFlush(salle);

        int databaseSizeBeforeUpdate = salleRepository.findAll().size();

        // Update the salle using partial update
        Salle partialUpdatedSalle = new Salle();
        partialUpdatedSalle.setId(salle.getId());

        partialUpdatedSalle
            .nombreSalleclasse(UPDATED_NOMBRE_SALLECLASSE)
            .nombreAteliers(UPDATED_NOMBRE_ATELIERS)
            .specialiase(UPDATED_SPECIALIASE)
            .nombreSalleSpecialise(UPDATED_NOMBRE_SALLE_SPECIALISE)
            .justificationSalleSpe(UPDATED_JUSTIFICATION_SALLE_SPE)
            .cdi(UPDATED_CDI)
            .nombreCDI(UPDATED_NOMBRE_CDI)
            .justifiactifSalleCDI(UPDATED_JUSTIFIACTIF_SALLE_CDI)
            .gym(UPDATED_GYM)
            .terrainSport(UPDATED_TERRAIN_SPORT)
            .autreSalle(UPDATED_AUTRE_SALLE);

        restSalleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalle))
            )
            .andExpect(status().isOk());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
        Salle testSalle = salleList.get(salleList.size() - 1);
        assertThat(testSalle.getNombreSalleclasse()).isEqualTo(UPDATED_NOMBRE_SALLECLASSE);
        assertThat(testSalle.getNombreAteliers()).isEqualTo(UPDATED_NOMBRE_ATELIERS);
        assertThat(testSalle.getSpecialiase()).isEqualTo(UPDATED_SPECIALIASE);
        assertThat(testSalle.getNombreSalleSpecialise()).isEqualTo(UPDATED_NOMBRE_SALLE_SPECIALISE);
        assertThat(testSalle.getJustificationSalleSpe()).isEqualTo(UPDATED_JUSTIFICATION_SALLE_SPE);
        assertThat(testSalle.getCdi()).isEqualTo(UPDATED_CDI);
        assertThat(testSalle.getNombreCDI()).isEqualTo(UPDATED_NOMBRE_CDI);
        assertThat(testSalle.getJustifiactifSalleCDI()).isEqualTo(UPDATED_JUSTIFIACTIF_SALLE_CDI);
        assertThat(testSalle.getGym()).isEqualTo(UPDATED_GYM);
        assertThat(testSalle.getTerrainSport()).isEqualTo(UPDATED_TERRAIN_SPORT);
        assertThat(testSalle.getAutreSalle()).isEqualTo(UPDATED_AUTRE_SALLE);
    }

    @Test
    @Transactional
    void patchNonExistingSalle() throws Exception {
        int databaseSizeBeforeUpdate = salleRepository.findAll().size();
        salle.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalle() throws Exception {
        int databaseSizeBeforeUpdate = salleRepository.findAll().size();
        salle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalle() throws Exception {
        int databaseSizeBeforeUpdate = salleRepository.findAll().size();
        salle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalleMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(salle)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Salle in the database
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalle() throws Exception {
        // Initialize the database
        salleRepository.saveAndFlush(salle);

        int databaseSizeBeforeDelete = salleRepository.findAll().size();

        // Delete the salle
        restSalleMockMvc
            .perform(delete(ENTITY_API_URL_ID, salle.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Salle> salleList = salleRepository.findAll();
        assertThat(salleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
