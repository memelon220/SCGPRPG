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

    public String criarJogador(String nome, int idade, String senha, int jogadornarrador)
            throws JogadorJaExisteException, TipoJogadorNaoExisteException, JogadorSenhaInvalidaException {
        Jogador jogador = null;

        if(senha.length() < 8){
            throw new JogadorSenhaInvalidaException();
        }

        switch (jogadornarrador) {
            case 1:
                    jogador = new Jogador(nome, idade, senha);
                break;
            case 2:
                jogador = new Narrador(nome, idade, senha);
                break;
            default:
                throw new TipoJogadorNaoExisteException();
        }
        servicoJogador.adicionar(jogador);
        return jogador.getID();
    }

    public void removerJogador(String j_ID) throws JogadorNaoExisteException{
        servicoJogador.remover(j_ID);
    }

    public void atualizarJogador(Jogador jogador1, Jogador jogador2) throws JogadorNaoExisteException{
        servicoJogador.atualizar(jogador1, jogador2);
    }

    public Jogador buscarJogador(String j_id) throws JogadorNaoExisteException {
        return servicoJogador.buscar(j_id);
    }

    public void criarPersonagem(Jogador usuario, String nome, int nivel, int forca, int destreza,
                                int constituicao, int inteligencia, int sabedoria, int carisma, String classe, String especie)
            throws PersonagemJaExisteException, IllegalArgumentException{

        if(nivel < 0 || nivel > 20){
            throw new IllegalArgumentException("Por favor, escolha um nivel de 0 a 20");
        }else if(forca < 8 || forca > 18 || destreza < 8 || destreza > 18 || constituicao < 8 || constituicao > 18 ||
                inteligencia < 8 || inteligencia > 18 || sabedoria < 8 || sabedoria > 18 || carisma < 8 || carisma > 18){
            throw new IllegalArgumentException("Por favor, escolha valores para os atributos de seu personagem de 8 a 18");
        }
        Personagem personagem = new Personagem(nome, nivel, forca, destreza, constituicao,
                inteligencia, sabedoria, carisma);

        if (classe == "mago"){
            Mago mago = new Mago(personagem);
            mago.aplicarClasse();
        }
        else if (classe == "ladino"){
            Ladino ladino = new Ladino(personagem);
            ladino.aplicarClasse();
        }else if(classe == "guerreiro"){
            Guerreiro guerreiro = new Guerreiro(personagem);
            guerreiro.aplicarClasse();
        }else if(classe == "clerigo"){
            Clerigo clerigo = new Clerigo(personagem);
            clerigo.aplicarClasse();
        }

        if (especie == "humano"){
            Humano classe_personagem = new Humano();
            classe_personagem.aplicarEspecie(personagem);
        } else if(especie == "halfling"){
            Halfling classe_personagem = new Halfling();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "elfo"){
            Elfo classe_personagem = new Elfo();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "anao"){
            Anão classe_personagem = new Anão();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "draconato"){
            Draconato classe_personagem = new Draconato();
            classe_personagem.aplicarEspecie(personagem);
        }

        usuario.adicionarPersonagem(personagem);
        servicoPersonagem.adicionar(personagem);
    }

    public void criarPersonagem(Jogador usuario, String nome, int nivel, String classe, String especie)
            throws PersonagemJaExisteException, IllegalArgumentException{
        if(nivel < 0 || nivel > 20){
            throw new IllegalArgumentException("Por favor, escolha um nivel de 0 a 20");
        }

        Personagem personagem = new Personagem(nome, nivel);

        usuario.adicionarPersonagem(personagem);
        servicoPersonagem.adicionar(personagem);

        if (classe == "mago"){
            Mago mago = new Mago(personagem);
            mago.aplicarClasse();
        }
        else if (classe == "ladino"){
            Ladino ladino = new Ladino(personagem);
            ladino.aplicarClasse();
        }else if(classe == "guerreiro"){
            Guerreiro guerreiro = new Guerreiro(personagem);
            guerreiro.aplicarClasse();
        }else if(classe == "clerigo"){
            Clerigo clerigo = new Clerigo(personagem);
            clerigo.aplicarClasse();
        }

        if (especie == "humano"){
            Humano classe_personagem = new Humano();
            classe_personagem.aplicarEspecie(personagem);
        } else if(especie == "halfling"){
            Halfling classe_personagem = new Halfling();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "elfo"){
            Elfo classe_personagem = new Elfo();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "anao"){
            Anão classe_personagem = new Anão();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "draconato"){
            Draconato classe_personagem = new Draconato();
            classe_personagem.aplicarEspecie(personagem);
        }
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
        ArrayList<Personagem> jogador_atualizar = jogador.getPersonagens();
        jogador_atualizar.remove(index);
        jogador.setPersonagens(jogador_atualizar);
        servicoPersonagem.remover(p_ID);
    }

    public void buscarPersonagem(String p_id) throws PersonagemNaoExisteException{
        servicoPersonagem.consultar(p_id);
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