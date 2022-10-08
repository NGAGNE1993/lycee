package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.NiveauxCalification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NiveauxCalification entity.
 */
@Repository
public interface NiveauxCalificationRepository extends JpaRepository<NiveauxCalification, Long> {
    default Optional<NiveauxCalification> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<NiveauxCalification> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<NiveauxCalification> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct niveauxCalification from NiveauxCalification niveauxCalification left join fetch niveauxCalification.lyceesTechniques left join fetch niveauxCalification.directeur left join fetch niveauxCalification.filiere",
        countQuery = "select count(distinct niveauxCalification) from NiveauxCalification niveauxCalification"
    )
    Page<NiveauxCalification> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct niveauxCalification from NiveauxCalification niveauxCalification left join fetch niveauxCalification.lyceesTechniques left join fetch niveauxCalification.directeur left join fetch niveauxCalification.filiere"
    )
    List<NiveauxCalification> findAllWithToOneRelationships();

    @Query(
        "select niveauxCalification from NiveauxCalification niveauxCalification left join fetch niveauxCalification.lyceesTechniques left join fetch niveauxCalification.directeur left join fetch niveauxCalification.filiere where niveauxCalification.id =:id"
    )
    Optional<NiveauxCalification> findOneWithToOneRelationships(@Param("id") Long id);

    @Query(
        "select niveauxCalification from NiveauxCalification niveauxCalification where niveauxCalification.directeur.user.login = ?#{principal.username}"
    )
    Page<NiveauxCalification> findByUserIsCurrentUser(Pageable pageable);
}