package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Partenaire;
import com.mycompany.myapp.domain.enumeration.NaturePart;
import com.mycompany.myapp.domain.enumeration.Provenance;
import com.mycompany.myapp.repository.PartenaireRepository;
import com.mycompany.myapp.service.PartenaireService;
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
 * Integration tests for the {@link PartenaireResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class PartenaireResourceIT {

    private static final String DEFAULT_DENOMINATION_PARTENAIRE = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION_PARTENAIRE = "BBBBBBBBBB";

    private static final Provenance DEFAULT_STATAU_PARTENAIRE = Provenance.ENTREPRISE;
    private static final Provenance UPDATED_STATAU_PARTENAIRE = Provenance.ONG;

    private static final String DEFAULT_AUTRE_CATEGORIE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_CATEGORIE = "BBBBBBBBBB";

    private static final NaturePart DEFAULT_TYPE_APPUI = NaturePart.TECHNIQUE;
    private static final NaturePart UPDATED_TYPE_APPUI = NaturePart.FINANCIER;

    private static final String DEFAULT_AUTRE_NATURE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_NATURE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/partenaires";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PartenaireRepository partenaireRepository;

    @Mock
    private PartenaireRepository partenaireRepositoryMock;

    @Mock
    private PartenaireService partenaireServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPartenaireMockMvc;

    private Partenaire partenaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Partenaire createEntity(EntityManager em) {
        Partenaire partenaire = new Partenaire()
            .denominationPartenaire(DEFAULT_DENOMINATION_PARTENAIRE)
            .statauPartenaire(DEFAULT_STATAU_PARTENAIRE)
            .autreCategorie(DEFAULT_AUTRE_CATEGORIE)
            .typeAppui(DEFAULT_TYPE_APPUI)
            .autreNature(DEFAULT_AUTRE_NATURE);
        return partenaire;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Partenaire createUpdatedEntity(EntityManager em) {
        Partenaire partenaire = new Partenaire()
            .denominationPartenaire(UPDATED_DENOMINATION_PARTENAIRE)
            .statauPartenaire(UPDATED_STATAU_PARTENAIRE)
            .autreCategorie(UPDATED_AUTRE_CATEGORIE)
            .typeAppui(UPDATED_TYPE_APPUI)
            .autreNature(UPDATED_AUTRE_NATURE);
        return partenaire;
    }

    @BeforeEach
    public void initTest() {
        partenaire = createEntity(em);
    }

    @Test
    @Transactional
    void createPartenaire() throws Exception {
        int databaseSizeBeforeCreate = partenaireRepository.findAll().size();
        // Create the Partenaire
        restPartenaireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partenaire)))
            .andExpect(status().isCreated());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeCreate + 1);
        Partenaire testPartenaire = partenaireList.get(partenaireList.size() - 1);
        assertThat(testPartenaire.getDenominationPartenaire()).isEqualTo(DEFAULT_DENOMINATION_PARTENAIRE);
        assertThat(testPartenaire.getStatauPartenaire()).isEqualTo(DEFAULT_STATAU_PARTENAIRE);
        assertThat(testPartenaire.getAutreCategorie()).isEqualTo(DEFAULT_AUTRE_CATEGORIE);
        assertThat(testPartenaire.getTypeAppui()).isEqualTo(DEFAULT_TYPE_APPUI);
        assertThat(testPartenaire.getAutreNature()).isEqualTo(DEFAULT_AUTRE_NATURE);
    }

    @Test
    @Transactional
    void createPartenaireWithExistingId() throws Exception {
        // Create the Partenaire with an existing ID
        partenaire.setId(1L);

        int databaseSizeBeforeCreate = partenaireRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPartenaireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partenaire)))
            .andExpect(status().isBadRequest());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDenominationPartenaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = partenaireRepository.findAll().size();
        // set the field null
        partenaire.setDenominationPartenaire(null);

        // Create the Partenaire, which fails.

        restPartenaireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partenaire)))
            .andExpect(status().isBadRequest());

        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatauPartenaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = partenaireRepository.findAll().size();
        // set the field null
        partenaire.setStatauPartenaire(null);

        // Create the Partenaire, which fails.

        restPartenaireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partenaire)))
            .andExpect(status().isBadRequest());

        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeAppuiIsRequired() throws Exception {
        int databaseSizeBeforeTest = partenaireRepository.findAll().size();
        // set the field null
        partenaire.setTypeAppui(null);

        // Create the Partenaire, which fails.

        restPartenaireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partenaire)))
            .andExpect(status().isBadRequest());

        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPartenaires() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        // Get all the partenaireList
        restPartenaireMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(partenaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].denominationPartenaire").value(hasItem(DEFAULT_DENOMINATION_PARTENAIRE)))
            .andExpect(jsonPath("$.[*].statauPartenaire").value(hasItem(DEFAULT_STATAU_PARTENAIRE.toString())))
            .andExpect(jsonPath("$.[*].autreCategorie").value(hasItem(DEFAULT_AUTRE_CATEGORIE)))
            .andExpect(jsonPath("$.[*].typeAppui").value(hasItem(DEFAULT_TYPE_APPUI.toString())))
            .andExpect(jsonPath("$.[*].autreNature").value(hasItem(DEFAULT_AUTRE_NATURE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPartenairesWithEagerRelationshipsIsEnabled() throws Exception {
        when(partenaireServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPartenaireMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(partenaireServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPartenairesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(partenaireServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPartenaireMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(partenaireServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getPartenaire() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        // Get the partenaire
        restPartenaireMockMvc
            .perform(get(ENTITY_API_URL_ID, partenaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(partenaire.getId().intValue()))
            .andExpect(jsonPath("$.denominationPartenaire").value(DEFAULT_DENOMINATION_PARTENAIRE))
            .andExpect(jsonPath("$.statauPartenaire").value(DEFAULT_STATAU_PARTENAIRE.toString()))
            .andExpect(jsonPath("$.autreCategorie").value(DEFAULT_AUTRE_CATEGORIE))
            .andExpect(jsonPath("$.typeAppui").value(DEFAULT_TYPE_APPUI.toString()))
            .andExpect(jsonPath("$.autreNature").value(DEFAULT_AUTRE_NATURE));
    }

    @Test
    @Transactional
    void getNonExistingPartenaire() throws Exception {
        // Get the partenaire
        restPartenaireMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPartenaire() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();

        // Update the partenaire
        Partenaire updatedPartenaire = partenaireRepository.findById(partenaire.getId()).get();
        // Disconnect from session so that the updates on updatedPartenaire are not directly saved in db
        em.detach(updatedPartenaire);
        updatedPartenaire
            .denominationPartenaire(UPDATED_DENOMINATION_PARTENAIRE)
            .statauPartenaire(UPDATED_STATAU_PARTENAIRE)
            .autreCategorie(UPDATED_AUTRE_CATEGORIE)
            .typeAppui(UPDATED_TYPE_APPUI)
            .autreNature(UPDATED_AUTRE_NATURE);

        restPartenaireMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPartenaire.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPartenaire))
            )
            .andExpect(status().isOk());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
        Partenaire testPartenaire = partenaireList.get(partenaireList.size() - 1);
        assertThat(testPartenaire.getDenominationPartenaire()).isEqualTo(UPDATED_DENOMINATION_PARTENAIRE);
        assertThat(testPartenaire.getStatauPartenaire()).isEqualTo(UPDATED_STATAU_PARTENAIRE);
        assertThat(testPartenaire.getAutreCategorie()).isEqualTo(UPDATED_AUTRE_CATEGORIE);
        assertThat(testPartenaire.getTypeAppui()).isEqualTo(UPDATED_TYPE_APPUI);
        assertThat(testPartenaire.getAutreNature()).isEqualTo(UPDATED_AUTRE_NATURE);
    }

    @Test
    @Transactional
    void putNonExistingPartenaire() throws Exception {
        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();
        partenaire.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartenaireMockMvc
            .perform(
                put(ENTITY_API_URL_ID, partenaire.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(partenaire))
            )
            .andExpect(status().isBadRequest());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPartenaire() throws Exception {
        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();
        partenaire.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPartenaireMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(partenaire))
            )
            .andExpect(status().isBadRequest());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPartenaire() throws Exception {
        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();
        partenaire.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPartenaireMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partenaire)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePartenaireWithPatch() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();

        // Update the partenaire using partial update
        Partenaire partialUpdatedPartenaire = new Partenaire();
        partialUpdatedPartenaire.setId(partenaire.getId());

        partialUpdatedPartenaire.statauPartenaire(UPDATED_STATAU_PARTENAIRE).typeAppui(UPDATED_TYPE_APPUI);

        restPartenaireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPartenaire.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPartenaire))
            )
            .andExpect(status().isOk());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
        Partenaire testPartenaire = partenaireList.get(partenaireList.size() - 1);
        assertThat(testPartenaire.getDenominationPartenaire()).isEqualTo(DEFAULT_DENOMINATION_PARTENAIRE);
        assertThat(testPartenaire.getStatauPartenaire()).isEqualTo(UPDATED_STATAU_PARTENAIRE);
        assertThat(testPartenaire.getAutreCategorie()).isEqualTo(DEFAULT_AUTRE_CATEGORIE);
        assertThat(testPartenaire.getTypeAppui()).isEqualTo(UPDATED_TYPE_APPUI);
        assertThat(testPartenaire.getAutreNature()).isEqualTo(DEFAULT_AUTRE_NATURE);
    }

    @Test
    @Transactional
    void fullUpdatePartenaireWithPatch() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();

        // Update the partenaire using partial update
        Partenaire partialUpdatedPartenaire = new Partenaire();
        partialUpdatedPartenaire.setId(partenaire.getId());

        partialUpdatedPartenaire
            .denominationPartenaire(UPDATED_DENOMINATION_PARTENAIRE)
            .statauPartenaire(UPDATED_STATAU_PARTENAIRE)
            .autreCategorie(UPDATED_AUTRE_CATEGORIE)
            .typeAppui(UPDATED_TYPE_APPUI)
            .autreNature(UPDATED_AUTRE_NATURE);

        restPartenaireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPartenaire.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPartenaire))
            )
            .andExpect(status().isOk());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
        Partenaire testPartenaire = partenaireList.get(partenaireList.size() - 1);
        assertThat(testPartenaire.getDenominationPartenaire()).isEqualTo(UPDATED_DENOMINATION_PARTENAIRE);
        assertThat(testPartenaire.getStatauPartenaire()).isEqualTo(UPDATED_STATAU_PARTENAIRE);
        assertThat(testPartenaire.getAutreCategorie()).isEqualTo(UPDATED_AUTRE_CATEGORIE);
        assertThat(testPartenaire.getTypeAppui()).isEqualTo(UPDATED_TYPE_APPUI);
        assertThat(testPartenaire.getAutreNature()).isEqualTo(UPDATED_AUTRE_NATURE);
    }

    @Test
    @Transactional
    void patchNonExistingPartenaire() throws Exception {
        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();
        partenaire.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartenaireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partenaire.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partenaire))
            )
            .andExpect(status().isBadRequest());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPartenaire() throws Exception {
        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();
        partenaire.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPartenaireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partenaire))
            )
            .andExpect(status().isBadRequest());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPartenaire() throws Exception {
        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();
        partenaire.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPartenaireMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(partenaire))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePartenaire() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        int databaseSizeBeforeDelete = partenaireRepository.findAll().size();

        // Delete the partenaire
        restPartenaireMockMvc
            .perform(delete(ENTITY_API_URL_ID, partenaire.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
