package model;

import java.util.List;

public class Grafo {

    public int[][] matriz;
    public List<Fio> arestas;
    public List<Poste> vertices;

    public Grafo(List<Fio> listaArestas, List<Poste> Listavertices) {
        this.matriz = new int[Listavertices.size()][Listavertices.size()];
        this.arestas = listaArestas;
        this.vertices = Listavertices;
        this.fazerMatriz();
    }

    private void fazerMatriz() {
        int linha = 0;
        int coluna = 0;

        for (Poste vertice_linha : vertices) {
            for (Poste vertice_coluna : vertices) {
                if (!vertice_linha.Adjacentes.contains(vertice_coluna) && !vertice_coluna.equals(vertice_linha) ) {            
                    matriz[linha][coluna] = 0;
                } else {
                    matriz[linha][coluna] = 1;
                }
                coluna++;
            }
            linha++;
            coluna = 0;
        }
    }

    public void ImprimirMatriz() {
        for (int linha = 0; linha < vertices.size(); linha++) {
            for (int coluna = 0; coluna < vertices.size(); coluna++) {
                System.out.printf("[%d]", matriz[linha][coluna]);
            }
            System.out.println();
        }
    }

}
