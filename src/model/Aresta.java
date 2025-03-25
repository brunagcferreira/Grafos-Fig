package model;

public class Aresta {
    public Vertice primeiro_vertice;
    public Vertice segundo_vertice;
    public String aresta;

    public Aresta(Vertice A, Vertice B) {
        this.primeiro_vertice = A;
        this.segundo_vertice = B;
        this.aresta = primeiro_vertice.valor + segundo_vertice.valor;
        primeiro_vertice.adicionar_adj(segundo_vertice);
        segundo_vertice.adicionar_adj(primeiro_vertice);
    }

}