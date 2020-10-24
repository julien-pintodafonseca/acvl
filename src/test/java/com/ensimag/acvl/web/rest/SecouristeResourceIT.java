package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.Secouriste;
import com.ensimag.acvl.repository.SecouristeRepository;
import com.ensimag.acvl.service.SecouristeService;
import com.ensimag.acvl.service.dto.SecouristeDTO;
import com.ensimag.acvl.service.mapper.SecouristeMapper;

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
 * Integration tests for the {@link SecouristeResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SecouristeResourceIT {

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

    private static final Boolean DEFAULT_EST_AMBULANCIER = false;
    private static final Boolean UPDATED_EST_AMBULANCIER = true;

    @Autowired
    private SecouristeRepository secouristeRepository;

    @Autowired
    private SecouristeMapper secouristeMapper;

    @Autowired
    private SecouristeService secouristeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecouristeMockMvc;

    private Secouriste secouriste;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secouriste createEntity(EntityManager em) {
        Secouriste secouriste = new Secouriste()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE)
            .estAmbulancier(DEFAULT_EST_AMBULANCIER);
        return secouriste;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secouriste createUpdatedEntity(EntityManager em) {
        Secouriste secouriste = new Secouriste()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE)
            .estAmbulancier(UPDATED_EST_AMBULANCIER);
        return secouriste;
    }

    @BeforeEach
    public void initTest() {
        secouriste = createEntity(em);
    }

    @Test
    @Transactional
    public void createSecouriste() throws Exception {
        int databaseSizeBeforeCreate = secouristeRepository.findAll().size();
        // Create the Secouriste
        SecouristeDTO secouristeDTO = secouristeMapper.toDto(secouriste);
        restSecouristeMockMvc.perform(post("/api/secouristes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secouristeDTO)))
            .andExpect(status().isCreated());

        // Validate the Secouriste in the database
        List<Secouriste> secouristeList = secouristeRepository.findAll();
        assertThat(secouristeList).hasSize(databaseSizeBeforeCreate + 1);
        Secouriste testSecouriste = secouristeList.get(secouristeList.size() - 1);
        assertThat(testSecouriste.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testSecouriste.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testSecouriste.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testSecouriste.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testSecouriste.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testSecouriste.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testSecouriste.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testSecouriste.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
        assertThat(testSecouriste.isEstAmbulancier()).isEqualTo(DEFAULT_EST_AMBULANCIER);
    }

    @Test
    @Transactional
    public void createSecouristeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = secouristeRepository.findAll().size();

        // Create the Secouriste with an existing ID
        secouriste.setId(1L);
        SecouristeDTO secouristeDTO = secouristeMapper.toDto(secouriste);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecouristeMockMvc.perform(post("/api/secouristes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secouristeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Secouriste in the database
        List<Secouriste> secouristeList = secouristeRepository.findAll();
        assertThat(secouristeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = secouristeRepository.findAll().size();
        // set the field null
        secouriste.setNom(null);

        // Create the Secouriste, which fails.
        SecouristeDTO secouristeDTO = secouristeMapper.toDto(secouriste);


        restSecouristeMockMvc.perform(post("/api/secouristes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secouristeDTO)))
            .andExpect(status().isBadRequest());

        List<Secouriste> secouristeList = secouristeRepository.findAll();
        assertThat(secouristeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = secouristeRepository.findAll().size();
        // set the field null
        secouriste.setPrenom(null);

        // Create the Secouriste, which fails.
        SecouristeDTO secouristeDTO = secouristeMapper.toDto(secouriste);


        restSecouristeMockMvc.perform(post("/api/secouristes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secouristeDTO)))
            .andExpect(status().isBadRequest());

        List<Secouriste> secouristeList = secouristeRepository.findAll();
        assertThat(secouristeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSecouristes() throws Exception {
        // Initialize the database
        secouristeRepository.saveAndFlush(secouriste);

        // Get all the secouristeList
        restSecouristeMockMvc.perform(get("/api/secouristes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secouriste.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].centre").value(hasItem(DEFAULT_CENTRE.toString())))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())))
            .andExpect(jsonPath("$.[*].estMobile").value(hasItem(DEFAULT_EST_MOBILE.booleanValue())))
            .andExpect(jsonPath("$.[*].estFixe").value(hasItem(DEFAULT_EST_FIXE.booleanValue())))
            .andExpect(jsonPath("$.[*].estAmbulancier").value(hasItem(DEFAULT_EST_AMBULANCIER.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSecouriste() throws Exception {
        // Initialize the database
        secouristeRepository.saveAndFlush(secouriste);

        // Get the secouriste
        restSecouristeMockMvc.perform(get("/api/secouristes/{id}", secouriste.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(secouriste.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.centre").value(DEFAULT_CENTRE.toString()))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()))
            .andExpect(jsonPath("$.estMobile").value(DEFAULT_EST_MOBILE.booleanValue()))
            .andExpect(jsonPath("$.estFixe").value(DEFAULT_EST_FIXE.booleanValue()))
            .andExpect(jsonPath("$.estAmbulancier").value(DEFAULT_EST_AMBULANCIER.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSecouriste() throws Exception {
        // Get the secouriste
        restSecouristeMockMvc.perform(get("/api/secouristes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSecouriste() throws Exception {
        // Initialize the database
        secouristeRepository.saveAndFlush(secouriste);

        int databaseSizeBeforeUpdate = secouristeRepository.findAll().size();

        // Update the secouriste
        Secouriste updatedSecouriste = secouristeRepository.findById(secouriste.getId()).get();
        // Disconnect from session so that the updates on updatedSecouriste are not directly saved in db
        em.detach(updatedSecouriste);
        updatedSecouriste
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE)
            .estAmbulancier(UPDATED_EST_AMBULANCIER);
        SecouristeDTO secouristeDTO = secouristeMapper.toDto(updatedSecouriste);

        restSecouristeMockMvc.perform(put("/api/secouristes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secouristeDTO)))
            .andExpect(status().isOk());

        // Validate the Secouriste in the database
        List<Secouriste> secouristeList = secouristeRepository.findAll();
        assertThat(secouristeList).hasSize(databaseSizeBeforeUpdate);
        Secouriste testSecouriste = secouristeList.get(secouristeList.size() - 1);
        assertThat(testSecouriste.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testSecouriste.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testSecouriste.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testSecouriste.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testSecouriste.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testSecouriste.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testSecouriste.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testSecouriste.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
        assertThat(testSecouriste.isEstAmbulancier()).isEqualTo(UPDATED_EST_AMBULANCIER);
    }

    @Test
    @Transactional
    public void updateNonExistingSecouriste() throws Exception {
        int databaseSizeBeforeUpdate = secouristeRepository.findAll().size();

        // Create the Secouriste
        SecouristeDTO secouristeDTO = secouristeMapper.toDto(secouriste);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecouristeMockMvc.perform(put("/api/secouristes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secouristeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Secouriste in the database
        List<Secouriste> secouristeList = secouristeRepository.findAll();
        assertThat(secouristeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSecouriste() throws Exception {
        // Initialize the database
        secouristeRepository.saveAndFlush(secouriste);

        int databaseSizeBeforeDelete = secouristeRepository.findAll().size();

        // Delete the secouriste
        restSecouristeMockMvc.perform(delete("/api/secouristes/{id}", secouriste.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Secouriste> secouristeList = secouristeRepository.findAll();
        assertThat(secouristeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
