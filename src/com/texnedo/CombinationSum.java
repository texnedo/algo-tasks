package com.texnedo;

import java.util.*;

public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum sum = new CombinationSum();
        int[] data = {2,3,5};
        System.out.println(sum.combinationSum(data, 8));
    }

    public List<List<Integer>> combinationSum(int[] candidates,
                                              int target) {
        Arrays.sort(candidates);
        HashMap<Integer, HashSet<Integer>> checkedTargets = new HashMap<>();
        return combinationSumInternal(
                candidates,
                target,
                Collections.emptyList(),
                checkedTargets
        );
    }

    public List<List<Integer>> combinationSumInternal(int[] candidates,
                                                      int target,
                                                      List<Integer> combination,
                                                      HashMap<Integer, HashSet<Integer>> checkedTargets) {
        HashSet<Integer> existing = checkedTargets.get(target);
        if (existing != null && existing.contains(combination.hashCode())) {
            return Collections.emptyList();
        }
        final HashSet<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < candidates.length; i++) {
            final List<Integer> currentCombination = new LinkedList<>(combination);
            currentCombination.add(candidates[i]);
            int diff = target - candidates[i];
            if (diff < 0) {
                //negative, don't add to the final list
                break;
            }
            if (diff == 0) {
                //positive, add to the final list
                ArrayList<Integer> foundCombination = new ArrayList<>(currentCombination);
                Collections.sort(foundCombination);
                result.add(foundCombination);
                break;
            }
            Collections.sort(currentCombination);
            result.addAll(combinationSumInternal(candidates, diff, currentCombination, checkedTargets));
        }
        if (existing == null) {
            existing = new HashSet<>();
        }
        existing.add(combination.hashCode());
        checkedTargets.put(target, existing);
        return new ArrayList<>(result);
    }
}
