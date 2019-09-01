package com.texnedo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class RandomFlipMatrix_v2 {
    private final int rowsCount;
    private final int colsCount;
    private final Random rnd = new Random();
    private final LinkedList<Range> ranges = new LinkedList<>();

    private static class Range {
        private final int start;
        private final int end;

        private Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        RandomFlipMatrix_v2 matrix = new RandomFlipMatrix_v2(3, 4);
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println();
        matrix.reset();
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
    }

    public RandomFlipMatrix_v2(int n_rows, int n_cols) {
        rowsCount = n_rows;
        colsCount = n_cols;
        ranges.add(new Range(0, rowsCount * colsCount - 1));
    }

    public int[] flip() {
        //pick available range
        final Range range = ranges.pollFirst();
        if (range == null) {
            return null;
        }
        final int rangeLength = range.end - range.start + 1;
        final int randomShift = rnd.nextInt(rangeLength);
        final int randomIndex = range.start + randomShift;
        //build result
        final int[] result = new int[2];
        result[0] = randomIndex / colsCount;
        result[1] = randomIndex % colsCount;
        //split range into two
        if (range.end != randomIndex) {
            final Range right = new Range(randomIndex + 1, range.end);
            ranges.addFirst(right);
        }
        if (range.start != randomIndex) {
            final Range left = new Range(range.start, randomIndex - 1);
            ranges.addFirst(left);
        }
        return result;
    }

    public void reset() {
        ranges.clear();
        ranges.add(new Range(0, rowsCount * colsCount - 1));
    }
}
