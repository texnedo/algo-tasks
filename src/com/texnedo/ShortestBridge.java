package com.texnedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShortestBridge {
    public static void main(String[] args) {
        ShortestBridge bridge = new ShortestBridge();
        int[][] data = {
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,1,0,1},
                {1,0,0,0,1},
                {1,1,1,1,1}
        };
        int[][] data1 = {
                {0,1,0},
                {0,0,0},
                {0,0,1}
        };
        int[][] data2 = {
                {1,0,0},
                {0,0,0},
                {0,0,1}
        };
        System.out.println(bridge.shortestBridge(data));
        System.out.println(bridge.shortestBridge(data1));
        System.out.println(bridge.shortestBridge(data2));
    }

    public int shortestBridge(int[][] A) {
        if (A == null) {
            throw new IllegalArgumentException();
        }
        if (A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int[][] visited = new int[A.length][A[0].length];
        List<List<int[]>> islands = new ArrayList<>(2);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                List<int[]> island = detectIsland(A, visited, i, j, islands.size() + 1);
                if (!island.isEmpty()) {
                    islands.add(island);
                }
            }
        }
        if (islands.size() != 2) {
            throw new IllegalArgumentException();
        }
        List<int[]> minSizeIsland = islands.get(0).size() < islands.get(1).size()
                ? islands.get(0) : islands.get(1);
        int minSizeIslandIndex = islands.get(0).size() < islands.get(1).size() ? 1 : 2;
        int maxSizeIslandIndex = islands.get(0).size() < islands.get(1).size() ? 2 : 1;
        int levelCount = 0;
        List<int[]> level = new LinkedList<>(minSizeIsland);
        while (!level.isEmpty()) {
            List<int[]> nextLevel = new LinkedList<>();
            for (int[] item : level) {
                if (visited[item[0]][item[1]] == 0) {
                    visited[item[0]][item[1]] = -1;
                }
                List<int[]> connected = getAll(A, item[0], item[1]);
                for (int[] connection : connected) {
                    int status = visited[connection[0]][connection[1]];
                    if (status == -1 || status == minSizeIslandIndex) {
                        continue;
                    }
                    if (status == maxSizeIslandIndex) {
                        return levelCount;
                    }
                    nextLevel.add(connection);
                    visited[connection[0]][connection[1]] = -1;
                }
            }
            level = nextLevel;
            levelCount++;
        }
        return levelCount;
    }

    private List<int[]> detectIsland(int[][] A, int[][] visited, int i, int j, int islandIndex) {
        if (A[i][j] == 0) {
            return Collections.emptyList();
        }
        if (A[i][j] == 1 && visited[i][j] != 0) {
            return Collections.emptyList();
        }
        int[] current = new int[] {i, j};
        List<int[]> scc = new ArrayList<>(20);
        List<int[]> level = new LinkedList<>();
        level.add(current);
        while (!level.isEmpty()) {
            List<int[]> nextLevel = new LinkedList<>();
            for (int[] item : level) {
                scc.add(item);
                visited[item[0]][item[1]] = islandIndex;
                List<int[]> connected = getConnected(A, item[0], item[1]);
                for (int[] connection : connected) {
                    if (visited[connection[0]][connection[1]] == 0) {
                        nextLevel.add(connection);
                    }
                }
            }
            level = nextLevel;
        }
        return scc;
    }

    private List<int[]> getConnected(int[][] A, int i, int j) {
        if (A[i][j] == 0) {
            return Collections.emptyList();
        }
        List<int[]> result = new ArrayList<>(4);
        if (i < A.length - 1 && A[i + 1][j] == 1) {
            result.add(new int[]{i + 1, j});
        }
        if (i > 0 && A[i - 1][j] == 1) {
            result.add(new int[]{i - 1, j});
        }
        if (j < A[i].length - 1 && A[i][j + 1] == 1) {
            result.add(new int[]{i, j + 1});
        }
        if (j > 0 && A[i][j - 1] == 1) {
            result.add(new int[]{i, j - 1});
        }
        return result;
    }

    private List<int[]> getAll(int[][] A, int i, int j) {
        List<int[]> result = new ArrayList<>(4);
        if (i < A.length - 1) {
            result.add(new int[]{i + 1, j});
        }
        if (i > 0) {
            result.add(new int[]{i - 1, j});
        }
        if (j < A[i].length - 1) {
            result.add(new int[]{i, j + 1});
        }
        if (j > 0) {
            result.add(new int[]{i, j - 1});
        }
        return result;
    }
}
