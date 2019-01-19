package com.texnedo;

import java.util.LinkedList;
import java.util.List;

public class CountRectangles {
    public static void main(String[] args) {
        CountRectangles rect = new CountRectangles();
        int[][] data = {
                {1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1}
        };
        System.out.println(rect.countRectangles(data));
        int[][] data1 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        System.out.println(rect.countRectangles(data1));
        int[][] data2 = {
                {1, 1, 1}
        };
        System.out.println(rect.countRectangles(data2));
    }

    public int countRectangles(int[][] data) {
        int[][] counts = new int[data[0].length][data[0].length];
        int total = 0;
        for (int i = 0; i < data.length; i++) {
            total += processRow(data[i], counts);
        }
        return total;
    }

    private int processRow(int[] row, int[][] counts) {
        int count = 0;
        List<Integer> start = new LinkedList<>();
        for (int j = 0; j < row.length; j++) {
            if (row[j] == 1) {
                for (Integer s : start) {
                    count += counts[s][j]++;
                }
                start.add(j);
            }
        }
        return count;
    }
}
