package com.texnedo;

import java.util.HashSet;

public class DistributeCandies {
    public int distributeCandies(int[] candies) {
        HashSet<Integer> types = new HashSet<>();
        for (int i = 0; i < candies.length; i++) {
            types.add(candies[i]);
        }
        return Math.min(candies.length / 2, types.size());
    }
}
