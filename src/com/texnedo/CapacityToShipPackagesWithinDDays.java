package com.texnedo;

import java.util.Arrays;
import java.util.Locale;

public class CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        CapacityToShipPackagesWithinDDays days = new CapacityToShipPackagesWithinDDays();
        int[] data = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(days.shipWithinDays(data, 5));
    }

    public int shipWithinDays(int[] weights, int D) {
        Integer[][] computed = new Integer[D][weights.length];
        int globalMinCapacity = Integer.MIN_VALUE;
        for (int i = 0; i < weights.length; i++) {
            if (globalMinCapacity < weights[i]) {
                globalMinCapacity = weights[i];
            }
        }
        return shipWithinDaysInternal(weights, D, 0, computed, globalMinCapacity);
    }

    public int shipWithinDaysInternal(int[] weights, int D, int index,
                                      Integer[][] computed, int globalMinCapacity) {
        if (weights.length == 0) {
            return 0;
        }
        if (D == 0) {
            return 0;
        }
        if (computed[D - 1][index] != null) {
            return computed[D - 1][index];
        }
        if (D == 1) {
            int total = 0;
            for (int i = index; i < weights.length; i++) {
                total += weights[i];
            }
            computed[D - 1][index] = total;
            System.out.println(String.format(Locale.US, "day = %d - (%d, %d), capacity = %d",
                    D, index, weights.length - 1, total));
            return total;
        }
        if (index == weights.length - 1) {
            System.out.println(String.format(Locale.US, "day = %d - (%d, %d), capacity = %d",
                    D, index, weights.length - 1, weights[index]));
            computed[D - 1][index] = weights[index];
            return weights[index];
        }
        int capacityForThisDay = weights[index];
        int minCapacity = Integer.MAX_VALUE;
        for (int i = index + 1; i < weights.length; i++) {
            int capacity = Math.max(capacityForThisDay,
                    shipWithinDaysInternal(weights, D - 1, i, computed, globalMinCapacity));
            if (minCapacity > capacity) {
                minCapacity = capacity;
            }
            capacityForThisDay += weights[i];
        }
        computed[D - 1][index] = minCapacity;
        System.out.println(String.format(Locale.US, "day = %d - (%d, %d), capacity = %d",
                D, index, weights.length - 1, minCapacity));
        return minCapacity;
    }
}
