package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.MedecinFixe;
import com.ensimag.acvl.repository.MedecinFixeRepository;
import com.ensimag.acvl.service.MedecinFixeService;
import com.ensimag.acvl.service.dto.MedecinFixeDTO;
import com.ensimag.acvl.service.mapper.MedecinFixeMapper;

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
 * Integration tests for the {@link MedecinFixeResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedecinFixeResourceIT {

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
    private MedecinFixeRepository medecinFixeRepository;

    @Autowired
    private MedecinFixeMapper medecinFixeMapper;

    @Autowired
    private MedecinFixeService medecinFixeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedecinFixeMockMvc;

    private MedecinFixe medecinFixe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedecinFixe createEntity(EntityManager em) {
        MedecinFixe medecinFixe = new MedecinFixe()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return medecinFixe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedecinFixe createUpdatedEntity(EntityManager em) {
        MedecinFixe medecinFixe = new MedecinFixe()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return medecinFixe;
    }

    @BeforeEach
    public void initTest() {
        medecinFixe = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedecinFixe() throws Exception {
        int databaseSizeBeforeCreate = medecinFixeRepository.findAll().size();
        // Create the MedecinFixe
        MedecinFixeDTO medecinFixeDTO = medecinFixeMapper.toDto(medecinFixe);
        restMedecinFixeMockMvc.perform(post("/api/medecin-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinFixeDTO)))
            .andExpect(status().isCreated());

        // Validate the MedecinFixe in the database
        List<MedecinFixe> medecinFixeList = medecinFixeRepository.findAll();
        assertThat(medecinFixeList).hasSize(databaseSizeBeforeCreate + 1);
        MedecinFixe testMedecinFixe = medecinFixeList.get(medecinFixeList.size() - 1);
        assertThat(testMedecinFixe.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMedecinFixe.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testMedecinFixe.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testMedecinFixe.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMedecinFixe.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testMedecinFixe.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testMedecinFixe.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testMedecinFixe.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createMedecinFixeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medecinFixeRepository.findAll().size();

        // Create the MedecinFixe with an existing ID
        medecinFixe.setId(1L);
        MedecinFixeDTO medecinFixeDTO = medecinFixeMapper.toDto(medecinFixe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedecinFixeMockMvc.perform(post("/api/medecin-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinFixeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedecinFixe in the database
        List<MedecinFixe> medecinFixeList = medecinFixeRepository.findAll();
        assertThat(medecinFixeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = medecinFixeRepository.findAll().size();
        // set the field null
        medecinFixe.setNom(null);

        // Create the MedecinFixe, which fails.
        MedecinFixeDTO medecinFixeDTO = medecinFixeMapper.toDto(medecinFixe);


        restMedecinFixeMockMvc.perform(post("/api/medecin-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinFixeDTO)))
            .andExpect(status().isBadRequest());

        List<MedecinFixe> medecinFixeList = medecinFixeRepository.findAll();
        assertThat(medecinFixeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = medecinFixeRepository.findAll().size();
        // set the field null
        medecinFixe.setPrenom(null);

        // Create the MedecinFixe, which fails.
        MedecinFixeDTO medecinFixeDTO = medecinFixeMapper.toDto(medecinFixe);


        restMedecinFixeMockMvc.perform(post("/api/medecin-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinFixeDTO)))
            .andExpect(status().isBadRequest());

        List<MedecinFixe> medecinFixeList = medecinFixeRepository.findAll();
        assertThat(medecinFixeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedecinFixes() throws Exception {
        // Initialize the database
        medecinFixeRepository.saveAndFlush(medecinFixe);

        // Get all the medecinFixeList
        restMedecinFixeMockMvc.perform(get("/api/medecin-fixes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medecinFixe.getId().intValue())))
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
    public void getMedecinFixe() throws Exception {
        // Initialize the database
        medecinFixeRepository.saveAndFlush(medecinFixe);

        // Get the medecinFixe
        restMedecinFixeMockMvc.perform(get("/api/medecin-fixes/{id}", medecinFixe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medecinFixe.getId().intValue()))
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
    public void getNonExistingMedecinFixe() throws Exception {
        // Get the medecinFixe
        restMedecinFixeMockMvc.perform(get("/api/medecin-fixes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedecinFixe() throws Exception {
        // Initialize the database
        medecinFixeRepository.saveAndFlush(medecinFixe);

        int databaseSizeBeforeUpdate = medecinFixeRepository.findAll().size();

        // Update the medecinFixe
        MedecinFixe updatedMedecinFixe = medecinFixeRepository.findById(medecinFixe.getId()).get();
        // Disconnect from session so that the updates on updatedMedecinFixe are not directly saved in db
        em.detach(updatedMedecinFixe);
        updatedMedecinFixe
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        MedecinFixeDTO medecinFixeDTO = medecinFixeMapper.toDto(updatedMedecinFixe);

        restMedecinFixeMockMvc.perform(put("/api/medecin-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinFixeDTO)))
            .andExpect(status().isOk());

        // Validate the MedecinFixe in the database
        List<MedecinFixe> medecinFixeList = medecinFixeRepository.findAll();
        assertThat(medecinFixeList).hasSize(databaseSizeBeforeUpdate);
        MedecinFixe testMedecinFixe = medecinFixeList.get(medecinFixeList.size() - 1);
        assertThat(testMedecinFixe.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMedecinFixe.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testMedecinFixe.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMedecinFixe.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMedecinFixe.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testMedecinFixe.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testMedecinFixe.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testMedecinFixe.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingMedecinFixe() throws Exception {
        int databaseSizeBeforeUpdate = medecinFixeRepository.findAll().size();

        // Create the MedecinFixe
        MedecinFixeDTO medecinFixeDTO = medecinFixeMapper.toDto(medecinFixe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedecinFixeMockMvc.perform(put("/api/medecin-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medecinFixeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedecinFixe in the database
        List<MedecinFixe> medecinFixeList = medecinFixeRepository.findAll();
        assertThat(medecinFixeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedecinFixe() throws Exception {
        // Initialize the database
        medecinFixeRepository.saveAndFlush(medecinFixe);

        int databaseSizeBeforeDelete = medecinFixeRepository.findAll().size();

        // Delete the medecinFixe
        restMedecinFixeMockMvc.perform(delete("/api/medecin-fixes/{id}", medecinFixe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedecinFixe> medecinFixeList = medecinFixeRepository.findAll();
        assertThat(medecinFixeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
