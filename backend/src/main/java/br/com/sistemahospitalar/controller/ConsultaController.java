package br.com.sistemahospitalar.controller;

import br.com.sistemahospitalar.dto.ConsultaRequest;
import br.com.sistemahospitalar.model.Consulta;
import br.com.sistemahospitalar.model.enums.SituacaoConsulta;
import br.com.sistemahospitalar.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) { this.service = service; }

    @GetMapping
    public List<Consulta> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Consulta buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta cadastrar(@Valid @RequestBody ConsultaRequest dados) { return service.cadastrar(dados); }

    @PutMapping("/{id}/situacao")
    public Consulta alterarSituacao(@PathVariable Long id, @RequestBody SituacaoConsulta situacao) {
        return service.alterarSituacao(id, situacao);
    }
}
