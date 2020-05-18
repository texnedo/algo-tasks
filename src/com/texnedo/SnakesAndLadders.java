package com.texnedo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class SnakesAndLadders {
    public static void main(String[] args) {
        int[][] data = {
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        };
        SnakesAndLadders snakesAndLadders = new SnakesAndLadders();
        System.out.println(Arrays.toString(snakesAndLadders.getPositionFromSquare(data, 1)));
        System.out.println(Arrays.toString(snakesAndLadders.getPositionFromSquare(data, 17)));
        System.out.println(Arrays.toString(snakesAndLadders.getPositionFromSquare(data, 22)));
        System.out.println(Arrays.toString(snakesAndLadders.getPositionFromSquare(data, 36)));
        System.out.println(Arrays.toString(snakesAndLadders.getPositionFromSquare(data, 18)));
        System.out.println(snakesAndLadders.snakesAndLadders(data));
        int[][] data1 = {
                {1,1,-1},
                {1,1,1},
                {-1,1,1}
        };
        System.out.println(snakesAndLadders.snakesAndLadders(data1));
    }

    private static class Path {
        final int moves;
        final int square;
        final boolean[] visited;

        private Path(int moves, int square, int finalSquare) {
            this.moves = moves;
            this.square = square;
            this.visited = new boolean[finalSquare];
            this.visited[square - 1] = true;
        }

        private Path(int moves, int square, boolean[] alreadyVisited) {
            this.moves = moves;
            this.square = square;
            this.visited = Arrays.copyOf(alreadyVisited, alreadyVisited.length);
            this.visited[square - 1] = true;
        }
    }

    public int snakesAndLadders(int[][] board) {
        return snakesAndLadders(board, 1);
    }

    public int snakesAndLadders(int[][] board, int square) {
        int finalSquare = board.length * board.length;
        LinkedList<Path> paths = new LinkedList<>();
        paths.add(new Path(0, square, finalSquare));
        while (!paths.isEmpty()) {
            LinkedList<Path> nextPaths = new LinkedList<>();
            for (Path path : paths) {
                for (int i = 1; i <= 6 && path.square + i <= finalSquare; i++) {
                    int nextSquare = path.square + i;
                    int[] position = getPositionFromSquare(board, nextSquare);
                    int jumpToSquare = board[position[0]][position[1]];
                    if (jumpToSquare != -1) {
                        nextSquare = jumpToSquare;
                    }
                    int pathMoves = path.moves + 1;
                    if (nextSquare == finalSquare) {
                        return pathMoves;
                    }
                    if (path.visited[nextSquare - 1]) {
                        continue;
                    }
                    nextPaths.add(new Path(pathMoves, nextSquare, path.visited));
                }
            }
            paths = nextPaths;
        }
        return -1;
    }

    private int[] getPositionFromSquare(int[][] board, int square) {
        int iOffset = (int) Math.ceil((float)square / board.length);
        int i = board.length - iOffset;
        int jOffset = square % board.length;
        int j;
        if (jOffset == 0) {
            j = (iOffset) % 2 == 0 ? 0 : board.length - 1;
        } else {
            j = (iOffset) % 2 != 0 ? jOffset - 1 : board.length - jOffset;
        }
        return new int[] {i, j};
    }
}
