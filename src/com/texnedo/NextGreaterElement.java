package com.texnedo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Stack<Integer> greaterStack = new Stack<>();
        HashMap<Integer, Integer> nums1Map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            nums1Map.put(nums1[i], i);
        }
        for (int i = nums2.length - 1; i >= 0; i--) {
            Integer nums1Index = nums1Map.get(nums2[i]);
            if (nums1Index != null) {
                if (greaterStack.empty()) {
                    result[nums1Index] = -1;
                } else {
                    while (!greaterStack.empty() && nums2[i] > greaterStack.peek()) {
                        greaterStack.pop();
                    }
                    if (greaterStack.empty()) {
                        result[nums1Index] = -1;
                    } else {
                        result[nums1Index] = greaterStack.peek();
                    }
                }
            }
            greaterStack.push(nums2[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        NextGreaterElement element = new NextGreaterElement();
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        System.out.println(Arrays.toString(element.nextGreaterElement(nums1, nums2)));
    }
}
