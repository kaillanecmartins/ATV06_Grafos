package Util;

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

    public T get(int indice) {
        if (indice < 0 || indice >= tamanho) return null;

        No temp = cabeca;
        for (int i = 0; i < indice; i++) {
            temp = temp.proximo;
        }
        return temp.elemento;
    }

    public int tamanho() {
        return tamanho;
    }
}
