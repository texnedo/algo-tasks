package com.texnedo;

import java.util.Arrays;

public class CountingBits {
    public static void main(String[] args) {
        CountingBits bits = new CountingBits();
        System.out.println(Arrays.toString(bits.countBits(16)));
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        int currentBound = 4;
        int currentOffset = 0;
        for (int i = 0; i <= num; i++) {
            if (i < 4) {
                switch (i) {
                    case 0: {
                        result[i] = 0;
                        break;
                    }
                    case 1: {
                        result[i] = 1;
                        break;
                    }
                    case 2: {
                        result[i] = 1;
                        break;
                    }
                    case 3: {
                        result[i] = 2;
                        break;
                    }
                }
            } else {
                if (i == currentBound) {
                    result[i] = 1;
                    currentOffset = currentBound;
                    currentBound = currentBound << 1;
                } else {
                    result[i] = result[i - currentOffset] + 1;
                }
            }
        }
        return result;
    }
}
