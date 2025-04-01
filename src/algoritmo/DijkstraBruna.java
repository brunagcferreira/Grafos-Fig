package algoritmo;

import java.util.*;
import model.RedeEletrica;
import model.Poste;

public class DijkstraBruna {
    
    private RedeEletrica grafo;

    public DijkstraBruna(RedeEletrica grafo) {
        this.grafo = grafo;
    }

    /**
     * Executa o algoritmo de Dijkstra para encontrar o menor caminho a partir do
     * poste 'origem' até o primeiro poste conectado (isConectado() == true), que é
     * o ponto de conexão com a provedora.
     *
     * Após encontrar o caminho, marca todos os postes do caminho como conectados.
     *
     * Se não houver caminho possível, exibe uma mensagem clara.
     *
     * @param origem O poste de entrada (que inicialmente não está conectado)
     */
    public void encontrarCaminho(Poste origem) {
        // Se o poste de entrada já estiver conectado, nada a fazer.
        if (origem.isConectado()) {
            System.out.println("O poste de entrada " + origem.getId() + " já está conectado.");
            return;
        }

        // Map para armazenar a distância mínima do poste de entrada a cada poste.
        Map<Poste, Double> distanciaMinima = new HashMap<>();
        // Map para armazenar o antecessor, para reconstruir o caminho.
        Map<Poste, Poste> antecessor = new HashMap<>();

        // Inicializa as distâncias como infinito para todos os postes.
        for (Poste p : grafo.getListaPostes()) {
            distanciaMinima.put(p, Double.POSITIVE_INFINITY);
            antecessor.put(p, null);
        }
        // A distância do poste de origem é 0.
        distanciaMinima.put(origem, 0.0);

        // PriorityQueue, fila de prioridade, para selecionar o próximo poste com menor distância.
        PriorityQueue<Poste> fila = new PriorityQueue<>(Comparator.comparingDouble(distanciaMinima::get));
        fila.add(origem);

        // Variável para armazenar o alvo (primeiro poste conectado encontrado)
        Poste posteAlvo = null;

        while (!fila.isEmpty()) {
            Poste posteAtual = fila.poll();

            // Se encontrar um poste conectado (que não seja o de entrada), esse é o destino.
            if (posteAtual.isConectado() && !posteAtual.equals(origem)) {
                posteAlvo = posteAtual;
                break;
            }

            // Recupera os vizinhos do poste atual através da lista de adjacência.
            // Considera que a chave é o ID do poste.
            LinkedList<String> vizinhosIds = grafo.getListaDeAjacencia().get(posteAtual.getId());
            if (vizinhosIds == null) continue;

            // Para cada vizinho, atualiza as distâncias se encontrar um caminho mais curto.
            for (String vizinhoId : vizinhosIds) {
                Poste vizinho = grafo.encontrarPostePorId(vizinhoId);
                if (vizinho == null) continue;

                // Calcula a distância do caminho passando por 'posteAtual'.
                double alt = distanciaMinima.get(posteAtual) + grafo.getDistancia(posteAtual, vizinho);
                if (alt < distanciaMinima.get(vizinho)) {
                    distanciaMinima.put(vizinho, alt);
                    antecessor.put(vizinho, posteAtual);
                    // Atualiza a Fila de Prioridade removendo e re-adicionando o vizinho.
                    fila.remove(vizinho);
                    fila.add(vizinho);
                }
            }
        }

        // Se não foi encontrado nenhum poste conectado, exibe mensagem e encerra.
        if (posteAlvo == null) {
            System.out.println("Não há caminho possível a partir do poste " + origem.getId() + " até um poste conectado.");
            return;
        }

        // Reconstrói o caminho a partir do target usando o mapa 'antecessor'.
        List<Poste> caminho = new ArrayList<>();
        Poste aux = posteAlvo;
        while (aux != null) {
            caminho.add(aux);
            aux = antecessor.get(aux);
        }
        Collections.reverse(caminho);

        // Exibe o caminho e a distância total.
        double distanciaTotal = distanciaMinima.get(posteAlvo);
        System.out.println("Menor caminho do poste " + origem.getId() + " até o poste conectado " + posteAlvo.getId() + ":");
        for (Poste p : caminho) {
            System.out.print(p.getId() + " ");
        }
        System.out.println("\nDistância total: " + distanciaTotal);

        // Conecta todos os postes que fazem parte do caminho.
        for (Poste p : caminho) {
            p.setConectado(true);
        }
    }
}

