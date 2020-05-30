package com.texnedo;

import com.texnedo.utils.Graph;
import com.texnedo.utils.WeightedQuickUnionPathCompressionUF;

import java.util.*;

public class RedundantConnection {
    public static void main(String[] args) {
        int[][] data = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        RedundantConnection connection = new RedundantConnection();
        System.out.println(Arrays.toString(connection.findRedundantConnection(data)));
        System.out.println(Arrays.toString(connection.findRedundantConnectionUF(data)));

        int[][] data1 = {{1,2}, {1,3}, {2,3}};
        System.out.println(Arrays.toString(connection.findRedundantConnection(data1)));
        System.out.println(Arrays.toString(connection.findRedundantConnectionUF(data1)));
    }

    public int[] findRedundantConnection(int[][] edges) {
        HashMap<Integer, List<Integer>> vertexes = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            List<Integer> startConnections
                    = vertexes.computeIfAbsent(start, k -> new LinkedList<>());
            startConnections.add(end);
            List<Integer> endConnections
                    = vertexes.computeIfAbsent(end, k -> new LinkedList<>());
            endConnections.add(start);
        }
        for (int i = edges.length - 1; i >= 0; i--) {
            if (isTree(vertexes, edges[i])) {
                return edges[i];
            }
        }
        return null;
    }

    /**
     * Checks if the graph is connected and has no cycles using DFS pass
     * */
    boolean isTree(HashMap<Integer, List<Integer>> vertexes, int[] exclude) {
        List<Integer> layer = new LinkedList<>();
        boolean[] visitedVertex = new boolean[vertexes.size()];
        int visitedCount = 0;
        layer.add(1);
        boolean[][] visitedEdge = new boolean[vertexes.size()][vertexes.size()];
        while (!layer.isEmpty()) {
            List<Integer> nextLayer = new LinkedList<>();
            for (Integer vertexStart : layer) {
                if (visitedVertex[vertexStart - 1]) {
                    return false;
                }
                visitedVertex[vertexStart - 1] = true;
                visitedCount++;
                List<Integer> connections = vertexes.get(vertexStart);
                for (Integer vertexEnd : connections) {
                    int min = Math.min(vertexStart, vertexEnd);
                    int max = Math.max(vertexStart, vertexEnd);
                    if (visitedEdge[min - 1][max - 1]) {
                        continue;
                    }
                    visitedEdge[min - 1][max - 1] = true;
                    boolean isExcludeEdge =
                            min == Math.min(exclude[0], exclude[1])
                                    && max == Math.max(exclude[0], exclude[1]);
                    if (isExcludeEdge) {
                        continue;
                    }
                    nextLayer.add(vertexEnd);
                }
            }
            layer = nextLayer;
        }
        return visitedCount == vertexes.size();
    }

    public int[] findRedundantConnectionUF(int[][] edges) {
        int maxVertex = 0;
        for (int i = 0; i < edges.length; i++) {
            maxVertex = Math.max(maxVertex, Math.max(edges[i][0], edges[i][1]));
        }
        WeightedQuickUnionPathCompressionUF uf =
                new WeightedQuickUnionPathCompressionUF(maxVertex);
        for (int i = 0; i < edges.length; i++) {
            if (uf.connected(edges[i][0] - 1, edges[i][1] - 1)) {
                return edges[i];
            }
            uf.union(edges[i][0] - 1, edges[i][1] - 1);
        }
        return null;
    }
}
