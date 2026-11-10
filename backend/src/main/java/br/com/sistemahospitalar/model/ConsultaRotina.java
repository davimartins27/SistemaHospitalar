package br.com.sistemahospitalar.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONSULTA_ROTINA")
public class ConsultaRotina extends Consulta {

    @Override
    public void validarCondicoes() {
        if (getMotivo() == null || getMotivo().isBlank()) {
            throw new IllegalArgumentException("Informe o motivo da consulta de rotina.");
        }
    }
}
