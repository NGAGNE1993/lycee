package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Partenaire;
import com.mycompany.myapp.repository.PartenaireRepository;
import com.mycompany.myapp.service.PartenaireService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Partenaire}.
 */
@Service
@Transactional
public class PartenaireServiceImpl implements PartenaireService {

    private final Logger log = LoggerFactory.getLogger(PartenaireServiceImpl.class);

    private final PartenaireRepository partenaireRepository;

    public PartenaireServiceImpl(PartenaireRepository partenaireRepository) {
        this.partenaireRepository = partenaireRepository;
    }

    @Override
    public Partenaire save(Partenaire partenaire) {
        log.debug("Request to save Partenaire : {}", partenaire);
        return partenaireRepository.save(partenaire);
    }

    @Override
    public Partenaire update(Partenaire partenaire) {
        log.debug("Request to save Partenaire : {}", partenaire);
        return partenaireRepository.save(partenaire);
    }

    @Override
    public Optional<Partenaire> partialUpdate(Partenaire partenaire) {
        log.debug("Request to partially update Partenaire : {}", partenaire);

        return partenaireRepository
            .findById(partenaire.getId())
            .map(existingPartenaire -> {
                if (partenaire.getDenominationPartenaire() != null) {
                    existingPartenaire.setDenominationPartenaire(partenaire.getDenominationPartenaire());
                }
                if (partenaire.getStatauPartenaire() != null) {
                    existingPartenaire.setStatauPartenaire(partenaire.getStatauPartenaire());
                }
                if (partenaire.getAutreCategorie() != null) {
                    existingPartenaire.setAutreCategorie(partenaire.getAutreCategorie());
                }
                if (partenaire.getTypeAppui() != null) {
                    existingPartenaire.setTypeAppui(partenaire.getTypeAppui());
                }
                if (partenaire.getAutreNature() != null) {
                    existingPartenaire.setAutreNature(partenaire.getAutreNature());
                }

                return existingPartenaire;
            })
            .map(partenaireRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Partenaire> findAll(Pageable pageable) {
        log.debug("Request to get all Partenaires");
        return partenaireRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Partenaire> findByUserIsCurrentUser(Pageable pageable) {
        log.debug("Request to get all Partenaires");
        return partenaireRepository.findByUserIsCurrentUser(pageable);
    }
    
    public Page<Partenaire> findAllWithEagerRelationships(Pageable pageable) {
        return partenaireRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Partenaire> findOne(Long id) {
        log.debug("Request to get Partenaire : {}", id);
        return partenaireRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Partenaire : {}", id);
        partenaireRepository.deleteById(id);
    }
}
