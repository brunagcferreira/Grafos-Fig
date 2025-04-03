package test;

import algoritmo.BuscaEmLargura;
import model.Conexoes;
import model.Poste;
import model.RedeEletrica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.LeitorArquivo;

import java.util.*;

public class Teste {

    private RedeEletrica rede;
    private BuscaEmLargura busca;

    // Estruturas para simular os dados que seriam lidos do arquivo:
    private ArrayList<Poste> listaPostes = new ArrayList<>();       // Lista de postes (vértices do grafo)
    private ArrayList<Conexoes> listaConexoes = new ArrayList<>();  // Lista de conexões (arestas do grafo)
    private Map<String, LinkedList<String>> listaDeAjacencia = new LinkedHashMap<>();  // Lista de adjacência (grafo)

    // Teste 1: Verifica se um poste é criado e adicionado corretamente às estruturas.
    @Test
    public void criar_poste() throws Exception {
        Poste novo_poste = new Poste(false);  // Cria um novo poste (não conectado à rede)
        listaPostes.add(novo_poste);          // Adiciona à lista de postes
        listaDeAjacencia.put(novo_poste.getId(), null);  // Adiciona à lista de adjacência (sem vizinhos)
        assert listaPostes.contains(novo_poste);  // Verifica se o poste foi adicionado
    }

    // Teste 2: Verifica se uma conexão entre postes é criada e armazenada corretamente.
    @Test
    public void criar_conexao() throws Exception {
        Poste poste_destino = new Poste(false);  // Cria poste de destino
        Poste poste_origem = new Poste(false);   // Cria poste de origem
        listaPostes.add(poste_destino);          // Adiciona ambos à lista de postes
        listaPostes.add(poste_origem);

        // Simula a adjacência: poste_destino está conectado ao poste_origem
        LinkedList<String> poste_destino_adj = new LinkedList<>();
        poste_destino_adj.add(poste_origem.getId());
        listaDeAjacencia.put(poste_destino.getId(), poste_destino_adj);

        // Cria uma conexão física entre os postes (com distância 8.0)
        Conexoes nova_conexao = new Conexoes(poste_origem, poste_destino, 8.0);
        listaConexoes.add(nova_conexao);

        assert listaConexoes.contains(nova_conexao);  // Verifica se a conexão foi criada
    }

    // Teste 3: Verifica se a rede elétrica é criada corretamente com as estruturas de dados.
    @Test
    public void criar_rede() throws Exception {
        // Cria uma rede com as listas de postes, conexões e adjacência
        RedeEletrica rede_teste = new RedeEletrica(listaDeAjacencia, listaConexoes, listaPostes);

        // Valida se a rede contém os mesmos postes e conexões passados no construtor
        assert rede_teste.getListaPostes().equals(listaPostes) &&
                rede_teste.getListaConexoes().equals(listaConexoes);
    }

    // Teste 4: Simula a conexão de um poste à rede e verifica se casas são atendidas.
    @Test
    public void conectar_poste() throws Exception {
        // Cria uma rede vazia (ou com dados pré-existentes)
        RedeEletrica rede = new RedeEletrica(listaDeAjacencia, listaConexoes, listaPostes);

        // Cria um poste "provedor" (fonte de conexão)
        Poste poste_provedor = new Poste(false);
        listaPostes.add(poste_provedor);

        // Conecta o provedor ao primeiro poste da lista
        LinkedList<String> poste_provedor_adj = new LinkedList<>();
        poste_provedor_adj.add(listaPostes.get(0).getId());  // Adiciona adjacência

        Conexoes nova_conexao = new Conexoes(poste_provedor, listaPostes.get(0), 8.0);
        listaConexoes.add(nova_conexao);
        listaDeAjacencia.put(poste_provedor.getId(), poste_provedor_adj);

        // Configura o poste como conectado (simulando o provedor de internet)
        poste_provedor = rede.encontrarPostePorId("1");  // Busca pelo ID (ajuste conforme necessário)
        poste_provedor.setConectado(true);

        // Executa a busca em largura para conectar o caminho
        BuscaEmLargura buscaLargura = new BuscaEmLargura(rede);
        List<Poste> caminho = buscaLargura.encontrarCaminho(rede.getListaPostes().getFirst());
        buscaLargura.conectarCaminho(caminho);

        // Valida se o primeiro poste da rede atende casas (após conexão)
        assert rede.getListaPostes().getFirst().getCasasAtendidas() > 0;
    }
}