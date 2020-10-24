package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.SecouristeService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.SecouristeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ensimag.acvl.domain.Secouriste}.
 */
@RestController
@RequestMapping("/api")
public class SecouristeResource {

    private final Logger log = LoggerFactory.getLogger(SecouristeResource.class);

    private static final String ENTITY_NAME = "secouriste";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecouristeService secouristeService;

    public SecouristeResource(SecouristeService secouristeService) {
        this.secouristeService = secouristeService;
    }

    /**
     * {@code POST  /secouristes} : Create a new secouriste.
     *
     * @param secouristeDTO the secouristeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secouristeDTO, or with status {@code 400 (Bad Request)} if the secouriste has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/secouristes")
    public ResponseEntity<SecouristeDTO> createSecouriste(@Valid @RequestBody SecouristeDTO secouristeDTO) throws URISyntaxException {
        log.debug("REST request to save Secouriste : {}", secouristeDTO);
        if (secouristeDTO.getId() != null) {
            throw new BadRequestAlertException("A new secouriste cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecouristeDTO result = secouristeService.save(secouristeDTO);
        return ResponseEntity.created(new URI("/api/secouristes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /secouristes} : Updates an existing secouriste.
     *
     * @param secouristeDTO the secouristeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secouristeDTO,
     * or with status {@code 400 (Bad Request)} if the secouristeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secouristeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/secouristes")
    public ResponseEntity<SecouristeDTO> updateSecouriste(@Valid @RequestBody SecouristeDTO secouristeDTO) throws URISyntaxException {
        log.debug("REST request to update Secouriste : {}", secouristeDTO);
        if (secouristeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SecouristeDTO result = secouristeService.save(secouristeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, secouristeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /secouristes} : get all the secouristes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secouristes in body.
     */
    @GetMapping("/secouristes")
    public List<SecouristeDTO> getAllSecouristes() {
        log.debug("REST request to get all Secouristes");
        return secouristeService.findAll();
    }

    /**
     * {@code GET  /secouristes/:id} : get the "id" secouriste.
     *
     * @param id the id of the secouristeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secouristeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/secouristes/{id}")
    public ResponseEntity<SecouristeDTO> getSecouriste(@PathVariable Long id) {
        log.debug("REST request to get Secouriste : {}", id);
        Optional<SecouristeDTO> secouristeDTO = secouristeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(secouristeDTO);
    }

    /**
     * {@code DELETE  /secouristes/:id} : delete the "id" secouriste.
     *
     * @param id the id of the secouristeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/secouristes/{id}")
    public ResponseEntity<Void> deleteSecouriste(@PathVariable Long id) {
        log.debug("REST request to delete Secouriste : {}", id);
        secouristeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
