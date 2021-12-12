package com.texnedo;

import java.util.*;

public class ShortestPathBinaryMatrix {
    public static void main(String[] args) {
        int[][] data = {{0,1},{1,0}};
        ShortestPathBinaryMatrix matrix = new ShortestPathBinaryMatrix();
        System.out.println(matrix.shortestPathBinaryMatrix(data));
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        if (grid.length == 1 && grid[0].length == 1) {
            return 1;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        List<int[]> layer = new LinkedList<>();
        int[] target = new int[] {grid.length - 1, grid[0].length - 1};
        layer.add(new int[] {0, 0});
        int hopCount = 1;
        while (!layer.isEmpty()) {
            List<int[]> nextLayer = new LinkedList<>();
            for (int[] current : layer) {
                if (Arrays.equals(current, target)) {
                    return hopCount;
                }
                List<int[]> nextToVisit =
                        getAdjacentCells(grid, visited, current[0], current[1]);
                if (!nextToVisit.isEmpty()) {
                    nextLayer.addAll(nextToVisit);
                }
                visited[current[0]][current[1]] = true;
            }
            layer.clear();
            layer.addAll(nextLayer);
            nextLayer.clear();
            hopCount++;
        }
        return -1;
    }

    private List<int[]> getAdjacentCells(int[][] grid, boolean[][] visited, int i, int j) {
        if (visited[i][j]) {
            return Collections.emptyList();
        }
        if (grid[i][j] == 1) {
            return Collections.emptyList();
        }
        List<int[]> result = new ArrayList<>(8);
        for (int ishift = -1; ishift <= 1; ishift++) {
            for (int jshift = -1; jshift <= 1; jshift++) {
                int icurrent = i + ishift;
                int jcurrent = j + jshift;
                boolean iboarder = icurrent >= 0 && icurrent < grid.length;
                boolean jboarder = jcurrent >= 0 && jcurrent < grid[0].length;
                if (iboarder && jboarder &&
                        grid[icurrent][jcurrent] != 1 && !visited[icurrent][jcurrent]) {
                    result.add(new int[] {icurrent, jcurrent});
                }
            }
        }
        return result;
    }
}
