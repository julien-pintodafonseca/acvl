package com.ensimag.acvl.service.mapper;


import com.ensimag.acvl.domain.*;
import com.ensimag.acvl.service.dto.MdcParmDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MdcParm} and its DTO {@link MdcParmDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MdcParmMapper extends EntityMapper<MdcParmDTO, MdcParm> {



    default MdcParm fromId(Long id) {
        if (id == null) {
            return null;
        }
        MdcParm mdcParm = new MdcParm();
        mdcParm.setId(id);
        return mdcParm;
    }
}
