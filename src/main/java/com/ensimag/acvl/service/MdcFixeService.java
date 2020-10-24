package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.MdcFixe;
import com.ensimag.acvl.repository.MdcFixeRepository;
import com.ensimag.acvl.service.dto.MdcFixeDTO;
import com.ensimag.acvl.service.mapper.MdcFixeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MdcFixe}.
 */
@Service
@Transactional
public class MdcFixeService {

    private final Logger log = LoggerFactory.getLogger(MdcFixeService.class);

    private final MdcFixeRepository mdcFixeRepository;

    private final MdcFixeMapper mdcFixeMapper;

    public MdcFixeService(MdcFixeRepository mdcFixeRepository, MdcFixeMapper mdcFixeMapper) {
        this.mdcFixeRepository = mdcFixeRepository;
        this.mdcFixeMapper = mdcFixeMapper;
    }

    /**
     * Save a mdcFixe.
     *
     * @param mdcFixeDTO the entity to save.
     * @return the persisted entity.
     */
    public MdcFixeDTO save(MdcFixeDTO mdcFixeDTO) {
        log.debug("Request to save MdcFixe : {}", mdcFixeDTO);
        MdcFixe mdcFixe = mdcFixeMapper.toEntity(mdcFixeDTO);
        mdcFixe = mdcFixeRepository.save(mdcFixe);
        return mdcFixeMapper.toDto(mdcFixe);
    }

    /**
     * Get all the mdcFixes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MdcFixeDTO> findAll() {
        log.debug("Request to get all MdcFixes");
        return mdcFixeRepository.findAll().stream()
            .map(mdcFixeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one mdcFixe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MdcFixeDTO> findOne(Long id) {
        log.debug("Request to get MdcFixe : {}", id);
        return mdcFixeRepository.findById(id)
            .map(mdcFixeMapper::toDto);
    }

    /**
     * Delete the mdcFixe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MdcFixe : {}", id);
        mdcFixeRepository.deleteById(id);
    }
}
