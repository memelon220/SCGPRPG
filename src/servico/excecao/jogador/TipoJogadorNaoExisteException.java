package servico.excecao.jogador;

public class TipoJogadorNaoExisteException extends JogadorException {
    public TipoJogadorNaoExisteException() {
        super("Este tipo de conta nao existe.");
    }
}
