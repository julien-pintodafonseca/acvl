package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonnelSanteMapperTest {

    private PersonnelSanteMapper personnelSanteMapper;

    @BeforeEach
    public void setUp() {
        personnelSanteMapper = new PersonnelSanteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(personnelSanteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(personnelSanteMapper.fromId(null)).isNull();
    }
}
