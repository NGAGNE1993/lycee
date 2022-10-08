package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.IventaireDesMatetiere;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the IventaireDesMatetiere entity.
 */
@Repository
public interface IventaireDesMatetiereRepository extends JpaRepository<IventaireDesMatetiere, Long> {
    default Optional<IventaireDesMatetiere> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<IventaireDesMatetiere> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<IventaireDesMatetiere> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct iventaireDesMatetiere from IventaireDesMatetiere iventaireDesMatetiere left join fetch iventaireDesMatetiere.lyceesTechniques left join fetch iventaireDesMatetiere.comptableMatiere",
        countQuery = "select count(distinct iventaireDesMatetiere) from IventaireDesMatetiere iventaireDesMatetiere"
    )
    Page<IventaireDesMatetiere> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct iventaireDesMatetiere from IventaireDesMatetiere iventaireDesMatetiere left join fetch iventaireDesMatetiere.lyceesTechniques left join fetch iventaireDesMatetiere.comptableMatiere"
    )
    List<IventaireDesMatetiere> findAllWithToOneRelationships();

    @Query(
        "select iventaireDesMatetiere from IventaireDesMatetiere iventaireDesMatetiere left join fetch iventaireDesMatetiere.lyceesTechniques left join fetch iventaireDesMatetiere.comptableMatiere where iventaireDesMatetiere.id =:id"
    )
    Optional<IventaireDesMatetiere> findOneWithToOneRelationships(@Param("id") Long id);

    @Query(
        "select iventaireDesMatetiere from IventaireDesMatetiere iventaireDesMatetiere where iventaireDesMatetiere.comptableMatiere.user.login = ?#{principal.username}"
    )
    Page<IventaireDesMatetiere> findByUserIsCurrentUser(Pageable pageable);
}
