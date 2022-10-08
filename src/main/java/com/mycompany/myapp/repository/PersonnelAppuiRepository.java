package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PersonnelAppui;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PersonnelAppui entity.
 */
@Repository
public interface PersonnelAppuiRepository extends JpaRepository<PersonnelAppui, Long> {
    default Optional<PersonnelAppui> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<PersonnelAppui> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<PersonnelAppui> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct personnelAppui from PersonnelAppui personnelAppui left join fetch personnelAppui.lyceesTechniques left join fetch personnelAppui.directeur",
        countQuery = "select count(distinct personnelAppui) from PersonnelAppui personnelAppui"
    )
    Page<PersonnelAppui> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct personnelAppui from PersonnelAppui personnelAppui left join fetch personnelAppui.lyceesTechniques left join fetch personnelAppui.directeur"
    )
    List<PersonnelAppui> findAllWithToOneRelationships();

    @Query(
        "select personnelAppui from PersonnelAppui personnelAppui left join fetch personnelAppui.lyceesTechniques left join fetch personnelAppui.directeur where personnelAppui.id =:id"
    )
    Optional<PersonnelAppui> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select personnelAppui from PersonnelAppui personnelAppui where personnelAppui.directeur.user.login = ?#{principal.username}")
    Page<PersonnelAppui> findByUserIsCurrentUser(Pageable pageable);
}
