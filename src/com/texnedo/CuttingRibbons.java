package com.texnedo;

import java.util.Arrays;

public class CuttingRibbons {
    public static void main(String[] args) {
        CuttingRibbons ribbons = new CuttingRibbons();
        int[] data = {7,5,9};
        int[] data1 = {100000,1,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000};
        int[] data2 = {100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000};
        System.out.println(ribbons.maxLength(data2, 51));
        System.out.println(ribbons.maxLength(data1, 50));
        System.out.println(ribbons.maxLength(data, 4));
    }

    public int maxLength(int[] ribbons, int k) {
        int minLength = 1;
        int maxLength = minLength;
        for (int i = 0; i < ribbons.length; i++) {
            if (ribbons[i] > maxLength) {
                maxLength = ribbons[i];
            }
        }
        int lastFixLength = 0;
        while (minLength <= maxLength) {
            int middleLength = minLength + (maxLength - minLength) / 2;
            int count = 0;
            for (int i = 0; i < ribbons.length; i++) {
                count += ribbons[i] / middleLength;
                if (count == k) {
                    return middleLength;
                }
            }
            if (count < k) {
                maxLength = middleLength - 1;
            } else {
                lastFixLength = middleLength;
                minLength = middleLength + 1;
            }
        }
        return lastFixLength;
    }
}
