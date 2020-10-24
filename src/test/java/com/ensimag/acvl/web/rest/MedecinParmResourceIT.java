package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.MedecinParm;
import com.ensimag.acvl.repository.MedecinParmRepository;
import com.ensimag.acvl.service.MedecinParmService;
import com.ensimag.acvl.service.dto.MedecinParmDTO;
import com.ensimag.acvl.service.mapper.MedecinParmMapper;

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
 * Integration tests for the {@link MedecinParmResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedecinParmResourceIT {

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
    private MedecinParmRepository medecinParmRepository;

    @Autowired
    private MedecinParmMapper medecinParmMapper;

    @Autowired
    private MedecinParmService medecinParmService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedecinParmMockMvc;

    private MedecinParm medecinParm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedecinParm createEntity(EntityManager em) {
        MedecinParm medecinParm = new MedecinParm()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return medecinParm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedecinParm createUpdatedEntity(EntityManager em) {
        MedecinParm medecinParm = new MedecinParm()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return medecinParm;
    }

    @BeforeEach
    public void initTest() {
        medecinParm = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedecinParm() throws Exception {
        int databaseSizeBeforeCreate = medecinParmRepository.findAll().size();
        // Create the MedecinParm
        MedecinParmDTO medecinParmDTO = medecinParmMapper.toDto(medecinParm);
        restMedecinParmMockMvc.perform(post("/api/medecin-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinParmDTO)))
            .andExpect(status().isCreated());

        // Validate the MedecinParm in the database
        List<MedecinParm> medecinParmList = medecinParmRepository.findAll();
        assertThat(medecinParmList).hasSize(databaseSizeBeforeCreate + 1);
        MedecinParm testMedecinParm = medecinParmList.get(medecinParmList.size() - 1);
        assertThat(testMedecinParm.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMedecinParm.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testMedecinParm.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testMedecinParm.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMedecinParm.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testMedecinParm.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testMedecinParm.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testMedecinParm.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createMedecinParmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medecinParmRepository.findAll().size();

        // Create the MedecinParm with an existing ID
        medecinParm.setId(1L);
        MedecinParmDTO medecinParmDTO = medecinParmMapper.toDto(medecinParm);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedecinParmMockMvc.perform(post("/api/medecin-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinParmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedecinParm in the database
        List<MedecinParm> medecinParmList = medecinParmRepository.findAll();
        assertThat(medecinParmList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = medecinParmRepository.findAll().size();
        // set the field null
        medecinParm.setNom(null);

        // Create the MedecinParm, which fails.
        MedecinParmDTO medecinParmDTO = medecinParmMapper.toDto(medecinParm);


        restMedecinParmMockMvc.perform(post("/api/medecin-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinParmDTO)))
            .andExpect(status().isBadRequest());

        List<MedecinParm> medecinParmList = medecinParmRepository.findAll();
        assertThat(medecinParmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = medecinParmRepository.findAll().size();
        // set the field null
        medecinParm.setPrenom(null);

        // Create the MedecinParm, which fails.
        MedecinParmDTO medecinParmDTO = medecinParmMapper.toDto(medecinParm);


        restMedecinParmMockMvc.perform(post("/api/medecin-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinParmDTO)))
            .andExpect(status().isBadRequest());

        List<MedecinParm> medecinParmList = medecinParmRepository.findAll();
        assertThat(medecinParmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedecinParms() throws Exception {
        // Initialize the database
        medecinParmRepository.saveAndFlush(medecinParm);

        // Get all the medecinParmList
        restMedecinParmMockMvc.perform(get("/api/medecin-parms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medecinParm.getId().intValue())))
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
    public void getMedecinParm() throws Exception {
        // Initialize the database
        medecinParmRepository.saveAndFlush(medecinParm);

        // Get the medecinParm
        restMedecinParmMockMvc.perform(get("/api/medecin-parms/{id}", medecinParm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medecinParm.getId().intValue()))
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
    public void getNonExistingMedecinParm() throws Exception {
        // Get the medecinParm
        restMedecinParmMockMvc.perform(get("/api/medecin-parms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedecinParm() throws Exception {
        // Initialize the database
        medecinParmRepository.saveAndFlush(medecinParm);

        int databaseSizeBeforeUpdate = medecinParmRepository.findAll().size();

        // Update the medecinParm
        MedecinParm updatedMedecinParm = medecinParmRepository.findById(medecinParm.getId()).get();
        // Disconnect from session so that the updates on updatedMedecinParm are not directly saved in db
        em.detach(updatedMedecinParm);
        updatedMedecinParm
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        MedecinParmDTO medecinParmDTO = medecinParmMapper.toDto(updatedMedecinParm);

        restMedecinParmMockMvc.perform(put("/api/medecin-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinParmDTO)))
            .andExpect(status().isOk());

        // Validate the MedecinParm in the database
        List<MedecinParm> medecinParmList = medecinParmRepository.findAll();
        assertThat(medecinParmList).hasSize(databaseSizeBeforeUpdate);
        MedecinParm testMedecinParm = medecinParmList.get(medecinParmList.size() - 1);
        assertThat(testMedecinParm.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMedecinParm.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testMedecinParm.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMedecinParm.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMedecinParm.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testMedecinParm.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testMedecinParm.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testMedecinParm.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingMedecinParm() throws Exception {
        int databaseSizeBeforeUpdate = medecinParmRepository.findAll().size();

        // Create the MedecinParm
        MedecinParmDTO medecinParmDTO = medecinParmMapper.toDto(medecinParm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedecinParmMockMvc.perform(put("/api/medecin-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinParmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedecinParm in the database
        List<MedecinParm> medecinParmList = medecinParmRepository.findAll();
        assertThat(medecinParmList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedecinParm() throws Exception {
        // Initialize the database
        medecinParmRepository.saveAndFlush(medecinParm);

        int databaseSizeBeforeDelete = medecinParmRepository.findAll().size();

        // Delete the medecinParm
        restMedecinParmMockMvc.perform(delete("/api/medecin-parms/{id}", medecinParm.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedecinParm> medecinParmList = medecinParmRepository.findAll();
        assertThat(medecinParmList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
