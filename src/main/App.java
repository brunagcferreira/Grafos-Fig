package main;

import java.util.ArrayList;
import java.util.List;
import model.Fio;
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

        Fio BC = new Fio(B, C, 8);
        Fio AD = new Fio(A, D, 9);
        Fio AB = new Fio(A, B, 10);
        Fio AC = new Fio(A, C, 9);
        Fio EF = new Fio(E, F, 8);

        List<Poste> listaVertices = new ArrayList<>();
        List<Fio> listaArestas = new ArrayList<>();

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
