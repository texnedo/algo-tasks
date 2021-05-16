package com.texnedo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class RelativeRanks {
    private static class Rank {
        final int score;
        final int index;

        private Rank(int score, int index) {
            this.score = score;
            this.index = index;
        }
    }

    public static String getTitle(int place) {
        switch (place) {
            case 1: {
                return "Gold Medal";
            }
            case 2: {
                return "Silver Medal";
            }
            case 3: {
                return "Bronze Medal";
            }
            default: {
                return Integer.toString(place);
            }
        }
    }

    public String[] findRelativeRanks(int[] score) {
        PriorityQueue<Rank> queue = new PriorityQueue<>(new Comparator<Rank>() {
            @Override
            public int compare(Rank o1, Rank o2) {
                return -Integer.compare(o1.score, o2.score);
            }
        });
        for (int i = 0; i < score.length; i++) {
            queue.add(new Rank(score[i], i + 1));
        }
        String[] result = new String[score.length];
        int place = 1;
        while (!queue.isEmpty()) {
            Rank rank = queue.poll();
            result[rank.index] = getTitle(place);
            place++;
        }
        return result;
    }
}
