package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Difficulte;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Difficulte entity.
 */
@Repository
public interface DifficulteRepository extends JpaRepository<Difficulte, Long> {
    default Optional<Difficulte> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Difficulte> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Difficulte> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct difficulte from Difficulte difficulte left join fetch difficulte.lyceesTechniques left join fetch difficulte.proviseur",
        countQuery = "select count(distinct difficulte) from Difficulte difficulte"
    )
    Page<Difficulte> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct difficulte from Difficulte difficulte left join fetch difficulte.lyceesTechniques left join fetch difficulte.proviseur"
    )
    List<Difficulte> findAllWithToOneRelationships();

    @Query(
        "select difficulte from Difficulte difficulte left join fetch difficulte.lyceesTechniques left join fetch difficulte.proviseur where difficulte.id =:id"
    )
    Optional<Difficulte> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select difficulte from  Difficulte  difficulte where  difficulte.proviseur.user.login = ?#{principal.username}")
    Page<Difficulte> findByUserIsCurrentUser(Pageable pageable);
}
