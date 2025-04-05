package servico.excecao.jogador;

public class JogadorJaExisteException extends JogadorException {

    public JogadorJaExisteException() {
        super("O jogador ja existe e nao pode ser adicionado novamente.");
    }
}
