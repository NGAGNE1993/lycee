package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.RapportRF;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RapportRF entity.
 */
@Repository
public interface RapportRFRepository extends JpaRepository<RapportRF, Long> {
    default Optional<RapportRF> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<RapportRF> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<RapportRF> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct rapportRF from RapportRF rapportRF left join fetch rapportRF.lyceesTechniques left join fetch rapportRF.proviseur",
        countQuery = "select count(distinct rapportRF) from RapportRF rapportRF"
    )
    Page<RapportRF> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct rapportRF from RapportRF rapportRF left join fetch rapportRF.lyceesTechniques left join fetch rapportRF.proviseur"
    )
    List<RapportRF> findAllWithToOneRelationships();

    @Query(
        "select rapportRF from RapportRF rapportRF left join fetch rapportRF.lyceesTechniques left join fetch rapportRF.proviseur where rapportRF.id =:id"
    )
    Optional<RapportRF> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select rapportRF from RapportRF rapportRF where rapportRF.proviseur.user.login = ?#{principal.username}")
    Page<RapportRF> findByUserIsCurrentUser(Pageable pageable);
}