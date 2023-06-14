package Bellman;

import java.util.*;

public class Bellman {
    private static final int INF = Integer.MAX_VALUE; // Valor utilizado para representar a distância infinita
    
    public static void bellmanFord(List<List<Aresta>> adjList) {
        int n = adjList.size(); // Número de vértices no grafo
        
        for (int start = 0; start < n; start++) { // Executa o algoritmo a partir de cada vértice
            int[] dist = new int[n]; // Array que armazena as menores distâncias conhecidas até cada vértice
            Arrays.fill(dist, INF);
            dist[start] = 0;
            
            boolean relaxed = true;
            for (int i = 0; i < n - 1 && relaxed; i++) {
                relaxed = false;
                for (int u = 0; u < n; u++) {
                    for (Aresta Aresta : adjList.get(u)) {
                        int v = Aresta.to;
                        int w = Aresta.peso;
                        
                        if (dist[u] != INF && dist[u] + w < dist[v]) {
                            dist[v] = dist[u] + w;
                            relaxed = true;
                        }
                    }
                }
            }
            
            // Verifica se há ciclo negativo alcançável a partir do vértice inicial
            boolean hasNegativeCycle = false;
            for (int u = 0; u < n; u++) {
                for (Aresta Aresta : adjList.get(u)) {
                    int v = Aresta.to;
                    int w = Aresta.peso;
                    
                    if (dist[u] != INF && dist[u] + w < dist[v]) {
                        hasNegativeCycle = true;
                    }
                }
            }
            
            // Imprime os caminhos mínimos a partir do vértice inicial
            System.out.println("\nCaminhos mínimos a partir do vértice " + start + ":");
            for (int end = 0; end < n; end++) {
                if (dist[end] == INF) {
                    System.out.println("Não há caminho de " + start + " até " + end);
                } else {
                    System.out.println("Menor caminho de " + start + " até " + end + ": " + dist[end]);
                }
            }
            
            if (hasNegativeCycle) {
                System.out.println("O grafo contém ciclo negativo alcançável a partir do vértice " + start);
            }
        }
        
    }
    
    static class Aresta {
        int to; // Vértice de destino
        int peso; // Peso da aresta
        
        public Aresta(int to, int peso) {
            this.to = to;
            this.peso = peso;
        }
    }
    public static void main(String[] args) {
       List<List<Aresta>> adjList = new ArrayList<>();

       int n = 5;

        // Adiciona as listas vazias para cada vértice
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Adiciona as arestas no grafo
        adjList.get(0).add(new Aresta(1, 5));
        adjList.get(0).add(new Aresta(2, 3));
        adjList.get(1).add(new Aresta(3, 6));
        adjList.get(2).add(new Aresta(1, -2));
        adjList.get(2).add(new Aresta(3, 7));
        adjList.get(3).add(new Aresta(4, -1));
        adjList.get(4).add(new Aresta(2, 1));
        adjList.get(4).add(new Aresta(0, 7));


        bellmanFord(adjList);

        
    }

}

