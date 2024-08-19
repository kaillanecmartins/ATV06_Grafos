package Questao1;

public class Main {
    public static void main(String[] args) {
        // Criando um grafo direcionado
        Grafo grafo = new Grafo(true);

        // Teste de inserção de vértices
        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");

        // Teste de inserção de arestas
        grafo.inserirAresta("A", "B", 5);
        grafo.inserirAresta("B", "C", 3);
        //grafo.inserirAresta("C", "A", 2);

        // Imprimir o grafo
        System.out.println("Grafo direcionado:");
        grafo.imprimirGrafo();

        // Verificação de ciclo
        boolean haCiclo = grafo.verificarCiclo();
        System.out.println("\nO grafo tem ciclo? " + haCiclo);

        // Criando um grafo não direcionado
        Grafo grafoNaoDirecionado = new Grafo(false);

        // Teste de inserção de vértices
        grafoNaoDirecionado.inserirVertice("A");
        grafoNaoDirecionado.inserirVertice("B");
        grafoNaoDirecionado.inserirVertice("C");

        // Teste de inserção de arestas
        grafoNaoDirecionado.inserirAresta("A", "B", 5);
        grafoNaoDirecionado.inserirAresta("B", "C", 3);

        // Imprimir o grafo
        System.out.println("\nGrafo não direcionado:");
        grafoNaoDirecionado.imprimirGrafo();

        // Verificação de ciclo
        haCiclo = grafoNaoDirecionado.verificarCiclo();
        System.out.println("\nO grafo não direcionado tem ciclo? " + haCiclo);
    }
}
