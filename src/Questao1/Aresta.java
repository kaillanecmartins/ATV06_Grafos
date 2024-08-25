package Questao1;

import java.util.Objects;

public class Aresta implements Comparable<Aresta> {
    private Vertice verticeOrigem;
    private Vertice verticeDestino;
    private int peso;

    public Aresta(Vertice verticeOrigem, Vertice verticeDestino, int peso) {
        this.verticeOrigem = verticeOrigem;
        this.verticeDestino = verticeDestino;
        this.peso = peso;
    }

    public Vertice getVerticeOrigem() {
        return verticeOrigem;
    }

    public Vertice getVerticeDestino() {
        return verticeDestino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(Aresta outraAresta) {
        return Integer.compare(this.peso, outraAresta.getPeso());
    }

    @Override
    public String toString(){
        return String.format("( %s, %s)", verticeOrigem.getRotulo(), verticeDestino.getRotulo());
    }

    //TODO
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta aresta = (Aresta) o;
        return peso == aresta.peso
                && verticeOrigem.equals(aresta.verticeOrigem)
                && verticeDestino.equals(aresta.verticeDestino);
    }

}

