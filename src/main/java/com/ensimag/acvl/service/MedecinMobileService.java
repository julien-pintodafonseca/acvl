package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.MedecinMobile;
import com.ensimag.acvl.repository.MedecinMobileRepository;
import com.ensimag.acvl.service.dto.MedecinMobileDTO;
import com.ensimag.acvl.service.mapper.MedecinMobileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MedecinMobile}.
 */
@Service
@Transactional
public class MedecinMobileService {

    private final Logger log = LoggerFactory.getLogger(MedecinMobileService.class);

    private final MedecinMobileRepository medecinMobileRepository;

    private final MedecinMobileMapper medecinMobileMapper;

    public MedecinMobileService(MedecinMobileRepository medecinMobileRepository, MedecinMobileMapper medecinMobileMapper) {
        this.medecinMobileRepository = medecinMobileRepository;
        this.medecinMobileMapper = medecinMobileMapper;
    }

    /**
     * Save a medecinMobile.
     *
     * @param medecinMobileDTO the entity to save.
     * @return the persisted entity.
     */
    public MedecinMobileDTO save(MedecinMobileDTO medecinMobileDTO) {
        log.debug("Request to save MedecinMobile : {}", medecinMobileDTO);
        MedecinMobile medecinMobile = medecinMobileMapper.toEntity(medecinMobileDTO);
        medecinMobile = medecinMobileRepository.save(medecinMobile);
        return medecinMobileMapper.toDto(medecinMobile);
    }

    /**
     * Get all the medecinMobiles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MedecinMobileDTO> findAll() {
        log.debug("Request to get all MedecinMobiles");
        return medecinMobileRepository.findAll().stream()
            .map(medecinMobileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one medecinMobile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MedecinMobileDTO> findOne(Long id) {
        log.debug("Request to get MedecinMobile : {}", id);
        return medecinMobileRepository.findById(id)
            .map(medecinMobileMapper::toDto);
    }

    /**
     * Delete the medecinMobile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MedecinMobile : {}", id);
        medecinMobileRepository.deleteById(id);
    }
}
