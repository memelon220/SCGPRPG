package servico.excecao.jogador;

public class ConviteJaExisteException extends JogadorException {
    public ConviteJaExisteException() {
        super("Voce ja fez um convite para este jogador!");
    }
}
