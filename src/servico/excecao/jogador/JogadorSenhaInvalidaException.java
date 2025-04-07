package servico.excecao.jogador;

public class JogadorSenhaInvalidaException extends Exception {
    public JogadorSenhaInvalidaException() {
        super("A senha deve ter pelo menos 8 caracteres!");
    }
}
