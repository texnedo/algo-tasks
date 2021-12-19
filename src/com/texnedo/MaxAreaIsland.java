package com.texnedo;

import java.util.LinkedList;
import java.util.List;

public class MaxAreaIsland {
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int currentArea = runDfs(grid, i, j, visited);
                if (currentArea > maxArea) {
                    maxArea = currentArea;
                }
            }
        }
        return maxArea;
    }

    private int runDfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (grid[i][j] == 0) {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }
        List<int[]> level = new LinkedList<>();
        level.add(new int[] {i, j});
        int area = 0;
        while(!level.isEmpty()) {
            List<int[]> nextLevel = new LinkedList<>();
            for (int[] point : level) {
                if (point[0] >= 0 && point[0] < grid.length && point[1] >= 0 && point[1] < grid[0].length) {
                    if (!visited[point[0]][point[1]] && grid[point[0]][point[1]] == 1) {
                        area++;
                        visited[point[0]][point[1]] = true;
                        nextLevel.add(new int[]{point[0] - 1, point[1]});
                        nextLevel.add(new int[]{point[0] + 1, point[1]});
                        nextLevel.add(new int[]{point[0], point[1] - 1});
                        nextLevel.add(new int[]{point[0], point[1] + 1});
                    }
                }
            }
            level.clear();
            level.addAll(nextLevel);
            nextLevel.clear();
        }
        return area;
    }
}
