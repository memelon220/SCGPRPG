package servico.entidade;

import java.util.Random;
import java.util.Scanner;

public class Personagem {
    Scanner sc = new Scanner(System.in);
    private int XP, nivel, vidaAtual, manaAtual, magia;
    private static int vidaMax, manaMax;
    private static int totalXP = 0;
    private String nome;
    private int forca, destreza, constituicao, inteligencia, sabedoria, carisma;
    private ClassePersonagem classe;
    private EspeciePersonagem especie;
    private final Random random = new Random();
    boolean randomizar;

    public Personagem(String nome, boolean randomizar) {
        this.XP = 0;
        nivel = 1;
        this.nome = nome;
        this.randomizar = randomizar;
        definirAtributos(randomizar);
    }

    public void adicionarXP(int v) {
        System.out.println("Você recebeu " + v + " Pontos de Experiência");
        XP += v;
        while (XP >= 100) {
            XP -= 100;
            nivel++;
            vidaMax++;
            System.out.println("Subiu de nível! Nível atual: " + nivel);
        }
    }

    public void definirAtributos(boolean opc) {
        if (opc) {
            forca = random.nextInt(8, 19);
            destreza = random.nextInt(8, 19);
            constituicao = random.nextInt(8, 19);
            sabedoria = random.nextInt(8, 19);
            inteligencia = random.nextInt(8, 19);
            carisma = random.nextInt(8, 19);
        } else {
            System.out.println("Digite o valor de força: ");
            forca = sc.nextInt();
            System.out.println("Digite o valor de destreza: ");
            destreza = sc.nextInt();
            System.out.println("Digite o valor de constituição: ");
            constituicao = sc.nextInt();
            System.out.println("Digite o valor de inteligência: ");
            inteligencia = sc.nextInt();
            System.out.println("Digite o valor de sabedoria: ");
            sabedoria = sc.nextInt();
            System.out.println("Digite o valor de carisma: ");
            carisma = sc.nextInt();
        }
        definirSecundarios();
    }

    public int calcularModificador(int atributo){
        int aux = (int) Math.floor((atributo - 10) / 2);
        return aux;
    }

    public void definirSecundarios() {
        vidaMax = 10 + ((constituicao - 10) / 2);
        magia = 8 + ((inteligencia - 10) / 2);
    }

    public void exibirFicha() {
        System.out.printf("Nome: %s | Nível: %d | Classe: %s | Especie: %s| XP: %d | Vida Máxima: %d | Vida Atual: %d | Magia: %d%n", nome, nivel, this.classe.getNomeClasse(), this.especie.getNome(), XP, vidaMax, vidaAtual, magia);
        System.out.println("|Atributos|");
        System.out.printf("Força: %d | Destreza: %d | Constituição: %d | Inteligência: %d | Sabedoria: %d | Carisma: %d%n",
                forca, destreza, constituicao, inteligencia, sabedoria, carisma);
    }

    public void exibirXP() {
        System.out.println("XP: " + XP);
        System.out.println("Nível: " + nivel);
    }


    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vida) {
        this.vidaAtual = vida;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(int vidaMax) {
        Personagem.vidaMax = vidaMax;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getSabedoria() {
        return sabedoria;
    }

    public void setSabedoria(int sabedoria) {
        this.sabedoria = sabedoria;
    }

    public int getCarisma() {
        return carisma;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public ClassePersonagem getClasse() {
        return classe;
    }

    public void setClasse(ClassePersonagem classe) {
        this.classe = classe;
    }

    public int getManaAtual() {
        return manaAtual;
    }

    public void setManaAtual(int manaAtual) {
        this.manaAtual = manaAtual;
    }

    public static int getManaMax() {
        return manaMax;
    }

    public static void setManaMax(int manaMax) {
        Personagem.manaMax = manaMax;
    }

    public static int getTotalXP() {
        return totalXP;
    }

    public static void setTotalXP(int totalXP) {
        Personagem.totalXP = totalXP;
    }

    public EspeciePersonagem getEspecie() {
        return especie;
    }

    public void setEspecie(EspeciePersonagem especie) {
        this.especie = especie;
    }
}