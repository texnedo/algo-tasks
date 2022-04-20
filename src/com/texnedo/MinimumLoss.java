package com.texnedo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinimumLoss {
    public static int minimumLoss(List<Long> price) {
        ArrayList<long[]> values = new ArrayList<>(price.size());
        for (int i = 0; i < price.size(); i++) {
            values.add(new long[] {i, price.get(i)});
        }
        values.sort(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return -Long.compare(o1[1], o2[1]);
            }
        });
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i - 1)[0] < values.get(i)[0]) {
                long currentDiff = values.get(i - 1)[1] - values.get(i)[1];
                if (currentDiff > 0 && currentDiff < minDiff) {
                    minDiff = (int) currentDiff;
                }
            }
        }
        return minDiff;
    }
}
