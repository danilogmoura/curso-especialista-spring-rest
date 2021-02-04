package com.algafood.di.service;

import com.algafood.di.modelo.Cliente;
import com.algafood.di.notificacao.NivelUrgencia;
import com.algafood.di.notificacao.Notificador;
import com.algafood.di.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class AtivacaoClienteService {

    private Notificador notificador;

    //    @PostConstruct
    public void init() {
        System.out.println("INIT " + notificador);
    }

    //    @PreDestroy
    public void destroy() {
        System.out.println("DESTROY");
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    public void setNotificadores(Notificador notificador) {
        this.notificador = notificador;
    }
}
