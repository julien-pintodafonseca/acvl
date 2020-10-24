package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class InfirmierDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InfirmierDTO.class);
        InfirmierDTO infirmierDTO1 = new InfirmierDTO();
        infirmierDTO1.setId(1L);
        InfirmierDTO infirmierDTO2 = new InfirmierDTO();
        assertThat(infirmierDTO1).isNotEqualTo(infirmierDTO2);
        infirmierDTO2.setId(infirmierDTO1.getId());
        assertThat(infirmierDTO1).isEqualTo(infirmierDTO2);
        infirmierDTO2.setId(2L);
        assertThat(infirmierDTO1).isNotEqualTo(infirmierDTO2);
        infirmierDTO1.setId(null);
        assertThat(infirmierDTO1).isNotEqualTo(infirmierDTO2);
    }
}
