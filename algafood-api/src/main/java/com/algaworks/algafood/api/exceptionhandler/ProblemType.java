package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
    PARAMETRO_INVALIDO("Parametro inválido", "/parametro-invalido"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
    ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio"),
    MENSAGEM_INCOMPREENSIVEL("Mensagem Incompreensivel", "/mensagem-incompreensivel");

    private String title;
    private String uri;

    ProblemType(String title, String uri) {
        this.title = title;
        this.uri = "https://algafood.com.br" + uri;
    }
}
