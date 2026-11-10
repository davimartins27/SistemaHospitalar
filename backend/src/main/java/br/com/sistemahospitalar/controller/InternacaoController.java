package br.com.sistemahospitalar.controller;

import br.com.sistemahospitalar.dto.InternacaoRequest;
import br.com.sistemahospitalar.model.Internacao;
import br.com.sistemahospitalar.service.InternacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internacoes")
public class InternacaoController {

    private final InternacaoService service;

    public InternacaoController(InternacaoService service) { this.service = service; }

    @GetMapping
    public List<Internacao> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Internacao buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Internacao cadastrar(@Valid @RequestBody InternacaoRequest dados) { return service.cadastrar(dados); }

    @PostMapping("/{id}/alta")
    public Internacao registrarAlta(@PathVariable Long id) { return service.registrarAlta(id); }
}
