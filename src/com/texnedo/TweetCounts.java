package com.texnedo;

import java.util.*;

public class TweetCounts {
    public static void main(String[] args) {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);
        System.out.println(tweetCounts
                .getTweetCountsPerFrequency("minute", "tweet3", 0, 59));
        System.out.println(tweetCounts
                .getTweetCountsPerFrequency("minute", "tweet3", 0, 60));
        tweetCounts.recordTweet("tweet3", 120);
        System.out.println(tweetCounts
                .getTweetCountsPerFrequency("hour", "tweet3", 0, 210));
    }

    private HashMap<String, TreeSet<Integer>> tweets = new HashMap<>();
    private static HashMap<String, Integer> times = new HashMap<>();
    static {
        times.put("minute", 60);
        times.put("hour", 60 * 60);
        times.put("day", 60 * 60 * 24);
    }

    public TweetCounts() {

    }

    public void recordTweet(String tweetName, int time) {
        if (tweetName == null || tweetName.length() == 0 || time < 0) {
            throw new IllegalArgumentException();
        }
        TreeSet<Integer> timeline = tweets.computeIfAbsent(tweetName, k -> new TreeSet<>());
        timeline.add(time);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq,
                                                    String tweetName,
                                                    int startTime,
                                                    int endTime) {
        if (freq == null || tweetName == null || startTime > endTime
                || startTime < 0) {
            throw new IllegalArgumentException();
        }
        Integer interval = times.get(freq);
        if (interval == null) {
            throw new IllegalArgumentException();
        }
        TreeSet<Integer> timeline = tweets.get(tweetName);
        if (timeline == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new LinkedList<>();
        for (int i = startTime; i <= endTime; i += interval) {
            SortedSet<Integer> result = timeline.subSet(i, true,
                    Math.min(i + interval - 1, endTime), true
            );
            list.add(result.size());
        }
        return list;
    }
}
