package Questao5;

import Questao1.Aresta;
import Questao1.Grafo;
import Util.ListaLigada;

public class Kruskal {

    private static final ListaLigada<Aresta> arestaOrdenada = new ListaLigada<>();

    public static Result execute(Grafo grafo){
        //Ordena as aresta pelo peso em ordem crescente
        ordenar(grafo);

        var pesoTotal = 0;
        var resultado = new ListaLigada<Aresta>();
        var grafoParaCiclo = new Grafo(false);

        for (int i = 0; i < arestaOrdenada.tamanho(); i++) {
            var arestaAtual = arestaOrdenada.get(i);
            var vertA = arestaAtual.getVerticeOrigem().getRotulo();
            var vertB = arestaAtual.getVerticeDestino().getRotulo();
            var peso = arestaAtual.getPeso();

            //Vai adicionando tudo
            try {
                grafoParaCiclo.inserirVertice(vertA);
            } catch (Exception ignored) {}

            try {
                grafoParaCiclo.inserirVertice(vertB);
            } catch (Exception ignored) {}

            try {
                grafoParaCiclo.inserirAresta(vertA,vertB, peso);
                resultado.adicionar(arestaAtual);
                pesoTotal += arestaAtual.getPeso();

                //Se gerar um ciclo, remove a aresta
                if(grafoParaCiclo.verificarCiclo()){
                    grafoParaCiclo.removerAresta(vertA,vertB);
                    resultado.remover(arestaAtual);
                    pesoTotal -= arestaAtual.getPeso();
                }
            } catch (Exception ignored) {}
        }
        return new Result(resultado, pesoTotal);
    }

    private static void ordenar(Grafo grafo){
        var vertices = grafo.verificarVerticesRaiz();
        for (int i = 0; i < vertices.tamanho(); i++) {
            var vizinhos = vertices.get(i).getAdjacentes();
            for (int j = 0; j < vizinhos.tamanho(); j++) {
                var arestaAtual = vizinhos.get(j);
                var arestaInver = new Aresta(arestaAtual.getVerticeDestino(),arestaAtual.getVerticeOrigem(), arestaAtual.getPeso());

                if(arestaOrdenada.tamanho() == 0){
                    arestaOrdenada.adicionar(arestaAtual);
                }
                else if(!arestaOrdenada.pesquisar(arestaAtual) && !arestaOrdenada.pesquisar(arestaInver))
                {
                    for (int k = 0; k < arestaOrdenada.tamanho(); k++) {
                        var arestaOrdenadaAtual = arestaOrdenada.get(k);

                        if(arestaAtual.compareTo(arestaOrdenadaAtual) < 0){
                            arestaOrdenada.adicionar(arestaAtual, k);
                            break;
                        }
                    }
                    if(!arestaOrdenada.pesquisar(arestaAtual)){
                        arestaOrdenada.adicionar(arestaAtual);
                    }
                }
            }
        }
    }
}
