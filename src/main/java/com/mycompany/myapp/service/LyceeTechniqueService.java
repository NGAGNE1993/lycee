package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.LyceeTechnique;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link LyceeTechnique}.
 */
public interface LyceeTechniqueService {
    /**
     * Save a lyceeTechnique.
     *
     * @param lyceeTechnique the entity to save.
     * @return the persisted entity.
     */
    LyceeTechnique save(LyceeTechnique lyceeTechnique);

    /**
     * Updates a lyceeTechnique.
     *
     * @param lyceeTechnique the entity to update.
     * @return the persisted entity.
     */
    LyceeTechnique update(LyceeTechnique lyceeTechnique);

    /**
     * Partially updates a lyceeTechnique.
     *
     * @param lyceeTechnique the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LyceeTechnique> partialUpdate(LyceeTechnique lyceeTechnique);

    /**
     * Get all the lyceeTechniques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LyceeTechnique> findAll(Pageable pageable);

    Page<LyceeTechnique> findByUserIsCurrentUser(Pageable pageable);
    /**
     * Get all the lyceeTechniques with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LyceeTechnique> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" lyceeTechnique.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LyceeTechnique> findOne(Long id);

    /**
     * Delete the "id" lyceeTechnique.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
