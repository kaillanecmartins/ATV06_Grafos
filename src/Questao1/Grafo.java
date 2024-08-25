package Questao1;

import Util.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Grafo {
    private ListaLigada<Vertice> vertices;
    private boolean direcionado;
     private int tempo;

    public Grafo(boolean direcionado) {
        this.vertices = new ListaLigada<>();
        this.direcionado = direcionado;
        this.tempo = 0;
    }

    public void inserirVertice(String rotulo) {
        if (pesquisarVertice(rotulo) != null) {
            throw new IllegalArgumentException("Vértice com rótulo " + rotulo + " já existe.");
        }
        vertices.adicionar(new Vertice(rotulo));
    }

    //TODO
    public ListaLigada<String> getAllVertices(){
        var result = new ListaLigada<String>();
        for (int i = 0; i < vertices.tamanho(); i++) {
            result.adicionar(vertices.get(i).getRotulo());
        }
        return result;
    }
    public void removerVertice(String rotulo) {
        Vertice vertice = pesquisarVertice(rotulo);
        if (vertice == null) {
            throw new IllegalArgumentException("Vértice com rótulo " + rotulo + " não encontrado.");
        }

        while (vertice.getAdjacentes().tamanho() > 0) {
            removerAresta(vertice.getRotulo(), vertice.getAdjacentes().get(0).getVerticeDestino().getRotulo());
        }
        vertices.remover(vertice);
    }

    public void inserirAresta(String origem, String destino, int peso) {
        Vertice verticeOrigem = pesquisarVertice(origem);
        Vertice verticeDestino = pesquisarVertice(destino);
        if (verticeOrigem == null || verticeDestino == null) {
            throw new IllegalArgumentException("Um ou ambos os vértices não foram encontrados.");
        }

        Aresta arestaExistente = pesquisarAresta(origem, destino);
        if (arestaExistente != null) {
            throw new IllegalArgumentException("Aresta entre " + origem + " e " + destino + " já existe.");
        }

        Aresta aresta = new Aresta(verticeOrigem, verticeDestino, peso);
        verticeOrigem.adicionarAresta(aresta);

        if (!direcionado) {
            Aresta arestaSimetrica = new Aresta(verticeDestino, verticeOrigem, peso);
            verticeDestino.adicionarAresta(arestaSimetrica);
        }
    }

    public void removerAresta(String origem, String destino) {
        Vertice verticeOrigem = pesquisarVertice(origem);
        Vertice verticeDestino = pesquisarVertice(destino);
        if (verticeOrigem == null || verticeDestino == null) {
            throw new IllegalArgumentException("Um ou ambos os vértices não foram encontrados.");
        }

        Aresta arestaExistente = null;
        for (int i = 0; i < verticeOrigem.getAdjacentes().tamanho(); i++) {
            Aresta aresta = verticeOrigem.getAdjacentes().get(i);
            if (aresta.getVerticeDestino().equals(verticeDestino)) {
                arestaExistente = aresta;
                break;
            }
        }

        if (arestaExistente == null) {
            throw new IllegalArgumentException("Aresta entre " + origem + " e " + destino + " não encontrada.");
        }

        verticeOrigem.removerAresta(arestaExistente);

        if (!direcionado) {
            for (int i = 0; i < verticeDestino.getAdjacentes().tamanho(); i++) {
                Aresta aresta = verticeDestino.getAdjacentes().get(i);
                if (aresta.getVerticeDestino().equals(verticeOrigem)) {
                    verticeDestino.removerAresta(aresta);
                    break;
                }
            }
        }
    }

    public Vertice pesquisarVertice(String rotulo) {
        for (int i = 0; i < vertices.tamanho(); i++) {
            Vertice vertice = vertices.get(i);
            if (vertice.getRotulo().equals(rotulo)) {
                return vertice;
            }
        }
        return null;
    }

    public Aresta pesquisarAresta(String origem, String destino) {
        Vertice verticeOrigem = pesquisarVertice(origem);
        Vertice verticeDestino = pesquisarVertice(destino);
        if (verticeOrigem != null && verticeDestino != null) {
            for (int i = 0; i < verticeOrigem.getAdjacentes().tamanho(); i++) {
                Aresta aresta = verticeOrigem.getAdjacentes().get(i);
                if (aresta.getVerticeDestino().equals(verticeDestino)) {
                    return aresta;
                }
            }
        }
        return null;
    }
    public int tamanhoVertice(){
        return vertices.tamanho();
    }
    public ListaLigada<Vertice> obterAdjacentes(String rotulo) {
        Vertice vertice = pesquisarVertice(rotulo);
        ListaLigada<Vertice> adjacentes = new ListaLigada<>();
        if (vertice != null) {
            for (int i = 0; i < vertice.getAdjacentes().tamanho(); i++) {
                adjacentes.adicionar(vertice.getAdjacentes().get(i).getVerticeDestino());
            }
        }
        return adjacentes;
    }

    public void imprimirGrafo() {
        for (int i = 0; i < vertices.tamanho(); i++) {
            Vertice vertice = vertices.get(i);
            System.out.print(vertice.getRotulo() + ": ");
            for (int j = 0; j < vertice.getAdjacentes().tamanho(); j++) {
                System.out.print(vertice.getAdjacentes().get(j).getVerticeDestino().getRotulo() + " ");
            }
            System.out.println();
        }
    }

    // Método para verificar se há ciclos no grafo usando DFS
    public boolean verificarCiclo() {
        boolean[] visitado = new boolean[vertices.tamanho()];
        boolean[] pilhaRecursao = new boolean[vertices.tamanho()];

        for (int i = 0; i < vertices.tamanho(); i++) {
            if (verificarCicloDFS(vertices.get(i), visitado, pilhaRecursao)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificarCicloDFS(Vertice vertice, boolean[] visitado, boolean[] pilhaRecursao) {
        int indiceVertice = obterIndiceVertice(vertice.getRotulo());

        if (indiceVertice == -1) return false;

        if (pilhaRecursao[indiceVertice]) return true;
        if (visitado[indiceVertice]) return false;

        visitado[indiceVertice] = true;
        pilhaRecursao[indiceVertice] = true;

        ListaLigada<Aresta> adjacentes = vertice.getAdjacentes();
        for (int i = 0; i < adjacentes.tamanho(); i++) {
            Vertice verticeAdjacente = adjacentes.get(i).getVerticeDestino();
            if (verificarCicloDFS(verticeAdjacente, visitado, pilhaRecursao)) {
                return true;
            }
        }

        pilhaRecursao[indiceVertice] = false;
        return false;
    }

    //TODO o normal não funciona direito então fiz outro
    public boolean verificarCiclo2() {
        if(vertices.tamanho() < 3){
            return false;
        }

        ListaLigada<Vertice> visitados = new ListaLigada<>();

        for (int i = 0; i < vertices.tamanho(); i++) {
            var verticeAtual = vertices.get(i);
            if(!visitados.pesquisar(verticeAtual)){
                if(verificarCicloDFS2(verticeAtual, null, visitados)){
                    return true;
                }
            }
        }
        return false;
    }

    //TODO também não funcionava e fiz outro
    private boolean verificarCicloDFS2(Vertice atual,Vertice anterior, ListaLigada<Vertice> visitados) {
        visitados.adicionar(atual);

        var vizinhos = atual.getAdjacentes();
        for (int i = 0; i < vizinhos.tamanho(); i++) {
            var verticeAtual = vizinhos.get(i).getVerticeDestino();
            if(!visitados.pesquisar(verticeAtual)){
                if(verificarCicloDFS2(verticeAtual, atual, visitados)){
                    return true;
                }
            } else if (verticeAtual != anterior && anterior != null) {
                return true;
                
            }
        }
        return false;
    }
    private int obterIndiceVertice(String rotulo) {
        for (int i = 0; i < vertices.tamanho(); i++) {
            if (vertices.get(i).getRotulo().equals(rotulo)) {
                return i;
            }
        }
        return -1;
    }
    
     // Método para executar DFS e calcular tempos de chegada e partida
    public void executarDFS() {
        boolean[] visitado = new boolean[vertices.tamanho()];
        this.tempo = 0;

        for (int i = 0; i < vertices.tamanho(); i++) {
            Vertice vertice = vertices.get(i);
            if (!visitado[i]) {
                dfs(vertice, visitado);
            }
        }

        // Imprime os tempos de chegada e partida
        for (int i = 0; i < vertices.tamanho(); i++) {
            Vertice vertice = vertices.get(i);
            System.out.println(vertice.getRotulo() + " (Chegada: " + vertice.getTempoChegada() + ", Partida: " + vertice.getTempoPartida() + ")");
        }
    }

    private void dfs(Vertice vertice, boolean[] visitado) {
        int indiceVertice = obterIndiceVertice(vertice.getRotulo());
        if (indiceVertice == -1) return;

        visitado[indiceVertice] = true;
        vertice.setTempoChegada(++tempo);

        ListaLigada<Aresta> adjacentes = vertice.getAdjacentes();
        for (int i = 0; i < adjacentes.tamanho(); i++) {
            Vertice verticeAdjacente = adjacentes.get(i).getVerticeDestino();
            if (!visitado[obterIndiceVertice(verticeAdjacente.getRotulo())]) {
                dfs(verticeAdjacente, visitado);
            }
        }

        vertice.setTempoPartida(++tempo);
    }
    
    // Método para verificar se um vértice é raiz
    public ListaLigada<Vertice> verificarVerticesRaiz() {
        ListaLigada<Vertice> verticesRaiz = new ListaLigada<>();

        for (int i = 0; i < vertices.tamanho(); i++) {
            Vertice vertice = vertices.get(i);
            boolean[] visitado = new boolean[vertices.tamanho()];

            dfs(vertice, visitado);

            // Verifica se todos os vértices foram visitados a partir desse vértice
            boolean todosVisitados = true;
            for (boolean v : visitado) {
                if (!v) {
                    todosVisitados = false;
                    break;
                }
            }

            if (todosVisitados) {
                verticesRaiz.adicionar(vertice);
            }
        }

        return verticesRaiz;
    }
    
     // Método para verificar se o grafo é bipartido utilizando DFS
    public boolean verificarBipartidoDFS() {
        int[] cores = new int[vertices.tamanho()];
        for (int i = 0; i < cores.length; i++) {
            cores[i] = -1; // Inicialmente, todos os vértices não têm cor
        }

        // Tentar colorir todos os componentes do grafo
        for (int i = 0; i < vertices.tamanho(); i++) {
            Vertice vertice = vertices.get(i);
            if (cores[i] == -1) {
                if (!dfsColorir(vertice, cores, 0)) {
                    return false; // Se não for possível colorir, não é bipartido
                }
            }
        }

        // Se conseguiu colorir, o grafo é bipartido
        return true;
    }

    private boolean dfsColorir(Vertice vertice, int[] cores, int cor) {
        int indiceVertice = obterIndiceVertice(vertice.getRotulo());
        if (indiceVertice == -1) return false;

        cores[indiceVertice] = cor; // Atribui a cor ao vértice

        // Percorre os vértices adjacentes
        ListaLigada<Aresta> adjacentes = vertice.getAdjacentes();
        for (int i = 0; i < adjacentes.tamanho(); i++) {
            Vertice verticeAdjacente = adjacentes.get(i).getVerticeDestino();
            int indiceAdjacente = obterIndiceVertice(verticeAdjacente.getRotulo());

            if (cores[indiceAdjacente] == -1) {
                // Se o vértice adjacente não tem cor, colorir com a cor oposta
                if (!dfsColorir(verticeAdjacente, cores, 1 - cor)) {
                    return false;
                }
            } else if (cores[indiceAdjacente] == cores[indiceVertice]) {
                // Se o vértice adjacente tem a mesma cor, o grafo não é bipartido
                return false;
            }
        }

        return true;
    }

    // Método para imprimir as partições se o grafo for bipartido
    public void imprimirParticoes() {
        int[] cores = new int[vertices.tamanho()];
        for (int i = 0; i < cores.length; i++) {
            cores[i] = -1; // Inicialmente, todos os vértices não têm cor
        }

        boolean bipartido = true;

        for (int i = 0; i < vertices.tamanho(); i++) {
            Vertice vertice = vertices.get(i);
            if (cores[i] == -1) {
                if (!dfsColorir(vertice, cores, 0)) {
                    bipartido = false;
                    break;
                }
            }
        }

        if (bipartido) {
            ListaLigada<Vertice> particao1 = new ListaLigada<>();
            ListaLigada<Vertice> particao2 = new ListaLigada<>();

            for (int i = 0; i < vertices.tamanho(); i++) {
                if (cores[i] == 0) {
                    particao1.adicionar(vertices.get(i));
                } else {
                    particao2.adicionar(vertices.get(i));
                }
            }

            System.out.println("O grafo É bipartido:");
            System.out.print("Partição 1: ");
            imprimirVertices(particao1);

            System.out.print("Partição 2: ");
            imprimirVertices(particao2);

        } else {
            System.out.println("O grafo NÃO é bipartido.");
        }
    }

    private void imprimirVertices(ListaLigada<Vertice> particao) {
        for (int i = 0; i < particao.tamanho(); i++) {
            System.out.print(particao.get(i).getRotulo());
            if (i < particao.tamanho() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    // Algoritmo de Prim
    /*public void mstPrim(String verticeInicial) {
        Vertice verticeInicio = pesquisarVertice(verticeInicial);
        if (verticeInicio == null) {
            throw new IllegalArgumentException("Vértice inicial não encontrado.");
        }

        MinHeap heap = new MinHeap(arestas.tamanho());
        ListaLigada<Aresta> mst = new ListaLigada<>();
        boolean[] visitado = new boolean[vertices.tamanho()];

        adicionarArestasAdjacentes(heap, verticeInicio, visitado);

        while (!heap.estaVazio()) {
            Aresta arestaMin = heap.extrairMin();
            Vertice v = arestaMin.getVerticeDestino();

            if (!visitado[obterIndiceVertice(v.getRotulo())]) {
                mst.adicionar(arestaMin);
                adicionarArestasAdjacentes(heap, v, visitado);
            }
        }

        imprimirMST("Prim", mst);
    }*/
    /*
    // Algoritmo de Kruskal
    public void mstKruskal() {
        ListaLigada<Aresta> mst = new ListaLigada<>();
        ConjuntoDisjunto conjunto = new ConjuntoDisjunto(vertices.tamanho());

        // Ordena as arestas pelo peso
        ordenarArestasPorPeso();

        for (int i = 0; i < arestas.tamanho(); i++) {
            Aresta aresta = arestas.get(i);
            int origem = obterIndiceVertice(aresta.getVerticeOrigem().getRotulo());
            int destino = obterIndiceVertice(aresta.getVerticeDestino().getRotulo());

            if (conjunto.encontrar(origem) != conjunto.encontrar(destino)) {
                mst.adicionar(aresta);
                conjunto.unir(origem, destino);
            }
        }

        imprimirMST("Kruskal", mst);
    }

    private void ordenarArestasPorPeso() {
        // Algoritmo de ordenação simples para ordenar arestas pelo peso
        for (int i = 0; i < arestas.tamanho(); i++) {
            for (int j = 0; j < arestas.tamanho() - i - 1; j++) {
                if (arestas.get(j).compareTo(arestas.get(j + 1)) > 0) {
                    Aresta temp = arestas.get(j);
                    arestas.remover(temp);
                    arestas.adicionar(temp);
                }
            }
        }
    }

    // Algoritmo de Boruvka
    public void mstBoruvka() {
        ListaLigada<Aresta> mst = new ListaLigada<>();
        ConjuntoDisjunto conjunto = new ConjuntoDisjunto(vertices.tamanho());

        int componentes = vertices.tamanho();

        while (componentes > 1) {
            Aresta[] menorAresta = new Aresta[vertices.tamanho()];

            for (int i = 0; i < arestas.tamanho(); i++) {
                Aresta aresta = arestas.get(i);
                int origem = conjunto.encontrar(obterIndiceVertice(aresta.getVerticeOrigem().getRotulo()));
                int destino = conjunto.encontrar(obterIndiceVertice(aresta.getVerticeDestino().getRotulo()));

                if (origem != destino) {
                    if (menorAresta[origem] == null || aresta.getPeso() < menorAresta[origem].getPeso()) {
                        menorAresta[origem] = aresta;
                    }

                    if (menorAresta[destino] == null || aresta.getPeso() < menorAresta[destino].getPeso()) {
                        menorAresta[destino] = aresta;
                    }
                }
            }

            for (int i = 0; i < menorAresta.length; i++) {
                if (menorAresta[i] != null) {
                    int origem = conjunto.encontrar(obterIndiceVertice(menorAresta[i].getVerticeOrigem().getRotulo()));
                    int destino = conjunto.encontrar(obterIndiceVertice(menorAresta[i].getVerticeDestino().getRotulo()));

                    if (origem != destino) {
                        mst.adicionar(menorAresta[i]);
                        conjunto.unir(origem, destino);
                        componentes--;
                    }
                }
            }
        }

        imprimirMST("Boruvka", mst);
    }

    // Método auxiliar para imprimir a MST gerada
    private void imprimirMST(String algoritmo, ListaLigada<Aresta> mst) {
        System.out.println("MST usando " + algoritmo + ":");
        for (int i = 0; i < mst.tamanho(); i++) {
            Aresta aresta = mst.get(i);
            System.out.println(aresta.getVerticeOrigem().getRotulo() + " - " +
                               aresta.getVerticeDestino().getRotulo() + " : " +
                               aresta.getPeso());
        }
    }
    
    // Algoritmo de Prim para gerar MST
    public Grafo mstPrim(String verticeInicial) {
        Vertice verticeInicio = pesquisarVertice(verticeInicial);
        if (verticeInicio == null) {
            throw new IllegalArgumentException("Vértice inicial não encontrado.");
        }

        Grafo mst = new Grafo(false);  // MST será um novo grafo não direcionado
        mst.inserirVertice(verticeInicio.getRotulo());

        MinHeap heap = new MinHeap(arestas.tamanho());
        boolean[] visitado = new boolean[vertices.tamanho()];

        adicionarArestasAdjacentes(heap, verticeInicio, visitado);

        while (!heap.estaVazio()) {
            Aresta arestaMin = heap.extrairMin();
            Vertice v = arestaMin.getVerticeDestino();

            if (!visitado[obterIndiceVertice(v.getRotulo())]) {
                mst.inserirVertice(v.getRotulo());
                mst.inserirAresta(arestaMin.getVerticeOrigem().getRotulo(), v.getRotulo(), arestaMin.getPeso());
                adicionarArestasAdjacentes(heap, v, visitado);
            }
        }

        return mst;
    }

    private void adicionarArestasAdjacentes(MinHeap heap, Vertice vertice, boolean[] visitado) {
        int indice = obterIndiceVertice(vertice.getRotulo());
        visitado[indice] = true;

        ListaLigada<Aresta> adjacentes = vertice.getAdjacentes();
        for (int i = 0; i < adjacentes.tamanho(); i++) {
            Aresta aresta = adjacentes.get(i);
            if (!visitado[obterIndiceVertice(aresta.getVerticeDestino().getRotulo())]) {
                heap.inserir(aresta);
            }
        }
    }

    // Algoritmo DFS para encontrar o ciclo mínimo (TSP aproximado)
    public void cicloMinimoDFS(String verticeInicial) {
        Grafo mst = mstPrim(verticeInicial);
        boolean[] visitado = new boolean[mst.vertices.tamanho()];
        ListaLigada<Vertice> ciclo = new ListaLigada<>();

        Vertice verticeInicio = mst.pesquisarVertice(verticeInicial);
        mst.dfsCiclo(verticeInicio, visitado, ciclo);

        // Conectando o último vértice ao inicial para formar um ciclo
        Vertice ultimo = ciclo.get(ciclo.tamanho() - 1);
        System.out.println(ultimo.getRotulo() + " -> " + verticeInicial + " (retorno ao início)");

        // Imprimindo o ciclo
        imprimirCiclo(ciclo);
    }

    private void dfsCiclo(Vertice vertice, boolean[] visitado, ListaLigada<Vertice> ciclo) {
        int indice = obterIndiceVertice(vertice.getRotulo());
        visitado[indice] = true;
        ciclo.adicionar(vertice);

        ListaLigada<Aresta> adjacentes = vertice.getAdjacentes();
        for (int i = 0; i < adjacentes.tamanho(); i++) {
            Vertice v = adjacentes.get(i).getVerticeDestino();
            if (!visitado[obterIndiceVertice(v.getRotulo())]) {
                dfsCiclo(v, visitado, ciclo);
            }
        }
    }*/

    private void imprimirCiclo(ListaLigada<Vertice> ciclo) {
        for (int i = 0; i < ciclo.tamanho() - 1; i++) {
            System.out.println(ciclo.get(i).getRotulo() + " -> " + ciclo.get(i + 1).getRotulo());
        }
    }

    //TODO
    @Override
    public String toString(){
        StringBuilder resultado = new StringBuilder();
        resultado.append("Grafo {");
        for (int i = 0; i < vertices.tamanho(); i++) {
            var vertice = vertices.get(i);
            //resultado.append(vertice.getRotulo()).append(": ");
            var vizinhos = vertice.getAdjacentes();
            if (vizinhos.tamanho() == 0){
                resultado.append("(").append(vertice).append(")");
            }
            else
                resultado.append(vizinhos);
//            for (int j = 0; j < vertice.getAdjacentes().tamanho(); j++) {
//                resultado.append(vertice.getAdjacentes().get(j).getVerticeDestino().getRotulo()).append(" ");
//            }
            if(i != vertices.tamanho() - 1)
                resultado.append(" ");
        }
        return resultado.append("}").toString();
    }

    //TODO altamente necessário para BORUVKA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grafo grafo = (Grafo) o;
        boolean verticesIguais = grafo.vertices.pesquisar(vertices.get(0));
        for (int i = 1; i < vertices.tamanho(); i++) {
            verticesIguais &= grafo.vertices.pesquisar(vertices.get(i));
        }
        return direcionado == grafo.direcionado && verticesIguais;
    }
}
