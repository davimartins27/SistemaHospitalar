package br.com.sistemahospitalar.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ESPECIALISTA")
public class MedicoEspecialista extends ProfissionalSaude {

    private String especialidade;

    @Override
    public void validarAtendimento(Atendimento atendimento) {
        if (atendimento instanceof ConsultaEspecializada consulta) {
            if (especialidade == null || consulta.getEspecialidadeSolicitada() == null
                    || !especialidade.equalsIgnoreCase(consulta.getEspecialidadeSolicitada())) {
                throw new IllegalArgumentException("A especialidade do médico não corresponde à consulta.");
            }
            return;
        }

        if (atendimento instanceof AtendimentoEmergencia) {
            return;
        }

        throw new IllegalArgumentException("Médico especialista não pode realizar esse tipo de atendimento.");
    }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}
