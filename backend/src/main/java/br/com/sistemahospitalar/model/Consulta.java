package br.com.sistemahospitalar.model;

import br.com.sistemahospitalar.model.enums.SituacaoConsulta;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CONSULTA")
public abstract class Consulta extends Atendimento {

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_consulta")
    private SituacaoConsulta situacao = SituacaoConsulta.AGENDADA;

    private String motivo;

    @Column(length = 2000)
    private String observacoesMedicas;

    public void alterarSituacao(SituacaoConsulta novaSituacao) {
        if (novaSituacao == null || !situacao.podeIrPara(novaSituacao)) {
            throw new IllegalArgumentException("Não é permitido alterar a consulta de " + situacao + " para " + novaSituacao + ".");
        }
        situacao = novaSituacao;
    }

    public SituacaoConsulta getSituacao() { return situacao; }
    public void setSituacao(SituacaoConsulta situacao) { this.situacao = situacao; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getObservacoesMedicas() { return observacoesMedicas; }
    public void setObservacoesMedicas(String observacoesMedicas) { this.observacoesMedicas = observacoesMedicas; }
}
