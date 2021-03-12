package com.algaworks.algafood.domain.excption;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradoException(Long restauranteId) {
        super(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
    }
}
