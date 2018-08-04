package com.texnedo;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.builder.HistBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TruncatedFibonacci {
    public static void main(String[] args) throws Exception {
        //long ts = System.nanoTime();
        System.out.println(solution(10000000));
        //System.out.println("Diff0 = " + (System.nanoTime() - ts));
        //long ts1 = System.nanoTime();
        System.out.println(solutionOld(10000000));
        //System.out.println("Diff1 = " + (System.nanoTime() - ts1));

        int COUNT = 1005000;

        List<Integer> iterations = new ArrayList<>(COUNT);
        List<Long> oldTs = new ArrayList<>(COUNT);
        List<Long> newTs = new ArrayList<>(COUNT);
        for (int i = 1000000; i < COUNT; i++) {
            long ts = System.nanoTime();
            int val1 = solutionOld(i);
            oldTs.add((System.nanoTime() - ts) / 1000);
            ts = System.nanoTime();
            int val2 = solution(i);
            newTs.add((System.nanoTime() - ts) / 1000);
            iterations.add(i);
            if (val1 != val2) {
                throw new Exception();
            }
        }
        Plot plt = Plot.create();
        plt.plot()
                .add(iterations, newTs, ".")
                .add(iterations, oldTs, "-")
                .label("new")
                .linestyle("-");
        plt.show();
        plt.executeSilently();

    }

    public static int solutionOld(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 1;
        }
        long previous = 1;
        long current = 1;
        for (int i = 3; i <= N; i++) {
            long tmp = current;
            current = (int)((tmp + previous) % 1000000);
            previous = tmp;
        }
        return (int)current;
    }

    public static int solution(int N) {
        HashMap<Integer, Integer> computedValues = new HashMap<>();
        return solutionInternal(N, computedValues);
    }

    public static int solutionInternal(int N, HashMap<Integer, Integer> computedValues) {
        if (N < 0) {
            return 0;
        }
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 1;
        }
        Integer result = computedValues.get(N);
        if (result != null) {
            return result;
        }
        /**
         * According to Fibonacci matrix form definitions we could compute
         * fibonacci sequence value with special equation, which allows us to divide the task by 2, what
         * leads to O(logN) complexity.
         * */
        if (N % 2 == 0) {
            int subN = N / 2;
            long subF = solutionInternal(subN, computedValues);
            long subF1 = solutionInternal(subN - 1, computedValues);
            result = (int)(((2 * subF1 + subF) * subF) % 1000000);
        } else {
            int subN = (int)(((long)N + 1) / 2);
            long subF = solutionInternal(subN, computedValues);
            long subF1 = solutionInternal(subN - 1, computedValues);
            result = (int)((subF * subF + subF1 * subF1) % 1000000);
        }
        /**
         * Also we could use memorization to cut down already computed recursion branches and improve
         * little bit the overall performance.
         * */
        computedValues.put(N, result);
        return result;
    }
}
