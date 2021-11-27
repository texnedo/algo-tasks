package com.texnedo;

import java.util.*;

public class CombinationSumII {
    public static void main(String[] args) {
        CombinationSumII sum = new CombinationSumII();
        int[] data = {10,1,2,7,6,1,5};
        System.out.println(sum.combinationSum2(data, 8));
        int[] data2 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        //System.out.println(sum.combinationSum2(data2, 27));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        HashSet<List<Integer>> results = new HashSet<>();
        combinationSum2Internal2(
                candidates,
                new HashSet<>(),
                0,
                target,
                results,
                new HashMap<>()
        );
        List<List<Integer>> resultsConverted = new ArrayList<>(results.size());
        resultsConverted.addAll(results);
        return resultsConverted;
    }

    private void combinationSum2Internal(int[] candidates,
                                         HashSet<Integer> used,
                                         int currentSum,
                                         int target,
                                         HashSet<List<Integer>> results,
                                         HashMap<Integer, HashSet<HashSet<Integer>>> existing) {
        if (target <= 0) {
            return;
        }
        HashSet<HashSet<Integer>> additionToUsed = existing.get(target);
        if (additionToUsed != null) {
            for (HashSet<Integer> combination : additionToUsed) {
                HashSet<Integer> joined = new HashSet<>(combination);
                joined.addAll(used);

            }
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (!used.contains(i)) {
                if (candidates[i] <= target) {
                    HashSet<HashSet<Integer>> existingUsed = existing.get(currentSum);
                    if (existingUsed == null) {
                        existingUsed = new HashSet<>();
                        existing.put(currentSum, existingUsed);
                    }
                    existingUsed.add(new HashSet<>(used));
                    System.out.println(currentSum);
                    System.out.println(used);
                    used.add(i);
                    if (candidates[i] == target) {
                        addResultCombination(candidates, used, results);
                    } else {
                        combinationSum2Internal(
                                candidates,
                                used,
                                currentSum + candidates[i],
                                target - candidates[i],
                                results,
                                existing
                        );
                    }
                    used.remove(i);
                }
            }
        }
    }

    private void combinationSum2Internal2(int[] candidates,
                                         HashSet<Integer> used,
                                         int currentSum,
                                         int target,
                                         HashSet<List<Integer>> results,
                                         HashMap<Integer, HashSet<HashSet<Integer>>> existing) {
        if (target < 0) {
            return;
        }
        HashSet<HashSet<Integer>> additionToUsed = existing.get(target);
        if (additionToUsed != null) {
            for (HashSet<Integer> combination : additionToUsed) {
                HashSet<Integer> joined = new HashSet<>(combination);
                joined.addAll(used);
                addResultCombination(candidates, joined, results);
            }
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (!used.contains(i)) {
                if (candidates[i] <= target) {
                    used.add(i);
                    if (candidates[i] == target) {
                        addResultCombination(candidates, used, results);
                    } else {
                        HashSet<HashSet<Integer>> existingUsed = existing.get(currentSum + candidates[i]);
                        if (existingUsed == null) {
                            existingUsed = new HashSet<>();
                            existing.put(currentSum + candidates[i], existingUsed);
                        }
                        existingUsed.add(new HashSet<>(used));
                        combinationSum2Internal2(
                                candidates,
                                used,
                                currentSum + candidates[i],
                                target - candidates[i],
                                results,
                                existing
                        );
                    }
                    used.remove(i);
                }
            }
        }
    }

    private void addResultCombination(int[] candidates,
                                      HashSet<Integer> used,
                                      HashSet<List<Integer>> results) {
        List<Integer> values = new ArrayList<>(used.size());
        for (Integer usedIndex : used) {
            values.add(candidates[usedIndex]);
        }
        values.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        results.add(values);
    }
}
