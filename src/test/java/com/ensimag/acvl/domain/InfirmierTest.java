package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class InfirmierTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Infirmier.class);
        Infirmier infirmier1 = new Infirmier();
        infirmier1.setId(1L);
        Infirmier infirmier2 = new Infirmier();
        infirmier2.setId(infirmier1.getId());
        assertThat(infirmier1).isEqualTo(infirmier2);
        infirmier2.setId(2L);
        assertThat(infirmier1).isNotEqualTo(infirmier2);
        infirmier1.setId(null);
        assertThat(infirmier1).isNotEqualTo(infirmier2);
    }
}
