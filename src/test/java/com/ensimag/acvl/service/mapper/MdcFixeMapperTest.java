package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MdcFixeMapperTest {

    private MdcFixeMapper mdcFixeMapper;

    @BeforeEach
    public void setUp() {
        mdcFixeMapper = new MdcFixeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mdcFixeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mdcFixeMapper.fromId(null)).isNull();
    }
}
