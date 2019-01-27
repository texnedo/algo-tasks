package com.texnedo;

import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle {
    public static void main(String[] args) {
        MaximalRectangle area = new MaximalRectangle();
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(area.maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        if (matrix.length == 0) {
            return 0;
        }
        if (matrix[0].length == 0) {
            return 0;
        }
        int[] previous = new int[matrix[0].length];
        int[] current = new int[matrix[0].length];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    current[j] = previous[j] + 1;
                }
            }
            int currentMaxArea = maxAreaFast(current);
            if (currentMaxArea > maxArea) {
                maxArea = currentMaxArea;
            }
            int[] tmp = previous;
            previous = current;
            current = tmp;
            Arrays.fill(current, 0);
        }
        return maxArea;
    }

    int maxAreaFast(int[] heights) {
        Stack<Integer> indexes = new Stack<>();
        int maxArea = 0;
        int currentIndex = 0;
        while (currentIndex < heights.length) {
            if (indexes.size() == 0 || heights[indexes.peek()] < heights[currentIndex]) {
                indexes.push(currentIndex);
                currentIndex++;
            } else {
                int areaWithCurrentMin = getAreaWithCurrentMin(heights, indexes, currentIndex);
                if (areaWithCurrentMin > maxArea) {
                    maxArea = areaWithCurrentMin;
                }
            }
        }
        while (!indexes.empty()) {
            int areaWithCurrentMin = getAreaWithCurrentMin(heights, indexes, currentIndex);
            if (areaWithCurrentMin > maxArea) {
                maxArea = areaWithCurrentMin;
            }
        }
        return maxArea;
    }

    private int getAreaWithCurrentMin(int[] heights, Stack<Integer> indexes, int currentIndex) {
        int maxIndex = indexes.pop();
        int leftMinIndex = indexes.size() == 0 ? -1 : indexes.peek();
        int rightMinIndex = currentIndex;
        return heights[maxIndex] * (rightMinIndex - leftMinIndex - 1);
    }
}
