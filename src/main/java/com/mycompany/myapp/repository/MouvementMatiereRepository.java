package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MouvementMatiere;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MouvementMatiere entity.
 */
@Repository
public interface MouvementMatiereRepository extends JpaRepository<MouvementMatiere, Long> {
    default Optional<MouvementMatiere> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<MouvementMatiere> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<MouvementMatiere> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct mouvementMatiere from MouvementMatiere mouvementMatiere left join fetch mouvementMatiere.lyceesTechniques left join fetch mouvementMatiere.comptableMatiere",
        countQuery = "select count(distinct mouvementMatiere) from MouvementMatiere mouvementMatiere"
    )
    Page<MouvementMatiere> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct mouvementMatiere from MouvementMatiere mouvementMatiere left join fetch mouvementMatiere.lyceesTechniques left join fetch mouvementMatiere.comptableMatiere"
    )
    List<MouvementMatiere> findAllWithToOneRelationships();

    @Query(
        "select mouvementMatiere from MouvementMatiere mouvementMatiere left join fetch mouvementMatiere.lyceesTechniques left join fetch mouvementMatiere.comptableMatiere where mouvementMatiere.id =:id"
    )
    Optional<MouvementMatiere> findOneWithToOneRelationships(@Param("id") Long id);

    @Query(
        "select mouvementMatiere from MouvementMatiere mouvementMatiere where mouvementMatiere.comptableMatiere.user.login = ?#{principal.username}"
    )
    Page<MouvementMatiere> findByUserIsCurrentUser(Pageable pageable);
}