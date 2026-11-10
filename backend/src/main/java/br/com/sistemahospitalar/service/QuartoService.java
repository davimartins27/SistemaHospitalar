package br.com.sistemahospitalar.service;

import br.com.sistemahospitalar.dto.QuartoRequest;
import br.com.sistemahospitalar.exception.RecursoNaoEncontradoException;
import br.com.sistemahospitalar.model.Quarto;
import br.com.sistemahospitalar.model.enums.SituacaoInternacao;
import br.com.sistemahospitalar.model.enums.SituacaoQuarto;
import br.com.sistemahospitalar.repository.InternacaoRepository;
import br.com.sistemahospitalar.repository.QuartoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartoService {

    private final QuartoRepository repository;
    private final InternacaoRepository internacaoRepository;

    public QuartoService(QuartoRepository repository, InternacaoRepository internacaoRepository) {
        this.repository = repository;
        this.internacaoRepository = internacaoRepository;
    }

    public List<Quarto> listar() { return repository.findAll(); }

    public Quarto buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Quarto não encontrado."));
    }

    public Quarto cadastrar(QuartoRequest dados) {
        if (dados.getCapacidadeMaxima() <= 0) {
            throw new IllegalArgumentException("A capacidade do quarto deve ser maior que zero.");
        }
        if (repository.findByNumeroIdentificacao(dados.getNumeroIdentificacao()).isPresent()) {
            throw new IllegalArgumentException("Já existe um quarto com esse número.");
        }
        Quarto quarto = new Quarto();
        preencher(quarto, dados);
        quarto.validarTipoECapacidade();
        quarto.setSituacaoAtual(SituacaoQuarto.DISPONIVEL);
        return repository.save(quarto);
    }

    public Quarto atualizar(Long id, QuartoRequest dados) {
        Quarto quarto = buscar(id);
        long ocupacao = internacaoRepository.countByQuarto_IdAndSituacao(id, SituacaoInternacao.ATIVA);
        if (dados.getCapacidadeMaxima() <= 0 || dados.getCapacidadeMaxima() < ocupacao) {
            throw new IllegalArgumentException("A capacidade não pode ser menor que a quantidade de pacientes internados.");
        }
        repository.findByNumeroIdentificacao(dados.getNumeroIdentificacao()).ifPresent(outro -> {
            if (!outro.getId().equals(id)) {
                throw new IllegalArgumentException("Já existe outro quarto com esse número.");
            }
        });
        preencher(quarto, dados);
        quarto.validarTipoECapacidade();
        quarto.atualizarSituacao((int) ocupacao);
        return repository.save(quarto);
    }

    public void excluir(Long id) {
        Quarto quarto = buscar(id);
        long ocupacao = internacaoRepository.countByQuarto_IdAndSituacao(id, SituacaoInternacao.ATIVA);
        if (ocupacao > 0) {
            throw new IllegalArgumentException("Não é possível excluir um quarto com internação ativa.");
        }
        repository.delete(quarto);
    }

    private void preencher(Quarto quarto, QuartoRequest dados) {
        quarto.setNumeroIdentificacao(dados.getNumeroIdentificacao());
        quarto.setAndar(dados.getAndar());
        quarto.setCapacidadeMaxima(dados.getCapacidadeMaxima());
        quarto.setTipo(dados.getTipo());
    }
}
