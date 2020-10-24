package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MedecinParmTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedecinParm.class);
        MedecinParm medecinParm1 = new MedecinParm();
        medecinParm1.setId(1L);
        MedecinParm medecinParm2 = new MedecinParm();
        medecinParm2.setId(medecinParm1.getId());
        assertThat(medecinParm1).isEqualTo(medecinParm2);
        medecinParm2.setId(2L);
        assertThat(medecinParm1).isNotEqualTo(medecinParm2);
        medecinParm1.setId(null);
        assertThat(medecinParm1).isNotEqualTo(medecinParm2);
    }
}
