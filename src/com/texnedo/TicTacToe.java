package com.texnedo;

import java.util.Locale;
import java.util.Random;

public class TicTacToe {
    private final int size;
    private final int [][] board;
    private final int [][] iLines;
    private final int [][] jLines;
    private final int [] dCounts;
    private int winner = 0;
    private int lastPlayer = 0;

    public TicTacToe(int size) {
        this.size = size;
        this.board = new int[size][size];
        this.iLines = new int[2][size];
        this.jLines = new int[2][size];
        this.dCounts = new int[2];
    }

    public static void main(String[] args) {
        TicTacToe toe = new TicTacToe(4);
        int result = 0;
        int player = 1;
        Random rnd = new Random();
        int stepCount = 0;
        while (result == 0 && stepCount < toe.size * toe.size) {
            int ri = rnd.nextInt(toe.size);
            int rj = rnd.nextInt(toe.size);
            try {
                result = toe.move(ri, rj, player);
                player = player == 1 ? 2 : 1;
                toe.print();
                stepCount++;
            } catch (IllegalStateException ex) {
                System.out.println(String.format(
                            Locale.US,
                            "Failed to use %d, %d location for player %d", ri, rj, player
                        )
                );
            }
        }
        if (result == 0) {
            System.out.println("There is no winner");
        } else {
            System.out.println("The winner is " + result);
        }
    }

    public int move(int i, int j, int player) {
        if (player != 1 && player != 2) {
            throw new IllegalArgumentException();
        }
        if (board[i][j] != 0 || lastPlayer == player) {
            throw new IllegalStateException();
        }
        if (winner != 0) {
            return winner;
        }
        board[i][j] = player;
        if (i == j) {
            dCounts[player - 1]++;
        }
        iLines[player - 1][i]++;
        jLines[player - 1][j]++;
        winner = checkWinner();
        lastPlayer = player;
        return winner;
    }

    public void print() {
        for (int j = 0; j < size; j++) {
            System.out.print("===");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 1) {
                    System.out.print(" X ");
                } else if (board[i][j] == 2) {
                    System.out.print(" O ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        for (int j = 0; j < size; j++) {
            System.out.print("===");
        }
        System.out.println();
    }

    private int checkWinner() {
        if (dCounts[0] == size) {
            return 1;
        }
        if (dCounts[1] == size) {
            return 2;
        }
        for (int index = 0; index < size; index++) {
            if (iLines[0][index] == size) {
                return 1;
            }
            if (jLines[1][index] == size) {
                return 2;
            }
        }
        return 0;
    }
}
