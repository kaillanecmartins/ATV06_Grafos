package Questao2;

import Util.LeituraArquivo;
import Questao1.*;

public class Main {
    public static void main(String[] args) {
        // Verifica se o caminho do arquivo foi passado como argumento
        if (args.length != 1) {
            System.out.println("Uso: java Main <caminho_do_arquivo>");
            return;
        }

        String caminhoArquivo = "src/Entradas/dados_q2.txt";

        // Cria um objeto de leitura do arquivo
        LeituraArquivo leituraArquivo = new LeituraArquivo(caminhoArquivo);

        // Lê todas as linhas do arquivo
        Object[] linhas = leituraArquivo.lerTudo();

        // Cria um grafo não direcionado
        Grafo grafo = new Grafo(false);

        // Processa cada linha e insere os vértices e arestas no grafo
        for (Object linha : linhas) {
            String[] vertices = linha.toString().split(";");
            if (vertices.length == 2) {
                String verticeOrigem = vertices[0].trim();
                String verticeDestino = vertices[1].trim();

                // Insere os vértices no grafo (se já não existirem)
                if (grafo.pesquisarVertice(verticeOrigem) == null) {
                    grafo.inserirVertice(verticeOrigem);
                }
                if (grafo.pesquisarVertice(verticeDestino) == null) {
                    grafo.inserirVertice(verticeDestino);
                }

                // Insere a aresta no grafo com peso 1 (pode ser ajustado conforme necessário)
                grafo.inserirAresta(verticeOrigem, verticeDestino, 1);
            } else {
                System.out.println("Formato de linha inválido: " + linha);
            }
        }

        // Exibe o grafo montado
        System.out.println("Grafo montado:");
        grafo.imprimirGrafo();

        /*
        // Executa os algoritmos de MST
        System.out.println("\nÁrvore Geradora Mínima usando o Algoritmo de Prim:");
        grafo.algoritmoPrim(grafo.vertices.get(0).getRotulo());

        System.out.println("\nÁrvore Geradora Mínima usando o Algoritmo de Kruskal:");
        grafo.algoritmoKruskal();

        System.out.println("\nÁrvore Geradora Mínima usando o Algoritmo de Boruvka:");
        grafo.algoritmoBoruvka();*/
    }
}
