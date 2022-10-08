package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Filiere;
import com.mycompany.myapp.domain.enumeration.ELEVAGE;
import com.mycompany.myapp.domain.enumeration.Filieres;
import com.mycompany.myapp.domain.enumeration.SANTEBIOLOGIECHIMIE;
import com.mycompany.myapp.repository.FiliereRepository;
import com.mycompany.myapp.service.FiliereService;
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

/**
 * Integration tests for the {@link FiliereResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class FiliereResourceIT {

    private static final Filieres DEFAULT_NOM_FILIERE = Filieres.AGRICULTURE;
    private static final Filieres UPDATED_NOM_FILIERE = Filieres.ELEVAGE;

    private static final String DEFAULT_NOM_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_AUTRE = "BBBBBBBBBB";

    private static final SANTEBIOLOGIECHIMIE DEFAULT_NOM_PROGRAMME = SANTEBIOLOGIECHIMIE.AGENT_DE_SANTE_COMMUNAUTAIRE;
    private static final SANTEBIOLOGIECHIMIE UPDATED_NOM_PROGRAMME = SANTEBIOLOGIECHIMIE.AGENT_DE_STERILISATION_EN_MILIEU_HOSPITALIER;

    private static final String DEFAULT_AUTRE_AGR = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_AGR = "BBBBBBBBBB";

    private static final ELEVAGE DEFAULT_NOM_PROGRAMME_EL = ELEVAGE.AVICULTURE;
    private static final ELEVAGE UPDATED_NOM_PROGRAMME_EL = ELEVAGE.AUTRE;

    private static final String DEFAULT_AUTRE_EL = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_EL = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_MIN = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_MIN = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_BAT = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_BAT = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_MECAN = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_MECAN = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_MENU = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_MENU = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_AGRO = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_AGRO = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_ELECTRO = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_ELECTRO = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_STRUC = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_STRUC = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_EC = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_EC = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_COM = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_COM = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_IN = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_IN = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_SAN = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_SAN = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_PROGRAMME = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_PROGRAMME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/filieres";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FiliereRepository filiereRepository;

    @Mock
    private FiliereRepository filiereRepositoryMock;

    @Mock
    private FiliereService filiereServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFiliereMockMvc;

    private Filiere filiere;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Filiere createEntity(EntityManager em) {
        Filiere filiere = new Filiere()
            .nomFiliere(DEFAULT_NOM_FILIERE)
            .nomAutre(DEFAULT_NOM_AUTRE)
            .nomProgramme(DEFAULT_NOM_PROGRAMME)
            .autreAGR(DEFAULT_AUTRE_AGR)
            .nomProgrammeEl(DEFAULT_NOM_PROGRAMME_EL)
            .autreEL(DEFAULT_AUTRE_EL)
            .autreMIN(DEFAULT_AUTRE_MIN)
            .autreBAT(DEFAULT_AUTRE_BAT)
            .autreMECAN(DEFAULT_AUTRE_MECAN)
            .autreMENU(DEFAULT_AUTRE_MENU)
            .autreAGRO(DEFAULT_AUTRE_AGRO)
            .autreELECTRO(DEFAULT_AUTRE_ELECTRO)
            .autreSTRUC(DEFAULT_AUTRE_STRUC)
            .autreEC(DEFAULT_AUTRE_EC)
            .autreCOM(DEFAULT_AUTRE_COM)
            .autreIN(DEFAULT_AUTRE_IN)
            .autreSAN(DEFAULT_AUTRE_SAN)
            .autreProgramme(DEFAULT_AUTRE_PROGRAMME);
        return filiere;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Filiere createUpdatedEntity(EntityManager em) {
        Filiere filiere = new Filiere()
            .nomFiliere(UPDATED_NOM_FILIERE)
            .nomAutre(UPDATED_NOM_AUTRE)
            .nomProgramme(UPDATED_NOM_PROGRAMME)
            .autreAGR(UPDATED_AUTRE_AGR)
            .nomProgrammeEl(UPDATED_NOM_PROGRAMME_EL)
            .autreEL(UPDATED_AUTRE_EL)
            .autreMIN(UPDATED_AUTRE_MIN)
            .autreBAT(UPDATED_AUTRE_BAT)
            .autreMECAN(UPDATED_AUTRE_MECAN)
            .autreMENU(UPDATED_AUTRE_MENU)
            .autreAGRO(UPDATED_AUTRE_AGRO)
            .autreELECTRO(UPDATED_AUTRE_ELECTRO)
            .autreSTRUC(UPDATED_AUTRE_STRUC)
            .autreEC(UPDATED_AUTRE_EC)
            .autreCOM(UPDATED_AUTRE_COM)
            .autreIN(UPDATED_AUTRE_IN)
            .autreSAN(UPDATED_AUTRE_SAN)
            .autreProgramme(UPDATED_AUTRE_PROGRAMME);
        return filiere;
    }

    @BeforeEach
    public void initTest() {
        filiere = createEntity(em);
    }

    @Test
    @Transactional
    void createFiliere() throws Exception {
        int databaseSizeBeforeCreate = filiereRepository.findAll().size();
        // Create the Filiere
        restFiliereMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(filiere)))
            .andExpect(status().isCreated());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeCreate + 1);
        Filiere testFiliere = filiereList.get(filiereList.size() - 1);
        assertThat(testFiliere.getNomFiliere()).isEqualTo(DEFAULT_NOM_FILIERE);
        assertThat(testFiliere.getNomAutre()).isEqualTo(DEFAULT_NOM_AUTRE);
        assertThat(testFiliere.getNomProgramme()).isEqualTo(DEFAULT_NOM_PROGRAMME);
        assertThat(testFiliere.getAutreAGR()).isEqualTo(DEFAULT_AUTRE_AGR);
        assertThat(testFiliere.getNomProgrammeEl()).isEqualTo(DEFAULT_NOM_PROGRAMME_EL);
        assertThat(testFiliere.getAutreEL()).isEqualTo(DEFAULT_AUTRE_EL);
        assertThat(testFiliere.getAutreMIN()).isEqualTo(DEFAULT_AUTRE_MIN);
        assertThat(testFiliere.getAutreBAT()).isEqualTo(DEFAULT_AUTRE_BAT);
        assertThat(testFiliere.getAutreMECAN()).isEqualTo(DEFAULT_AUTRE_MECAN);
        assertThat(testFiliere.getAutreMENU()).isEqualTo(DEFAULT_AUTRE_MENU);
        assertThat(testFiliere.getAutreAGRO()).isEqualTo(DEFAULT_AUTRE_AGRO);
        assertThat(testFiliere.getAutreELECTRO()).isEqualTo(DEFAULT_AUTRE_ELECTRO);
        assertThat(testFiliere.getAutreSTRUC()).isEqualTo(DEFAULT_AUTRE_STRUC);
        assertThat(testFiliere.getAutreEC()).isEqualTo(DEFAULT_AUTRE_EC);
        assertThat(testFiliere.getAutreCOM()).isEqualTo(DEFAULT_AUTRE_COM);
        assertThat(testFiliere.getAutreIN()).isEqualTo(DEFAULT_AUTRE_IN);
        assertThat(testFiliere.getAutreSAN()).isEqualTo(DEFAULT_AUTRE_SAN);
        assertThat(testFiliere.getAutreProgramme()).isEqualTo(DEFAULT_AUTRE_PROGRAMME);
    }

    @Test
    @Transactional
    void createFiliereWithExistingId() throws Exception {
        // Create the Filiere with an existing ID
        filiere.setId(1L);

        int databaseSizeBeforeCreate = filiereRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFiliereMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(filiere)))
            .andExpect(status().isBadRequest());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNomFiliereIsRequired() throws Exception {
        int databaseSizeBeforeTest = filiereRepository.findAll().size();
        // set the field null
        filiere.setNomFiliere(null);

        // Create the Filiere, which fails.

        restFiliereMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(filiere)))
            .andExpect(status().isBadRequest());

        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFilieres() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);

        // Get all the filiereList
        restFiliereMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(filiere.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomFiliere").value(hasItem(DEFAULT_NOM_FILIERE.toString())))
            .andExpect(jsonPath("$.[*].nomAutre").value(hasItem(DEFAULT_NOM_AUTRE)))
            .andExpect(jsonPath("$.[*].nomProgramme").value(hasItem(DEFAULT_NOM_PROGRAMME.toString())))
            .andExpect(jsonPath("$.[*].autreAGR").value(hasItem(DEFAULT_AUTRE_AGR)))
            .andExpect(jsonPath("$.[*].nomProgrammeEl").value(hasItem(DEFAULT_NOM_PROGRAMME_EL.toString())))
            .andExpect(jsonPath("$.[*].autreEL").value(hasItem(DEFAULT_AUTRE_EL)))
            .andExpect(jsonPath("$.[*].autreMIN").value(hasItem(DEFAULT_AUTRE_MIN)))
            .andExpect(jsonPath("$.[*].autreBAT").value(hasItem(DEFAULT_AUTRE_BAT)))
            .andExpect(jsonPath("$.[*].autreMECAN").value(hasItem(DEFAULT_AUTRE_MECAN)))
            .andExpect(jsonPath("$.[*].autreMENU").value(hasItem(DEFAULT_AUTRE_MENU)))
            .andExpect(jsonPath("$.[*].autreAGRO").value(hasItem(DEFAULT_AUTRE_AGRO)))
            .andExpect(jsonPath("$.[*].autreELECTRO").value(hasItem(DEFAULT_AUTRE_ELECTRO)))
            .andExpect(jsonPath("$.[*].autreSTRUC").value(hasItem(DEFAULT_AUTRE_STRUC)))
            .andExpect(jsonPath("$.[*].autreEC").value(hasItem(DEFAULT_AUTRE_EC)))
            .andExpect(jsonPath("$.[*].autreCOM").value(hasItem(DEFAULT_AUTRE_COM)))
            .andExpect(jsonPath("$.[*].autreIN").value(hasItem(DEFAULT_AUTRE_IN)))
            .andExpect(jsonPath("$.[*].autreSAN").value(hasItem(DEFAULT_AUTRE_SAN)))
            .andExpect(jsonPath("$.[*].autreProgramme").value(hasItem(DEFAULT_AUTRE_PROGRAMME)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFilieresWithEagerRelationshipsIsEnabled() throws Exception {
        when(filiereServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFiliereMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(filiereServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFilieresWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(filiereServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFiliereMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(filiereServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getFiliere() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);

        // Get the filiere
        restFiliereMockMvc
            .perform(get(ENTITY_API_URL_ID, filiere.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(filiere.getId().intValue()))
            .andExpect(jsonPath("$.nomFiliere").value(DEFAULT_NOM_FILIERE.toString()))
            .andExpect(jsonPath("$.nomAutre").value(DEFAULT_NOM_AUTRE))
            .andExpect(jsonPath("$.nomProgramme").value(DEFAULT_NOM_PROGRAMME.toString()))
            .andExpect(jsonPath("$.autreAGR").value(DEFAULT_AUTRE_AGR))
            .andExpect(jsonPath("$.nomProgrammeEl").value(DEFAULT_NOM_PROGRAMME_EL.toString()))
            .andExpect(jsonPath("$.autreEL").value(DEFAULT_AUTRE_EL))
            .andExpect(jsonPath("$.autreMIN").value(DEFAULT_AUTRE_MIN))
            .andExpect(jsonPath("$.autreBAT").value(DEFAULT_AUTRE_BAT))
            .andExpect(jsonPath("$.autreMECAN").value(DEFAULT_AUTRE_MECAN))
            .andExpect(jsonPath("$.autreMENU").value(DEFAULT_AUTRE_MENU))
            .andExpect(jsonPath("$.autreAGRO").value(DEFAULT_AUTRE_AGRO))
            .andExpect(jsonPath("$.autreELECTRO").value(DEFAULT_AUTRE_ELECTRO))
            .andExpect(jsonPath("$.autreSTRUC").value(DEFAULT_AUTRE_STRUC))
            .andExpect(jsonPath("$.autreEC").value(DEFAULT_AUTRE_EC))
            .andExpect(jsonPath("$.autreCOM").value(DEFAULT_AUTRE_COM))
            .andExpect(jsonPath("$.autreIN").value(DEFAULT_AUTRE_IN))
            .andExpect(jsonPath("$.autreSAN").value(DEFAULT_AUTRE_SAN))
            .andExpect(jsonPath("$.autreProgramme").value(DEFAULT_AUTRE_PROGRAMME));
    }

    @Test
    @Transactional
    void getNonExistingFiliere() throws Exception {
        // Get the filiere
        restFiliereMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFiliere() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);

        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();

        // Update the filiere
        Filiere updatedFiliere = filiereRepository.findById(filiere.getId()).get();
        // Disconnect from session so that the updates on updatedFiliere are not directly saved in db
        em.detach(updatedFiliere);
        updatedFiliere
            .nomFiliere(UPDATED_NOM_FILIERE)
            .nomAutre(UPDATED_NOM_AUTRE)
            .nomProgramme(UPDATED_NOM_PROGRAMME)
            .autreAGR(UPDATED_AUTRE_AGR)
            .nomProgrammeEl(UPDATED_NOM_PROGRAMME_EL)
            .autreEL(UPDATED_AUTRE_EL)
            .autreMIN(UPDATED_AUTRE_MIN)
            .autreBAT(UPDATED_AUTRE_BAT)
            .autreMECAN(UPDATED_AUTRE_MECAN)
            .autreMENU(UPDATED_AUTRE_MENU)
            .autreAGRO(UPDATED_AUTRE_AGRO)
            .autreELECTRO(UPDATED_AUTRE_ELECTRO)
            .autreSTRUC(UPDATED_AUTRE_STRUC)
            .autreEC(UPDATED_AUTRE_EC)
            .autreCOM(UPDATED_AUTRE_COM)
            .autreIN(UPDATED_AUTRE_IN)
            .autreSAN(UPDATED_AUTRE_SAN)
            .autreProgramme(UPDATED_AUTRE_PROGRAMME);

        restFiliereMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFiliere.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFiliere))
            )
            .andExpect(status().isOk());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
        Filiere testFiliere = filiereList.get(filiereList.size() - 1);
        assertThat(testFiliere.getNomFiliere()).isEqualTo(UPDATED_NOM_FILIERE);
        assertThat(testFiliere.getNomAutre()).isEqualTo(UPDATED_NOM_AUTRE);
        assertThat(testFiliere.getNomProgramme()).isEqualTo(UPDATED_NOM_PROGRAMME);
        assertThat(testFiliere.getAutreAGR()).isEqualTo(UPDATED_AUTRE_AGR);
        assertThat(testFiliere.getNomProgrammeEl()).isEqualTo(UPDATED_NOM_PROGRAMME_EL);
        assertThat(testFiliere.getAutreEL()).isEqualTo(UPDATED_AUTRE_EL);
        assertThat(testFiliere.getAutreMIN()).isEqualTo(UPDATED_AUTRE_MIN);
        assertThat(testFiliere.getAutreBAT()).isEqualTo(UPDATED_AUTRE_BAT);
        assertThat(testFiliere.getAutreMECAN()).isEqualTo(UPDATED_AUTRE_MECAN);
        assertThat(testFiliere.getAutreMENU()).isEqualTo(UPDATED_AUTRE_MENU);
        assertThat(testFiliere.getAutreAGRO()).isEqualTo(UPDATED_AUTRE_AGRO);
        assertThat(testFiliere.getAutreELECTRO()).isEqualTo(UPDATED_AUTRE_ELECTRO);
        assertThat(testFiliere.getAutreSTRUC()).isEqualTo(UPDATED_AUTRE_STRUC);
        assertThat(testFiliere.getAutreEC()).isEqualTo(UPDATED_AUTRE_EC);
        assertThat(testFiliere.getAutreCOM()).isEqualTo(UPDATED_AUTRE_COM);
        assertThat(testFiliere.getAutreIN()).isEqualTo(UPDATED_AUTRE_IN);
        assertThat(testFiliere.getAutreSAN()).isEqualTo(UPDATED_AUTRE_SAN);
        assertThat(testFiliere.getAutreProgramme()).isEqualTo(UPDATED_AUTRE_PROGRAMME);
    }

    @Test
    @Transactional
    void putNonExistingFiliere() throws Exception {
        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();
        filiere.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFiliereMockMvc
            .perform(
                put(ENTITY_API_URL_ID, filiere.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(filiere))
            )
            .andExpect(status().isBadRequest());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFiliere() throws Exception {
        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();
        filiere.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFiliereMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(filiere))
            )
            .andExpect(status().isBadRequest());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFiliere() throws Exception {
        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();
        filiere.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFiliereMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(filiere)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFiliereWithPatch() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);

        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();

        // Update the filiere using partial update
        Filiere partialUpdatedFiliere = new Filiere();
        partialUpdatedFiliere.setId(filiere.getId());

        partialUpdatedFiliere
            .nomFiliere(UPDATED_NOM_FILIERE)
            .nomAutre(UPDATED_NOM_AUTRE)
            .nomProgrammeEl(UPDATED_NOM_PROGRAMME_EL)
            .autreEL(UPDATED_AUTRE_EL)
            .autreMIN(UPDATED_AUTRE_MIN)
            .autreSTRUC(UPDATED_AUTRE_STRUC)
            .autreIN(UPDATED_AUTRE_IN)
            .autreSAN(UPDATED_AUTRE_SAN);

        restFiliereMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiliere.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFiliere))
            )
            .andExpect(status().isOk());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
        Filiere testFiliere = filiereList.get(filiereList.size() - 1);
        assertThat(testFiliere.getNomFiliere()).isEqualTo(UPDATED_NOM_FILIERE);
        assertThat(testFiliere.getNomAutre()).isEqualTo(UPDATED_NOM_AUTRE);
        assertThat(testFiliere.getNomProgramme()).isEqualTo(DEFAULT_NOM_PROGRAMME);
        assertThat(testFiliere.getAutreAGR()).isEqualTo(DEFAULT_AUTRE_AGR);
        assertThat(testFiliere.getNomProgrammeEl()).isEqualTo(UPDATED_NOM_PROGRAMME_EL);
        assertThat(testFiliere.getAutreEL()).isEqualTo(UPDATED_AUTRE_EL);
        assertThat(testFiliere.getAutreMIN()).isEqualTo(UPDATED_AUTRE_MIN);
        assertThat(testFiliere.getAutreBAT()).isEqualTo(DEFAULT_AUTRE_BAT);
        assertThat(testFiliere.getAutreMECAN()).isEqualTo(DEFAULT_AUTRE_MECAN);
        assertThat(testFiliere.getAutreMENU()).isEqualTo(DEFAULT_AUTRE_MENU);
        assertThat(testFiliere.getAutreAGRO()).isEqualTo(DEFAULT_AUTRE_AGRO);
        assertThat(testFiliere.getAutreELECTRO()).isEqualTo(DEFAULT_AUTRE_ELECTRO);
        assertThat(testFiliere.getAutreSTRUC()).isEqualTo(UPDATED_AUTRE_STRUC);
        assertThat(testFiliere.getAutreEC()).isEqualTo(DEFAULT_AUTRE_EC);
        assertThat(testFiliere.getAutreCOM()).isEqualTo(DEFAULT_AUTRE_COM);
        assertThat(testFiliere.getAutreIN()).isEqualTo(UPDATED_AUTRE_IN);
        assertThat(testFiliere.getAutreSAN()).isEqualTo(UPDATED_AUTRE_SAN);
        assertThat(testFiliere.getAutreProgramme()).isEqualTo(DEFAULT_AUTRE_PROGRAMME);
    }

    @Test
    @Transactional
    void fullUpdateFiliereWithPatch() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);

        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();

        // Update the filiere using partial update
        Filiere partialUpdatedFiliere = new Filiere();
        partialUpdatedFiliere.setId(filiere.getId());

        partialUpdatedFiliere
            .nomFiliere(UPDATED_NOM_FILIERE)
            .nomAutre(UPDATED_NOM_AUTRE)
            .nomProgramme(UPDATED_NOM_PROGRAMME)
            .autreAGR(UPDATED_AUTRE_AGR)
            .nomProgrammeEl(UPDATED_NOM_PROGRAMME_EL)
            .autreEL(UPDATED_AUTRE_EL)
            .autreMIN(UPDATED_AUTRE_MIN)
            .autreBAT(UPDATED_AUTRE_BAT)
            .autreMECAN(UPDATED_AUTRE_MECAN)
            .autreMENU(UPDATED_AUTRE_MENU)
            .autreAGRO(UPDATED_AUTRE_AGRO)
            .autreELECTRO(UPDATED_AUTRE_ELECTRO)
            .autreSTRUC(UPDATED_AUTRE_STRUC)
            .autreEC(UPDATED_AUTRE_EC)
            .autreCOM(UPDATED_AUTRE_COM)
            .autreIN(UPDATED_AUTRE_IN)
            .autreSAN(UPDATED_AUTRE_SAN)
            .autreProgramme(UPDATED_AUTRE_PROGRAMME);

        restFiliereMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiliere.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFiliere))
            )
            .andExpect(status().isOk());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
        Filiere testFiliere = filiereList.get(filiereList.size() - 1);
        assertThat(testFiliere.getNomFiliere()).isEqualTo(UPDATED_NOM_FILIERE);
        assertThat(testFiliere.getNomAutre()).isEqualTo(UPDATED_NOM_AUTRE);
        assertThat(testFiliere.getNomProgramme()).isEqualTo(UPDATED_NOM_PROGRAMME);
        assertThat(testFiliere.getAutreAGR()).isEqualTo(UPDATED_AUTRE_AGR);
        assertThat(testFiliere.getNomProgrammeEl()).isEqualTo(UPDATED_NOM_PROGRAMME_EL);
        assertThat(testFiliere.getAutreEL()).isEqualTo(UPDATED_AUTRE_EL);
        assertThat(testFiliere.getAutreMIN()).isEqualTo(UPDATED_AUTRE_MIN);
        assertThat(testFiliere.getAutreBAT()).isEqualTo(UPDATED_AUTRE_BAT);
        assertThat(testFiliere.getAutreMECAN()).isEqualTo(UPDATED_AUTRE_MECAN);
        assertThat(testFiliere.getAutreMENU()).isEqualTo(UPDATED_AUTRE_MENU);
        assertThat(testFiliere.getAutreAGRO()).isEqualTo(UPDATED_AUTRE_AGRO);
        assertThat(testFiliere.getAutreELECTRO()).isEqualTo(UPDATED_AUTRE_ELECTRO);
        assertThat(testFiliere.getAutreSTRUC()).isEqualTo(UPDATED_AUTRE_STRUC);
        assertThat(testFiliere.getAutreEC()).isEqualTo(UPDATED_AUTRE_EC);
        assertThat(testFiliere.getAutreCOM()).isEqualTo(UPDATED_AUTRE_COM);
        assertThat(testFiliere.getAutreIN()).isEqualTo(UPDATED_AUTRE_IN);
        assertThat(testFiliere.getAutreSAN()).isEqualTo(UPDATED_AUTRE_SAN);
        assertThat(testFiliere.getAutreProgramme()).isEqualTo(UPDATED_AUTRE_PROGRAMME);
    }

    @Test
    @Transactional
    void patchNonExistingFiliere() throws Exception {
        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();
        filiere.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFiliereMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, filiere.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(filiere))
            )
            .andExpect(status().isBadRequest());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFiliere() throws Exception {
        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();
        filiere.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFiliereMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(filiere))
            )
            .andExpect(status().isBadRequest());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFiliere() throws Exception {
        int databaseSizeBeforeUpdate = filiereRepository.findAll().size();
        filiere.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFiliereMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(filiere)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Filiere in the database
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFiliere() throws Exception {
        // Initialize the database
        filiereRepository.saveAndFlush(filiere);

        int databaseSizeBeforeDelete = filiereRepository.findAll().size();

        // Delete the filiere
        restFiliereMockMvc
            .perform(delete(ENTITY_API_URL_ID, filiere.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Filiere> filiereList = filiereRepository.findAll();
        assertThat(filiereList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
