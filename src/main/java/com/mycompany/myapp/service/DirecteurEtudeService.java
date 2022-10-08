package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.DirecteurEtude;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link DirecteurEtude}.
 */
public interface DirecteurEtudeService {
    /**
     * Save a directeurEtude.
     *
     * @param directeurEtude the entity to save.
     * @return the persisted entity.
     */
    DirecteurEtude save(DirecteurEtude directeurEtude);

    /**
     * Updates a directeurEtude.
     *
     * @param directeurEtude the entity to update.
     * @return the persisted entity.
     */
    DirecteurEtude update(DirecteurEtude directeurEtude);

    /**
     * Partially updates a directeurEtude.
     *
     * @param directeurEtude the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DirecteurEtude> partialUpdate(DirecteurEtude directeurEtude);

    /**
     * Get all the directeurEtudes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DirecteurEtude> findAll(Pageable pageable);

    /**
     * Get all the directeurEtudes with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DirecteurEtude> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" directeurEtude.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DirecteurEtude> findOne(Long id);

    /**
     * Delete the "id" directeurEtude.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
