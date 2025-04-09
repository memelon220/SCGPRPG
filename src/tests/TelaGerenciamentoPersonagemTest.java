package tests;

import fachada.SCGPRPG;
import iu.TelaGerenciamentoPersonagem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import servico.entidade.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class TelaGerenciamentoPersonagemTest {

    @Test
    public void testAtualizarPersonagem_Nome_DeveAlterarNomeComSucesso() throws IllegalAccessException, NoSuchFieldException {
        SCGPRPG fachada = new SCGPRPG(); // Supondo que existe um construtor vazio
        Jogador jogador = new Jogador("teste", 18, "12345678");
        Personagem personagem = new Personagem("Teste", 2);
        String id = personagem.getID();
        String inputSimulado = id + "\n1\nNovo Nome\n";
        personagem.setNome("Personagem Antigo");
        jogador.setPersonagens(new ArrayList<>());
        jogador.getPersonagens().add(personagem);
        TelaGerenciamentoPersonagem tela = new TelaGerenciamentoPersonagem(fachada, jogador);
        Field campoScanner = TelaGerenciamentoPersonagem.class.getDeclaredField("sc");
        campoScanner.setAccessible(true);
        campoScanner.set(tela, new Scanner(inputSimulado));
        tela.atualizarPersonagem();
        assertEquals("Novo Nome", personagem.getNome());
    }

}