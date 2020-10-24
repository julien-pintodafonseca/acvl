package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MedecinRegulateurDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedecinRegulateurDTO.class);
        MedecinRegulateurDTO medecinRegulateurDTO1 = new MedecinRegulateurDTO();
        medecinRegulateurDTO1.setId(1L);
        MedecinRegulateurDTO medecinRegulateurDTO2 = new MedecinRegulateurDTO();
        assertThat(medecinRegulateurDTO1).isNotEqualTo(medecinRegulateurDTO2);
        medecinRegulateurDTO2.setId(medecinRegulateurDTO1.getId());
        assertThat(medecinRegulateurDTO1).isEqualTo(medecinRegulateurDTO2);
        medecinRegulateurDTO2.setId(2L);
        assertThat(medecinRegulateurDTO1).isNotEqualTo(medecinRegulateurDTO2);
        medecinRegulateurDTO1.setId(null);
        assertThat(medecinRegulateurDTO1).isNotEqualTo(medecinRegulateurDTO2);
    }
}
