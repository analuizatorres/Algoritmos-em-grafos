package Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Vertice {
    int numero;
    List<Integer> adj_list_entrada;
    List<Integer> adj_list_saida;

    public Vertice(int numero) {
        this.numero = numero;
        this.adj_list_entrada = new ArrayList<>();
        this.adj_list_saida = new ArrayList<>();
    }
}

class Grafo {
    int[][] matriz_arestas;
    Vertice[] vertices;

    public Grafo(int[][] matriz_arestas_passada) {
        this.matriz_arestas = Arrays.copyOf(matriz_arestas_passada, matriz_arestas_passada.length);
        int qtd_vertices = matriz_arestas_passada.length;
        if (qtd_vertices > 0 && matriz_arestas_passada.length == matriz_arestas_passada[0].length) {
            this.vertices = new Vertice[qtd_vertices];
            for (int i = 0; i < qtd_vertices; i++) {
                Vertice v = new Vertice(i + 1);
                this.vertices[i] = v;
            }
            for (int i = 0; i < qtd_vertices; i++) {
                for (int j = 0; j < qtd_vertices; j++) {
                    if (matriz_arestas_passada[i][j] > 0 && matriz_arestas_passada[i][j] != Integer.MAX_VALUE) {
                        for (Vertice k : this.vertices) {
                            if (k.numero == j + 1) {
                                k.adj_list_entrada.add(i + 1);
                                break;
                            }
                        }
                        for (Vertice k : this.vertices) {
                            if (k.numero == i + 1) {
                                k.adj_list_saida.add(j + 1);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public int quantidadeVertices() {
        return this.vertices.length;
    }

    public int quantidadeArestas() {
        int qtd_arestas = 0;
        for (int i = 0; i < matriz_arestas.length; i++) {
            for (int j = 0; j < matriz_arestas[i].length; j++) {
                if (matriz_arestas[i][j] > 0 && matriz_arestas[i][j] != Integer.MAX_VALUE) {
                    qtd_arestas++;
                }
            }
        }
        return qtd_arestas;
    }
}

public class Diijkstra {

    static int[] dijkstra(Grafo g, int v) {
        int n = g.quantidadeVertices();
        int[] C = new int[n];
        boolean[] visitado = new boolean[n];

        Arrays.fill(C, Integer.MAX_VALUE);
        C[v - 1] = 0;

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(v);

        while (!heap.isEmpty()) {
            int u = heap.poll();
            visitado[u - 1] = true;
            for (int vertice : g.vertices[u - 1].adj_list_saida) {
                if (!visitado[vertice - 1]) {
                    int custo = g.matriz_arestas[u - 1][vertice - 1];
                    if (C[vertice - 1] > C[u - 1] + custo) {
                        C[vertice - 1] = C[u - 1] + custo;
                        heap.offer(vertice);
                    }
                }
            }
        }

        return Arrays.copyOfRange(C, 0, v - 1);
    }

    public static void main(String[] args) {
        int[][] matriz_arestas = {{0, 1, Integer.MAX_VALUE, Integer.MAX_VALUE},
                                  {Integer.MAX_VALUE, 0, 1, Integer.MAX_VALUE},
                                  {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
                                  {1, 1, Integer.MAX_VALUE, 0}};

        Grafo G = new Grafo(matriz_arestas);

        int[] A = dijkstra(G, 4);

        System.out.println(Arrays.toString(A));
    }
}
