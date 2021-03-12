package com.algaworks.algafood.domain.excption;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradoException(String message) {
        super(message);
    }

    public EstadoNaoEncontradoException(Long estadoId) {
        super(String.format("Não existe um cadastro de estado com código %d", estadoId));
    }
}
