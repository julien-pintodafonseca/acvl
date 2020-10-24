package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.MdcParm;
import com.ensimag.acvl.repository.MdcParmRepository;
import com.ensimag.acvl.service.MdcParmService;
import com.ensimag.acvl.service.dto.MdcParmDTO;
import com.ensimag.acvl.service.mapper.MdcParmMapper;

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
 * Integration tests for the {@link MdcParmResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MdcParmResourceIT {

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
    private MdcParmRepository mdcParmRepository;

    @Autowired
    private MdcParmMapper mdcParmMapper;

    @Autowired
    private MdcParmService mdcParmService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMdcParmMockMvc;

    private MdcParm mdcParm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MdcParm createEntity(EntityManager em) {
        MdcParm mdcParm = new MdcParm()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return mdcParm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MdcParm createUpdatedEntity(EntityManager em) {
        MdcParm mdcParm = new MdcParm()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return mdcParm;
    }

    @BeforeEach
    public void initTest() {
        mdcParm = createEntity(em);
    }

    @Test
    @Transactional
    public void createMdcParm() throws Exception {
        int databaseSizeBeforeCreate = mdcParmRepository.findAll().size();
        // Create the MdcParm
        MdcParmDTO mdcParmDTO = mdcParmMapper.toDto(mdcParm);
        restMdcParmMockMvc.perform(post("/api/mdc-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcParmDTO)))
            .andExpect(status().isCreated());

        // Validate the MdcParm in the database
        List<MdcParm> mdcParmList = mdcParmRepository.findAll();
        assertThat(mdcParmList).hasSize(databaseSizeBeforeCreate + 1);
        MdcParm testMdcParm = mdcParmList.get(mdcParmList.size() - 1);
        assertThat(testMdcParm.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMdcParm.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testMdcParm.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testMdcParm.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testMdcParm.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testMdcParm.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testMdcParm.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testMdcParm.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createMdcParmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mdcParmRepository.findAll().size();

        // Create the MdcParm with an existing ID
        mdcParm.setId(1L);
        MdcParmDTO mdcParmDTO = mdcParmMapper.toDto(mdcParm);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMdcParmMockMvc.perform(post("/api/mdc-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcParmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MdcParm in the database
        List<MdcParm> mdcParmList = mdcParmRepository.findAll();
        assertThat(mdcParmList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = mdcParmRepository.findAll().size();
        // set the field null
        mdcParm.setNom(null);

        // Create the MdcParm, which fails.
        MdcParmDTO mdcParmDTO = mdcParmMapper.toDto(mdcParm);


        restMdcParmMockMvc.perform(post("/api/mdc-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcParmDTO)))
            .andExpect(status().isBadRequest());

        List<MdcParm> mdcParmList = mdcParmRepository.findAll();
        assertThat(mdcParmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = mdcParmRepository.findAll().size();
        // set the field null
        mdcParm.setPrenom(null);

        // Create the MdcParm, which fails.
        MdcParmDTO mdcParmDTO = mdcParmMapper.toDto(mdcParm);


        restMdcParmMockMvc.perform(post("/api/mdc-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcParmDTO)))
            .andExpect(status().isBadRequest());

        List<MdcParm> mdcParmList = mdcParmRepository.findAll();
        assertThat(mdcParmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMdcParms() throws Exception {
        // Initialize the database
        mdcParmRepository.saveAndFlush(mdcParm);

        // Get all the mdcParmList
        restMdcParmMockMvc.perform(get("/api/mdc-parms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mdcParm.getId().intValue())))
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
    public void getMdcParm() throws Exception {
        // Initialize the database
        mdcParmRepository.saveAndFlush(mdcParm);

        // Get the mdcParm
        restMdcParmMockMvc.perform(get("/api/mdc-parms/{id}", mdcParm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mdcParm.getId().intValue()))
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
    public void getNonExistingMdcParm() throws Exception {
        // Get the mdcParm
        restMdcParmMockMvc.perform(get("/api/mdc-parms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMdcParm() throws Exception {
        // Initialize the database
        mdcParmRepository.saveAndFlush(mdcParm);

        int databaseSizeBeforeUpdate = mdcParmRepository.findAll().size();

        // Update the mdcParm
        MdcParm updatedMdcParm = mdcParmRepository.findById(mdcParm.getId()).get();
        // Disconnect from session so that the updates on updatedMdcParm are not directly saved in db
        em.detach(updatedMdcParm);
        updatedMdcParm
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        MdcParmDTO mdcParmDTO = mdcParmMapper.toDto(updatedMdcParm);

        restMdcParmMockMvc.perform(put("/api/mdc-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcParmDTO)))
            .andExpect(status().isOk());

        // Validate the MdcParm in the database
        List<MdcParm> mdcParmList = mdcParmRepository.findAll();
        assertThat(mdcParmList).hasSize(databaseSizeBeforeUpdate);
        MdcParm testMdcParm = mdcParmList.get(mdcParmList.size() - 1);
        assertThat(testMdcParm.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMdcParm.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testMdcParm.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testMdcParm.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testMdcParm.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testMdcParm.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testMdcParm.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testMdcParm.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingMdcParm() throws Exception {
        int databaseSizeBeforeUpdate = mdcParmRepository.findAll().size();

        // Create the MdcParm
        MdcParmDTO mdcParmDTO = mdcParmMapper.toDto(mdcParm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMdcParmMockMvc.perform(put("/api/mdc-parms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mdcParmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MdcParm in the database
        List<MdcParm> mdcParmList = mdcParmRepository.findAll();
        assertThat(mdcParmList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMdcParm() throws Exception {
        // Initialize the database
        mdcParmRepository.saveAndFlush(mdcParm);

        int databaseSizeBeforeDelete = mdcParmRepository.findAll().size();

        // Delete the mdcParm
        restMdcParmMockMvc.perform(delete("/api/mdc-parms/{id}", mdcParm.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MdcParm> mdcParmList = mdcParmRepository.findAll();
        assertThat(mdcParmList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
