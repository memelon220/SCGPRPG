package dados.personagem;

import servico.entidade.Personagem;
import java.util.ArrayList;
import java.io.*;

public class RepositorioPersonagensArquivo implements IRepositorioPersonagens {

    private static final String ARQUIVO_PERSONAGENS = "personagens.dat";
    private ArrayList<Personagem> arquivoPersonagens;

    public RepositorioPersonagensArquivo(){
        this.arquivoPersonagens = carregarPersonagem();
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Personagem> carregarPersonagem() {
        File arquivo = new File(ARQUIVO_PERSONAGENS);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?> lista) {
                if (!lista.isEmpty() && !(lista.getFirst() instanceof Personagem)) {
                    throw new IOException("Formato inválido do arquivo de personagens");
                }
                return (ArrayList<Personagem>) lista;
            }
            throw new IOException("O arquivo não contém uma lista válida");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar personagen: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void salvarPersonagem() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_PERSONAGENS))) {
            oos.writeObject(arquivoPersonagens);
        } catch (IOException e) {
            System.err.println("Erro ao salvar personagens: " + e.getMessage());
        }
    }

    @Override
    public void adicionar(Personagem personagem) {
        arquivoPersonagens.add(personagem);
        salvarPersonagem();
    }

    @Override
    public void remover(Personagem personagem){
        arquivoPersonagens.removeIf(p -> personagem.getID().equals(p.getID()));
        salvarPersonagem();

    }

    public void atualizar(Personagem personagem, String p_id) {
        if(existe(p_id)){
            int i = arquivoPersonagens.indexOf(personagem);
            arquivoPersonagens.set(i, personagem);
            salvarPersonagem();
        }
    }

    @Override
    public Personagem buscar(String p_Id) {
        for (Personagem p : arquivoPersonagens) {
            if (p.getID().equals(p_Id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void listar(){
        for(Personagem p : arquivoPersonagens){
            System.out.println(p);
        }
    }

    @Override
    public boolean existe(String p_Id) {
        for (Personagem p : arquivoPersonagens) {
            if (p.getID().equals(p_Id)) {
                return true;
            }
        }
        return false;
    }

}
