package model;

public class Poste {
    private static int contador = 0; //conta quantos postes tem
    private static int capacidadeMax = 5; //qntd máxima de casas de clientes para aquele poste
    private String id;
    private boolean conectado; //se o poste está conectao a provedora
    private int casasAtendidas=0; //quantidade de casas atuais, não deve ultrapassar capacidadeMax

    public Poste(boolean conectado){
        this.id = String.valueOf(contador++);
        this.conectado = conectado;
        this.casasAtendidas = 0;
    }

    public String getId() {
        return id;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public static int getContador() {
        return contador;
    }

    public static int getCapacidadeMax() {
        return capacidadeMax;
    }

    public int getCasasAtendidas() {
        return casasAtendidas;
    }

    public void setCasasAtendidas(int casasAtendidas) {
        this.casasAtendidas = casasAtendidas;
    }

}
