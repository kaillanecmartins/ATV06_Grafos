package Questao1;

public class MinHeap {
    private Aresta[] heap;
    private int tamanho;
    private int capacidade;

    public MinHeap(int capacidade) {
        this.capacidade = capacidade;
        this.heap = new Aresta[capacidade];
        this.tamanho = 0;
    }

    private int pai(int i) {
        return (i - 1) / 2;
    }

    private int esquerda(int i) {
        return 2 * i + 1;
    }

    private int direita(int i) {
        return 2 * i + 2;
    }

    public void inserir(Aresta aresta) {
        if (tamanho == capacidade) return;
        tamanho++;
        int i = tamanho - 1;
        heap[i] = aresta;

        while (i != 0 && heap[pai(i)].compareTo(heap[i]) > 0) {
            trocar(i, pai(i));
            i = pai(i);
        }
    }

    public Aresta extrairMin() {
        if (tamanho == 1) {
            tamanho--;
            return heap[0];
        }

        Aresta raiz = heap[0];
        heap[0] = heap[tamanho - 1];
        tamanho--;
        heapificar(0);

        return raiz;
    }

    private void heapificar(int i) {
        int menor = i;
        int esq = esquerda(i);
        int dir = direita(i);

        if (esq < tamanho && heap[esq].compareTo(heap[menor]) < 0) {
            menor = esq;
        }

        if (dir < tamanho && heap[dir].compareTo(heap[menor]) < 0) {
            menor = dir;
        }

        if (menor != i) {
            trocar(i, menor);
            heapificar(menor);
        }
    }

    private void trocar(int i, int j) {
        Aresta temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public boolean estaVazio() {
        return tamanho == 0;
    }
}
