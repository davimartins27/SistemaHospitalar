package br.com.sistemahospitalar.model;

import br.com.sistemahospitalar.model.enums.SituacaoQuarto;
import br.com.sistemahospitalar.model.enums.TipoQuarto;
import jakarta.persistence.*;

@Entity
@Table(name = "quartos")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numeroIdentificacao;

    private Integer andar;

    @Column(nullable = false)
    private Integer capacidadeMaxima;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoQuarto tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoQuarto situacaoAtual = SituacaoQuarto.DISPONIVEL;

    public void validarTipoECapacidade() {
        if (capacidadeMaxima == null || capacidadeMaxima <= 0 || tipo == null) {
            throw new IllegalArgumentException("Informe um tipo de quarto e uma capacidade maior que zero.");
        }
        if ((tipo == TipoQuarto.APARTAMENTO || tipo == TipoQuarto.UTI) && capacidadeMaxima != 1) {
            throw new IllegalArgumentException("Quartos do tipo apartamento e UTI possuem capacidade de um paciente.");
        }
    }

    public boolean temVaga(int ocupacaoAtual) {
        return ocupacaoAtual < capacidadeMaxima;
    }

    public void atualizarSituacao(int ocupacaoAtual) {
        situacaoAtual = temVaga(ocupacaoAtual) ? SituacaoQuarto.DISPONIVEL : SituacaoQuarto.OCUPADO;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNumeroIdentificacao() { return numeroIdentificacao; }
    public void setNumeroIdentificacao(Integer numeroIdentificacao) { this.numeroIdentificacao = numeroIdentificacao; }
    public Integer getAndar() { return andar; }
    public void setAndar(Integer andar) { this.andar = andar; }
    public Integer getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setCapacidadeMaxima(Integer capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }
    public TipoQuarto getTipo() { return tipo; }
    public void setTipo(TipoQuarto tipo) { this.tipo = tipo; }
    public SituacaoQuarto getSituacaoAtual() { return situacaoAtual; }
    public void setSituacaoAtual(SituacaoQuarto situacaoAtual) { this.situacaoAtual = situacaoAtual; }
}
