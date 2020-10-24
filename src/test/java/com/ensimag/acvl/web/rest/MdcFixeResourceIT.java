package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.MdcFixe;
import com.ensimag.acvl.repository.MdcFixeRepository;
import com.ensimag.acvl.service.MdcFixeService;
import com.ensimag.acvl.service.dto.MdcFixeDTO;
import com.ensimag.acvl.service.mapper.MdcFixeMapper;

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
 * Integration tests for the {@link MdcFixeResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MdcFixeResourceIT {

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
    private MdcFixeRepository mdcFixeRepository;

    @Autowired
    private MdcFixeMapper mdcFixeMapper;

    @Autowired
    private MdcFixeService mdcFixeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMdcFixeMockMvc;

    private MdcFixe mdcFixe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MdcFixe createEntity(EntityManager em) {
        MdcFixe mdcFixe = new MdcFixe()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return mdcFixe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MdcFixe createUpdatedEntity(EntityManager em) {
        MdcFixe mdcFixe = new MdcFixe()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return mdcFixe;
    }

    @BeforeEach
    public void initTest() {
        mdcFixe = createEntity(em);
    }

    @Test
    @Transactional
    public void createMdcFixe() throws Exception {
        int databaseSizeBeforeCreate = mdcFixeRepository.findAll().size();
        // Create the MdcFixe
        MdcFixeDTO mdcFixeDTO = mdcFixeMapper.toDto(mdcFixe);
        restMdcFixeMockMvc.perform(post("/api/mdc-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcFixeDTO)))
            .andExpect(status().isCreated());

        // Validate the MdcFixe in the database
        List<MdcFixe> mdcFixeList = mdcFixeRepository.findAll();
        assertThat(mdcFixeList).hasSize(databaseSizeBeforeCreate + 1);
        MdcFixe testMdcFixe = mdcFixeList.get(mdcFixeList.size() - 1);
        assertThat(testMdcFixe.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMdcFixe.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testMdcFixe.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testMdcFixe.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMdcFixe.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testMdcFixe.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testMdcFixe.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testMdcFixe.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createMdcFixeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mdcFixeRepository.findAll().size();

        // Create the MdcFixe with an existing ID
        mdcFixe.setId(1L);
        MdcFixeDTO mdcFixeDTO = mdcFixeMapper.toDto(mdcFixe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMdcFixeMockMvc.perform(post("/api/mdc-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcFixeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MdcFixe in the database
        List<MdcFixe> mdcFixeList = mdcFixeRepository.findAll();
        assertThat(mdcFixeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = mdcFixeRepository.findAll().size();
        // set the field null
        mdcFixe.setNom(null);

        // Create the MdcFixe, which fails.
        MdcFixeDTO mdcFixeDTO = mdcFixeMapper.toDto(mdcFixe);


        restMdcFixeMockMvc.perform(post("/api/mdc-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcFixeDTO)))
            .andExpect(status().isBadRequest());

        List<MdcFixe> mdcFixeList = mdcFixeRepository.findAll();
        assertThat(mdcFixeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = mdcFixeRepository.findAll().size();
        // set the field null
        mdcFixe.setPrenom(null);

        // Create the MdcFixe, which fails.
        MdcFixeDTO mdcFixeDTO = mdcFixeMapper.toDto(mdcFixe);


        restMdcFixeMockMvc.perform(post("/api/mdc-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcFixeDTO)))
            .andExpect(status().isBadRequest());

        List<MdcFixe> mdcFixeList = mdcFixeRepository.findAll();
        assertThat(mdcFixeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMdcFixes() throws Exception {
        // Initialize the database
        mdcFixeRepository.saveAndFlush(mdcFixe);

        // Get all the mdcFixeList
        restMdcFixeMockMvc.perform(get("/api/mdc-fixes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mdcFixe.getId().intValue())))
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
    public void getMdcFixe() throws Exception {
        // Initialize the database
        mdcFixeRepository.saveAndFlush(mdcFixe);

        // Get the mdcFixe
        restMdcFixeMockMvc.perform(get("/api/mdc-fixes/{id}", mdcFixe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mdcFixe.getId().intValue()))
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
    public void getNonExistingMdcFixe() throws Exception {
        // Get the mdcFixe
        restMdcFixeMockMvc.perform(get("/api/mdc-fixes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMdcFixe() throws Exception {
        // Initialize the database
        mdcFixeRepository.saveAndFlush(mdcFixe);

        int databaseSizeBeforeUpdate = mdcFixeRepository.findAll().size();

        // Update the mdcFixe
        MdcFixe updatedMdcFixe = mdcFixeRepository.findById(mdcFixe.getId()).get();
        // Disconnect from session so that the updates on updatedMdcFixe are not directly saved in db
        em.detach(updatedMdcFixe);
        updatedMdcFixe
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        MdcFixeDTO mdcFixeDTO = mdcFixeMapper.toDto(updatedMdcFixe);

        restMdcFixeMockMvc.perform(put("/api/mdc-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcFixeDTO)))
            .andExpect(status().isOk());

        // Validate the MdcFixe in the database
        List<MdcFixe> mdcFixeList = mdcFixeRepository.findAll();
        assertThat(mdcFixeList).hasSize(databaseSizeBeforeUpdate);
        MdcFixe testMdcFixe = mdcFixeList.get(mdcFixeList.size() - 1);
        assertThat(testMdcFixe.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMdcFixe.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testMdcFixe.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMdcFixe.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMdcFixe.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testMdcFixe.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testMdcFixe.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testMdcFixe.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingMdcFixe() throws Exception {
        int databaseSizeBeforeUpdate = mdcFixeRepository.findAll().size();

        // Create the MdcFixe
        MdcFixeDTO mdcFixeDTO = mdcFixeMapper.toDto(mdcFixe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMdcFixeMockMvc.perform(put("/api/mdc-fixes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcFixeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MdcFixe in the database
        List<MdcFixe> mdcFixeList = mdcFixeRepository.findAll();
        assertThat(mdcFixeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMdcFixe() throws Exception {
        // Initialize the database
        mdcFixeRepository.saveAndFlush(mdcFixe);

        int databaseSizeBeforeDelete = mdcFixeRepository.findAll().size();

        // Delete the mdcFixe
        restMdcFixeMockMvc.perform(delete("/api/mdc-fixes/{id}", mdcFixe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MdcFixe> mdcFixeList = mdcFixeRepository.findAll();
        assertThat(mdcFixeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
