package com.algaworks.di;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.NotificadorEmail;
import com.algaworks.di.service.AtivacaoClienteService;

public class Main {

    public static void main(String[] args) {
        Cliente joao = new Cliente("João", "joao@zty.com", "31231231123");
        Cliente maria = new Cliente("Maria", "maria@zty.com", "4362232525");

        AtivacaoClienteService ativacaoCliente = new AtivacaoClienteService(new NotificadorEmail());
//        AtivacaoClienteService ativacaoCliente = new AtivacaoClienteService(new NotificadorSMS());
        ativacaoCliente.ativar(joao);
        ativacaoCliente.ativar(maria);
    }
}
