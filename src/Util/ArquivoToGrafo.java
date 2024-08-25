package Util;

import Questao1.Grafo;

import java.util.StringTokenizer;

//TODO
public abstract class ArquivoToGrafo {

    public static Grafo get(String path, boolean direcionado){
        var arq = new LeituraArquivo(path);

        Grafo grafo = new Grafo(direcionado);
        for(Object item : arq.lerTudo()){
            var separador = new StringTokenizer(item.toString(), ";");

            var verticeA = (String) separador.nextToken();
            var verticeB = (String) separador.nextToken();
            int peso = 0;
            if(separador.hasMoreElements()){
                peso = Integer.parseInt(separador.nextToken());
            }

            try {
                grafo.inserirVertice(verticeA);
            } catch (Exception ignored){}
            try {
                grafo.inserirVertice(verticeB);
            } catch (Exception ignored){}
            try {

                grafo.inserirAresta(verticeA, verticeB, peso);
            } catch (Exception ignored){}
        }

        return grafo;
    }
}
