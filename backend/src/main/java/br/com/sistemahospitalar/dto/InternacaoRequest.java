package br.com.sistemahospitalar.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class InternacaoRequest {
    @NotNull private Long pacienteId;
    @NotNull private Long profissionalId;
    @NotNull private Long quartoId;
    @NotNull private LocalDate dataEntrada;
    private LocalDate dataPrevistaAlta;
    private String observacoes;

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Long profissionalId) { this.profissionalId = profissionalId; }
    public Long getQuartoId() { return quartoId; }
    public void setQuartoId(Long quartoId) { this.quartoId = quartoId; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(LocalDate dataEntrada) { this.dataEntrada = dataEntrada; }
    public LocalDate getDataPrevistaAlta() { return dataPrevistaAlta; }
    public void setDataPrevistaAlta(LocalDate dataPrevistaAlta) { this.dataPrevistaAlta = dataPrevistaAlta; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
