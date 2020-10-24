package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.MdcMobileService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.MdcMobileDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.MdcMobile}.
 */
@RestController
@RequestMapping("/api")
public class MdcMobileResource {

    private final Logger log = LoggerFactory.getLogger(MdcMobileResource.class);

    private static final String ENTITY_NAME = "mdcMobile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MdcMobileService mdcMobileService;

    public MdcMobileResource(MdcMobileService mdcMobileService) {
        this.mdcMobileService = mdcMobileService;
    }

    /**
     * {@code POST  /mdc-mobiles} : Create a new mdcMobile.
     *
     * @param mdcMobileDTO the mdcMobileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mdcMobileDTO, or with status {@code 400 (Bad Request)} if the mdcMobile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mdc-mobiles")
    public ResponseEntity<MdcMobileDTO> createMdcMobile(@Valid @RequestBody MdcMobileDTO mdcMobileDTO) throws URISyntaxException {
        log.debug("REST request to save MdcMobile : {}", mdcMobileDTO);
        if (mdcMobileDTO.getId() != null) {
            throw new BadRequestAlertException("A new mdcMobile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MdcMobileDTO result = mdcMobileService.save(mdcMobileDTO);
        return ResponseEntity.created(new URI("/api/mdc-mobiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mdc-mobiles} : Updates an existing mdcMobile.
     *
     * @param mdcMobileDTO the mdcMobileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mdcMobileDTO,
     * or with status {@code 400 (Bad Request)} if the mdcMobileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mdcMobileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mdc-mobiles")
    public ResponseEntity<MdcMobileDTO> updateMdcMobile(@Valid @RequestBody MdcMobileDTO mdcMobileDTO) throws URISyntaxException {
        log.debug("REST request to update MdcMobile : {}", mdcMobileDTO);
        if (mdcMobileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MdcMobileDTO result = mdcMobileService.save(mdcMobileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mdcMobileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mdc-mobiles} : get all the mdcMobiles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mdcMobiles in body.
     */
    @GetMapping("/mdc-mobiles")
    public List<MdcMobileDTO> getAllMdcMobiles() {
        log.debug("REST request to get all MdcMobiles");
        return mdcMobileService.findAll();
    }

    /**
     * {@code GET  /mdc-mobiles/:id} : get the "id" mdcMobile.
     *
     * @param id the id of the mdcMobileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mdcMobileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mdc-mobiles/{id}")
    public ResponseEntity<MdcMobileDTO> getMdcMobile(@PathVariable Long id) {
        log.debug("REST request to get MdcMobile : {}", id);
        Optional<MdcMobileDTO> mdcMobileDTO = mdcMobileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mdcMobileDTO);
    }

    /**
     * {@code DELETE  /mdc-mobiles/:id} : delete the "id" mdcMobile.
     *
     * @param id the id of the mdcMobileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mdc-mobiles/{id}")
    public ResponseEntity<Void> deleteMdcMobile(@PathVariable Long id) {
        log.debug("REST request to delete MdcMobile : {}", id);
        mdcMobileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
