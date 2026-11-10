package br.com.sistemahospitalar.dto;

import br.com.sistemahospitalar.model.enums.TipoQuarto;
import jakarta.validation.constraints.NotNull;

public class QuartoRequest {
    @NotNull private Integer numeroIdentificacao;
    @NotNull private Integer andar;
    @NotNull private Integer capacidadeMaxima;
    @NotNull private TipoQuarto tipo;

    public Integer getNumeroIdentificacao() { return numeroIdentificacao; }
    public void setNumeroIdentificacao(Integer numeroIdentificacao) { this.numeroIdentificacao = numeroIdentificacao; }
    public Integer getAndar() { return andar; }
    public void setAndar(Integer andar) { this.andar = andar; }
    public Integer getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setCapacidadeMaxima(Integer capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }
    public TipoQuarto getTipo() { return tipo; }
    public void setTipo(TipoQuarto tipo) { this.tipo = tipo; }
}
