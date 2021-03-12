package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada");

    private String title;
    private String uri;

    ProblemType(String title, String uri) {
        this.title = title;
        this.uri = "https://algafood.com.br" + uri;
    }
}
