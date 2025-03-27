package model;

public class Poste {
    private static int contador = 0; 
    private String id;
    private boolean conectado;

    public Poste(boolean conectado){
        this.id = String.valueOf(contador++);
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
