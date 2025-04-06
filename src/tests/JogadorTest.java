package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servico.entidade.Personagem;
import servico.entidade.Jogador;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JogadorTest {
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogador3;

    @BeforeEach
    void setUp(){
        this.jogador1 = new Jogador("Carlos", 12);
        this.jogador2 = new Jogador("Bianca", 19);
        this.jogador3 = new Jogador("Samuel", 30);
    }

    @Test
    void testJogador(){
        assertEquals("Carlos", jogador1.getNome());
        assertEquals(12, jogador1.getIdade());
        assertEquals("Bianca", jogador2.getNome());
        assertEquals(19, jogador2.getIdade());
        assertNotEquals(31, jogador3.getIdade());
        assertNotEquals("Samueo", jogador3.getNome());
        assertNotEquals(jogador1, jogador2);
        assertNotEquals(jogador1.getID(), jogador2.getID());
        assertNotEquals(jogador2.getID(), jogador3.getID());
        assertNotEquals(jogador1.getID(), jogador3.getID());
    }

    @Test
    void testAdicionarPersonagem(){
        jogador1.adicionarPersonagem(new Personagem("Vlaad", 1));
        jogador2.adicionarPersonagem(new Personagem("Furiae", 1, 12, 13,
                14, 15, 16, 17));

        assertNotNull(jogador1.getPersonagens());
        assertNotEquals(2, jogador1.getPersonagens().size());
        assertEquals(1, jogador2.getPersonagens().size());
        assertEquals(0, jogador3.getPersonagens().size());

        ArrayList<Personagem> personagens = jogador1.getPersonagens();
        System.out.println(personagens.getFirst().getForca());
        System.out.println(personagens.getFirst().getDestreza());
        System.out.println(personagens.getFirst().getConstituicao());
        System.out.println(personagens.getFirst().getSabedoria());
        System.out.println(personagens.getFirst().getInteligencia());
    }

}
