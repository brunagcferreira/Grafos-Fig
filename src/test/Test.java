package test;
import algoritmo.BuscaEmLargura;
import model.Conexoes;
import model.Poste;
import model.RedeEletrica;
import org.junit.jupiter.api.BeforeEach;
import util.LeitorArquivo;

public class Test {

    private RedeEletrica rede;
    private BuscaEmLargura busca;

     @BeforeEach
    void setUp() throws Exception {
        String entrada = "src/resources/entrada_teste.txt";  // Arquivo de testes
        LeitorArquivo leitor = new LeitorArquivo(entrada);
        leitor.inicializarListaDeAdjacencia();

        rede = new RedeEletrica(leitor.getListaDeAjacencia(), leitor.getListaConexoes(), leitor.getListaPostes());
        busca = new BuscaEmLargura(rede);
        
        // Define um poste como conectado (simulando a provedora)
        Poste provedora = rede.encontrarPostePorId("0");
        provedora.setConectado(true);
    }


}
