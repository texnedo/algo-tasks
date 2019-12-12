package com.texnedo;

public class CountServersThatCommunicate {
    public static void main(String[] args) {
        CountServersThatCommunicate communicate = new CountServersThatCommunicate();
        int[][] data = {{1,1,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};
        System.out.println(communicate.countServers(data));
    }

    public int countServers(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int serverCount = 0;
        int[] rowServerCount = new int[grid.length];
        int[] columnServerCount = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    serverCount++;
                    rowServerCount[i]++;
                    columnServerCount[j]++;
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && rowServerCount[i] == 1 && columnServerCount[j] == 1) {
                    serverCount--;
                }
            }
        }
        return serverCount;
    }
}
