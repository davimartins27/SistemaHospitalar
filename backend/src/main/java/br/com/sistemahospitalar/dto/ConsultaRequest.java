package br.com.sistemahospitalar.dto;

import br.com.sistemahospitalar.model.enums.PrioridadeEmergencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaRequest {
    @NotBlank private String modalidade;
    @NotNull private Long pacienteId;
    @NotNull private Long profissionalId;
    @NotNull private LocalDate data;
    @NotNull private LocalTime horario;
    @NotBlank private String motivo;
    private String observacoesMedicas;
    private String especialidadeSolicitada;
    private PrioridadeEmergencia prioridade;

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Long profissionalId) { this.profissionalId = profissionalId; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getHorario() { return horario; }
    public void setHorario(LocalTime horario) { this.horario = horario; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getObservacoesMedicas() { return observacoesMedicas; }
    public void setObservacoesMedicas(String observacoesMedicas) { this.observacoesMedicas = observacoesMedicas; }
    public String getEspecialidadeSolicitada() { return especialidadeSolicitada; }
    public void setEspecialidadeSolicitada(String especialidadeSolicitada) { this.especialidadeSolicitada = especialidadeSolicitada; }
    public PrioridadeEmergencia getPrioridade() { return prioridade; }
    public void setPrioridade(PrioridadeEmergencia prioridade) { this.prioridade = prioridade; }
}
