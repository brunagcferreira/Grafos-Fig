package model;

import java.util.LinkedList;
import java.util.Map;

public class Grafo {
    private Map<String, LinkedList<String>> listaDeAjacencia;
    //private List<Conexoes> listaConexoes;
    //private Set<Poste> listaPostes; //Set não permite objetos repetidos na lista

    public Grafo(Map<String, LinkedList<String>> listaAdjacencia) { 
        //a lsita de conexões será lida do arquivo .txt ou .json pelo objeto da classe LeitorArquivo
        //que ja vai retornar formatado
        this.listaDeAjacencia = listaAdjacencia;
    }

    public Map<String, LinkedList<String>> getListaDeAjacencia() {
        return listaDeAjacencia;
    }

    public void setListaDeAjacencia(Map<String, LinkedList<String>> listaDeAjacencia) {
        this.listaDeAjacencia = listaDeAjacencia;
    }

}
