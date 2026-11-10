package br.com.sistemahospitalar.service;

import br.com.sistemahospitalar.dto.ProcedimentoRequest;
import br.com.sistemahospitalar.exception.RecursoNaoEncontradoException;
import br.com.sistemahospitalar.model.*;
import br.com.sistemahospitalar.model.enums.SituacaoConsulta;
import br.com.sistemahospitalar.model.enums.SituacaoProcedimento;
import br.com.sistemahospitalar.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcedimentoService {

    private final ProcedimentoRepository repository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ConsultaRepository consultaRepository;

    public ProcedimentoService(ProcedimentoRepository repository,
                               PacienteRepository pacienteRepository,
                               ProfissionalRepository profissionalRepository,
                               ConsultaRepository consultaRepository) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.consultaRepository = consultaRepository;
    }

    public List<ProcedimentoCirurgico> listar() { return repository.findAll(); }

    public ProcedimentoCirurgico buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Procedimento cirúrgico não encontrado."));
    }

    @Transactional
    public ProcedimentoCirurgico cadastrar(ProcedimentoRequest dados) {
        Paciente paciente = pacienteRepository.findById(dados.getPacienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Paciente não encontrado."));
        ProfissionalSaude profissional = profissionalRepository.findById(dados.getCirurgiaoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cirurgião não encontrado."));

        ProcedimentoCirurgico procedimento = new ProcedimentoCirurgico();
        procedimento.setPaciente(paciente);
        procedimento.setProfissional(profissional);
        procedimento.setData(dados.getData());
        procedimento.setHorario(dados.getHorario());
        procedimento.setDescricao(dados.getDescricao());
        procedimento.setTipoCirurgia(dados.getTipoCirurgia());
        procedimento.setObservacoesPreOperatorias(dados.getObservacoesPreOperatorias());
        procedimento.setObservacoesPosOperatorias(dados.getObservacoesPosOperatorias());
        procedimento.validar();
        verificarHorario(profissional.getId(), dados.getData(), dados.getHorario());
        return repository.save(procedimento);
    }

    @Transactional
    public ProcedimentoCirurgico alterarSituacao(Long id, SituacaoProcedimento novaSituacao) {
        ProcedimentoCirurgico procedimento = buscar(id);
        procedimento.alterarSituacao(novaSituacao);
        return repository.save(procedimento);
    }

    private void verificarHorario(Long profissionalId, java.time.LocalDate data, java.time.LocalTime horario) {
        boolean consultaMarcada = consultaRepository.findAll().stream()
                .anyMatch(c -> c.getProfissional().getId().equals(profissionalId)
                        && c.getData().equals(data)
                        && c.getHorario().equals(horario)
                        && (c.getSituacao() == SituacaoConsulta.AGENDADA
                        || c.getSituacao() == SituacaoConsulta.CONFIRMADA
                        || c.getSituacao() == SituacaoConsulta.EM_ATENDIMENTO));

        boolean procedimentoMarcado = repository.findAll().stream()
                .anyMatch(p -> p.getProfissional().getId().equals(profissionalId)
                        && p.getData().equals(data)
                        && p.getHorario().equals(horario)
                        && (p.getSituacao() == SituacaoProcedimento.AGENDADO
                        || p.getSituacao() == SituacaoProcedimento.EM_ANDAMENTO));

        if (consultaMarcada || procedimentoMarcado) {
            throw new IllegalArgumentException("O profissional já possui um atendimento nesse dia e horário.");
        }
    }
}
