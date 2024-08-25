package Questao5;

import Util.ArquivoToGrafo;

public class Questao5 {
    public static void main(String[] args) {

        var grafo = ArquivoToGrafo.get("src/Entradas/dados_q5.txt", false);

        // Gerar MST usando Prim
        System.out.println("---- Algoritmo de Prim ----");
        System.out.println(Prim.executar(grafo, "A"));

        // Gerar MST usando Kruskal
        System.out.println("\n---- Algoritmo de Kruskal ----");
        System.out.println(Kruskal.execute(grafo));

        // Gerar MST usando Boruvka
        System.out.println("\n---- Algoritmo de Boruvka ----");
        System.out.println(Boruvka.execute(grafo));
    }
}
