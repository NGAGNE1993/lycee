package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Examen;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Examen}.
 */
public interface ExamenService {
    /**
     * Save a examen.
     *
     * @param examen the entity to save.
     * @return the persisted entity.
     */
    Examen save(Examen examen);

    /**
     * Updates a examen.
     *
     * @param examen the entity to update.
     * @return the persisted entity.
     */
    Examen update(Examen examen);

    /**
     * Partially updates a examen.
     *
     * @param examen the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Examen> partialUpdate(Examen examen);

    /**
     * Get all the examen.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Examen> findAll(Pageable pageable);
    Page<Examen> findByUserIsCurrentUser(Pageable pageable);

    /**
     * Get all the examen with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Examen> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" examen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Examen> findOne(Long id);

    /**
     * Delete the "id" examen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
