package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Visite;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Visite}.
 */
public interface VisiteService {
    /**
     * Save a visite.
     *
     * @param visite the entity to save.
     * @return the persisted entity.
     */
    Visite save(Visite visite);

    /**
     * Updates a visite.
     *
     * @param visite the entity to update.
     * @return the persisted entity.
     */
    Visite update(Visite visite);

    /**
     * Partially updates a visite.
     *
     * @param visite the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Visite> partialUpdate(Visite visite);

    /**
     * Get all the visites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Visite> findAll(Pageable pageable);

    Page<Visite> findByUserIsCurrentUser(Pageable pageable);

    /**
     * Get all the visites with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Visite> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" visite.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Visite> findOne(Long id);

    /**
     * Delete the "id" visite.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
