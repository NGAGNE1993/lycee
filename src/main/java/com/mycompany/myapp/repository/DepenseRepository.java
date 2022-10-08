package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Depense;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Depense entity.
 */
@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {
    default Optional<Depense> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Depense> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Depense> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct depense from Depense depense left join fetch depense.lyceesTechniques left join fetch depense.comptableFinancier",
        countQuery = "select count(distinct depense) from Depense depense"
    )
    Page<Depense> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct depense from Depense depense left join fetch depense.lyceesTechniques left join fetch depense.comptableFinancier"
    )
    List<Depense> findAllWithToOneRelationships();

    @Query(
        "select depense from Depense depense left join fetch depense.lyceesTechniques left join fetch depense.comptableFinancier where depense.id =:id"
    )
    Optional<Depense> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select  depense from Depense  depense where  depense.comptableFinancier.user.login = ?#{principal.username}")
    Page<Depense> findByUserIsCurrentUser(Pageable pageable);
}