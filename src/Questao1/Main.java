package Questao1;

import Util.ArquivoToGrafo;
import Util.LeituraArquivo;

import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(true);

        LeituraArquivo arq = new LeituraArquivo("src/Entradas/dados_q1.txt");
        StringTokenizer str;
        int count = 1;
        String dados = arq.lerEnesima(count).toString();
        String aux;

        System.out.println("------QUESTAO 1------");

        /*
        grafo.imprimirGrafo();

        while (dados != null) {
            str = new StringTokenizer(dados, ";");
            if (str.hasMoreElements()) {
                aux = str.nextToken();
                Vertice v1 = new Vertice(aux);
                if (grafo.pesquisarVertice(aux) != null) {
                    grafo.inserirVertice(v1.getRotulo());
                }
                aux = str.nextToken();
                Vertice v2 = new Vertice(aux);
                if (grafo.pesquisarVertice(aux) != null) {
                    grafo.inserirVertice(v2.getRotulo());
                }
                Aresta aresta = new Aresta(v1, v2, 0);
                grafo.inserirAresta(v1.getRotulo(), v2.getRotulo(), aresta.getPeso());
            }
            count++;
            try {
                dados = arq.lerEnesima(count).toString();
            } catch (Exception e) {
                break;
            }
        }*/

        grafo = ArquivoToGrafo.get("src/Entradas/dados_q1.txt", true);
        grafo.imprimirGrafo();
        // Executar DFS e imprimir tempos de chegada e partida
        grafo.executarDFS();
    }
}
