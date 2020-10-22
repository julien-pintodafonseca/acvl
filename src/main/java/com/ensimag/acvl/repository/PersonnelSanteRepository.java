package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.PersonnelSante;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PersonnelSante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonnelSanteRepository extends JpaRepository<PersonnelSante, Long> {
}
