package com.algafood.listener;

import com.algafood.di.notificacao.ClienteAtivadoEvent;
import com.algafood.di.notificacao.NivelUrgencia;
import com.algafood.di.notificacao.Notificador;
import com.algafood.di.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
    }

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }
}
