package com.texnedo;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 *        *
 *    *   ** *
 * _*_**_*****
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2].
 * min(1,2) = 1, length = 1 => 1
 * min(2,3) = 2, length = 3 => 3
 * min(1,1) = 1, length = 1 => 1
 * min(2,2) = 2, length = 1 => 1
 * total = 6
 *
 * In this case, 6 units of rain water (blue section) are being trapped.
 *
 *  *  *
 *  *  *      *
 *  *  **  *  *  *
 * _*__*****__****
 * [0,4,0,0,4,2,1,1,2,0,0,3,1,1,2]
 * 8 + (18 - (2 + 2 + 2)) + 2 = 22
 *
 * [5,5,1,7,1,1,5,2,7,6]
 * 4 + (28 - 9) = 23
 *
 * [5,5,4,7,8,2,6,9,4,5]
 * 1 + 1 + 6 + 2
 * */
public class TrappingWater {
    public static void main(String[] args) {
        TrappingWater trapper = new TrappingWater();
        int[] data = {0,1,0,2,1,0,1,3,2,1,2};
        System.out.println(trapper.trap(data));
        int[] data1 = {0,4,0,0,4,2,1,1,2,0,0,3,1,1,2};
        System.out.println(trapper.trap(data1));
        int[] data2 = {5,2,1,2,1,6};
        System.out.println(trapper.trap(data2));
        int[] data3 = {5,5,1,7,1,1,5,2,7,6};
        System.out.println(trapper.trap(data3));
        int[] data4= {5,5,4,7,8,2,6,9,4,5};
        System.out.println(trapper.trap(data4));
    }

    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        LinkedList<Integer> maxHeightsIndexes = new LinkedList<>();
        int trappedWater = 0;
        int heightBefore = 0;
        int maxHeightBefore = 0;
        for (int i = 0; i < height.length; i++) {
            while (maxHeightsIndexes.size() != 0 &&
                    maxHeightBefore > height[maxHeightsIndexes.peekLast()] &&
                    height[maxHeightsIndexes.peekLast()] < height[i]) {
                maxHeightsIndexes.removeLast();
            }
            if (heightBefore < height[i] &&
                    (i == height.length - 1 || height[i + 1] <= height[i])) {
                maxHeightsIndexes.offerLast(i);
                if (maxHeightBefore < height[i]) {
                    maxHeightBefore = height[i];
                }
            }
            heightBefore = height[i];
        }
        while (maxHeightsIndexes.size() > 1) {
            int left = maxHeightsIndexes.removeFirst();
            int right = maxHeightsIndexes.peekFirst();
            int minBorderHeight = Math.min(height[left], height[right]);
            int maxTrappedWater = minBorderHeight * (right - left - 1);
            for (int i = left + 1; i < right; i++) {
                maxTrappedWater -= Math.min(height[i], minBorderHeight);
            }
            trappedWater += maxTrappedWater;
        }
        return trappedWater;
    }

    public int trap2(int[] height) {
        int maxIndex = -1;
        int trappedVolume = 0;
        int trapStart = -1;
        for (int i = 0; i < height.length; i++) {
            if (maxIndex == - 1) {
                if (height[i] == 0) {
                    //can't be a trap, skip
                    continue;
                }
                //init first height, skip
                maxIndex = i;
            } else {
                if (height[i] > height[maxIndex]) {
                    //can't collect water, skip
                    maxIndex = i;
                    if (trapStart != -1) {

                    }
                } else {
                    trapStart = maxIndex;
                }
            }
        }
        return trappedVolume;
    }
}
