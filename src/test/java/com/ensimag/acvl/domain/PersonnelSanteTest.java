package com.ensimag.acvl.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class PersonnelSanteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonnelSante.class);
        PersonnelSante personnelSante1 = new PersonnelSante();
        personnelSante1.setId(1L);
        PersonnelSante personnelSante2 = new PersonnelSante();
        personnelSante2.setId(personnelSante1.getId());
        assertThat(personnelSante1).isEqualTo(personnelSante2);
        personnelSante2.setId(2L);
        assertThat(personnelSante1).isNotEqualTo(personnelSante2);
        personnelSante1.setId(null);
        assertThat(personnelSante1).isNotEqualTo(personnelSante2);
    }
}
