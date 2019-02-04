package com.texnedo;

import java.util.Arrays;

public class SortColors {
    public static void main(String[] args) {
        int [] data = {2,0,2,1,1,0};
        SortColors colors = new SortColors();
        colors.sortColors(data);
        System.out.println(Arrays.toString(data));
    }

    public void sortColors(int[] nums) {
        int[] colors = new int[3];
        for (int i = 0; i < nums.length; i++) {
            colors[nums[i]]++;
        }
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (colors[j] <= 0) {
                j++;
            }
            nums[i] = j;
            colors[j]--;
        }
    }
}
