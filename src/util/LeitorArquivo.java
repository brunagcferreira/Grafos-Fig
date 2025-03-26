package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.*;

import model.Poste;

public class LeitorArquivo {
    private String caminho;
    private int qntdArestas;
    private int qntdVertices;
    private Map<String, LinkedList<String>> listaVertices;
    private ArrayList<Poste> listaPostes;

    public LeitorArquivo(String caminho){
        this.caminho = caminho;
        this.listaVertices = new HashMap<>();
        this.listaPostes = new ArrayList<Poste>();
    }

    public void inicializarListaDeVertices(){
        try (BufferedReader br = new BufferedReader(new FileReader (caminho))){
            String linha = br.readLine();
            String[] verticeAresta = linha.split(" ");
            setQntdVertices(Integer.parseInt(verticeAresta[0]));
            setQntdArestas(Integer.parseInt(verticeAresta[1]));

            for(int i = 0; i < qntdVertices; i++){
                linha = br.readLine();
                listaVertices.put(linha, new LinkedList<>());
                Poste p = new Poste(String.valueOf(i), false);
                listaPostes.add(p);
            }

            for(int i = 0; i < qntdArestas; i++){
                linha = br.readLine();
                String origem = String.valueOf(linha.charAt(0));
                String destino = String.valueOf(linha.charAt(1));
                if(!listaVertices.get(origem).contains(destino)){
                    listaVertices.get(origem).add(destino);
                }

                if(!listaVertices.get(destino).contains(origem)){
                    listaVertices.get(destino).add(origem);
                }
            }
            
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo" + e.getMessage());
        }
    }

    public void exibirLista(){
        for(String i : listaVertices.keySet()){
            System.out.println(i + ": " + listaVertices.get(i));
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

    public Map<String, LinkedList<String>> getListaVertices() {
        return listaVertices;
    }

    public void setListaVertices(Map<String, LinkedList<String>> listaVertices) {
        this.listaVertices = listaVertices;
    }

    public List<Poste> getListaPostes() {
        return listaPostes;
    }

    public void setListaPostes(ArrayList<Poste> listaPostes) {
        this.listaPostes = listaPostes;
    }

}
