package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servico.entidade.*;

import static org.junit.jupiter.api.Assertions.*;

public class AtacarTest {

    private Personagem personagem1, personagem2, personagem3, personagem4;
    private Jogador jogador1, jogador2, jogador3, jogador4;

    @BeforeEach
    void setUp() {
        EspeciePersonagem especie1 = new Draconato();
        EspeciePersonagem especie2 = new Humano();
        EspeciePersonagem especie3 = new Anão();
        EspeciePersonagem especie4 = new Elfo();

        this.jogador1 = new Jogador("Carlos", 19, "12345678");
        this.jogador2 = new Jogador("Carla", 20, "12345678");
        this.jogador3 = new Jogador("Cleiton", 21, "12345678");
        this.jogador4 = new Jogador("Clecia", 22, "12345678");

        personagem1 = new Personagem("Breno", 12);
        ClassePersonagem classe1 = new Clerigo(personagem1);
        classe1.aplicarClasse();
        especie1.aplicarEspecie(personagem1);
        personagem2 = new Personagem("Brenda", 13);
        ClassePersonagem classe2 = new Clerigo(personagem2);
        classe2.aplicarClasse();
        especie2.aplicarEspecie(personagem2);
        personagem3 = new Personagem("Bruno", 14);
        ClassePersonagem classe3 = new Clerigo(personagem3);
        classe3.aplicarClasse();
        especie3.aplicarEspecie(personagem3);
        personagem4 = new Personagem("Bruna", 15);
        ClassePersonagem classe4 = new Clerigo(personagem4);
        classe4.aplicarClasse();
        especie4.aplicarEspecie(personagem4);

        jogador1.adicionarPersonagem(personagem1);
        jogador2.adicionarPersonagem(personagem2);
        jogador3.adicionarPersonagem(personagem3);
        jogador4.adicionarPersonagem(personagem4);
    }
        @Test
        public void testAtaque(){
            int vidaAtual1, vidaAtual2, vidaAtual3, vidaAtual4;
            boolean menor1 = false, menor2 = false;
            vidaAtual1 = jogador1.getPersonagens().getFirst().getVidaAtual();
            vidaAtual2 = jogador2.getPersonagens().getFirst().getVidaAtual();
            vidaAtual3 = jogador3.getPersonagens().getFirst().getVidaAtual();
            vidaAtual4 = jogador4.getPersonagens().getFirst().getVidaAtual();

            jogador1.getPersonagens().getFirst().getClasse().Atacar(personagem2);
            jogador3.getPersonagens().getFirst().getClasse().Atacar(personagem4);

            if(jogador2.getPersonagens().getFirst().getVidaAtual() != vidaAtual2 &&
            jogador1.getPersonagens().getFirst().getVidaAtual() == vidaAtual1){
                menor1 = true;
            }

            if(jogador4.getPersonagens().getFirst().getVidaAtual() != vidaAtual4 &&
                    jogador3.getPersonagens().getFirst().getVidaAtual() == vidaAtual3){
                menor2 = true;
            }
            assertTrue(menor1);
            assertTrue(menor2);

        }
    }

