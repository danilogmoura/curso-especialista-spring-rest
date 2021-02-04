package com.algafood.di.service;

import com.algafood.di.modelo.Cliente;
import com.algafood.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    private List<Notificador> notificadores;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificadores.forEach(notificador ->
                notificador.notificar(cliente, "Seu cadastro no sistema está ativo!")
        );
    }

    @Autowired
    public void setNotificadores(List<Notificador> notificadores) {
        this.notificadores = notificadores;
    }
}
