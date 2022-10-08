package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.PersonnelAdministratif;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link PersonnelAdministratif}.
 */
public interface PersonnelAdministratifService {
    /**
     * Save a personnelAdministratif.
     *
     * @param personnelAdministratif the entity to save.
     * @return the persisted entity.
     */
    PersonnelAdministratif save(PersonnelAdministratif personnelAdministratif);

    /**
     * Updates a personnelAdministratif.
     *
     * @param personnelAdministratif the entity to update.
     * @return the persisted entity.
     */
    PersonnelAdministratif update(PersonnelAdministratif personnelAdministratif);

    /**
     * Partially updates a personnelAdministratif.
     *
     * @param personnelAdministratif the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PersonnelAdministratif> partialUpdate(PersonnelAdministratif personnelAdministratif);

    /**
     * Get all the personnelAdministratifs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PersonnelAdministratif> findAll(Pageable pageable);

    Page<PersonnelAdministratif> findByUserIsCurrentUser(Pageable pageable);
    /**
     * Get all the personnelAdministratifs with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PersonnelAdministratif> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" personnelAdministratif.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersonnelAdministratif> findOne(Long id);

    /**
     * Delete the "id" personnelAdministratif.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
