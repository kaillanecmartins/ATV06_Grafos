package Questao1;

public class Vertice {
    private String rotulo;
    private int grau;
    int tempoChegada;
    int tempoPartida;
    Vertice proximo;

    public Vertice(String rotulo) {
        this.rotulo = rotulo;
        this.grau = 0;
        this.proximo = null;
        this.tempoChegada = -1;
        this.tempoPartida = -1;
    }

    /*public Vertice(String rotulo) {
        this.rotulo = rotulo;
        this.grau = 0;
    }*/

    public String getRotulo() {
        return rotulo;
    }

    public int getGrau() {
        return grau;
    }

    public void incrementarGrau() {
        this.grau++;
    }

    public void decrementarGrau() {
        if (this.grau > 0) {
            this.grau--;
        }
    }
}

