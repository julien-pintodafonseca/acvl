package com.ensimag.acvl.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InfirmierMapperTest {

    private InfirmierMapper infirmierMapper;

    @BeforeEach
    public void setUp() {
        infirmierMapper = new InfirmierMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(infirmierMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(infirmierMapper.fromId(null)).isNull();
    }
}
