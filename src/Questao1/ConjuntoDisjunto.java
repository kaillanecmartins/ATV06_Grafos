package Questao1;

public class ConjuntoDisjunto {
    private int[] pai;
    private int[] rank;

    public ConjuntoDisjunto(int tamanho) {
        pai = new int[tamanho];
        rank = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            pai[i] = i;
            rank[i] = 0;
        }
    }

    public int encontrar(int i) {
        if (pai[i] != i) {
            pai[i] = encontrar(pai[i]);
        }
        return pai[i];
    }

    public void unir(int x, int y) {
        int raizX = encontrar(x);
        int raizY = encontrar(y);

        if (raizX != raizY) {
            if (rank[raizX] > rank[raizY]) {
                pai[raizY] = raizX;
            } else if (rank[raizX] < rank[raizY]) {
                pai[raizX] = raizY;
            } else {
                pai[raizY] = raizX;
                rank[raizX]++;
            }
        }
    }
}
