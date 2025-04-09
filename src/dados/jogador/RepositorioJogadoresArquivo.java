package dados.jogador;

import servico.entidade.Jogador;
import java.util.ArrayList;
import java.io.*;

public class RepositorioJogadoresArquivo implements IRepositorioJogadores {

    private static final String ARQUIVO_JOGADORES = "jogadores.dat";
    private ArrayList<Jogador> arquivoJogadores;

    public RepositorioJogadoresArquivo(){
        this.arquivoJogadores = carregarJogador();
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Jogador> carregarJogador() {
        File arquivo = new File(ARQUIVO_JOGADORES);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?> lista) {
                if (!lista.isEmpty() && !(lista.getFirst() instanceof Jogador)) {
                    throw new IOException("Formato inválido do arquivo de jogadores");
                }
                return (ArrayList<Jogador>) lista;
            }
            throw new IOException("O arquivo não contém uma lista válida");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar jogadores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void salvarJogador() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_JOGADORES))) {
            oos.writeObject(arquivoJogadores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar jogadores: " + e.getMessage());
        }
    }

    @Override
    public void adicionar(Jogador jogador) {
        arquivoJogadores.add(jogador);
        salvarJogador();
    }

    @Override
    public void remover(Jogador jogador){
        arquivoJogadores.removeIf(j -> jogador.getID().equals(j.getID()));
        salvarJogador();

    }

    @Override
    public void atualizar(Jogador jogador) {
        int index = arquivoJogadores.indexOf(buscar(jogador.getID()));
        if (index != -1) {
            arquivoJogadores.set(index, jogador);
            salvarJogador();
        }
    }

    @Override
    public Jogador buscar(String j_Id) {
        for (Jogador j : arquivoJogadores) {
            if (j.getID().equals(j_Id)) {
                return j;
            }
        }
        return null;
    }

    @Override
    public void listar(){
        for(Jogador j : arquivoJogadores){
            System.out.println(j);
        }
    }

    @Override
    public boolean existe(String j_Id) {
        for (Jogador j : arquivoJogadores) {
            if (j.getID().equals(j_Id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Jogador> getArrayJogadores() {
        return arquivoJogadores;
    }

}