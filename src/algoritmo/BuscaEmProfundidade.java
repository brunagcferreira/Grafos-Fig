package algoritmo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Poste;
import model.Grafo;

//implementacao inicial
public class BuscaEmProfundidade {
    private Grafo grafo;
    private Set<String> visitados;
    private List<Poste> ordemVisita;
    private Map<String, String> pai;

    public BuscaEmProfundidade(Grafo grafo) {
        this.grafo = grafo;
        this.visitados = new HashSet<>();
        this.ordemVisita = new ArrayList<>();
        this.pai = new HashMap<>();
    }

 
    public void buscar(Poste inicio) {
        if (visitados.contains(inicio.getId())) {
            return;
        }

        visitados.add(inicio.getId());
        ordemVisita.add(inicio);

        LinkedList<String> vizinhosIds = grafo.getListaDeAjacencia().get(inicio.getId());
        if (vizinhosIds != null) {
            for (String vizinhoId : vizinhosIds) {
                if (!visitados.contains(vizinhoId)) {
                    Poste vizinho = encontrarPostePorId(vizinhoId);
                    if (vizinho != null) {
                        pai.put(vizinhoId, inicio.getId());
                        buscar(vizinho);
                    }
                }
            }
        }
    }

    private Poste encontrarPostePorId(String id) {
        for (Poste poste : grafo.getListaPostes()) {
            if (poste.getId().equals(id)) {
                return poste;
            }
        }
        return null;
    }

    public List<Poste> getOrdemVisita() {
        return ordemVisita;
    }

    public boolean isConexo() {
        return visitados.size() == grafo.getListaPostes().size();
    }
    
    public List<Poste> fazerCaminho(Poste alvo) {
        List<Poste> caminho = new ArrayList<>();
        String atualId = alvo.getId();

        while (atualId != null) {
            Poste poste = encontrarPostePorId(atualId);
            if (poste != null) {
                caminho.add(0, poste); // Adiciona no início para manter a ordem
            }
            atualId = pai.get(atualId);
        }

        return caminho;
    }

}