package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MdcFixeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MdcFixeDTO.class);
        MdcFixeDTO mdcFixeDTO1 = new MdcFixeDTO();
        mdcFixeDTO1.setId(1L);
        MdcFixeDTO mdcFixeDTO2 = new MdcFixeDTO();
        assertThat(mdcFixeDTO1).isNotEqualTo(mdcFixeDTO2);
        mdcFixeDTO2.setId(mdcFixeDTO1.getId());
        assertThat(mdcFixeDTO1).isEqualTo(mdcFixeDTO2);
        mdcFixeDTO2.setId(2L);
        assertThat(mdcFixeDTO1).isNotEqualTo(mdcFixeDTO2);
        mdcFixeDTO1.setId(null);
        assertThat(mdcFixeDTO1).isNotEqualTo(mdcFixeDTO2);
    }
}
