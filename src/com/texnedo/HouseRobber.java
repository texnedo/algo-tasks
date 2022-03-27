package com.texnedo;

import java.util.HashMap;
import java.util.HashSet;

public class HouseRobber {
    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();
        int[] data = {1, 2, 3, 1};
        System.out.println(robber.rob(data));
    }

    public int rob(int[] nums) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        return Math.max(robInternal(nums, 0, nums.length - 2, cache),
                robInternal(nums, 1, nums.length - 1, cache));
    }

    private int robInternal(int[] nums,
                            int index,
                            int maxIndex,
                            HashMap<Integer, Integer> cache) {
        if (index > maxIndex) {
            return 0;
        }
        Integer existing = cache.get(index);
        if (existing != null) {
            return existing;
        }
        int robIndex = nums[index] + robInternal(nums, index + 2, maxIndex, cache);
        int dontRobIndex = robInternal(nums, index + 1, maxIndex, cache);
        int maxValue = Math.max(robIndex, dontRobIndex);
        cache.put(index, maxValue);
        return maxValue;
    }

    private int robInternal2(int[] nums,
                            HashSet<Integer> availableIndexes,
                            HashMap<String, Integer> cache) {
        if (availableIndexes.isEmpty()) {
            return 0;
        }
        if (availableIndexes.size() == 1) {
            return nums[availableIndexes.iterator().next()];
        }
        String hashCode = availableIndexes.toString();
        Integer existing = cache.get(hashCode);
        if (existing != null) {
            return existing;
        }
        int maxValue = 0;
        for (int index = 0; index < nums.length; index++) {
            if (availableIndexes.contains(index)) {
                availableIndexes.remove(index);
                int leftIndex = getLeftHouseIndex(nums, index);
                int rightIndex = getRightHouseIndex(nums, index);
                boolean hasLeft = availableIndexes.remove(leftIndex);
                boolean hasRight = availableIndexes.remove(rightIndex);
                int currentValue = nums[index] + robInternal2(nums, availableIndexes, cache);
                if (currentValue > maxValue) {
                    maxValue = currentValue;
                }
                availableIndexes.add(index);
                if (hasLeft) {
                    availableIndexes.add(leftIndex);
                }
                if (hasRight) {
                    availableIndexes.add(rightIndex);
                }
            }
        }
        cache.put(hashCode, maxValue);
        return maxValue;
    }

    private static HashSet<Integer> buildAvailable(int[] nums) {
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            result.add(i);
        }
        return result;
    }

    private int getLeftHouseIndex(int[] nums, int index) {
        if (index == 0) {
            return nums.length - 1;
        }
        return index - 1;
    }

    private int getRightHouseIndex(int[] nums, int index) {
        if (index == nums.length - 1) {
            return 0;
        }
        return index + 1;
    }
}
