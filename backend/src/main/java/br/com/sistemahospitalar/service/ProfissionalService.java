package br.com.sistemahospitalar.service;

import br.com.sistemahospitalar.dto.ProfissionalRequest;
import br.com.sistemahospitalar.exception.RecursoNaoEncontradoException;
import br.com.sistemahospitalar.model.*;
import br.com.sistemahospitalar.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    private final ProfissionalRepository repository;

    public ProfissionalService(ProfissionalRepository repository) {
        this.repository = repository;
    }

    public List<ProfissionalSaude> listar() {
        return repository.findAll();
    }

    public ProfissionalSaude buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Profissional não encontrado."));
    }

    public List<ProfissionalSaude> listarPorEspecialidade(String especialidade) {
        return repository.findAll().stream()
                .filter(p -> p instanceof MedicoEspecialista)
                .map(p -> (MedicoEspecialista) p)
                .filter(p -> p.getEspecialidade() != null && p.getEspecialidade().equalsIgnoreCase(especialidade))
                .map(p -> (ProfissionalSaude) p)
                .toList();
    }

    public ProfissionalSaude cadastrar(ProfissionalRequest dados) {
        if (repository.findByRegistroProfissional(dados.getRegistroProfissional()).isPresent()) {
            throw new IllegalArgumentException("Já existe um profissional com esse registro.");
        }

        String categoria = dados.getCategoria().trim().toUpperCase();
        ProfissionalSaude profissional;

        switch (categoria) {
            case "CLINICO_GERAL" -> profissional = new MedicoClinicoGeral();
            case "ESPECIALISTA" -> {
                if (dados.getEspecialidade() == null || dados.getEspecialidade().isBlank()) {
                    throw new IllegalArgumentException("Informe a especialidade do médico.");
                }
                MedicoEspecialista especialista = new MedicoEspecialista();
                especialista.setEspecialidade(dados.getEspecialidade());
                profissional = especialista;
            }
            case "CIRURGIAO" -> {
                if (dados.getAreaCirurgica() == null || dados.getAreaCirurgica().isBlank()) {
                    throw new IllegalArgumentException("Informe a área cirúrgica do profissional.");
                }
                Cirurgiao cirurgiao = new Cirurgiao();
                cirurgiao.setAreaCirurgica(dados.getAreaCirurgica());
                profissional = cirurgiao;
            }
            default -> throw new IllegalArgumentException("Categoria inválida. Use CLINICO_GERAL, ESPECIALISTA ou CIRURGIAO.");
        }

        profissional.setNome(dados.getNome());
        profissional.setRegistroProfissional(dados.getRegistroProfissional());
        profissional.setTelefone(dados.getTelefone());
        profissional.setEmail(dados.getEmail());
        return repository.save(profissional);
    }
}
