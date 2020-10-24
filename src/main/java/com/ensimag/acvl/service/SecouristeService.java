package com.ensimag.acvl.service;

import com.ensimag.acvl.domain.Secouriste;
import com.ensimag.acvl.repository.SecouristeRepository;
import com.ensimag.acvl.service.dto.SecouristeDTO;
import com.ensimag.acvl.service.mapper.SecouristeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Secouriste}.
 */
@Service
@Transactional
public class SecouristeService {

    private final Logger log = LoggerFactory.getLogger(SecouristeService.class);

    private final SecouristeRepository secouristeRepository;

    private final SecouristeMapper secouristeMapper;

    public SecouristeService(SecouristeRepository secouristeRepository, SecouristeMapper secouristeMapper) {
        this.secouristeRepository = secouristeRepository;
        this.secouristeMapper = secouristeMapper;
    }

    /**
     * Save a secouriste.
     *
     * @param secouristeDTO the entity to save.
     * @return the persisted entity.
     */
    public SecouristeDTO save(SecouristeDTO secouristeDTO) {
        log.debug("Request to save Secouriste : {}", secouristeDTO);
        Secouriste secouriste = secouristeMapper.toEntity(secouristeDTO);
        secouriste = secouristeRepository.save(secouriste);
        return secouristeMapper.toDto(secouriste);
    }

    /**
     * Get all the secouristes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SecouristeDTO> findAll() {
        log.debug("Request to get all Secouristes");
        return secouristeRepository.findAll().stream()
            .map(secouristeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one secouriste by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SecouristeDTO> findOne(Long id) {
        log.debug("Request to get Secouriste : {}", id);
        return secouristeRepository.findById(id)
            .map(secouristeMapper::toDto);
    }

    /**
     * Delete the secouriste by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Secouriste : {}", id);
        secouristeRepository.deleteById(id);
    }
}
