package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.NiveauxEtudes;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NiveauxEtudes entity.
 */
@Repository
public interface NiveauxEtudesRepository extends JpaRepository<NiveauxEtudes, Long> {
    default Optional<NiveauxEtudes> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<NiveauxEtudes> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<NiveauxEtudes> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct niveauxEtudes from NiveauxEtudes niveauxEtudes left join fetch niveauxEtudes.lyceesTechniques left join fetch niveauxEtudes.directeur left join fetch niveauxEtudes.serie",
        countQuery = "select count(distinct niveauxEtudes) from NiveauxEtudes niveauxEtudes"
    )
    Page<NiveauxEtudes> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct niveauxEtudes from NiveauxEtudes niveauxEtudes left join fetch niveauxEtudes.lyceesTechniques left join fetch niveauxEtudes.directeur left join fetch niveauxEtudes.serie"
    )
    List<NiveauxEtudes> findAllWithToOneRelationships();

    @Query(
        "select niveauxEtudes from NiveauxEtudes niveauxEtudes left join fetch niveauxEtudes.lyceesTechniques left join fetch niveauxEtudes.directeur left join fetch niveauxEtudes.serie where niveauxEtudes.id =:id"
    )
    Optional<NiveauxEtudes> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select niveauxEtudes from NiveauxEtudes niveauxEtudes where niveauxEtudes.directeur.user.login = ?#{principal.username}")
    Page<NiveauxEtudes> findByUserIsCurrentUser(Pageable pageable);
}
