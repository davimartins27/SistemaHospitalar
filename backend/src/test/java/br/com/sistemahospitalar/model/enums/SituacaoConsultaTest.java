package br.com.sistemahospitalar.model.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SituacaoConsultaTest {

    @Test
    void consultaAgendadaPodeSerConfirmada() {
        assertTrue(SituacaoConsulta.AGENDADA.podeIrPara(SituacaoConsulta.CONFIRMADA));
    }

    @Test
    void consultaConcluidaNaoPodeVoltarParaAgendada() {
        assertFalse(SituacaoConsulta.CONCLUIDA.podeIrPara(SituacaoConsulta.AGENDADA));
    }
}
