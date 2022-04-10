package com.texnedo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FourSum_v2 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> left = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                Integer existing = left.get(sum);
                if (existing == null) {
                    left.put(sum, 1);
                } else {
                    left.put(sum, existing + 1);
                }
;           }
        }
        HashMap<Integer, Integer> right = new HashMap<>();
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sum = nums3[i] + nums4[j];
                Integer existing = right.get(sum);
                if (existing == null) {
                    right.put(sum, 1);
                } else {
                    right.put(sum, existing + 1);
                }
            }
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> item : left.entrySet()) {
            int remaining = -item.getKey();
            int leftCount = item.getValue();
            Integer rightCount = right.get(remaining);
            if (rightCount != null) {
                count += leftCount * rightCount;
            }
        }
        return count;
    }
}
