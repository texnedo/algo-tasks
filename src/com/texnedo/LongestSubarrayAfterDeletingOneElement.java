package com.texnedo;

public class LongestSubarrayAfterDeletingOneElement {
    public static void main(String[] args) {
        LongestSubarrayAfterDeletingOneElement del = new LongestSubarrayAfterDeletingOneElement();
        int[] data = {0,1,1,1,0,1,1,0,1};
        System.out.println(del.longestSubarray(data));
        int[] data1 = {0,1,1,1,0,1,1,1};
        System.out.println(del.longestSubarray(data1));
        int[] data2 = {1,0,1,1,1,1,1,1,0,1,1,1,1,1};
        System.out.println(del.longestSubarray(data2));
    }

    public int longestSubarray(int[] nums) {
        int start = -1;
        int zeroCount = 0;
        int lastZeroIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (start == -1) {
                if (nums[i] == 0) {
                    continue;
                }
                start = i;
            } else {
                if (nums[i] == 0) {
                    if (zeroCount == 1) {
                        int length = i - start - zeroCount;
                        if (length > maxLength) {
                            maxLength = length;
                        }
                        if (nums[lastZeroIndex + 1] != 0) {
                            start = lastZeroIndex + 1;
                            zeroCount = 1;
                        } else {
                            start = -1;
                            zeroCount = 0;
                        }
                    } else {
                        lastZeroIndex = i;
                        zeroCount++;
                    }
                }
            }
        }
        if (start == 0 && zeroCount == 0) {
            zeroCount = 1;
        }
        if (start != -1) {
            int length = nums.length - start - zeroCount;
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }
}
