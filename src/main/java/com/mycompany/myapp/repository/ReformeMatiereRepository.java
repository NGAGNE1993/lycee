package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ReformeMatiere;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ReformeMatiere entity.
 */
@Repository
public interface ReformeMatiereRepository extends JpaRepository<ReformeMatiere, Long> {
    default Optional<ReformeMatiere> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<ReformeMatiere> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<ReformeMatiere> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct reformeMatiere from ReformeMatiere reformeMatiere left join fetch reformeMatiere.lyceesTechniques left join fetch reformeMatiere.comptableMatiere",
        countQuery = "select count(distinct reformeMatiere) from ReformeMatiere reformeMatiere"
    )
    Page<ReformeMatiere> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct reformeMatiere from ReformeMatiere reformeMatiere left join fetch reformeMatiere.lyceesTechniques left join fetch reformeMatiere.comptableMatiere"
    )
    List<ReformeMatiere> findAllWithToOneRelationships();

    @Query(
        "select reformeMatiere from ReformeMatiere reformeMatiere left join fetch reformeMatiere.lyceesTechniques left join fetch reformeMatiere.comptableMatiere where reformeMatiere.id =:id"
    )
    Optional<ReformeMatiere> findOneWithToOneRelationships(@Param("id") Long id);

    @Query(
        "select reformeMatiere from ReformeMatiere reformeMatiere where reformeMatiere.comptableMatiere.user.login = ?#{principal.username}"
    )
    Page<ReformeMatiere> findByUserIsCurrentUser(Pageable pageable);
}
