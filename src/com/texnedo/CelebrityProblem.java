package com.texnedo;

import java.util.Stack;

public class CelebrityProblem {
    public static void main(String[] args) {
        CelebrityProblem problem = new CelebrityProblem();
        int[][] data = {
                { 0, 0, 1, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 1, 0 }
        };
        System.out.println(problem.getIdFast(data));
    }

    static boolean knows(int[][] data, int a, int b) {
        return data[a][b] == 1;
    }

    int getIdBruteForce(int[][] data) {
        int[] incomingConnections = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (knows(data, i, j)) {
                    incomingConnections[j]++;
                }
            }
        }
        int maxCount = 0;
        int maxId = -1;
        for (int i = 0; i < incomingConnections.length; i++) {
            if (maxCount < incomingConnections[i]) {
                maxCount = incomingConnections[i];
                maxId = i;
            }
        }
        if (maxId == -1 || maxCount < data.length - 1) {
            return -1;
        }
        return maxId;
    }

    int getIdFast(int[][] data) {
        Stack<Integer> persons = new Stack<>();
        for (int i = 0; i < data.length; i++) {
            persons.push(i);
        }
        while (persons.size() > 1) {
            int i = persons.pop();
            int j = persons.pop();
            if (knows(data, i, j)) {
                persons.push(j);
            } else {
                persons.push(i);
            }
        }
        for (int i = 0; i < data.length; i++) {
            if (persons.peek() != i && !knows(data, i, persons.peek())) {
                return -1;
            }
        }
        return persons.peek();
    }
}
