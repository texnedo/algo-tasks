package com.texnedo;

import java.util.Stack;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        ContainerWithMostWater water = new ContainerWithMostWater();
        int[] data = {1,8,6,2,5,4,8,3,7};
        int[] data1 = {1,3,2,5,25,24,5};
        System.out.println(water.maxArea(data));
        System.out.println(water.maxArea(data1));
    }

    public int maxArea(int[] height) {
        /**
         * task to find two max borders with max distance between them
         * [1,8,6,2,5,4,8,3,7]
         *  0,1,2,3,4,5,6,7,8
         *  8 - 7 = 7, 7 * min(7, 8) = 49
         * */
        int maxArea = 0;
        int first = 0;
        int last = height.length - 1;
        while (first < last) {
            int area = Math.min(height[first], height[last]) * (last - first);
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[first] > height[last]) {
                last--;
            } else {
                first++;
            }

        }
        return maxArea;
    }
}
