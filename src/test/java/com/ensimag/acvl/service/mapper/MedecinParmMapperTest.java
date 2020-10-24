package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedecinParmMapperTest {

    private MedecinParmMapper medecinParmMapper;

    @BeforeEach
    public void setUp() {
        medecinParmMapper = new MedecinParmMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medecinParmMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medecinParmMapper.fromId(null)).isNull();
    }
}
