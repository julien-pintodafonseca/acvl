package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.MdcFixeService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.MdcFixeDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.MdcFixe}.
 */
@RestController
@RequestMapping("/api")
public class MdcFixeResource {

    private final Logger log = LoggerFactory.getLogger(MdcFixeResource.class);

    private static final String ENTITY_NAME = "mdcFixe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MdcFixeService mdcFixeService;

    public MdcFixeResource(MdcFixeService mdcFixeService) {
        this.mdcFixeService = mdcFixeService;
    }

    /**
     * {@code POST  /mdc-fixes} : Create a new mdcFixe.
     *
     * @param mdcFixeDTO the mdcFixeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mdcFixeDTO, or with status {@code 400 (Bad Request)} if the mdcFixe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mdc-fixes")
    public ResponseEntity<MdcFixeDTO> createMdcFixe(@Valid @RequestBody MdcFixeDTO mdcFixeDTO) throws URISyntaxException {
        log.debug("REST request to save MdcFixe : {}", mdcFixeDTO);
        if (mdcFixeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mdcFixe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MdcFixeDTO result = mdcFixeService.save(mdcFixeDTO);
        return ResponseEntity.created(new URI("/api/mdc-fixes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mdc-fixes} : Updates an existing mdcFixe.
     *
     * @param mdcFixeDTO the mdcFixeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mdcFixeDTO,
     * or with status {@code 400 (Bad Request)} if the mdcFixeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mdcFixeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mdc-fixes")
    public ResponseEntity<MdcFixeDTO> updateMdcFixe(@Valid @RequestBody MdcFixeDTO mdcFixeDTO) throws URISyntaxException {
        log.debug("REST request to update MdcFixe : {}", mdcFixeDTO);
        if (mdcFixeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MdcFixeDTO result = mdcFixeService.save(mdcFixeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mdcFixeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mdc-fixes} : get all the mdcFixes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mdcFixes in body.
     */
    @GetMapping("/mdc-fixes")
    public List<MdcFixeDTO> getAllMdcFixes() {
        log.debug("REST request to get all MdcFixes");
        return mdcFixeService.findAll();
    }

    /**
     * {@code GET  /mdc-fixes/:id} : get the "id" mdcFixe.
     *
     * @param id the id of the mdcFixeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mdcFixeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mdc-fixes/{id}")
    public ResponseEntity<MdcFixeDTO> getMdcFixe(@PathVariable Long id) {
        log.debug("REST request to get MdcFixe : {}", id);
        Optional<MdcFixeDTO> mdcFixeDTO = mdcFixeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mdcFixeDTO);
    }

    /**
     * {@code DELETE  /mdc-fixes/:id} : delete the "id" mdcFixe.
     *
     * @param id the id of the mdcFixeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mdc-fixes/{id}")
    public ResponseEntity<Void> deleteMdcFixe(@PathVariable Long id) {
        log.debug("REST request to delete MdcFixe : {}", id);
        mdcFixeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
