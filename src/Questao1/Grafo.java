package Questao1;

import Util.*;

public class Grafo{
    private ListaLigada<ListaLigada<Integer>> adjacencias;

    public Grafo() {
        adjacencias = new ListaLigada<>();
    }

    public void adicionarVertice() {
        ListaLigada<Integer> novaLista = new ListaLigada<>();
        adjacencias.inserirFim(novaLista);
    }

    public void adicionarAresta(int origem, int destino) {
        if (origem >= 0 && origem < adjacencias.getTamanho() && destino >= 0 && destino < adjacencias.getTamanho()) {
            ListaLigada<Integer> listaOrigem = (ListaLigada<Integer>) adjacencias.buscarPorIndex(origem);
            listaOrigem.inserirFim(destino);
        } else {
            System.out.println("Vértices inválidos");
        }
    }

    public void removerAresta(int origem, int destino) {
        if (origem >= 0 && origem < adjacencias.getTamanho() && destino >= 0 && destino < adjacencias.getTamanho()) {
            ListaLigada<Integer> listaOrigem = (ListaLigada<Integer>) adjacencias.buscarPorIndex(origem);
            Integer elementoRemovido = (Integer) listaOrigem.remover(destino);
            if (elementoRemovido == null) {
                System.out.println("Aresta não encontrada");
            }
        } else {
            System.out.println("Vértices inválidos");
        }
    }

    public ListaLigada<Integer> obterAdjacentes(int vertice) {
        if (vertice >= 0 && vertice < adjacencias.getTamanho()) {
            return (ListaLigada<Integer>) adjacencias.buscarPorIndex(vertice);
        } else {
            System.out.println("Vértice inválido");
            return null;
        }
    }

    public void imprimirGrafo() {
        for (int i = 0; i < adjacencias.getTamanho(); i++) {
            ListaLigada<Integer> lista = (ListaLigada<Integer>) adjacencias.buscarPorIndex(i);
            System.out.print(i + " -> ");
            if (lista != null) {
                for (int j = 0; j < lista.getTamanho(); j++) {
                    System.out.print(lista.buscarPorIndex(j) + " ");
                }
            }
            System.out.println();
        }
    }

}

