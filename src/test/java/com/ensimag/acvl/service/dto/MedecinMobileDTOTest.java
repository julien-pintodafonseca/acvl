package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MedecinMobileDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedecinMobileDTO.class);
        MedecinMobileDTO medecinMobileDTO1 = new MedecinMobileDTO();
        medecinMobileDTO1.setId(1L);
        MedecinMobileDTO medecinMobileDTO2 = new MedecinMobileDTO();
        assertThat(medecinMobileDTO1).isNotEqualTo(medecinMobileDTO2);
        medecinMobileDTO2.setId(medecinMobileDTO1.getId());
        assertThat(medecinMobileDTO1).isEqualTo(medecinMobileDTO2);
        medecinMobileDTO2.setId(2L);
        assertThat(medecinMobileDTO1).isNotEqualTo(medecinMobileDTO2);
        medecinMobileDTO1.setId(null);
        assertThat(medecinMobileDTO1).isNotEqualTo(medecinMobileDTO2);
    }
}
