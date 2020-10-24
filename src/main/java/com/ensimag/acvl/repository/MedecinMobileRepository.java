package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.MedecinMobile;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedecinMobile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedecinMobileRepository extends JpaRepository<MedecinMobile, Long> {
}
