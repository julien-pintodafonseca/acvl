package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.MedecinRegulateur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedecinRegulateur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedecinRegulateurRepository extends JpaRepository<MedecinRegulateur, Long> {
}
