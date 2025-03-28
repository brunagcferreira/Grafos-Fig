package algoritmo;

import model.Poste;
import model.Grafo;
import java.util.*;

public class Dijkstra {
    private Grafo grafo;
    private Map<String, Double> distancias;
    private Map<String, Poste> pais;
    private Set<Poste> postesConectados;

    public Dijkstra(Grafo grafo) {
        this.grafo = grafo;
        this.distancias = new HashMap<>();
        this.pais = new HashMap<>();
        this.postesConectados = new HashSet<>();
    }

    public void encontrarMenorCaminho(Poste origem) {
    	
        for (Poste poste : grafo.getListaPostes()) {
            distancias.put(poste.getId(), Double.POSITIVE_INFINITY);
        }
        
        distancias.put(origem.getId(), 0.0);

        PriorityQueue<Poste> fila = new PriorityQueue<>(Comparator.comparingDouble(p -> distancias.get(p.getId())));
        
        fila.add(origem);

        while (!fila.isEmpty()) {
            Poste atual = fila.poll();

            if (atual.isConectado() && !atual.equals(origem)) {
                conectarCaminho(atual);
                return;
            }

            for (String vizinhoId : grafo.getListaDeAjacencia().get(atual.getId())) {
                Poste vizinho = grafo.encontrarPostePorId(vizinhoId);
                double distancia = grafo.getDistancia(atual, vizinho);
                double distanciaTotal = distancias.get(atual.getId()) + distancia;

                if (distanciaTotal < distancias.get(vizinho.getId())) {
                    distancias.put(vizinho.getId(), distanciaTotal);
                    pais.put(vizinho.getId(), atual);
                    fila.add(vizinho);
                }
            }
        }
    }

    private void conectarCaminho(Poste destino) {
        Poste atual = destino;
        while (atual != null) {
            atual.setConectado(true);
            postesConectados.add(atual);
            atual = pais.get(atual.getId());
        }
    }

    public List<Poste> getCaminho(Poste destino) {
        List<Poste> caminho = new ArrayList<>();
        Poste atual = destino;

        while (atual != null) {
            caminho.add(0, atual);
            atual = pais.get(atual.getId());
        }

        return caminho;
    }
    
    public double getDistancia(String posteId) {
        return distancias.getOrDefault(posteId, Double.POSITIVE_INFINITY);
    }
}