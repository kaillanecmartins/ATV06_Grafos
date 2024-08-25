package Questao5;

import Questao1.Aresta;
import Util.ListaLigada;

public record Result(ListaLigada<Aresta> arestas, int pesoTotal) {

    @Override
    public String toString(){
        return """
                Peso Total: %s
                Arestas: %s
                """.formatted(pesoTotal, arestas);
    }
}
