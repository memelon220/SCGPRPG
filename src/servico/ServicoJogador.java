package servico;

import dados.jogador.IRepositorioJogadores;
import servico.entidade.Jogador;
import servico.excecao.jogador.ConviteNaoExisteException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.jogador.JogadorJaExisteException;
import servico.entidade.Personagem;
import java.util.ArrayList;
import servico.entidade.Convite;

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

    public void atualizar(Jogador jogador) throws JogadorNaoExisteException {
        if (!repositorioJogadores.existe(jogador.getID())) {
            throw new JogadorNaoExisteException();
        }
        repositorioJogadores.atualizar(jogador);
    }

    public void processarConvite(String jogadorId, String conviteId, boolean aceitar)
            throws JogadorNaoExisteException, ConviteNaoExisteException {

        Jogador jogador = repositorioJogadores.buscar(jogadorId);
        if(jogador == null){
            throw new JogadorNaoExisteException();
        }
        Convite convite = jogador.getConvitesRecebidos().stream()
                .filter(c -> c.getId().equals(conviteId))
                .findFirst()
                .orElseThrow(ConviteNaoExisteException::new);

        if (aceitar) {
            convite.marcarComoAceito();
        } else {
            convite.marcarComoRecusado();
        }

        repositorioJogadores.atualizar(jogador);
    }


    public ArrayList<Jogador> listarJogadores() {
        return repositorioJogadores.getArrayJogadores();
    }

    public ArrayList<Personagem> getPersonagensDoJogador(String j_id) throws JogadorNaoExisteException {
        Jogador jogador = repositorioJogadores.buscar(j_id);
        return jogador.getPersonagens();
    }

}