package br.com.sistemahospitalar.service;

import br.com.sistemahospitalar.dto.ConsultaRequest;
import br.com.sistemahospitalar.exception.RecursoNaoEncontradoException;
import br.com.sistemahospitalar.model.*;
import br.com.sistemahospitalar.model.enums.SituacaoConsulta;
import br.com.sistemahospitalar.model.enums.SituacaoProcedimento;
import br.com.sistemahospitalar.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ProcedimentoRepository procedimentoRepository;

    public ConsultaService(ConsultaRepository consultaRepository,
                           PacienteRepository pacienteRepository,
                           ProfissionalRepository profissionalRepository,
                           ProcedimentoRepository procedimentoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.procedimentoRepository = procedimentoRepository;
    }

    public List<Consulta> listar() {
        return consultaRepository.findAll();
    }

    public Consulta buscar(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Consulta não encontrada."));
    }

    @Transactional
    public Consulta cadastrar(ConsultaRequest dados) {
        Paciente paciente = pacienteRepository.findById(dados.getPacienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Paciente não encontrado."));
        ProfissionalSaude profissional = profissionalRepository.findById(dados.getProfissionalId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Profissional não encontrado."));

        Consulta consulta;
        String modalidade = dados.getModalidade().trim().toUpperCase();
        switch (modalidade) {
            case "ROTINA" -> consulta = new ConsultaRotina();
            case "ESPECIALIZADA" -> {
                if (dados.getEspecialidadeSolicitada() == null || dados.getEspecialidadeSolicitada().isBlank()) {
                    throw new IllegalArgumentException("Informe a especialidade solicitada.");
                }
                ConsultaEspecializada especializada = new ConsultaEspecializada();
                especializada.setEspecialidadeSolicitada(dados.getEspecialidadeSolicitada());
                consulta = especializada;
            }
            case "EMERGENCIA" -> {
                if (dados.getPrioridade() == null) {
                    throw new IllegalArgumentException("Informe a prioridade da emergência.");
                }
                AtendimentoEmergencia emergencia = new AtendimentoEmergencia();
                emergencia.setPrioridade(dados.getPrioridade());
                consulta = emergencia;
            }
            default -> throw new IllegalArgumentException("Modalidade inválida. Use ROTINA, ESPECIALIZADA ou EMERGENCIA.");
        }

        consulta.setPaciente(paciente);
        consulta.setProfissional(profissional);
        consulta.setData(dados.getData());
        consulta.setHorario(dados.getHorario());
        consulta.setMotivo(dados.getMotivo());
        consulta.setObservacoesMedicas(dados.getObservacoesMedicas());
        consulta.validar();
        verificarHorarioDisponivel(profissional.getId(), dados.getData(), dados.getHorario());

        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta alterarSituacao(Long id, SituacaoConsulta novaSituacao) {
        Consulta consulta = buscar(id);
        consulta.alterarSituacao(novaSituacao);
        return consultaRepository.save(consulta);
    }

    private void verificarHorarioDisponivel(Long profissionalId, java.time.LocalDate data, java.time.LocalTime horario) {
        boolean consultaMarcada = consultaRepository.findAll().stream()
                .anyMatch(c -> c.getProfissional().getId().equals(profissionalId)
                        && c.getData().equals(data)
                        && c.getHorario().equals(horario)
                        && (c.getSituacao() == SituacaoConsulta.AGENDADA
                        || c.getSituacao() == SituacaoConsulta.CONFIRMADA
                        || c.getSituacao() == SituacaoConsulta.EM_ATENDIMENTO));

        boolean cirurgiaMarcada = procedimentoRepository.findAll().stream()
                .anyMatch(p -> p.getProfissional().getId().equals(profissionalId)
                        && p.getData().equals(data)
                        && p.getHorario().equals(horario)
                        && (p.getSituacao() == SituacaoProcedimento.AGENDADO
                        || p.getSituacao() == SituacaoProcedimento.EM_ANDAMENTO));

        if (consultaMarcada || cirurgiaMarcada) {
            throw new IllegalArgumentException("O profissional já possui um atendimento nesse dia e horário.");
        }
    }
}
