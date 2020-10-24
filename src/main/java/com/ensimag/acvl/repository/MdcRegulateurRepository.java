package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.MdcRegulateur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MdcRegulateur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MdcRegulateurRepository extends JpaRepository<MdcRegulateur, Long> {
}
