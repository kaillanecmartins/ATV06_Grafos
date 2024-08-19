package Questao6;

import Questao1.*;

public class Questao6 {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(false); // Grafo não direcionado

        // Inserir vértices
        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");
        grafo.inserirVertice("F");

        // Inserir arestas com pesos
        grafo.inserirAresta("A", "B", 4);
        grafo.inserirAresta("A", "F", 2);
        grafo.inserirAresta("B", "C", 6);
        grafo.inserirAresta("B", "F", 5);
        grafo.inserirAresta("C", "D", 3);
        grafo.inserirAresta("D", "E", 8);
        grafo.inserirAresta("E", "F", 7);

        // Encontrar e imprimir o ciclo mínimo usando DFS após gerar a MST com Prim
        System.out.println("---- Ciclo Mínimo (TSP Aproximado) ----");
        grafo.cicloMinimoDFS("A");
    }
}
