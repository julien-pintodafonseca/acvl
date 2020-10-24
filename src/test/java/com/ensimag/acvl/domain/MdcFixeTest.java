package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MdcFixeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MdcFixe.class);
        MdcFixe mdcFixe1 = new MdcFixe();
        mdcFixe1.setId(1L);
        MdcFixe mdcFixe2 = new MdcFixe();
        mdcFixe2.setId(mdcFixe1.getId());
        assertThat(mdcFixe1).isEqualTo(mdcFixe2);
        mdcFixe2.setId(2L);
        assertThat(mdcFixe1).isNotEqualTo(mdcFixe2);
        mdcFixe1.setId(null);
        assertThat(mdcFixe1).isNotEqualTo(mdcFixe2);
    }
}
