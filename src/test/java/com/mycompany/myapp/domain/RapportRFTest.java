package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RapportRFTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RapportRF.class);
        RapportRF rapportRF1 = new RapportRF();
        rapportRF1.setId(1L);
        RapportRF rapportRF2 = new RapportRF();
        rapportRF2.setId(rapportRF1.getId());
        assertThat(rapportRF1).isEqualTo(rapportRF2);
        rapportRF2.setId(2L);
        assertThat(rapportRF1).isNotEqualTo(rapportRF2);
        rapportRF1.setId(null);
        assertThat(rapportRF1).isNotEqualTo(rapportRF2);
    }
}
