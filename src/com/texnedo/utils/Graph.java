package com.texnedo.utils;

import java.util.LinkedList;
import java.util.List;

public class Graph {
    final List<Integer> connections[];

    public Graph(int N) {
        //noinspection unchecked
        this.connections = new LinkedList[N];
    }

    public void addEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        if (connections[v] == null) {
            connections[v] = new LinkedList<>();
        }
        connections[v].add(w);
    }

    private void checkVertex(int v) {
        if (v < 0 || v > connections.length - 1) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasCycle(boolean[] visited, int v) {
        if (visited == null || visited.length != connections.length) {
            throw new IllegalArgumentException();
        }
        checkVertex(v);
        visited[v] = true;
        List<Integer> links = connections[v];
        if (links == null) {
            return false;
        }
        for (Integer w : links) {
            if (visited[w]) {
                return true;
            }
            if (hasCycle(visited, w)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[connections.length];
        return hasCycle(visited, 0);
    }

    public boolean isTree() {
        boolean[] visited = new boolean[connections.length];
        if (hasCycle(visited, 0)) {
            return false;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
