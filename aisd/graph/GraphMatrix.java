package graph;

import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrix {
    private int[][] adjacencyMatrix;
    private int numVertices;

    public GraphMatrix(int numVertices) {
        this.numVertices = numVertices;
        adjacencyMatrix = new int[numVertices][numVertices];
    }

    public void addEdge(int source, int destination) {
        adjacencyMatrix[source][destination] = 1;
        adjacencyMatrix[destination][source] = 1;
    }

    public void printGraph() {
        System.out.println("Adjacency Matrix:");

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        dfsUtil(startVertex, visited);
    }

    private void dfsUtil(int currentVertex, boolean[] visited) {
        visited[currentVertex] = true;
        System.out.print(currentVertex + " ");

        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[currentVertex][i] == 1 && !visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }
    public void bfs(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        System.out.print(startVertex + " ");

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[currentVertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    System.out.print(i + " ");
                    queue.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphMatrix graph = new GraphMatrix(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);

        System.out.println("Breadth First Traversal:");
        graph.bfs(0);

        System.out.println("\nDepth First Traversal:");
        graph.dfs(0);
    }
}
