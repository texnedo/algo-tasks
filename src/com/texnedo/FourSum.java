package com.texnedo;

import java.util.*;

public class FourSum {
    public static void main(String[] args) {
        FourSum sum = new FourSum();
        int[] data = {1, 0, -1, 0, -2, 2};
        System.out.println(sum.fourSum(data, 0));
        int[] data1 = {0, 0, 0, 0};
        System.out.println(sum.fourSum(data1, 0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }
        HashMap<Integer, Map<Integer, List<Integer>>> twoSums = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                Map<Integer, List<Integer>> result = twoSums.get(sum);
                if (result == null) {
                    result = new HashMap<>();
                    twoSums.put(sum, result);
                }
                List<Integer> pair = new ArrayList<>(2);
                pair.add(Math.min(i, j));
                pair.add(Math.max(i, j));
                result.put(pair.hashCode(), pair);
            }
        }
        HashMap<Integer, List<Integer>> uniqList = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, List<Integer>>> item : twoSums.entrySet()) {
            int diff = target - item.getKey();
            Map<Integer, List<Integer>> pairs = twoSums.get(diff);
            if (pairs != null){
                for (List<Integer> left : pairs.values()) {
                    for (List<Integer> right : item.getValue().values()) {
                        HashSet<Integer> indexes = new HashSet<>();
                        indexes.addAll(left);
                        indexes.addAll(right);
                        if (indexes.size() == 4) {
                            List<Integer> compound = new ArrayList<>(4);
                            for (Integer index : left) {
                                compound.add(nums[index]);
                            }
                            for (Integer index : right) {
                                compound.add(nums[index]);
                            }
                            Collections.sort(compound);
                            uniqList.put(compound.hashCode(), compound);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(uniqList.values());
    }
}
