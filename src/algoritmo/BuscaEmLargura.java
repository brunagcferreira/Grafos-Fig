package algoritmo;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import model.Poste;
import model.RedeEletrica;

public class BuscaEmLargura {
    private RedeEletrica rede;
    private Poste posteResponsavel;

    public BuscaEmLargura(RedeEletrica rede) {
        this.rede = rede;
    }

    public List<Poste> encontrarCaminho(Poste posteCliente) throws Exception {
        // Se o poste da casa já estiver conectado e tiver capacidade, conecta direto
        if (posteCliente.isConectado()) {
            if (posteCliente.getCasasAtendidas() < Poste.getCapacidadeMax()) {
                posteCliente.setCasasAtendidas(posteCliente.getCasasAtendidas() + 1);
                this.posteResponsavel = posteCliente;
                return Collections.singletonList(posteCliente);
            }
        }

        Map<Poste, Poste> pais = new HashMap<>();
        Map<Poste, Double> distancias = new HashMap<>();
        Queue<Poste> fila = new LinkedList<>();

        for (Poste p : rede.getListaPostes()) {
            distancias.put(p, Double.POSITIVE_INFINITY);
        }
        distancias.put(posteCliente, 0.0);
        fila.add(posteCliente);

        Poste posteConectadoMaisProximo = null;
        double menorDistancia = Double.POSITIVE_INFINITY;

        while (!fila.isEmpty()) {
            Poste atual = fila.poll();

            if (atual.isConectado() && atual.getCasasAtendidas() < Poste.getCapacidadeMax()) {
                if (distancias.get(atual) < menorDistancia) {
                    menorDistancia = distancias.get(atual);
                    posteConectadoMaisProximo = atual;
                }
                continue;
            }

            for (String vizinhoId : rede.getListaDeAjacencia().get(atual.getId())) {
                Poste vizinho = rede.encontrarPostePorId(vizinhoId);
                double distanciaFio = rede.getDistancia(atual, vizinho);
                double novaDistancia = distancias.get(atual) + distanciaFio;

                if (novaDistancia < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDistancia);
                    pais.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }

        if (posteConectadoMaisProximo != null) {
            List<Poste> caminho = reconstruirCaminho(pais, posteConectadoMaisProximo, posteCliente);

            if(posteCliente.getCasasAtendidas() < Poste.getCapacidadeMax()){
                posteCliente.setCasasAtendidas(posteCliente.getCasasAtendidas() + 1);
                this.posteResponsavel = posteCliente;
            }
            else{
                posteConectadoMaisProximo.setCasasAtendidas(posteConectadoMaisProximo.getCasasAtendidas()+1);
                this.posteResponsavel = posteConectadoMaisProximo;
            }
            return caminho;
        }

        for (String vizinhoId : rede.getListaDeAjacencia().get(posteCliente.getId())) {
            Poste vizinho = rede.encontrarPostePorId(vizinhoId);
            if (!vizinho.isConectado()) {
                List<Poste> caminho = new LinkedList<>();
                caminho.add(posteCliente);
                caminho.add(vizinho);
                vizinho.setConectado(true);
                vizinho.setCasasAtendidas(1);
                this.posteResponsavel = vizinho;
                return caminho;
            }
        }

        throw new Exception("nenhum poste disponível");
    }

    private List<Poste> reconstruirCaminho(Map<Poste, Poste> pais, Poste destino, Poste origem) {
        LinkedList<Poste> caminho = new LinkedList<>();
        Poste atual = destino;

        while (atual != null && !atual.equals(origem)) {
            caminho.addFirst(atual);
            atual = pais.get(atual);
        }
        caminho.addFirst(origem);

        return caminho;
    }

    public void conectarCaminho(List<Poste> caminho) {
        for (Poste poste : caminho) {
            poste.setConectado(true);
        }
    }

    public double calcularDistanciaTotal(List<Poste> caminho) {
        if (caminho == null || caminho.size() < 2) {
            return 0;
        }

        double distanciaTotal = 0;
        for (int i = 0; i < caminho.size() - 1; i++) {
            Poste atual = caminho.get(i);
            Poste proximo = caminho.get(i + 1);
            distanciaTotal += rede.getDistancia(atual, proximo);
        }
        return distanciaTotal;
    }

    public String caminhoToString(List<Poste> caminho) {
        if (caminho == null || caminho.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (Poste poste : caminho) {
            sb.append(poste.getId()).append(", ");
        }

        sb.setLength(sb.length() - 2);
        sb.append("]");

        return sb.toString();
    }

    public RedeEletrica getRede() {
        return rede;
    }

    public void setRede(RedeEletrica rede) {
        this.rede = rede;
    }

    public Poste getPosteResponsavel() {
        return posteResponsavel;
    }

    public void setPosteResponsavel(Poste posteCliente) {
        this.posteResponsavel = posteCliente;
    }

}