package Questao1;

public class Teste {
    public static void main(String[] args) {
        // Criando um grafo não direcionado
        Grafo<Integer> grafo = new Grafo<>(false);

        Vertice<Integer> v1 = new Vertice<>(1);
        Vertice<Integer> v2 = new Vertice<>(2);
        Vertice<Integer> v3 = new Vertice<>(3);
        Vertice<Integer> v4 = new Vertice<>(4);

        // Adicionando vértices ao grafo
        grafo.inserirVertice(v1);
        grafo.inserirVertice(v2);
        grafo.inserirVertice(v3);
        grafo.inserirVertice(v4);

        // Adicionando arestas (não direcionadas)
        Aresta<Integer> a1 = new Aresta<>(v1, v2, false);
        Aresta<Integer> a2 = new Aresta<>(v2, v3, false);
        Aresta<Integer> a3 = new Aresta<>(v3, v4, false);
        Aresta<Integer> a4 = new Aresta<>(v4, v1, false);
        
        grafo.inserirAresta(a1);
        grafo.inserirAresta(a2);
        grafo.inserirAresta(a3);
        grafo.inserirAresta(a4);

        // Verificando a existência de ciclos
        boolean possuiCiclo = grafo.contemCiclo();
        if (possuiCiclo) {
            System.out.println("O grafo contém um ciclo.");
        } else {
            System.out.println("O grafo não contém ciclos.");
        }
    }
}
