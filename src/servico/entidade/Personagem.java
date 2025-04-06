package servico.entidade;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

public class Personagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L; // Versão inicial

    private Jogador jogador;
    private int XP, nivel, vidaAtual, manaAtual, magia, classeResistencia;
    private static int vidaMax, manaMax;
    private static int totalXP = 0;
    private String nome;
    private final String ID;
    private int forca, destreza, constituicao, inteligencia, sabedoria, carisma;
    private ClassePersonagem classe;
    private EspeciePersonagem especie;
    private final Random random = new Random();


    public Personagem(String nome, int nivel){
        Random random = new Random();
        int numero = random.nextInt(1000000);
        this.ID = String.format("P%06d", numero);
        this.XP = 0;
        this.nivel = nivel;
        this.nome = nome;
        definirAtributos();
    }

    public Personagem(String nome, int nivel, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma) {
        Random random = new Random();
        int numero = random.nextInt(1000000);
        this.ID = String.format("P%06d", numero);
        this.XP = 0;
        this.nivel = nivel;
        this.nome = nome;
        definirAtributos(forca, destreza, constituicao, inteligencia, sabedoria, carisma);
    }

    public void adicionarXP(int v) {
        XP += v;
        while (XP >= 100) {
            XP -= 100;
            nivel++;
            vidaMax++;
        }
    }

    public void definirAtributos(){
            forca = random.nextInt(8, 19);
            destreza = random.nextInt(8, 19);
            constituicao = random.nextInt(8, 19);
            sabedoria = random.nextInt(8, 19);
            inteligencia = random.nextInt(8, 19);
            carisma = random.nextInt(8, 19);
            definirSecundarios();
        }

   public void definirAtributos(int forca, int destreza, int constituicao,  int inteligencia, int sabedoria, int carisma){
                this.forca = forca;
                this.destreza = destreza;
                this.constituicao = constituicao;
                this.inteligencia = inteligencia;
                this.sabedoria = sabedoria;
                this.carisma = carisma;
                definirSecundarios();
            }

    public int calcularModificador(int atributo) {
        return (int) (double) ((atributo - 10) / 2);
    }

    public void definirSecundarios() {
        vidaMax = 10 + calcularModificador(constituicao);
        magia = 8 + calcularModificador(inteligencia);
        classeResistencia = 10 + calcularModificador(destreza);
    }
/*
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
*/

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getClasseResistencia(){
        return classeResistencia;
    }

    public void setClasseResistencia(int cr){
        this.classeResistencia = cr;
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

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public String getID() {
        return ID;
    }

}