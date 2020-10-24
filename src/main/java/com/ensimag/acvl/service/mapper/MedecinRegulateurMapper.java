package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.MedecinRegulateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedecinRegulateur} and its DTO {@link MedecinRegulateurDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedecinRegulateurMapper extends EntityMapper<MedecinRegulateurDTO, MedecinRegulateur> {



    default MedecinRegulateur fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedecinRegulateur medecinRegulateur = new MedecinRegulateur();
        medecinRegulateur.setId(id);
        return medecinRegulateur;
    }
}
