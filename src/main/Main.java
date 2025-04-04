package main;

import java.util.List;
import java.util.Scanner;
import algoritmo.BuscaEmLargura;
import model.RedeEletrica;
import model.Poste;
import util.LeitorArquivo;

public class Main {
    public static void main(String[] args) throws Exception {
        String entrada = "src/resources/entrada.txt";
        LeitorArquivo leitor = new LeitorArquivo(entrada);
        leitor.inicializarListaDeAdjacencia();

        RedeEletrica rede = new RedeEletrica(leitor.getListaDeAjacencia(), leitor.getListaConexoes(), leitor.getListaPostes());

        //rede.exibirListaDePostes();

        Poste provedora = rede.encontrarPostePorId("28");
        provedora.setConectado(true);

        BuscaEmLargura buscaLargura = new BuscaEmLargura(rede);

        Scanner sc = new Scanner(System.in);
        int opt;
		do {
            System.out.println("-----------MENU-----------");
	        System.out.println("1. Conectar um poste");
            System.out.println("2. Exibir informações de um poste");
            System.out.println("3. Listar postes conectados");
            System.out.println("4. Exibir a lista de adjacência");
            System.out.println("5. Exibir informações do poste da provedora");
            System.out.println("99. Sair");
	        System.out.print("Escolha uma opção: ");
	        opt = sc.nextInt();
	        sc.nextLine();

	        switch (opt) {
	        case 1:
                System.out.print("Insira o id do poste que deseja conectar: ");
                String id = sc.next();
                Poste alvo = rede.encontrarPostePorId(id);
                if (alvo != null) {
                    List<Poste> caminho = buscaLargura.encontrarCaminho(alvo);
                    Poste posteCliPoste = buscaLargura.getPosteResponsavel();
                    System.out.println("Cliente conectado ao poste ID " + posteCliPoste.getId());
                    System.out.println("Casas atendidas pelo poste id "+ posteCliPoste.getId() + ": " + posteCliPoste.getCasasAtendidas() + "/" + Poste.getCapacidadeMax());
                    System.out.println("caminho: " + buscaLargura.caminhoToString(caminho));
                    System.out.println("Distancia percorrida: " + buscaLargura.calcularDistanciaTotal(caminho));
                    buscaLargura.conectarCaminho(caminho);
                } else {
                    System.out.println("Poste de id " + id + " não encontrado");
                }
                break;

            case 2:
                System.out.print("Insira o id do poste que deseja consultar: ");
                String idConsulta = sc.next();
                Poste posteConsulta = rede.encontrarPostePorId(idConsulta);
                if (posteConsulta != null) {
                    System.out.println("Informações do Poste " + idConsulta + ":");
                    System.out.println("Conectado à rede: " + (posteConsulta.isConectado() ? "Sim" : "Não"));
                    System.out.println("Casas atendidas: " + posteConsulta.getCasasAtendidas() + "/" + Poste.getCapacidadeMax());
                    System.out.print("Vizinhos: ");
                    List<String> vizinhos = rede.getListaDeAjacencia().get(posteConsulta.getId());
                    if (vizinhos != null && !vizinhos.isEmpty()) {
                        System.out.println(vizinhos);
                    } else {
                        System.out.println("Nenhum vizinho encontrado");
                    }
                } else {
                    System.out.println("Poste de id " + idConsulta + " não encontrado");
                }
                break;

            case 3:
                System.out.println("Postes conectados:");
                for (Poste p : rede.getListaPostes()) {
                    if (p.isConectado()) {
                        System.out.println("ID: " + p.getId() + " - Casas atendidas: " + p.getCasasAtendidas() + "/" + Poste.getCapacidadeMax());
                    }
                }
                break;
            case 4:
                    System.out.println();
                    System.out.println("Lista de adjacência:");
                    rede.exibirListaDeAjacencia();
                    System.out.println();
                break;

            case 5:
                System.out.println("Informações do Poste da provedora:");
                System.out.println("ID: " + provedora.getId());
                System.out.println("Conectado à rede: " + (provedora.isConectado() ? "Sim" : "Não"));
                System.out.println("Casas atendidas: " + provedora.getCasasAtendidas() + "/" + Poste.getCapacidadeMax());
                System.out.print("Vizinhos: ");
                List<String> vizinhos = rede.getListaDeAjacencia().get(provedora.getId());
                if (vizinhos != null && !vizinhos.isEmpty()) {
                    System.out.println(vizinhos);
                } else {
                    System.out.println("Nenhum vizinho encontrado");
                }
                break;
            
	        case 99:
	            break;
	        default:
	        	System.out.println("Opção invalida");
	        	break;
	        }
		}
		while (opt != 99);

	        sc.close();
    }
}

