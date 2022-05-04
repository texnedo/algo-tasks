package com.texnedo;

import java.util.HashMap;

public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        int[] data = {0, 0, 0, 0, 0};
        System.out.println(numSubarraysWithSum(data, 0));
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] prefix = new int[nums.length + 1];
        prefix[0] = 0;
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        int result = 0;
        for (int i = 0; i < prefix.length; i++) {
            int diff = prefix[i] - goal;
            Integer existing = map.get(diff);
            if (existing != null) {
                result += existing;
            }
            Integer value = map.get(prefix[i]);
            if (value != null) {
                map.put(prefix[i], value + 1);
            } else {
                map.put(prefix[i], 1);
            }
        }
        return result;
    }
}
