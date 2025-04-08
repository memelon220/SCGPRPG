package iu;

import servico.entidade.Jogador;
import servico.entidade.Narrador;
import java.util.ArrayList;

public class TelaNotificacoes {

    private Jogador usuario;

    public TelaNotificacoes(Jogador usuario) {
        this.usuario = usuario;
    }

    public TelaNotificacoes(Narrador usuario) { this.usuario = usuario; }

    public void listarNotificacoes(){
        ArrayList<String> notif = usuario.getNotificacoes();
        for(String i : notif){
            System.out.println("-- " +i);
        }
        removerNotificacoes();
    }

    public void removerNotificacoes(){
        ArrayList<String> notif = usuario.getNotificacoes();
        for(String i : notif){
            notif.remove(i);
        }
    }

}