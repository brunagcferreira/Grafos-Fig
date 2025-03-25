package model;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    public String valor;
    public List<Vertice> Adjacentes;

    public Vertice(String V) {
        this.valor = V;
        this.Adjacentes = new ArrayList<>();
    }

    public void adicionar_adj(Vertice novo) {
        Adjacentes.add(novo);
    }

}
