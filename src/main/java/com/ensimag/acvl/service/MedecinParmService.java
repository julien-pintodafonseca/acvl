package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.MedecinParm;
import com.ensimag.acvl.repository.MedecinParmRepository;
import com.ensimag.acvl.service.dto.MedecinParmDTO;
import com.ensimag.acvl.service.mapper.MedecinParmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MedecinParm}.
 */
@Service
@Transactional
public class MedecinParmService {

    private final Logger log = LoggerFactory.getLogger(MedecinParmService.class);

    private final MedecinParmRepository medecinParmRepository;

    private final MedecinParmMapper medecinParmMapper;

    public MedecinParmService(MedecinParmRepository medecinParmRepository, MedecinParmMapper medecinParmMapper) {
        this.medecinParmRepository = medecinParmRepository;
        this.medecinParmMapper = medecinParmMapper;
    }

    /**
     * Save a medecinParm.
     *
     * @param medecinParmDTO the entity to save.
     * @return the persisted entity.
     */
    public MedecinParmDTO save(MedecinParmDTO medecinParmDTO) {
        log.debug("Request to save MedecinParm : {}", medecinParmDTO);
        MedecinParm medecinParm = medecinParmMapper.toEntity(medecinParmDTO);
        medecinParm = medecinParmRepository.save(medecinParm);
        return medecinParmMapper.toDto(medecinParm);
    }

    /**
     * Get all the medecinParms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MedecinParmDTO> findAll() {
        log.debug("Request to get all MedecinParms");
        return medecinParmRepository.findAll().stream()
            .map(medecinParmMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one medecinParm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedecinParmDTO> findOne(Long id) {
        log.debug("Request to get MedecinParm : {}", id);
        return medecinParmRepository.findById(id)
            .map(medecinParmMapper::toDto);
    }

    /**
     * Delete the medecinParm by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedecinParm : {}", id);
        medecinParmRepository.deleteById(id);
    }
}
