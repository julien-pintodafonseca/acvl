package com.ensimag.acvl.web.rest;

import com.ensimag.acvl.AcvlApp;
import com.ensimag.acvl.domain.PersonnelSante;
import com.ensimag.acvl.repository.PersonnelSanteRepository;
import com.ensimag.acvl.service.PersonnelSanteService;
import com.ensimag.acvl.service.dto.PersonnelSanteDTO;
import com.ensimag.acvl.service.mapper.PersonnelSanteMapper;

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
 * Integration tests for the {@link PersonnelSanteResource} REST controller.
 */
@SpringBootTest(classes = AcvlApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonnelSanteResourceIT {

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
    private PersonnelSanteRepository personnelSanteRepository;

    @Autowired
    private PersonnelSanteMapper personnelSanteMapper;

    @Autowired
    private PersonnelSanteService personnelSanteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonnelSanteMockMvc;

    private PersonnelSante personnelSante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonnelSante createEntity(EntityManager em) {
        PersonnelSante personnelSante = new PersonnelSante()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .centre(DEFAULT_CENTRE)
            .etat(DEFAULT_ETAT)
            .estMobile(DEFAULT_EST_MOBILE)
            .estFixe(DEFAULT_EST_FIXE);
        return personnelSante;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonnelSante createUpdatedEntity(EntityManager em) {
        PersonnelSante personnelSante = new PersonnelSante()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        return personnelSante;
    }

    @BeforeEach
    public void initTest() {
        personnelSante = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonnelSante() throws Exception {
        int databaseSizeBeforeCreate = personnelSanteRepository.findAll().size();
        // Create the PersonnelSante
        PersonnelSanteDTO personnelSanteDTO = personnelSanteMapper.toDto(personnelSante);
        restPersonnelSanteMockMvc.perform(post("/api/personnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnelSanteDTO)))
            .andExpect(status().isCreated());

        // Validate the PersonnelSante in the database
        List<PersonnelSante> personnelSanteList = personnelSanteRepository.findAll();
        assertThat(personnelSanteList).hasSize(databaseSizeBeforeCreate + 1);
        PersonnelSante testPersonnelSante = personnelSanteList.get(personnelSanteList.size() - 1);
        assertThat(testPersonnelSante.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testPersonnelSante.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testPersonnelSante.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testPersonnelSante.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testPersonnelSante.getCentre()).isEqualTo(DEFAULT_CENTRE);
        assertThat(testPersonnelSante.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testPersonnelSante.isEstMobile()).isEqualTo(DEFAULT_EST_MOBILE);
        assertThat(testPersonnelSante.isEstFixe()).isEqualTo(DEFAULT_EST_FIXE);
    }

    @Test
    @Transactional
    public void createPersonnelSanteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personnelSanteRepository.findAll().size();

        // Create the PersonnelSante with an existing ID
        personnelSante.setId(1L);
        PersonnelSanteDTO personnelSanteDTO = personnelSanteMapper.toDto(personnelSante);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonnelSanteMockMvc.perform(post("/api/personnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnelSanteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonnelSante in the database
        List<PersonnelSante> personnelSanteList = personnelSanteRepository.findAll();
        assertThat(personnelSanteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnelSanteRepository.findAll().size();
        // set the field null
        personnelSante.setNom(null);

        // Create the PersonnelSante, which fails.
        PersonnelSanteDTO personnelSanteDTO = personnelSanteMapper.toDto(personnelSante);


        restPersonnelSanteMockMvc.perform(post("/api/personnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnelSanteDTO)))
            .andExpect(status().isBadRequest());

        List<PersonnelSante> personnelSanteList = personnelSanteRepository.findAll();
        assertThat(personnelSanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = personnelSanteRepository.findAll().size();
        // set the field null
        personnelSante.setPrenom(null);

        // Create the PersonnelSante, which fails.
        PersonnelSanteDTO personnelSanteDTO = personnelSanteMapper.toDto(personnelSante);


        restPersonnelSanteMockMvc.perform(post("/api/personnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnelSanteDTO)))
            .andExpect(status().isBadRequest());

        List<PersonnelSante> personnelSanteList = personnelSanteRepository.findAll();
        assertThat(personnelSanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonnelSantes() throws Exception {
        // Initialize the database
        personnelSanteRepository.saveAndFlush(personnelSante);

        // Get all the personnelSanteList
        restPersonnelSanteMockMvc.perform(get("/api/personnel-santes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personnelSante.getId().intValue())))
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
    public void getPersonnelSante() throws Exception {
        // Initialize the database
        personnelSanteRepository.saveAndFlush(personnelSante);

        // Get the personnelSante
        restPersonnelSanteMockMvc.perform(get("/api/personnel-santes/{id}", personnelSante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personnelSante.getId().intValue()))
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
    public void getNonExistingPersonnelSante() throws Exception {
        // Get the personnelSante
        restPersonnelSanteMockMvc.perform(get("/api/personnel-santes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonnelSante() throws Exception {
        // Initialize the database
        personnelSanteRepository.saveAndFlush(personnelSante);

        int databaseSizeBeforeUpdate = personnelSanteRepository.findAll().size();

        // Update the personnelSante
        PersonnelSante updatedPersonnelSante = personnelSanteRepository.findById(personnelSante.getId()).get();
        // Disconnect from session so that the updates on updatedPersonnelSante are not directly saved in db
        em.detach(updatedPersonnelSante);
        updatedPersonnelSante
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .centre(UPDATED_CENTRE)
            .etat(UPDATED_ETAT)
            .estMobile(UPDATED_EST_MOBILE)
            .estFixe(UPDATED_EST_FIXE);
        PersonnelSanteDTO personnelSanteDTO = personnelSanteMapper.toDto(updatedPersonnelSante);

        restPersonnelSanteMockMvc.perform(put("/api/personnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnelSanteDTO)))
            .andExpect(status().isOk());

        // Validate the PersonnelSante in the database
        List<PersonnelSante> personnelSanteList = personnelSanteRepository.findAll();
        assertThat(personnelSanteList).hasSize(databaseSizeBeforeUpdate);
        PersonnelSante testPersonnelSante = personnelSanteList.get(personnelSanteList.size() - 1);
        assertThat(testPersonnelSante.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testPersonnelSante.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testPersonnelSante.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testPersonnelSante.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testPersonnelSante.getCentre()).isEqualTo(UPDATED_CENTRE);
        assertThat(testPersonnelSante.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testPersonnelSante.isEstMobile()).isEqualTo(UPDATED_EST_MOBILE);
        assertThat(testPersonnelSante.isEstFixe()).isEqualTo(UPDATED_EST_FIXE);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonnelSante() throws Exception {
        int databaseSizeBeforeUpdate = personnelSanteRepository.findAll().size();

        // Create the PersonnelSante
        PersonnelSanteDTO personnelSanteDTO = personnelSanteMapper.toDto(personnelSante);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonnelSanteMockMvc.perform(put("/api/personnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personnelSanteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonnelSante in the database
        List<PersonnelSante> personnelSanteList = personnelSanteRepository.findAll();
        assertThat(personnelSanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonnelSante() throws Exception {
        // Initialize the database
        personnelSanteRepository.saveAndFlush(personnelSante);

        int databaseSizeBeforeDelete = personnelSanteRepository.findAll().size();

        // Delete the personnelSante
        restPersonnelSanteMockMvc.perform(delete("/api/personnel-santes/{id}", personnelSante.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersonnelSante> personnelSanteList = personnelSanteRepository.findAll();
        assertThat(personnelSanteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
