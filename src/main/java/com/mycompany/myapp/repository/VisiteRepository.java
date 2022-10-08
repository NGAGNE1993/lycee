package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Visite;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Visite entity.
 */
@Repository
public interface VisiteRepository extends JpaRepository<Visite, Long> {
    default Optional<Visite> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Visite> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Visite> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct visite from Visite visite left join fetch visite.lyceesTechniques left join fetch visite.proviseur",
        countQuery = "select count(distinct visite) from Visite visite"
    )
    Page<Visite> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct visite from Visite visite left join fetch visite.lyceesTechniques left join fetch visite.proviseur")
    List<Visite> findAllWithToOneRelationships();

    @Query("select visite from Visite visite left join fetch visite.lyceesTechniques left join fetch visite.proviseur where visite.id =:id")
    Optional<Visite> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select visite from Visite visite  where visite.proviseur.user.login = ?#{principal.username}")
    Page<Visite> findByUserIsCurrentUser(Pageable pageable);
}
