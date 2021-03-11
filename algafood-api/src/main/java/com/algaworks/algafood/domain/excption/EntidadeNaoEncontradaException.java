package com.algaworks.algafood.domain.excption;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends ResponseStatusException {

    public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
        super(status, mensagem);
    }

    public EntidadeNaoEncontradaException(String message) {
        this(HttpStatus.NOT_FOUND, message);
    }
}
