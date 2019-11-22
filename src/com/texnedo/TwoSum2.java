package com.texnedo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TwoSum2 {
    public static void main(String[] args) {
        TwoSum2 sum = new TwoSum2();
        int[] data = {2,7,11,15};
        System.out.println(Arrays.toString(sum.twoSum(data, 9)));
        int[] data1 = {1,2,3,4,4,9,56,90};
        System.out.println(Arrays.toString(sum.twoSum(data1, 8)));
    }

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < numbers.length; i++) {
            int goal = target - numbers[i];
            int index = Arrays.binarySearch(numbers, goal);
            if (index < 0 || index == i) {
                continue;
            }
            int left = i + 1;
            int right = index + 1;
            return new int[] {Math.min(left, right), Math.max(left, right)};
        }
        throw new IllegalArgumentException();
    }
}
