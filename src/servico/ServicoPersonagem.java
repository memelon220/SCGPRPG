package servico;

import dados.personagem.IRepositorioPersonagens;
import servico.entidade.Personagem;
import servico.excecao.personagem.PersonagemNaoExisteException;
import servico.excecao.personagem.PersonagemJaExisteException;
public class ServicoPersonagem {
    private IRepositorioPersonagens repositorioPersonagens;

    public ServicoPersonagem(IRepositorioPersonagens repositorioPersonagens) {
        this.repositorioPersonagens = repositorioPersonagens;
    }

    public void adicionar(Personagem personagem) throws PersonagemJaExisteException {
        boolean existe = repositorioPersonagens.existe(personagem.getID());
        if (existe) {
            throw new PersonagemJaExisteException();
        } else {
            repositorioPersonagens.adicionar(personagem);
        }

    }

    public void remover(String p_Id) throws PersonagemNaoExisteException {
        Personagem personagem = repositorioPersonagens.buscar(p_Id);
        if (personagem != null) {
            repositorioPersonagens.remover(personagem);
        } else {
            throw new PersonagemNaoExisteException();
        }
    }

    public Personagem consultar(String p_Id) throws PersonagemNaoExisteException {
        Personagem personagem = repositorioPersonagens.buscar(p_Id);
        if (personagem == null) {
            throw new PersonagemNaoExisteException();
        } else {
            return personagem;
        }
    }

    public void atualizar(Personagem personagem1, Personagem personagem2) throws PersonagemNaoExisteException {
        Personagem personagem = repositorioPersonagens.buscar(personagem1.getID());
        if (personagem == null) {
            throw new PersonagemNaoExisteException();
        }else{
            repositorioPersonagens.atualizar(personagem1, personagem2);
        }
    }
}
