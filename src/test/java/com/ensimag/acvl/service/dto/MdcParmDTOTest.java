package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MdcParmDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MdcParmDTO.class);
        MdcParmDTO mdcParmDTO1 = new MdcParmDTO();
        mdcParmDTO1.setId(1L);
        MdcParmDTO mdcParmDTO2 = new MdcParmDTO();
        assertThat(mdcParmDTO1).isNotEqualTo(mdcParmDTO2);
        mdcParmDTO2.setId(mdcParmDTO1.getId());
        assertThat(mdcParmDTO1).isEqualTo(mdcParmDTO2);
        mdcParmDTO2.setId(2L);
        assertThat(mdcParmDTO1).isNotEqualTo(mdcParmDTO2);
        mdcParmDTO1.setId(null);
        assertThat(mdcParmDTO1).isNotEqualTo(mdcParmDTO2);
    }
}
