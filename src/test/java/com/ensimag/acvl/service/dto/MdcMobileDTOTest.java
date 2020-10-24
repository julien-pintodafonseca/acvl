package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MdcMobileDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MdcMobileDTO.class);
        MdcMobileDTO mdcMobileDTO1 = new MdcMobileDTO();
        mdcMobileDTO1.setId(1L);
        MdcMobileDTO mdcMobileDTO2 = new MdcMobileDTO();
        assertThat(mdcMobileDTO1).isNotEqualTo(mdcMobileDTO2);
        mdcMobileDTO2.setId(mdcMobileDTO1.getId());
        assertThat(mdcMobileDTO1).isEqualTo(mdcMobileDTO2);
        mdcMobileDTO2.setId(2L);
        assertThat(mdcMobileDTO1).isNotEqualTo(mdcMobileDTO2);
        mdcMobileDTO1.setId(null);
        assertThat(mdcMobileDTO1).isNotEqualTo(mdcMobileDTO2);
    }
}
