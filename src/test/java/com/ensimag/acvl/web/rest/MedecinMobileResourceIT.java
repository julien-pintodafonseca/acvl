package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.MedecinMobile;
import com.ensimag.acvl.repository.MedecinMobileRepository;
import com.ensimag.acvl.service.MedecinMobileService;
import com.ensimag.acvl.service.dto.MedecinMobileDTO;
import com.ensimag.acvl.service.mapper.MedecinMobileMapper;

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
 * Integration tests for the {@link MedecinMobileResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedecinMobileResourceIT {

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
    private MedecinMobileRepository medecinMobileRepository;

    @Autowired
    private MedecinMobileMapper medecinMobileMapper;

    @Autowired
    private MedecinMobileService medecinMobileService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedecinMobileMockMvc;

    private MedecinMobile medecinMobile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedecinMobile createEntity(EntityManager em) {
        MedecinMobile medecinMobile = new MedecinMobile()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return medecinMobile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedecinMobile createUpdatedEntity(EntityManager em) {
        MedecinMobile medecinMobile = new MedecinMobile()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return medecinMobile;
    }

    @BeforeEach
    public void initTest() {
        medecinMobile = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedecinMobile() throws Exception {
        int databaseSizeBeforeCreate = medecinMobileRepository.findAll().size();
        // Create the MedecinMobile
        MedecinMobileDTO medecinMobileDTO = medecinMobileMapper.toDto(medecinMobile);
        restMedecinMobileMockMvc.perform(post("/api/medecin-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinMobileDTO)))
            .andExpect(status().isCreated());

        // Validate the MedecinMobile in the database
        List<MedecinMobile> medecinMobileList = medecinMobileRepository.findAll();
        assertThat(medecinMobileList).hasSize(databaseSizeBeforeCreate + 1);
        MedecinMobile testMedecinMobile = medecinMobileList.get(medecinMobileList.size() - 1);
        assertThat(testMedecinMobile.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMedecinMobile.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testMedecinMobile.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testMedecinMobile.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMedecinMobile.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testMedecinMobile.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testMedecinMobile.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testMedecinMobile.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createMedecinMobileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medecinMobileRepository.findAll().size();

        // Create the MedecinMobile with an existing ID
        medecinMobile.setId(1L);
        MedecinMobileDTO medecinMobileDTO = medecinMobileMapper.toDto(medecinMobile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedecinMobileMockMvc.perform(post("/api/medecin-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinMobileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedecinMobile in the database
        List<MedecinMobile> medecinMobileList = medecinMobileRepository.findAll();
        assertThat(medecinMobileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = medecinMobileRepository.findAll().size();
        // set the field null
        medecinMobile.setNom(null);

        // Create the MedecinMobile, which fails.
        MedecinMobileDTO medecinMobileDTO = medecinMobileMapper.toDto(medecinMobile);


        restMedecinMobileMockMvc.perform(post("/api/medecin-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinMobileDTO)))
            .andExpect(status().isBadRequest());

        List<MedecinMobile> medecinMobileList = medecinMobileRepository.findAll();
        assertThat(medecinMobileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = medecinMobileRepository.findAll().size();
        // set the field null
        medecinMobile.setPrenom(null);

        // Create the MedecinMobile, which fails.
        MedecinMobileDTO medecinMobileDTO = medecinMobileMapper.toDto(medecinMobile);


        restMedecinMobileMockMvc.perform(post("/api/medecin-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinMobileDTO)))
            .andExpect(status().isBadRequest());

        List<MedecinMobile> medecinMobileList = medecinMobileRepository.findAll();
        assertThat(medecinMobileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedecinMobiles() throws Exception {
        // Initialize the database
        medecinMobileRepository.saveAndFlush(medecinMobile);

        // Get all the medecinMobileList
        restMedecinMobileMockMvc.perform(get("/api/medecin-mobiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medecinMobile.getId().intValue())))
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
    public void getMedecinMobile() throws Exception {
        // Initialize the database
        medecinMobileRepository.saveAndFlush(medecinMobile);

        // Get the medecinMobile
        restMedecinMobileMockMvc.perform(get("/api/medecin-mobiles/{id}", medecinMobile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medecinMobile.getId().intValue()))
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
    public void getNonExistingMedecinMobile() throws Exception {
        // Get the medecinMobile
        restMedecinMobileMockMvc.perform(get("/api/medecin-mobiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedecinMobile() throws Exception {
        // Initialize the database
        medecinMobileRepository.saveAndFlush(medecinMobile);

        int databaseSizeBeforeUpdate = medecinMobileRepository.findAll().size();

        // Update the medecinMobile
        MedecinMobile updatedMedecinMobile = medecinMobileRepository.findById(medecinMobile.getId()).get();
        // Disconnect from session so that the updates on updatedMedecinMobile are not directly saved in db
        em.detach(updatedMedecinMobile);
        updatedMedecinMobile
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        MedecinMobileDTO medecinMobileDTO = medecinMobileMapper.toDto(updatedMedecinMobile);

        restMedecinMobileMockMvc.perform(put("/api/medecin-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinMobileDTO)))
            .andExpect(status().isOk());

        // Validate the MedecinMobile in the database
        List<MedecinMobile> medecinMobileList = medecinMobileRepository.findAll();
        assertThat(medecinMobileList).hasSize(databaseSizeBeforeUpdate);
        MedecinMobile testMedecinMobile = medecinMobileList.get(medecinMobileList.size() - 1);
        assertThat(testMedecinMobile.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMedecinMobile.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testMedecinMobile.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMedecinMobile.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMedecinMobile.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testMedecinMobile.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testMedecinMobile.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testMedecinMobile.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingMedecinMobile() throws Exception {
        int databaseSizeBeforeUpdate = medecinMobileRepository.findAll().size();

        // Create the MedecinMobile
        MedecinMobileDTO medecinMobileDTO = medecinMobileMapper.toDto(medecinMobile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedecinMobileMockMvc.perform(put("/api/medecin-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinMobileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedecinMobile in the database
        List<MedecinMobile> medecinMobileList = medecinMobileRepository.findAll();
        assertThat(medecinMobileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedecinMobile() throws Exception {
        // Initialize the database
        medecinMobileRepository.saveAndFlush(medecinMobile);

        int databaseSizeBeforeDelete = medecinMobileRepository.findAll().size();

        // Delete the medecinMobile
        restMedecinMobileMockMvc.perform(delete("/api/medecin-mobiles/{id}", medecinMobile.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedecinMobile> medecinMobileList = medecinMobileRepository.findAll();
        assertThat(medecinMobileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
