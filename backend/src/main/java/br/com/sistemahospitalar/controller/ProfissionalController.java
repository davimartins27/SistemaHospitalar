package br.com.sistemahospitalar.controller;

import br.com.sistemahospitalar.dto.ProfissionalRequest;
import br.com.sistemahospitalar.model.ProfissionalSaude;
import br.com.sistemahospitalar.service.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionalService service;

    public ProfissionalController(ProfissionalService service) { this.service = service; }

    @GetMapping
    public List<ProfissionalSaude> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ProfissionalSaude buscar(@PathVariable Long id) { return service.buscar(id); }

    @GetMapping("/especialidade/{especialidade}")
    public List<ProfissionalSaude> listarPorEspecialidade(@PathVariable String especialidade) {
        return service.listarPorEspecialidade(especialidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalSaude cadastrar(@Valid @RequestBody ProfissionalRequest dados) {
        return service.cadastrar(dados);
    }
}
