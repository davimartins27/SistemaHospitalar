package br.com.sistemahospitalar.model;

import jakarta.persistence.*;

@Entity
@Table(name = "historicos_medicos")
public class HistoricoMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "paciente_id", unique = true)
    private Paciente paciente;

    @Column(length = 3000)
    private String informacoesRelevantes;

    public void adicionarInformacao(String informacao) {
        if (informacao == null || informacao.isBlank()) {
            throw new IllegalArgumentException("A informação do histórico não pode ficar vazia.");
        }
        if (informacoesRelevantes == null || informacoesRelevantes.isBlank()) {
            informacoesRelevantes = informacao;
        } else {
            informacoesRelevantes += "\n" + informacao;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public String getInformacoesRelevantes() { return informacoesRelevantes; }
    public void setInformacoesRelevantes(String informacoesRelevantes) { this.informacoesRelevantes = informacoesRelevantes; }
}
