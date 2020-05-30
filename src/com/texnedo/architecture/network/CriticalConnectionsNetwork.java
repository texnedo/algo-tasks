package com.texnedo.architecture.network;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/bridge-in-a-graph/ - way to find weak connection spots
 * https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 *
 * Current implementation is slow as it runs DFS for after removal of each edge.
 * Although, algorithm https://www.geeksforgeeks.org/bridge-in-a-graph/ allows to reduce numbers
 * of DFS passes significantly.
 *
 * In DFS tree an edge (u, v) (u is parent of v in DFS tree) is bridge if there does not exist
 * any other alternative to reach u or an ancestor of u from subtree rooted with v.
 * */
public class CriticalConnectionsNetwork {
    public static void main(String[] args) {
        CriticalConnectionsNetwork network = new CriticalConnectionsNetwork();
        int[][] data = {{0,1},{1,2},{2,0},{1,3}};
        List<List<Integer>> connections = new ArrayList<>(data.length);
        for (int i = 0; i < data.length; i++) {
            connections.add(Arrays.asList(data[i][0], data[i][1]));
        }
        System.out.println(network.criticalConnections(4, connections));
        //
        int[][] data1 = {{0,1},{1,2},{2,0},{1,3},{3,4},{4,5},{5,3}};
        List<List<Integer>> connections1 = new ArrayList<>(data1.length);
        for (int i = 0; i < data1.length; i++) {
            connections1.add(Arrays.asList(data1[i][0], data1[i][1]));
        }
        System.out.println(network.criticalConnections(6, connections1));
    }

    private static class Server {
        final int number;
        List<Server> connections = new LinkedList<>();

        private Server(int number) {
            this.number = number;
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Server[] servers = buildNetworkGraph(n, connections);
        List<List<Integer>> result = new LinkedList<>();
        for (List<Integer> connectionToDrop : connections) {
            if (runDFS(servers, connectionToDrop) != servers.length) {
                result.add(connectionToDrop);
            }
        }
        return result;
    }

    private int runDFS(Server[] servers, List<Integer> connectionToDrop) {
        boolean[] visited = new boolean[servers.length];
        LinkedList<Server> serverQueue = new LinkedList<>();
        serverQueue.addFirst(servers[0]);
        int visitedCount = 0;
        while (!serverQueue.isEmpty()) {
            Server leftMost = serverQueue.pollFirst();
            if (leftMost == null) {
                break;
            }
            if (visited[leftMost.number]) {
                continue;
            }
            for (Server connection : leftMost.connections) {
                if (!visited[connection.number] &&
                        !isConnectionToDrop(leftMost, connection, connectionToDrop)) {
                    serverQueue.addFirst(connection);
                }
            }
            visited[leftMost.number] = true;
            visitedCount++;
        }
        return visitedCount;
    }

    private boolean isConnectionToDrop(Server server1,
                                       Server server2,
                                       List<Integer> connectionToDrop) {
        int minServerNumber = Math.min(server1.number, server2.number);
        int maxServerNumber = Math.max(server1.number, server2.number);
        int minLinkNumber = Math.min(connectionToDrop.get(0), connectionToDrop.get(1));
        int maxLinkNumber = Math.max(connectionToDrop.get(0), connectionToDrop.get(1));
        if (minServerNumber == minLinkNumber && maxServerNumber == maxLinkNumber) {
            return true;
        }
        return false;
    }

    private Server[] buildNetworkGraph(int n, List<List<Integer>> connections) {
        Server[] servers = new Server[n];
        for (List<Integer> connection : connections) {
            int start = connection.get(0);
            int end = connection.get(1);
            Server startNode = servers[start];
            if (startNode == null) {
                startNode = new Server(start);
                servers[start] = startNode;
            }
            Server endNode = servers[end];
            if (endNode == null) {
                endNode = new Server(end);
                servers[end] = endNode;
            }
            startNode.connections.add(endNode);
            endNode.connections.add(startNode);
        }
        return servers;
    }
}
