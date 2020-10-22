package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.PersonnelSanteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PersonnelSante} and its DTO {@link PersonnelSanteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonnelSanteMapper extends EntityMapper<PersonnelSanteDTO, PersonnelSante> {



    default PersonnelSante fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonnelSante personnelSante = new PersonnelSante();
        personnelSante.setId(id);
        return personnelSante;
    }
}
