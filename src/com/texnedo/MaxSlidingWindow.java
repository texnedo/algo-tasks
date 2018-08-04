package com.texnedo;

import java.util.LinkedList;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        //fill the first window
        for (int i = 0; i < k; i++) {
            while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }
        //
        for (int i = k, j = 0; i < nums.length; i++, j++) {
            result[j] = nums[queue.peekFirst()];
            while(!queue.isEmpty() && queue.peekFirst() <= i - k) {
                queue.removeFirst();
            }
            while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }
        result[result.length - 1] = nums[queue.peekFirst()];
        return result;
    }
}
