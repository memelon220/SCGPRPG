package servico.excecao.personagem;

public class PersonagemNaoElegivelException extends PersonagemException{
    public PersonagemNaoElegivelException(String motivo) {
        super("Personagem não elegível: " + motivo);
    }
}
