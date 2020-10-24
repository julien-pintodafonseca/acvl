package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.MdcFixe;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MdcFixe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MdcFixeRepository extends JpaRepository<MdcFixe, Long> {
}
