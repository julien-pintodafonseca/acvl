package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.Infirmier;
import com.ensimag.acvl.repository.InfirmierRepository;
import com.ensimag.acvl.service.InfirmierService;
import com.ensimag.acvl.service.dto.InfirmierDTO;
import com.ensimag.acvl.service.mapper.InfirmierMapper;

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
 * Integration tests for the {@link InfirmierResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InfirmierResourceIT {

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
    private InfirmierRepository infirmierRepository;

    @Autowired
    private InfirmierMapper infirmierMapper;

    @Autowired
    private InfirmierService infirmierService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfirmierMockMvc;

    private Infirmier infirmier;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Infirmier createEntity(EntityManager em) {
        Infirmier infirmier = new Infirmier()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return infirmier;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Infirmier createUpdatedEntity(EntityManager em) {
        Infirmier infirmier = new Infirmier()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return infirmier;
    }

    @BeforeEach
    public void initTest() {
        infirmier = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfirmier() throws Exception {
        int databaseSizeBeforeCreate = infirmierRepository.findAll().size();
        // Create the Infirmier
        InfirmierDTO infirmierDTO = infirmierMapper.toDto(infirmier);
        restInfirmierMockMvc.perform(post("/api/infirmiers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infirmierDTO)))
            .andExpect(status().isCreated());

        // Validate the Infirmier in the database
        List<Infirmier> infirmierList = infirmierRepository.findAll();
        assertThat(infirmierList).hasSize(databaseSizeBeforeCreate + 1);
        Infirmier testInfirmier = infirmierList.get(infirmierList.size() - 1);
        assertThat(testInfirmier.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testInfirmier.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testInfirmier.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testInfirmier.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testInfirmier.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testInfirmier.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testInfirmier.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testInfirmier.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createInfirmierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = infirmierRepository.findAll().size();

        // Create the Infirmier with an existing ID
        infirmier.setId(1L);
        InfirmierDTO infirmierDTO = infirmierMapper.toDto(infirmier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfirmierMockMvc.perform(post("/api/infirmiers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infirmierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Infirmier in the database
        List<Infirmier> infirmierList = infirmierRepository.findAll();
        assertThat(infirmierList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = infirmierRepository.findAll().size();
        // set the field null
        infirmier.setNom(null);

        // Create the Infirmier, which fails.
        InfirmierDTO infirmierDTO = infirmierMapper.toDto(infirmier);


        restInfirmierMockMvc.perform(post("/api/infirmiers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infirmierDTO)))
            .andExpect(status().isBadRequest());

        List<Infirmier> infirmierList = infirmierRepository.findAll();
        assertThat(infirmierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = infirmierRepository.findAll().size();
        // set the field null
        infirmier.setPrenom(null);

        // Create the Infirmier, which fails.
        InfirmierDTO infirmierDTO = infirmierMapper.toDto(infirmier);


        restInfirmierMockMvc.perform(post("/api/infirmiers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infirmierDTO)))
            .andExpect(status().isBadRequest());

        List<Infirmier> infirmierList = infirmierRepository.findAll();
        assertThat(infirmierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInfirmiers() throws Exception {
        // Initialize the database
        infirmierRepository.saveAndFlush(infirmier);

        // Get all the infirmierList
        restInfirmierMockMvc.perform(get("/api/infirmiers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(infirmier.getId().intValue())))
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
    public void getInfirmier() throws Exception {
        // Initialize the database
        infirmierRepository.saveAndFlush(infirmier);

        // Get the infirmier
        restInfirmierMockMvc.perform(get("/api/infirmiers/{id}", infirmier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(infirmier.getId().intValue()))
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
    public void getNonExistingInfirmier() throws Exception {
        // Get the infirmier
        restInfirmierMockMvc.perform(get("/api/infirmiers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfirmier() throws Exception {
        // Initialize the database
        infirmierRepository.saveAndFlush(infirmier);

        int databaseSizeBeforeUpdate = infirmierRepository.findAll().size();

        // Update the infirmier
        Infirmier updatedInfirmier = infirmierRepository.findById(infirmier.getId()).get();
        // Disconnect from session so that the updates on updatedInfirmier are not directly saved in db
        em.detach(updatedInfirmier);
        updatedInfirmier
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        InfirmierDTO infirmierDTO = infirmierMapper.toDto(updatedInfirmier);

        restInfirmierMockMvc.perform(put("/api/infirmiers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infirmierDTO)))
            .andExpect(status().isOk());

        // Validate the Infirmier in the database
        List<Infirmier> infirmierList = infirmierRepository.findAll();
        assertThat(infirmierList).hasSize(databaseSizeBeforeUpdate);
        Infirmier testInfirmier = infirmierList.get(infirmierList.size() - 1);
        assertThat(testInfirmier.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testInfirmier.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testInfirmier.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testInfirmier.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testInfirmier.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testInfirmier.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testInfirmier.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testInfirmier.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingInfirmier() throws Exception {
        int databaseSizeBeforeUpdate = infirmierRepository.findAll().size();

        // Create the Infirmier
        InfirmierDTO infirmierDTO = infirmierMapper.toDto(infirmier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfirmierMockMvc.perform(put("/api/infirmiers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infirmierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Infirmier in the database
        List<Infirmier> infirmierList = infirmierRepository.findAll();
        assertThat(infirmierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInfirmier() throws Exception {
        // Initialize the database
        infirmierRepository.saveAndFlush(infirmier);

        int databaseSizeBeforeDelete = infirmierRepository.findAll().size();

        // Delete the infirmier
        restInfirmierMockMvc.perform(delete("/api/infirmiers/{id}", infirmier.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Infirmier> infirmierList = infirmierRepository.findAll();
        assertThat(infirmierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
