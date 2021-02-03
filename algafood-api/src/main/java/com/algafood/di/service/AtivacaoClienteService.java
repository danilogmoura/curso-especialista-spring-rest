package com.algafood.di.service;

import com.algafood.di.modelo.Cliente;
import com.algafood.di.notificacao.NotificadorEmail;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    private NotificadorEmail notificador;

    public void notificar(Cliente cliente) {
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}
