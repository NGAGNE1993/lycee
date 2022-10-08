package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.NiveauxCalification;
import com.mycompany.myapp.domain.enumeration.NiveauQualif;
import com.mycompany.myapp.repository.NiveauxCalificationRepository;
import com.mycompany.myapp.service.NiveauxCalificationService;
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
 * Integration tests for the {@link NiveauxCalificationResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class NiveauxCalificationResourceIT {

    private static final String DEFAULT_NIVEAU_CALIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_NIVEAU_CALIFICATION = "BBBBBBBBBB";

    private static final NiveauQualif DEFAULT_TYPE_NIVEAU_CALIFICATION = NiveauQualif.ATTESTE;
    private static final NiveauQualif UPDATED_TYPE_NIVEAU_CALIFICATION = NiveauQualif.CPS;

    private static final String ENTITY_API_URL = "/api/niveaux-califications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NiveauxCalificationRepository niveauxCalificationRepository;

    @Mock
    private NiveauxCalificationRepository niveauxCalificationRepositoryMock;

    @Mock
    private NiveauxCalificationService niveauxCalificationServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNiveauxCalificationMockMvc;

    private NiveauxCalification niveauxCalification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NiveauxCalification createEntity(EntityManager em) {
        NiveauxCalification niveauxCalification = new NiveauxCalification()
            .niveauCalification(DEFAULT_NIVEAU_CALIFICATION)
            .typeNiveauCalification(DEFAULT_TYPE_NIVEAU_CALIFICATION);
        return niveauxCalification;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NiveauxCalification createUpdatedEntity(EntityManager em) {
        NiveauxCalification niveauxCalification = new NiveauxCalification()
            .niveauCalification(UPDATED_NIVEAU_CALIFICATION)
            .typeNiveauCalification(UPDATED_TYPE_NIVEAU_CALIFICATION);
        return niveauxCalification;
    }

    @BeforeEach
    public void initTest() {
        niveauxCalification = createEntity(em);
    }

    @Test
    @Transactional
    void createNiveauxCalification() throws Exception {
        int databaseSizeBeforeCreate = niveauxCalificationRepository.findAll().size();
        // Create the NiveauxCalification
        restNiveauxCalificationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(niveauxCalification))
            )
            .andExpect(status().isCreated());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeCreate + 1);
        NiveauxCalification testNiveauxCalification = niveauxCalificationList.get(niveauxCalificationList.size() - 1);
        assertThat(testNiveauxCalification.getNiveauCalification()).isEqualTo(DEFAULT_NIVEAU_CALIFICATION);
        assertThat(testNiveauxCalification.getTypeNiveauCalification()).isEqualTo(DEFAULT_TYPE_NIVEAU_CALIFICATION);
    }

    @Test
    @Transactional
    void createNiveauxCalificationWithExistingId() throws Exception {
        // Create the NiveauxCalification with an existing ID
        niveauxCalification.setId(1L);

        int databaseSizeBeforeCreate = niveauxCalificationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNiveauxCalificationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(niveauxCalification))
            )
            .andExpect(status().isBadRequest());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNiveauCalificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = niveauxCalificationRepository.findAll().size();
        // set the field null
        niveauxCalification.setNiveauCalification(null);

        // Create the NiveauxCalification, which fails.

        restNiveauxCalificationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(niveauxCalification))
            )
            .andExpect(status().isBadRequest());

        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNiveauxCalifications() throws Exception {
        // Initialize the database
        niveauxCalificationRepository.saveAndFlush(niveauxCalification);

        // Get all the niveauxCalificationList
        restNiveauxCalificationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(niveauxCalification.getId().intValue())))
            .andExpect(jsonPath("$.[*].niveauCalification").value(hasItem(DEFAULT_NIVEAU_CALIFICATION)))
            .andExpect(jsonPath("$.[*].typeNiveauCalification").value(hasItem(DEFAULT_TYPE_NIVEAU_CALIFICATION.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllNiveauxCalificationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(niveauxCalificationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restNiveauxCalificationMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(niveauxCalificationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllNiveauxCalificationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(niveauxCalificationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restNiveauxCalificationMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(niveauxCalificationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getNiveauxCalification() throws Exception {
        // Initialize the database
        niveauxCalificationRepository.saveAndFlush(niveauxCalification);

        // Get the niveauxCalification
        restNiveauxCalificationMockMvc
            .perform(get(ENTITY_API_URL_ID, niveauxCalification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(niveauxCalification.getId().intValue()))
            .andExpect(jsonPath("$.niveauCalification").value(DEFAULT_NIVEAU_CALIFICATION))
            .andExpect(jsonPath("$.typeNiveauCalification").value(DEFAULT_TYPE_NIVEAU_CALIFICATION.toString()));
    }

    @Test
    @Transactional
    void getNonExistingNiveauxCalification() throws Exception {
        // Get the niveauxCalification
        restNiveauxCalificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewNiveauxCalification() throws Exception {
        // Initialize the database
        niveauxCalificationRepository.saveAndFlush(niveauxCalification);

        int databaseSizeBeforeUpdate = niveauxCalificationRepository.findAll().size();

        // Update the niveauxCalification
        NiveauxCalification updatedNiveauxCalification = niveauxCalificationRepository.findById(niveauxCalification.getId()).get();
        // Disconnect from session so that the updates on updatedNiveauxCalification are not directly saved in db
        em.detach(updatedNiveauxCalification);
        updatedNiveauxCalification.niveauCalification(UPDATED_NIVEAU_CALIFICATION).typeNiveauCalification(UPDATED_TYPE_NIVEAU_CALIFICATION);

        restNiveauxCalificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedNiveauxCalification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedNiveauxCalification))
            )
            .andExpect(status().isOk());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeUpdate);
        NiveauxCalification testNiveauxCalification = niveauxCalificationList.get(niveauxCalificationList.size() - 1);
        assertThat(testNiveauxCalification.getNiveauCalification()).isEqualTo(UPDATED_NIVEAU_CALIFICATION);
        assertThat(testNiveauxCalification.getTypeNiveauCalification()).isEqualTo(UPDATED_TYPE_NIVEAU_CALIFICATION);
    }

    @Test
    @Transactional
    void putNonExistingNiveauxCalification() throws Exception {
        int databaseSizeBeforeUpdate = niveauxCalificationRepository.findAll().size();
        niveauxCalification.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNiveauxCalificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, niveauxCalification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(niveauxCalification))
            )
            .andExpect(status().isBadRequest());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNiveauxCalification() throws Exception {
        int databaseSizeBeforeUpdate = niveauxCalificationRepository.findAll().size();
        niveauxCalification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNiveauxCalificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(niveauxCalification))
            )
            .andExpect(status().isBadRequest());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNiveauxCalification() throws Exception {
        int databaseSizeBeforeUpdate = niveauxCalificationRepository.findAll().size();
        niveauxCalification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNiveauxCalificationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(niveauxCalification))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNiveauxCalificationWithPatch() throws Exception {
        // Initialize the database
        niveauxCalificationRepository.saveAndFlush(niveauxCalification);

        int databaseSizeBeforeUpdate = niveauxCalificationRepository.findAll().size();

        // Update the niveauxCalification using partial update
        NiveauxCalification partialUpdatedNiveauxCalification = new NiveauxCalification();
        partialUpdatedNiveauxCalification.setId(niveauxCalification.getId());

        partialUpdatedNiveauxCalification
            .niveauCalification(UPDATED_NIVEAU_CALIFICATION)
            .typeNiveauCalification(UPDATED_TYPE_NIVEAU_CALIFICATION);

        restNiveauxCalificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNiveauxCalification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNiveauxCalification))
            )
            .andExpect(status().isOk());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeUpdate);
        NiveauxCalification testNiveauxCalification = niveauxCalificationList.get(niveauxCalificationList.size() - 1);
        assertThat(testNiveauxCalification.getNiveauCalification()).isEqualTo(UPDATED_NIVEAU_CALIFICATION);
        assertThat(testNiveauxCalification.getTypeNiveauCalification()).isEqualTo(UPDATED_TYPE_NIVEAU_CALIFICATION);
    }

    @Test
    @Transactional
    void fullUpdateNiveauxCalificationWithPatch() throws Exception {
        // Initialize the database
        niveauxCalificationRepository.saveAndFlush(niveauxCalification);

        int databaseSizeBeforeUpdate = niveauxCalificationRepository.findAll().size();

        // Update the niveauxCalification using partial update
        NiveauxCalification partialUpdatedNiveauxCalification = new NiveauxCalification();
        partialUpdatedNiveauxCalification.setId(niveauxCalification.getId());

        partialUpdatedNiveauxCalification
            .niveauCalification(UPDATED_NIVEAU_CALIFICATION)
            .typeNiveauCalification(UPDATED_TYPE_NIVEAU_CALIFICATION);

        restNiveauxCalificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNiveauxCalification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNiveauxCalification))
            )
            .andExpect(status().isOk());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeUpdate);
        NiveauxCalification testNiveauxCalification = niveauxCalificationList.get(niveauxCalificationList.size() - 1);
        assertThat(testNiveauxCalification.getNiveauCalification()).isEqualTo(UPDATED_NIVEAU_CALIFICATION);
        assertThat(testNiveauxCalification.getTypeNiveauCalification()).isEqualTo(UPDATED_TYPE_NIVEAU_CALIFICATION);
    }

    @Test
    @Transactional
    void patchNonExistingNiveauxCalification() throws Exception {
        int databaseSizeBeforeUpdate = niveauxCalificationRepository.findAll().size();
        niveauxCalification.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNiveauxCalificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, niveauxCalification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(niveauxCalification))
            )
            .andExpect(status().isBadRequest());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNiveauxCalification() throws Exception {
        int databaseSizeBeforeUpdate = niveauxCalificationRepository.findAll().size();
        niveauxCalification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNiveauxCalificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(niveauxCalification))
            )
            .andExpect(status().isBadRequest());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNiveauxCalification() throws Exception {
        int databaseSizeBeforeUpdate = niveauxCalificationRepository.findAll().size();
        niveauxCalification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNiveauxCalificationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(niveauxCalification))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NiveauxCalification in the database
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNiveauxCalification() throws Exception {
        // Initialize the database
        niveauxCalificationRepository.saveAndFlush(niveauxCalification);

        int databaseSizeBeforeDelete = niveauxCalificationRepository.findAll().size();

        // Delete the niveauxCalification
        restNiveauxCalificationMockMvc
            .perform(delete(ENTITY_API_URL_ID, niveauxCalification.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NiveauxCalification> niveauxCalificationList = niveauxCalificationRepository.findAll();
        assertThat(niveauxCalificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
