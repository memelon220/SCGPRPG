package servico.excecao.personagem;

public class PersonagemNaoPertenceAoJogadorException extends PersonagemException{
    public PersonagemNaoPertenceAoJogadorException() {
        super("O personagem não pertence ao jogador solicitante");
            }
}
