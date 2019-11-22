package com.texnedo;

public class MinimumPathSum {
    public static void main(String[] args) {
        MinimumPathSum sum = new MinimumPathSum();
        int[][] data = {{1,3,1}, {1,5,1}, {4,2,1}};
        System.out.println(sum.minPathSum(data));
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }
        Integer[][] computed = new Integer[grid.length][grid[0].length];
        return minPathSumInternal(grid, 0, 0, computed);
    }

    public int minPathSumInternal(int[][] grid, int i, int j, Integer[][] computed) {
        System.out.println(i + ":" + j);
        if (i >= grid.length || j >= grid[i].length) {
            //not valid option
            return 0;
        }
        if (i == grid.length - 1 && j == grid[i].length - 1) {
            //found the bottom right cell
            return grid[i][j];
        }
        if (computed[i][j] != null) {
            return computed[i][j];
        }
        int downSum = Integer.MAX_VALUE;
        if (i < grid.length - 1) {
            downSum = minPathSumInternal(grid, i + 1, j, computed);
        }
        int rightSum = Integer.MAX_VALUE;
        if (j < grid[i].length - 1) {
            rightSum = minPathSumInternal(grid, i, j + 1, computed);
        }
        if (rightSum == Integer.MAX_VALUE && downSum == Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        int minSum = grid[i][j] + Math.min(downSum, rightSum);
        computed[i][j] = minSum;
        return minSum;
    }
}
