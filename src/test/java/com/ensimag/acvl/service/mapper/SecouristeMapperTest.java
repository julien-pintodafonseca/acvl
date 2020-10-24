package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SecouristeMapperTest {

    private SecouristeMapper secouristeMapper;

    @BeforeEach
    public void setUp() {
        secouristeMapper = new SecouristeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(secouristeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(secouristeMapper.fromId(null)).isNull();
    }
}
