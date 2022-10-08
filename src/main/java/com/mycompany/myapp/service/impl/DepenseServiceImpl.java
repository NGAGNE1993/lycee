package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Depense;
import com.mycompany.myapp.repository.DepenseRepository;
import com.mycompany.myapp.service.DepenseService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Depense}.
 */
@Service
@Transactional
public class DepenseServiceImpl implements DepenseService {

    private final Logger log = LoggerFactory.getLogger(DepenseServiceImpl.class);

    private final DepenseRepository depenseRepository;

    public DepenseServiceImpl(DepenseRepository depenseRepository) {
        this.depenseRepository = depenseRepository;
    }

    @Override
    public Depense save(Depense depense) {
        log.debug("Request to save Depense : {}", depense);
        return depenseRepository.save(depense);
    }

    @Override
    public Depense update(Depense depense) {
        log.debug("Request to save Depense : {}", depense);
        return depenseRepository.save(depense);
    }

    @Override
    public Optional<Depense> partialUpdate(Depense depense) {
        log.debug("Request to partially update Depense : {}", depense);

        return depenseRepository
            .findById(depense.getId())
            .map(existingDepense -> {
                if (depense.getTypeDepense() != null) {
                    existingDepense.setTypeDepense(depense.getTypeDepense());
                }
                if (depense.getAutreDepense() != null) {
                    existingDepense.setAutreDepense(depense.getAutreDepense());
                }
                if (depense.getDescription() != null) {
                    existingDepense.setDescription(depense.getDescription());
                }
                if (depense.getMontantDepense() != null) {
                    existingDepense.setMontantDepense(depense.getMontantDepense());
                }

                return existingDepense;
            })
            .map(depenseRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Depense> findAll(Pageable pageable) {
        log.debug("Request to get all Depenses");
        return depenseRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Depense> findByUserIsCurrentUser(Pageable pageable) {
        log.debug("Request to get all Depenses");
        return depenseRepository.findByUserIsCurrentUser(pageable);
    }
    
    public Page<Depense> findAllWithEagerRelationships(Pageable pageable) {
        return depenseRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Depense> findOne(Long id) {
        log.debug("Request to get Depense : {}", id);
        return depenseRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Depense : {}", id);
        depenseRepository.deleteById(id);
    }
}
