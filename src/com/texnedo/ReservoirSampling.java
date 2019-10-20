package com.texnedo;

import java.util.Arrays;
import java.util.Random;

public class ReservoirSampling {
    public static void main(String[] args) {
        ReservoirSampling sampling = new ReservoirSampling(20);
        for (int i = 0; i < 1000000; i++) {
            sampling.add(i);
            System.out.println(Arrays.toString(sampling.samples));
        }
    }

    private final Random rnd = new Random();
    private final int[] samples;
    private int count;

    public ReservoirSampling(int size) {
        this.samples = new int[size];
    }

    public void add(int value) {
        if (count < samples.length) {
            samples[count] = value;
        } else {
            int index = rnd.nextInt(count);
            if (index < samples.length) {
                samples[index] = value;
            }
        }
        count++;
    }
}
