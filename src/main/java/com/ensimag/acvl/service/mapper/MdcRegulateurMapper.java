package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.MdcRegulateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MdcRegulateur} and its DTO {@link MdcRegulateurDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MdcRegulateurMapper extends EntityMapper<MdcRegulateurDTO, MdcRegulateur> {



    default MdcRegulateur fromId(Long id) {
        if (id == null) {
            return null;
        }
        MdcRegulateur mdcRegulateur = new MdcRegulateur();
        mdcRegulateur.setId(id);
        return mdcRegulateur;
    }
}
