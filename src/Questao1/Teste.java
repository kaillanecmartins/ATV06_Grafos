package Questao1;

public class Teste {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.adicionarVertice();
        grafo.adicionarVertice();
        grafo.adicionarVertice();
        grafo.adicionarVertice();

        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(0, 2);
        grafo.adicionarAresta(1, 2);
        grafo.adicionarAresta(2, 3);

        System.out.println("Grafo:");
        grafo.imprimirGrafo();

        System.out.println("Removendo aresta entre o vértice 0 e 2:");
        grafo.removerAresta(0, 2);

        System.out.println("Grafo após remoção:");
        grafo.imprimirGrafo();

        System.out.println("Vizinhos do vértice 0: " + grafo.obterAdjacentes(0));
    
    }
}
