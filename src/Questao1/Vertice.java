package Questao1;

import Util.ListaLigada;

public class Vertice {
    private String rotulo;
    private int grau;
    private ListaLigada<Aresta> adjacentes;
    private int tempoChegada; 
    private int tempoPartida;   

    public Vertice(String rotulo) {
        this.rotulo = rotulo;
        this.grau = 0;
        this.adjacentes = new ListaLigada<>();
        this.tempoChegada = -1; 
        this.tempoPartida = -1;  
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public int getGrau() {
        return grau;
    }

    public void incrementaGrau() {
        grau++;
    }

    public void decrementaGrau() {
        grau--;
    }

    public void adicionarAresta(Aresta aresta) {
        adjacentes.adicionar(aresta);
        incrementaGrau();
    }

    public void removerAresta(Aresta aresta) {
        if (adjacentes.remover(aresta)) {
            decrementaGrau();
        }
    }

    public ListaLigada<Aresta> getAdjacentes() {
        return adjacentes;
    }
    
    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getTempoPartida() {
        return tempoPartida;
    }

    public void setTempoPartida(int tempoPartida) {
        this.tempoPartida = tempoPartida;
    }
}


