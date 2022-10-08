package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Serie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Serie entity.
 */
@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    default Optional<Serie> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Serie> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Serie> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct serie from Serie serie left join fetch serie.lyceesTechniques left join fetch serie.directeur",
        countQuery = "select count(distinct serie) from Serie serie"
    )
    Page<Serie> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct serie from Serie serie left join fetch serie.lyceesTechniques left join fetch serie.directeur")
    List<Serie> findAllWithToOneRelationships();

    @Query("select serie from Serie serie left join fetch serie.lyceesTechniques left join fetch serie.directeur where serie.id =:id")
    Optional<Serie> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select serie from Serie serie where serie.directeur.user.login = ?#{principal.username}")
    Page<Serie> findByUserIsCurrentUser(Pageable pageable);
}