package com.ensimag.acvl.repository;

import com.ensimag.acvl.domain.MdcMobile;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MdcMobile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MdcMobileRepository extends JpaRepository<MdcMobile, Long> {
}
