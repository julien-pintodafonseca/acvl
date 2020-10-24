package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MdcMobileMapperTest {

    private MdcMobileMapper mdcMobileMapper;

    @BeforeEach
    public void setUp() {
        mdcMobileMapper = new MdcMobileMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mdcMobileMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mdcMobileMapper.fromId(null)).isNull();
    }
}
