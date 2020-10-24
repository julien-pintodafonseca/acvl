package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.MdcMobile;
import com.ensimag.acvl.repository.MdcMobileRepository;
import com.ensimag.acvl.service.MdcMobileService;
import com.ensimag.acvl.service.dto.MdcMobileDTO;
import com.ensimag.acvl.service.mapper.MdcMobileMapper;

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
 * Integration tests for the {@link MdcMobileResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MdcMobileResourceIT {

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
    private MdcMobileRepository mdcMobileRepository;

    @Autowired
    private MdcMobileMapper mdcMobileMapper;

    @Autowired
    private MdcMobileService mdcMobileService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMdcMobileMockMvc;

    private MdcMobile mdcMobile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MdcMobile createEntity(EntityManager em) {
        MdcMobile mdcMobile = new MdcMobile()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return mdcMobile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MdcMobile createUpdatedEntity(EntityManager em) {
        MdcMobile mdcMobile = new MdcMobile()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return mdcMobile;
    }

    @BeforeEach
    public void initTest() {
        mdcMobile = createEntity(em);
    }

    @Test
    @Transactional
    public void createMdcMobile() throws Exception {
        int databaseSizeBeforeCreate = mdcMobileRepository.findAll().size();
        // Create the MdcMobile
        MdcMobileDTO mdcMobileDTO = mdcMobileMapper.toDto(mdcMobile);
        restMdcMobileMockMvc.perform(post("/api/mdc-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcMobileDTO)))
            .andExpect(status().isCreated());

        // Validate the MdcMobile in the database
        List<MdcMobile> mdcMobileList = mdcMobileRepository.findAll();
        assertThat(mdcMobileList).hasSize(databaseSizeBeforeCreate + 1);
        MdcMobile testMdcMobile = mdcMobileList.get(mdcMobileList.size() - 1);
        assertThat(testMdcMobile.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMdcMobile.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testMdcMobile.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testMdcMobile.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMdcMobile.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testMdcMobile.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testMdcMobile.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testMdcMobile.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createMdcMobileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mdcMobileRepository.findAll().size();

        // Create the MdcMobile with an existing ID
        mdcMobile.setId(1L);
        MdcMobileDTO mdcMobileDTO = mdcMobileMapper.toDto(mdcMobile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMdcMobileMockMvc.perform(post("/api/mdc-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcMobileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MdcMobile in the database
        List<MdcMobile> mdcMobileList = mdcMobileRepository.findAll();
        assertThat(mdcMobileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = mdcMobileRepository.findAll().size();
        // set the field null
        mdcMobile.setNom(null);

        // Create the MdcMobile, which fails.
        MdcMobileDTO mdcMobileDTO = mdcMobileMapper.toDto(mdcMobile);


        restMdcMobileMockMvc.perform(post("/api/mdc-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcMobileDTO)))
            .andExpect(status().isBadRequest());

        List<MdcMobile> mdcMobileList = mdcMobileRepository.findAll();
        assertThat(mdcMobileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = mdcMobileRepository.findAll().size();
        // set the field null
        mdcMobile.setPrenom(null);

        // Create the MdcMobile, which fails.
        MdcMobileDTO mdcMobileDTO = mdcMobileMapper.toDto(mdcMobile);


        restMdcMobileMockMvc.perform(post("/api/mdc-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcMobileDTO)))
            .andExpect(status().isBadRequest());

        List<MdcMobile> mdcMobileList = mdcMobileRepository.findAll();
        assertThat(mdcMobileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMdcMobiles() throws Exception {
        // Initialize the database
        mdcMobileRepository.saveAndFlush(mdcMobile);

        // Get all the mdcMobileList
        restMdcMobileMockMvc.perform(get("/api/mdc-mobiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mdcMobile.getId().intValue())))
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
    public void getMdcMobile() throws Exception {
        // Initialize the database
        mdcMobileRepository.saveAndFlush(mdcMobile);

        // Get the mdcMobile
        restMdcMobileMockMvc.perform(get("/api/mdc-mobiles/{id}", mdcMobile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mdcMobile.getId().intValue()))
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
    public void getNonExistingMdcMobile() throws Exception {
        // Get the mdcMobile
        restMdcMobileMockMvc.perform(get("/api/mdc-mobiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMdcMobile() throws Exception {
        // Initialize the database
        mdcMobileRepository.saveAndFlush(mdcMobile);

        int databaseSizeBeforeUpdate = mdcMobileRepository.findAll().size();

        // Update the mdcMobile
        MdcMobile updatedMdcMobile = mdcMobileRepository.findById(mdcMobile.getId()).get();
        // Disconnect from session so that the updates on updatedMdcMobile are not directly saved in db
        em.detach(updatedMdcMobile);
        updatedMdcMobile
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        MdcMobileDTO mdcMobileDTO = mdcMobileMapper.toDto(updatedMdcMobile);

        restMdcMobileMockMvc.perform(put("/api/mdc-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcMobileDTO)))
            .andExpect(status().isOk());

        // Validate the MdcMobile in the database
        List<MdcMobile> mdcMobileList = mdcMobileRepository.findAll();
        assertThat(mdcMobileList).hasSize(databaseSizeBeforeUpdate);
        MdcMobile testMdcMobile = mdcMobileList.get(mdcMobileList.size() - 1);
        assertThat(testMdcMobile.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMdcMobile.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testMdcMobile.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMdcMobile.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMdcMobile.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testMdcMobile.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testMdcMobile.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testMdcMobile.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingMdcMobile() throws Exception {
        int databaseSizeBeforeUpdate = mdcMobileRepository.findAll().size();

        // Create the MdcMobile
        MdcMobileDTO mdcMobileDTO = mdcMobileMapper.toDto(mdcMobile);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMdcMobileMockMvc.perform(put("/api/mdc-mobiles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcMobileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MdcMobile in the database
        List<MdcMobile> mdcMobileList = mdcMobileRepository.findAll();
        assertThat(mdcMobileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMdcMobile() throws Exception {
        // Initialize the database
        mdcMobileRepository.saveAndFlush(mdcMobile);

        int databaseSizeBeforeDelete = mdcMobileRepository.findAll().size();

        // Delete the mdcMobile
        restMdcMobileMockMvc.perform(delete("/api/mdc-mobiles/{id}", mdcMobile.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MdcMobile> mdcMobileList = mdcMobileRepository.findAll();
        assertThat(mdcMobileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
