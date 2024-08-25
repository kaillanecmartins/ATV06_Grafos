package Questao3;

import Questao1.*;
import Util.*;

public class Questao3 {
    public static void main(String[] args) {
        Grafo grafo = ArquivoToGrafo.get("src/Entradas/dados_q3.txt", true);

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
