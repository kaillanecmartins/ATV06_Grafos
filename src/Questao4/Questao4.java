package Questao4;

import Questao1.*;
import Util.ArquivoToGrafo;

public class Questao4 {
    public static void main(String[] args) {
        Grafo grafo = ArquivoToGrafo.get("src/Entradas/dados_q4.txt", false); // Grafo não direcionado


        // Verificar se o grafo é bipartido e imprimir as partições
        grafo.imprimirParticoes();
    }
}
