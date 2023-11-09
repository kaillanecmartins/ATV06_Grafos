package Questao1;

public class Aresta<TIPO> {
    private Vertice<TIPO> inicio;
    private Vertice<TIPO> fim;
    private boolean isDirecionada;
    private Double peso;

    public Aresta(Vertice<TIPO> inicio, Vertice<TIPO> fim, boolean isDirecionada, Double peso) {
        this.inicio = inicio;
        this.fim = fim;
        this.isDirecionada = isDirecionada;
        this.peso = peso;
    }

    public Aresta() {
    }

    
    
    public Aresta(Vertice<TIPO> inicio, Vertice<TIPO> fim, boolean isDirecionada) {
        this.inicio = inicio;
        this.fim = fim;
        this.isDirecionada = isDirecionada;
        this.peso = 0.0;
    }

    public Vertice<TIPO> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice<TIPO> inicio) {
        this.inicio = inicio;
    }

    public Vertice<TIPO> getFim() {
        return fim;
    }

    public void setFim(Vertice<TIPO> fim) {
        this.fim = fim;
    }

    public boolean isIsDirecionada() {
        return isDirecionada;
    }

    public void setIsDirecionada(boolean isDirecionada) {
        this.isDirecionada = isDirecionada;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Aresta{" + "inicio:" + inicio + ", fim:" + fim + ", isDirecionada:" + isDirecionada + ", peso:" + peso + '}';
    }
    
}
