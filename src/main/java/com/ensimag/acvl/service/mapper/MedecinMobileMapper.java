package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.MedecinMobileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedecinMobile} and its DTO {@link MedecinMobileDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedecinMobileMapper extends EntityMapper<MedecinMobileDTO, MedecinMobile> {



    default MedecinMobile fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedecinMobile medecinMobile = new MedecinMobile();
        medecinMobile.setId(id);
        return medecinMobile;
    }
}
