import java.util.Random;
import java.util.Scanner;

public class Personagem {
    Scanner sc = new Scanner(System.in);
    private int XP, nivel, vida, magia;
    private String nome;
    private int forca, destreza, constituicao, inteligencia, sabedoria, carisma;
    private Random random = new Random();
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
            vida++;
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

    public void definirSecundarios() {
        vida = 10 + ((constituicao - 10) / 2);
        magia = 8 + ((inteligencia - 10) / 2);
    }

    public void exibirFicha() {
        System.out.printf("Nome: %s | Nível: %d | XP: %d | Vida: %d | Magia: %d%n", nome, nivel, XP, vida, magia);
        System.out.println("|Atributos|");
        System.out.printf("Força: %d | Destreza: %d | Constituição: %d | Inteligência: %d | Sabedoria: %d | Carisma: %d%n",
                forca, destreza, constituicao, inteligencia, sabedoria, carisma);
    }

    public void exibirXP() {
        System.out.println("XP: " + XP);
        System.out.println("Nível: " + nivel);
    }
    /* Luigi: Transformei esse pedaço na própria classe.
    public void rolarDado(int q, int l) {
        int aux = 0;
        for (int i = 0; i < q; i++) {
            int dado = random.nextInt(1, l + 1);
            aux += dado;
        }
        System.out.println(q + "d" + l + " = " + aux);
    }
    */
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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

}


