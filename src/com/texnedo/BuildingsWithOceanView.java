package com.texnedo;

import java.util.Arrays;
import java.util.Stack;

public class BuildingsWithOceanView {
    public int[] findBuildings(int[] heights) {
        if (heights == null) {
            throw new IllegalArgumentException();
        }
        if (heights.length == 0) {
            return new int[] {};
        }
        int maxBefore = 0;
        Stack<Integer> indexesWithView = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > maxBefore) {
                indexesWithView.push(i);
                maxBefore = heights[i];
            }
        }
        int[] result = new int[indexesWithView.size()];
        int i = 0;
        while (!indexesWithView.isEmpty()) {
            result[i] = indexesWithView.pop();
            i++;
        }
        return result;
    }
}
