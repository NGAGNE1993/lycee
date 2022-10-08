package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Recette;
import com.mycompany.myapp.repository.RecetteRepository;
import com.mycompany.myapp.service.RecetteService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Recette}.
 */
@Service
@Transactional
public class RecetteServiceImpl implements RecetteService {

    private final Logger log = LoggerFactory.getLogger(RecetteServiceImpl.class);

    private final RecetteRepository recetteRepository;

    public RecetteServiceImpl(RecetteRepository recetteRepository) {
        this.recetteRepository = recetteRepository;
    }

    @Override
    public Recette save(Recette recette) {
        log.debug("Request to save Recette : {}", recette);
        return recetteRepository.save(recette);
    }

    @Override
    public Recette update(Recette recette) {
        log.debug("Request to save Recette : {}", recette);
        return recetteRepository.save(recette);
    }

    @Override
    public Optional<Recette> partialUpdate(Recette recette) {
        log.debug("Request to partially update Recette : {}", recette);

        return recetteRepository
            .findById(recette.getId())
            .map(existingRecette -> {
                if (recette.getType() != null) {
                    existingRecette.setType(recette.getType());
                }
                if (recette.getAutreRecette() != null) {
                    existingRecette.setAutreRecette(recette.getAutreRecette());
                }
                if (recette.getTypeRessource() != null) {
                    existingRecette.setTypeRessource(recette.getTypeRessource());
                }
                if (recette.getMontant() != null) {
                    existingRecette.setMontant(recette.getMontant());
                }
                if (recette.getDate() != null) {
                    existingRecette.setDate(recette.getDate());
                }

                return existingRecette;
            })
            .map(recetteRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Recette> findAll(Pageable pageable) {
        log.debug("Request to get all Recettes");
        return recetteRepository.findAll(pageable);
    }

    public Page<Recette> findAllWithEagerRelationships(Pageable pageable) {
        return recetteRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Recette> findOne(Long id) {
        log.debug("Request to get Recette : {}", id);
        return recetteRepository.findOneWithEagerRelationships(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Recette> findByUserIsCurrentUser(Pageable pageable) {
        log.debug("Request to get all Recettes");
        return recetteRepository.findByUserIsCurrentUser(pageable);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recette : {}", id);
        recetteRepository.deleteById(id);
    }
}
