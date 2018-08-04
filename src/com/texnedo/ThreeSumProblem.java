package com.texnedo;

import java.util.*;

public class ThreeSumProblem {
    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Set<Integer>> numsMap = new HashMap<>();
        List<List<Integer>> result = new LinkedList<>();
        Set<Integer> solutions = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> indexSet = numsMap.get(nums[i]);
            if (indexSet == null) {
                indexSet = new HashSet<>();
                numsMap.put(nums[i], indexSet);
            }
            indexSet.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    int sum = nums[i] + nums[j];
                    Set<Integer> indexSet = numsMap.get(-sum);
                    if (indexSet == null) {
                        continue;
                    }
                    boolean hasi = indexSet.contains(i);
                    boolean hasj = indexSet.contains(j);
                    boolean invalid = hasi && hasj && indexSet.size() == 2;
                    if (invalid) {
                        continue;
                    }
                    invalid = (hasi || hasj) && indexSet.size() == 1;
                    if (invalid) {
                        continue;
                    }
                    ArrayList<Integer> solution = new ArrayList<>(3);
                    solution.add(nums[i]);
                    solution.add(nums[j]);
                    solution.add(-sum);
                    Collections.sort(solution);
                    int solutionId = solution.hashCode();
                    if (!solutions.contains(solutionId)) {
                        result.add(solution);
                        solutions.add(solutionId);
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        Set<Integer> solutions = new HashSet<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (end > start) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == 0) {
                    ArrayList<Integer> solution = new ArrayList<>(3);
                    solution.add(nums[i]);
                    solution.add(nums[start]);
                    solution.add(nums[end]);
                    int solutionId = solution.hashCode();
                    if (!solutions.contains(solutionId)) {
                        result.add(solution);
                        solutions.add(solutionId);
                    }
                    end--;
                    start++;
                } else if (sum > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return result;
    }
}
