package Questao2;

import Questao1.*;
import Util.ArquivoToGrafo;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = ArquivoToGrafo.get("src/Entradas/dados_q2.txt", true);

        System.out.println("------QUESTAO 2------");

        // Executa DFS e imprime tempos de chegada e partida
        grafo.executarDFS();
    }
}
