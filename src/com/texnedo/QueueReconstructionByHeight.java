package com.texnedo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QueueReconstructionByHeight {
    public static void main(String[] args) {
        QueueReconstructionByHeight height = new QueueReconstructionByHeight();
        int[][] data = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] result = height.reconstructQueue(data);
    }

    public int[][] reconstructQueue(int[][] people) {
        if (people == null) {
            throw new IllegalArgumentException();
        }
        if (people.length == 0) {
            return people;
        }
        if (people[0].length != 2) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int result = -Integer.compare(o1[0], o2[0]);
                if (result != 0) {
                    return result;
                }
                return Integer.compare(o1[1], o2[1]);
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        int[][] result = new int[people.length][people[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
