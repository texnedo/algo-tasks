package com.texnedo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] data = {4,2,0,3,2,5};
        System.out.println(trap(data));
    }

    public static int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int previous = height[stack.pop()];
                if (stack.isEmpty()) {
                    //no more bounded items
                    break;
                }
                int diff = i - stack.peek() - 1;
                int water = Math.min(height[i], height[stack.peek()]) - previous;
                sum += water * diff;
            }
            stack.push(i);
        }
        return sum;
    }

    public static int trapSlow(int[] height) {
        int sum = 0;
        while (true) {
            int start = -1;
            int countZero = 0;
            int countMoreThanZero = 0;
            System.out.println(Arrays.toString(height));
            for (int i = 0; i < height.length; i++) {
                if (height[i] >= 1) {
                    start = i;
                    sum += countZero;
                    countZero = 0;
                    countMoreThanZero++;
                } else if (height[i] == 0 && start >= 0) {
                    countZero++;
                }
                if (height[i] > 0) {
                    height[i]--;
                }
            }
            System.out.println(sum);
            if (countMoreThanZero <= 1) {
                break;
            }
        }
        return sum;
    }
}
