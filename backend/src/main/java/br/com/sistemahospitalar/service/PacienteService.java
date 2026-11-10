package br.com.sistemahospitalar.service;

import br.com.sistemahospitalar.exception.RecursoNaoEncontradoException;
import br.com.sistemahospitalar.model.Paciente;
import br.com.sistemahospitalar.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> listar() {
        return repository.findAll();
    }

    public Paciente buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Paciente não encontrado."));
    }

    public Paciente cadastrar(Paciente paciente) {
        if (repository.findByCpf(paciente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF.");
        }
        paciente.setId(null);
        return repository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente dados) {
        Paciente paciente = buscar(id);
        repository.findByCpf(dados.getCpf()).ifPresent(outro -> {
            if (!outro.getId().equals(id)) {
                throw new IllegalArgumentException("Esse CPF já está cadastrado.");
            }
        });
        paciente.setNome(dados.getNome());
        paciente.setCpf(dados.getCpf());
        paciente.setDataNascimento(dados.getDataNascimento());
        paciente.setTelefone(dados.getTelefone());
        paciente.setEndereco(dados.getEndereco());
        paciente.setEmail(dados.getEmail());
        return repository.save(paciente);
    }

    public void excluir(Long id) {
        repository.delete(buscar(id));
    }
}
