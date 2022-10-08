package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.NiveauxCalification;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link NiveauxCalification}.
 */
public interface NiveauxCalificationService {
    /**
     * Save a niveauxCalification.
     *
     * @param niveauxCalification the entity to save.
     * @return the persisted entity.
     */
    NiveauxCalification save(NiveauxCalification niveauxCalification);

    /**
     * Updates a niveauxCalification.
     *
     * @param niveauxCalification the entity to update.
     * @return the persisted entity.
     */
    NiveauxCalification update(NiveauxCalification niveauxCalification);

    /**
     * Partially updates a niveauxCalification.
     *
     * @param niveauxCalification the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NiveauxCalification> partialUpdate(NiveauxCalification niveauxCalification);

    /**
     * Get all the niveauxCalifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NiveauxCalification> findAll(Pageable pageable);

    Page<NiveauxCalification> findByUserIsCurrentUser(Pageable pageable);
    /**
     * Get all the niveauxCalifications with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NiveauxCalification> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" niveauxCalification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NiveauxCalification> findOne(Long id);

    /**
     * Delete the "id" niveauxCalification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
