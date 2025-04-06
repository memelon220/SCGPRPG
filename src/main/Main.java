package main;

import iu.TelaPrincipal;
import servico.entidade.Dado;
import servico.entidade.Personagem;
import fachada.SCGPRPG;
import servico.entidade.Jogador;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        TelaPrincipal Tela = new TelaPrincipal(new SCGPRPG(), new Jogador("Jonas", 13));
        Tela.iniciar();

    }
}