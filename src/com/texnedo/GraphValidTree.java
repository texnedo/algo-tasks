package com.texnedo;

import java.util.*;

public class GraphValidTree {

    public static void main(String[] args) {
        GraphValidTree tree = new GraphValidTree();
        int[][] data = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int[][] data1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {7, 8}};
        int[][] data2 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {4, 0}};
        System.out.println(tree.isValidTree(data));
        System.out.println(tree.isValidTree(data1));
        System.out.println(tree.isValidTree(data2));
    }

    boolean isValidTree(int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            List<Integer> connections = adjList.get(from);
            if (connections == null) {
                connections = new LinkedList<>();
                adjList.put(from, connections);
            }
            connections.add(to);
        }
        return !hasCycle(adjList) && allReachable(adjList);
    }

    boolean hasCycle(Map<Integer, List<Integer>> adjList) {
        Set<Integer> visited = new HashSet<>();
        for (Integer i : adjList.keySet()) {
            if (visited.contains(i)) {
                continue;
            }
            if (dfsCycleCheck(adjList, visited, i)) {
                return true;
            }
        }
        return false;
    }

    boolean dfsCycleCheck(Map<Integer, List<Integer>> adjList, Set<Integer> visited, int start) {
        if (visited.contains(start)) {
            return true;
        }
        visited.add(start);
        List<Integer> connections = adjList.get(start);
        if (connections == null) {
            return false;
        }
        for (Integer connection : connections) {
            if (dfsCycleCheck(adjList, visited, connection)) {
                return true;
            }
        }
        return false;
    }

    boolean allReachable(Map<Integer, List<Integer>> adjList) {
        Set<Integer> visited = new HashSet<>();
        bfsIntegrityCheck(adjList, visited, 0);
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            if (!visited.contains(entry.getKey())) {
                return false;
            }
            if (entry.getValue() != null) {
                for (Integer integer : entry.getValue()) {
                    if (!visited.contains(integer)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void bfsIntegrityCheck(Map<Integer, List<Integer>> adjList,
                           Set<Integer> visited,
                           int start) {
        visited.add(start);
        List<Integer> connections = adjList.get(start);
        if (connections == null) {
            return;
        }
        Queue<Integer> level = new LinkedList<>();
        level.offer(start);
        while (!level.isEmpty()) {
            LinkedList<Integer> nextLevel = new LinkedList<>();
            while (!level.isEmpty()) {
                Integer child = level.poll();
                visited.add(child);
                List<Integer> children = adjList.get(child);
                if (children != null) {
                    nextLevel.addAll(children);
                }
            }
            level.addAll(nextLevel);
        }
    }
}
