package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class SecouristeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecouristeDTO.class);
        SecouristeDTO secouristeDTO1 = new SecouristeDTO();
        secouristeDTO1.setId(1L);
        SecouristeDTO secouristeDTO2 = new SecouristeDTO();
        assertThat(secouristeDTO1).isNotEqualTo(secouristeDTO2);
        secouristeDTO2.setId(secouristeDTO1.getId());
        assertThat(secouristeDTO1).isEqualTo(secouristeDTO2);
        secouristeDTO2.setId(2L);
        assertThat(secouristeDTO1).isNotEqualTo(secouristeDTO2);
        secouristeDTO1.setId(null);
        assertThat(secouristeDTO1).isNotEqualTo(secouristeDTO2);
    }
}
