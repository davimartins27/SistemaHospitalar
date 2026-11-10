package br.com.sistemahospitalar.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLINICO_GERAL")
public class MedicoClinicoGeral extends ProfissionalSaude {

    @Override
    public void validarAtendimento(Atendimento atendimento) {
        if (!(atendimento instanceof ConsultaRotina)
                && !(atendimento instanceof AtendimentoEmergencia)) {
            throw new IllegalArgumentException("Médico clínico geral não pode realizar esse tipo de atendimento.");
        }
    }
}
