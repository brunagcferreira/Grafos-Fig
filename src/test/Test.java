package test;
import algoritmo.BuscaEmLargura;
import model.Conexoes;
import model.Poste;
import model.RedeEletrica;
import util.LeitorArquivo;

public class Test {

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
