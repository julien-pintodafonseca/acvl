package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MdcRegulateurMapperTest {

    private MdcRegulateurMapper mdcRegulateurMapper;

    @BeforeEach
    public void setUp() {
        mdcRegulateurMapper = new MdcRegulateurMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mdcRegulateurMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mdcRegulateurMapper.fromId(null)).isNull();
    }
}
