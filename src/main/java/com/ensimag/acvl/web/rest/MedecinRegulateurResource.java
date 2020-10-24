package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.MedecinRegulateurService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.MedecinRegulateurDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.MedecinRegulateur}.
 */
@RestController
@RequestMapping("/api")
public class MedecinRegulateurResource {

    private final Logger log = LoggerFactory.getLogger(MedecinRegulateurResource.class);

    private static final String ENTITY_NAME = "medecinRegulateur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedecinRegulateurService medecinRegulateurService;

    public MedecinRegulateurResource(MedecinRegulateurService medecinRegulateurService) {
        this.medecinRegulateurService = medecinRegulateurService;
    }

    /**
     * {@code POST  /medecin-regulateurs} : Create a new medecinRegulateur.
     *
     * @param medecinRegulateurDTO the medecinRegulateurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medecinRegulateurDTO, or with status {@code 400 (Bad Request)} if the medecinRegulateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medecin-regulateurs")
    public ResponseEntity<MedecinRegulateurDTO> createMedecinRegulateur(@Valid @RequestBody MedecinRegulateurDTO medecinRegulateurDTO) throws URISyntaxException {
        log.debug("REST request to save MedecinRegulateur : {}", medecinRegulateurDTO);
        if (medecinRegulateurDTO.getId() != null) {
            throw new BadRequestAlertException("A new medecinRegulateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedecinRegulateurDTO result = medecinRegulateurService.save(medecinRegulateurDTO);
        return ResponseEntity.created(new URI("/api/medecin-regulateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medecin-regulateurs} : Updates an existing medecinRegulateur.
     *
     * @param medecinRegulateurDTO the medecinRegulateurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medecinRegulateurDTO,
     * or with status {@code 400 (Bad Request)} if the medecinRegulateurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medecinRegulateurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medecin-regulateurs")
    public ResponseEntity<MedecinRegulateurDTO> updateMedecinRegulateur(@Valid @RequestBody MedecinRegulateurDTO medecinRegulateurDTO) throws URISyntaxException {
        log.debug("REST request to update MedecinRegulateur : {}", medecinRegulateurDTO);
        if (medecinRegulateurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedecinRegulateurDTO result = medecinRegulateurService.save(medecinRegulateurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, medecinRegulateurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medecin-regulateurs} : get all the medecinRegulateurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medecinRegulateurs in body.
     */
    @GetMapping("/medecin-regulateurs")
    public List<MedecinRegulateurDTO> getAllMedecinRegulateurs() {
        log.debug("REST request to get all MedecinRegulateurs");
        return medecinRegulateurService.findAll();
    }

    /**
     * {@code GET  /medecin-regulateurs/:id} : get the "id" medecinRegulateur.
     *
     * @param id the id of the medecinRegulateurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medecinRegulateurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medecin-regulateurs/{id}")
    public ResponseEntity<MedecinRegulateurDTO> getMedecinRegulateur(@PathVariable Long id) {
        log.debug("REST request to get MedecinRegulateur : {}", id);
        Optional<MedecinRegulateurDTO> medecinRegulateurDTO = medecinRegulateurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medecinRegulateurDTO);
    }

    /**
     * {@code DELETE  /medecin-regulateurs/:id} : delete the "id" medecinRegulateur.
     *
     * @param id the id of the medecinRegulateurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medecin-regulateurs/{id}")
    public ResponseEntity<Void> deleteMedecinRegulateur(@PathVariable Long id) {
        log.debug("REST request to delete MedecinRegulateur : {}", id);
        medecinRegulateurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
