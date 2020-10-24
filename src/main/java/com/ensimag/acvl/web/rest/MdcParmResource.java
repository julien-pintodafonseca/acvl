package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.MdcParmService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.MdcParmDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.MdcParm}.
 */
@RestController
@RequestMapping("/api")
public class MdcParmResource {

    private final Logger log = LoggerFactory.getLogger(MdcParmResource.class);

    private static final String ENTITY_NAME = "mdcParm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MdcParmService mdcParmService;

    public MdcParmResource(MdcParmService mdcParmService) {
        this.mdcParmService = mdcParmService;
    }

    /**
     * {@code POST  /mdc-parms} : Create a new mdcParm.
     *
     * @param mdcParmDTO the mdcParmDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mdcParmDTO, or with status {@code 400 (Bad Request)} if the mdcParm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mdc-parms")
    public ResponseEntity<MdcParmDTO> createMdcParm(@Valid @RequestBody MdcParmDTO mdcParmDTO) throws URISyntaxException {
        log.debug("REST request to save MdcParm : {}", mdcParmDTO);
        if (mdcParmDTO.getId() != null) {
            throw new BadRequestAlertException("A new mdcParm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MdcParmDTO result = mdcParmService.save(mdcParmDTO);
        return ResponseEntity.created(new URI("/api/mdc-parms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mdc-parms} : Updates an existing mdcParm.
     *
     * @param mdcParmDTO the mdcParmDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mdcParmDTO,
     * or with status {@code 400 (Bad Request)} if the mdcParmDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mdcParmDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mdc-parms")
    public ResponseEntity<MdcParmDTO> updateMdcParm(@Valid @RequestBody MdcParmDTO mdcParmDTO) throws URISyntaxException {
        log.debug("REST request to update MdcParm : {}", mdcParmDTO);
        if (mdcParmDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MdcParmDTO result = mdcParmService.save(mdcParmDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mdcParmDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mdc-parms} : get all the mdcParms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mdcParms in body.
     */
    @GetMapping("/mdc-parms")
    public List<MdcParmDTO> getAllMdcParms() {
        log.debug("REST request to get all MdcParms");
        return mdcParmService.findAll();
    }

    /**
     * {@code GET  /mdc-parms/:id} : get the "id" mdcParm.
     *
     * @param id the id of the mdcParmDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mdcParmDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mdc-parms/{id}")
    public ResponseEntity<MdcParmDTO> getMdcParm(@PathVariable Long id) {
        log.debug("REST request to get MdcParm : {}", id);
        Optional<MdcParmDTO> mdcParmDTO = mdcParmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mdcParmDTO);
    }

    /**
     * {@code DELETE  /mdc-parms/:id} : delete the "id" mdcParm.
     *
     * @param id the id of the mdcParmDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mdc-parms/{id}")
    public ResponseEntity<Void> deleteMdcParm(@PathVariable Long id) {
        log.debug("REST request to delete MdcParm : {}", id);
        mdcParmService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
