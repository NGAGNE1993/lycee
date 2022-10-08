package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.RapportRF;
import com.mycompany.myapp.repository.RapportRFRepository;
import com.mycompany.myapp.service.RapportRFService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RapportRF}.
 */
@Service
@Transactional
public class RapportRFServiceImpl implements RapportRFService {

    private final Logger log = LoggerFactory.getLogger(RapportRFServiceImpl.class);

    private final RapportRFRepository rapportRFRepository;

    public RapportRFServiceImpl(RapportRFRepository rapportRFRepository) {
        this.rapportRFRepository = rapportRFRepository;
    }

    @Override
    public RapportRF save(RapportRF rapportRF) {
        log.debug("Request to save RapportRF : {}", rapportRF);
        return rapportRFRepository.save(rapportRF);
    }

    @Override
    public RapportRF update(RapportRF rapportRF) {
        log.debug("Request to save RapportRF : {}", rapportRF);
        return rapportRFRepository.save(rapportRF);
    }

    @Override
    public Optional<RapportRF> partialUpdate(RapportRF rapportRF) {
        log.debug("Request to partially update RapportRF : {}", rapportRF);

        return rapportRFRepository
            .findById(rapportRF.getId())
            .map(existingRapportRF -> {
                if (rapportRF.getTypeRaport() != null) {
                    existingRapportRF.setTypeRaport(rapportRF.getTypeRaport());
                }
                if (rapportRF.getRentre() != null) {
                    existingRapportRF.setRentre(rapportRF.getRentre());
                }
                if (rapportRF.getRentreContentType() != null) {
                    existingRapportRF.setRentreContentType(rapportRF.getRentreContentType());
                }
                if (rapportRF.getFin() != null) {
                    existingRapportRF.setFin(rapportRF.getFin());
                }
                if (rapportRF.getFinContentType() != null) {
                    existingRapportRF.setFinContentType(rapportRF.getFinContentType());
                }

                return existingRapportRF;
            })
            .map(rapportRFRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RapportRF> findAll(Pageable pageable) {
        log.debug("Request to get all RapportRFS");
        return rapportRFRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RapportRF> findByUserIsCurrentUser(Pageable pageable) {
        log.debug("Request to get all RapportRFS");
        return rapportRFRepository.findByUserIsCurrentUser(pageable);
    }

    public Page<RapportRF> findAllWithEagerRelationships(Pageable pageable) {
        return rapportRFRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RapportRF> findOne(Long id) {
        log.debug("Request to get RapportRF : {}", id);
        return rapportRFRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RapportRF : {}", id);
        rapportRFRepository.deleteById(id);
    }
}
