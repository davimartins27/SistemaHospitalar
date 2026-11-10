package br.com.sistemahospitalar.controller;

import br.com.sistemahospitalar.dto.QuartoRequest;
import br.com.sistemahospitalar.model.Quarto;
import br.com.sistemahospitalar.service.QuartoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    private final QuartoService service;

    public QuartoController(QuartoService service) { this.service = service; }

    @GetMapping
    public List<Quarto> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Quarto buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quarto cadastrar(@Valid @RequestBody QuartoRequest dados) { return service.cadastrar(dados); }

    @PutMapping("/{id}")
    public Quarto atualizar(@PathVariable Long id, @Valid @RequestBody QuartoRequest dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }
}
