package com.texnedo;

import java.util.Arrays;

public class MoveZeros {
    public static void main(String[] args) {
        MoveZeros moveZeros = new MoveZeros();
        int[] data = {0,1,0,3,12};
        moveZeros.moveZeroes(data);
        System.out.println(Arrays.toString(data));
    }

    public void moveZeroes(int[] nums) {
        int firstZeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (firstZeroIndex == -1) {
                    firstZeroIndex = i;
                }
                continue;
            }
            if (firstZeroIndex == -1) {
                continue;
            }
            nums[firstZeroIndex] = nums[i];
            nums[i] = 0;
            if (firstZeroIndex < nums.length - 1 && nums[firstZeroIndex + 1] == 0) {
                firstZeroIndex++;
            } else {
                firstZeroIndex = -1;
            }
        }
    }
}
