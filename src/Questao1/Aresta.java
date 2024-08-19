package Questao1;

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
}

