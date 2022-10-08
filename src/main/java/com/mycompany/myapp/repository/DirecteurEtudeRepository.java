package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DirecteurEtude;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DirecteurEtude entity.
 */
@Repository
public interface DirecteurEtudeRepository extends JpaRepository<DirecteurEtude, Long> {
    default Optional<DirecteurEtude> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<DirecteurEtude> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<DirecteurEtude> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct directeurEtude from DirecteurEtude directeurEtude left join fetch directeurEtude.user",
        countQuery = "select count(distinct directeurEtude) from DirecteurEtude directeurEtude"
    )
    Page<DirecteurEtude> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct directeurEtude from DirecteurEtude directeurEtude left join fetch directeurEtude.user")
    List<DirecteurEtude> findAllWithToOneRelationships();

    @Query("select directeurEtude from DirecteurEtude directeurEtude left join fetch directeurEtude.user where directeurEtude.id =:id")
    Optional<DirecteurEtude> findOneWithToOneRelationships(@Param("id") Long id);
}
