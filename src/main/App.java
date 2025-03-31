package main;

import java.util.List;
import java.util.Scanner;
import algoritmo.Dijkstra;
import algoritmo.DijkstraBruna;
import model.Grafo;
import model.Poste;
import util.LeitorArquivo;

public class App {
    public static void main(String[] args) {
        String entrada = "src/resources/entrada.txt";
        LeitorArquivo leitor = new LeitorArquivo(entrada);
        leitor.inicializarListaDeAdjacencia();
        
        Grafo grafo = new Grafo(leitor.getListaDeAjacencia(), leitor.getListaConexoes(), leitor.getListaPostes());
        System.out.println("LISTA DE POSTES POR ID");
        grafo.exibirListaDePostes();

        System.out.println("LISTA DE ADJACÊCNIA");
        grafo.exibirListaDeAjacencia();

        //System.out.println("LISTA DE CONEXÕES");
        //grafo.exibirConexoes();

        //System.out.println( "qntd de conexoes" + grafo.getListaConexoes().size());
        //Dijkstra dijkstra = new Dijkstra(grafo);

        DijkstraBruna dk = new DijkstraBruna(grafo);
        
        Poste provedora = grafo.encontrarPostePorId("0");
        if (provedora != null) {
            provedora.setConectado(true);
            System.out.println("Provedora configurada como Poste " + provedora.getId());
        }

        int loop = 1;
        System.out.println("MENU");
        System.out.println("1.Verificar menor caminho");
        System.out.println("99. encerrar");
        while (loop == 1){
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite a opção desejada: ");
            int i = sc.nextInt();
            switch (i){
                case 1:
                    System.out.println("Insira o ID do poste de origem: ");
                    String id = sc.next();
                    Poste alvo = grafo.encontrarPostePorId(id);
                    dk.encontrarCaminho(alvo);
                    break;

                case 99:
                    loop = 0;
                    sc.close();
                    break;
                default:
                    System.out.println("Opção inválida :()");
                    break;

            }
        }


        /*dijkstra.encontrarMenorCaminho(provedora);
        
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
        }*/
    }
}
