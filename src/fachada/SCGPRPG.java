package fachada;

import servico.entidade.*;
import servico.ServicoCampanha;
import servico.ServicoJogador;
import servico.ServicoPersonagem;
import servico.excecao.campanha.*;
import servico.excecao.personagem.*;
import servico.excecao.jogador.*;
import dados.campanha.RepositorioCampanhasArrayList;
import dados.jogador.RepositorioJogadoresArrayList;
import dados.personagem.RepositorioPersonagensArrayList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class SCGPRPG {

    private ServicoJogador servicoJogador;
    private ServicoPersonagem servicoPersonagem;
    private ServicoCampanha servicoCampanha;

    public SCGPRPG() {
        this.servicoJogador = new ServicoJogador(new RepositorioJogadoresArrayList());
        this.servicoPersonagem = new ServicoPersonagem(new RepositorioPersonagensArrayList());
        this.servicoCampanha = new ServicoCampanha(new RepositorioCampanhasArrayList());
    }

    public String criarJogador(String nome, int idade, String senha, int jogadornarrador)
            throws JogadorJaExisteException, TipoJogadorNaoExisteException, JogadorSenhaInvalidaException {
        Jogador jogador = null;

        if(senha.length() < 8){
            throw new JogadorSenhaInvalidaException();
        }

        switch (jogadornarrador) {
            case 1:
                    jogador = new Jogador(nome, idade, senha);
                break;
            case 2:
                jogador = new Narrador(nome, idade, senha);
                break;
            default:
                throw new TipoJogadorNaoExisteException();
        }
        servicoJogador.adicionar(jogador);
        return jogador.getID();
    }

    public void removerJogador(String j_ID) throws JogadorNaoExisteException{
        servicoJogador.remover(j_ID);
    }

    public void atualizarJogador(Jogador jogador1, Jogador jogador2) throws JogadorNaoExisteException{
        servicoJogador.atualizar(jogador1, jogador2);
    }

    public Jogador buscarJogador(String j_id) throws JogadorNaoExisteException {
        return servicoJogador.buscar(j_id);
    }

    public void criarPersonagem(Jogador usuario, String nome, int nivel, int forca, int destreza,
                                int constituicao, int inteligencia, int sabedoria, int carisma, String classe, String especie)
            throws PersonagemJaExisteException, IllegalArgumentException{

        if(nivel < 0 || nivel > 20){
            throw new IllegalArgumentException("Por favor, escolha um nivel de 0 a 20");
        }else if(forca < 8 || forca > 18 || destreza < 8 || destreza > 18 || constituicao < 8 || constituicao > 18 ||
                inteligencia < 8 || inteligencia > 18 || sabedoria < 8 || sabedoria > 18 || carisma < 8 || carisma > 18){
            throw new IllegalArgumentException("Por favor, escolha valores para os atributos de seu personagem de 8 a 18");
        }
        Personagem personagem = new Personagem(nome, nivel, forca, destreza, constituicao,
                inteligencia, sabedoria, carisma);

        if (classe == "mago"){
            Mago mago = new Mago(personagem);
            mago.aplicarClasse();
        }
        else if (classe == "ladino"){
            Ladino ladino = new Ladino(personagem);
            ladino.aplicarClasse();
        }else if(classe == "guerreiro"){
            Guerreiro guerreiro = new Guerreiro(personagem);
            guerreiro.aplicarClasse();
        }else if(classe == "clerigo"){
            Clerigo clerigo = new Clerigo(personagem);
            clerigo.aplicarClasse();
        }

        if (especie == "humano"){
            Humano classe_personagem = new Humano();
            classe_personagem.aplicarEspecie(personagem);
        } else if(especie == "halfling"){
            Halfling classe_personagem = new Halfling();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "elfo"){
            Elfo classe_personagem = new Elfo();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "anao"){
            Anão classe_personagem = new Anão();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "draconato"){
            Draconato classe_personagem = new Draconato();
            classe_personagem.aplicarEspecie(personagem);
        }

        usuario.adicionarPersonagem(personagem);
        servicoPersonagem.adicionar(personagem);
    }

    public void criarPersonagem(Jogador usuario, String nome, int nivel, String classe, String especie)
            throws PersonagemJaExisteException, IllegalArgumentException{
        if(nivel < 0 || nivel > 20){
            throw new IllegalArgumentException("Por favor, escolha um nivel de 0 a 20");
        }

        Personagem personagem = new Personagem(nome, nivel);

        usuario.adicionarPersonagem(personagem);
        servicoPersonagem.adicionar(personagem);

        if (classe == "mago"){
            Mago mago = new Mago(personagem);
            mago.aplicarClasse();
        }
        else if (classe == "ladino"){
            Ladino ladino = new Ladino(personagem);
            ladino.aplicarClasse();
        }else if(classe == "guerreiro"){
            Guerreiro guerreiro = new Guerreiro(personagem);
            guerreiro.aplicarClasse();
        }else if(classe == "clerigo"){
            Clerigo clerigo = new Clerigo(personagem);
            clerigo.aplicarClasse();
        }

        if (especie == "humano"){
            Humano classe_personagem = new Humano();
            classe_personagem.aplicarEspecie(personagem);
        } else if(especie == "halfling"){
            Halfling classe_personagem = new Halfling();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "elfo"){
            Elfo classe_personagem = new Elfo();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "anao"){
            Anão classe_personagem = new Anão();
            classe_personagem.aplicarEspecie(personagem);
        }else if(especie == "draconato"){
            Draconato classe_personagem = new Draconato();
            classe_personagem.aplicarEspecie(personagem);
        }
    }

    public void atualizarPersonagem(Personagem personagem, String p_id)
            throws PersonagemNaoExisteException {
        servicoPersonagem.atualizar(personagem, p_id);
    }

    public void removerPersonagem(String p_ID) throws PersonagemNaoExisteException{
        Jogador jogador = servicoPersonagem.consultar(p_ID).getJogador();
        int index = jogador.getPersonagens().indexOf(servicoPersonagem.consultar(p_ID));
        ArrayList<Personagem> jogador_atualizar = jogador.getPersonagens();
        jogador_atualizar.remove(index);
        jogador.setPersonagens(jogador_atualizar);
        servicoPersonagem.remover(p_ID);
    }

    public Personagem buscarPersonagem(String p_id) throws PersonagemNaoExisteException{
        return servicoPersonagem.consultar(p_id);
    }

    public void criarCampanha(Narrador narrador, String nome, String descricao
            , String status, int limite_jogadores) throws CampanhaJaExisteException{

        Campanha campanha = new Campanha(narrador, nome, descricao, status, limite_jogadores);
        servicoCampanha.adicionar(campanha);
        narrador.adicionarCampanha(campanha);
    }

    public void removerCampanha(String c_Id) throws CampanhaNaoExisteException{
        Campanha campanha = servicoCampanha.buscar(c_Id);
        servicoCampanha.remover(campanha);
    }

    public void atualizarCampanha(Campanha campanha1, Campanha campanha2) throws CampanhaNaoExisteException{
        servicoCampanha.atualizar(campanha1, campanha2);
    }

    public void solicitarEntradaEmCampanha(String jogadorID, String personagemID, String campanhaID)
            throws CampanhaNaoExisteException, PersonagemNaoExisteException, JogadorNaoExisteException,
            CampanhaLotadaException, PersonagemNaoPertenceAoJogadorException,
            SolicitacaoJaExisteException {
        Campanha campanha = servicoCampanha.buscar(campanhaID);
        Jogador jogador = servicoJogador.buscar(jogadorID);
        Personagem personagem = servicoPersonagem.consultar(personagemID);

        if (!personagem.getJogador().getID().equals(jogadorID)) {
            throw new PersonagemNaoPertenceAoJogadorException();
        }

        if (campanha.getSolicitacoes().stream()
                .anyMatch(s -> s.getPersonagem().getID().equals(personagemID))) {
            throw new SolicitacaoJaExisteException();
        }
        if (!campanha.temVagas()) {
            throw new CampanhaLotadaException(campanha.getlimiteJogadores());
        }
        Solicitacao solicitacao = new Solicitacao(jogador, personagem, campanha);
        campanha.adicionarSolicitacao(solicitacao);
    }

    public ArrayList<Personagem> getPersonagensDoJogador(String j_id) throws JogadorNaoExisteException {
        if(buscarJogador(j_id) == null){
            throw new JogadorNaoExisteException();
        }
        return servicoJogador.getPersonagensDoJogador(j_id);
    }

    public void processarSolicitacao(String cId, String pId, boolean aprovar)
            throws CampanhaNaoExisteException, PersonagemNaoExisteException, CampanhaNaoExisteException,
            CampanhaLotadaException {
        Campanha campanha = servicoCampanha.buscar(cId);
        Narrador narrador = campanha.getNarrador();
        Solicitacao solicitacao = campanha.getSolicitacoes().stream()
                .filter(s -> s.getPersonagem().getID().equals(pId))
                .findFirst()
                .orElseThrow();
        boolean aprovacao = narrador.aprovarSolicitacao(solicitacao, aprovar);

        if (aprovacao){
            campanha.adicionarJogador(solicitacao.getJogador());
            campanha.adicionarPersonagem(solicitacao.getPersonagem());
            atualizarCampanha(servicoCampanha.buscar(cId), campanha);
            narrador.atualizarCampanhaNarrador(servicoCampanha.buscar(cId), campanha);
        }
    }

    public Campanha buscarCampanha(String c_ID) throws CampanhaNaoExisteException{
        return servicoCampanha.buscar(c_ID);
    }

    public ArrayList<Campanha> listarCampanhasAbertas() {
        return (ArrayList<Campanha>) servicoCampanha.listarTodas().stream()
                .filter(c -> c.getStatus().equals("ABERTA") && c.temVagas())
                .collect(Collectors.toList());
    }

    public ArrayList<Jogador> listarJogadores() {
        return (ArrayList<Jogador>) servicoJogador.listarJogadores();
    }

    public void cancelarConvite(String narradorId, String campanhaId, String personagemId)
            throws CampanhaNaoExisteException, ConviteNaoExisteException {
        Campanha campanha = buscarCampanha(campanhaId);
        if (!campanha.getNarrador().getID().equals(narradorId)) {
            throw new CampanhaNaoExisteException();
        }
        Convite convite = campanha.getConvites().stream()
                .filter(c -> c.getPersonagem().getID().equals(personagemId))
                .findFirst()
                .orElseThrow(() -> new ConviteNaoExisteException());
        if (convite.isAceito() || convite.isRecusado()) {
            throw new ConviteNaoExisteException();
        }
        campanha.getConvites().remove(convite);
        System.out.println("Convite cancelado com sucesso");
    }

    public ArrayList<Convite> listarConvitesDoNarrador(String narradorId) {
        ArrayList<Convite> convites = new ArrayList<>();
        for (Campanha campanha : servicoCampanha.listarTodas()) {
            if (campanha.getNarrador().getID().equals(narradorId)) {
                convites.addAll(campanha.getConvites());
            }
        }

        return convites;
    }

    public Convite buscarConvite(String campanhaId, String personagemId)
            throws CampanhaNaoExisteException, ConviteNaoExisteException {

        Campanha campanha = buscarCampanha(campanhaId);

        return campanha.getConvites().stream()
                .filter(c -> c.getPersonagem().getID().equals(personagemId))
                .findFirst()
                .orElseThrow(() -> new ConviteNaoExisteException());
    }

    public void enviarConviteParaJogador(String narradorId, String campanhaId, String jogadorId, String personagemId)
            throws CampanhaLotadaException, JogadorNaoExisteException,
            PersonagemNaoPertenceAoJogadorException, ConviteJaExisteException,
            CampanhaNaoExisteException, PersonagemNaoExisteException {

        Campanha campanha = buscarCampanha(campanhaId);
        Jogador jogador = buscarJogador(jogadorId);
        Personagem personagem = buscarPersonagem(personagemId);

        if (!campanha.getNarrador().getID().equals(narradorId)) {
            throw new CampanhaNaoExisteException();
        }

        if (!personagem.getJogador().getID().equals(jogadorId)) {
            throw new PersonagemNaoPertenceAoJogadorException();
        }

        boolean conviteExistente = campanha.getConvites().stream()
                .anyMatch(c -> c.getPersonagem().getID().equals(personagemId) &&
                        !c.isAceito() && !c.isRecusado());

        if (conviteExistente) {
            throw new ConviteJaExisteException();
        }

        if (campanha.getJogadores().size() >= campanha.getlimiteJogadores()) {
            throw new CampanhaLotadaException(campanha.getlimiteJogadores());
        }

        Convite novoConvite = new Convite(
                UUID.randomUUID().toString(),
                campanha,
                jogador,
                personagem
        );

        campanha.adicionarConvite(novoConvite);
    }

    public void responderConvite(String jogadorId, String conviteId, boolean aceitar)
            throws ConviteNaoExisteException {

        Convite convite = buscarConvitePorId(conviteId);

        if (!convite.getJogador().getID().equals(jogadorId)) {
            throw new ConviteNaoExisteException();
        }

        if (aceitar) {
            if (convite.getCampanha().getJogadores().size() >=
                    convite.getCampanha().getlimiteJogadores()) {
                throw new ConviteNaoExisteException();
            }
            convite.getCampanha().getJogadores().add(convite.getJogador());
            convite.setAceito(true);
        } else {
            convite.setRecusado(true);
        }
    }

    public Convite buscarConvitePorId(String conviteId) throws ConviteNaoExisteException {
        for (Campanha campanha : servicoCampanha.listarTodas()){
            for (Convite convite : campanha.getConvites()) {
                if (convite.getId().equals(conviteId)) {
                    return convite;
                }
            }
        }
        throw new ConviteNaoExisteException();
    }

}