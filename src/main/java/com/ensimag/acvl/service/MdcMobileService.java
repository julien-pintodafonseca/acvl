package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.MdcMobile;
import com.ensimag.acvl.repository.MdcMobileRepository;
import com.ensimag.acvl.service.dto.MdcMobileDTO;
import com.ensimag.acvl.service.mapper.MdcMobileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MdcMobile}.
 */
@Service
@Transactional
public class MdcMobileService {

    private final Logger log = LoggerFactory.getLogger(MdcMobileService.class);

    private final MdcMobileRepository mdcMobileRepository;

    private final MdcMobileMapper mdcMobileMapper;

    public MdcMobileService(MdcMobileRepository mdcMobileRepository, MdcMobileMapper mdcMobileMapper) {
        this.mdcMobileRepository = mdcMobileRepository;
        this.mdcMobileMapper = mdcMobileMapper;
    }

    /**
     * Save a mdcMobile.
     *
     * @param mdcMobileDTO the entity to save.
     * @return the persisted entity.
     */
    public MdcMobileDTO save(MdcMobileDTO mdcMobileDTO) {
        log.debug("Request to save MdcMobile : {}", mdcMobileDTO);
        MdcMobile mdcMobile = mdcMobileMapper.toEntity(mdcMobileDTO);
        mdcMobile = mdcMobileRepository.save(mdcMobile);
        return mdcMobileMapper.toDto(mdcMobile);
    }

    /**
     * Get all the mdcMobiles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MdcMobileDTO> findAll() {
        log.debug("Request to get all MdcMobiles");
        return mdcMobileRepository.findAll().stream()
            .map(mdcMobileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one mdcMobile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MdcMobileDTO> findOne(Long id) {
        log.debug("Request to get MdcMobile : {}", id);
        return mdcMobileRepository.findById(id)
            .map(mdcMobileMapper::toDto);
    }

    /**
     * Delete the mdcMobile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MdcMobile : {}", id);
        mdcMobileRepository.deleteById(id);
    }
}
