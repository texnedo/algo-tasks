package com.texnedo;

public class WordSearch {
    private static final int FOUND = Integer.MAX_VALUE;
    private static final int NOT_FOUND = Integer.MIN_VALUE;
    private static final int UP = 1;
    private static final int DOWN = 1 << 1;
    private static final int LEFT = 1 << 2;
    private static final int RIGHT = 1 << 3;

    public static void main(String[] args) {
        WordSearch search = new WordSearch();
//        char[][] board =
//                {
//                        {'A','B','C','E'},
//                        {'S','F','C','S'},
//                        {'A','D','E','E'}
//                };
        char [][] board = {
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}
        };
        System.out.println(search.exist(board, "ABCESEEEFS"));
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) {
            return false;
        }
        if (board.length == 0) {
            return false;
        }
        if (word.length() == 0) {
            return true;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isWordExist(board, i, j, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWordExist(char[][] board, int i, int j, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean result = isWordExistInternal(board, i, j, word, 0, visited);
        System.out.println();
        return result;
    }

    private boolean isWordExistInternal(char[][] board, int i, int j,
                                        String word, int index, boolean[][] visited) {
        if (visited[i][j]) {
            return false;
        }
        System.out.print(board[i][j]);
        System.out.print(String.format("(%d)->", index));
        int ways = getPossibleWays(board, i, j, word, index);
        switch (ways) {
            case NOT_FOUND: {
                return false;
            }
            case FOUND: {
                return true;
            }
        }
        visited[i][j] = true;
        if ((ways & UP) == UP) {
            if (isWordExistInternal(board, i - 1, j, word, index + 1, visited)) {
                return true;
            }
        }
        if ((ways & DOWN) == DOWN) {
            if (isWordExistInternal(board, i + 1, j, word, index + 1, visited)) {
                return true;
            }
        }
        if ((ways & LEFT) == LEFT) {
            if (isWordExistInternal(board, i, j - 1, word, index + 1, visited)) {
                return true;
            }
        }
        if ((ways & RIGHT) == RIGHT) {
            if (isWordExistInternal(board, i, j + 1, word, index + 1, visited)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

    private int getPossibleWays(char[][] board, int i, int j, String word, int index) {
        if (index >= word.length()) {
            throw new IllegalStateException();
        }
        char current = board[i][j];
        if (current != word.charAt(index)) {
            return NOT_FOUND;
        }
        if (index == word.length() - 1) {
            return FOUND;
        }
        int ways = 0;
        if (i < board.length - 1) {
            ways |= DOWN;
        }
        if (i > 0) {
            ways |= UP;
        }
        if (j < board[i].length - 1) {
            ways |= RIGHT;
        }
        if (j > 0) {
            ways |= LEFT;
        }
        return ways;
    }
}
