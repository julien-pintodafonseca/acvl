package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MdcRegulateurDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MdcRegulateurDTO.class);
        MdcRegulateurDTO mdcRegulateurDTO1 = new MdcRegulateurDTO();
        mdcRegulateurDTO1.setId(1L);
        MdcRegulateurDTO mdcRegulateurDTO2 = new MdcRegulateurDTO();
        assertThat(mdcRegulateurDTO1).isNotEqualTo(mdcRegulateurDTO2);
        mdcRegulateurDTO2.setId(mdcRegulateurDTO1.getId());
        assertThat(mdcRegulateurDTO1).isEqualTo(mdcRegulateurDTO2);
        mdcRegulateurDTO2.setId(2L);
        assertThat(mdcRegulateurDTO1).isNotEqualTo(mdcRegulateurDTO2);
        mdcRegulateurDTO1.setId(null);
        assertThat(mdcRegulateurDTO1).isNotEqualTo(mdcRegulateurDTO2);
    }
}
