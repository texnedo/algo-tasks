package com.texnedo;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxBarArea {
    public static void main(String[] args) {
        MaxBarArea area = new MaxBarArea();
        int[] data = {6, 2, 5, 4, 5, 1, 6};
        System.out.println(area.compute(data));
    }

    public int compute(int[] bars) {
        if (bars == null || bars.length == 0) {
            return 0;
        }
        int maxArea = 0;
        int level = 1;
        System.out.println(Arrays.toString(bars));
        boolean hasAtLeastOneBar;
        do {
            hasAtLeastOneBar = false;
            boolean regionStarted = false;
            int regionCount = 0;
            for (int i = 0; i < bars.length; i++) {
                if (bars[i] != 0) {
                    if (!regionStarted) {
                        regionStarted = true;
                    }
                    regionCount++;
                    bars[i]--;
                    hasAtLeastOneBar = true;
                } else {
                    if (regionStarted) {
                        regionStarted = false;
                        int area = regionCount * level;
                        if (maxArea < area) {
                            maxArea = area;
                        }
                        regionCount = 0;
                    }
                }
            }
            if (regionStarted) {
                int area = regionCount * level;
                if (maxArea < area) {
                    maxArea = area;
                }
            }
            System.out.println(Arrays.toString(bars));
            System.out.println(maxArea);
            level++;
        } while (hasAtLeastOneBar);
        return maxArea;
    }
}
