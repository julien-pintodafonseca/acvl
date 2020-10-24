package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.MedecinParmDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedecinParm} and its DTO {@link MedecinParmDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedecinParmMapper extends EntityMapper<MedecinParmDTO, MedecinParm> {



    default MedecinParm fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedecinParm medecinParm = new MedecinParm();
        medecinParm.setId(id);
        return medecinParm;
    }
}
