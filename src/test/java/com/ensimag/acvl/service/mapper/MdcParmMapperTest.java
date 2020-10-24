package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MdcParmMapperTest {

    private MdcParmMapper mdcParmMapper;

    @BeforeEach
    public void setUp() {
        mdcParmMapper = new MdcParmMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mdcParmMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mdcParmMapper.fromId(null)).isNull();
    }
}
