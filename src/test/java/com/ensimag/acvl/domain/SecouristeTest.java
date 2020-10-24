package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class SecouristeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Secouriste.class);
        Secouriste secouriste1 = new Secouriste();
        secouriste1.setId(1L);
        Secouriste secouriste2 = new Secouriste();
        secouriste2.setId(secouriste1.getId());
        assertThat(secouriste1).isEqualTo(secouriste2);
        secouriste2.setId(2L);
        assertThat(secouriste1).isNotEqualTo(secouriste2);
        secouriste1.setId(null);
        assertThat(secouriste1).isNotEqualTo(secouriste2);
    }
}
