package com.texnedo;

import java.util.Locale;
import java.util.Stack;

public class OneTwoThreePattern {

    /**
     * https://en.wikipedia.org/wiki/Stack-sortable_permutation
     * */

    public static void main(String[] args) {
        OneTwoThreePattern pattern = new OneTwoThreePattern();
        int[] data = {1, 3, 2};
        System.out.println(pattern.isStackSortable(data));
        System.out.println(pattern.find132pattern(data));
    }

    public boolean find132pattern(int[] nums) {
        return isStackSortableReversed(nums);
    }

    private boolean isStackSortable(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int previous = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                int current = stack.pop();
                if (current < previous) {
                    return false;
                }
                previous = current;
            }
            stack.push(nums[i]);
        }
        return stack.isEmpty() || previous < stack.peek();
    }

    private boolean isStackSortableReversed(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int previous = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                int current = stack.pop();
                if (current < previous) {
                    return true;
                }
                previous = current;
            }
            stack.push(nums[i]);
        }
        return !stack.isEmpty() && previous > stack.peek();
    }
}
