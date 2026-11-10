package br.com.sistemahospitalar.service;

import br.com.sistemahospitalar.dto.InternacaoRequest;
import br.com.sistemahospitalar.exception.RecursoNaoEncontradoException;
import br.com.sistemahospitalar.model.*;
import br.com.sistemahospitalar.model.enums.SituacaoInternacao;
import br.com.sistemahospitalar.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InternacaoService {

    private final InternacaoRepository repository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final QuartoRepository quartoRepository;

    public InternacaoService(InternacaoRepository repository,
                             PacienteRepository pacienteRepository,
                             ProfissionalRepository profissionalRepository,
                             QuartoRepository quartoRepository) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.quartoRepository = quartoRepository;
    }

    public List<Internacao> listar() { return repository.findAll(); }

    public Internacao buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Internação não encontrada."));
    }

    @Transactional
    public Internacao cadastrar(InternacaoRequest dados) {
        Paciente paciente = pacienteRepository.findById(dados.getPacienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Paciente não encontrado."));
        ProfissionalSaude profissional = profissionalRepository.findById(dados.getProfissionalId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Profissional não encontrado."));
        Quarto quarto = quartoRepository.findById(dados.getQuartoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Quarto não encontrado."));

        if (dados.getDataPrevistaAlta() != null && dados.getDataPrevistaAlta().isBefore(dados.getDataEntrada())) {
            throw new IllegalArgumentException("A previsão de alta não pode ser anterior à entrada.");
        }

        long ocupacao = repository.countByQuarto_IdAndSituacao(quarto.getId(), SituacaoInternacao.ATIVA);
        if (!quarto.temVaga((int) ocupacao)) {
            throw new IllegalArgumentException("O quarto já atingiu sua capacidade máxima.");
        }

        Internacao internacao = new Internacao();
        internacao.setPaciente(paciente);
        internacao.setProfissionalResponsavel(profissional);
        internacao.setQuarto(quarto);
        internacao.setDataEntrada(dados.getDataEntrada());
        internacao.setDataPrevistaAlta(dados.getDataPrevistaAlta());
        internacao.setObservacoes(dados.getObservacoes());
        internacao.setSituacao(SituacaoInternacao.ATIVA);

        Internacao salva = repository.save(internacao);
        quarto.atualizarSituacao((int) ocupacao + 1);
        quartoRepository.save(quarto);
        return salva;
    }

    @Transactional
    public Internacao registrarAlta(Long id) {
        Internacao internacao = buscar(id);
        internacao.registrarAlta();
        Internacao salva = repository.save(internacao);
        Quarto quarto = internacao.getQuarto();
        long ocupacao = repository.countByQuarto_IdAndSituacao(quarto.getId(), SituacaoInternacao.ATIVA);
        quarto.atualizarSituacao((int) ocupacao);
        quartoRepository.save(quarto);
        return salva;
    }
}
