package dados.personagem;

import servico.entidade.Jogador;
import servico.entidade.Personagem;
import java.util.ArrayList;

public class RepositorioPersonagensArrayList implements IRepositorioPersonagens {

    private ArrayList<Personagem> arrayPersonagens;

    public RepositorioPersonagensArrayList(){
        arrayPersonagens = new ArrayList<>();
    }

    @Override
    public void adicionar(Personagem personagem) {
        arrayPersonagens.add(personagem);
    }

    @Override
    public void remover(Personagem personagem) {
        int i = arrayPersonagens.indexOf(personagem);
        if(i != -1){
            arrayPersonagens.remove(i);
        }
    }

    @Override
    public void atualizar(Personagem personagem, String p_id) {
        if(existe(p_id)){
            int i = arrayPersonagens.indexOf(personagem);
            arrayPersonagens.set(i, personagem);
        }
    }

    @Override
    public Personagem buscar(String p_Id) {
        Personagem personagemProcurado = null;
        for (Personagem p : arrayPersonagens) {
            if (p.getID().equals(p_Id)) {
                personagemProcurado = p;
                break;
            }
        }
        return personagemProcurado;
    }

    @Override
    public void listar(){
        for(Personagem p : arrayPersonagens){
            System.out.println(p);
        }
    }

    @Override
    public boolean existe(String p_Id) {
        boolean flag = false;
        for(Personagem p : arrayPersonagens){
            if(p.getID().equals(p_Id)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public ArrayList<Personagem> getArrayPersonagens() {
        return arrayPersonagens;
    }
}