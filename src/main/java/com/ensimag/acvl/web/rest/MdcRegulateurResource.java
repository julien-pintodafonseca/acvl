package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.MdcRegulateurService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.MdcRegulateurDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.MdcRegulateur}.
 */
@RestController
@RequestMapping("/api")
public class MdcRegulateurResource {

    private final Logger log = LoggerFactory.getLogger(MdcRegulateurResource.class);

    private static final String ENTITY_NAME = "mdcRegulateur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MdcRegulateurService mdcRegulateurService;

    public MdcRegulateurResource(MdcRegulateurService mdcRegulateurService) {
        this.mdcRegulateurService = mdcRegulateurService;
    }

    /**
     * {@code POST  /mdc-regulateurs} : Create a new mdcRegulateur.
     *
     * @param mdcRegulateurDTO the mdcRegulateurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mdcRegulateurDTO, or with status {@code 400 (Bad Request)} if the mdcRegulateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mdc-regulateurs")
    public ResponseEntity<MdcRegulateurDTO> createMdcRegulateur(@Valid @RequestBody MdcRegulateurDTO mdcRegulateurDTO) throws URISyntaxException {
        log.debug("REST request to save MdcRegulateur : {}", mdcRegulateurDTO);
        if (mdcRegulateurDTO.getId() != null) {
            throw new BadRequestAlertException("A new mdcRegulateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MdcRegulateurDTO result = mdcRegulateurService.save(mdcRegulateurDTO);
        return ResponseEntity.created(new URI("/api/mdc-regulateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mdc-regulateurs} : Updates an existing mdcRegulateur.
     *
     * @param mdcRegulateurDTO the mdcRegulateurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mdcRegulateurDTO,
     * or with status {@code 400 (Bad Request)} if the mdcRegulateurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mdcRegulateurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mdc-regulateurs")
    public ResponseEntity<MdcRegulateurDTO> updateMdcRegulateur(@Valid @RequestBody MdcRegulateurDTO mdcRegulateurDTO) throws URISyntaxException {
        log.debug("REST request to update MdcRegulateur : {}", mdcRegulateurDTO);
        if (mdcRegulateurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MdcRegulateurDTO result = mdcRegulateurService.save(mdcRegulateurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mdcRegulateurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mdc-regulateurs} : get all the mdcRegulateurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mdcRegulateurs in body.
     */
    @GetMapping("/mdc-regulateurs")
    public List<MdcRegulateurDTO> getAllMdcRegulateurs() {
        log.debug("REST request to get all MdcRegulateurs");
        return mdcRegulateurService.findAll();
    }

    /**
     * {@code GET  /mdc-regulateurs/:id} : get the "id" mdcRegulateur.
     *
     * @param id the id of the mdcRegulateurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mdcRegulateurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mdc-regulateurs/{id}")
    public ResponseEntity<MdcRegulateurDTO> getMdcRegulateur(@PathVariable Long id) {
        log.debug("REST request to get MdcRegulateur : {}", id);
        Optional<MdcRegulateurDTO> mdcRegulateurDTO = mdcRegulateurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mdcRegulateurDTO);
    }

    /**
     * {@code DELETE  /mdc-regulateurs/:id} : delete the "id" mdcRegulateur.
     *
     * @param id the id of the mdcRegulateurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mdc-regulateurs/{id}")
    public ResponseEntity<Void> deleteMdcRegulateur(@PathVariable Long id) {
        log.debug("REST request to delete MdcRegulateur : {}", id);
        mdcRegulateurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
