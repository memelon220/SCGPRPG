package servico.excecao.jogador;

public class SolicitacaoJaExisteException extends JogadorException{
    public SolicitacaoJaExisteException(){
        super("Voce ja tem uma solicitacao pendente para esta campanha! Por favor, aguarde a aprovacao");
    }
}
