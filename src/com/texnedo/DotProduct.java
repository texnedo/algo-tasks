package com.texnedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotProduct {
    public static void main(String[] args) {
        int[] data1 = {0,1,0,0,2,0,0};
        int[] data2 = {1,0,0,0,3,0,4};

        SparseVector vec1 = new SparseVector(data1);
        SparseVector vec2 = new SparseVector(data2);

        System.out.println(vec1.dotProduct(vec2));
    }

    static class SparseVector {
        private final int originalSize;
        private final HashMap<Integer, Integer> data = new HashMap<>();

        SparseVector(int[] nums) {
            originalSize = nums.length;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    data.put(i, nums[i]);
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            if (originalSize != vec.originalSize) {
                throw new IllegalArgumentException();
            }
            int product = 0;
            for (Map.Entry<Integer, Integer> item : data.entrySet()) {
                Integer vecValue = vec.data.get(item.getKey());
                if (vecValue != null) {
                    product += vecValue * item.getValue();
                }
            }
            return product;
        }
    }

    static class Range {
        int startIndex;
        int endIndex;
        int value;

        Range(int startIndex, int endIndex, int value) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.value = value;
        }

        public boolean fitsSingle(Range range) {
            return startIndex == endIndex &&
                    startIndex == range.startIndex &&
                    endIndex == range.endIndex;
        }

        public boolean fitsZeroRange(Range range) {
            return value == 0 &&
                    range.value == 0 &&
                    startIndex == range.startIndex &&
                    endIndex == range.endIndex;
        }
    }

    static class SparseVectorList {
        private final List<Range> data = new ArrayList<>();

        SparseVectorList(int[] nums) {
            Range zeroRange = null;
            for (int i = 0; i < nums.length; i++) {
                int value = nums[i];
                if (value != 0) {
                    if (zeroRange != null) {
                        zeroRange.endIndex = i - 1;
                        data.add(zeroRange);
                        zeroRange = null;
                    }
                    data.add(new Range(i, i, value));
                } else {
                    if (zeroRange == null) {
                        zeroRange = new Range(i, i, 0);
                    } else {
                        zeroRange.endIndex = i;
                    }
                }
            }
            if (zeroRange != null) {
                zeroRange.endIndex = nums.length - 1;
                data.add(zeroRange);
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVectorList vec) {
            if (vec.data.size() != data.size()) {
                throw new IllegalArgumentException();
            }
            int product = 0;
            int it1 = 0;
            int it2 = 0;
            while (it1 < data.size() || it2 < vec.data.size()) {
                Range range1 = null;
                Range range2 = null;
                if (it1 < data.size()) {
                    range1 = data.get(it1);
                    //it1++;
                }
                if (it2 < vec.data.size()) {
                    range2 = vec.data.get(it2);
                    //it2++;
                }
                if (range1 != null && range2 != null) {
                    if (range1.fitsSingle(range2)) {
                        product += range1.value * range2.value;
                        it1++;
                        it2++;
                    } else if (range1.fitsZeroRange(range2)) {
                        it1++;
                        it2++;
                    }
                    //TODO -- support other combinations
                }
            }
            return product;
        }
    }

    static class SparseVectorSimple {
        private final int[] data;

        SparseVectorSimple(int[] nums) {
            data = nums;
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVectorSimple vec) {
            if (vec.data.length != data.length) {
                throw new IllegalArgumentException();
            }
            int product = 0;
            for (int i = 0; i < data.length; i++) {
                product += data[i] * vec.data[i];
            }
            return product;
        }
    }
}
