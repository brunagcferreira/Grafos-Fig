package model;

public class Fio {
    public Poste primeiro_vertice;
    public Poste segundo_vertice;
    public String aresta;
    public int comprimento_fio;

    public Fio(Poste A, Poste B) {
        this.primeiro_vertice = A;
        this.segundo_vertice = B;
        this.aresta = primeiro_vertice.vertice + segundo_vertice.vertice;
        primeiro_vertice.adicionar_adj(segundo_vertice);
        segundo_vertice.adicionar_adj(primeiro_vertice);
    }

}