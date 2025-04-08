package iu;

import servico.entidade.Narrador;

import java.util.Scanner;
import fachada.SCGPRPG;
import servico.excecao.campanha.CampanhaJaExisteException;
import java.time.LocalDate;

public class TelaCriacaoCampanha {

    private Scanner sc;
    private SCGPRPG fachada;
    private Narrador usuario;

    public TelaCriacaoCampanha(SCGPRPG fachada, Narrador usuario) {
        this.sc = new Scanner(System.in);
        this.fachada = fachada;
        this.usuario = usuario;
    }

    public void solicitarDados() {
        System.out.println(">>>> Dados da campanha<<<<");
        System.out.println("Digite o nome da campanha:");
        String nome = sc.nextLine();
        System.out.println("Digite uma descrição para a campanha:");
        String descricao = sc.nextLine();
        System.out.println("Qual o limite de jogadores para esta campanha?");
        int limite_jogadores = sc.nextInt();
        sc.nextLine();
        try {
            fachada.criarCampanha(usuario, nome, descricao,"ABERTA", limite_jogadores);
            System.out.println("Campanha criada com sucesso!");
        }catch(CampanhaJaExisteException e){
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro com a geração automática de ID. Por favor, digite as informacoes novamente");
            solicitarDados();
        }

    }

}
