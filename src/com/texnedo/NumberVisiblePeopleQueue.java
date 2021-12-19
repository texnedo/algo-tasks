package com.texnedo;

import java.util.Stack;

public class NumberVisiblePeopleQueue {
    public int[] canSeePersonsCount(int[] heights) {
        Stack<Integer> heightStack = new Stack<>();
        int[] result = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!heightStack.isEmpty() && heightStack.peek() < heights[i]) {
                int index = heightStack.pop();
                result[index]++;
            }
            result[i] = heightStack.isEmpty() ? 0 : 1;
            heightStack.push(i);
        }
        return result;
    }
}
