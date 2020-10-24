package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.MedecinParmService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.MedecinParmDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.MedecinParm}.
 */
@RestController
@RequestMapping("/api")
public class MedecinParmResource {

    private final Logger log = LoggerFactory.getLogger(MedecinParmResource.class);

    private static final String ENTITY_NAME = "medecinParm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedecinParmService medecinParmService;

    public MedecinParmResource(MedecinParmService medecinParmService) {
        this.medecinParmService = medecinParmService;
    }

    /**
     * {@code POST  /medecin-parms} : Create a new medecinParm.
     *
     * @param medecinParmDTO the medecinParmDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medecinParmDTO, or with status {@code 400 (Bad Request)} if the medecinParm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medecin-parms")
    public ResponseEntity<MedecinParmDTO> createMedecinParm(@Valid @RequestBody MedecinParmDTO medecinParmDTO) throws URISyntaxException {
        log.debug("REST request to save MedecinParm : {}", medecinParmDTO);
        if (medecinParmDTO.getId() != null) {
            throw new BadRequestAlertException("A new medecinParm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedecinParmDTO result = medecinParmService.save(medecinParmDTO);
        return ResponseEntity.created(new URI("/api/medecin-parms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medecin-parms} : Updates an existing medecinParm.
     *
     * @param medecinParmDTO the medecinParmDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medecinParmDTO,
     * or with status {@code 400 (Bad Request)} if the medecinParmDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medecinParmDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medecin-parms")
    public ResponseEntity<MedecinParmDTO> updateMedecinParm(@Valid @RequestBody MedecinParmDTO medecinParmDTO) throws URISyntaxException {
        log.debug("REST request to update MedecinParm : {}", medecinParmDTO);
        if (medecinParmDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedecinParmDTO result = medecinParmService.save(medecinParmDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, medecinParmDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medecin-parms} : get all the medecinParms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medecinParms in body.
     */
    @GetMapping("/medecin-parms")
    public List<MedecinParmDTO> getAllMedecinParms() {
        log.debug("REST request to get all MedecinParms");
        return medecinParmService.findAll();
    }

    /**
     * {@code GET  /medecin-parms/:id} : get the "id" medecinParm.
     *
     * @param id the id of the medecinParmDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medecinParmDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medecin-parms/{id}")
    public ResponseEntity<MedecinParmDTO> getMedecinParm(@PathVariable Long id) {
        log.debug("REST request to get MedecinParm : {}", id);
        Optional<MedecinParmDTO> medecinParmDTO = medecinParmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medecinParmDTO);
    }

    /**
     * {@code DELETE  /medecin-parms/:id} : delete the "id" medecinParm.
     *
     * @param id the id of the medecinParmDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medecin-parms/{id}")
    public ResponseEntity<Void> deleteMedecinParm(@PathVariable Long id) {
        log.debug("REST request to delete MedecinParm : {}", id);
        medecinParmService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
