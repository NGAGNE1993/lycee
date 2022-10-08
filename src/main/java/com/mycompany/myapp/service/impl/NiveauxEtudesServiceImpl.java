package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.NiveauxEtudes;
import com.mycompany.myapp.repository.NiveauxEtudesRepository;
import com.mycompany.myapp.service.NiveauxEtudesService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NiveauxEtudes}.
 */
@Service
@Transactional
public class NiveauxEtudesServiceImpl implements NiveauxEtudesService {

    private final Logger log = LoggerFactory.getLogger(NiveauxEtudesServiceImpl.class);

    private final NiveauxEtudesRepository niveauxEtudesRepository;

    public NiveauxEtudesServiceImpl(NiveauxEtudesRepository niveauxEtudesRepository) {
        this.niveauxEtudesRepository = niveauxEtudesRepository;
    }

    @Override
    public NiveauxEtudes save(NiveauxEtudes niveauxEtudes) {
        log.debug("Request to save NiveauxEtudes : {}", niveauxEtudes);
        return niveauxEtudesRepository.save(niveauxEtudes);
    }

    @Override
    public NiveauxEtudes update(NiveauxEtudes niveauxEtudes) {
        log.debug("Request to save NiveauxEtudes : {}", niveauxEtudes);
        return niveauxEtudesRepository.save(niveauxEtudes);
    }

    @Override
    public Optional<NiveauxEtudes> partialUpdate(NiveauxEtudes niveauxEtudes) {
        log.debug("Request to partially update NiveauxEtudes : {}", niveauxEtudes);

        return niveauxEtudesRepository
            .findById(niveauxEtudes.getId())
            .map(existingNiveauxEtudes -> {
                if (niveauxEtudes.getNomNiveau() != null) {
                    existingNiveauxEtudes.setNomNiveau(niveauxEtudes.getNomNiveau());
                }
                if (niveauxEtudes.getTypeNiveau() != null) {
                    existingNiveauxEtudes.setTypeNiveau(niveauxEtudes.getTypeNiveau());
                }

                return existingNiveauxEtudes;
            })
            .map(niveauxEtudesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NiveauxEtudes> findAll(Pageable pageable) {
        log.debug("Request to get all NiveauxEtudes");
        return niveauxEtudesRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NiveauxEtudes> findByUserIsCurrentUser(Pageable pageable) {
        log.debug("Request to get all NiveauxEtudes");
        return niveauxEtudesRepository.findByUserIsCurrentUser(pageable);
    }
    
    public Page<NiveauxEtudes> findAllWithEagerRelationships(Pageable pageable) {
        return niveauxEtudesRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NiveauxEtudes> findOne(Long id) {
        log.debug("Request to get NiveauxEtudes : {}", id);
        return niveauxEtudesRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiveauxEtudes : {}", id);
        niveauxEtudesRepository.deleteById(id);
    }
}
