package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.MedecinParm;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedecinParm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedecinParmRepository extends JpaRepository<MedecinParm, Long> {
}
