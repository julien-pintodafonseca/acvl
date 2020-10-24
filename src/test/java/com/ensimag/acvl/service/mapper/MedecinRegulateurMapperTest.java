package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedecinRegulateurMapperTest {

    private MedecinRegulateurMapper medecinRegulateurMapper;

    @BeforeEach
    public void setUp() {
        medecinRegulateurMapper = new MedecinRegulateurMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medecinRegulateurMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medecinRegulateurMapper.fromId(null)).isNull();
    }
}
