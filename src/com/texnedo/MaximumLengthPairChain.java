package com.texnedo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class MaximumLengthPairChain {
    public static void main(String[] args) {
        MaximumLengthPairChain lengthPairChain = new MaximumLengthPairChain();
        int[][] data = {{1, 5}, {3, 6}, {7, 8}, {9, 10}};
        System.out.println(lengthPairChain.findLongestChain(data));

        int[][] data1 = {{1, 5}, {7, 8}, {9, 15}, {12, 16}};
        System.out.println(lengthPairChain.findLongestChain(data1));

        int[][] data2 = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(lengthPairChain.findLongestChain(data2));

        int[][] data3 = {{1, 8}, {2, 3}, {4, 5}, {9, 10}, {11, 12}};
        System.out.println(lengthPairChain.findLongestChain(data3));
    }

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int lastChainValue = Integer.MIN_VALUE;
        int chainCount = 0;
        for (int i = 0; i < pairs.length; i++) {
            if (lastChainValue < pairs[i][0]) {
                chainCount++;
                lastChainValue = pairs[i][1];
            }
        }
        return chainCount;
    }

    public int findLongestChainFull(int[][] pairs) {
        ArrayList<int[]> ranges = new ArrayList<>(pairs.length * 2);
        for (int i = 0; i < pairs.length; i++) {
            ranges.add(new int[] {pairs[i][0], 1}); //open brace
            ranges.add(new int[] {pairs[i][1], -1}); //close brace
        }
        ranges.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        Stack<int[]> stack = new Stack<>();
        int maxChainLength = 0;
        int currentChainLength = 0;
        int currentLevel = 0;
        int lastChainValue = Integer.MIN_VALUE;
        for (int[] current : ranges) {
            if (stack.isEmpty()) {
                stack.push(current);
            } else {
                int[] previous = stack.peek();
                if (previous[1] == 1 && current[1] == 1) {
                    stack.push(current);
                } else if (previous[1] == 1 && current[1] == -1) {
                    stack.pop();
                    if (currentChainLength == 0) {
                        currentLevel = stack.size();
                        currentChainLength++;
                        lastChainValue = current[0];
                    } else if (currentLevel == stack.size() && lastChainValue < previous[0]) {
                        currentChainLength++;
                    } else {
                        if (currentLevel != stack.size()) {
                            if (currentChainLength > maxChainLength) {
                                maxChainLength = currentChainLength;
                            }
                            currentChainLength = 0;
                            currentLevel = 0;
                            lastChainValue = Integer.MIN_VALUE;
                        }
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
        }
        if (currentChainLength > maxChainLength) {
            maxChainLength = currentChainLength;
        }
        return maxChainLength;
    }
}
