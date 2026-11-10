package br.com.sistemahospitalar.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONSULTA_ESPECIALIZADA")
public class ConsultaEspecializada extends Consulta {

    private String especialidadeSolicitada;

    @Override
    public void validarCondicoes() {
        if (especialidadeSolicitada == null || especialidadeSolicitada.isBlank()) {
            throw new IllegalArgumentException("Informe a especialidade solicitada.");
        }
        if (getMotivo() == null || getMotivo().isBlank()) {
            throw new IllegalArgumentException("Informe o motivo da consulta.");
        }
    }

    public String getEspecialidadeSolicitada() { return especialidadeSolicitada; }
    public void setEspecialidadeSolicitada(String especialidadeSolicitada) { this.especialidadeSolicitada = especialidadeSolicitada; }
}
