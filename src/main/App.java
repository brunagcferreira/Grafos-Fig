package main;

import java.util.List;

import algoritmo.Dijkstra;
import model.Grafo;
import model.Poste;
import util.LeitorArquivo;

public class App {
    public static void main(String[] args) {
        String entrada = "src/resources/entrada_atualizada.txt";
        LeitorArquivo leitor = new LeitorArquivo(entrada);
        leitor.inicializarListaDeAdjacencia();
        
        Grafo grafo = new Grafo(leitor.getListaDeAjacencia(), leitor.getListaConexoes(), leitor.getListaPostes());
        System.out.println("LISTA DE POSTES");
        grafo.exibirListaDePostes();

        System.out.println("LISTA DE ADJACÊCNIA");
        grafo.exibirListaDeAjacencia();

        System.out.println("LISTA DE CONEXÕES");
        grafo.exibirConexoes();

        System.out.println( "qntd de conexoes" + grafo.getListaConexoes().size());
        
        Dijkstra dijkstra = new Dijkstra(grafo);
        
        Poste provedora = grafo.encontrarPostePorId("0");
        if (provedora != null) {
            provedora.setConectado(true);
            System.out.println("Provedora configurada como Poste " + provedora.getId());
        }
        
        dijkstra.encontrarMenorCaminho(provedora);
        
        for (Poste poste : grafo.getListaPostes()) {
            if (!poste.equals(provedora)) {
                List<Poste> caminho = dijkstra.getCaminho(poste);
                double distancia = dijkstra.getDistancia(poste.getId());
                
                System.out.print("caminho para " + poste.getId() + ": ");
                for (Poste p : caminho) {
                    System.out.print(p.getId() + " ");
                }
                System.out.println("distancia: " + distancia + "");
            }
        }
    }
}
