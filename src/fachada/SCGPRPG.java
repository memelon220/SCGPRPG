package fachada;

import servico.entidade.*;
import servico.ServicoCampanha;
import servico.ServicoJogador;
import servico.ServicoPersonagem;
import servico.ServicoPersonagem;
import servico.ServicoCampanha;
import servico.ServicoJogador;
import servico.excecao.campanha.*;
import servico.excecao.personagem.*;
import servico.excecao.jogador.*;
import dados.campanha.RepositorioCampanhasArrayList;
import dados.jogador.RepositorioJogadoresArrayList;
import dados.personagem.RepositorioPersonagensArrayList;

public class SCGPRPG {

    private ServicoJogador servicoJogador;
    private ServicoPersonagem servicoPersonagem;
    private ServicoCampanha servicoCampanha;

    public SCGPRPG() {
        this.servicoJogador = new ServicoJogador(new RepositorioJogadoresArrayList());
        this.servicoPersonagem = new ServicoPersonagem(new RepositorioPersonagensArrayList());
        this.servicoCampanha = new ServicoCampanha(new RepositorioCampanhasArrayList());
    }

    public void criarJogador(){
    }
    public void criarNarrador(){
    }
    public void criarPersonagem(){
    }
    public void criarCampanha(){
    }
    public void adicionarPersonagem(){
    }
}
