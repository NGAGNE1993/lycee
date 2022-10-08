package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Besoin;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Besoin}.
 */
public interface BesoinService {
    /**
     * Save a besoin.
     *
     * @param besoin the entity to save.
     * @return the persisted entity.
     */
    Besoin save(Besoin besoin);

    /**
     * Updates a besoin.
     *
     * @param besoin the entity to update.
     * @return the persisted entity.
     */
    Besoin update(Besoin besoin);

    /**
     * Partially updates a besoin.
     *
     * @param besoin the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Besoin> partialUpdate(Besoin besoin);

    /**
     * Get all the besoins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Besoin> findAll(Pageable pageable);

    Page<Besoin> findByUserIsCurrentUser(Pageable pageable);

    /**
     * Get all the besoins with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Besoin> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" besoin.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Besoin> findOne(Long id);

    /**
     * Delete the "id" besoin.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
