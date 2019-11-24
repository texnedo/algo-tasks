package com.texnedo;

public class BattleshipsInBoard {
    public static void main(String[] args) {
        BattleshipsInBoard board = new BattleshipsInBoard();
        String boardData =
                "X..X\n" +
                "...X\n" +
                "...X";
        System.out.println(board.countBattleships(parseBoard(boardData)));
        String boardData2 =
                "X..X\n" +
                "...X\n" +
                "X..X";
        System.out.println(board.countBattleships(parseBoard(boardData2)));
    }

    private static char[][] parseBoard(String board) {
        String[] tokens = board.split("\n");
        char[][] data = new char[tokens.length][tokens[0].length()];
        for (int i = 0; i < tokens.length; i++) {
            for (int j = 0; j < tokens[i].length(); j++) {
                data[i][j] = tokens[i].charAt(j);
            }
        }
        return data;
    }

    public int countBattleships(char[][] board) {
        int totalCount = 0;
        for (int i = 1; i <= board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean hasSpaceUnder = i == board.length || board[i][j] == '.';
                boolean isFirstShipField =
                        board[i - 1][j] == 'X' && (j == 0 || board[i - 1][j - 1] != 'X');
                if (isFirstShipField && hasSpaceUnder) {
                    totalCount++;
                }
            }
        }
        return totalCount;
    }
}
