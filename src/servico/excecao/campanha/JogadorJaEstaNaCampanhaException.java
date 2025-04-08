package servico.excecao.campanha;

public class JogadorJaEstaNaCampanhaException extends CampanhaException{
    public JogadorJaEstaNaCampanhaException() {
        super("O jogador ja esta na campanha!");
    }
}
