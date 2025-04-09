package dados.campanha;

import servico.entidade.Campanha;
import java.util.ArrayList;
import java.io.*;

public class RepositorioCampanhasArquivo implements IRepositorioCampanhas {

    private static final String ARQUIVO_CAMPANHAS = "campanhas.dat";
    private ArrayList<Campanha> arquivoCampanhas;


    public RepositorioCampanhasArquivo(){
        this.arquivoCampanhas = carregarCampanha();
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Campanha> carregarCampanha() {
        File arquivo = new File(ARQUIVO_CAMPANHAS);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?> lista) {
                if (!lista.isEmpty() && !(lista.getFirst() instanceof Campanha)) {
                    throw new IOException("Formato inválido do arquivo de campanhas");
                }
                return (ArrayList<Campanha>) lista;
            }
            throw new IOException("O arquivo não contém uma lista válida");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar campanhas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void salvarCampanha() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CAMPANHAS))) {
            oos.writeObject(arquivoCampanhas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar campanhas: " + e.getMessage());
        }
    }

    @Override
    public void adicionar(Campanha campanha) {
        arquivoCampanhas.add(campanha);
        salvarCampanha();
    }

    @Override
    public void remover(Campanha campanha){
        arquivoCampanhas.removeIf(c -> campanha.getID().equals(c.getID()));
        salvarCampanha();

    }

    @Override
    public void atualizar(Campanha campanha) {
        int index = arquivoCampanhas.indexOf(buscar(campanha.getID()));
        if (index != -1) {
            arquivoCampanhas.set(index, campanha);
            salvarCampanha();
        }
    }

    @Override
    public Campanha buscar(String c_Id) {
        for (Campanha c : arquivoCampanhas) {
            if (c.getID().equals(c_Id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void listar(){
        for(Campanha c : arquivoCampanhas){
            System.out.println(c);
        }
    }

    public ArrayList<Campanha> listarTodas() {
        return arquivoCampanhas;
    }

    @Override
    public boolean existe(String c_Id) {
        for (Campanha c : arquivoCampanhas) {
            if (c.getID().equals(c_Id)) {
                return true;
            }
        }
        return false;
    }

}