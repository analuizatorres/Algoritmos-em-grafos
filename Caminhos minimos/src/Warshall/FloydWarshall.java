package Warshall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloydWarshall {

    private static final int INF = 1000000000;

    public static void floydWarshall(List<List<Aresta>> grafo) {
        int n = grafo.size();
        int[][] distancia = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distancia[i], INF);
            distancia[i][i] = 0;
        }

        for (int u = 0; u < n; u++) {
            for (Aresta e : grafo.get(u)) {
                int v = e.to;
                int w = e.peso;
                distancia[u][v] = w;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancia[i][k] != INF && distancia[k][j] != INF) {
                        distancia[i][j] = Math.min(distancia[i][j], distancia[i][k] + distancia[k][j]);
                    }
                }
            }
        }

        // printa distancia minima
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distancia[i][j] == INF) {
                    System.out.print("inf |"); //INF, aqui é so pra matriz nao ficar tão torta, então botei so como inf
                } else {
                    System.out.print(distancia[i][j] + " | ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<Aresta>> grafo = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            grafo.add(new ArrayList<>());
        }
        grafo.get(0).add(new Aresta(1, 5));
        grafo.get(0).add(new Aresta(2, 10));
        grafo.get(1).add(new Aresta(2, 3));
        grafo.get(1).add(new Aresta(3, 20));
        grafo.get(2).add(new Aresta(3, 2));
        grafo.get(3).add(new Aresta(1, 6));

        floydWarshall(grafo);
    }
}
