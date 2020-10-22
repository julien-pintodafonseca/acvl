package com.ensimag.acvl.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ensimag.acvl.web.rest.TestUtil;

public class PersonnelSanteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonnelSanteDTO.class);
        PersonnelSanteDTO personnelSanteDTO1 = new PersonnelSanteDTO();
        personnelSanteDTO1.setId(1L);
        PersonnelSanteDTO personnelSanteDTO2 = new PersonnelSanteDTO();
        assertThat(personnelSanteDTO1).isNotEqualTo(personnelSanteDTO2);
        personnelSanteDTO2.setId(personnelSanteDTO1.getId());
        assertThat(personnelSanteDTO1).isEqualTo(personnelSanteDTO2);
        personnelSanteDTO2.setId(2L);
        assertThat(personnelSanteDTO1).isNotEqualTo(personnelSanteDTO2);
        personnelSanteDTO1.setId(null);
        assertThat(personnelSanteDTO1).isNotEqualTo(personnelSanteDTO2);
    }
}
