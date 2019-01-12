package com.texnedo;

import java.util.Stack;

public class MaxHistogramArea {
    public static void main(String[] args) {
        MaxHistogramArea area = new MaxHistogramArea();
        int[] heights = {1, 2, 3, 2, 5 ,3, 2, 1};
        System.out.println(area.maxAreaFast(heights));
    }

    int maxAreaBrutForce(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int currentArea = heights[i];
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[j] >= heights[i]) {
                    currentArea += heights[i];
                } else {
                    break;
                }
            }
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }
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
