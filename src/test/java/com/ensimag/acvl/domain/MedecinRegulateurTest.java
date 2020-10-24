package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MedecinRegulateurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedecinRegulateur.class);
        MedecinRegulateur medecinRegulateur1 = new MedecinRegulateur();
        medecinRegulateur1.setId(1L);
        MedecinRegulateur medecinRegulateur2 = new MedecinRegulateur();
        medecinRegulateur2.setId(medecinRegulateur1.getId());
        assertThat(medecinRegulateur1).isEqualTo(medecinRegulateur2);
        medecinRegulateur2.setId(2L);
        assertThat(medecinRegulateur1).isNotEqualTo(medecinRegulateur2);
        medecinRegulateur1.setId(null);
        assertThat(medecinRegulateur1).isNotEqualTo(medecinRegulateur2);
    }
}
