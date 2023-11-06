package Util;

public class Nodo<TIPO>{
    private TIPO info;
    private Nodo proximo;
    private Nodo anterior;

    public Nodo(TIPO info){
        this.info = info;
        this.proximo = null;
        this.anterior = null;
    }
    public Nodo(){
        
    }
    public void setInfo(TIPO info){
        this.info = info;
    }
    public void setProximo(Nodo proximo){
        this.proximo = proximo;
    }
    public void setAnterior(Nodo anterior){
        this.anterior = anterior;
    }

    public TIPO getInfo(){
        return info;
    }
    public Nodo getProximo(){
        return proximo;
    }
    public Nodo getAnterior(){
        return anterior;
    }
}
