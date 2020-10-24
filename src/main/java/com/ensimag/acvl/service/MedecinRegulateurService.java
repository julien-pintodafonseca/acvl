package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.MedecinRegulateur;
import com.ensimag.acvl.repository.MedecinRegulateurRepository;
import com.ensimag.acvl.service.dto.MedecinRegulateurDTO;
import com.ensimag.acvl.service.mapper.MedecinRegulateurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MedecinRegulateur}.
 */
@Service
@Transactional
public class MedecinRegulateurService {

    private final Logger log = LoggerFactory.getLogger(MedecinRegulateurService.class);

    private final MedecinRegulateurRepository medecinRegulateurRepository;

    private final MedecinRegulateurMapper medecinRegulateurMapper;

    public MedecinRegulateurService(MedecinRegulateurRepository medecinRegulateurRepository, MedecinRegulateurMapper medecinRegulateurMapper) {
        this.medecinRegulateurRepository = medecinRegulateurRepository;
        this.medecinRegulateurMapper = medecinRegulateurMapper;
    }

    /**
     * Save a medecinRegulateur.
     *
     * @param medecinRegulateurDTO the entity to save.
     * @return the persisted entity.
     */
    public MedecinRegulateurDTO save(MedecinRegulateurDTO medecinRegulateurDTO) {
        log.debug("Request to save MedecinRegulateur : {}", medecinRegulateurDTO);
        MedecinRegulateur medecinRegulateur = medecinRegulateurMapper.toEntity(medecinRegulateurDTO);
        medecinRegulateur = medecinRegulateurRepository.save(medecinRegulateur);
        return medecinRegulateurMapper.toDto(medecinRegulateur);
    }

    /**
     * Get all the medecinRegulateurs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MedecinRegulateurDTO> findAll() {
        log.debug("Request to get all MedecinRegulateurs");
        return medecinRegulateurRepository.findAll().stream()
            .map(medecinRegulateurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one medecinRegulateur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedecinRegulateurDTO> findOne(Long id) {
        log.debug("Request to get MedecinRegulateur : {}", id);
        return medecinRegulateurRepository.findById(id)
            .map(medecinRegulateurMapper::toDto);
    }

    /**
     * Delete the medecinRegulateur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedecinRegulateur : {}", id);
        medecinRegulateurRepository.deleteById(id);
    }
}
