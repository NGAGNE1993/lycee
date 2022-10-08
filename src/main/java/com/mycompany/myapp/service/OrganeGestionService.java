package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.OrganeGestion;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link OrganeGestion}.
 */
public interface OrganeGestionService {
    /**
     * Save a organeGestion.
     *
     * @param organeGestion the entity to save.
     * @return the persisted entity.
     */
    OrganeGestion save(OrganeGestion organeGestion);

    /**
     * Updates a organeGestion.
     *
     * @param organeGestion the entity to update.
     * @return the persisted entity.
     */
    OrganeGestion update(OrganeGestion organeGestion);

    /**
     * Partially updates a organeGestion.
     *
     * @param organeGestion the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OrganeGestion> partialUpdate(OrganeGestion organeGestion);

    /**
     * Get all the organeGestions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrganeGestion> findAll(Pageable pageable);

    Page<OrganeGestion> findByUserIsCurrentUser(Pageable pageable);
    /**
     * Get all the organeGestions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrganeGestion> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" organeGestion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrganeGestion> findOne(Long id);

    /**
     * Delete the "id" organeGestion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
