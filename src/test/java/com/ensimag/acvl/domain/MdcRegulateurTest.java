package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class MdcRegulateurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MdcRegulateur.class);
        MdcRegulateur mdcRegulateur1 = new MdcRegulateur();
        mdcRegulateur1.setId(1L);
        MdcRegulateur mdcRegulateur2 = new MdcRegulateur();
        mdcRegulateur2.setId(mdcRegulateur1.getId());
        assertThat(mdcRegulateur1).isEqualTo(mdcRegulateur2);
        mdcRegulateur2.setId(2L);
        assertThat(mdcRegulateur1).isNotEqualTo(mdcRegulateur2);
        mdcRegulateur1.setId(null);
        assertThat(mdcRegulateur1).isNotEqualTo(mdcRegulateur2);
    }
}
