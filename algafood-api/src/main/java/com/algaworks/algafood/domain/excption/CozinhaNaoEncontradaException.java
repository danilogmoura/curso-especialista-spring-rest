package com.algaworks.algafood.domain.excption;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradaException(String message) {
        super(message);
    }

    public CozinhaNaoEncontradaException(Long cozinhaId) {
        super(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
    }
}
