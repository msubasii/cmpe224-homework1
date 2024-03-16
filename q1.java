
//-----------------------------------------------------
//Title:Shortest Path (homework1 q1)
//Author: Melisa SUBAŞI
//ID: 22829169256
//Section: 1
//Assignment: 1
//Description: This program finds the shortest path between two nodes in a graph.
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class q1 {

 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     int N = sc.nextInt(); // Number of nodes
     int M = sc.nextInt(); // Number of edges
     int X = sc.nextInt(); // Source node
     int Y = sc.nextInt(); // Destination node

     // Create an adjacency list to represent the graph
     List<Set<Integer>> graph = new ArrayList<>(N + 1);
     for (int i = 0; i <= N; i++) {
         graph.add(new HashSet<>());
     }

     // Read and build the graph
     for (int i = 0; i < M; i++) {
         int A = sc.nextInt();
         int B = sc.nextInt();
         graph.get(A).add(B);
         graph.get(B).add(A);
     }

     // Find the minimum distance between nodes X and Y
     int minDistance = getDistance(X, Y, graph);
     List<String> newTracks = new ArrayList<>();

     // Try adding new edges and check if they maintain the minimum distance
     for (int i = 1; i <= N; i++) {
         for (int j = i + 1; j <= N; j++) {
             if (!graph.get(i).contains(j)) {
                 graph.get(i).add(j);
                 graph.get(j).add(i);

                 int newDistance = getDistance(X, Y, graph);

                 // If the new distance is the same as the minimum distance, add the edge to newTracks
                 if (newDistance == minDistance) {
                     newTracks.add(i + " " + j);
                 }

                 graph.get(i).remove(j);
                 graph.get(j).remove(i);
             }
         }
     }

     // Output the results
     if (newTracks.isEmpty()) {
         System.out.println(-1);
     } else {
         System.out.println(newTracks.size());
         newTracks.sort(Comparator.naturalOrder());
         for (String track : newTracks) {
             System.out.println(track);
         }
     }
 }

 /** Calculates the shortest distance between two nodes X and Y in a graph using BFS.
  * x,The starting node.
  * y,     The destination node.
  * graph, The graph represented as an adjacency list.
  * returns The shortest distance between X and Y.
  */
 private static int getDistance(int X, int Y, List<Set<Integer>> graph) {
     Queue<Integer> queue = new LinkedList<>();
     boolean[] visited = new boolean[graph.size()];
     int[] distance = new int[graph.size()];

     queue.add(X);
     visited[X] = true;

     while (!queue.isEmpty()) {
         int current = queue.poll();
         for (int neighbor : graph.get(current)) {
             if (!visited[neighbor]) {
                 queue.add(neighbor);
                 visited[neighbor] = true;
                 distance[neighbor] = distance[current] + 1;
             }
         }
     }

     return distance[Y];
 }
}
