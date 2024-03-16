
//-----------------------------------------------------
//Title:Shortest Path (homework1 q2)
//Author: Melisa SUBAŞI
//ID: 22829169256
//Section: 1
//Assignment: 1
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of museums and roads
        int M = scanner.nextInt();
        int N = scanner.nextInt();

        // Create an adjacency list to represent the undirected graph
        Map<Integer, List<Edge>> graph = new HashMap<>();

        // Read the connections between museums and build the graph
        for (int i = 0; i < N; i++) {
            int U = scanner.nextInt();
            int V = scanner.nextInt();
            int weight = scanner.nextInt();

            // Add edges to the adjacency list
            graph.computeIfAbsent(U, k -> new ArrayList<>()).add(new Edge(V, weight));
            graph.computeIfAbsent(V, k -> new ArrayList<>()).add(new Edge(U, weight));
        }

        // Initialize variables to track minimum time and the path
        int minTime = Integer.MAX_VALUE;
        List<Integer> minPath = null;

        // Try each museum as the starting point
        for (int startMuseum = 1; startMuseum <= M; startMuseum++) {
            List<Integer> path = new ArrayList<>();
            Set<Integer> visited = new HashSet<>();
            int totalTime = dfs(graph, startMuseum, path, visited);

            // If all museums are visited, update the minimum time and path
            if (path.size() == M) {
                if (totalTime < minTime) {
                    minTime = totalTime;
                    minPath = new ArrayList<>(path);
                }
            }
        }

        // Print the result
        if (minPath == null) {
            System.out.println("-1");
        } else {
            System.out.println(minTime);
            for (int museum : minPath) {
                System.out.print(museum + " ");
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Depth-first search to calculate the time to visit all museums
    private static int dfs(Map<Integer, List<Edge>> graph, int current, List<Integer> path, Set<Integer> visited) {
        // Mark the current museum as visited
        visited.add(current);
        path.add(current);

        int totalTime = 0;

        // Visit adjacent museums
        for (Edge neighbor : graph.getOrDefault(current, Collections.emptyList())) {
            if (!visited.contains(neighbor.destination)) {
                totalTime += neighbor.weight;
                totalTime += dfs(graph, neighbor.destination, path, visited);
            }
        }

        return totalTime;
    }

    // Edge class to represent the connection between museums
    private static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}
