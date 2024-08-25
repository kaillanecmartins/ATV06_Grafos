package Questao5;

import Questao1.Aresta;
import Questao1.Grafo;
import Questao1.Vertice;
import Util.ListaLigada;

public class Prim {
    private static final ListaLigada<Aresta> resultado = new ListaLigada<>();
    private static final ListaLigada<Vertice> verticesVisitados = new ListaLigada<>();
    private static int pesoTotal = 0;
    private static final ListaLigada<Aresta> arestaCandidata = new ListaLigada<>();

    public static Result executar(Grafo grafo, String verticeInicial) {

        Vertice vertice = grafo.pesquisarVertice(verticeInicial);
        if (vertice == null) {
            throw new IllegalArgumentException("Vértice inicial não encontrado.");
        }

        while(true) {
            //Marca ele como visitado
            if(!verticesVisitados.pesquisar(vertice)){
                verticesVisitados.adicionar(vertice);
            }

            //Se já visitou tudo então terminou
            if (verticesVisitados.tamanho() == grafo.tamanhoVertice()){
                break;
            }

            var arestaMenor = arestaMenorPesoNaoVisitada(vertice);

            resultado.adicionar(arestaMenor);
            pesoTotal += arestaMenor.getPeso();
            arestaCandidata.remover(arestaMenor);

            //proximo da iteração
            vertice = arestaMenor.getVerticeDestino();
        }

            return new Result(resultado, pesoTotal);
    }



    private static Aresta arestaMenorPesoNaoVisitada(Vertice vertice){
        ListaLigada<Aresta> vizinhos = vertice.getAdjacentes();

        //adiciona a "Pilha" todas as arestas candidatas(com vertices não visitados)
        for (int i = 0; i < vizinhos.tamanho(); i++) {
            var atual = vizinhos.get(i);
            if (!(verticesVisitados.pesquisar(atual.getVerticeDestino()))){
                arestaCandidata.adicionar(atual);
            }
        }

        Aresta menor = arestaCandidata.get(0);
        for (int i = 1; i < arestaCandidata.tamanho(); i++) {
            var atual = arestaCandidata.get(i);

            var isMenor = atual.compareTo(menor) < 0;
            //Se a da proxima iteração for visitada não pode pois gera ciclo
            var isNotVisitada = !(verticesVisitados.pesquisar(atual.getVerticeDestino()));
            if(isMenor && isNotVisitada){
                menor = atual;
            }
        }

        return menor;
    }
}
