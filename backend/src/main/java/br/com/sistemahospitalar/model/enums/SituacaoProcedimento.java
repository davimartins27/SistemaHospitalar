package br.com.sistemahospitalar.model.enums;

public enum SituacaoProcedimento {
    AGENDADO,
    EM_ANDAMENTO,
    CONCLUIDO,
    CANCELADO;

    public boolean podeIrPara(SituacaoProcedimento novaSituacao) {
        if (novaSituacao == this) {
            return true;
        }

        return switch (this) {
            case AGENDADO -> novaSituacao == EM_ANDAMENTO || novaSituacao == CANCELADO;
            case EM_ANDAMENTO -> novaSituacao == CONCLUIDO || novaSituacao == CANCELADO;
            case CONCLUIDO, CANCELADO -> false;
        };
    }
}
