package model;

public class Conexoes {                          // Aresta
    private String id;
    private Poste origem;
    private Poste destino;
    private Double distancia;

    public Conexoes(String id, Poste origem, Poste destino, Double distancia) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Poste getOrigem() {
        return origem;
    }

    public void setOrigem(Poste origem) {
        this.origem = origem;
    }

    public Poste getDestino() {
        return destino;
    }

    public void setDestino(Poste destino) {
        this.destino = destino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }
}