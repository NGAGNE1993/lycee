package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Apprenant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data SQL repository for the Apprenant entity.
 */
@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
    default Optional<Apprenant> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Apprenant> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Apprenant> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct apprenant from Apprenant apprenant left join fetch apprenant.serie left join fetch apprenant.filiere left join fetch apprenant.niveauxEtudes left join fetch apprenant.niveauxCalification left join fetch apprenant.lyceesTechniques left join fetch apprenant.directeur",
        countQuery = "select count(distinct apprenant) from Apprenant apprenant"
    )
    Page<Apprenant> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct apprenant from Apprenant apprenant left join fetch apprenant.serie left join fetch apprenant.filiere left join fetch apprenant.niveauxEtudes left join fetch apprenant.niveauxCalification left join fetch apprenant.lyceesTechniques left join fetch apprenant.directeur"
    )
    List<Apprenant> findAllWithToOneRelationships();

    @Query(
        "select apprenant from Apprenant apprenant left join fetch apprenant.serie left join fetch apprenant.filiere left join fetch apprenant.niveauxEtudes left join fetch apprenant.niveauxCalification left join fetch apprenant.lyceesTechniques left join fetch apprenant.directeur where apprenant.id =:id"
    )
    Optional<Apprenant> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select apprenant from Apprenant  apprenant where apprenant.directeur.user.login = ?#{principal.username}")
    Page<Apprenant> findByUserIsCurrentUser(Pageable pageable);
}