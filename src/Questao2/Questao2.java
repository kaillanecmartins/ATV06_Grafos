package Questao2;

import Questao1.*;
import Util.LeituraArquivo;
import java.util.StringTokenizer;

public class Questao2 {
    
    public static void main(String[] args) {
        Grafo grafo = new Grafo(true);

        // Inserir vértices
        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");

        // Inserir arestas
        grafo.inserirAresta("A", "B", 0);
        grafo.inserirAresta("A", "C", 0);
        grafo.inserirAresta("B", "D", 0);
        grafo.inserirAresta("C", "D", 0);

        // Executar DFS e imprimir tempos de chegada e partida
        grafo.executarDFS();
    }
    /*public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>(false);

        LeituraArquivo arq = new LeituraArquivo("src/Entradas/dados_q2e3.txt");
        StringTokenizer str;
        int count = 1;
        String dados = arq.lerEnesima(count).toString();
        String aux;

        System.out.println("------QUESTAO 2------");

        while (dados != null) {
            str = new StringTokenizer(dados, ";");
            if (str.hasMoreElements()) {
                aux = str.nextToken();
                Vertice<String> v1 = new Vertice<>(aux);
                if (grafo.pesquisarVertice(aux) != null) {
                    grafo.inserirVertice(v1);
                }
                aux = str.nextToken();
                Vertice<String> v2 = new Vertice<>(aux);
                if (grafo.pesquisarVertice(aux) != null) {
                    grafo.inserirVertice(v2);
                }
                Aresta<String> aresta = new Aresta<>(v1, v2, false);
                if (grafo.pesquisarAresta(v1, v2) != null && grafo.pesquisarAresta(v2, v1) != null) {
                    grafo.inserirAresta(aresta);
                }
            }
            count++;
            try {
                dados = arq.lerEnesima(count).toString();
            } catch (Exception e) {
                break;
            }
        }

        boolean resultado = grafo.contemCiclo();
        if (resultado) {
            System.out.println("O grafo contém ciclo");
        } else {
            System.out.println("O grafo não contém ciclo");
        }
    }*/
}
