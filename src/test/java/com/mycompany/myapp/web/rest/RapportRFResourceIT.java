package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.RapportRF;
import com.mycompany.myapp.domain.enumeration.TypeRapport;
import com.mycompany.myapp.repository.RapportRFRepository;
import com.mycompany.myapp.service.RapportRFService;
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
 * Integration tests for the {@link RapportRFResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class RapportRFResourceIT {

    private static final TypeRapport DEFAULT_TYPE_RAPORT = TypeRapport.FIN;
    private static final TypeRapport UPDATED_TYPE_RAPORT = TypeRapport.RENTREE;

    private static final byte[] DEFAULT_RENTRE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_RENTRE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_RENTRE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_RENTRE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FIN = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FIN = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FIN_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FIN_CONTENT_TYPE = "image/png";

    private static final String ENTITY_API_URL = "/api/rapport-rfs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RapportRFRepository rapportRFRepository;

    @Mock
    private RapportRFRepository rapportRFRepositoryMock;

    @Mock
    private RapportRFService rapportRFServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRapportRFMockMvc;

    private RapportRF rapportRF;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RapportRF createEntity(EntityManager em) {
        RapportRF rapportRF = new RapportRF()
            .typeRaport(DEFAULT_TYPE_RAPORT)
            .rentre(DEFAULT_RENTRE)
            .rentreContentType(DEFAULT_RENTRE_CONTENT_TYPE)
            .fin(DEFAULT_FIN)
            .finContentType(DEFAULT_FIN_CONTENT_TYPE);
        return rapportRF;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RapportRF createUpdatedEntity(EntityManager em) {
        RapportRF rapportRF = new RapportRF()
            .typeRaport(UPDATED_TYPE_RAPORT)
            .rentre(UPDATED_RENTRE)
            .rentreContentType(UPDATED_RENTRE_CONTENT_TYPE)
            .fin(UPDATED_FIN)
            .finContentType(UPDATED_FIN_CONTENT_TYPE);
        return rapportRF;
    }

    @BeforeEach
    public void initTest() {
        rapportRF = createEntity(em);
    }

    @Test
    @Transactional
    void createRapportRF() throws Exception {
        int databaseSizeBeforeCreate = rapportRFRepository.findAll().size();
        // Create the RapportRF
        restRapportRFMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rapportRF)))
            .andExpect(status().isCreated());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeCreate + 1);
        RapportRF testRapportRF = rapportRFList.get(rapportRFList.size() - 1);
        assertThat(testRapportRF.getTypeRaport()).isEqualTo(DEFAULT_TYPE_RAPORT);
        assertThat(testRapportRF.getRentre()).isEqualTo(DEFAULT_RENTRE);
        assertThat(testRapportRF.getRentreContentType()).isEqualTo(DEFAULT_RENTRE_CONTENT_TYPE);
        assertThat(testRapportRF.getFin()).isEqualTo(DEFAULT_FIN);
        assertThat(testRapportRF.getFinContentType()).isEqualTo(DEFAULT_FIN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void createRapportRFWithExistingId() throws Exception {
        // Create the RapportRF with an existing ID
        rapportRF.setId(1L);

        int databaseSizeBeforeCreate = rapportRFRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRapportRFMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rapportRF)))
            .andExpect(status().isBadRequest());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRapportRFS() throws Exception {
        // Initialize the database
        rapportRFRepository.saveAndFlush(rapportRF);

        // Get all the rapportRFList
        restRapportRFMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rapportRF.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeRaport").value(hasItem(DEFAULT_TYPE_RAPORT.toString())))
            .andExpect(jsonPath("$.[*].rentreContentType").value(hasItem(DEFAULT_RENTRE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].rentre").value(hasItem(Base64Utils.encodeToString(DEFAULT_RENTRE))))
            .andExpect(jsonPath("$.[*].finContentType").value(hasItem(DEFAULT_FIN_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fin").value(hasItem(Base64Utils.encodeToString(DEFAULT_FIN))));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRapportRFSWithEagerRelationshipsIsEnabled() throws Exception {
        when(rapportRFServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRapportRFMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(rapportRFServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRapportRFSWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(rapportRFServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRapportRFMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(rapportRFServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getRapportRF() throws Exception {
        // Initialize the database
        rapportRFRepository.saveAndFlush(rapportRF);

        // Get the rapportRF
        restRapportRFMockMvc
            .perform(get(ENTITY_API_URL_ID, rapportRF.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rapportRF.getId().intValue()))
            .andExpect(jsonPath("$.typeRaport").value(DEFAULT_TYPE_RAPORT.toString()))
            .andExpect(jsonPath("$.rentreContentType").value(DEFAULT_RENTRE_CONTENT_TYPE))
            .andExpect(jsonPath("$.rentre").value(Base64Utils.encodeToString(DEFAULT_RENTRE)))
            .andExpect(jsonPath("$.finContentType").value(DEFAULT_FIN_CONTENT_TYPE))
            .andExpect(jsonPath("$.fin").value(Base64Utils.encodeToString(DEFAULT_FIN)));
    }

    @Test
    @Transactional
    void getNonExistingRapportRF() throws Exception {
        // Get the rapportRF
        restRapportRFMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRapportRF() throws Exception {
        // Initialize the database
        rapportRFRepository.saveAndFlush(rapportRF);

        int databaseSizeBeforeUpdate = rapportRFRepository.findAll().size();

        // Update the rapportRF
        RapportRF updatedRapportRF = rapportRFRepository.findById(rapportRF.getId()).get();
        // Disconnect from session so that the updates on updatedRapportRF are not directly saved in db
        em.detach(updatedRapportRF);
        updatedRapportRF
            .typeRaport(UPDATED_TYPE_RAPORT)
            .rentre(UPDATED_RENTRE)
            .rentreContentType(UPDATED_RENTRE_CONTENT_TYPE)
            .fin(UPDATED_FIN)
            .finContentType(UPDATED_FIN_CONTENT_TYPE);

        restRapportRFMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRapportRF.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRapportRF))
            )
            .andExpect(status().isOk());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeUpdate);
        RapportRF testRapportRF = rapportRFList.get(rapportRFList.size() - 1);
        assertThat(testRapportRF.getTypeRaport()).isEqualTo(UPDATED_TYPE_RAPORT);
        assertThat(testRapportRF.getRentre()).isEqualTo(UPDATED_RENTRE);
        assertThat(testRapportRF.getRentreContentType()).isEqualTo(UPDATED_RENTRE_CONTENT_TYPE);
        assertThat(testRapportRF.getFin()).isEqualTo(UPDATED_FIN);
        assertThat(testRapportRF.getFinContentType()).isEqualTo(UPDATED_FIN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingRapportRF() throws Exception {
        int databaseSizeBeforeUpdate = rapportRFRepository.findAll().size();
        rapportRF.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRapportRFMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rapportRF.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rapportRF))
            )
            .andExpect(status().isBadRequest());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRapportRF() throws Exception {
        int databaseSizeBeforeUpdate = rapportRFRepository.findAll().size();
        rapportRF.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRapportRFMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rapportRF))
            )
            .andExpect(status().isBadRequest());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRapportRF() throws Exception {
        int databaseSizeBeforeUpdate = rapportRFRepository.findAll().size();
        rapportRF.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRapportRFMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rapportRF)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRapportRFWithPatch() throws Exception {
        // Initialize the database
        rapportRFRepository.saveAndFlush(rapportRF);

        int databaseSizeBeforeUpdate = rapportRFRepository.findAll().size();

        // Update the rapportRF using partial update
        RapportRF partialUpdatedRapportRF = new RapportRF();
        partialUpdatedRapportRF.setId(rapportRF.getId());

        partialUpdatedRapportRF.rentre(UPDATED_RENTRE).rentreContentType(UPDATED_RENTRE_CONTENT_TYPE);

        restRapportRFMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRapportRF.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRapportRF))
            )
            .andExpect(status().isOk());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeUpdate);
        RapportRF testRapportRF = rapportRFList.get(rapportRFList.size() - 1);
        assertThat(testRapportRF.getTypeRaport()).isEqualTo(DEFAULT_TYPE_RAPORT);
        assertThat(testRapportRF.getRentre()).isEqualTo(UPDATED_RENTRE);
        assertThat(testRapportRF.getRentreContentType()).isEqualTo(UPDATED_RENTRE_CONTENT_TYPE);
        assertThat(testRapportRF.getFin()).isEqualTo(DEFAULT_FIN);
        assertThat(testRapportRF.getFinContentType()).isEqualTo(DEFAULT_FIN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateRapportRFWithPatch() throws Exception {
        // Initialize the database
        rapportRFRepository.saveAndFlush(rapportRF);

        int databaseSizeBeforeUpdate = rapportRFRepository.findAll().size();

        // Update the rapportRF using partial update
        RapportRF partialUpdatedRapportRF = new RapportRF();
        partialUpdatedRapportRF.setId(rapportRF.getId());

        partialUpdatedRapportRF
            .typeRaport(UPDATED_TYPE_RAPORT)
            .rentre(UPDATED_RENTRE)
            .rentreContentType(UPDATED_RENTRE_CONTENT_TYPE)
            .fin(UPDATED_FIN)
            .finContentType(UPDATED_FIN_CONTENT_TYPE);

        restRapportRFMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRapportRF.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRapportRF))
            )
            .andExpect(status().isOk());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeUpdate);
        RapportRF testRapportRF = rapportRFList.get(rapportRFList.size() - 1);
        assertThat(testRapportRF.getTypeRaport()).isEqualTo(UPDATED_TYPE_RAPORT);
        assertThat(testRapportRF.getRentre()).isEqualTo(UPDATED_RENTRE);
        assertThat(testRapportRF.getRentreContentType()).isEqualTo(UPDATED_RENTRE_CONTENT_TYPE);
        assertThat(testRapportRF.getFin()).isEqualTo(UPDATED_FIN);
        assertThat(testRapportRF.getFinContentType()).isEqualTo(UPDATED_FIN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingRapportRF() throws Exception {
        int databaseSizeBeforeUpdate = rapportRFRepository.findAll().size();
        rapportRF.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRapportRFMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rapportRF.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rapportRF))
            )
            .andExpect(status().isBadRequest());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRapportRF() throws Exception {
        int databaseSizeBeforeUpdate = rapportRFRepository.findAll().size();
        rapportRF.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRapportRFMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rapportRF))
            )
            .andExpect(status().isBadRequest());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRapportRF() throws Exception {
        int databaseSizeBeforeUpdate = rapportRFRepository.findAll().size();
        rapportRF.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRapportRFMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(rapportRF))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RapportRF in the database
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRapportRF() throws Exception {
        // Initialize the database
        rapportRFRepository.saveAndFlush(rapportRF);

        int databaseSizeBeforeDelete = rapportRFRepository.findAll().size();

        // Delete the rapportRF
        restRapportRFMockMvc
            .perform(delete(ENTITY_API_URL_ID, rapportRF.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RapportRF> rapportRFList = rapportRFRepository.findAll();
        assertThat(rapportRFList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
