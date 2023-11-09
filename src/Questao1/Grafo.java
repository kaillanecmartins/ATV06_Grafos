package Questao1;

import Util.*;

public class Grafo<TIPO>{
    private ListaLigada<Vertice<TIPO>> vertices;
    private ListaLigada<Aresta<TIPO>> arestas;
    private boolean isDirecionado;

    public Grafo(boolean isDirecionado) {
        this.vertices = new ListaLigada<>();
        this.arestas = new ListaLigada<>();
        this.isDirecionado = isDirecionado;
    }
    
    public Grafo(){
        
    }
    
    public void inserirVertice(Vertice<TIPO> vertice) {
        vertices.inserir(vertice);
    }

    public void removerVertice(Vertice<TIPO> vertice) {
        vertices.remover(vertice);
        // Também remova todas as arestas que têm este vértice como início ou fim
        Nodo<Aresta<TIPO>> nodoAresta = arestas.getInicio();
        while (nodoAresta != null) {
            Aresta<TIPO> aresta = nodoAresta.getInfo();
            if (aresta.getInicio().equals(vertice) || aresta.getFim().equals(vertice)) {
                arestas.remover(aresta);
            }
            nodoAresta = nodoAresta.getProximo();
        }
    }

    public void inserirAresta(Aresta<TIPO> aresta) {
        arestas.inserir(aresta);
    }

    public void removerAresta(Aresta<TIPO> aresta) {
        arestas.remover(aresta);
    }
    
    public void inserirArestaDirecionada(Vertice<TIPO> inicio, Vertice<TIPO> fim, Double peso) {
        Aresta<TIPO> arestaDirecionada = new Aresta<>(inicio, fim, true, peso);
        arestas.inserir(arestaDirecionada);
    }
    
    public Vertice<TIPO> pesquisarVertice(TIPO dado) {
        Nodo<Vertice<TIPO>> nodoVertice = vertices.getInicio();
        while (nodoVertice != null) {
            Vertice<TIPO> vertice = nodoVertice.getInfo();
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
            nodoVertice = nodoVertice.getProximo();
        }
        return null;
    }

    public Aresta<TIPO> pesquisarAresta(Vertice<TIPO> inicio, Vertice<TIPO> fim) {
        Nodo<Aresta<TIPO>> nodoAresta = arestas.getInicio();
        while (nodoAresta != null) {
            Aresta<TIPO> aresta = nodoAresta.getInfo();
            if (aresta.getInicio().equals(inicio) && aresta.getFim().equals(fim)) {
                return aresta;
            }
            nodoAresta = nodoAresta.getProximo();
        }
        return null;
    }
    
    public ListaLigada<Vertice<TIPO>> obterAdjacentes(Vertice<TIPO> vertice) {
        ListaLigada<Vertice<TIPO>> adjacentes = new ListaLigada<>();

        Nodo<Aresta<TIPO>> nodoAresta = arestas.getInicio();
        while (nodoAresta != null) {
            Aresta<TIPO> aresta = nodoAresta.getInfo();
            if (aresta.getInicio().equals(vertice)) {
                adjacentes.inserir(aresta.getFim());
            }
            nodoAresta = nodoAresta.getProximo();
        }
        return adjacentes;
    }

    public void imprimirAdjacentes() {
        Nodo<Vertice<TIPO>> nodoVertice = vertices.getInicio();
        while (nodoVertice != null) {
            Vertice<TIPO> vertice = nodoVertice.getInfo();
            ListaLigada<Vertice<TIPO>> adjacentes = obterAdjacentes(vertice);

            System.out.print("Vértice " + vertice.getDado() + " tem como adjacentes: ");
            Nodo<Vertice<TIPO>> nodoAdjacente = adjacentes.getInicio();
            while (nodoAdjacente != null) {
                System.out.print(nodoAdjacente.getInfo().getDado() + " ");
                nodoAdjacente = nodoAdjacente.getProximo();
            }
            System.out.println();

            nodoVertice = nodoVertice.getProximo();
        }
    }
    
    public boolean contemCiclo() {
        ListaLigada<Vertice<TIPO>> fila = new ListaLigada<>();

        // Inicializa a fila com vértices de grau 1
        Nodo<Vertice<TIPO>> nodoVertice = vertices.getInicio();
        while (nodoVertice != null) {
            if (nodoVertice.getInfo().getGrau() == 1) {
                fila.inserir(nodoVertice.getInfo());
            }
            nodoVertice = nodoVertice.getProximo();
        }

        while (fila.getTamanho() > 0) {
            Vertice<TIPO> verticeAtual = (Vertice<TIPO>) fila.retirarInicio();

            // Visita os vértices adjacentes
            ListaLigada<Vertice<TIPO>> adjacentes = obterAdjacentes(verticeAtual);
            Nodo<Vertice<TIPO>> nodoAdjacente = adjacentes.getInicio();
            while (nodoAdjacente != null) {
                Vertice<TIPO> adjacente = nodoAdjacente.getInfo();

                // Decrementa o grau do vértice adjacente
                adjacente.setGrau(adjacente.getGrau() - 1);

                // Se o grau do vértice adjacente se torna 1, insere na fila
                if (adjacente.getGrau() == 1) {
                    fila.inserir(adjacente);
                }

                nodoAdjacente = nodoAdjacente.getProximo();
            }
        }

        // Verifica se existem vértices não visitados (com grau diferente de 0)
        nodoVertice = vertices.getInicio();
        while (nodoVertice != null) {
            if (nodoVertice.getInfo().getGrau() > 0) {
                return true; // Existe ciclo
            }
            nodoVertice = nodoVertice.getProximo();
        }

        return false; // Não há ciclo
    }

}

