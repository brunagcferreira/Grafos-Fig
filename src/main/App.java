package main;

import java.util.ArrayList;
import java.util.List;
import model.Aresta;
import model.Grafo;
import model.Vertice;




public class App {
    public static void main(String[] args) throws Exception {

        Vertice A = new Vertice("A");
        Vertice B = new Vertice("B");
        Vertice C = new Vertice("C");
        Vertice D = new Vertice("D");
        Vertice E = new Vertice("E");
        Vertice F = new Vertice("F");

        Aresta BC = new Aresta(B, C);
        Aresta AD = new Aresta(A, D);
        Aresta AB = new Aresta(A, B);
        Aresta AC = new Aresta(A, C);
        Aresta EF = new Aresta(E, F);

        List<Vertice> listaVertices = new ArrayList<>();
        List<Aresta> listaArestas = new ArrayList<>();

        listaArestas.add(BC);
        listaArestas.add(AB);
        listaArestas.add(AC);
        listaArestas.add(AD);
        listaArestas.add(EF);

        listaVertices.add(A);
        listaVertices.add(B);
        listaVertices.add(C);
        listaVertices.add(D);
        listaVertices.add(E);
        listaVertices.add(F);

        Grafo matriz = new Grafo(listaVertices.size(), listaArestas.size(), listaArestas, listaVertices);

        matriz.ImprimirMatriz();

    }
}
