package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedecinMobileMapperTest {

    private MedecinMobileMapper medecinMobileMapper;

    @BeforeEach
    public void setUp() {
        medecinMobileMapper = new MedecinMobileMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medecinMobileMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medecinMobileMapper.fromId(null)).isNull();
    }
}
