package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MedecinFixeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedecinFixe.class);
        MedecinFixe medecinFixe1 = new MedecinFixe();
        medecinFixe1.setId(1L);
        MedecinFixe medecinFixe2 = new MedecinFixe();
        medecinFixe2.setId(medecinFixe1.getId());
        assertThat(medecinFixe1).isEqualTo(medecinFixe2);
        medecinFixe2.setId(2L);
        assertThat(medecinFixe1).isNotEqualTo(medecinFixe2);
        medecinFixe1.setId(null);
        assertThat(medecinFixe1).isNotEqualTo(medecinFixe2);
    }
}
