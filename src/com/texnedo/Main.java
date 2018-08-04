package com.texnedo;

public class Main {
    public static int minSubArrayLen(int s, int[] nums) {
        if (s < 0 || nums.length == 0) {
            return 0;
        }
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    int length = j - i + 1;
                    if (length < minLength) {
                        minLength = length;
                    }
                    break;
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }

    public static int minSubArrayLen2(int s, int[] nums) {
        if (s < 0 || nums.length == 0) {
            return 0;
        }
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    int length = j - i + 1;
                    if (length < minLength) {
                        minLength = length;
                    }
                    break;
                }
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }
        return minLength;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,2,4,3};
	    minSubArrayLen(7, arr);
    }
}
