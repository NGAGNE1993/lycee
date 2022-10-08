package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LyceeTechnique;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LyceeTechnique entity.
 */
@Repository
public interface LyceeTechniqueRepository extends JpaRepository<LyceeTechnique, Long> {
    default Optional<LyceeTechnique> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<LyceeTechnique> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<LyceeTechnique> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct lyceeTechnique from LyceeTechnique lyceeTechnique left join fetch lyceeTechnique.proviseur",
        countQuery = "select count(distinct lyceeTechnique) from LyceeTechnique lyceeTechnique"
    )
    Page<LyceeTechnique> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct lyceeTechnique from LyceeTechnique lyceeTechnique left join fetch lyceeTechnique.proviseur")
    List<LyceeTechnique> findAllWithToOneRelationships();

    @Query("select lyceeTechnique from LyceeTechnique lyceeTechnique left join fetch lyceeTechnique.proviseur where lyceeTechnique.id =:id")
    Optional<LyceeTechnique> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select lyceeTechnique from LyceeTechnique lyceeTechnique where lyceeTechnique.proviseur.user.login = ?#{principal.username}")
    Page<LyceeTechnique> findByUserIsCurrentUser(Pageable pageable);
}