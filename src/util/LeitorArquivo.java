package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import model.Conexoes;
import model.Poste;

public class LeitorArquivo {
    private String caminho;
    private int qntdArestas;
    private int qntdVertices;
    private Map<String, LinkedList<String>> listaDeAjacencia;
    private Map<String, Poste> listaPostes;
    private Set<Conexoes> listaConexoes;

    public LeitorArquivo(String caminho){
        this.caminho = caminho;
        this.listaDeAjacencia = new LinkedHashMap<String, LinkedList<String>>();
        this.listaPostes = new LinkedHashMap<String, Poste>();
        this.listaConexoes = new LinkedHashSet<>();
    }

    public void inicializarListaDeAdjacencia(){

        try (BufferedReader br = new BufferedReader(new FileReader (caminho))){
            String linha = br.readLine();
            String[] verticeAresta = linha.split(" ");
            setQntdVertices(Integer.parseInt(verticeAresta[0]));
            setQntdArestas(Integer.parseInt(verticeAresta[1]));

            for(int i = 0; i < qntdVertices; i++){
                
                Poste p = new Poste(false);
                listaPostes.put(p.getId(), p);

                listaDeAjacencia.put(p.getId(), new LinkedList<>());
            }

            for(int i = 0; i < qntdArestas; i++){
                linha = br.readLine();
                String[] origemDestinoPeso = linha.split(" ");
                String origem = origemDestinoPeso[0];
                String destino = origemDestinoPeso[1];
                Double distancia = Double.parseDouble(origemDestinoPeso[2]);

                Conexoes c = new Conexoes(listaPostes.get(origem) , listaPostes.get(destino), distancia);
                listaConexoes.add(c);
                
                if(!listaDeAjacencia.get(origem).contains(destino)){
                    listaDeAjacencia.get(origem).add(destino);
                }

                if(!listaDeAjacencia.get(destino).contains(origem)){
                    listaDeAjacencia.get(destino).add(origem);
                }
            }
            
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo" + e.getMessage());
        }
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public int getQntdArestas() {
        return qntdArestas;
    }

    public void setQntdArestas(int qntdArestas) {
        this.qntdArestas = qntdArestas;
    }

    public int getQntdVertices() {
        return qntdVertices;
    }

    public void setQntdVertices(int qntdVertices) {
        this.qntdVertices = qntdVertices;
    }

    public Map<String, LinkedList<String>> getListaDeAjacencia() {
        return listaDeAjacencia;
    }

    public void setListaDeAjacencia(Map<String, LinkedList<String>> listaVertices) {
        this.listaDeAjacencia = listaVertices;
    }

    public Set<Poste> getListaPostes() {
        Set<Poste> lista = new LinkedHashSet<>();
        for(String s : listaPostes.keySet()){
            Poste p = listaPostes.get(s);
            lista.add(p);
        }
        return lista;
    }

    public void setListaPostes(Map<String, Poste> listaPostes) {
        this.listaPostes = listaPostes;
    }

    public Set<Conexoes> getListaConexoes() {
        return listaConexoes;
    }

    public void setListaConexoes(Set<Conexoes> listaConexoes) {
        this.listaConexoes = listaConexoes;
    }

}
