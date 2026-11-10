package br.com.sistemahospitalar.controller;

import br.com.sistemahospitalar.dto.ProcedimentoRequest;
import br.com.sistemahospitalar.model.ProcedimentoCirurgico;
import br.com.sistemahospitalar.model.enums.SituacaoProcedimento;
import br.com.sistemahospitalar.service.ProcedimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedimentos-cirurgicos")
public class ProcedimentoController {

    private final ProcedimentoService service;

    public ProcedimentoController(ProcedimentoService service) { this.service = service; }

    @GetMapping
    public List<ProcedimentoCirurgico> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ProcedimentoCirurgico buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProcedimentoCirurgico cadastrar(@Valid @RequestBody ProcedimentoRequest dados) {
        return service.cadastrar(dados);
    }

    @PutMapping("/{id}/situacao")
    public ProcedimentoCirurgico alterarSituacao(@PathVariable Long id,
                                                  @RequestBody SituacaoProcedimento situacao) {
        return service.alterarSituacao(id, situacao);
    }
}
