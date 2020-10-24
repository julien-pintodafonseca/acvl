package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.MedecinRegulateur;
import com.ensimag.acvl.repository.MedecinRegulateurRepository;
import com.ensimag.acvl.service.MedecinRegulateurService;
import com.ensimag.acvl.service.dto.MedecinRegulateurDTO;
import com.ensimag.acvl.service.mapper.MedecinRegulateurMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ensimag.acvl.domain.enumeration.TypeCentre;
import com.ensimag.acvl.domain.enumeration.Etat;
/**
 * Integration tests for the {@link MedecinRegulateurResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedecinRegulateurResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final TypeCentre DEFAULT_CENTRE = TypeCentre.SECOURS;
    private static final TypeCentre UPDATED_CENTRE = TypeCentre.SOIN;

    private static final Etat DEFAULT_ETAT = Etat.DISPO_BASE_OP;
    private static final Etat UPDATED_ETAT = Etat.DISPO_HORS_BASE_OP;

    private static final Boolean DEFAULT_EST_MOBILE = false;
    private static final Boolean UPDATED_EST_MOBILE = true;

    private static final Boolean DEFAULT_EST_FIXE = false;
    private static final Boolean UPDATED_EST_FIXE = true;

    @Autowired
    private MedecinRegulateurRepository medecinRegulateurRepository;

    @Autowired
    private MedecinRegulateurMapper medecinRegulateurMapper;

    @Autowired
    private MedecinRegulateurService medecinRegulateurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedecinRegulateurMockMvc;

    private MedecinRegulateur medecinRegulateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedecinRegulateur createEntity(EntityManager em) {
        MedecinRegulateur medecinRegulateur = new MedecinRegulateur()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return medecinRegulateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedecinRegulateur createUpdatedEntity(EntityManager em) {
        MedecinRegulateur medecinRegulateur = new MedecinRegulateur()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return medecinRegulateur;
    }

    @BeforeEach
    public void initTest() {
        medecinRegulateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedecinRegulateur() throws Exception {
        int databaseSizeBeforeCreate = medecinRegulateurRepository.findAll().size();
        // Create the MedecinRegulateur
        MedecinRegulateurDTO medecinRegulateurDTO = medecinRegulateurMapper.toDto(medecinRegulateur);
        restMedecinRegulateurMockMvc.perform(post("/api/medecin-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinRegulateurDTO)))
            .andExpect(status().isCreated());

        // Validate the MedecinRegulateur in the database
        List<MedecinRegulateur> medecinRegulateurList = medecinRegulateurRepository.findAll();
        assertThat(medecinRegulateurList).hasSize(databaseSizeBeforeCreate + 1);
        MedecinRegulateur testMedecinRegulateur = medecinRegulateurList.get(medecinRegulateurList.size() - 1);
        assertThat(testMedecinRegulateur.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMedecinRegulateur.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testMedecinRegulateur.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testMedecinRegulateur.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMedecinRegulateur.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testMedecinRegulateur.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testMedecinRegulateur.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testMedecinRegulateur.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createMedecinRegulateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medecinRegulateurRepository.findAll().size();

        // Create the MedecinRegulateur with an existing ID
        medecinRegulateur.setId(1L);
        MedecinRegulateurDTO medecinRegulateurDTO = medecinRegulateurMapper.toDto(medecinRegulateur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedecinRegulateurMockMvc.perform(post("/api/medecin-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinRegulateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedecinRegulateur in the database
        List<MedecinRegulateur> medecinRegulateurList = medecinRegulateurRepository.findAll();
        assertThat(medecinRegulateurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = medecinRegulateurRepository.findAll().size();
        // set the field null
        medecinRegulateur.setNom(null);

        // Create the MedecinRegulateur, which fails.
        MedecinRegulateurDTO medecinRegulateurDTO = medecinRegulateurMapper.toDto(medecinRegulateur);


        restMedecinRegulateurMockMvc.perform(post("/api/medecin-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinRegulateurDTO)))
            .andExpect(status().isBadRequest());

        List<MedecinRegulateur> medecinRegulateurList = medecinRegulateurRepository.findAll();
        assertThat(medecinRegulateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = medecinRegulateurRepository.findAll().size();
        // set the field null
        medecinRegulateur.setPrenom(null);

        // Create the MedecinRegulateur, which fails.
        MedecinRegulateurDTO medecinRegulateurDTO = medecinRegulateurMapper.toDto(medecinRegulateur);


        restMedecinRegulateurMockMvc.perform(post("/api/medecin-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinRegulateurDTO)))
            .andExpect(status().isBadRequest());

        List<MedecinRegulateur> medecinRegulateurList = medecinRegulateurRepository.findAll();
        assertThat(medecinRegulateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedecinRegulateurs() throws Exception {
        // Initialize the database
        medecinRegulateurRepository.saveAndFlush(medecinRegulateur);

        // Get all the medecinRegulateurList
        restMedecinRegulateurMockMvc.perform(get("/api/medecin-regulateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medecinRegulateur.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].centre").value(hasItem(DEFAULT_CENTRE.toString())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())))
            .andExpect(jsonPath("$.[*].estMobile").value(hasItem(DEFAULT_EST_MOBILE.booleanValue())))
            .andExpect(jsonPath("$.[*].estFixe").value(hasItem(DEFAULT_EST_FIXE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getMedecinRegulateur() throws Exception {
        // Initialize the database
        medecinRegulateurRepository.saveAndFlush(medecinRegulateur);

        // Get the medecinRegulateur
        restMedecinRegulateurMockMvc.perform(get("/api/medecin-regulateurs/{id}", medecinRegulateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medecinRegulateur.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.centre").value(DEFAULT_CENTRE.toString()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()))
            .andExpect(jsonPath("$.estMobile").value(DEFAULT_EST_MOBILE.booleanValue()))
            .andExpect(jsonPath("$.estFixe").value(DEFAULT_EST_FIXE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMedecinRegulateur() throws Exception {
        // Get the medecinRegulateur
        restMedecinRegulateurMockMvc.perform(get("/api/medecin-regulateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedecinRegulateur() throws Exception {
        // Initialize the database
        medecinRegulateurRepository.saveAndFlush(medecinRegulateur);

        int databaseSizeBeforeUpdate = medecinRegulateurRepository.findAll().size();

        // Update the medecinRegulateur
        MedecinRegulateur updatedMedecinRegulateur = medecinRegulateurRepository.findById(medecinRegulateur.getId()).get();
        // Disconnect from session so that the updates on updatedMedecinRegulateur are not directly saved in db
        em.detach(updatedMedecinRegulateur);
        updatedMedecinRegulateur
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        MedecinRegulateurDTO medecinRegulateurDTO = medecinRegulateurMapper.toDto(updatedMedecinRegulateur);

        restMedecinRegulateurMockMvc.perform(put("/api/medecin-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinRegulateurDTO)))
            .andExpect(status().isOk());

        // Validate the MedecinRegulateur in the database
        List<MedecinRegulateur> medecinRegulateurList = medecinRegulateurRepository.findAll();
        assertThat(medecinRegulateurList).hasSize(databaseSizeBeforeUpdate);
        MedecinRegulateur testMedecinRegulateur = medecinRegulateurList.get(medecinRegulateurList.size() - 1);
        assertThat(testMedecinRegulateur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMedecinRegulateur.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testMedecinRegulateur.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMedecinRegulateur.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMedecinRegulateur.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testMedecinRegulateur.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testMedecinRegulateur.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testMedecinRegulateur.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingMedecinRegulateur() throws Exception {
        int databaseSizeBeforeUpdate = medecinRegulateurRepository.findAll().size();

        // Create the MedecinRegulateur
        MedecinRegulateurDTO medecinRegulateurDTO = medecinRegulateurMapper.toDto(medecinRegulateur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedecinRegulateurMockMvc.perform(put("/api/medecin-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinRegulateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedecinRegulateur in the database
        List<MedecinRegulateur> medecinRegulateurList = medecinRegulateurRepository.findAll();
        assertThat(medecinRegulateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedecinRegulateur() throws Exception {
        // Initialize the database
        medecinRegulateurRepository.saveAndFlush(medecinRegulateur);

        int databaseSizeBeforeDelete = medecinRegulateurRepository.findAll().size();

        // Delete the medecinRegulateur
        restMedecinRegulateurMockMvc.perform(delete("/api/medecin-regulateurs/{id}", medecinRegulateur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedecinRegulateur> medecinRegulateurList = medecinRegulateurRepository.findAll();
        assertThat(medecinRegulateurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
