package com.texnedo;

import java.util.*;

public class RestoreArrayFromAdjacentPairs {
    public static void main(String[] args) {
        RestoreArrayFromAdjacentPairs pairs = new RestoreArrayFromAdjacentPairs();
        int[][] data = {{2,1},{3,4},{3,2}};
        System.out.println(Arrays.toString(pairs.restoreArray(data)));
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        HashMap<Integer, Set<Integer>> adjacency = new HashMap<>();
        for (int i = 0; i < adjacentPairs.length; i++) {
            Set<Integer> first =
                    adjacency.computeIfAbsent(adjacentPairs[i][0], k -> new HashSet<>());
            first.add(adjacentPairs[i][1]);
            Set<Integer> second =
                    adjacency.computeIfAbsent(adjacentPairs[i][1], k -> new HashSet<>());
            second.add(adjacentPairs[i][0]);
        }
        int[] result = new int[adjacency.size()];
        Integer startNumber = null;
        for (Map.Entry<Integer, Set<Integer>> item : adjacency.entrySet()) {
            if (item.getValue().size() == 1) {
                startNumber = item.getKey();
            }
        }
        if (startNumber == null) {
            throw new IllegalArgumentException();
        }
        result[0] = startNumber;
        for (int i = 1; i < result.length; i++) {
            Set<Integer> linksFirst = adjacency.get(result[i - 1]);
            if (linksFirst.size() != 1) {
                throw new IllegalArgumentException();
            }
            result[i] = linksFirst.iterator().next();
            linksFirst.remove(result[i]);
            Set<Integer> linksSecond = adjacency.get(result[i]);
            linksSecond.remove(result[i - 1]);
        }
        return result;
    }
}
