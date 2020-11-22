package com.texnedo;

public class FriendCircles {
    public static void main(String[] args) {
        FriendCircles circles = new FriendCircles();
        int[][] data = {
                {1,1,0},
                {1,1,0},
                {0,0,1}
        };
        System.out.println(circles.findCircleNum(data));
        int[][] data1 = {
                {1,1,0},
                {1,1,1},
                {0,1,1}
        };
        System.out.println(circles.findCircleNum(data1));
    }

    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M.length != M[0].length) {
            throw new IllegalArgumentException();
        }
        boolean[][] visited = new boolean[M.length][M[0].length];
        int circleCount = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i][i]) {
                detectCircleDFS(M, visited, i);
                circleCount++;
            }
        }
        return circleCount;
    }

    private void detectCircleDFS(int[][] M, boolean[][] visited, int i) {
        //loop though ith friends
        for (int j = 0; j < M[i].length; j++) {
            if (M[i][j] == 1 && !visited[i][j]) {
                visited[i][j] = true;
                detectCircleDFS(M, visited, j);
            }
        }
    }
}
