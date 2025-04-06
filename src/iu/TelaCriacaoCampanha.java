package iu;

import servico.entidade.Narrador;

import java.util.Scanner;
import fachada.SCGPRPG;

public class TelaCriacaoCampanha {
    private Scanner sc;
    private SCGPRPG fachada;
    private Narrador usuario;

    public TelaCriacaoCampanha(SCGPRPG fachada, Narrador usuario) {
        this.sc = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void solicitarDados() {
        System.out.println(">>>> Dados da campanha<<<<");
        System.out.println("Digite o nome da campanha:");


    }

}
