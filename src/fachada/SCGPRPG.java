package fachada;

import servico.entidade.*;
import servico.ServicoCampanha;
import servico.ServicoJogador;
import servico.ServicoPersonagem;
import servico.excecao.campanha.*;
import servico.excecao.personagem.*;
import servico.excecao.jogador.*;
import dados.campanha.RepositorioCampanhasArrayList;
import dados.jogador.RepositorioJogadoresArrayList;
import dados.personagem.RepositorioPersonagensArrayList;
import java.util.ArrayList;

public class SCGPRPG {

    private ServicoJogador servicoJogador;
    private ServicoPersonagem servicoPersonagem;
    private ServicoCampanha servicoCampanha;

    public SCGPRPG() {
        this.servicoJogador = new ServicoJogador(new RepositorioJogadoresArrayList());
        this.servicoPersonagem = new ServicoPersonagem(new RepositorioPersonagensArrayList());
        this.servicoCampanha = new ServicoCampanha(new RepositorioCampanhasArrayList());
    }

    public void criarJogador(String nome, int idade, int jogadornarrador)
            throws JogadorJaExisteException, TipoJogadorNaoExisteException {
        Jogador jogador = null;

        switch (jogadornarrador) {
            case 1:
                jogador = new Jogador(nome, idade);
                break;
            case 2:
                jogador = new Narrador(nome, idade);
                break;
            default:
                throw new TipoJogadorNaoExisteException();
        }
        servicoJogador.adicionar(jogador);
    }

    public void removerJogador(String j_ID) throws JogadorNaoExisteException{
        servicoJogador.remover(j_ID);
    }

    public void atualizarJogador(Jogador jogador1, Jogador jogador2) throws JogadorNaoExisteException{
        servicoJogador.atualizar(jogador1, jogador2);
    }


    public void criarPersonagem(Jogador usuario, String nome, int nivel, int forca, int destreza,
                                int constituicao, int inteligencia, int sabedoria, int carisma)
            throws PersonagemJaExisteException, IllegalArgumentException{

        if(nivel < 0 || nivel > 20){
            throw new IllegalArgumentException("Por favor, escolha um nivel de 0 a 20");
        }else if(forca < 8 || forca > 18 || destreza < 8 || destreza > 18 || constituicao < 8 || constituicao > 18 ||
                inteligencia < 8 || inteligencia > 18 || sabedoria < 8 || sabedoria > 18 || carisma < 8 || carisma > 18){
            throw new IllegalArgumentException("Por favor, escolha valores para os atributos de seu personagem de 8 a 18");
        }

        Personagem personagem = new Personagem(nome, nivel, forca, destreza, constituicao,
        inteligencia, sabedoria, carisma);

        usuario.adicionarPersonagem(personagem);
        servicoPersonagem.adicionar(personagem);
    }

    public void criarPersonagem(Jogador usuario, String nome, int nivel)
            throws PersonagemJaExisteException, IllegalArgumentException{
        if(nivel < 0 || nivel > 20){
            throw new IllegalArgumentException("Por favor, escolha um nivel de 0 a 20");
        }

        Personagem personagem = new Personagem(nome, nivel);

        usuario.adicionarPersonagem(personagem);
        servicoPersonagem.adicionar(personagem);
    }

    public void atualizarPersonagem(Personagem personagem1, Personagem personagem2)
            throws PersonagemNaoExisteException {
        Jogador jogador = personagem1.getJogador();
        int index = jogador.getPersonagens().indexOf(personagem1);
        jogador.getPersonagens().set(index, personagem2);
        servicoPersonagem.atualizar(personagem1, personagem2);
    }

    public void removerPersonagem(String p_ID) throws PersonagemNaoExisteException{
        Jogador jogador = servicoPersonagem.consultar(p_ID).getJogador();
        int index = jogador.getPersonagens().indexOf(servicoPersonagem.consultar(p_ID));
        jogador.getPersonagens().remove(index);
        servicoPersonagem.remover(p_ID);
    }

    public void criarCampanha(Narrador narrador, String nome, String descricao,
                              String dataInicio, String status) throws CampanhaJaExisteException{

        Campanha campanha = new Campanha(narrador, nome, descricao, dataInicio, status);
        servicoCampanha.adicionar(campanha);

    }

    public void removerCampanha(String c_Id) throws CampanhaNaoExisteException{
        Campanha campanha = servicoCampanha.buscar(c_Id);
        servicoCampanha.remover(campanha);
    }

    public void atualizarCampanha(Campanha campanha1, Campanha campanha2) throws CampanhaNaoExisteException{
        servicoCampanha.atualizar(campanha1, campanha2);
    }

    public void solicitarEntradaEmCampanha(String j_id, String p_id, String c_id) throws CampanhaNaoExisteException, PersonagemNaoExisteException, JogadorNaoExisteException {
        Campanha campanha = servicoCampanha.buscar(c_id);
        Jogador jogador = servicoJogador.buscar(j_id);
        Personagem personagem = servicoPersonagem.consultar(p_id);
        servicoCampanha.adicionarJogadorPersonagem(campanha, personagem, jogador);
    }

/*
    public void adicionarPersonagem() {
    }
*/

    public ArrayList<Personagem> getPersonagensDoJogador(String j_id) throws JogadorNaoExisteException {
        return servicoJogador.getPersonagensDoJogador(j_id);
    }

}