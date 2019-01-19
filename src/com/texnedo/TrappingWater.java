package com.texnedo;

public class TrappingWater {
    public int trap(int[] height) {
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
