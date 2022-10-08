package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Besoin;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Besoin entity.
 */
@Repository
public interface BesoinRepository extends JpaRepository<Besoin, Long> {
    default Optional<Besoin> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Besoin> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Besoin> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct besoin from Besoin besoin left join fetch besoin.lyceesTechniques left join fetch besoin.proviseur",
        countQuery = "select count(distinct besoin) from Besoin besoin"
    )
    Page<Besoin> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct besoin from Besoin besoin left join fetch besoin.lyceesTechniques left join fetch besoin.proviseur")
    List<Besoin> findAllWithToOneRelationships();

    @Query("select besoin from Besoin besoin left join fetch besoin.lyceesTechniques left join fetch besoin.proviseur where besoin.id =:id")
    Optional<Besoin> findOneWithToOneRelationships(@Param("id") Long id);

    
    @Query("select besoin from Besoin besoin where besoin.proviseur.user.login = ?#{principal.username}")
    Page<Besoin> findByUserIsCurrentUser(Pageable pageable);
}
