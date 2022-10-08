package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ComptableFinancier;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ComptableFinancier entity.
 */
@Repository
public interface ComptableFinancierRepository extends JpaRepository<ComptableFinancier, Long> {
    default Optional<ComptableFinancier> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<ComptableFinancier> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<ComptableFinancier> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct comptableFinancier from ComptableFinancier comptableFinancier left join fetch comptableFinancier.user",
        countQuery = "select count(distinct comptableFinancier) from ComptableFinancier comptableFinancier"
    )
    Page<ComptableFinancier> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct comptableFinancier from ComptableFinancier comptableFinancier left join fetch comptableFinancier.user")
    List<ComptableFinancier> findAllWithToOneRelationships();

    @Query(
        "select comptableFinancier from ComptableFinancier comptableFinancier left join fetch comptableFinancier.user where comptableFinancier.id =:id"
    )
    Optional<ComptableFinancier> findOneWithToOneRelationships(@Param("id") Long id);
}
