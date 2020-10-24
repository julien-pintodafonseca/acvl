package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.service.MedecinMobileService;
import com.ensimag.acvl.web.rest.errors.BadRequestAlertException;
import com.ensimag.acvl.service.dto.MedecinMobileDTO;

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
 * REST controller for managing {@link com.ensimag.acvl.domain.MedecinMobile}.
 */
@RestController
@RequestMapping("/api")
public class MedecinMobileResource {

    private final Logger log = LoggerFactory.getLogger(MedecinMobileResource.class);

    private static final String ENTITY_NAME = "medecinMobile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedecinMobileService medecinMobileService;

    public MedecinMobileResource(MedecinMobileService medecinMobileService) {
        this.medecinMobileService = medecinMobileService;
    }

    /**
     * {@code POST  /medecin-mobiles} : Create a new medecinMobile.
     *
     * @param medecinMobileDTO the medecinMobileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medecinMobileDTO, or with status {@code 400 (Bad Request)} if the medecinMobile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medecin-mobiles")
    public ResponseEntity<MedecinMobileDTO> createMedecinMobile(@Valid @RequestBody MedecinMobileDTO medecinMobileDTO) throws URISyntaxException {
        log.debug("REST request to save MedecinMobile : {}", medecinMobileDTO);
        if (medecinMobileDTO.getId() != null) {
            throw new BadRequestAlertException("A new medecinMobile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedecinMobileDTO result = medecinMobileService.save(medecinMobileDTO);
        return ResponseEntity.created(new URI("/api/medecin-mobiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medecin-mobiles} : Updates an existing medecinMobile.
     *
     * @param medecinMobileDTO the medecinMobileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medecinMobileDTO,
     * or with status {@code 400 (Bad Request)} if the medecinMobileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medecinMobileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medecin-mobiles")
    public ResponseEntity<MedecinMobileDTO> updateMedecinMobile(@Valid @RequestBody MedecinMobileDTO medecinMobileDTO) throws URISyntaxException {
        log.debug("REST request to update MedecinMobile : {}", medecinMobileDTO);
        if (medecinMobileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedecinMobileDTO result = medecinMobileService.save(medecinMobileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, medecinMobileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medecin-mobiles} : get all the medecinMobiles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medecinMobiles in body.
     */
    @GetMapping("/medecin-mobiles")
    public List<MedecinMobileDTO> getAllMedecinMobiles() {
        log.debug("REST request to get all MedecinMobiles");
        return medecinMobileService.findAll();
    }

    /**
     * {@code GET  /medecin-mobiles/:id} : get the "id" medecinMobile.
     *
     * @param id the id of the medecinMobileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medecinMobileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medecin-mobiles/{id}")
    public ResponseEntity<MedecinMobileDTO> getMedecinMobile(@PathVariable Long id) {
        log.debug("REST request to get MedecinMobile : {}", id);
        Optional<MedecinMobileDTO> medecinMobileDTO = medecinMobileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medecinMobileDTO);
    }

    /**
     * {@code DELETE  /medecin-mobiles/:id} : delete the "id" medecinMobile.
     *
     * @param id the id of the medecinMobileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medecin-mobiles/{id}")
    public ResponseEntity<Void> deleteMedecinMobile(@PathVariable Long id) {
        log.debug("REST request to delete MedecinMobile : {}", id);
        medecinMobileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
