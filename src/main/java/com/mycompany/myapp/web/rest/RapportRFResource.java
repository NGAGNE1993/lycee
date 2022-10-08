package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.RapportRF;
import com.mycompany.myapp.repository.RapportRFRepository;
import com.mycompany.myapp.service.RapportRFService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
import com.mycompany.myapp.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.RapportRF}.
 */
@RestController
@RequestMapping("/api")
public class RapportRFResource {

    private final Logger log = LoggerFactory.getLogger(RapportRFResource.class);

    private static final String ENTITY_NAME = "rapportRF";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RapportRFService rapportRFService;

    private final RapportRFRepository rapportRFRepository;

    public RapportRFResource(RapportRFService rapportRFService, RapportRFRepository rapportRFRepository) {
        this.rapportRFService = rapportRFService;
        this.rapportRFRepository = rapportRFRepository;
    }

    /**
     * {@code POST  /rapport-rfs} : Create a new rapportRF.
     *
     * @param rapportRF the rapportRF to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rapportRF, or with status {@code 400 (Bad Request)} if the rapportRF has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rapport-rfs")
    public ResponseEntity<RapportRF> createRapportRF(@RequestBody RapportRF rapportRF) throws URISyntaxException {
        log.debug("REST request to save RapportRF : {}", rapportRF);
        if (rapportRF.getId() != null) {
            throw new BadRequestAlertException("A new rapportRF cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RapportRF result = rapportRFService.save(rapportRF);
        return ResponseEntity
            .created(new URI("/api/rapport-rfs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rapport-rfs/:id} : Updates an existing rapportRF.
     *
     * @param id the id of the rapportRF to save.
     * @param rapportRF the rapportRF to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rapportRF,
     * or with status {@code 400 (Bad Request)} if the rapportRF is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rapportRF couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rapport-rfs/{id}")
    public ResponseEntity<RapportRF> updateRapportRF(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RapportRF rapportRF
    ) throws URISyntaxException {
        log.debug("REST request to update RapportRF : {}, {}", id, rapportRF);
        if (rapportRF.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rapportRF.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rapportRFRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RapportRF result = rapportRFService.update(rapportRF);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rapportRF.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /rapport-rfs/:id} : Partial updates given fields of an existing rapportRF, field will ignore if it is null
     *
     * @param id the id of the rapportRF to save.
     * @param rapportRF the rapportRF to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rapportRF,
     * or with status {@code 400 (Bad Request)} if the rapportRF is not valid,
     * or with status {@code 404 (Not Found)} if the rapportRF is not found,
     * or with status {@code 500 (Internal Server Error)} if the rapportRF couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rapport-rfs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RapportRF> partialUpdateRapportRF(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RapportRF rapportRF
    ) throws URISyntaxException {
        log.debug("REST request to partial update RapportRF partially : {}, {}", id, rapportRF);
        if (rapportRF.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rapportRF.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rapportRFRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RapportRF> result = rapportRFService.partialUpdate(rapportRF);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rapportRF.getId().toString())
        );
    }

    /**
     * {@code GET  /rapport-rfs} : get all the rapportRFS.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rapportRFS in body.
     */
    @GetMapping("/rapport-rfs")
    public ResponseEntity<List<RapportRF>> getAllRapportRFS(Pageable pageable)
       {
        log.debug("REST request to get a page of RapportRFS");
        if (SecurityUtils.hasCurrentUserThisAuthority("ROLE_ADMIN")) {
            Page<RapportRF> page = rapportRFService.findAll(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
        return ResponseEntity
            .ok()
            .headers(
                PaginationUtil.generatePaginationHttpHeaders(
                    ServletUriComponentsBuilder.fromCurrentRequest(),
                    (rapportRFService.findByUserIsCurrentUser(pageable))
                )
            )
            .body((rapportRFRepository.findByUserIsCurrentUser(pageable)).getContent());
    }

    /**
     * {@code GET  /rapport-rfs/:id} : get the "id" rapportRF.
     *
     * @param id the id of the rapportRF to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rapportRF, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rapport-rfs/{id}")
    public ResponseEntity<RapportRF> getRapportRF(@PathVariable Long id) {
        log.debug("REST request to get RapportRF : {}", id);
        Optional<RapportRF> rapportRF = rapportRFService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rapportRF);
    }

    /**
     * {@code DELETE  /rapport-rfs/:id} : delete the "id" rapportRF.
     *
     * @param id the id of the rapportRF to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rapport-rfs/{id}")
    public ResponseEntity<Void> deleteRapportRF(@PathVariable Long id) {
        log.debug("REST request to delete RapportRF : {}", id);
        rapportRFService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
