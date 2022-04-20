package com.texnedo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BigSorting {
    public static List<String> bigSorting(List<String> unsorted) {
        unsorted.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareToIgnoreCase(o2);
                }
                return Integer.compare(o1.length(), o2.length());
            }
        });
        return unsorted;
    }
}
