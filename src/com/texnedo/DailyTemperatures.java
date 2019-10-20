package com.texnedo;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] data = {73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperatures tmp = new DailyTemperatures();
        System.out.println(Arrays.toString(tmp.dailyTemperatures(data)));
    }

    private class Day {
        final int index;
        final int temperature;

        private Day(int index, int temperature) {
            this.index = index;
            this.temperature = temperature;
        }
    }

    public int[] dailyTemperatures(int[] T) {
        if (T == null) {
            throw new IllegalArgumentException();
        }
        if (T.length == 0 || T.length == 1) {
            return new int[T.length];
        }
        final int[] result = new int[T.length];
        final Stack<Day> lowerDays = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!lowerDays.empty() && lowerDays.peek().temperature < T[i]) {
                final Day lower = lowerDays.pop();
                result[lower.index] = i - lower.index;
            }
            lowerDays.push(new Day(i, T[i]));
        }
        return result;
    }
}
