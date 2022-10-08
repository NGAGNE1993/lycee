package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Salle;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Salle entity.
 */
@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
    default Optional<Salle> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Salle> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Salle> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct salle from Salle salle left join fetch salle.lyceesTechniques left join fetch salle.directeur",
        countQuery = "select count(distinct salle) from Salle salle"
    )
    Page<Salle> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct salle from Salle salle left join fetch salle.lyceesTechniques left join fetch salle.directeur")
    List<Salle> findAllWithToOneRelationships();

    @Query("select salle from Salle salle left join fetch salle.lyceesTechniques left join fetch salle.directeur where salle.id =:id")
    Optional<Salle> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select salle from Salle salle where salle.directeur.user.login = ?#{principal.username}")
    Page<Salle> findByUserIsCurrentUser(Pageable pageable);
}
