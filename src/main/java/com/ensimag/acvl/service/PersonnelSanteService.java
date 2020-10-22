package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.PersonnelSante;
import com.ensimag.acvl.repository.PersonnelSanteRepository;
import com.ensimag.acvl.service.dto.PersonnelSanteDTO;
import com.ensimag.acvl.service.mapper.PersonnelSanteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PersonnelSante}.
 */
@Service
@Transactional
public class PersonnelSanteService {

    private final Logger log = LoggerFactory.getLogger(PersonnelSanteService.class);

    private final PersonnelSanteRepository personnelSanteRepository;

    private final PersonnelSanteMapper personnelSanteMapper;

    public PersonnelSanteService(PersonnelSanteRepository personnelSanteRepository, PersonnelSanteMapper personnelSanteMapper) {
        this.personnelSanteRepository = personnelSanteRepository;
        this.personnelSanteMapper = personnelSanteMapper;
    }

    /**
     * Save a personnelSante.
     *
     * @param personnelSanteDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonnelSanteDTO save(PersonnelSanteDTO personnelSanteDTO) {
        log.debug("Request to save PersonnelSante : {}", personnelSanteDTO);
        PersonnelSante personnelSante = personnelSanteMapper.toEntity(personnelSanteDTO);
        personnelSante = personnelSanteRepository.save(personnelSante);
        return personnelSanteMapper.toDto(personnelSante);
    }

    /**
     * Get all the personnelSantes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PersonnelSanteDTO> findAll() {
        log.debug("Request to get all PersonnelSantes");
        return personnelSanteRepository.findAll().stream()
            .map(personnelSanteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one personnelSante by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonnelSanteDTO> findOne(Long id) {
        log.debug("Request to get PersonnelSante : {}", id);
        return personnelSanteRepository.findById(id)
            .map(personnelSanteMapper::toDto);
    }

    /**
     * Delete the personnelSante by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PersonnelSante : {}", id);
        personnelSanteRepository.deleteById(id);
    }
}
