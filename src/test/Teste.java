package test;
import algoritmo.BuscaEmLargura;
import model.Conexoes;
import model.Poste;
import model.RedeEletrica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.LeitorArquivo;

import java.util.LinkedList;
import java.util.List;

public class Teste {

    private RedeEletrica rede;
    private BuscaEmLargura busca;

    @BeforeEach
    void setUp() {

        String entrada = "src/resources/entrada_teste.txt";  // Arquivo de testes
        LeitorArquivo leitor = new LeitorArquivo(entrada);
        leitor.inicializarListaDeAdjacencia();

        rede = new RedeEletrica(leitor.getListaDeAjacencia(), leitor.getListaConexoes(), leitor.getListaPostes());
        busca = new BuscaEmLargura(rede);

        // Define um poste como conectado (simulando a provedora)
        Poste provedora = rede.encontrarPostePorId("2");
        provedora.setConectado(true);
    }
    @Test
    public void conectar_poste() throws Exception {

        BuscaEmLargura buscaLargura = new BuscaEmLargura(rede);
        List<Poste> caminho = buscaLargura.encontrarCaminho(rede.getListaPostes().getFirst());
        buscaLargura.conectarCaminho(caminho);
        assert rede.getListaPostes().getFirst().getCasasAtendidas() > 0;
    }


}
