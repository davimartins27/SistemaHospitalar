package br.com.sistemahospitalar.model.enums;

public enum SituacaoConsulta {
    AGENDADA,
    CONFIRMADA,
    EM_ATENDIMENTO,
    CONCLUIDA,
    CANCELADA,
    NAO_COMPARECEU;

    public boolean podeIrPara(SituacaoConsulta novaSituacao) {
        if (novaSituacao == this) {
            return true;
        }

        return switch (this) {
            case AGENDADA -> novaSituacao == CONFIRMADA
                    || novaSituacao == CANCELADA
                    || novaSituacao == NAO_COMPARECEU;
            case CONFIRMADA -> novaSituacao == EM_ATENDIMENTO
                    || novaSituacao == CANCELADA
                    || novaSituacao == NAO_COMPARECEU;
            case EM_ATENDIMENTO -> novaSituacao == CONCLUIDA;
            case CONCLUIDA, CANCELADA, NAO_COMPARECEU -> false;
        };
    }
}
