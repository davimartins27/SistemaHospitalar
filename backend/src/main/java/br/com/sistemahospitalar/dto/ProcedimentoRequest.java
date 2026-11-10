package br.com.sistemahospitalar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProcedimentoRequest {
    @NotNull private Long pacienteId;
    @NotNull private Long cirurgiaoId;
    @NotNull private LocalDate data;
    @NotNull private LocalTime horario;
    @NotBlank private String descricao;
    private String tipoCirurgia;
    private String observacoesPreOperatorias;
    private String observacoesPosOperatorias;

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getCirurgiaoId() { return cirurgiaoId; }
    public void setCirurgiaoId(Long cirurgiaoId) { this.cirurgiaoId = cirurgiaoId; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getHorario() { return horario; }
    public void setHorario(LocalTime horario) { this.horario = horario; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTipoCirurgia() { return tipoCirurgia; }
    public void setTipoCirurgia(String tipoCirurgia) { this.tipoCirurgia = tipoCirurgia; }
    public String getObservacoesPreOperatorias() { return observacoesPreOperatorias; }
    public void setObservacoesPreOperatorias(String observacoesPreOperatorias) { this.observacoesPreOperatorias = observacoesPreOperatorias; }
    public String getObservacoesPosOperatorias() { return observacoesPosOperatorias; }
    public void setObservacoesPosOperatorias(String observacoesPosOperatorias) { this.observacoesPosOperatorias = observacoesPosOperatorias; }
}
