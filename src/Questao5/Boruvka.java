package Questao5;

import Questao1.Aresta;
import Questao1.Grafo;
import Util.ListaLigada;

public abstract class Boruvka {

    //Abordagem para conseguir retornar mais de um valor no método
    private record ArestaMenorPeso(Grafo grafoA, Aresta arestaQueLiga, Grafo grafoB){}

    private static ListaLigada<Grafo> grafos = new ListaLigada<>();
    private static Grafo grafoOriginal;

    private static int pesoTotal = 0;
    private static final ListaLigada<Aresta> resultado = new ListaLigada<>();
    public static Result execute(Grafo grafo){
        grafoOriginal = grafo;

        //Cada vertice vira um subgrafo separado
        var vertices = grafoOriginal.getAllVertices();
        for (int i = 0; i < vertices.tamanho(); i++) {
            Grafo subGrafo = new Grafo(false);
            var item = vertices.get(i);
            subGrafo.inserirVertice(item);
            grafos.adicionar(subGrafo);
        }

        //Aos poucos as vertice vão se juntando até sobrar 1
        while (grafos.tamanho() != 1){
            for (int i = 0; i < grafos.tamanho(); i++) {

                //Do subGrafo atual, eu vou procurar dentre os outros
                // aquele que tiver o menor peso e vou retornar o ArestaMenorPeso
                var menorResultado = menorPesoEntreSubGrafos(grafos.get(i));


                //Em seguinte, apenas junta o grafo atual
                // com aquele encontrado de menor peso e remove o mesmo
                var copyGrafoA = menorResultado.grafoA;
                var copyGrafoB = menorResultado.grafoB;
                adicionar(copyGrafoA, menorResultado.arestaQueLiga);

                var indexAntes = grafos.pesquisarIndice(copyGrafoA);
                grafos.remover(copyGrafoB);
                var indexDepois = grafos.pesquisarIndice(copyGrafoA);
                //Caso o grafo encontrado esteja "atras" do atual,
                // o grafo da proxima iteração vai ser pulado.
                // Ex: se o atual for 2 e o encontrado 0.
                // O atual vira 1 pois o encontrado é removido,
                // mas i continua sendo 2 e o proximo 3
                if (indexAntes > indexDepois){
                    i--;
                }
                unirGrafos(copyGrafoA, copyGrafoB);
                if(!grafos.pesquisar(copyGrafoA))
                    grafos.adicionar(copyGrafoA, 0);
                resultado.adicionar(menorResultado.arestaQueLiga);
                pesoTotal += menorResultado.arestaQueLiga.getPeso();
            }
        }
        return new Result(resultado, pesoTotal);
    }

    //Fiz apenas pelos try catch
    private static void adicionar(Grafo grafo, Aresta aresta){
        var destino = aresta.getVerticeDestino().getRotulo();
        var origem = aresta.getVerticeOrigem().getRotulo();
        try {
            grafo.inserirVertice(destino);
        } catch (Exception ignored) {
        }
        try {
            grafo.inserirVertice(origem);
        } catch (Exception ignored) {
        }
        try {
            grafo.inserirAresta(origem, destino, aresta.getPeso());

        } catch (Exception ignored) {
        }
    }
    private static void unirGrafos(Grafo A, Grafo B){
        var verticesB = B.getAllVertices();
        for (int j = 0; j < verticesB.tamanho(); j++) {
            var vizinhosB = B.pesquisarVertice(verticesB.get(j)).getAdjacentes();
            for (int k = 0; k < vizinhosB.tamanho(); k++) {
                var verticeAtual = vizinhosB.get(k);
                var origB = verticeAtual.getVerticeOrigem().getRotulo();
                var destB = verticeAtual.getVerticeDestino().getRotulo();
                var pesoB = verticeAtual.getPeso();
                try {
                    A.inserirVertice(origB);
                }catch (Exception ignored){}
                try {
                    A.inserirVertice(destB);
                }catch (Exception ignored){}
                try {
                    A.inserirAresta(origB, destB, pesoB);
                }catch (Exception ignored){}
            }

        }
    }

    //Para encontrar o resultado, as arestas de cada vertice do grafoA são
    private static ArestaMenorPeso menorPesoEntreSubGrafos(Grafo grafoA){
        Aresta menorAresta = null;
        Grafo grafoDeMenorAresta = null;

        var vertices = grafoA.getAllVertices();

        //Primeiro pega as arestas de cada vertice(o vertice é de grafoA, mas arestas são do grafoOrignal)
        for (int i = 0; i < vertices.tamanho(); i++) {
            var verticeAtual = grafoOriginal.pesquisarVertice(vertices.get(i));
            var vizinhos = verticeAtual.getAdjacentes();

            for (int j = 0; j < vizinhos.tamanho(); j++) {

                var arestaCandidata = vizinhos.get(j);
                var origem = arestaCandidata.getVerticeOrigem().getRotulo();
                var destino = arestaCandidata.getVerticeDestino().getRotulo();

                //Ou seja esse vertice já está nesse SubGrafo
                if(grafoA.pesquisarVertice(destino) != null){
                    continue;
                }

                //Segundo: procura nos outros subgrafos se
                // aquela aresta é a de menor peso
                for (int k = 0; k < grafos.tamanho(); k++) {

                    var outroGrafo = grafos.get(k);

                    if(grafoA != outroGrafo){
                        //Se o subgrafo atual k possui essa vertice para poder construir a aresta
                        var outroSubGrafoPossuiVertice = outroGrafo.pesquisarVertice(destino);

                        if(outroSubGrafoPossuiVertice != null){
                            //Nesse caso é a primeira vez que o valor é atribuido
                            if(menorAresta == null){
                                menorAresta = arestaCandidata;
                                grafoDeMenorAresta = outroGrafo;
                            }else if(arestaCandidata.getPeso() < menorAresta.getPeso()){
                                menorAresta = arestaCandidata;
                                grafoDeMenorAresta = outroGrafo;
                            }
                        }
                    }

                }
            }
        }
        return new ArestaMenorPeso(grafoA, menorAresta, grafoDeMenorAresta);
    }

}
