package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.NiveauxEtudes;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link NiveauxEtudes}.
 */
public interface NiveauxEtudesService {
    /**
     * Save a niveauxEtudes.
     *
     * @param niveauxEtudes the entity to save.
     * @return the persisted entity.
     */
    NiveauxEtudes save(NiveauxEtudes niveauxEtudes);

    /**
     * Updates a niveauxEtudes.
     *
     * @param niveauxEtudes the entity to update.
     * @return the persisted entity.
     */
    NiveauxEtudes update(NiveauxEtudes niveauxEtudes);

    /**
     * Partially updates a niveauxEtudes.
     *
     * @param niveauxEtudes the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NiveauxEtudes> partialUpdate(NiveauxEtudes niveauxEtudes);

    /**
     * Get all the niveauxEtudes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NiveauxEtudes> findAll(Pageable pageable);

    Page<NiveauxEtudes> findByUserIsCurrentUser(Pageable pageable);
    /**
     * Get all the niveauxEtudes with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NiveauxEtudes> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" niveauxEtudes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NiveauxEtudes> findOne(Long id);

    /**
     * Delete the "id" niveauxEtudes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
