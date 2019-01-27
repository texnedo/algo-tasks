package com.texnedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return Collections.emptyList();
        }
        List<Integer> first = Collections.singletonList(1);
        if (numRows == 1) {
            return Collections.singletonList(first);
        }
        List<Integer> second = new ArrayList<>(2);
        second.add(1);
        second.add(1);
        List<List<Integer>> result = new ArrayList<>(numRows);
        result.add(first);
        result.add(second);
        for (int i = 2; i < numRows; i++) {
            List<Integer> current = new ArrayList<>(i);
            current.add(1);
            List<Integer> previous = result.get(i - 1);
            for (int j = 1; j < previous.size(); j++) {
                current.add(previous.get(j - 1) + previous.get(j));
            }
            current.add(1);
            result.add(current);
        }
        return result;
    }
}
