package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.excption.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private RestauranteRepository restauranteRepository;

    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        return restauranteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestaurante.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = restauranteRepository.findById(id).orElse(null);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento");
            restauranteAtual = cadastroRestaurante.salvar(restauranteAtual);
            return ResponseEntity.ok(restauranteAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurante> remover(@PathVariable Long id) {
        try {
            cadastroRestaurante.remove(id);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteDestino = restauranteRepository.findById(id).orElse(null);

        if (restauranteDestino == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteDestino);
        return atualizar(id, restauranteDestino);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, key);

            if (field != null) {
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            }
        });
    }

    @Autowired
    public void setRestauranteRepository(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Autowired
    public void setCadastroRestaurante(CadastroRestauranteService cadastroRestaurante) {
        this.cadastroRestaurante = cadastroRestaurante;
    }
}
