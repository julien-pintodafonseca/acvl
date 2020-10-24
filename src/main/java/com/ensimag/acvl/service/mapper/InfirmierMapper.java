package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.InfirmierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Infirmier} and its DTO {@link InfirmierDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InfirmierMapper extends EntityMapper<InfirmierDTO, Infirmier> {



    default Infirmier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Infirmier infirmier = new Infirmier();
        infirmier.setId(id);
        return infirmier;
    }
}
