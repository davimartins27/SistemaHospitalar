package br.com.sistemahospitalar.model;

import br.com.sistemahospitalar.model.enums.PrioridadeEmergencia;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EMERGENCIA")
public class AtendimentoEmergencia extends Consulta {

    @Enumerated(EnumType.STRING)
    private PrioridadeEmergencia prioridade;

    @Override
    public void validarCondicoes() {
        if (prioridade == null) {
            throw new IllegalArgumentException("Informe a prioridade do atendimento de emergência.");
        }
        if (getMotivo() == null || getMotivo().isBlank()) {
            throw new IllegalArgumentException("Informe o motivo do atendimento de emergência.");
        }
    }

    public PrioridadeEmergencia getPrioridade() { return prioridade; }
    public void setPrioridade(PrioridadeEmergencia prioridade) { this.prioridade = prioridade; }
}
