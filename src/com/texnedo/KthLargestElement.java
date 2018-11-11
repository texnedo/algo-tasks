package com.texnedo;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestElement {
    public static void main(String[] args) {
        KthLargestElement element = new KthLargestElement();
        int[] data = {3};
        System.out.println(element.findKthLargest(data, 1));
    }

    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length) {
            throw new IllegalArgumentException();
        }
        final PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
        }
        for (int i = 0; i < k - 1; i++) {
            heap.poll();
        }
        return heap.poll();
    }
}
