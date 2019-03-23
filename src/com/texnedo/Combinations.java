package com.texnedo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Combinations {

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        System.out.println(combinations.combine(4, 2));
    }

    /**
     * Given two integers n and k, return all possible
     * combinations of k numbers out of 1 ... n.
     * */
    public List<List<Integer>> combine(int n, int k) {
        Set<Set<Integer>> result = combineInternal(n, k);
        List<List<Integer>> converted = new ArrayList<>(result.size());
        for (Set<Integer> set : result) {
            converted.add(new ArrayList<>(set));
        }
        return converted;
    }

    private Set<Set<Integer>> combineInternal(int n, int k) {
        if (k == 1) {
            Set<Set<Integer>> result = new HashSet<>(n);
            for (int i = 1; i <= n; i++) {
                HashSet<Integer> set = new HashSet<>();
                set.add(i);
                result.add(set);
            }
            return result;
        }
        Set<Set<Integer>> previous = combineInternal(n, k - 1);
        Set<Set<Integer>> result = new HashSet<>(n);
        for (Set<Integer> set : previous) {
            for (int i = 1; i <= n; i++) {
                if (!set.contains(i)) {
                    HashSet<Integer> superSet = new HashSet<>(set);
                    superSet.add(i);
                    if (!result.contains(superSet)) {
                        result.add(superSet);
                    }
                }
            }
        }
        return result;
    }
}
