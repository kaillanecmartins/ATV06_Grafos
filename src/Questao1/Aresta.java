package Questao1;

public class Aresta {
    private Vertice origem;
    private Vertice destino;
    private int peso; // Peso opcional
    Aresta proxima;

    public Aresta(Vertice destino, int peso) {
        this.destino = destino;
        this.peso = peso;
        this.proxima = null;
    }

    public Aresta(Vertice origem, Vertice destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Aresta(Vertice origem, Vertice destino) {
        this(origem, destino, 1); // Peso padrão = 1
    }

    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
}
