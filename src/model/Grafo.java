package model;

import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;

public class Grafo {
    private Map<String, LinkedList<String>> listaDeAjacencia;
    private ArrayList<Conexoes> listaConexoes;
    private ArrayList<Poste> listaPostes;

    public Grafo(Map<String, LinkedList<String>> listaDeAjacencia, ArrayList<Conexoes> listaConexoes,
            ArrayList<Poste> listaPostes) {
        this.listaDeAjacencia = listaDeAjacencia;
        this.listaConexoes = listaConexoes;
        this.listaPostes = listaPostes;
    }

    public void exibirListaDeAjacencia(){
        for(String i : listaDeAjacencia.keySet()){
            System.out.println(i + ": " + listaDeAjacencia.get(i));
        }
    }

    public void exibirListaDePostes(){
        for (Poste p : listaPostes){
            System.out.println("Poste ID: " + p.getId());
        }
    }

    public void exibirConexoes(){
        for (Conexoes c : listaConexoes){
            System.out.println("Poste " + c.getOrigem().getId() + " conectado ao poste " + c.getDestino().getId());
            System.out.println("Poste " + c.getDestino().getId() + " conectado ao poste " + c.getOrigem().getId());
        }
    }

    public Map<String, LinkedList<String>> getListaDeAjacencia() {
        return listaDeAjacencia;
    }

    public void setListaDeAjacencia(Map<String, LinkedList<String>> listaDeAjacencia) {
        this.listaDeAjacencia = listaDeAjacencia;
    }

    public ArrayList<Conexoes> getListaConexoes() {
        return listaConexoes;
    }

    public void setListaConexoes(ArrayList<Conexoes> listaConexoes) {
        this.listaConexoes = listaConexoes;
    }

    public ArrayList<Poste> getListaPostes() {
        return listaPostes;
    }

    public void setListaPostes(ArrayList<Poste> listaPostes) {
        this.listaPostes = listaPostes;
    }
}
