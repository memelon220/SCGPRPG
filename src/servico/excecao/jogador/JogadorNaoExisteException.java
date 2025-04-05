package servico.excecao.jogador;

public class JogadorNaoExisteException extends JogadorException {

    public JogadorNaoExisteException() {
        super("Este jogador não existe. Por favor, verifique as informacoes e tente novamente.");
    }
}
