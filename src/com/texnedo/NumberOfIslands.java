package com.texnedo;

public class NumberOfIslands {

    public static void main(String[] arr) {
        NumberOfIslands islands = new NumberOfIslands();
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(islands.numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    traverseLand(i, j, grid, visited);
                    count++;
                }
            }
        }
        return count;
    }

    public void traverseLand(int i, int j, char[][] grid, boolean[][] visited) {
        if (visited[i][j] || grid[i][j] != '1') {
            return;
        }
        visited[i][j] = true;
        if (i > 0 && grid[i - 1][j] == '1') {
            traverseLand(i - 1, j, grid, visited);
        }
        if (i < grid.length - 1 && grid[i + 1][j] == '1') {
            traverseLand(i + 1, j, grid, visited);
        }
        if (j > 0 && grid[i][j - 1] == '1') {
            traverseLand(i, j - 1, grid, visited);
        }
        if (j < grid[i].length - 1 && grid[i][j + 1] == '1') {
            traverseLand(i, j + 1, grid, visited);
        }
    }
}
