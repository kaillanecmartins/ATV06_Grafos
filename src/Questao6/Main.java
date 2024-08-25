package Questao6;

import Util.ArquivoToGrafo;

public class Main {
    public static void main(String[] args) {

        var grafo = ArquivoToGrafo.get("src/Entradas/dados_q6.txt", false);

        System.out.println("---- QUESTÃO 6 ----");
        System.out.println( GeradoCiclo.execute(grafo));
    }
}
