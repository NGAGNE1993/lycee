package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Filiere;
import com.mycompany.myapp.repository.FiliereRepository;
import com.mycompany.myapp.service.FiliereService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Filiere}.
 */
@Service
@Transactional
public class FiliereServiceImpl implements FiliereService {

    private final Logger log = LoggerFactory.getLogger(FiliereServiceImpl.class);

    private final FiliereRepository filiereRepository;

    public FiliereServiceImpl(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    @Override
    public Filiere save(Filiere filiere) {
        log.debug("Request to save Filiere : {}", filiere);
        return filiereRepository.save(filiere);
    }

    @Override
    public Filiere update(Filiere filiere) {
        log.debug("Request to save Filiere : {}", filiere);
        return filiereRepository.save(filiere);
    }

    @Override
    public Optional<Filiere> partialUpdate(Filiere filiere) {
        log.debug("Request to partially update Filiere : {}", filiere);

        return filiereRepository
            .findById(filiere.getId())
            .map(existingFiliere -> {
                if (filiere.getNomFiliere() != null) {
                    existingFiliere.setNomFiliere(filiere.getNomFiliere());
                }
                if (filiere.getNomAutre() != null) {
                    existingFiliere.setNomAutre(filiere.getNomAutre());
                }
                if (filiere.getNomProgramme() != null) {
                    existingFiliere.setNomProgramme(filiere.getNomProgramme());
                }
                if (filiere.getAutreAGR() != null) {
                    existingFiliere.setAutreAGR(filiere.getAutreAGR());
                }
                if (filiere.getNomProgrammeEl() != null) {
                    existingFiliere.setNomProgrammeEl(filiere.getNomProgrammeEl());
                }
                if (filiere.getAutreEL() != null) {
                    existingFiliere.setAutreEL(filiere.getAutreEL());
                }
                if (filiere.getAutreMIN() != null) {
                    existingFiliere.setAutreMIN(filiere.getAutreMIN());
                }
                if (filiere.getAutreBAT() != null) {
                    existingFiliere.setAutreBAT(filiere.getAutreBAT());
                }
                if (filiere.getAutreMECAN() != null) {
                    existingFiliere.setAutreMECAN(filiere.getAutreMECAN());
                }
                if (filiere.getAutreMENU() != null) {
                    existingFiliere.setAutreMENU(filiere.getAutreMENU());
                }
                if (filiere.getAutreAGRO() != null) {
                    existingFiliere.setAutreAGRO(filiere.getAutreAGRO());
                }
                if (filiere.getAutreELECTRO() != null) {
                    existingFiliere.setAutreELECTRO(filiere.getAutreELECTRO());
                }
                if (filiere.getAutreSTRUC() != null) {
                    existingFiliere.setAutreSTRUC(filiere.getAutreSTRUC());
                }
                if (filiere.getAutreEC() != null) {
                    existingFiliere.setAutreEC(filiere.getAutreEC());
                }
                if (filiere.getAutreCOM() != null) {
                    existingFiliere.setAutreCOM(filiere.getAutreCOM());
                }
                if (filiere.getAutreIN() != null) {
                    existingFiliere.setAutreIN(filiere.getAutreIN());
                }
                if (filiere.getAutreSAN() != null) {
                    existingFiliere.setAutreSAN(filiere.getAutreSAN());
                }
                if (filiere.getAutreProgramme() != null) {
                    existingFiliere.setAutreProgramme(filiere.getAutreProgramme());
                }

                return existingFiliere;
            })
            .map(filiereRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Filiere> findAll(Pageable pageable) {
        log.debug("Request to get all Filieres");
        return filiereRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Filiere> findByUserIsCurrentUser(Pageable pageable) {
        log.debug("Request to get all Filieres");
        return filiereRepository.findByUserIsCurrentUser(pageable);
    }
    
    public Page<Filiere> findAllWithEagerRelationships(Pageable pageable) {
        return filiereRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Filiere> findOne(Long id) {
        log.debug("Request to get Filiere : {}", id);
        return filiereRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Filiere : {}", id);
        filiereRepository.deleteById(id);
    }
}
