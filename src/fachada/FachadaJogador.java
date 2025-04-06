package fachada;

import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.personagem.PersonagemJaExisteException;
import servico.entidade.*;
import servico.excecao.personagem.*;
import java.util.ArrayList;

public class FachadaJogador {

    private SCGPRPG fachadaPrincipal;

    public FachadaJogador(SCGPRPG fachadaPrincipal) {
        this.fachadaPrincipal = fachadaPrincipal;
    }

    public void criarPersonagem(Jogador jogador, String nome, int nivel, int forca, int destreza,
                                int constituicao, int inteligencia, int sabedoria, int carisma)
            throws PersonagemJaExisteException, IllegalArgumentException {
        fachadaPrincipal.criarPersonagem(jogador, nome, nivel, forca, destreza, constituicao,
                inteligencia, sabedoria, carisma);
    }

    public void atualizarPersonagem(Personagem personagem1, Personagem personagem2) throws PersonagemNaoExisteException {
        fachadaPrincipal.atualizarPersonagem(personagem1, personagem2);
    }

    public void removerPersonagem(String p_ID) throws PersonagemNaoExisteException {
        fachadaPrincipal.removerPersonagem(p_ID);
    }

    public void excluirJogador(String j_id) throws JogadorNaoExisteException {
        fachadaPrincipal.removerJogador(j_id);
    }

    public void solicitarEntradaEmCampanha(String j_id, String p_id, String c_id) {

    }

    public ArrayList<Personagem> pegarPersonagens(String j_id) throws JogadorNaoExisteException {
        return fachadaPrincipal.getPersonagensDoJogador(j_id);
    }

}