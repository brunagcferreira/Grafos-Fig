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

    private ArrayList<Poste> listaPostes = new ArrayList<>();
    private ArrayList<Conexoes> listaConexoes = new ArrayList<>();
    private Map<String, LinkedList<String>> listaDeAjacencia = new LinkedHashMap<String, LinkedList<String>>();

    @Test
    public void criar_poste() throws Exception {
        Poste novo_poste = new Poste(false);
        listaPostes.add(novo_poste);
        listaDeAjacencia.put(novo_poste.getId(), null);
        assert listaPostes.contains(novo_poste);
    }

    @Test
    public void criar_conexao() throws Exception {
        Poste poste_destino = new Poste(false);
        Poste poste_origem = new Poste(false);
        listaPostes.add(poste_destino);
        listaPostes.add(poste_origem);

        LinkedList<String> poste_destino_adj = new LinkedList<>();
        poste_destino_adj.add(poste_origem.getId());

        listaDeAjacencia.put(poste_destino.getId(), poste_destino_adj);

        Conexoes nova_conexao = new Conexoes(poste_origem,poste_destino,8.0);
        listaConexoes.add(nova_conexao);

        assert listaConexoes.contains(nova_conexao);
    }

    @Test
    public void criar_rede() throws Exception {
        RedeEletrica rede_teste = new RedeEletrica(listaDeAjacencia,listaConexoes,listaPostes);
        assert  rede_teste.getListaPostes().equals(listaPostes) &&
                rede_teste.getListaConexoes().equals(listaConexoes) ;
    }

    @BeforeEach
    void setUp() {
/*
        rede = new RedeEletrica(listaDeAjacencia, listaConexoes, listaPostes);
        busca = new BuscaEmLargura(rede);


*/
    }

    @Test
    public void conectar_poste() throws Exception {
        RedeEletrica rede = new RedeEletrica(listaDeAjacencia,listaConexoes,listaPostes);

        // Define um poste como conectado (simulando a provedora)
        Poste provedora = rede.encontrarPostePorId("1");
        provedora.setConectado(true);

        BuscaEmLargura buscaLargura = new BuscaEmLargura(rede);

        List<Poste> caminho = buscaLargura.encontrarCaminho(rede.getListaPostes().getFirst());
        buscaLargura.conectarCaminho(caminho);
        assert rede.getListaPostes().getFirst().getCasasAtendidas() > 0;
    }


}
