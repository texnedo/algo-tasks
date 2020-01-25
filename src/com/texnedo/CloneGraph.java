package com.texnedo;

import java.util.*;

public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        public static Node parseAdjacencyList(int[][] adjacencyList) {
            if (adjacencyList == null || adjacencyList.length == 0) {
                throw new IllegalArgumentException();
            }
            HashMap<Integer, Node> nodes = new HashMap<>();
            for (int i = 0; i < adjacencyList.length; i++) {
                if (adjacencyList[i].length < 2) {
                    throw new IllegalArgumentException();
                }
                int id = adjacencyList[i][0];
                Node existing = nodes.get(id);
                if (existing == null) {
                    existing = new Node(id);
                    nodes.put(id, existing);
                }
                existing.neighbors = new ArrayList<>(adjacencyList[i].length - 1);
                for (int j = 1; j < adjacencyList[i].length; j++) {
                    int adjacentId = adjacencyList[i][j];
                    Node adjacent = nodes.get(adjacentId);
                    if (adjacent == null) {
                        adjacent = new Node(adjacentId);
                        nodes.put(adjacentId, adjacent);
                    }
                    existing.neighbors.add(adjacent);
                }
            }
            return nodes.values().iterator().next();
        }
    }

    public static void main(String[] args) {
        CloneGraph cloneGraph = new CloneGraph();
        int[][] data = {{1,2},{2,3},{3,4},{4,1}};
        Node result = cloneGraph.cloneGraph(Node.parseAdjacencyList(data));
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        HashMap<Node, Node> nodeMap = new HashMap<>();
        return cloneGraphInternal2(node, nodeMap);
    }

    public Node cloneGraphInternal(Node node, HashMap<Node, Node> nodeMap) {
        Node existing = nodeMap.get(node);
        if (existing != null) {
            return existing;
        }
        Node current = new Node(node.val);
        nodeMap.put(node, current);
        if (node.neighbors == null || node.neighbors.size() == 0) {
            return current;
        }
        current.neighbors = new ArrayList<>(node.neighbors.size());
        for (Node item : node.neighbors) {
            Node neighbour = nodeMap.get(item);
            if (neighbour == null) {
                neighbour = cloneGraphInternal(item, nodeMap);
                nodeMap.put(item, neighbour);
            }
            current.neighbors.add(neighbour);
        }
        return current;
    }

    public Node cloneGraphInternal2(Node node, HashMap<Node, Node> nodeMap) {
        LinkedList<Node> layer = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        layer.add(node);
        Node root = null;
        while (!layer.isEmpty()) {
            LinkedList<Node> nextLayer = new LinkedList<>();
            for (Node item : layer) {
                Node existing = nodeMap.get(item);
                if (existing == null) {
                    existing = new Node(item.val);
                    nodeMap.put(item, existing);
                    visited.add(existing);
                } else {
                    if (visited.contains(existing)) {
                        continue;
                    }
                }
                if (root == null) {
                    root = existing;
                }
                existing.neighbors = new ArrayList<>(item.neighbors.size());
                for (Node neighbour : item.neighbors) {
                    Node existingNeighbour = nodeMap.get(neighbour);
                    if (existingNeighbour == null) {
                        existingNeighbour = new Node(neighbour.val);
                        nodeMap.put(neighbour, existingNeighbour);
                    }
                    existing.neighbors.add(existingNeighbour);
                    nextLayer.add(neighbour);
                }
            }
            layer.clear();
            layer.addAll(nextLayer);
        }
        return root;
    }
}
