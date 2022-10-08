package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PersonnelAdministratif;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PersonnelAdministratif entity.
 */
@Repository
public interface PersonnelAdministratifRepository extends JpaRepository<PersonnelAdministratif, Long> {
    default Optional<PersonnelAdministratif> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<PersonnelAdministratif> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<PersonnelAdministratif> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct personnelAdministratif from PersonnelAdministratif personnelAdministratif left join fetch personnelAdministratif.lyceesTechniques left join fetch personnelAdministratif.proviseur",
        countQuery = "select count(distinct personnelAdministratif) from PersonnelAdministratif personnelAdministratif"
    )
    Page<PersonnelAdministratif> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct personnelAdministratif from PersonnelAdministratif personnelAdministratif left join fetch personnelAdministratif.lyceesTechniques left join fetch personnelAdministratif.proviseur"
    )
    List<PersonnelAdministratif> findAllWithToOneRelationships();

    @Query(
        "select personnelAdministratif from PersonnelAdministratif personnelAdministratif left join fetch personnelAdministratif.lyceesTechniques left join fetch personnelAdministratif.proviseur where personnelAdministratif.id =:id"
    )
    Optional<PersonnelAdministratif> findOneWithToOneRelationships(@Param("id") Long id);

    @Query(
        "select personnelAdministratif from PersonnelAdministratif personnelAdministratif where personnelAdministratif.proviseur.user.login = ?#{principal.username}"
    )
    Page<PersonnelAdministratif> findByUserIsCurrentUser(Pageable pageable);
}
