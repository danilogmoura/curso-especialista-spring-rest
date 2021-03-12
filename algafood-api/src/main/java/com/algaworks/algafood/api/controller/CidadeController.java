package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository cidadeRepository;

    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cidade buscar(@PathVariable Long id) {
        return cadastroCidade.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade cadastrar(@RequestBody Cidade cidade) {
        return cadastroCidade.cadastrar(cidade);
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return cadastroCidade.cadastrar(cidadeAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroCidade.remover(id);
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
