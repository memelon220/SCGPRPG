package fachada;

import servico.excecao.campanha.CampanhaLotadaException;
import servico.excecao.campanha.CampanhaNaoExisteException;
import servico.excecao.campanha.JogadorJaEstaNaCampanhaException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.jogador.SolicitacaoJaExisteException;
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
                                int constituicao, int inteligencia, int sabedoria, int carisma,
                                String classe, String especie)
            throws PersonagemJaExisteException, IllegalArgumentException {
        fachadaPrincipal.criarPersonagem(jogador, nome, nivel, forca, destreza, constituicao,
                inteligencia, sabedoria, carisma, classe, especie);
    }

    public void atualizarPersonagem(Personagem personagem, String p_id) throws PersonagemNaoExisteException {
        fachadaPrincipal.atualizarPersonagem(personagem, p_id);
    }

    public void removerPersonagem(String p_ID) throws PersonagemNaoExisteException {
        fachadaPrincipal.removerPersonagem(p_ID);
    }

    public void excluirJogador(String j_id) throws JogadorNaoExisteException {
        fachadaPrincipal.removerJogador(j_id);
    }

    public void solicitarEntradaEmCampanha(String j_id, String p_id, String c_id) throws CampanhaNaoExisteException,
            PersonagemNaoExisteException, JogadorNaoExisteException, CampanhaLotadaException,
            PersonagemNaoPertenceAoJogadorException, SolicitacaoJaExisteException, JogadorJaEstaNaCampanhaException {
        fachadaPrincipal.solicitarEntradaEmCampanha(j_id, p_id, c_id);
    }

    public ArrayList<Personagem> pegarPersonagens(String j_id) throws JogadorNaoExisteException {
        return fachadaPrincipal.getPersonagensDoJogador(j_id);
    }

}