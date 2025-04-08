package servico.excecao.jogador;

public class ConviteNaoExisteException extends JogadorException {
    public ConviteNaoExisteException() {
        super("O convite nao existe!");
    }
}
