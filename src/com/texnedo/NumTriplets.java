package com.texnedo;

import java.lang.reflect.Array;
import java.util.*;

public class NumTriplets {
    private static class Triplet {
        private int data[] = new int[3];

        Triplet(int i, int j, int k) {
            data[0] = i;
            data[1] = j;
            data[2] = k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triplet triplet = (Triplet) o;
            return Arrays.equals(data, triplet.data);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(data);
        }
    }

    public int numTriplets(int[] nums1, int[] nums2) {
        HashMap<Integer, List<Integer>> squares1 = new HashMap<>();
        computeSquares(nums1, squares1);
        HashMap<Integer, List<Integer>> squares2 = new HashMap<>();
        computeSquares(nums2, squares2);
        int result = 0;
        result += countTriplets(nums2, squares1);
        System.out.println();
        result += countTriplets(nums1, squares2);
        return result;
    }

    private int countTriplets(int[] nums,
                              HashMap<Integer, List<Integer>> squares) {
        HashSet<Triplet> triplets = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int mult = nums[i] * nums[j];
                List<Integer> indexes = squares.get(mult);
                if (indexes != null) {
                    for (Integer index : indexes) {
                        Triplet triplet = new Triplet(i, j, index);
                        System.out.println(String.format("%d - %d - %d", i, j, index));
                        triplets.add(triplet);
                    }
                }
            }
        }
        return triplets.size();
    }

    private void computeSquares(int[] nums, HashMap<Integer, List<Integer>> squares) {
        for (int i = 0; i < nums.length; i++) {
            int square = nums[i] * nums[i];
            List<Integer> result1 = squares.computeIfAbsent(square, k -> new ArrayList<>());
            result1.add(i);
        }
    }

    public static void main(String[] args) {
        NumTriplets triplets = new NumTriplets();
        int[] num1 = {7,7,8,3};
        int[] num2 = {1,2,9,7};
//        int[] num1 = {1,1};
//        int[] num2 = {1,1,1};
        System.out.println(triplets.numTriplets(num1, num2));
    }
}
