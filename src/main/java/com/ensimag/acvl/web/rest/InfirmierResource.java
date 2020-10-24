package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.InfirmierService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.InfirmierDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.Infirmier}.
 */
@RestController
@RequestMapping("/api")
public class InfirmierResource {

    private final Logger log = LoggerFactory.getLogger(InfirmierResource.class);

    private static final String ENTITY_NAME = "infirmier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfirmierService infirmierService;

    public InfirmierResource(InfirmierService infirmierService) {
        this.infirmierService = infirmierService;
    }

    /**
     * {@code POST  /infirmiers} : Create a new infirmier.
     *
     * @param infirmierDTO the infirmierDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new infirmierDTO, or with status {@code 400 (Bad Request)} if the infirmier has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/infirmiers")
    public ResponseEntity<InfirmierDTO> createInfirmier(@Valid @RequestBody InfirmierDTO infirmierDTO) throws URISyntaxException {
        log.debug("REST request to save Infirmier : {}", infirmierDTO);
        if (infirmierDTO.getId() != null) {
            throw new BadRequestAlertException("A new infirmier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InfirmierDTO result = infirmierService.save(infirmierDTO);
        return ResponseEntity.created(new URI("/api/infirmiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /infirmiers} : Updates an existing infirmier.
     *
     * @param infirmierDTO the infirmierDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated infirmierDTO,
     * or with status {@code 400 (Bad Request)} if the infirmierDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the infirmierDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/infirmiers")
    public ResponseEntity<InfirmierDTO> updateInfirmier(@Valid @RequestBody InfirmierDTO infirmierDTO) throws URISyntaxException {
        log.debug("REST request to update Infirmier : {}", infirmierDTO);
        if (infirmierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InfirmierDTO result = infirmierService.save(infirmierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, infirmierDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /infirmiers} : get all the infirmiers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infirmiers in body.
     */
    @GetMapping("/infirmiers")
    public List<InfirmierDTO> getAllInfirmiers() {
        log.debug("REST request to get all Infirmiers");
        return infirmierService.findAll();
    }

    /**
     * {@code GET  /infirmiers/:id} : get the "id" infirmier.
     *
     * @param id the id of the infirmierDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infirmierDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/infirmiers/{id}")
    public ResponseEntity<InfirmierDTO> getInfirmier(@PathVariable Long id) {
        log.debug("REST request to get Infirmier : {}", id);
        Optional<InfirmierDTO> infirmierDTO = infirmierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(infirmierDTO);
    }

    /**
     * {@code DELETE  /infirmiers/:id} : delete the "id" infirmier.
     *
     * @param id the id of the infirmierDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/infirmiers/{id}")
    public ResponseEntity<Void> deleteInfirmier(@PathVariable Long id) {
        log.debug("REST request to delete Infirmier : {}", id);
        infirmierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
