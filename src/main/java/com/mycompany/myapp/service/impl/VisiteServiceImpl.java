package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Visite;
import com.mycompany.myapp.repository.VisiteRepository;
import com.mycompany.myapp.service.VisiteService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Visite}.
 */
@Service
@Transactional
public class VisiteServiceImpl implements VisiteService {

    private final Logger log = LoggerFactory.getLogger(VisiteServiceImpl.class);

    private final VisiteRepository visiteRepository;

    public VisiteServiceImpl(VisiteRepository visiteRepository) {
        this.visiteRepository = visiteRepository;
    }

    @Override
    public Visite save(Visite visite) {
        log.debug("Request to save Visite : {}", visite);
        return visiteRepository.save(visite);
    }

    @Override
    public Visite update(Visite visite) {
        log.debug("Request to save Visite : {}", visite);
        return visiteRepository.save(visite);
    }

    @Override
    public Optional<Visite> partialUpdate(Visite visite) {
        log.debug("Request to partially update Visite : {}", visite);

        return visiteRepository
            .findById(visite.getId())
            .map(existingVisite -> {
                if (visite.getNature() != null) {
                    existingVisite.setNature(visite.getNature());
                }
                if (visite.getAutreNature() != null) {
                    existingVisite.setAutreNature(visite.getAutreNature());
                }
                if (visite.getProvenance() != null) {
                    existingVisite.setProvenance(visite.getProvenance());
                }
                if (visite.getAutreProvenance() != null) {
                    existingVisite.setAutreProvenance(visite.getAutreProvenance());
                }
                if (visite.getObjet() != null) {
                    existingVisite.setObjet(visite.getObjet());
                }
                if (visite.getPeriode() != null) {
                    existingVisite.setPeriode(visite.getPeriode());
                }

                return existingVisite;
            })
            .map(visiteRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Visite> findAll(Pageable pageable) {
        log.debug("Request to get all Visites");
        return visiteRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Visite> findByUserIsCurrentUser(Pageable pageable) {
        log.debug("Request to get all Visites");
        return visiteRepository.findByUserIsCurrentUser(pageable);
    }

    public Page<Visite> findAllWithEagerRelationships(Pageable pageable) {
        return visiteRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Visite> findOne(Long id) {
        log.debug("Request to get Visite : {}", id);
        return visiteRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Visite : {}", id);
        visiteRepository.deleteById(id);
    }
}
