package Questao6;

import Questao1.Aresta;
import Questao1.Grafo;
import Questao5.Prim;
import Util.ListaLigada;

/*
preferivel todos os vertices terem grau PAR
PASSO 1: remover as arestas de maiores pesos
de cada vertice SEGUINDO as restrções
            -OS verticeS da aresta precisa de GRAU > 2
            -NÃO pode remover aresta que deixa o grafo DESCONEXO(ps:Grau=1)
deve-se remover até o vertice ficar com grau=2
ou não ser mais po não ser mais(o que vier primeiro)

PASSO 2: aplica prim ou k ou B
e depois cria uma aresta entre o ultimo vertice no N-primeiro vertice
 que for possivel criar a aresta, baseado no grafo Original



 */
public class GeradoCiclo {
    private static Grafo grafoResultado = new Grafo(false);
    static public ListaLigada<Aresta> execute(Grafo grafo){
        copia(grafo);
        //isOmelhorCiclo();
        limparGrafo();

        var primResult = Prim.executar(grafoResultado, "A");

        //Procurando uma aresta que conecte o Ultimo ao N-primeiro
        //N-primeiro pois o prim retorna a aresta na ordem em que foi adicionada e pode ser que o primeiro de verdade não possua aresta no grafo orignal com o ultimo vertice
        var indexFinal = primResult.arestas().tamanho()-1;
        var verticeFinal = primResult.arestas().get(indexFinal).getVerticeDestino().getRotulo();
        for (int i = 0; i < primResult.arestas().tamanho(); i++) {
            var verticeOrigem = primResult.arestas().get(i).getVerticeOrigem().getRotulo();
            var verticeDestino = primResult.arestas().get(i).getVerticeDestino().getRotulo();

            var arestaExisteA = grafoResultado.pesquisarAresta(verticeOrigem,verticeFinal);
            var arestaExisteB = grafoResultado.pesquisarAresta(verticeDestino,verticeFinal);
            var arestaExisteNoPrimA = primResult.arestas().pesquisar(arestaExisteA);
            var arestaExisteNoPrimB = primResult.arestas().pesquisar(arestaExisteB);

            if (arestaExisteA != null && !arestaExisteNoPrimA){
                primResult.arestas().adicionar(arestaExisteA);
                break;
            }
            if (arestaExisteB != null && !arestaExisteNoPrimB){
                primResult.arestas().adicionar(arestaExisteB);
                break;
            }
        }
        return  primResult.arestas();
    }

    //Aparentemente se o grafo tiver vertice de grau=1
    // ou simplesmente grau impar, existem "chances" dele ficar "feio"(não vai ser um circulo)
    static public void isOmelhorCiclo(){
        var vertices = grafoResultado.getAllVertices();

        var isTodosGrauPar = grafoResultado.pesquisarVertice(vertices.get(0)).getGrau() % 2 == 0;
        for (int i = 1; i < vertices.tamanho(); i++) {
            var vertAtual = grafoResultado.pesquisarVertice(vertices.get(i));
            isTodosGrauPar &= vertAtual.getGrau() % 2 == 0;
        }
        if (!isTodosGrauPar)
            System.err.println("Não será o melhor ciclo");
    }
    static private void copia(Grafo grafo){
        var vertices = grafo.getAllVertices();

        for (int i = 0; i < vertices.tamanho(); i++) {
            var vert = vertices.get(i);
            var vizinhos = grafo.pesquisarVertice(vert).getAdjacentes();

            try {
                grafoResultado.inserirVertice(vert);
            }catch (Exception ignored){}
            for (int j = 0; j < vizinhos.tamanho(); j++) {
                var aresta = vizinhos.get(j);
                var outrovert = aresta.getVerticeDestino();
                try {
                    grafoResultado.inserirVertice(outrovert.getRotulo());
                }catch (Exception ignored){}
                try {
                    grafoResultado.inserirAresta(vert, outrovert.getRotulo(), aresta.getPeso());
                }catch (Exception ignored){}

            }
        }
        ordenarArestaDecrescente();
    }
    static private void limparGrafo(){
        var vertices = grafoResultado.getAllVertices();

        for (int i = 0; i < vertices.tamanho(); i++) {
            var vertAtual = grafoResultado.pesquisarVertice(vertices.get(i));
            var vizinhos = vertAtual.getAdjacentes();

            if (vertAtual.getGrau() <= 2) {
                continue;
            }
            var index = 0;
            while (vizinhos.tamanho() > 2 && index < vizinhos.tamanho()-1){
                var aresta = vizinhos.get(index);
                if (aresta.getVerticeDestino().getGrau() > 2){
                    grafoResultado.removerAresta(aresta.getVerticeOrigem().getRotulo(), aresta.getVerticeDestino().getRotulo());
                }else {
                    index++;
                }
            }
        }
    }

    static private void ordenarArestaDecrescente(){
        var vertices = grafoResultado.getAllVertices();

        for (int i = 0; i < vertices.tamanho(); i++) {
            var vertAtual = grafoResultado.pesquisarVertice(vertices.get(i));
            var vizinhos = vertAtual.getAdjacentes();

            for (int j = 0; j < vizinhos.tamanho(); j++) {
                var arestaA = vizinhos.get(j);
                for (int k = j+1; k < vizinhos.tamanho(); k++) {
                    var arestaB = vizinhos.get(k);

                    if(arestaA.getPeso() < arestaB.getPeso()){
                        vizinhos.remover(arestaA);
                        vizinhos.remover(arestaB);

                        vizinhos.adicionar(arestaB, j);
                        vizinhos.adicionar(arestaA, k);
                    }
                }
            }
        }
    }
}
