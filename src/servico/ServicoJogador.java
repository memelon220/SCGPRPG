package servico;

import dados.jogador.IRepositorioJogadores;
import servico.entidade.Jogador;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.jogador.JogadorJaExisteException;

public class ServicoJogador {

    private IRepositorioJogadores repositorioJogadores;

    public ServicoJogador(IRepositorioJogadores repositorioJogadores) {
        this.repositorioJogadores = repositorioJogadores;
    }

    public void adicionar(Jogador jogador) throws JogadorJaExisteException{
        boolean existe = repositorioJogadores.existe(jogador.getID());
        if (existe) {
            throw new JogadorJaExisteException();
        } else {
            repositorioJogadores.adicionar(jogador);
        }

    }

    public void remover(String j_Id) throws JogadorNaoExisteException {
        Jogador jogador = repositorioJogadores.buscar(j_Id);
        if (jogador != null) {
            repositorioJogadores.remover(jogador);
        } else {
            throw new JogadorNaoExisteException();
        }
    }

    public Jogador buscar(String j_Id) throws JogadorNaoExisteException {
        Jogador jogador = repositorioJogadores.buscar(j_Id);
        if (jogador == null) {
            throw new JogadorNaoExisteException();
        } else {
            return jogador;
        }
    }

    public void atualizar(Jogador jogador1, Jogador jogador2) throws JogadorNaoExisteException {
        Jogador jogador = repositorioJogadores.buscar(jogador1.getID());
        if (jogador == null) {
            throw new JogadorNaoExisteException();
        } else {
            repositorioJogadores.atualizar(jogador1, jogador2);
        }
    }
}