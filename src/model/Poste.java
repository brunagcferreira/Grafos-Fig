package model;

public class Poste {
    private String id;
    private boolean conectado;

    public Poste(String id, boolean conectado){
        this.id = id;
        this.conectado = conectado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }
}
