package com.algafood.di.service;

import com.algafood.di.modelo.Cliente;
import com.algafood.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }

//    @Qualifier("email")
//    @Qualifier("sms")
//    @Qualifier("prioridade-normal")
    @Qualifier("urgente")
    @Autowired
    public void setNotificadores(Notificador notificador) {
        this.notificador = notificador;
    }
}
