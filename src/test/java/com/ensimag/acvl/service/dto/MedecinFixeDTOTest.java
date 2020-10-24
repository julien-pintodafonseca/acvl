package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MedecinFixeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedecinFixeDTO.class);
        MedecinFixeDTO medecinFixeDTO1 = new MedecinFixeDTO();
        medecinFixeDTO1.setId(1L);
        MedecinFixeDTO medecinFixeDTO2 = new MedecinFixeDTO();
        assertThat(medecinFixeDTO1).isNotEqualTo(medecinFixeDTO2);
        medecinFixeDTO2.setId(medecinFixeDTO1.getId());
        assertThat(medecinFixeDTO1).isEqualTo(medecinFixeDTO2);
        medecinFixeDTO2.setId(2L);
        assertThat(medecinFixeDTO1).isNotEqualTo(medecinFixeDTO2);
        medecinFixeDTO1.setId(null);
        assertThat(medecinFixeDTO1).isNotEqualTo(medecinFixeDTO2);
    }
}
