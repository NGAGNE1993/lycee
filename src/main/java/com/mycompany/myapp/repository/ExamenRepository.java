package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Examen;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Examen entity.
 */
@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
    default Optional<Examen> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Examen> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Examen> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct examen from Examen examen left join fetch examen.lyceesTechniques left join fetch examen.directeur",
        countQuery = "select count(distinct examen) from Examen examen"
    )
    Page<Examen> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct examen from Examen examen left join fetch examen.lyceesTechniques left join fetch examen.directeur")
    List<Examen> findAllWithToOneRelationships();

    @Query("select examen from Examen examen left join fetch examen.lyceesTechniques left join fetch examen.directeur where examen.id =:id")
    Optional<Examen> findOneWithToOneRelationships(@Param("id") Long id);
    
    @Query("select examen from Examen examen where examen.directeur.user.login = ?#{principal.username}")
    Page<Examen> findByUserIsCurrentUser(Pageable pageable);
}
