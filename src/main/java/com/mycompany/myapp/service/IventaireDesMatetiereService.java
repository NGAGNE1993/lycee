package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.IventaireDesMatetiere;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link IventaireDesMatetiere}.
 */
public interface IventaireDesMatetiereService {
    /**
     * Save a iventaireDesMatetiere.
     *
     * @param iventaireDesMatetiere the entity to save.
     * @return the persisted entity.
     */
    IventaireDesMatetiere save(IventaireDesMatetiere iventaireDesMatetiere);

    /**
     * Updates a iventaireDesMatetiere.
     *
     * @param iventaireDesMatetiere the entity to update.
     * @return the persisted entity.
     */
    IventaireDesMatetiere update(IventaireDesMatetiere iventaireDesMatetiere);

    /**
     * Partially updates a iventaireDesMatetiere.
     *
     * @param iventaireDesMatetiere the entity to update partially.
     * @return the persisted entity.
     */
    Optional<IventaireDesMatetiere> partialUpdate(IventaireDesMatetiere iventaireDesMatetiere);

    /**
     * Get all the iventaireDesMatetieres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IventaireDesMatetiere> findAll(Pageable pageable);

    Page<IventaireDesMatetiere> findByUserIsCurrentUser(Pageable pageable);
    /**
     * Get all the iventaireDesMatetieres with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IventaireDesMatetiere> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" iventaireDesMatetiere.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IventaireDesMatetiere> findOne(Long id);

    /**
     * Delete the "id" iventaireDesMatetiere.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
