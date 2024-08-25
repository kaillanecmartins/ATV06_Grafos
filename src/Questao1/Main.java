package Questao1;

import Util.ArquivoToGrafo;
import Util.LeituraArquivo;

import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(true);

        System.out.println("------QUESTAO 1------");

        grafo = ArquivoToGrafo.get("src/Entradas/dados_q1.txt", true);

        System.out.println("Exemplo com grafo direcionado");
        System.out.println("Imprimindo grafo");
        grafo.imprimirGrafo();
        System.out.println("Excluindo aresta");
        grafo.removerAresta("A", "B");
        System.out.println("Imprimindo grafo novamente");
        grafo.imprimirGrafo();
        System.out.println("Inserindo aresta");
        grafo.inserirAresta("D", "A", 1);
        System.out.println("Imprimindo grafo novamente");
        grafo.imprimirGrafo();
        System.out.println("Verificando se o grafo contém ciclo");
        if(grafo.verificarCiclo()){
            System.out.println("O grafo contém ciclo");
        }else{
            System.out.println("O grafo não contém ciclo");
        }
    }
}
