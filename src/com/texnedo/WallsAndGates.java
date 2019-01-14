package com.texnedo;

import com.texnedo.utils.Matrix;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WallsAndGates {
    private static int INF = Integer.MAX_VALUE;

    private static class Node {
        final int i;
        final int j;

        private Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF,}
        };
        Matrix.print(grid);
        WallsAndGates gates = new WallsAndGates();
        gates.measure(grid);
        Matrix.print(grid);
    }

    public void measure(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //look for gates and start BFS from each point
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                }
            }
        }
    }

    private List<Node> getUnComputedNodes(int[][] grid, int i, int j) {
        LinkedList<Node> nodes = new LinkedList<>();
        if (i > 0) {
            if (grid[i - 1][j] > 0) {
                nodes.add(new Node(i - 1, j));
            }
        }
        if (i < grid.length - 1) {
            if (grid[i + 1][j] > 0) {
                nodes.add(new Node(i + 1, j));
            }
        }
        if (j > 0) {
            if (grid[i][j - 1] > 0) {
                nodes.add(new Node(i, j - 1));
            }
        }
        if (j < grid[i].length - 1) {
            if (grid[i][j + 1] > 0) {
                nodes.add(new Node(i, j + 1));
            }
        }
        return nodes;
    }

    private void dfs(int[][] grid, int i, int j) {
        Queue<Node> level = new LinkedList<>();
        Queue<Node> nextLevel = new LinkedList<>();
        int[][] visited = new int[grid.length][grid[0].length];
        level.addAll(getUnComputedNodes(grid, i, j));
        int distance = 1;
        while (!level.isEmpty()) {
            while (!level.isEmpty()) {
                Node current = level.poll();
                if (current == null || visited[current.i][current.j] != 0) {
                    continue;
                }
                if (grid[current.i][current.j] > distance) {
                    grid[current.i][current.j] = distance;
                }
                nextLevel.addAll(getUnComputedNodes(grid, current.i, current.j));
                visited[current.i][current.j] = 1;
            }
            level.addAll(nextLevel);
            nextLevel.clear();
            distance++;
        }
    }
}
