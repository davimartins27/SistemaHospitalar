package br.com.sistemahospitalar.model;

import br.com.sistemahospitalar.model.enums.SituacaoProcedimento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("PROCEDIMENTO_CIRURGICO")
public class ProcedimentoCirurgico extends Atendimento {

    private String descricao;

    private String tipoCirurgia;

    @Column(length = 2000)
    private String observacoesPreOperatorias;

    @Column(length = 2000)
    private String observacoesPosOperatorias;

    private LocalDateTime dataHoraEfetiva;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_procedimento")
    private SituacaoProcedimento situacao = SituacaoProcedimento.AGENDADO;

    @Override
    public void validarCondicoes() {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Informe a descrição do procedimento cirúrgico.");
        }
    }

    public void alterarSituacao(SituacaoProcedimento novaSituacao) {
        if (novaSituacao == null || !situacao.podeIrPara(novaSituacao)) {
            throw new IllegalArgumentException("Situação inválida para o procedimento cirúrgico.");
        }
        situacao = novaSituacao;
        if (novaSituacao == SituacaoProcedimento.EM_ANDAMENTO && dataHoraEfetiva == null) {
            dataHoraEfetiva = LocalDateTime.now();
        }
        if (novaSituacao == SituacaoProcedimento.CONCLUIDO && dataHoraEfetiva == null) {
            dataHoraEfetiva = LocalDateTime.now();
        }
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTipoCirurgia() { return tipoCirurgia; }
    public void setTipoCirurgia(String tipoCirurgia) { this.tipoCirurgia = tipoCirurgia; }
    public String getObservacoesPreOperatorias() { return observacoesPreOperatorias; }
    public void setObservacoesPreOperatorias(String observacoesPreOperatorias) { this.observacoesPreOperatorias = observacoesPreOperatorias; }
    public String getObservacoesPosOperatorias() { return observacoesPosOperatorias; }
    public void setObservacoesPosOperatorias(String observacoesPosOperatorias) { this.observacoesPosOperatorias = observacoesPosOperatorias; }
    public LocalDateTime getDataHoraEfetiva() { return dataHoraEfetiva; }
    public void setDataHoraEfetiva(LocalDateTime dataHoraEfetiva) { this.dataHoraEfetiva = dataHoraEfetiva; }
    public SituacaoProcedimento getSituacao() { return situacao; }
    public void setSituacao(SituacaoProcedimento situacao) { this.situacao = situacao; }
}
