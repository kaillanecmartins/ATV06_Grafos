package Questao2;

import Questao1.*;
import Util.ArquivoToGrafo;
import Util.LeituraArquivo;
import java.util.StringTokenizer;

public class Questao2 {
    public static void main(String[] args) {
        Grafo grafo = ArquivoToGrafo.get("src/Entradas/dados_q2.txt", true);

        System.out.println("------QUESTAO 2------");

        // Executa DFS e imprime tempos de chegada e partida
        grafo.executarDFS();
    }
}
