package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.MedecinFixeService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.MedecinFixeDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.MedecinFixe}.
 */
@RestController
@RequestMapping("/api")
public class MedecinFixeResource {

    private final Logger log = LoggerFactory.getLogger(MedecinFixeResource.class);

    private static final String ENTITY_NAME = "medecinFixe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedecinFixeService medecinFixeService;

    public MedecinFixeResource(MedecinFixeService medecinFixeService) {
        this.medecinFixeService = medecinFixeService;
    }

    /**
     * {@code POST  /medecin-fixes} : Create a new medecinFixe.
     *
     * @param medecinFixeDTO the medecinFixeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medecinFixeDTO, or with status {@code 400 (Bad Request)} if the medecinFixe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medecin-fixes")
    public ResponseEntity<MedecinFixeDTO> createMedecinFixe(@Valid @RequestBody MedecinFixeDTO medecinFixeDTO) throws URISyntaxException {
        log.debug("REST request to save MedecinFixe : {}", medecinFixeDTO);
        if (medecinFixeDTO.getId() != null) {
            throw new BadRequestAlertException("A new medecinFixe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedecinFixeDTO result = medecinFixeService.save(medecinFixeDTO);
        return ResponseEntity.created(new URI("/api/medecin-fixes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medecin-fixes} : Updates an existing medecinFixe.
     *
     * @param medecinFixeDTO the medecinFixeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medecinFixeDTO,
     * or with status {@code 400 (Bad Request)} if the medecinFixeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medecinFixeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medecin-fixes")
    public ResponseEntity<MedecinFixeDTO> updateMedecinFixe(@Valid @RequestBody MedecinFixeDTO medecinFixeDTO) throws URISyntaxException {
        log.debug("REST request to update MedecinFixe : {}", medecinFixeDTO);
        if (medecinFixeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedecinFixeDTO result = medecinFixeService.save(medecinFixeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, medecinFixeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medecin-fixes} : get all the medecinFixes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medecinFixes in body.
     */
    @GetMapping("/medecin-fixes")
    public List<MedecinFixeDTO> getAllMedecinFixes() {
        log.debug("REST request to get all MedecinFixes");
        return medecinFixeService.findAll();
    }

    /**
     * {@code GET  /medecin-fixes/:id} : get the "id" medecinFixe.
     *
     * @param id the id of the medecinFixeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medecinFixeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medecin-fixes/{id}")
    public ResponseEntity<MedecinFixeDTO> getMedecinFixe(@PathVariable Long id) {
        log.debug("REST request to get MedecinFixe : {}", id);
        Optional<MedecinFixeDTO> medecinFixeDTO = medecinFixeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medecinFixeDTO);
    }

    /**
     * {@code DELETE  /medecin-fixes/:id} : delete the "id" medecinFixe.
     *
     * @param id the id of the medecinFixeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medecin-fixes/{id}")
    public ResponseEntity<Void> deleteMedecinFixe(@PathVariable Long id) {
        log.debug("REST request to delete MedecinFixe : {}", id);
        medecinFixeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
