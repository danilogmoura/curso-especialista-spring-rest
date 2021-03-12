package com.algaworks.algafood.domain.excption;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradaException(String message) {
        super(message);
    }

    public CidadeNaoEncontradaException(Long cidadeId) {
        super(String.format("Não existe um cadastro de cidade com código %d", cidadeId));
    }
}
