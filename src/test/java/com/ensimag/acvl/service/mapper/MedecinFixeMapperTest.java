package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedecinFixeMapperTest {

    private MedecinFixeMapper medecinFixeMapper;

    @BeforeEach
    public void setUp() {
        medecinFixeMapper = new MedecinFixeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medecinFixeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medecinFixeMapper.fromId(null)).isNull();
    }
}
