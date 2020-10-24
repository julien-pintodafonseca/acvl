package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.MdcFixeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MdcFixe} and its DTO {@link MdcFixeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MdcFixeMapper extends EntityMapper<MdcFixeDTO, MdcFixe> {



    default MdcFixe fromId(Long id) {
        if (id == null) {
            return null;
        }
        MdcFixe mdcFixe = new MdcFixe();
        mdcFixe.setId(id);
        return mdcFixe;
    }
}
