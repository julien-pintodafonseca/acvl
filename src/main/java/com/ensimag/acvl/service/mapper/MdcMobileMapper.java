package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.MdcMobileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MdcMobile} and its DTO {@link MdcMobileDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MdcMobileMapper extends EntityMapper<MdcMobileDTO, MdcMobile> {



    default MdcMobile fromId(Long id) {
        if (id == null) {
            return null;
        }
        MdcMobile mdcMobile = new MdcMobile();
        mdcMobile.setId(id);
        return mdcMobile;
    }
}
