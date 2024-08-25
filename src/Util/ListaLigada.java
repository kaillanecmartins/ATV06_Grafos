package Util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ListaLigada<T> {
    private class No {
        T elemento;
        No proximo;

        No(T elemento) {
            this.elemento = elemento;
            this.proximo = null;
        }
    }

    private No cabeca;
    private int tamanho;

    public ListaLigada() {
        this.cabeca = null;
        this.tamanho = 0;
    }


    public void adicionar(T elemento, int indice){
        No novoNo = new No(elemento);
        if (cabeca == null) {
            cabeca = novoNo;
        } else {
            if(indice == 0){
                novoNo.proximo = cabeca;
                cabeca = novoNo;
            }else{
                var no = getNo(indice - 1);
                novoNo.proximo = no.proximo;
                no.proximo = novoNo;
            }
        }
        tamanho++;
    }
    public void adicionar(T elemento) {
        No novoNo = new No(elemento);
        if (cabeca == null) {
            cabeca = novoNo;
        } else {
            No temp = cabeca;
            while (temp.proximo != null) {
                temp = temp.proximo;
            }
            temp.proximo = novoNo;
        }
        tamanho++;
    }

    public boolean remover(T elemento) {
        if (cabeca == null) return false;

        if (cabeca.elemento.equals(elemento)) {
            cabeca = cabeca.proximo;
            tamanho--;
            return true;
        }

        No temp = cabeca;
        while (temp.proximo != null && !temp.proximo.elemento.equals(elemento)) {
            temp = temp.proximo;
        }

        if (temp.proximo == null) return false;

        temp.proximo = temp.proximo.proximo;
        tamanho--;
        return true;
    }

    public boolean pesquisar(T elemento) {
        No temp = cabeca;
        while (temp != null) {
            if (temp.elemento.equals(elemento)) return true;
            temp = temp.proximo;
        }
        return false;
    }
    public int pesquisarIndice(T elemento){
        int index = 0;
        No temp = cabeca;
        while (temp != null) {
            if (temp.elemento.equals(elemento)) return index;
            index++;
            temp = temp.proximo;
        }
        return -1;
    }
    public T get(int indice) {
        return getNo(indice).elemento;
    }

    private No getNo(int indice){
        if (indice < 0 || indice >= tamanho) return null;

        No temp = cabeca;
        for (int i = 0; i < indice; i++) {
            temp = temp.proximo;
        }
        return temp;
    }
    public int tamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        var nomeClasse = cabeca.elemento.getClass().getSimpleName();
        if(tamanho != 0)
            return "List" + nomeClasse + "[" + toString(cabeca) + "]\n";
        else
            return "List" + nomeClasse + "[]";
    }

    private String toString(No no){
        if (no.proximo != null){
            return String.format("%s, %s", no.elemento, toString(no.proximo));
        }
        return String.format("%s", no.elemento);
    }
}
