package servico.entidade;
import servico.excecao.campanha.CampanhaNaoExisteException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.personagem.PersonagemNaoPertenceAoJogadorException;

import java.util.ArrayList;

public class Narrador extends Jogador{
    private ArrayList<Campanha> listaCampanhas;
    private Campanha campanhaAtual;

    public Narrador(String nome, int idade, String senha) {
        super(nome, idade, senha);
        this.ID = "N" + this.ID.substring(1); //troca J por N
        this.listaCampanhas = new ArrayList<Campanha>();
        this.campanhaAtual = null;
    }

    public boolean aprovarSolicitacao(Solicitacao solicitacao, boolean confirmacao) {
        boolean aprovacao = false;
        if (confirmacao) {
            solicitacao.getJogador().adicionarNotificacao(
                    "Solicitação para " + solicitacao.getPersonagem().getNome() +
                            " na campanha " + this.nome + " foi APROVADA"
            );
            aprovacao = true;
            solicitacao.getCampanha().getSolicitacoes().remove(solicitacao);
        } else {
            solicitacao.getJogador().adicionarNotificacao(
                    "Solicitação para " + solicitacao.getPersonagem().getNome() +
                            " na campanha " + this.nome + " foi REJEITADA"
            );
            solicitacao.getCampanha().getSolicitacoes().remove(solicitacao);
        }
        return aprovacao;
    }

    public void adicionarCampanha(Campanha campanha) {
        this.listaCampanhas.add(campanha);
    }

    public void atualizarCampanhaNarrador(Campanha campanha1, Campanha campanha2) throws CampanhaNaoExisteException {
        if(getListaCampanhas().contains(campanha1)){
            int i = getListaCampanhas().indexOf(campanha1);
            ArrayList<Campanha> campanha_aux = getListaCampanhas();
            campanha_aux.set(i, campanha2);
            setListaCampanhas(campanha_aux);
        }else{
            throw new CampanhaNaoExisteException();
        }
    }

    public void aprovarSolicitacao(Campanha campanha, Solicitacao solicitacao, boolean confirmacao) {
        aprovarSolicitacao(solicitacao, confirmacao);
    }


    public void enviarConvite(Campanha campanha, Jogador jogador, Personagem personagem)
            throws PersonagemNaoPertenceAoJogadorException, JogadorNaoExisteException {
        campanha.enviarConvite(jogador, personagem, campanha);
    }

    public ArrayList<Campanha> getListaCampanhas() {
        return listaCampanhas;
    }

    public void setListaCampanhas(ArrayList<Campanha> listaCampanhas) {
        this.listaCampanhas = listaCampanhas;
    }

    public Campanha getCampanhaAtual() {
        return campanhaAtual;
    }

    public void setCampanhaAtual(Campanha campanhaAtual) {
        this.campanhaAtual = campanhaAtual;
    }
}
