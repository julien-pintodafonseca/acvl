package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.PersonnelSanteService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.PersonnelSanteDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.PersonnelSante}.
 */
@RestController
@RequestMapping("/api")
public class PersonnelSanteResource {

    private final Logger log = LoggerFactory.getLogger(PersonnelSanteResource.class);

    private static final String ENTITY_NAME = "personnelSante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonnelSanteService personnelSanteService;

    public PersonnelSanteResource(PersonnelSanteService personnelSanteService) {
        this.personnelSanteService = personnelSanteService;
    }

    /**
     * {@code POST  /personnel-santes} : Create a new personnelSante.
     *
     * @param personnelSanteDTO the personnelSanteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personnelSanteDTO, or with status {@code 400 (Bad Request)} if the personnelSante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/personnel-santes")
    public ResponseEntity<PersonnelSanteDTO> createPersonnelSante(@Valid @RequestBody PersonnelSanteDTO personnelSanteDTO) throws URISyntaxException {
        log.debug("REST request to save PersonnelSante : {}", personnelSanteDTO);
        if (personnelSanteDTO.getId() != null) {
            throw new BadRequestAlertException("A new personnelSante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonnelSanteDTO result = personnelSanteService.save(personnelSanteDTO);
        return ResponseEntity.created(new URI("/api/personnel-santes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /personnel-santes} : Updates an existing personnelSante.
     *
     * @param personnelSanteDTO the personnelSanteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personnelSanteDTO,
     * or with status {@code 400 (Bad Request)} if the personnelSanteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personnelSanteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/personnel-santes")
    public ResponseEntity<PersonnelSanteDTO> updatePersonnelSante(@Valid @RequestBody PersonnelSanteDTO personnelSanteDTO) throws URISyntaxException {
        log.debug("REST request to update PersonnelSante : {}", personnelSanteDTO);
        if (personnelSanteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonnelSanteDTO result = personnelSanteService.save(personnelSanteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, personnelSanteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /personnel-santes} : get all the personnelSantes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personnelSantes in body.
     */
    @GetMapping("/personnel-santes")
    public List<PersonnelSanteDTO> getAllPersonnelSantes() {
        log.debug("REST request to get all PersonnelSantes");
        return personnelSanteService.findAll();
    }

    /**
     * {@code GET  /personnel-santes/:id} : get the "id" personnelSante.
     *
     * @param id the id of the personnelSanteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personnelSanteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personnel-santes/{id}")
    public ResponseEntity<PersonnelSanteDTO> getPersonnelSante(@PathVariable Long id) {
        log.debug("REST request to get PersonnelSante : {}", id);
        Optional<PersonnelSanteDTO> personnelSanteDTO = personnelSanteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personnelSanteDTO);
    }

    /**
     * {@code DELETE  /personnel-santes/:id} : delete the "id" personnelSante.
     *
     * @param id the id of the personnelSanteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personnel-santes/{id}")
    public ResponseEntity<Void> deletePersonnelSante(@PathVariable Long id) {
        log.debug("REST request to delete PersonnelSante : {}", id);
        personnelSanteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
