package br.com.sistemahospitalar.model;

import br.com.sistemahospitalar.model.enums.SituacaoInternacao;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "internacoes")
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profissional_id")
    private ProfissionalSaude profissionalResponsavel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    private LocalDate dataPrevistaAlta;
    private LocalDate dataEfetivaAlta;
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoInternacao situacao = SituacaoInternacao.ATIVA;

    public void registrarAlta() {
        if (situacao == SituacaoInternacao.ALTA) {
            throw new IllegalArgumentException("Essa internação já recebeu alta.");
        }
        situacao = SituacaoInternacao.ALTA;
        dataEfetivaAlta = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public ProfissionalSaude getProfissionalResponsavel() { return profissionalResponsavel; }
    public void setProfissionalResponsavel(ProfissionalSaude profissionalResponsavel) { this.profissionalResponsavel = profissionalResponsavel; }
    public Quarto getQuarto() { return quarto; }
    public void setQuarto(Quarto quarto) { this.quarto = quarto; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(LocalDate dataEntrada) { this.dataEntrada = dataEntrada; }
    public LocalDate getDataPrevistaAlta() { return dataPrevistaAlta; }
    public void setDataPrevistaAlta(LocalDate dataPrevistaAlta) { this.dataPrevistaAlta = dataPrevistaAlta; }
    public LocalDate getDataEfetivaAlta() { return dataEfetivaAlta; }
    public void setDataEfetivaAlta(LocalDate dataEfetivaAlta) { this.dataEfetivaAlta = dataEfetivaAlta; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public SituacaoInternacao getSituacao() { return situacao; }
    public void setSituacao(SituacaoInternacao situacao) { this.situacao = situacao; }
}
