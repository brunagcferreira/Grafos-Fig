package model;

import java.util.ArrayList;
import java.util.List;

public class Poste {
    public String vertice;
    public List<Poste> Adjacentes;

    public Poste(String V) {
        this.vertice = V;
        this.Adjacentes = new ArrayList<>();
    }

    public void adicionar_adj(Poste novo) {
        Adjacentes.add(novo);
    }

}
