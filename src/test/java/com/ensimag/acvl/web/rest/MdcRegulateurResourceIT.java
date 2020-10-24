package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.MdcRegulateur;
import com.ensimag.acvl.repository.MdcRegulateurRepository;
import com.ensimag.acvl.service.MdcRegulateurService;
import com.ensimag.acvl.service.dto.MdcRegulateurDTO;
import com.ensimag.acvl.service.mapper.MdcRegulateurMapper;

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
 * Integration tests for the {@link MdcRegulateurResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MdcRegulateurResourceIT {

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
    private MdcRegulateurRepository mdcRegulateurRepository;

    @Autowired
    private MdcRegulateurMapper mdcRegulateurMapper;

    @Autowired
    private MdcRegulateurService mdcRegulateurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMdcRegulateurMockMvc;

    private MdcRegulateur mdcRegulateur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MdcRegulateur createEntity(EntityManager em) {
        MdcRegulateur mdcRegulateur = new MdcRegulateur()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return mdcRegulateur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MdcRegulateur createUpdatedEntity(EntityManager em) {
        MdcRegulateur mdcRegulateur = new MdcRegulateur()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return mdcRegulateur;
    }

    @BeforeEach
    public void initTest() {
        mdcRegulateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createMdcRegulateur() throws Exception {
        int databaseSizeBeforeCreate = mdcRegulateurRepository.findAll().size();
        // Create the MdcRegulateur
        MdcRegulateurDTO mdcRegulateurDTO = mdcRegulateurMapper.toDto(mdcRegulateur);
        restMdcRegulateurMockMvc.perform(post("/api/mdc-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcRegulateurDTO)))
            .andExpect(status().isCreated());

        // Validate the MdcRegulateur in the database
        List<MdcRegulateur> mdcRegulateurList = mdcRegulateurRepository.findAll();
        assertThat(mdcRegulateurList).hasSize(databaseSizeBeforeCreate + 1);
        MdcRegulateur testMdcRegulateur = mdcRegulateurList.get(mdcRegulateurList.size() - 1);
        assertThat(testMdcRegulateur.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMdcRegulateur.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testMdcRegulateur.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testMdcRegulateur.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMdcRegulateur.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testMdcRegulateur.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testMdcRegulateur.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testMdcRegulateur.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createMdcRegulateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mdcRegulateurRepository.findAll().size();

        // Create the MdcRegulateur with an existing ID
        mdcRegulateur.setId(1L);
        MdcRegulateurDTO mdcRegulateurDTO = mdcRegulateurMapper.toDto(mdcRegulateur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMdcRegulateurMockMvc.perform(post("/api/mdc-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcRegulateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MdcRegulateur in the database
        List<MdcRegulateur> mdcRegulateurList = mdcRegulateurRepository.findAll();
        assertThat(mdcRegulateurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = mdcRegulateurRepository.findAll().size();
        // set the field null
        mdcRegulateur.setNom(null);

        // Create the MdcRegulateur, which fails.
        MdcRegulateurDTO mdcRegulateurDTO = mdcRegulateurMapper.toDto(mdcRegulateur);


        restMdcRegulateurMockMvc.perform(post("/api/mdc-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcRegulateurDTO)))
            .andExpect(status().isBadRequest());

        List<MdcRegulateur> mdcRegulateurList = mdcRegulateurRepository.findAll();
        assertThat(mdcRegulateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = mdcRegulateurRepository.findAll().size();
        // set the field null
        mdcRegulateur.setPrenom(null);

        // Create the MdcRegulateur, which fails.
        MdcRegulateurDTO mdcRegulateurDTO = mdcRegulateurMapper.toDto(mdcRegulateur);


        restMdcRegulateurMockMvc.perform(post("/api/mdc-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcRegulateurDTO)))
            .andExpect(status().isBadRequest());

        List<MdcRegulateur> mdcRegulateurList = mdcRegulateurRepository.findAll();
        assertThat(mdcRegulateurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMdcRegulateurs() throws Exception {
        // Initialize the database
        mdcRegulateurRepository.saveAndFlush(mdcRegulateur);

        // Get all the mdcRegulateurList
        restMdcRegulateurMockMvc.perform(get("/api/mdc-regulateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mdcRegulateur.getId().intValue())))
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
    public void getMdcRegulateur() throws Exception {
        // Initialize the database
        mdcRegulateurRepository.saveAndFlush(mdcRegulateur);

        // Get the mdcRegulateur
        restMdcRegulateurMockMvc.perform(get("/api/mdc-regulateurs/{id}", mdcRegulateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mdcRegulateur.getId().intValue()))
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
    public void getNonExistingMdcRegulateur() throws Exception {
        // Get the mdcRegulateur
        restMdcRegulateurMockMvc.perform(get("/api/mdc-regulateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMdcRegulateur() throws Exception {
        // Initialize the database
        mdcRegulateurRepository.saveAndFlush(mdcRegulateur);

        int databaseSizeBeforeUpdate = mdcRegulateurRepository.findAll().size();

        // Update the mdcRegulateur
        MdcRegulateur updatedMdcRegulateur = mdcRegulateurRepository.findById(mdcRegulateur.getId()).get();
        // Disconnect from session so that the updates on updatedMdcRegulateur are not directly saved in db
        em.detach(updatedMdcRegulateur);
        updatedMdcRegulateur
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        MdcRegulateurDTO mdcRegulateurDTO = mdcRegulateurMapper.toDto(updatedMdcRegulateur);

        restMdcRegulateurMockMvc.perform(put("/api/mdc-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcRegulateurDTO)))
            .andExpect(status().isOk());

        // Validate the MdcRegulateur in the database
        List<MdcRegulateur> mdcRegulateurList = mdcRegulateurRepository.findAll();
        assertThat(mdcRegulateurList).hasSize(databaseSizeBeforeUpdate);
        MdcRegulateur testMdcRegulateur = mdcRegulateurList.get(mdcRegulateurList.size() - 1);
        assertThat(testMdcRegulateur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMdcRegulateur.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testMdcRegulateur.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMdcRegulateur.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMdcRegulateur.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testMdcRegulateur.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testMdcRegulateur.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testMdcRegulateur.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingMdcRegulateur() throws Exception {
        int databaseSizeBeforeUpdate = mdcRegulateurRepository.findAll().size();

        // Create the MdcRegulateur
        MdcRegulateurDTO mdcRegulateurDTO = mdcRegulateurMapper.toDto(mdcRegulateur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMdcRegulateurMockMvc.perform(put("/api/mdc-regulateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcRegulateurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MdcRegulateur in the database
        List<MdcRegulateur> mdcRegulateurList = mdcRegulateurRepository.findAll();
        assertThat(mdcRegulateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMdcRegulateur() throws Exception {
        // Initialize the database
        mdcRegulateurRepository.saveAndFlush(mdcRegulateur);

        int databaseSizeBeforeDelete = mdcRegulateurRepository.findAll().size();

        // Delete the mdcRegulateur
        restMdcRegulateurMockMvc.perform(delete("/api/mdc-regulateurs/{id}", mdcRegulateur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MdcRegulateur> mdcRegulateurList = mdcRegulateurRepository.findAll();
        assertThat(mdcRegulateurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
