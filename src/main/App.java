package main;

import model.Grafo;
import util.LeitorArquivo;

public class App {
    public static void main(String[] args) {
        String caminho = "./entradas/entrada.txt";
        LeitorArquivo leitor = new LeitorArquivo(caminho);
        leitor.inicializarListaDeAdjacencia();
        
        Grafo grafo = new Grafo(leitor.getListaDeAjacencia(), leitor.getListaConexoes(), leitor.getListaPostes());
        System.out.println("LISTA DE POSTES");
        grafo.exibirListaDePostes();

        System.out.println("LISTA DE ADJACÊCNIA");
        grafo.exibirListaDeAjacencia();

        System.out.println("LISTA DE CONEXÕES");
        grafo.exibirConexoes();

        System.out.println( "qntd de conexoes" + grafo.getListaConexoes().size());
    }
}
