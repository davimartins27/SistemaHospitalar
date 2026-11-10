package br.com.sistemahospitalar.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CIRURGIAO")
public class Cirurgiao extends ProfissionalSaude {

    private String areaCirurgica;

    @Override
    public void validarAtendimento(Atendimento atendimento) {
        if (!(atendimento instanceof ProcedimentoCirurgico)) {
            throw new IllegalArgumentException("Cirurgião deve estar associado a um procedimento cirúrgico.");
        }
    }

    public String getAreaCirurgica() { return areaCirurgica; }
    public void setAreaCirurgica(String areaCirurgica) { this.areaCirurgica = areaCirurgica; }
}
