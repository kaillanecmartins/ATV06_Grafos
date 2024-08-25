package Questao1;

import Util.ArquivoToGrafo;
import Util.LeituraArquivo;

import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(true);

        System.out.println("------QUESTAO 1------");

        grafo = ArquivoToGrafo.get("src/Entradas/dados_q1.txt", true);

        System.out.println("Imprimindo grafo");
        grafo.imprimirGrafo();
        System.out.println("Excluindo aresta");
        grafo.removerAresta("A", "B");
        System.out.println("Imprimindo grafo novamente");
        grafo.imprimirGrafo();
    }
}
