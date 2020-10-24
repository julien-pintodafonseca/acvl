package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MdcMobileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MdcMobile.class);
        MdcMobile mdcMobile1 = new MdcMobile();
        mdcMobile1.setId(1L);
        MdcMobile mdcMobile2 = new MdcMobile();
        mdcMobile2.setId(mdcMobile1.getId());
        assertThat(mdcMobile1).isEqualTo(mdcMobile2);
        mdcMobile2.setId(2L);
        assertThat(mdcMobile1).isNotEqualTo(mdcMobile2);
        mdcMobile1.setId(null);
        assertThat(mdcMobile1).isNotEqualTo(mdcMobile2);
    }
}
