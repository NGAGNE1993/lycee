package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Salle;
import com.mycompany.myapp.repository.SalleRepository;
import com.mycompany.myapp.service.SalleService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Salle}.
 */
@Service
@Transactional
public class SalleServiceImpl implements SalleService {

    private final Logger log = LoggerFactory.getLogger(SalleServiceImpl.class);

    private final SalleRepository salleRepository;

    public SalleServiceImpl(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    @Override
    public Salle save(Salle salle) {
        log.debug("Request to save Salle : {}", salle);
        return salleRepository.save(salle);
    }

    @Override
    public Salle update(Salle salle) {
        log.debug("Request to save Salle : {}", salle);
        return salleRepository.save(salle);
    }

    @Override
    public Optional<Salle> partialUpdate(Salle salle) {
        log.debug("Request to partially update Salle : {}", salle);

        return salleRepository
            .findById(salle.getId())
            .map(existingSalle -> {
                if (salle.getNombreSalleclasse() != null) {
                    existingSalle.setNombreSalleclasse(salle.getNombreSalleclasse());
                }
                if (salle.getNombreAteliers() != null) {
                    existingSalle.setNombreAteliers(salle.getNombreAteliers());
                }
                if (salle.getSpecialiase() != null) {
                    existingSalle.setSpecialiase(salle.getSpecialiase());
                }
                if (salle.getNombreSalleSpecialise() != null) {
                    existingSalle.setNombreSalleSpecialise(salle.getNombreSalleSpecialise());
                }
                if (salle.getJustificationSalleSpe() != null) {
                    existingSalle.setJustificationSalleSpe(salle.getJustificationSalleSpe());
                }
                if (salle.getCdi() != null) {
                    existingSalle.setCdi(salle.getCdi());
                }
                if (salle.getNombreCDI() != null) {
                    existingSalle.setNombreCDI(salle.getNombreCDI());
                }
                if (salle.getJustifiactifSalleCDI() != null) {
                    existingSalle.setJustifiactifSalleCDI(salle.getJustifiactifSalleCDI());
                }
                if (salle.getGym() != null) {
                    existingSalle.setGym(salle.getGym());
                }
                if (salle.getTerrainSport() != null) {
                    existingSalle.setTerrainSport(salle.getTerrainSport());
                }
                if (salle.getAutreSalle() != null) {
                    existingSalle.setAutreSalle(salle.getAutreSalle());
                }

                return existingSalle;
            })
            .map(salleRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Salle> findAll(Pageable pageable) {
        log.debug("Request to get all Salles");
        return salleRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Salle> findByUserIsCurrentUser(Pageable pageable) {
        log.debug("Request to get all Salles");
        return salleRepository.findByUserIsCurrentUser(pageable);
    }

    public Page<Salle> findAllWithEagerRelationships(Pageable pageable) {
        return salleRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Salle> findOne(Long id) {
        log.debug("Request to get Salle : {}", id);
        return salleRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Salle : {}", id);
        salleRepository.deleteById(id);
    }
}
