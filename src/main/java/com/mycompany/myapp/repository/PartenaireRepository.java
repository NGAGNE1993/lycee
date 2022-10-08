package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Partenaire;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Partenaire entity.
 */
@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
    default Optional<Partenaire> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Partenaire> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Partenaire> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct partenaire from Partenaire partenaire left join fetch partenaire.lyceesTechniques left join fetch partenaire.proviseur",
        countQuery = "select count(distinct partenaire) from Partenaire partenaire"
    )
    Page<Partenaire> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct partenaire from Partenaire partenaire left join fetch partenaire.lyceesTechniques left join fetch partenaire.proviseur"
    )
    List<Partenaire> findAllWithToOneRelationships();

    @Query(
        "select partenaire from Partenaire partenaire left join fetch partenaire.lyceesTechniques left join fetch partenaire.proviseur where partenaire.id =:id"
    )
    Optional<Partenaire> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select partenaire from Partenaire partenaire where partenaire.proviseur.user.login = ?#{principal.username}")
    Page<Partenaire> findByUserIsCurrentUser(Pageable pageable);
}
