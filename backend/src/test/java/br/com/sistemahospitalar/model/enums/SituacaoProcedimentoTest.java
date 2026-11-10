package br.com.sistemahospitalar.model.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SituacaoProcedimentoTest {

    @Test
    void procedimentoAgendadoPodeSerIniciado() {
        assertTrue(SituacaoProcedimento.AGENDADO.podeIrPara(SituacaoProcedimento.EM_ANDAMENTO));
    }

    @Test
    void procedimentoAgendadoNaoPodeSerConcluidoDiretamente() {
        assertFalse(SituacaoProcedimento.AGENDADO.podeIrPara(SituacaoProcedimento.CONCLUIDO));
    }
}
