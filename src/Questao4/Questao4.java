package Questao4;

import Questao1.*;

public class Questao4 {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(false); // Grafo não direcionado

        // Inserir vértices
        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");
        grafo.inserirVertice("F");

        // Inserir arestas
        grafo.inserirAresta("A", "B", 0);
        grafo.inserirAresta("A", "C", 0);
        grafo.inserirAresta("B", "D", 0);
        grafo.inserirAresta("C", "D", 0);
        grafo.inserirAresta("D", "E", 0);
        grafo.inserirAresta("E", "F", 0);

        // Verificar se o grafo é bipartido e imprimir as partições
        grafo.imprimirParticoes();
    }
}
