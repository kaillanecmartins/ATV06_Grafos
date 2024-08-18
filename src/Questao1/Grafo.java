package Questao1;

import Util.Lista;

public class Grafo {
    private Lista vertices;
    private Lista arestas;

    public Grafo() {
        vertices = new Lista();
        arestas = new Lista();
    }

    public void inserirVertice(String rotulo) {
        Vertice vertice = new Vertice(rotulo);
        vertices.adicionar(vertice);
    }

    public void removerVertice(String rotulo) {
        Vertice vertice = pesquisarVertice(rotulo);
        if (vertice != null) {
            Object[] todasArestas = arestas.obterTodos();
            for (Object obj : todasArestas) {
                Aresta aresta = (Aresta) obj;
                if (aresta.getOrigem().equals(vertice) || aresta.getDestino().equals(vertice)) {
                    removerAresta(aresta.getOrigem().getRotulo(), aresta.getDestino().getRotulo());
                }
            }
            vertices.remover(vertice);
        }
    }

    public Vertice pesquisarVertice(String rotulo) {
        Object[] todosVertices = vertices.obterTodos();
        for (Object obj : todosVertices) {
            Vertice vertice = (Vertice) obj;
            if (vertice.getRotulo().equals(rotulo)) {
                return vertice;
            }
        }
        return null;
    }

    public void inserirAresta(String origem, String destino, int peso) {
        Vertice verticeOrigem = pesquisarVertice(origem);
        Vertice verticeDestino = pesquisarVertice(destino);
        if (verticeOrigem != null && verticeDestino != null) {
            Aresta aresta = new Aresta(verticeOrigem, verticeDestino, peso);
            arestas.adicionar(aresta);
            verticeOrigem.incrementarGrau();
            verticeDestino.incrementarGrau();
        }
    }

    public void inserirAresta(String origem, String destino) {
        inserirAresta(origem, destino, 1);
    }

    public void removerAresta(String origem, String destino) {
        Vertice verticeOrigem = pesquisarVertice(origem);
        Vertice verticeDestino = pesquisarVertice(destino);
        if (verticeOrigem != null && verticeDestino != null) {
            Object[] todasArestas = arestas.obterTodos();
            for (Object obj : todasArestas) {
                Aresta aresta = (Aresta) obj;
                if (aresta.getOrigem().equals(verticeOrigem) && aresta.getDestino().equals(verticeDestino)) {
                    arestas.remover(aresta);
                    verticeOrigem.decrementarGrau();
                    verticeDestino.decrementarGrau();
                    return;
                }
            }
        }
    }

    public Aresta pesquisarAresta(String origem, String destino) {
        Vertice verticeOrigem = pesquisarVertice(origem);
        Vertice verticeDestino = pesquisarVertice(destino);
        if (verticeOrigem != null && verticeDestino != null) {
            Object[] todasArestas = arestas.obterTodos();
            for (Object obj : todasArestas) {
                Aresta aresta = (Aresta) obj;
                if (aresta.getOrigem().equals(verticeOrigem) && aresta.getDestino().equals(verticeDestino)) {
                    return aresta;
                }
            }
        }
        return null;
    }

    public Object[] obterAdjacentes(String rotulo) {
        Vertice vertice = pesquisarVertice(rotulo);
        if (vertice != null) {
            Lista adjacentes = new Lista();
            Object[] todasArestas = arestas.obterTodos();
            for (Object obj : todasArestas) {
                Aresta aresta = (Aresta) obj;
                if (aresta.getOrigem().equals(vertice)) {
                    adjacentes.adicionar(aresta.getDestino());
                } else if (aresta.getDestino().equals(vertice)) {
                    adjacentes.adicionar(aresta.getOrigem());
                }
            }
            return adjacentes.obterTodos();
        }
        return new Object[0];
    }

    public void imprimirGrafo() {
        Object[] todosVertices = vertices.obterTodos();
        for (Object obj : todosVertices) {
            Vertice vertice = (Vertice) obj;
            System.out.print("Vertice " + vertice.getRotulo() + " adjacente a: ");
            Object[] adjacentes = obterAdjacentes(vertice.getRotulo());
            for (Object adj : adjacentes) {
                Vertice adjVertice = (Vertice) adj;
                System.out.print(adjVertice.getRotulo() + " ");
            }
            System.out.println();
        }
    }
}
