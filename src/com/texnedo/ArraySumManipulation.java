package com.texnedo;

import java.util.*;

public class ArraySumManipulation {
    static class Query {
        final List<Integer> originalQuery;
        final boolean isStart;
        final int value;

        Query(List<Integer> originalQuery, boolean isStart, int value) {
            this.originalQuery = originalQuery;
            this.isStart = isStart;
            this.value = value;
        }
    }

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        ArrayList<Query> indexes = new ArrayList<>(queries.size());
        for (List<Integer> item : queries) {
            indexes.add(new Query(item, true, item.get(0)));
            indexes.add(new Query(item, false, item.get(1)));
        }
        indexes.sort(new Comparator<Query>() {
            @Override
            public int compare(Query o1, Query o2) {
                return Integer.compare(o1.value, o2.value);
            }
        });
        Stack<Query> intersections = new Stack<>();
        long maxSum = 0;
        long currentSum = 0;
        Query previous = null;
        for (Query query : indexes) {
            if (intersections.isEmpty()) {
                intersections.add(query);
                currentSum += query.originalQuery.get(2);
            } else {
                if (intersections.peek().isStart && !query.isStart) {
                    Query opened = intersections.pop();
                    currentSum -= opened.originalQuery.get(2);
                } else {
                    intersections.add(query);
                    currentSum += query.originalQuery.get(2);
                    if (query.value == previous.value) {
                        long allSum = currentSum + previous.originalQuery.get(2);
                        if (allSum > maxSum) {
                            maxSum = allSum;
                        }
                    }
                }
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            previous = query;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        List<List<Integer>> data = new ArrayList<>();
        data.add(Arrays.asList(1, 5, 3));
        data.add(Arrays.asList(4, 8, 7));
        data.add(Arrays.asList(6, 9, 1));
        System.out.println(arrayManipulation(10, data));
    }
}
