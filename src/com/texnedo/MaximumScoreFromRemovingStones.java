package com.texnedo;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumScoreFromRemovingStones {
    /**
     * a = 4, b = 4, c = 6
     *
     *
     * 6, 4, 4 | 0
     *
     * 5, 4, 3 | 1
     *
     * 4, 3, 3 | 2
     *
     * 3, 3, 2 | 3
     *
     * 2, 2, 2 | 4
     *
     * 2, 1, 1 | 5
     *
     * 1, 1, 0 | 6
     *
     * 0, 0, 0 | 7
     * */

    public static void main(String[] args) {
        MaximumScoreFromRemovingStones stones = new MaximumScoreFromRemovingStones();
        System.out.println(stones.maximumScore(2, 4,6));
    }

    public int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });
        queue.add(a);
        queue.add(b);
        queue.add(c);
        int score = 0;
        while (queue.size() > 1) {
            int first = queue.poll();
            if (queue.isEmpty()) {
                break;
            }
            int second = queue.poll();
            first--;
            second--;
            score++;
            if (first > 0) {
                queue.offer(first);
            }
            if (second > 0) {
                queue.offer(second);
            }
        }
        return score;
    }
}
