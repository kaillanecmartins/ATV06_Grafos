package Questao3;

import Questao1.*;
import Util.*;

public class Questao3 {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(true);

        // Inserir vértices
        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");

        // Inserir arestas
        grafo.inserirAresta("A", "B", 0);
        grafo.inserirAresta("B", "C", 0);
        grafo.inserirAresta("C", "D", 0);
        grafo.inserirAresta("D", "E", 0);
        grafo.inserirAresta("A", "E", 0);
        grafo.inserirAresta("E", "A", 0);

        // Verificar vértices raiz
        ListaLigada<Vertice> verticesRaiz = grafo.verificarVerticesRaiz();

        // Imprimir os vértices raiz
        if (verticesRaiz.tamanho() > 0) {
            System.out.println("Vértices Raiz:");
            for (int i = 0; i < verticesRaiz.tamanho(); i++) {
                System.out.println(verticesRaiz.get(i).getRotulo());
            }
        } else {
            System.out.println("Nenhum vértice raiz encontrado.");
        }
    }
}
