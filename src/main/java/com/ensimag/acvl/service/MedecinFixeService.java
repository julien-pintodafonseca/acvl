package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.MedecinFixe;
import com.ensimag.acvl.repository.MedecinFixeRepository;
import com.ensimag.acvl.service.dto.MedecinFixeDTO;
import com.ensimag.acvl.service.mapper.MedecinFixeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MedecinFixe}.
 */
@Service
@Transactional
public class MedecinFixeService {

    private final Logger log = LoggerFactory.getLogger(MedecinFixeService.class);

    private final MedecinFixeRepository medecinFixeRepository;

    private final MedecinFixeMapper medecinFixeMapper;

    public MedecinFixeService(MedecinFixeRepository medecinFixeRepository, MedecinFixeMapper medecinFixeMapper) {
        this.medecinFixeRepository = medecinFixeRepository;
        this.medecinFixeMapper = medecinFixeMapper;
    }

    /**
     * Save a medecinFixe.
     *
     * @param medecinFixeDTO the entity to save.
     * @return the persisted entity.
     */
    public MedecinFixeDTO save(MedecinFixeDTO medecinFixeDTO) {
        log.debug("Request to save MedecinFixe : {}", medecinFixeDTO);
        MedecinFixe medecinFixe = medecinFixeMapper.toEntity(medecinFixeDTO);
        medecinFixe = medecinFixeRepository.save(medecinFixe);
        return medecinFixeMapper.toDto(medecinFixe);
    }

    /**
     * Get all the medecinFixes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MedecinFixeDTO> findAll() {
        log.debug("Request to get all MedecinFixes");
        return medecinFixeRepository.findAll().stream()
            .map(medecinFixeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one medecinFixe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedecinFixeDTO> findOne(Long id) {
        log.debug("Request to get MedecinFixe : {}", id);
        return medecinFixeRepository.findById(id)
            .map(medecinFixeMapper::toDto);
    }

    /**
     * Delete the medecinFixe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedecinFixe : {}", id);
        medecinFixeRepository.deleteById(id);
    }
}
