package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.Infirmier;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Infirmier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfirmierRepository extends JpaRepository<Infirmier, Long> {
}
