package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.RapportRF;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RapportRF}.
 */
public interface RapportRFService {
    /**
     * Save a rapportRF.
     *
     * @param rapportRF the entity to save.
     * @return the persisted entity.
     */
    RapportRF save(RapportRF rapportRF);

    /**
     * Updates a rapportRF.
     *
     * @param rapportRF the entity to update.
     * @return the persisted entity.
     */
    RapportRF update(RapportRF rapportRF);

    /**
     * Partially updates a rapportRF.
     *
     * @param rapportRF the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RapportRF> partialUpdate(RapportRF rapportRF);

    /**
     * Get all the rapportRFS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RapportRF> findAll(Pageable pageable);

    Page<RapportRF> findByUserIsCurrentUser(Pageable pageable);
    /**
     * Get all the rapportRFS with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RapportRF> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" rapportRF.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RapportRF> findOne(Long id);

    /**
     * Delete the "id" rapportRF.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
