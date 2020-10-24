package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.SecouristeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Secouriste} and its DTO {@link SecouristeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SecouristeMapper extends EntityMapper<SecouristeDTO, Secouriste> {



    default Secouriste fromId(Long id) {
        if (id == null) {
            return null;
        }
        Secouriste secouriste = new Secouriste();
        secouriste.setId(id);
        return secouriste;
    }
}
