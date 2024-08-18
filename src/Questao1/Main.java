package Questao1;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");

        grafo.inserirAresta("A", "B", 5);
        grafo.inserirAresta("B", "C");

        grafo.imprimirGrafo();

        System.out.println("Removendo aresta B-C");
        grafo.removerAresta("B", "C");
        grafo.imprimirGrafo();

        System.out.println("Removendo vertice B");
        grafo.removerVertice("B");
        grafo.imprimirGrafo();
    }
}
