package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MedecinMobileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedecinMobile.class);
        MedecinMobile medecinMobile1 = new MedecinMobile();
        medecinMobile1.setId(1L);
        MedecinMobile medecinMobile2 = new MedecinMobile();
        medecinMobile2.setId(medecinMobile1.getId());
        assertThat(medecinMobile1).isEqualTo(medecinMobile2);
        medecinMobile2.setId(2L);
        assertThat(medecinMobile1).isNotEqualTo(medecinMobile2);
        medecinMobile1.setId(null);
        assertThat(medecinMobile1).isNotEqualTo(medecinMobile2);
    }
}
