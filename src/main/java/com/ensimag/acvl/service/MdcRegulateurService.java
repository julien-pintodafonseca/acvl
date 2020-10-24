package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.MdcRegulateur;
import com.ensimag.acvl.repository.MdcRegulateurRepository;
import com.ensimag.acvl.service.dto.MdcRegulateurDTO;
import com.ensimag.acvl.service.mapper.MdcRegulateurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MdcRegulateur}.
 */
@Service
@Transactional
public class MdcRegulateurService {

    private final Logger log = LoggerFactory.getLogger(MdcRegulateurService.class);

    private final MdcRegulateurRepository mdcRegulateurRepository;

    private final MdcRegulateurMapper mdcRegulateurMapper;

    public MdcRegulateurService(MdcRegulateurRepository mdcRegulateurRepository, MdcRegulateurMapper mdcRegulateurMapper) {
        this.mdcRegulateurRepository = mdcRegulateurRepository;
        this.mdcRegulateurMapper = mdcRegulateurMapper;
    }

    /**
     * Save a mdcRegulateur.
     *
     * @param mdcRegulateurDTO the entity to save.
     * @return the persisted entity.
     */
    public MdcRegulateurDTO save(MdcRegulateurDTO mdcRegulateurDTO) {
        log.debug("Request to save MdcRegulateur : {}", mdcRegulateurDTO);
        MdcRegulateur mdcRegulateur = mdcRegulateurMapper.toEntity(mdcRegulateurDTO);
        mdcRegulateur = mdcRegulateurRepository.save(mdcRegulateur);
        return mdcRegulateurMapper.toDto(mdcRegulateur);
    }

    /**
     * Get all the mdcRegulateurs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MdcRegulateurDTO> findAll() {
        log.debug("Request to get all MdcRegulateurs");
        return mdcRegulateurRepository.findAll().stream()
            .map(mdcRegulateurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one mdcRegulateur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MdcRegulateurDTO> findOne(Long id) {
        log.debug("Request to get MdcRegulateur : {}", id);
        return mdcRegulateurRepository.findById(id)
            .map(mdcRegulateurMapper::toDto);
    }

    /**
     * Delete the mdcRegulateur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MdcRegulateur : {}", id);
        mdcRegulateurRepository.deleteById(id);
    }
}
