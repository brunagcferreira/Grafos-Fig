package model;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Grafo {
    private Map<String, LinkedList<String>> listaDeAjacencia;
    private Set<Conexoes> listaConexoes;
    private Set<Poste> listaPostes;

    public Grafo(Map<String, LinkedList<String>> listaDeAjacencia, Set<Conexoes> listaConexoes,
            Set<Poste> listaPostes) {
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
        }
    }

    public Map<String, LinkedList<String>> getListaDeAjacencia() {
        return listaDeAjacencia;
    }

    public void setListaDeAjacencia(Map<String, LinkedList<String>> listaDeAjacencia) {
        this.listaDeAjacencia = listaDeAjacencia;
    }

    public Set<Conexoes> getListaConexoes() {
        return listaConexoes;
    }

    public void setListaConexoes(Set<Conexoes> listaConexoes) {
        this.listaConexoes = listaConexoes;
    }

    public Set<Poste> getListaPostes() {
        return listaPostes;
    }

    public void setListaPostes(Set<Poste> listaPostes) {
        this.listaPostes = listaPostes;
    }
}
