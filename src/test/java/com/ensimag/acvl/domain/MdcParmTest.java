package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MdcParmTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MdcParm.class);
        MdcParm mdcParm1 = new MdcParm();
        mdcParm1.setId(1L);
        MdcParm mdcParm2 = new MdcParm();
        mdcParm2.setId(mdcParm1.getId());
        assertThat(mdcParm1).isEqualTo(mdcParm2);
        mdcParm2.setId(2L);
        assertThat(mdcParm1).isNotEqualTo(mdcParm2);
        mdcParm1.setId(null);
        assertThat(mdcParm1).isNotEqualTo(mdcParm2);
    }
}
