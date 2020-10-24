package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.MedecinFixe;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedecinFixe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedecinFixeRepository extends JpaRepository<MedecinFixe, Long> {
}
