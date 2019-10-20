package com.texnedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomPickIndex {
    public static void main(String[] args) {
        int[] data = {1,2,3,3,3};
        Solution solution = new Solution(data);
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(2));
        System.out.println(solution.pick(1));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(3));
    }


    static class IndexData {
        private static final Random rnd = new Random();
        private final ArrayList<Integer> indexes = new ArrayList<>();
        private int untouchedSize = 0;

        void add(int index) {
            indexes.add(index);
            untouchedSize = indexes.size();
        }

        int getRandomIndex() {
            if (indexes.size() == 0) {
                throw new IllegalArgumentException();
            }
            if (indexes.size() == 1) {
                return indexes.get(0);
            }
            if (untouchedSize == 0) {
                untouchedSize = indexes.size();
            }
            final int indexToGet = rnd.nextInt(untouchedSize);
            final int result = indexes.get(indexToGet);
            final int indexToSwap = untouchedSize - 1;
            swap(indexToGet, indexToSwap);
            untouchedSize--;
            return result;
        }

        void swap(int left, int right) {
            int tmp = indexes.get(left);
            indexes.set(left, indexes.get(right));
            indexes.set(right, tmp);
        }
    }

    static class Solution {
        private final int[] nums;
        HashMap<Integer, IndexData> indexDataHashMap = new HashMap<>();

        public Solution(int[] nums) {
            this.nums = nums;
            for (int i = 0; i < nums.length; i++) {
                IndexData data = indexDataHashMap.get(nums[i]);
                if (data == null) {
                    data = new IndexData();
                    indexDataHashMap.put(nums[i], data);
                }
                data.add(i);
            }
        }

        public int pick(int target) {
            return indexDataHashMap.get(target).getRandomIndex();
        }
    }
}
