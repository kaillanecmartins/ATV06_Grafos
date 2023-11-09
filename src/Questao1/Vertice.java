package Questao1;

import Util.*;

public class Vertice<TIPO>{
    private TIPO dado;
    private ListaLigada<Aresta<TIPO>> arestas;
    private int grau;

    public Vertice(TIPO dado) {
        this.dado = dado;
        this.arestas = new ListaLigada<>();
        this.grau = 0;
    }

    public Vertice() {
    }
    
    public TIPO getDado() {
        return dado;
    }

    public void setDado(TIPO dado) {
        this.dado = dado;
    }

    public ListaLigada<Aresta<TIPO>> getArestas() {
        return arestas;
    }

    public void setArestas(ListaLigada<Aresta<TIPO>> arestas) {
        this.arestas = arestas;
    }

    @Override
    public String toString() {
        return "Vertice{" + "dado:" + dado + '}';
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }
    
}
