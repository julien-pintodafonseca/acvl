package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.MdcParm;
import com.ensimag.acvl.repository.MdcParmRepository;
import com.ensimag.acvl.service.dto.MdcParmDTO;
import com.ensimag.acvl.service.mapper.MdcParmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MdcParm}.
 */
@Service
@Transactional
public class MdcParmService {

    private final Logger log = LoggerFactory.getLogger(MdcParmService.class);

    private final MdcParmRepository mdcParmRepository;

    private final MdcParmMapper mdcParmMapper;

    public MdcParmService(MdcParmRepository mdcParmRepository, MdcParmMapper mdcParmMapper) {
        this.mdcParmRepository = mdcParmRepository;
        this.mdcParmMapper = mdcParmMapper;
    }

    /**
     * Save a mdcParm.
     *
     * @param mdcParmDTO the entity to save.
     * @return the persisted entity.
     */
    public MdcParmDTO save(MdcParmDTO mdcParmDTO) {
        log.debug("Request to save MdcParm : {}", mdcParmDTO);
        MdcParm mdcParm = mdcParmMapper.toEntity(mdcParmDTO);
        mdcParm = mdcParmRepository.save(mdcParm);
        return mdcParmMapper.toDto(mdcParm);
    }

    /**
     * Get all the mdcParms.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MdcParmDTO> findAll() {
        log.debug("Request to get all MdcParms");
        return mdcParmRepository.findAll().stream()
            .map(mdcParmMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one mdcParm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MdcParmDTO> findOne(Long id) {
        log.debug("Request to get MdcParm : {}", id);
        return mdcParmRepository.findById(id)
            .map(mdcParmMapper::toDto);
    }

    /**
     * Delete the mdcParm by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MdcParm : {}", id);
        mdcParmRepository.deleteById(id);
    }
}
