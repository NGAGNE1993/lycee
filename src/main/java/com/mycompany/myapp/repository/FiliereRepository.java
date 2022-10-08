package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Filiere;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Filiere entity.
 */
@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    default Optional<Filiere> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Filiere> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Filiere> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct filiere from Filiere filiere left join fetch filiere.lyceesTechniques left join fetch filiere.directeur",
        countQuery = "select count(distinct filiere) from Filiere filiere"
    )
    Page<Filiere> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct filiere from Filiere filiere left join fetch filiere.lyceesTechniques left join fetch filiere.directeur")
    List<Filiere> findAllWithToOneRelationships();

    @Query(
        "select filiere from Filiere filiere left join fetch filiere.lyceesTechniques left join fetch filiere.directeur where filiere.id =:id"
    )
    Optional<Filiere> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select filiere from Filiere filiere where  filiere.directeur.user.login = ?#{principal.username}")
    Page<Filiere> findByUserIsCurrentUser(Pageable pageable);
}