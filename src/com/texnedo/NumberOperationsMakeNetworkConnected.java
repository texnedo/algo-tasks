package com.texnedo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NumberOperationsMakeNetworkConnected {
    public static void main(String[] args) {
        NumberOperationsMakeNetworkConnected connected = new NumberOperationsMakeNetworkConnected();
        int[][] data = {{0,1},{0,2},{0,3},{1,2}};
        System.out.println(connected.makeConnected(6, data));

        int[][] data1 = {{0,1},{0,2},{0,3},{1,2},{4, 5}};
        System.out.println(connected.makeConnected(6, data1));
    }

    public int makeConnected(int n, int[][] connections) {
        if (n == 0) {
            return 0;
        }
        @SuppressWarnings("unchecked")
        List<int[]>[] nodes = new List[n];
        for (int i = 0; i < connections.length; i++) {
            int[] edge = connections[i];
            if (nodes[edge[0]] == null) {
                nodes[edge[0]] = new ArrayList<>();
            }
            nodes[edge[0]].add(edge);
        }
        boolean[] visited = new boolean[n];
        List<List<Integer>> clusters = new ArrayList<>();
        List<Integer> clusterEdgeCount = new ArrayList<>();
        for (int i = 0; i < nodes.length; i++) {
            if (!visited[i]) {
                List<Integer> currentCluster = new LinkedList<>();
                int edgeCount = dfs(nodes, visited, currentCluster, i);
                clusters.add(currentCluster);
                clusterEdgeCount.add(edgeCount);
            }
        }
        if (clusters.size() == 1) {
            return 0;
        }
        int edgesNeeded = clusters.size() - 1;
        int redundantEdges = 0;
        for (int i = 0; i < clusters.size(); i++) {
            List<Integer> cluster = clusters.get(i);
            redundantEdges += clusterEdgeCount.get(i) - (cluster.size() - 1);
        }
        if (edgesNeeded <= redundantEdges) {
            return edgesNeeded;
        }
        return -1;
    }

    private int dfs(List<int[]>[] nodes, boolean[] visited, List<Integer> cluster, int index) {
        if (visited[index]) {
            return 0;
        }
        cluster.add(index);
        visited[index] = true;
        List<int[]> connections = nodes[index];
        if (connections == null) {
            return 0;
        }
        int subConnectionsCount = 0;
        for (int[] connection : connections) {
            if (connection[0] != index) {
                subConnectionsCount += dfs(nodes, visited, cluster, connection[0]);
            } else {
                subConnectionsCount += dfs(nodes, visited, cluster, connection[1]);
            }
        }
        return subConnectionsCount + connections.size();
    }
}
