package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.MdcParm;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MdcParm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MdcParmRepository extends JpaRepository<MdcParm, Long> {
}
