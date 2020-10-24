package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.Secouriste;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Secouriste entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecouristeRepository extends JpaRepository<Secouriste, Long> {
}
