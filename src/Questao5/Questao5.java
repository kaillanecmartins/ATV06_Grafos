package Questao5;

import Questao1.*;

public class Questao5 {
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

        // Gerar MST usando Prim
        System.out.println("---- Algoritmo de Prim ----");
        grafo.mstPrim("A");

        // Gerar MST usando Kruskal
        System.out.println("\n---- Algoritmo de Kruskal ----");
        grafo.mstKruskal();

        // Gerar MST usando Boruvka
        System.out.println("\n---- Algoritmo de Boruvka ----");
        grafo.mstBoruvka();
    }
}
