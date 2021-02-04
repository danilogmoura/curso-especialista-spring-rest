package com.algafood.listener;

import com.algafood.di.notificacao.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaNotaFiscalService {

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        System.out.println("Emitindo nota fiscal para cliente " + event.getCliente().getNome());
    }
}
