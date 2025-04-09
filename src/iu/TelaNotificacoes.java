package iu;

import servico.entidade.Jogador;
import servico.entidade.Narrador;
import java.util.ArrayList;

public class TelaNotificacoes {

    private Jogador usuario;

    public TelaNotificacoes(Jogador usuario) {
        this.usuario = usuario;
    }

    public void listarNotificacoes() {
        ArrayList<String> notificacoes = usuario.getNotificacoes();
        for (String notif : notificacoes) {
            System.out.println("-- " + notif);
        }
        usuario.setNotificacoes(new ArrayList<String>());
    }

}