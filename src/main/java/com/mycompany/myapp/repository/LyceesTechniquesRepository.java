package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LyceesTechniques;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LyceesTechniques entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LyceesTechniquesRepository extends JpaRepository<LyceesTechniques, Long> {}
