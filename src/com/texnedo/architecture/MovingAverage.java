package com.texnedo.architecture;

import java.util.LinkedList;

public class MovingAverage {
    final int maxWindowSize;
    int runningSum = 0;
    LinkedList<Integer> window = new LinkedList<>();

    public MovingAverage(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        maxWindowSize = size;
    }

    public double next(int val) {
        runningSum += val;
        window.offer(val);
        if (window.size() > maxWindowSize && !window.isEmpty()) {
            runningSum -= window.poll();
        }
        return (double) runningSum / window.size();
    }
}
