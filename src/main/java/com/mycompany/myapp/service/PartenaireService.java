package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Partenaire;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Partenaire}.
 */
public interface PartenaireService {
    /**
     * Save a partenaire.
     *
     * @param partenaire the entity to save.
     * @return the persisted entity.
     */
    Partenaire save(Partenaire partenaire);

    /**
     * Updates a partenaire.
     *
     * @param partenaire the entity to update.
     * @return the persisted entity.
     */
    Partenaire update(Partenaire partenaire);

    /**
     * Partially updates a partenaire.
     *
     * @param partenaire the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Partenaire> partialUpdate(Partenaire partenaire);

    /**
     * Get all the partenaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Partenaire> findAll(Pageable pageable);

    Page<Partenaire> findByUserIsCurrentUser(Pageable pageable);
    /**
     * Get all the partenaires with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Partenaire> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" partenaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Partenaire> findOne(Long id);

    /**
     * Delete the "id" partenaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
