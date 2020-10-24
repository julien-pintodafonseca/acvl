package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MedecinParmDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedecinParmDTO.class);
        MedecinParmDTO medecinParmDTO1 = new MedecinParmDTO();
        medecinParmDTO1.setId(1L);
        MedecinParmDTO medecinParmDTO2 = new MedecinParmDTO();
        assertThat(medecinParmDTO1).isNotEqualTo(medecinParmDTO2);
        medecinParmDTO2.setId(medecinParmDTO1.getId());
        assertThat(medecinParmDTO1).isEqualTo(medecinParmDTO2);
        medecinParmDTO2.setId(2L);
        assertThat(medecinParmDTO1).isNotEqualTo(medecinParmDTO2);
        medecinParmDTO1.setId(null);
        assertThat(medecinParmDTO1).isNotEqualTo(medecinParmDTO2);
    }
}
