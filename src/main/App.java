package main;

import java.util.ArrayList;
import java.util.List;
import model.Conexoes;
import model.Grafo;
import model.Poste;




public class App {
    public static void main(String[] args) throws Exception {

        Poste A = new Poste("A");
        Poste B = new Poste("B");
        Poste C = new Poste("C");
        Poste D = new Poste("D");
        Poste E = new Poste("E");
        Poste F = new Poste("F");

        Conexoes BC = new Conexoes(B, C, 8);
        Conexoes AD = new Conexoes(A, D, 9);
        Conexoes AB = new Conexoes(A, B, 10);
        Conexoes AC = new Conexoes(A, C, 9);
        Conexoes EF = new Conexoes(E, F, 8);

        List<Poste> listaVertices = new ArrayList<>();
        List<Conexoes> listaArestas = new ArrayList<>();

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

        Grafo matriz = new Grafo(listaArestas, listaVertices);

        System.out.println(BC.comprimento_fio);
        System.out.println(AB.comprimento_fio);
        System.out.println(AD.comprimento_fio);
        matriz.ImprimirMatriz();

    }
}
