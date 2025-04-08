package iu;

import servico.entidade.Narrador;

import java.util.Scanner;
import fachada.SCGPRPG;
import servico.excecao.campanha.CampanhaJaExisteException;

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
        System.out.println("Qual a data de início da campanha?");
        String dataInicio = sc.nextLine();
        System.out.println("Qual o status atual da sua campanha? (em andamento, esperando jogadores, hiato, etc");
        String status = sc.nextLine();
        try {
            fachada.criarCampanha(usuario, nome, descricao, dataInicio, status);
            System.out.println("Campanha criada com sucesso!");
        }catch(CampanhaJaExisteException e){
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro com a geração automática de ID. Por favor, digite as informacoes novamente");
            solicitarDados();
        }

    }

}
