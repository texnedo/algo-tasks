package com.texnedo;

public class MaxConsecutiveOnesWithOneZero {
    public static void main(String[] args) {
        MaxConsecutiveOnesWithOneZero ones = new MaxConsecutiveOnesWithOneZero();
        int data[] = {1,1,1,1};
        System.out.println(ones.findMaxConsecutiveOnes(data));
        int data1[] = {1,1,1,1,0,1,1};
        System.out.println(ones.findMaxConsecutiveOnes(data1));
        int data2[] = {1,1,1,1,0,0,1,1};
        System.out.println(ones.findMaxConsecutiveOnes(data2));
        int data3[] = {1,1,1,1,0,0,1,1,0,1,1,1,0,0,1};
        System.out.println(ones.findMaxConsecutiveOnes(data3));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLength = 0;
        int currentLength = 0;
        boolean wasZero = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (currentLength != 0) {
                    if (wasZero) {
                        currentLength = 0;
                        wasZero = false;
                    } else {
                        wasZero = true;
                    }
                }
            } else {
                currentLength++;
            }
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
        }
        return maxLength;
    }
}
