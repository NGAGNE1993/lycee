package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OrganeGestion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OrganeGestion entity.
 */
@Repository
public interface OrganeGestionRepository extends JpaRepository<OrganeGestion, Long> {
    default Optional<OrganeGestion> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<OrganeGestion> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<OrganeGestion> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct organeGestion from OrganeGestion organeGestion left join fetch organeGestion.lyceesTechniques left join fetch organeGestion.proviseur",
        countQuery = "select count(distinct organeGestion) from OrganeGestion organeGestion"
    )
    Page<OrganeGestion> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct organeGestion from OrganeGestion organeGestion left join fetch organeGestion.lyceesTechniques left join fetch organeGestion.proviseur"
    )
    List<OrganeGestion> findAllWithToOneRelationships();

    @Query(
        "select organeGestion from OrganeGestion organeGestion left join fetch organeGestion.lyceesTechniques left join fetch organeGestion.proviseur where organeGestion.id =:id"
    )
    Optional<OrganeGestion> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select organeGestion from OrganeGestion organeGestion where organeGestion.proviseur.user.login = ?#{principal.username}")
    Page<OrganeGestion> findByUserIsCurrentUser(Pageable pageable);
}