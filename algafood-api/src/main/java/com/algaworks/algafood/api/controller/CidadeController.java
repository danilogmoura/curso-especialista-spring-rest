package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.excption.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository cidadeRepository;

    private CadastroCidadeService cadastroCidade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
        Cidade cidade = cidadeRepository.buscar(id);

        if (cidade == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidade.cadastrar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeRepository.buscar(id);

        if (cidadeAtual == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return cadastrar(cidadeAtual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cidade> remover(@PathVariable Long id) {
        try {
            cadastroCidade.remover(id);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    public void setCidadeRepository(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Autowired
    public void setCadastroCidade(CadastroCidadeService cadastroCidade) {
        this.cadastroCidade = cadastroCidade;
    }
}