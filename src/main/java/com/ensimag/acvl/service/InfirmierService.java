package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.Infirmier;
import com.ensimag.acvl.repository.InfirmierRepository;
import com.ensimag.acvl.service.dto.InfirmierDTO;
import com.ensimag.acvl.service.mapper.InfirmierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Infirmier}.
 */
@Service
@Transactional
public class InfirmierService {

    private final Logger log = LoggerFactory.getLogger(InfirmierService.class);

    private final InfirmierRepository infirmierRepository;

    private final InfirmierMapper infirmierMapper;

    public InfirmierService(InfirmierRepository infirmierRepository, InfirmierMapper infirmierMapper) {
        this.infirmierRepository = infirmierRepository;
        this.infirmierMapper = infirmierMapper;
    }

    /**
     * Save a infirmier.
     *
     * @param infirmierDTO the entity to save.
     * @return the persisted entity.
     */
    public InfirmierDTO save(InfirmierDTO infirmierDTO) {
        log.debug("Request to save Infirmier : {}", infirmierDTO);
        Infirmier infirmier = infirmierMapper.toEntity(infirmierDTO);
        infirmier = infirmierRepository.save(infirmier);
        return infirmierMapper.toDto(infirmier);
    }

    /**
     * Get all the infirmiers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InfirmierDTO> findAll() {
        log.debug("Request to get all Infirmiers");
        return infirmierRepository.findAll().stream()
            .map(infirmierMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one infirmier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InfirmierDTO> findOne(Long id) {
        log.debug("Request to get Infirmier : {}", id);
        return infirmierRepository.findById(id)
            .map(infirmierMapper::toDto);
    }

    /**
     * Delete the infirmier by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Infirmier : {}", id);
        infirmierRepository.deleteById(id);
    }
}
