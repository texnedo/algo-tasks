package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * You need to implement the function pickIndex(), which randomly picks an index in the range
 * [0, w.length - 1] (inclusive) and returns it. The probability of picking
 * an index i is w[i] / sum(w).
 *
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25
 * (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 * */
public class RandomPickWithWeight {
    public static void main(String[] args) {
        int[] data = {1,3,10};
        int[] result = new int[data.length];
        Solution solution = new Solution(data);
        for (int i = 0; i < 100; i++) {
            int index = solution.pickIndex();
            result[index]++;
            System.out.println(index);
        }
        System.out.println(Arrays.toString(result));
    }

    static class Solution {
        private final int totalSum;
        private final int[] weightsSum;
        private final Random rnd = new Random();

        public Solution(int[] w) {
            int wSum = 0;
            weightsSum = new int[w.length];
            for (int i = 0; i < w.length; i++) {
               wSum += w[i];
               weightsSum[i] = wSum;
            }
            totalSum = wSum;
        }

        public int pickIndex() {
            final int random = rnd.nextInt(totalSum) + 1;
            final int result = Arrays.binarySearch(weightsSum, random);
            if (result >= 0) {
                return result;
            }
            return -result - 1;
        }

//        public int pickIndex() {
//            final int universeRandom = rnd.nextInt(100) + 1;
//            return pickIndexInternal(universeRandom, weights.length);
//        }
//
//        private int pickIndexInternal(int universeRandom, int length) {
//            if (length == 1) {
//                return 0;
//            }
//            final int randomIndex = rnd.nextInt(length);
//            final double indexProbability = weights[randomIndex] / weightsSum * 100;
//            if (universeRandom <= indexProbability) {
//                return randomIndex;
//            }
//            swap(weights, randomIndex, length - 1);
//            final int result = pickIndexInternal(universeRandom, length - 1);
//            swap(weights, randomIndex, length - 1);
//            return result;
//        }
//
//        private void swap(int[] data, int first, int second) {
//            int tmp = data[first];
//            data[first] = data[second];
//            data[second] = tmp;
//        }
    }
}
