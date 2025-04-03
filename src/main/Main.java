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
        
        System.out.println("LISTA DE POSTES POR ID");
        rede.exibirListaDePostes();

        System.out.println("LISTA DE ADJACÊCNIA");
        rede.exibirListaDeAjacencia();

        Poste provedora = rede.encontrarPostePorId("28");
        provedora.setConectado(true);

        BuscaEmLargura buscaLargura = new BuscaEmLargura(rede);

        Scanner sc = new Scanner(System.in);
        int opt;
		do {
	        System.out.println("1. Conectar um poste");
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
                    System.out.println("caminho: " + buscaLargura.caminhoToString(caminho));
                    System.out.println("Distancia até o poste mais próximo conectado a provedora: " + buscaLargura.calcularDistanciaTotal(caminho));
                    System.out.println("Quantidade de casas atendidas pelo poste id "+ alvo.getId() + ": " + alvo.getCasasAtendidas());
                    buscaLargura.conectarCaminho(caminho);
                } else {
                    System.out.println("Poste de id " + id + " nao encontrado");
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

