package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.MedecinFixeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedecinFixe} and its DTO {@link MedecinFixeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedecinFixeMapper extends EntityMapper<MedecinFixeDTO, MedecinFixe> {



    default MedecinFixe fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedecinFixe medecinFixe = new MedecinFixe();
        medecinFixe.setId(id);
        return medecinFixe;
    }
}
